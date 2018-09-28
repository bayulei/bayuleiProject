package com.adc.da.activiti.service;


import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.vo.StandardReleaseVO;
import com.adc.da.activiti.vo.TakeAdviceEngineersOpinionVO;
import com.adc.da.activiti.vo.TakeAdviceVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.adc.da.activiti.controller.StandardReleaseController.STANDARD_RELEASE_KEY;
import static com.adc.da.activiti.controller.TakeAdviceController.TAKE_ADVICE_KEY;

@Service("TakeAdviceService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class TakeAdviceService {

    private static final Logger logger = LoggerFactory.getLogger(TakeAdviceService.class);

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private BusProcessEOService busProcessEOService;

    @Autowired
    private BusExecuProcessEOService busExecuProcessEOService;

    @Autowired
    private TaskService taskService;

    /**
     *  法规管理工程师发起流程
     * @MethodName:engineerSubmitOrSaveProcess
     * @author: DuYunbao
     * @param:[takeAdviceVO]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/20 14:00
     */
    public ResponseMessage<String> engineerSubmitOrSaveProcess(TakeAdviceVO takeAdviceVO,String ProcessInstanceId) throws Exception {
        Boolean isFirstStart = false;
        if (StringUtils.isEmpty(ProcessInstanceId)) {
            //启动流程
            ProcessInstance pi = flowProcessUtil.startStandardApprovalProcess(TAKE_ADVICE_KEY);
            ProcessInstanceId = pi.getProcessInstanceId();
            isFirstStart = true;
        }

        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(ProcessInstanceId)
                .singleResult();

        //设置流程变量(已有则覆盖)
        runtimeService.setVariableLocal(task.getExecutionId(), "法规管理工程师发起流程表单", takeAdviceVO);
        runtimeService.setVariableLocal(task.getExecutionId(),"cutOffTime",takeAdviceVO.getEndTime());
        //设置当前任务受理人
        taskService.setAssignee(task.getId(), "dyb");

        BusProcessEO busProcessEO = new BusProcessEO();
        busProcessEO.setProcessStatus("0");

        //flag为2时表示是提交流程
        if (StringUtils.isNotEmpty(takeAdviceVO.getFlag()) && "2".equals(takeAdviceVO.getFlag())) {
            //设置开启子流程需要的变量
            List<String> interfacePersonList =takeAdviceVO.getInterfacePersonList();
            runtimeService.setVariableLocal(task.getExecutionId(), "interfacePersonList", interfacePersonList);

            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");

            //将提交申请第一步任务走完 即向后执行一步
            taskService.complete(task.getId());

            //获取当前执行的任务
            List<Task> taskNowList = taskService.createTaskQuery()
                    .processInstanceId(ProcessInstanceId)
                    .list();

            //指定任务处理人
            int i = 0 ;
            for (Task taskNow : taskNowList){
                taskService.setAssignee(taskNow.getId(), interfacePersonList.get(i));
                i++;
            }

            busProcessEO.setLastUserId("从session取");
            busProcessEO.setLastUserName("从session取");
            busProcessEO.setProcessStatus("1");
        }

        busProcessEO.setProcessNumber(takeAdviceVO.getProcessNumber());
        busProcessEO.setProcessType(takeAdviceVO.getProcessType());
        busProcessEO.setStandardId(takeAdviceVO.getRelatedStandards());
        busProcessEO.setCreateUserId("从session取");

        if (isFirstStart) {
            flowProcessUtil.addProcess(ProcessInstanceId, busProcessEO);
        } else {
            flowProcessUtil.updateProcessByProcessInstanceId(ProcessInstanceId, busProcessEO);
        }
        return Result.success();
    }

    /**
     *  法规接口人分发
     * @MethodName:rulePersonDistribute
     * @author: DuYunbao
     * @param:[flag, engineerIdList, taskId]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/18 16:31
     */
    public ResponseMessage<String> rulePersonDistribute(String flag,List<String> engineerIdList,Task task) throws Exception {
        String taskId = task.getId();

        if ("1".equals(flag)) {
            //保存按钮
            //设置流程变量(已有则覆盖)
            runtimeService.setVariableLocal(task.getExecutionId(), "engineerIdList", engineerIdList);
            runtimeService.setVariableLocal(task.getExecutionId(), "engineerTotal", engineerIdList.size());
        } else if ("2".equals(flag)) {
            //提交按钮则完成当前任务
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            //设置流程变量(已有则覆盖)
            runtimeService.setVariableLocal(task.getExecutionId(), "engineerIdList", engineerIdList);
            runtimeService.setVariableLocal(task.getExecutionId(), "engineerTotal", engineerIdList.size());
            runtimeService.setVariableLocal(task.getExecutionId(), "interfacePerson", task.getAssignee());
            //将子流程的实例对象存到 子任务的描述中
            runtimeService.setVariableLocal(task.getExecutionId(), "childrenExecutionId", task.getExecutionId());

            taskService.complete(taskId);
            //查询此时任务
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId())
                    .taskDescription(task.getExecutionId()).list();

            //为此时的 任务设置parentTaskId
            for (Task taskNow : taskList){
                taskNow.setParentTaskId(taskId);
                taskService.saveTask(taskNow);
            }

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);

        } else if ("3".equals(flag)) {
            //驳回
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            runtimeService.setVariableLocal(task.getExecutionId(), "engineerTotal", -1);

            //此流程默认驳回到起点
            String destTaskKey = "usertask1";
            flowProcessUtil.rejectTask(task.getProcessInstanceId(), task.getAssignee(), destTaskKey, "");
            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);
        }else  if("4".equals(flag)){
            //无关联
            runtimeService.setVariableLocal(task.getExecutionId(), "engineerTotal",0);
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            taskService.complete(taskId);

            //查询当前任务
            Task taskNow = taskService.createTaskQuery().executionId(task.getExecutionId()).singleResult();
            //查找当前法规接口人的科长
            String sectionChief = "sectionChief";
            //设置处理人
            taskService.setAssignee(taskNow.getId(),sectionChief);

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);
        }

        return Result.success();
    }

    /**
     *  工程师填写反馈意见
     * @MethodName:engineerFillSuggest
     * @author: DuYunbao
     * @param:[list, flag, taskId]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/18 16:57
     */
    public ResponseMessage<String> engineerFillSuggest(List<TakeAdviceEngineersOpinionVO> analyList,
                                                       List<TakeAdviceEngineersOpinionVO> suggestList,
                                                       String flag,Task task ) throws Exception {
        //查询当前任务
        String taskId = task.getId();

        if ("1".equals(flag)) {
            //保存按钮
            //设置流程变量(已有则覆盖)
            taskService.setVariableLocal(task.getId(), "符合性分析", analyList);
            taskService.setVariableLocal(task.getId(), "工程师反馈意见", suggestList);

        } else if ("2".equals(flag)) {
            //提交按钮则完成当前任务
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            //设置流程变量(已有则覆盖)
            taskService.setVariableLocal(task.getId(), "符合性分析", analyList);
            taskService.setVariableLocal(task.getId(), "工程师反馈意见", suggestList);
            taskService.complete(taskId);
            /*//查询此时是否存在下一级任务（会签未完毕，不会出现下一级任务）
            Task taskNow = taskService.createTaskQuery().executionId(task.getDescription()).taskName("法规接口人汇总反馈意见")
                    .singleResult();
            if(taskNow != null){
                //从当前子流程中取出此流程的接口人
                String personId =(String)runtimeService.getVariable(task.getDescription(),"interfacePerson");
                //为下一个任务节点分配处理人
                taskService.setAssignee(taskNow.getId(),personId);
            }*/
            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);

        } else if ("3".equals(flag)) {
            //驳回
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }

            //此流程默认驳回到法规接口人分发
            String destTaskKey = "usertask2";
            flowProcessUtil.rejectTask(task.getProcessInstanceId(), task.getAssignee(), destTaskKey, "");
            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);
        }

        return Result.success();
    }


    /**
     *  法规接口人汇总反馈意见
     * @MethodName:gatherSuggest
     * @author: DuYunbao
     * @param:[flag, analyMap, suggestMap, taskId]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/20 10:15
     */
    public ResponseMessage<String> gatherSuggest(String flag,Map<String,List<TakeAdviceEngineersOpinionVO>> analyMap
            ,Map<String,List<TakeAdviceEngineersOpinionVO>> suggestMap,Task task) throws Exception {

        String taskId = task.getId();

        //设置流程变量(已有则覆盖)
        List<TakeAdviceEngineersOpinionVO>  adviceEngineersOpinionVOList = new ArrayList<>();
        for (Map.Entry<String,List<TakeAdviceEngineersOpinionVO>> entry :analyMap.entrySet()) {
            taskService.setVariableLocal(entry.getKey(), "符合性分析", entry.getValue());
            adviceEngineersOpinionVOList.addAll(entry.getValue());
        }
        taskService.setVariableLocal(taskId, "符合性分析", adviceEngineersOpinionVOList);
        List<TakeAdviceEngineersOpinionVO>  adviceEngineersOpinionVOList2 = new ArrayList<>();
        for (Map.Entry<String,List<TakeAdviceEngineersOpinionVO>> entry :suggestMap.entrySet()) {
            taskService.setVariableLocal(entry.getKey(), "工程师反馈意见", entry.getValue());
            adviceEngineersOpinionVOList2.addAll(entry.getValue());
        }
        taskService.setVariableLocal(taskId, "工程师反馈意见", adviceEngineersOpinionVOList2);

        if ("2".equals(flag)) {
            //提交按钮则完成当前任务
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }

            taskService.complete(taskId);

            //查询此时任务
            Task  taskNow = taskService.createTaskQuery().executionId(task.getExecutionId()).singleResult();
            //从当前子流程中取出此流程的接口人
            String personId =(String)runtimeService.getVariable(task.getExecutionId(),"interfacePerson");
            //查找其科长
            String roomUserId = "科长"+personId;
            //设置任务处理人
            taskService.setAssignee(taskNow.getId(),roomUserId);

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);
        }

        return Result.success();
    }

    /**
     *  科室或者部门负责人审核
     * @MethodName:roomOrDepartmentCheck
     * @author: DuYunbao
     * @param:[flag, taskId, comment]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/20 11:26
     */
    public ResponseMessage<String> roomOrDepartmentCheck(String flag, Task task,String comment) throws Exception {
      String taskId = task.getId();

        taskService.setVariableLocal(taskId,"审批意见",comment);

        if ("2".equals(flag)) {
            //提交按钮则完成当前任务
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            taskService.complete(taskId);
            //查询此时是否存在下一级任务
            Task taskNow = taskService.createTaskQuery().executionId(task.getExecutionId())
                    .singleResult();
            if(taskNow != null){
                //从当前子流程中取出此流程的接口人
                String personId =(String)runtimeService.getVariable(task.getExecutionId(),"法规接口人");
                //获取部门负责人
                String departmentUserId = "部门负责人"+personId;
                //为下一个任务节点分配处理人
                taskService.setAssignee(taskNow.getId(),departmentUserId);
            }else {
                Task endTask = taskService.createTaskQuery().executionId(task.getProcessInstanceId())
                        .taskName("法规管理工程师汇总").singleResult();
                if (endTask != null){
                    //查找流程发起人
                    HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                            .processInstanceId(endTask.getProcessInstanceId()).singleResult();
                    //设置处理人
                    taskService.setAssignee(endTask.getId(),historicProcessInstance.getStartUserId());
                }
            }

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);

        } else if ("3".equals(flag)) {
            //驳回
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            //此流程默认驳回到法规接口人汇总反馈意见
            String destTaskKey = "usertask4";
            flowProcessUtil.rejectTask(task.getProcessInstanceId(), task.getAssignee(), destTaskKey, "");
            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);
        }

        return Result.success();
    }

    /**
     *  法规管理工程师汇总
     * @MethodName:engineerGather
     * @author: DuYunbao
     * @param:[taskId]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/20 13:53
     */
    public ResponseMessage<String> engineerGather(String taskId) throws Exception {
        //查询当前任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

            //提交按钮则完成当前任务
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            taskService.complete(taskId);

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);

        return Result.success();
    }


    public ResponseMessage<TakeAdviceVO> queryProcessVariable(String taskId) {

        HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();

        //查询流程实例绑定的流程变量
        HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                .executionId(task.getProcessInstanceId()).variableName("法规管理工程师发起流程表单").singleResult();
        TakeAdviceVO takeAdviceVO  = (TakeAdviceVO) historicVariableInstance.getValue();

        switch (task.getName()){
            case "法规管理工程师发起流程":{
                return Result.success(takeAdviceVO);
            }

            case "法规接口人分发":{
                takeAdviceVO.setCopyPersonIdList(null);
                takeAdviceVO.setInterfacePersonList(null);
                HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("engineerIdList").singleResult();
                takeAdviceVO.setEngineerIdList((List<String>) variableInstance.getValue());
                return Result.success(takeAdviceVO);
            }

            case "工程师填写反馈意见":{
                takeAdviceVO.setCopyPersonIdList(null);
                takeAdviceVO.setInterfacePersonList(null);
                HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery()
                        .taskId(taskId).variableName("符合性分析").singleResult();
                HistoricVariableInstance variableInstance2 = historyService.createHistoricVariableInstanceQuery()
                        .taskId(taskId).variableName("意见反馈").singleResult();
                takeAdviceVO.setAnalyList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                takeAdviceVO.setSuggestList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                return Result.success(takeAdviceVO);
            }

            case "法规接口人汇总反馈意见":{
                takeAdviceVO.setCopyPersonIdList(null);
                takeAdviceVO.setInterfacePersonList(null);
                List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId()).taskDescription(task.getExecutionId()).list();
                for (HistoricTaskInstance taskInstance : taskInstanceList){
                    HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery()
                            .taskId(taskInstance.getId()).variableName("符合性分析").singleResult();
                    HistoricVariableInstance variableInstance2 = historyService.createHistoricVariableInstanceQuery()
                            .taskId(taskInstance.getId()).variableName("意见反馈").singleResult();
                    if(variableInstance!= null && variableInstance.getValue()!= null){
                        List<TakeAdviceEngineersOpinionVO> list1 = (List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue();
                        takeAdviceVO.getAnalyList().addAll(list1);
                    }
                    if(variableInstance2!= null && variableInstance2.getValue()!= null){
                        List<TakeAdviceEngineersOpinionVO> list2 = (List<TakeAdviceEngineersOpinionVO>) variableInstance2.getValue();
                        takeAdviceVO.getSuggestList().addAll(list2);
                    }

                }
                return Result.success(takeAdviceVO);
            }

            case "法规接口人科长审核":{
                takeAdviceVO.setCopyPersonIdList(null);
                takeAdviceVO.setInterfacePersonList(null);
                HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("符合性分析").singleResult();
                HistoricVariableInstance variableInstance2 = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("意见反馈").singleResult();
                takeAdviceVO.setAnalyList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                takeAdviceVO.setSuggestList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                List<HistoricVariableInstance> variableInstanceList = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("审批意见").list();
                for (HistoricVariableInstance variableInstances :variableInstanceList){
                    takeAdviceVO.getCommentList().add((String) variableInstances.getValue());
                }
                return Result.success(takeAdviceVO);
            }

            case "部门负责人审批":{
                takeAdviceVO.setCopyPersonIdList(null);
                takeAdviceVO.setInterfacePersonList(null);
                HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("符合性分析").singleResult();
                HistoricVariableInstance variableInstance2 = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("意见反馈").singleResult();
                takeAdviceVO.setAnalyList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                takeAdviceVO.setSuggestList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                List<HistoricVariableInstance> variableInstanceList = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("审批意见").list();
                for (HistoricVariableInstance variableInstances :variableInstanceList){
                    takeAdviceVO.getCommentList().add((String) variableInstances.getValue());
                }
                return Result.success(takeAdviceVO);
            }

            case "法规管理工程师汇总":{
                takeAdviceVO.setCopyPersonIdList(null);
                takeAdviceVO.setInterfacePersonList(null);
                HistoricVariableInstance variableInstance = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("符合性分析").singleResult();
                HistoricVariableInstance variableInstance2 = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("意见反馈").singleResult();
                takeAdviceVO.setAnalyList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                takeAdviceVO.setSuggestList((List<TakeAdviceEngineersOpinionVO>) variableInstance.getValue());
                List<HistoricVariableInstance> variableInstanceList = historyService.createHistoricVariableInstanceQuery()
                        .executionId(task.getExecutionId()).variableName("审批意见").list();
                for (HistoricVariableInstance variableInstances :variableInstanceList){
                    takeAdviceVO.getCommentList().add((String) variableInstances.getValue());
                }
                return Result.success(takeAdviceVO);
            }

            default:return null;
        }

    }
}
