package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.activiti.vo.CorrigendaApprovalVO;
import com.adc.da.sys.util.UUIDUtils;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * <br>
 * <b>功能：</b>BUS_PROCESS BusProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-31 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("corrigendaApprovalService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CorrigendaApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(CorrigendaApprovalService.class);

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    @Autowired
    private BusExecuProcessEOService busExecuProcessEOService;

    @Autowired
    private BusProcessEOService busProcessEOService;

    /**
     * 启动流程
     *
     * @MethodName:startProcess
     * @author:yuzhong
     * @param:[corrigendaApprovalVO,userId,processDefinitionKey,processInstanceId]
     * @return:void date: 2018年9月4日 10:24:08
     */
    public String startProcess(CorrigendaApprovalVO corrigendaApprovalVO,
            String userId, String processDefinitionKey, String processInstanceId) throws Exception {
        //如果有流程Id，那么就删除这个流程，重新赋值流程变量再启动流程
        if (!StringUtils.isEmpty(processInstanceId)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("processNumber", corrigendaApprovalVO.getProcessNumber());
            map.put("processType", corrigendaApprovalVO.getProcessType());
            map.put("processDescription", corrigendaApprovalVO.getProcessDescription());
            map.put("applicat", userId);
            map.put("fileList",corrigendaApprovalVO.getFileIdList());
            map.put("corrigendaApprovalVOList",corrigendaApprovalVO.getCorrigendaApprovalList());
            runtimeService.setVariables(processInstanceId,map);
            //往流程业务表修改数据
            BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
            busExecuProcessEOPage.setProcInstId(processInstanceId);
            List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
            if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                //往流程业务和流程的关联表修改数据
                BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
                busExecuProcessEO.setProcInstId(processInstanceId);
                busExecuProcessEO.setId(busExecuProcessEOList.get(0).getId());
                busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
                //往流程业务表修改数据
                BusProcessEO busProcessEO = new BusProcessEO();
                busProcessEO.setProcessNumber(corrigendaApprovalVO.getProcessNumber());
                busProcessEO.setProcessType(corrigendaApprovalVO.getProcessType());
                busProcessEO.setCreateUserId(userId);
                busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                //1代表未完成
                busProcessEO.setProcessStatus("1");
                busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
            }
        }else{
            //设置流程发起人id
            identityService.setAuthenticatedUserId(userId);
            //设置此流程的流程变量
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("processNumber", corrigendaApprovalVO.getProcessNumber());
            map.put("processType", corrigendaApprovalVO.getProcessType());
            map.put("processDescription", corrigendaApprovalVO.getProcessDescription());
            map.put("applicat", userId);
            map.put("fileList",corrigendaApprovalVO.getFileIdList());
            map.put("corrigendaApprovalVOList", corrigendaApprovalVO.getCorrigendaApprovalList());
            //启动流程
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);

            processInstanceId = processInstance.getId();
            //获取当前执行的任务
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstance.getProcessInstanceId())
                    .singleResult();

            taskService.setAssignee(task.getId(),userId);

            //往流程业务表添加数据
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setProcessNumber(corrigendaApprovalVO.getProcessNumber());
            busProcessEO.setProcessType(corrigendaApprovalVO.getProcessType());
            busProcessEO.setCreateUserId(userId);
            String busprocessId = UUIDUtils.randomUUID20();
            busProcessEO.setId(busprocessId);
            //1代表未完成
            busProcessEO.setProcessStatus("1");
            busProcessEO.setCreateTime(new Date(System.currentTimeMillis()));
            busProcessEOService.insertSelective(busProcessEO);
            //往流程业务和流程的关联表添加数据
            BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
            busExecuProcessEO.setProcessId(busprocessId);
            busExecuProcessEO.setProcInstId(processInstance.getId());
            busExecuProcessEO.setId(UUIDUtils.randomUUID20());
            busExecuProcessEOService.insertSelective(busExecuProcessEO);
        }
        return processInstanceId;
    }

    /**
     * 完成审批
     *
     * @MethodName:completeApproval
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId,comment]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public String completeApproval(String processInstanceId, String nowUserId, String comment) throws Exception {
        //获取当前执行的任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                .taskAssignee(nowUserId).singleResult();
        if (!StringUtils.isEmpty(comment)) {
//            // 由于流程用户上下文对象是线程独立的，所以要在需要的位置设置，要保证设置和获取操作在同一个线程中
//            Authentication.setAuthenticatedUserId(nowUserId);//批注人的名称  一定要写，不然查看的时候不知道人物信息
//            // 添加批注信息
//            taskService.addComment(task.getId(), null, comment);//comment为批注内容
            task.setDescription(comment);//设置意见
            taskService.saveTask(task);
        }

        if(!StringUtils.isEmpty(task.getOwner())){
            taskService.resolveTask(task.getId());
        }

        //将提交申请第一步任务走完 即向后执行一步
        taskService.complete(task.getId());

        //获取当前执行的任务(已经是第二步了)
        Task task2 = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (task2 != null) {
            String taskDefKey = task2.getTaskDefinitionKey();
            //如果是最后一步标准工程师接收，那么审批人要指定为标准工程师
            if(taskDefKey.equals("usertask6")){
                List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId)
                        .taskDefinitionKey("usertask2").orderByTaskCreateTime().desc().list();
                if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
                    taskService.setAssignee(task2.getId(),historicTaskInstanceList.get(0).getAssignee());
                }
            }else{
                //设置下一步的代办人
                taskService.setAssignee(task2.getId(), "下一个人");
            }

            //修改业务表的上一操作人
            BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
            busExecuProcessEOPage.setProcInstId(processInstanceId);
            List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
            if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                BusProcessEO busProcessEO = new BusProcessEO();
                busProcessEO.setLastUserId(nowUserId);
                busProcessEO.setLastUserName("查出来是谁");
                busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
            }
        } else {
            //修改业务表的上一操作人，完成状态，完成时间
            BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
            busExecuProcessEOPage.setProcInstId(processInstanceId);
            List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
            if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                BusProcessEO busProcessEO = new BusProcessEO();
                busProcessEO.setLastUserId(nowUserId);
                busProcessEO.setLastUserName("查出来是谁");
                busProcessEO.setEndTime(new Date(System.currentTimeMillis()));
                busProcessEO.setProcessStatus("2");
                busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
            }
        }
        return processInstanceId;
    }

    /**
     * 查看任务详情
     *
     * @MethodName:getTaskInfo
     * @author:yuzhong
     * @param:[taskId]
     * @return:Map
     * date: 2018年9月5日 16:40:26
     */
    public Map<String, Object> getTaskInfo(String taskId, String processInstanceId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //获取历史的流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(processInstanceId).list();
        if (historicVariableInstanceList != null && !historicVariableInstanceList.isEmpty()) {
            for (HistoricVariableInstance hvi : historicVariableInstanceList) {
                resultMap.put(hvi.getVariableName(),hvi.getValue());
            }
        }
        //获取历史的审批意见
        List<Map<String,String>> commentList = new ArrayList<Map<String,String>>();
//        List<Comment> comments = taskService.getProcessInstanceComments(processInstanceId);
//        if(comments!=null && !comments.isEmpty()){
//            for(Comment comment : comments){
//                Map<String,String> map = new HashMap<String,String>();
//                map.put("审批人",comment.getUserId());
//                map.put("审批意见",comment.getFullMessage());
//                commentList.add(map);
//            }
//        }
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc().
                processInstanceId(processInstanceId).list();
        if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
            for(HistoricTaskInstance historicTaskInstance : historicTaskInstanceList){
                Map<String, String> map = new HashMap<String, String>();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(historicTaskInstance.getDescription()!=null
                        && !StringUtils.isEmpty(historicTaskInstance.getDescription())){
                    map.put("审批人", historicTaskInstance.getAssignee());
                    map.put("审批日期", simpleDateFormat.format(historicTaskInstance.getStartTime()));
                    map.put("审批意见", historicTaskInstance.getDescription());
                }else{
                    map.put("发起人", historicTaskInstance.getAssignee());
                    map.put("发起日期", simpleDateFormat.format(historicTaskInstance.getStartTime()));
                }
                commentList.add(map);
            }
        }
        resultMap.put("comment",commentList);
        return resultMap;
    }

    /**
     * 驳回到任意节点
     *
     * @MethodName:reject
     * @author:yuzhong
     * @param:[processInstanceId,comment]
     * @return:void
     * date:2018年9月14日 09:29:10
     */
    public String reject(String processInstanceId,String nowUserId,String comment) throws Exception {
        String message = "";
        Task taskEntity =  taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        String destTaskKey = "";
        String nowTaskKey = taskEntity.getTaskDefinitionKey();
        //当前任务key
        switch (nowTaskKey){
        case "usertask2":
            destTaskKey = "usertask1";
            break;
        case "usertask3":
            destTaskKey = "usertask1";
            break;
        case "usertask4":
            destTaskKey = "usertask3";
            break;
        case "usertask5":
            destTaskKey = "usertask4";
            break;
        default:
            message = "无需驳回";
        }
        if(message.equals("无需驳回")){
            return message;
        }
        message = flowProcessUtil.rejectTask(processInstanceId,nowUserId,destTaskKey,comment);
        //修改业务表的上一操作人
        BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
        busExecuProcessEOPage.setProcInstId(processInstanceId);
        List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
        if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId(nowUserId);
            busProcessEO.setLastUserName("查出来是谁");
            busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
            busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
        }
        return message;
    }
}
