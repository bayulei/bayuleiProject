package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusNoticecheckProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.activiti.page.BusNoticecheckProcessEOPage;
import com.adc.da.activiti.vo.NoticeAndCheckApprovalVO;
import com.adc.da.sys.util.UUIDUtils;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
//@Service("noticeAndCheckApprovalService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class NoticeAndCheckApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(NoticeAndCheckApprovalService.class);

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

    @Autowired
    private BusNoticecheckProcessEOService busNoticecheckProcessEOService;

    /**
     * 启动流程
     *
     * @MethodName:startProcess
     * @author: yuzhong
     * @param:[standardApprovalVO,userId,processDefinitionKey,processInstanceId]
     * @return:void date: 2018年9月4日 10:24:08
     */
    public String startProcess(NoticeAndCheckApprovalVO noticeAndCheckApprovalVO,
            String userId, String processDefinitionKey, String processInstanceId) throws Exception {
        //如果有流程Id，那么修改流程变量，不要重复启动流程
        if (!StringUtils.isEmpty(processInstanceId)) {
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId)
                    .singleResult();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("processNumber", noticeAndCheckApprovalVO.getProcessNumber());
            map.put("processType", noticeAndCheckApprovalVO.getProcessType());
            map.put("processDescription", noticeAndCheckApprovalVO.getProcessDescription());
            map.put("fileList", noticeAndCheckApprovalVO.getFileIdList());
            map.put("assignerUserList",noticeAndCheckApprovalVO.getAssignerUserList());
            map.put("endTime",noticeAndCheckApprovalVO.getEndTime());
            map.put("project",noticeAndCheckApprovalVO.getProject());
            map.put("workRequirements",noticeAndCheckApprovalVO.getWorkRequirements());
            if (!StringUtils.isEmpty(noticeAndCheckApprovalVO.getApprovalOpinion())) {
                //设置意见
                task.setDescription(noticeAndCheckApprovalVO.getApprovalOpinion());
                taskService.saveTask(task);
            }
            switch (task.getName()){
                case "法规管理工程师发起流程":
                    map.put("userId",userId);
                    map.put("applicat", userId);
                    BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
                    busExecuProcessEOPage.setProcInstId(processInstanceId);
                    List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
                    if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                        //往流程业务和流程的关联表添加数据
                        BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
                        busExecuProcessEO.setProcInstId(processInstanceId);
                        busExecuProcessEO.setId(busExecuProcessEOList.get(0).getId());
                        busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
                        //往流程业务表添加数据
                        BusProcessEO busProcessEO = new BusProcessEO();
                        busProcessEO.setProcessNumber(noticeAndCheckApprovalVO.getProcessNumber());
                        busProcessEO.setProcessType(noticeAndCheckApprovalVO.getProcessType());
                        busProcessEO.setCreateUserId(userId);
                        busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                        //1代表未完成
                        busProcessEO.setProcessStatus("1");
                        busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
                    }
                    break;
                case "法规接口人分发":
                    List<String> engineerList = new ArrayList<String>();
                    BusNoticecheckProcessEOPage page = new BusNoticecheckProcessEOPage();
                    page.setTaskId(task.getId());
                    List<BusNoticecheckProcessEO> list = busNoticecheckProcessEOService.queryByList(page);
                    if(list!=null && !list.isEmpty()){
                        busNoticecheckProcessEOService.deleteNoticeCheckInfo(task.getId());
                    }
                    List<BusNoticecheckProcessEO> busNoticecheckProcessEOS = noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList();
                    if (busNoticecheckProcessEOS != null && !busNoticecheckProcessEOS.isEmpty()) {
                        for (BusNoticecheckProcessEO busNoticecheckProcessEO : busNoticecheckProcessEOS) {
                            busNoticecheckProcessEO.setId(UUIDUtils.randomUUID20());
                            busNoticecheckProcessEO.setTaskId(task.getId());
                            busNoticecheckProcessEO.setProcInstId(processInstanceId);
                            busNoticecheckProcessEO.setResponsiblemanid(userId);
                            busNoticecheckProcessEOService.insertSelective(busNoticecheckProcessEO);
                            engineerList.add(busNoticecheckProcessEO.getEngineerid());
                        }
                    }
                    taskService.setVariable(task.getId(),"engineerList",engineerList);
                    taskService.setVariable(task.getId(),"flag",0);
                    break;
                case "工程师填写反馈意见":
                    BusNoticecheckProcessEOPage busNoticecheckProcessEOPage = new BusNoticecheckProcessEOPage();
                    busNoticecheckProcessEOPage.setEngineerid(userId);
                    busNoticecheckProcessEOPage.setProcInstId(processInstanceId);
                    busNoticecheckProcessEOPage.setTaskId(task.getParentTaskId());
                    List<BusNoticecheckProcessEO> busNoticecheckProcessEOList = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage);
                    if(busNoticecheckProcessEOList!=null && !busNoticecheckProcessEOList.isEmpty()){
                        BusNoticecheckProcessEO busNoticecheckProcessEO = busNoticecheckProcessEOList.get(0);
                        busNoticecheckProcessEO.setClausenum(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getClausenum());
                        busNoticecheckProcessEO.setClausecontent(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getClausecontent());
                        busNoticecheckProcessEO.setIsqualified(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getIsqualified());
                        busNoticecheckProcessEO.setPlan(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getPlan());
                        busNoticecheckProcessEO.setProject(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getProject());
                        busNoticecheckProcessEO.setSolution(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getSolution());
                        busNoticecheckProcessEOService.updateByPrimaryKeySelective(busNoticecheckProcessEO);
                    }
                    break;
                case "法规接口人汇总":
                    List<BusNoticecheckProcessEO> busNoticecheckProcessEOList1 = noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList();
                    if(busNoticecheckProcessEOList1!=null && !busNoticecheckProcessEOList1.isEmpty()){
                        for(BusNoticecheckProcessEO busNoticecheckProcessEO : busNoticecheckProcessEOList1){
                            busNoticecheckProcessEOService.updateByPrimaryKeySelective(busNoticecheckProcessEO);
                        }
                    }
                    break;
            }
            runtimeService.setVariables(processInstanceId,map);
        }else{
            //设置流程发起人id
            identityService.setAuthenticatedUserId(userId);
            //设置此流程的流程变量
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("processNumber", noticeAndCheckApprovalVO.getProcessNumber());
            map.put("processType", noticeAndCheckApprovalVO.getProcessType());
            map.put("processDescription", noticeAndCheckApprovalVO.getProcessDescription());
            map.put("fileList", noticeAndCheckApprovalVO.getFileIdList());
            map.put("assignerUserList",noticeAndCheckApprovalVO.getAssignerUserList());
            map.put("endTime",noticeAndCheckApprovalVO.getEndTime());
            map.put("project",noticeAndCheckApprovalVO.getProject());
            map.put("workRequirements",noticeAndCheckApprovalVO.getWorkRequirements());
            map.put("userId",userId);
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
            busProcessEO.setProcessNumber(noticeAndCheckApprovalVO.getProcessNumber());
            busProcessEO.setProcessType(noticeAndCheckApprovalVO.getProcessType());
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
     * 完成后赋值待办人
     *
     * @MethodName:addAssignee
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public String addAssignee(Task task,NoticeAndCheckApprovalVO noticeAndCheckApprovalVO,String processInstanceId, String nowUserId) throws Exception {
        if("部门负责人审批".equals(task.getName())){
            //获取当前执行的任务(已经是第二步了)
            List<Task> taskList = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .list();
            if (taskList != null && !taskList.isEmpty()) {
                //设置下一步的代办人
                int i = 0;
                for (Task taskNow : taskList) {
                    taskService.setAssignee(taskNow.getId(), noticeAndCheckApprovalVO.getAssignerUserList().get(i));
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
        }else if("法规接口人分发".equals(task.getName())){
            List<String> engineerList = new ArrayList<String>();
            BusNoticecheckProcessEOPage page = new BusNoticecheckProcessEOPage();
            page.setProcInstId(processInstanceId);
            List<BusNoticecheckProcessEO> list = busNoticecheckProcessEOService.queryByList(page);
            if(list!=null && !list.isEmpty()){
                busNoticecheckProcessEOService.deleteNoticeCheckInfo(task.getId());
            }
            List<BusNoticecheckProcessEO> busNoticecheckProcessEOS = noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList();
            if (busNoticecheckProcessEOS != null && !busNoticecheckProcessEOS.isEmpty()) {
                for (BusNoticecheckProcessEO busNoticecheckProcessEO : busNoticecheckProcessEOS) {
                    busNoticecheckProcessEO.setId(UUIDUtils.randomUUID20());
                    busNoticecheckProcessEO.setTaskId(task.getId());
                    busNoticecheckProcessEO.setProcInstId(processInstanceId);
                    busNoticecheckProcessEO.setResponsiblemanid(nowUserId);
                    busNoticecheckProcessEOService.insertSelective(busNoticecheckProcessEO);
                    engineerList.add(busNoticecheckProcessEO.getEngineerid());
                }
            }

            //获取当前执行的任务(已经是第二步了)
            String parentTaskId = task.getId();
            String parentExecutionId = task.getExecutionId();
            //通过上一任务的执行对象Id获取开启子任务的总执行对象
            Execution execution = runtimeService.createExecutionQuery().parentId(parentExecutionId).singleResult();
            //通过获得子任务的执行对象获取具体开启多少个的子任务
            List<Execution> executionList = runtimeService.createExecutionQuery().parentId(execution.getId()).list();
            if(executionList!=null && !executionList.isEmpty()){
                int i = 0;
                for(Execution ex : executionList){
                    Task t = taskService.createTaskQuery().executionId(ex.getId()).singleResult();
                    t.setAssignee(engineerList.get(i));
                    t.setParentTaskId(parentTaskId);
                    taskService.saveTask(t);
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
        }else if("工程师填写反馈意见".equals(task.getName())){
            //变更工程师流程业务表
            BusNoticecheckProcessEOPage busNoticecheckProcessEOPage = new BusNoticecheckProcessEOPage();
            busNoticecheckProcessEOPage.setEngineerid(nowUserId);
            busNoticecheckProcessEOPage.setProcInstId(processInstanceId);
            busNoticecheckProcessEOPage.setTaskId(task.getParentTaskId());
            List<BusNoticecheckProcessEO> busNoticecheckProcessEOList = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage);
            if(busNoticecheckProcessEOList!=null && !busNoticecheckProcessEOList.isEmpty()){
                BusNoticecheckProcessEO busNoticecheckProcessEO = busNoticecheckProcessEOList.get(0);
                busNoticecheckProcessEO.setClausenum(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getClausenum());
                busNoticecheckProcessEO.setClausecontent(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getClausecontent());
                busNoticecheckProcessEO.setIsqualified(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getIsqualified());
                busNoticecheckProcessEO.setPlan(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getPlan());
                busNoticecheckProcessEO.setProject(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getProject());
                busNoticecheckProcessEO.setSolution(noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList().get(0).getSolution());
                busNoticecheckProcessEOService.updateByPrimaryKeySelective(busNoticecheckProcessEO);
            }
            List<HistoricTaskInstance> childTaskList = historyService.createHistoricTaskInstanceQuery().taskParentTaskId(task.getParentTaskId()).list();
            if(childTaskList!=null && !childTaskList.isEmpty()){
                //用num来计算未完成的有多少个子任务
                int num = 0;
                for(HistoricTaskInstance historicTaskInstance : childTaskList){
                    if(historicTaskInstance.getEndTime()==null){
                        num++;
                    }
                }
                //如果num是0，那么证明此子流程的子任务已经全部完成，那么就进去子任务的下一步
                if(num==0){
                    HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery().taskId(task.getParentTaskId()).singleResult();
                    Task t = taskService.createTaskQuery().executionId(parentTask.getExecutionId()).singleResult();
                    t.setAssignee(parentTask.getAssignee());
                    taskService.saveTask(t);
                }
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
        }else {
            if("法规接口人汇总".equals(task.getName())) {
                List<BusNoticecheckProcessEO> busNoticecheckProcessEOList1 = noticeAndCheckApprovalVO.getBusNoticecheckProcessEOList();
                if (busNoticecheckProcessEOList1 != null && !busNoticecheckProcessEOList1.isEmpty()) {
                    for (BusNoticecheckProcessEO busNoticecheckProcessEO : busNoticecheckProcessEOList1) {
                        busNoticecheckProcessEOService.updateByPrimaryKeySelective(busNoticecheckProcessEO);
                    }
                }
            }
            Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
                    .parentId(processInstanceId).singleResult();
            if (execution != null) {
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
            } else {
                Map map = runtimeService.getVariables(processInstanceId);
                map.put("userId","下一个人");
                runtimeService.setVariables(processInstanceId,map);
                Task taskNow = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
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
            }
        }
        return processInstanceId;
    }

    /**
     * 流程完成审批
     *
     * @MethodName:completeProcess
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public Task completeProcess(NoticeAndCheckApprovalVO noticeAndCheckApprovalVO,String processInstanceId, String nowUserId) throws Exception {
        //通过代办人，获取该人需要执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(nowUserId)
                .singleResult();
        if(task!=null) {
            //将提交申请第一步任务走完 即向后执行一步
            taskService.complete(task.getId());
        }
        return task;
    }

    /**
     * 查看任务详情
     *
     * @MethodName:getTaskInfo
     * @author:yuzhong
     * @param:[taskId]
     * @return:Map date: 2018年9月5日 16:40:26
     */
    public Map<String, Object> getTaskInfo(String nowUserId, String processInstanceId) throws Exception{
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                .taskAssignee(nowUserId).singleResult();
        //获取历史的流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(processInstanceId).list();
        if (historicVariableInstanceList != null && !historicVariableInstanceList.isEmpty()) {
            for (HistoricVariableInstance hvi : historicVariableInstanceList) {
                resultMap.put(hvi.getVariableName(),hvi.getValue());
            }
        }

        //获取工程师审批的列表（根据流程进行到哪一步，来判断需要展现列表数据）
        switch (task.getName()){
            case "法规接口人汇总":
                //获取接口人分发过的工程师的结果列表
                BusNoticecheckProcessEOPage busNoticecheckProcessEOPage = new BusNoticecheckProcessEOPage();
                busNoticecheckProcessEOPage.setResponsiblemanid(nowUserId);
                busNoticecheckProcessEOPage.setProcInstId(processInstanceId);
                busNoticecheckProcessEOPage.setTaskId(task.getId());
                List<BusNoticecheckProcessEO> busNoticecheckProcessEOList = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage);
                resultMap.put("engineerList",busNoticecheckProcessEOList);
                break;
            case "收文部门科室负责人审核":
                /*获取当前登录人所在科室*/
                BusNoticecheckProcessEOPage busNoticecheckProcessEOPage2 = new BusNoticecheckProcessEOPage();
                busNoticecheckProcessEOPage2.setOffice("aa");
                busNoticecheckProcessEOPage2.setProcInstId(processInstanceId);
                busNoticecheckProcessEOPage2.setTaskId(task.getId());
                List<BusNoticecheckProcessEO> busNoticecheckProcessEOList2 = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage2);
                resultMap.put("engineerList",busNoticecheckProcessEOList2);
                break;
            case "收文部门负责人审核":
                /*获取当前登录人所在部门*/
                BusNoticecheckProcessEOPage busNoticecheckProcessEOPage3 = new BusNoticecheckProcessEOPage();
                busNoticecheckProcessEOPage3.setDept("aa");
                busNoticecheckProcessEOPage3.setProcInstId(processInstanceId);
                busNoticecheckProcessEOPage3.setTaskId(task.getId());
                List<BusNoticecheckProcessEO> busNoticecheckProcessEOList3 = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage3);
                resultMap.put("engineerList",busNoticecheckProcessEOList3);
                break;
            case "发起人审批":
                //此流程下所有的部门所选工程师所有结果
                BusNoticecheckProcessEOPage busNoticecheckProcessEOPage4 = new BusNoticecheckProcessEOPage();
                busNoticecheckProcessEOPage4.setProcInstId(processInstanceId);
                busNoticecheckProcessEOPage4.setTaskId(task.getId());
                List<BusNoticecheckProcessEO> busNoticecheckProcessEOList4 = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage4);
                resultMap.put("engineerList",busNoticecheckProcessEOList4);
                break;
        }


        //获取当前人的审批意见（只是保存了，并没有提交审批）
        HistoricTaskInstance currHistoricTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
        resultMap.put("当前审批意见",currHistoricTaskInstance.getDescription());

        //获取历史的审批意见
        List<Map<String,String>> commentList = new ArrayList<Map<String,String>>();

        List<HistoricTaskInstance> historicTaskInstanceList = new ArrayList<HistoricTaskInstance>();
        //如果执行对象和流程实例不相同，证明此时是子流程环节，子流程只能查看当前流程的审批历史和子流程开启前的审批历史
        if(!task.getExecutionId().equals(processInstanceId)) {
            historicTaskInstanceList.addAll(historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc()
                    .executionId(processInstanceId).list());
            if("收文部门负责人审核".equals(task.getName())) {
                historicTaskInstanceList.addAll(historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc()
                        .executionId(task.getExecutionId()).list());
            }
        }else{
            //如果两者相同，那么就是主流程，那么需要查看之前所有的子流程和主流程的审批历史
            historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc()
                    .processInstanceId(processInstanceId).list();
        }
        if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
            for(HistoricTaskInstance historicTaskInstance : historicTaskInstanceList){
                if(historicTaskInstance.getEndTime()!=null) {
                    Map<String, String> map = new HashMap<String, String>();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(historicTaskInstance.getDescription()!=null && !StringUtils.isEmpty(historicTaskInstance.getDescription())){
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
        }
        resultMap.put("comment",commentList);
        return resultMap;
    }


    /**
     * 无关联跳过任务节点
     * @MethodName:NoRelationship
     * @author:yuzhong
     * @param:[nowUserId,processInstanceId]
     * @return:void
     * date: 2018年9月17日 10:34:37
     */
    public String NoRelationship(String nowUserId,String processInstanceId,Integer flag) {
        try{
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                    .taskAssignee(nowUserId).singleResult();
            taskService.setVariable(task.getId(),"flag",flag);
            taskService.complete(task.getId());
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
            return "无关联成功";
        }catch(Exception e){
            logger.error(e.getMessage());
            return "无关联失败";
        }
    }

    /**
     * 驳回到任意节点
     *
     * @MethodName:reject
     * @author:yuzhong
     * @param:[processInstanceId,comment]
     * @return:void
     * date:2018年9月17日 14:12:08
     */
    public String reject(String processInstanceId,String nowUserId,String comment) throws Exception {
        String message = "";
        Task taskEntity =  taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(nowUserId).singleResult();
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
            break;
        case "usertask5":
            destTaskKey = "usertask4";
            break;
        case "usertask7":
            destTaskKey = "usertask6";
            break;
        case "usertask8":
            destTaskKey = "usertask6";
            break;
        default:
            message = "无需驳回";
        }
        if(message.equals("无需驳回")){
            return message;
        }
        if("usertask4".equals(nowTaskKey)){
            List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().taskDefinitionKey("usertask1").orderByTaskCreateTime().desc().list();
            if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
                for(HistoricTaskInstance historicTaskInstance : historicTaskInstanceList){
                    if(historicTaskInstance.getEndTime()!=null){
                        Map map = runtimeService.getVariables(processInstanceId);
                        map.put("userId",historicTaskInstance.getAssignee());
                        map.put("flag",2);
                        runtimeService.setVariables(processInstanceId,map);
                        break;
                    }
                }
            }

            List<Task> taskList =  taskService.createTaskQuery().processInstanceId(processInstanceId).list();
            if(taskList!=null && !taskList.isEmpty()){
                for(Task task : taskList){
                    task.setDescription(comment);//设置驳回意见
                    taskService.saveTask(task);
                    taskService.complete(task.getId());
                }
            }
        }else {
            message = flowProcessUtil.rejectTask(processInstanceId, nowUserId, destTaskKey, comment);
            BusNoticecheckProcessEOPage page = new BusNoticecheckProcessEOPage();
            page.setTaskId(taskEntity.getParentTaskId());
            List<BusNoticecheckProcessEO> list = busNoticecheckProcessEOService.queryByList(page);
            if(list!=null && !list.isEmpty()){
                busNoticecheckProcessEOService.deleteNoticeCheckInfo(taskEntity.getParentTaskId());
            }
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
        return message;
    }
}
