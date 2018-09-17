package com.adc.da.activiti.service;


import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.vo.StandardAdjustVO;
import com.adc.da.activiti.vo.StandardReleaseVO;
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
import org.bouncycastle.util.StringList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.adc.da.activiti.controller.StandardAdjustProcessController.STANDARD_ADJUST_KEY;
import static com.adc.da.activiti.controller.StandardReleaseController.STANDARD_RELEASE_KEY;

@Service("StandardReleaseService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StandardReleaseService {

    private static final Logger logger = LoggerFactory.getLogger(StandardReleaseService.class);

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


    public ResponseMessage<String> submitOrSaveProcess(StandardReleaseVO standardReleaseVO) throws Exception {
        String ProcessInstanceId;
        if (StringUtils.isNotEmpty(standardReleaseVO.getProcessInstanceId())) {
            //更新流程
            ProcessInstanceId = standardReleaseVO.getProcessInstanceId();
        } else {
            //启动流程
            ProcessInstance pi = flowProcessUtil.startStandardApprovalProcess(STANDARD_RELEASE_KEY);
            ProcessInstanceId = pi.getProcessInstanceId();
        }

        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(ProcessInstanceId)
                .singleResult();

        //设置流程变量(已有则覆盖)
        taskService.setVariable(task.getId(), "起草部门标准化员发起流程表单", standardReleaseVO);
        taskService.setVariable(task.getId(), "standardEngineerId", standardReleaseVO.getStandardEngineerId());

        //设置当前任务受理人
        taskService.setAssignee(task.getId(), "dyb");

        BusProcessEO busProcessEO = new BusProcessEO();
        busProcessEO.setProcessStatus("0");
        //flag为2时表示是提交流程
        if (StringUtils.isNotEmpty(standardReleaseVO.getFlag()) && "2".equals(standardReleaseVO.getFlag())) {

            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");

            //将提交申请第一步任务走完 即向后执行一步
            taskService.complete(task.getId());

            //获取当前执行的任务
            Task taskNow = taskService.createTaskQuery()
                    .processInstanceId(ProcessInstanceId)
                    .singleResult();

            //查询提交人的下一级
            String departmentUserId = "提交人部门部长";
            //指定任务处理人
            taskService.setAssignee(taskNow.getId(), departmentUserId);

            busProcessEO.setLastUserId("从session取");
            busProcessEO.setLastUserName("从session取");
            //busProcessEO.setNowUserId();
            busProcessEO.setProcessStatus("1");
        }

        busProcessEO.setProcessNumber(standardReleaseVO.getProcessNumber());
        busProcessEO.setProcessType(standardReleaseVO.getProcessType());
        busProcessEO.setStandardId(standardReleaseVO.getRelatedStandards());
        busProcessEO.setCreateUserId("从session取");

        if (StringUtils.isNotEmpty(standardReleaseVO.getProcessInstanceId())) {
            flowProcessUtil.updateProcessByProcessInstanceId(ProcessInstanceId, busProcessEO);
        } else {
            flowProcessUtil.addProcess(ProcessInstanceId, busProcessEO);
        }
        return Result.success();
    }

    public ResponseMessage<String> SubmitOrRetreatProcess(StandardReleaseVO standardReleaseVO,  String taskId, String comment) throws Exception {
        String flag = standardReleaseVO.getFlag();
        //查询当前任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (StringUtils.isNotEmpty(flag) && "1".equals(flag)) {
            //保存按钮

            //设置流程变量(已有则覆盖)
            taskService.setVariable(task.getId(), "起草部门标准化员发起流程表单", standardReleaseVO);

        } else if (StringUtils.isNotEmpty(flag) && "2".equals(flag)) {
            //提交按钮则完成当前任务
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            if (StringUtils.isNotEmpty(comment)) {
                taskService.addComment(taskId, task.getProcessInstanceId(), comment);
            }
            //设置流程变量(已有则覆盖)
            taskService.setVariable(task.getId(), "起草部门标准化员发起流程表单", standardReleaseVO);

            if (task.getName().equals("质量部体系科科长审核") && StringUtils.isNotEmpty(standardReleaseVO.getReleativeDepatmentIds())) {
                //  质量部体系科长审核一步需要判断是否存在相关部门，以决定是否需要相关部门负责人审核
                String[] releativeDepatmentIds = standardReleaseVO.getReleativeDepatmentIds().split(",");
                List<String>  departmentUserIds =  new ArrayList<>();
                for (int i = 0; i < releativeDepatmentIds.length; i++) {
                    //查找相关部门的负责人
                  String  departmentUserId = "根据部门id查找负责人"+releativeDepatmentIds[i];
                    departmentUserIds.add(departmentUserId);
                }
                taskService.setVariable(task.getId(),"departmentTotal",departmentUserIds.size());
                taskService.setVariable(task.getId(),"departmentUserIds",departmentUserIds);
            }
            taskService.complete(taskId);

            //查询完成上一次任务后当前任务
            List<Task> taskNowList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();

            if (taskNowList != null && !taskNowList.isEmpty()) {
                //仍旧存在任务则继续推动任务
                for (Task taskNow :taskNowList){
                    //判断当前任务是否存在受理人,不存在则需要分配
                    if(StringUtils.isEmpty(taskNow.getAssignee())){
                        //查询当前办理人的下一级负责人
                        String nextAssiner = "谁知道勒";
                        //设置任务受理人
                        taskService.setAssignee(taskNow.getId(), nextAssiner);
                    }
                }
            }

            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            if (taskNowList != null && !taskNowList.isEmpty()) {
                busProcessEO.setProcessStatus("3");
            }
            if (StringUtils.isNotEmpty(task.getOwner())) {
                busProcessEO.setLastUserName(task.getOwner() + "委托" + task.getAssignee());
            } else {
                busProcessEO.setLastUserName("从session取");
            }
            flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(), busProcessEO);

        } else if (StringUtils.isNotEmpty(flag) && "3".equals(flag)) {

            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            if (StringUtils.isNotEmpty(task.getOwner())) {
                //判断此任务是否被委托，是则确认委托结果
                taskService.resolveTask(taskId);
            }
            if (StringUtils.isNotEmpty(comment)) {
                taskService.addComment(taskId, task.getProcessInstanceId(), comment);
            }
            //查找所有当前任务节点,为同级的任务节点设置驳回标准，标明任务撤销
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId())
                    .list();
            for (Task taskNow :  taskList){
                if(!taskNow.getId().equals(taskId)){
                    taskService.addComment(taskNow.getId(), task.getProcessInstanceId(), "已经撤销");
                }
            }

            //此流程默认驳回到起点
            String destTaskKey = "usertask1";
            //flag 为3，则退回
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


    public ResponseMessage<Map<String, Object>> queryProcessVariable(String taskId) {

        HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();

        //查询流程实例绑定的流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(task.getProcessInstanceId()).list();
        StandardReleaseVO standardReleaseVO = null;
        for (HistoricVariableInstance variableInstance : historicVariableInstanceList) {
            if ("起草部门标准化员发起流程表单".equals(variableInstance.getVariableName())) {
                standardReleaseVO =(StandardReleaseVO) variableInstance.getValue();
            }
        }

        //查询当前任务之前有多少任务
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId())
                .orderByHistoricTaskInstanceEndTime().asc().list();

        //查询每个任务节点之前的历史意见
        List<Comment> commentHistory = new LinkedList<>();
        for (HistoricTaskInstance taskInstance : historicTaskInstanceList) {
            //查询该任务的审批意见
            List<Comment> commentList = taskService.getTaskComments(taskInstance.getId());
            commentHistory.addAll(commentList);
        }

        Map<String, Object> map = new HashMap<>();

        switch (task.getName()){
            case "起草部门标准化员发起流程":
                map.put("历史审批意见", commentHistory);
                map.put("起草部门标准化员发起流程表单", standardReleaseVO);
                break;

            case "编制部门部长审核":
                map.put("历史审批意见", commentHistory);
                //编制部门部长审核不需要展示相关部门和选择的归口工程师
                standardReleaseVO.setReleativeDepatmentIds("");
                standardReleaseVO.setStandardEngineerId("");
                map.put("起草部门标准化员发起流程表单", standardReleaseVO);
                break;

            case "相关部门负责人审核":
                /* 此任务不允许看到同级其他人的审核意见
                   取得当前任务的同级任务
                 */
                List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId()).taskName("相关部门负责人审核").list();
                List<String> stringList = new ArrayList<>();
                for (HistoricTaskInstance taskInstance : taskList){
                    if(!taskInstance.getId().equals(taskId)){
                        stringList.add(taskInstance.getId());
                    }
                }
                List<String>  taskIdList = new ArrayList<>();
                for (HistoricTaskInstance taskInstanceList : historicTaskInstanceList){
                    if (!stringList.contains(taskInstanceList.getId())){
                        taskIdList.add(taskInstanceList.getId());
                    };
                }
                commentHistory.clear();
                for (String taskid : taskIdList) {
                    //查询该任务的审批意见
                    List<Comment> commentList = taskService.getTaskComments(taskid);
                    commentHistory.addAll(commentList);
                }

                map.put("历史审批意见", commentHistory);
                //相关部门负责人审核审核不需要展示选择的归口工程师
                standardReleaseVO.setStandardEngineerId("");
                map.put("起草部门标准化员发起流程表单", standardReleaseVO);
                break;

            default:
                map.put("历史审批意见", commentHistory);
                //审核不需要展示选择的归口工程师的任务
                standardReleaseVO.setStandardEngineerId("");
                map.put("起草部门标准化员发起流程表单", standardReleaseVO);
                break;
        }

        return Result.success(map);
    }
}
