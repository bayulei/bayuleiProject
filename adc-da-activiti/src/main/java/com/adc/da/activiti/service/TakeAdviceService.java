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
        for (Map.Entry<String,List<TakeAdviceEngineersOpinionVO>> entry :analyMap.entrySet()) {
            taskService.setVariableLocal(entry.getKey(), "符合性分析", entry.getValue());
        }
        for (Map.Entry<String,List<TakeAdviceEngineersOpinionVO>> entry :suggestMap.entrySet()) {
            taskService.setVariableLocal(entry.getKey(), "工程师反馈意见", entry.getValue());
        }

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
