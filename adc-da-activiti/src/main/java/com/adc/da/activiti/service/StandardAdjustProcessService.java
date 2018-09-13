package com.adc.da.activiti.service;


import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.vo.StandardAdjustVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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

import static com.adc.da.activiti.controller.StandardAdjustProcessController.STANDARD_ADJUST_KEY;

@Service("StandardAdjustProcessService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StandardAdjustProcessService {

    private static final Logger logger = LoggerFactory.getLogger(StandardAdjustProcessService.class);

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


    public ResponseMessage<String> submitOrSaveStandardApprovalProcess(StandardAdjustVO standardAdjustVO) throws Exception {
        String ProcessInstanceId;
        if (StringUtils.isNotEmpty(standardAdjustVO.getProcessInstanceId())) {
            //更新流程
            ProcessInstanceId = standardAdjustVO.getProcessInstanceId();
        } else {
            //启动流程
            ProcessInstance pi = flowProcessUtil.startStandardApprovalProcess(STANDARD_ADJUST_KEY);
            ProcessInstanceId = pi.getProcessInstanceId();
        }

        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(ProcessInstanceId)
                .singleResult();

        //设置流程变量(已有则覆盖)
        taskService.setVariable(task.getId(), "标准编写部门发起流程表单", standardAdjustVO);

        //设置当前任务受理人
        taskService.setAssignee(task.getId(), "dyb");

        BusProcessEO busProcessEO = new BusProcessEO();
        busProcessEO.setProcessStatus("0");
        //flag为2时表示是提交流程
        if (StringUtils.isNotEmpty(standardAdjustVO.getFlag()) && "2".equals(standardAdjustVO.getFlag())) {

            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");

            //将提交申请第一步任务走完 即向后执行一步
            taskService.complete(task.getId());

            //获取当前执行的任务
            Task taskNow = taskService.createTaskQuery()
                    .processInstanceId(ProcessInstanceId)
                    .singleResult();

            //查询提交人的部门部长
            String departmentUserId = "提交人部门部长";
            //指定任务处理人
            taskService.setAssignee(taskNow.getId(), departmentUserId);

            busProcessEO.setLastUserId("从session取");
            busProcessEO.setLastUserName("从session取");
            //busProcessEO.setNowUserId();
            busProcessEO.setProcessStatus("1");
        }


        busProcessEO.setProcessNumber(standardAdjustVO.getProcessNumber());
        busProcessEO.setProcessType(standardAdjustVO.getProcessType());
        busProcessEO.setStandardId(standardAdjustVO.getRelatedStandards());
        busProcessEO.setCreateUserId("从session取");

        if (StringUtils.isNotEmpty(standardAdjustVO.getProcessInstanceId())) {
            flowProcessUtil.updateProcessByProcessInstanceId(ProcessInstanceId, busProcessEO);
        }else {
            flowProcessUtil.addProcess(ProcessInstanceId, busProcessEO);
        }
        return Result.success();
    }

    public ResponseMessage<String> SubmitOrRetreatProcess(String flag, String taskId, String comment) throws Exception {
        //查询当前任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //设置流程审核人
        Authentication.setAuthenticatedUserId("从session取");
        if (StringUtils.isNotEmpty(task.getOwner())) {
            //判断此任务是否被委托，是则确认委托结果
            taskService.resolveTask(taskId);
        }
        if(StringUtils.isNotEmpty(comment)){
            taskService.addComment(taskId, task.getProcessInstanceId(), comment);
        }

        if (StringUtils.isNotEmpty(flag) && "2".equals(flag)) {
            //提交按钮则完成当前任务
            taskService.complete(taskId);

            //查询完成上一次任务后当前任务
            Task taskNow = taskService.createTaskQuery().executionId(task.getExecutionId()).singleResult();

            if (taskNow != null) {
                //仍旧存在任务则继续推动任务
                //查询当前办理人的下一级负责人
                String nextAssiner = "谁知道勒";
                //设置任务受理人
                taskService.setAssignee(taskNow.getId(), nextAssiner);
            }

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (taskNow == null) {
                busProcessEO.setProcessStatus("3");
            }
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);

        } else {
            //此流程默认驳回到起点
            String destTaskKey = "usertask1";
            //flag 为3，则退回
            flowProcessUtil.rejectTask(task.getProcessInstanceId(),task.getAssignee(),destTaskKey,"");
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


    public ResponseMessage<Map<String,Object>> queryProcessVariable(String taskId) {

        HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();

        //查询流程实例绑定的流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(task.getProcessInstanceId()).list();
        //查询当前任务之前有多少任务
        List<HistoricTaskInstance>  historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId())
                .orderByHistoricTaskInstanceEndTime().asc().list();
        Map<String,Object> map = new HashMap<>();
        //查询每个任务节点之前的历史意见
        List<Comment> commentHistory = new LinkedList<>();
        for (HistoricTaskInstance taskInstance : historicTaskInstanceList){
            //查询该任务的审批意见
            List<Comment> commentList = taskService.getTaskComments(taskInstance.getId());
            commentHistory.addAll(commentList);
        }
        map.put("历史审批意见",commentHistory);

        for (HistoricVariableInstance variableInstance :historicVariableInstanceList){
            if("标准编写部门发起流程表单".equals(variableInstance.getVariableName())){
                map.put("标准编写部门发起流程表单",(StandardAdjustVO)variableInstance.getValue());
            }
        }

        return  Result.success(map);
    }
}
