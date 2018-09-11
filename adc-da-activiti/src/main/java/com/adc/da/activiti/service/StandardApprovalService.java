package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.vo.StandardApprovalVO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.sys.util.UUIDUtils;
import org.activiti.engine.*;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <br>
 * <b>功能：</b>BUS_PROCESS BusProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-31 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("standardApprovalService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StandardApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(StandardApprovalService.class);

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
     * @MethodName:startBuyApprovalProcess
     * @author: yuzhong
     * @param:[buyApprovalEO,userId,processDefinitionKey,processInstanceId]
     * @return:void date: 2018年9月4日 10:24:08
     */
    public ProcessInstance startBuyApprovalProcess(StandardApprovalVO standardApprovalEO,
            String userId, String processDefinitionKey, String processInstanceId) throws Exception {
        //如果有流程Id，那么就删除这个流程，重新赋值流程变量再启动流程
        if (!StringUtils.isEmpty(processInstanceId)) {
            flowProcessUtil.deleteByProcessInstance(processInstanceId);
        }
        //设置流程发起人id
        identityService.setAuthenticatedUserId(userId);
        //设置此流程的流程变量
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("processNumber", standardApprovalEO.getProcessNumber());
        map.put("processType", standardApprovalEO.getProcessType());
        map.put("processDescription", standardApprovalEO.getProcessDescription());
        map.put("applicat", userId);
        map.put("file", standardApprovalEO.getFileUrl());
        map.put("assignerUserList",standardApprovalEO.getAssignerUserList());
        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);

        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getProcessInstanceId())
                .singleResult();

        taskService.setAssignee(task.getId(),userId);
        //拿流程Id去关联表查一下，有数据证明那么需要更新，没有数据，需要新增
        if (!StringUtils.isEmpty(processInstanceId)) {
            BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
            busExecuProcessEOPage.setProcInstId(processInstanceId);
            List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
            if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                //往流程业务和流程的关联表添加数据
                BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
                busExecuProcessEO.setProcInstId(processInstance.getId());
                busExecuProcessEO.setId(busExecuProcessEOList.get(0).getId());
                busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
                //往流程业务表添加数据
                BusProcessEO busProcessEO = new BusProcessEO();
                busProcessEO.setProcessNumber(standardApprovalEO.getProcessNumber());
                busProcessEO.setProcessType(standardApprovalEO.getProcessType());
                busProcessEO.setCreateUserId(userId);
                busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                //1代表未完成
                busProcessEO.setProcessStatus("1");
                busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
            }
        } else {
            //往流程业务表添加数据
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setProcessNumber(standardApprovalEO.getProcessNumber());
            busProcessEO.setProcessType(standardApprovalEO.getProcessType());
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
        return processInstance;
    }

    /**
     * 完成第一步发起审批
     *
     * @MethodName:completeFirstApproval
     * @author: yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public String completeFirstApproval(StandardApprovalVO standardApprovalVO,String processInstanceId, String nowUserId, String comment) throws Exception {
        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (!StringUtils.isEmpty(comment)) {
            // 由于流程用户上下文对象是线程独立的，所以要在需要的位置设置，要保证设置和获取操作在同一个线程中
            Authentication.setAuthenticatedUserId(nowUserId);//批注人的名称  一定要写，不然查看的时候不知道人物信息
            // 添加批注信息
            taskService.addComment(task.getId(), null, comment);//comment为批注内容
        }

        //将提交申请第一步任务走完 即向后执行一步
        taskService.complete(task.getId());

        //获取当前执行的任务(已经是第二步了)
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();
        if (taskList != null && !taskList.isEmpty()) {
            //设置下一步的代办人
            int i = 0;
            for (Task taskNow : taskList) {
                taskService.setAssignee(taskNow.getId(), standardApprovalVO.getAssignerUserList().get(i));
                i++;
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
        }
        return processInstanceId;
    }

    /**
     * 流程完成审批
     *
     * @MethodName:completeProcess
     * @author: yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public String completeProcess(String processInstanceId, String nowUserId, String comment) throws Exception {
        //通过代办人，获取该人需要执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(nowUserId)
                .singleResult();
        if (!StringUtils.isEmpty(comment)) {
            // 由于流程用户上下文对象是线程独立的，所以要在需要的位置设置，要保证设置和获取操作在同一个线程中
            Authentication.setAuthenticatedUserId(nowUserId);//批注人的名称  一定要写，不然查看的时候不知道人物信息
            // 添加批注信息
            taskService.addComment(task.getId(), null, comment);//comment为批注内容
        }

        //将提交申请第一步任务走完 即向后执行一步
        taskService.complete(task.getId());

        /*
        *
        *
        * 需要加入该人所在部门的领导
        *
        * */

        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
                .parentId(processInstanceId).singleResult();
        if(execution!=null){
            //查询完成上一次任务后当前任务
            Task taskNow = taskService.createTaskQuery().executionId(task.getExecutionId()).singleResult();
            if (taskNow != null) {
                //设置下一步的代办人
                taskService.setAssignee(taskNow.getId(), "下一个人");
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
            }
        }else{
            Task taskNow = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            if(taskNow!=null){
                //设置下一步的代办人
                taskService.setAssignee(taskNow.getId(), "下一个人");
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
            }else{
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
        }
        return processInstanceId;
    }

    /**
     * 查看任务详情
     *
     * @MethodName:getTaskInfo
     * @author: yuzhong
     * @param:[taskId]
     * @return:Map date: 2018年9月5日 16:40:26
     */
    public Map<String, Object> getTaskInfo(String taskId, String processInstanceId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(processInstanceId).list();
        if (historicVariableInstanceList != null && !historicVariableInstanceList.isEmpty()) {
            for (HistoricVariableInstance hvi : historicVariableInstanceList) {
                resultMap.put(hvi.getVariableName(),hvi.getValue());
            }
        }
        List<Comment> commentList = taskService.getTaskComments(taskId);
        if(commentList!=null && !commentList.isEmpty()){
            for(Comment comment : commentList){
                resultMap.put("comment",comment.getFullMessage());
            }
        }
        return resultMap;
    }
}
