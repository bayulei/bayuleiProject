package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.entity.VehicleApprovalEO;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.adc.da.activiti.controller.VehicleApprovalController.ProcessDefinitionKey;


/**
 * <br>
 * <b>功能：</b>BUS_PROCESS BusProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-31 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("VehicleApprovalService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class VehicleApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleApprovalService.class);

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
     * 启动流程,创建流程实例
     *
     * @MethodName:startStandardApprovalProcess
     * @author: DuYunbao
     * @param:[vehicleApprovalEO]
     * @return:org.activiti.engine.runtime.ProcessInstance date: 2018/9/3 20:15
     */
    public ProcessInstance startStandardApprovalProcess(VehicleApprovalEO vehicleApprovalEO) {
        //从session取当前登录人Id
        String userId = "dyb";

        //设置流程发起人id
        Authentication.setAuthenticatedUserId(userId);
        //与正在执行的流程实例和执行对象相关的Service
        ProcessInstance pi = runtimeService
                //使用流程定义的key启动流程实例，key对应bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
                .startProcessInstanceByKey(ProcessDefinitionKey);
        return pi;
    }


    /**
     * 提交流程或者保存流程
     *
     * @MethodName:submitOrSaveStandardApprovalProcess
     * @author: DuYunbao
     * @param:[vehicleApprovalEO, flag]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String> date: 2018/9/4 14:26
     */
    public ResponseMessage<String> submitOrSaveStandardApprovalProcess(VehicleApprovalEO vehicleApprovalEO, String flag, String ProcessInstanceId) throws Exception {
        //启动流程
        try {
            ProcessInstance pi = this.startStandardApprovalProcess(vehicleApprovalEO);
            //获取当前执行的任务
            Task task = taskService.createTaskQuery()
                    .processInstanceId(pi.getProcessInstanceId())
                    .singleResult();

            //设置流程变量
            taskService.setVariable(task.getId(), "项目组总体经理发起流程表单", vehicleApprovalEO);

            //设置当前任务受理人
            taskService.setAssignee(task.getId(), "dyb");

            //设置子流程的开启参数 创建多个子流程用的集合
            taskService.setVariable(task.getId(), "assignerUserList", vehicleApprovalEO.getAssignerUserList());

            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setNowUserId("从session取");
            busProcessEO.setNowUesrName("从session取");
            busProcessEO.setProcessStatus("0");
            //flag为2时表示是提交流程
            if (StringUtils.isNotEmpty(flag) && "2".equals(flag)) {

                //设置流程审核人
                Authentication.setAuthenticatedUserId("从session取");
                //将提交申请第一步任务走完 即向后执行一步
                taskService.complete(task.getId());

                //获取当前执行的任务
                List<Task> taskList = taskService.createTaskQuery()
                        .processInstanceId(pi.getProcessInstanceId())
                        .list();

                //指定任务处理人
                int i = 0;
                for (Task taskNow : taskList) {
                    taskService.setAssignee(taskNow.getId(), vehicleApprovalEO.getAssignerUserList().get(i));
                    i++;
                }

                busProcessEO.setLastUserId("从session取");
                busProcessEO.setLastUserName("从session取");
                //busProcessEO.setNowUserId();
                busProcessEO.setNowUesrName("专业组人员,开子流程了，不知道该输入啥");
                busProcessEO.setProcessStatus("1");
            }


            busProcessEO.setProcessNumber(vehicleApprovalEO.getProcessNumber());
            busProcessEO.setProcessType(vehicleApprovalEO.getProcessType());
            busProcessEO.setStandardId(vehicleApprovalEO.getRelatedStandards());
            busProcessEO.setCreateUserId("");

            //非空代表非初次触发，删除上次流程
            if (StringUtils.isNotEmpty(ProcessInstanceId)) {
                flowProcessUtil.deleteByProcessInstance(ProcessInstanceId);
                //删除上次附件
                //..................
                //更新业务流程实例关系表和业务流程表
                BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
                busExecuProcessEO.setProcInstId(ProcessInstanceId);
                List<BusExecuProcessEO> ExecuProcessEOList = busExecuProcessEOService.selectByEO(busExecuProcessEO);
                busProcessEO.setId(ExecuProcessEOList.get(0).getProcessId());
                busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
                busExecuProcessEO.setId(ExecuProcessEOList.get(0).getId());
                busExecuProcessEO.setProcInstId(pi.getId());
                busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
            } else {

                busProcessEO.setId(UUIDUtils.randomUUID20());
                busProcessEO.setCreateTime(new Date());
                busProcessEOService.insertSelective(busProcessEO);
                BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
                busExecuProcessEO.setProcessId(busProcessEO.getId());
                busExecuProcessEO.setProcInstId(pi.getId());
                busExecuProcessEO.setId(UUIDUtils.randomUUID20());
                busExecuProcessEOService.insertSelective(busExecuProcessEO);
            }

            return Result.success();
        } catch (ActivitiException e) {
            e.printStackTrace();
            return Result.error();
        }

    }

    public ResponseMessage<String> writeTestProgram(String flag, String taskId) throws Exception {
        //查询当前任务所在的流程实例
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        //附件信息
        String url = "http://dwz.cn/SM3TT1YK";

        //删除当前的任务所在的子流程实例的流程变量
        runtimeService.removeVariableLocal(execution.getParentId(),"填写后的附件");
        //删除上次附件
        //..................
        //为当前的任务所在的子流程实例设置流程变量
        runtimeService.setVariableLocal(execution.getParentId(), "填写后的附件", url);

        if(StringUtils.isNotEmpty(flag) && "2".equals(flag)){
            //设置流程审核人
            Authentication.setAuthenticatedUserId("从session取");
            //提交按钮则完成当前任务
            taskService.complete(taskId);
            //查询完成上一次任务后当前任务
            Task taskNow = taskService.createTaskQuery().executionId(execution.getId()).singleResult();
            //查询流程从中获取发起人
            HistoricProcessInstance  historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(taskNow.getProcessInstanceId()).singleResult();
            //设置任务受理人
            taskService.setAssignee(taskNow.getId(),historicProcessInstance.getStartUserId());
            //更新业务流程主表
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setLastUserId("从session取");
            busProcessEO.setLastUserName("从session取");
            flowProcessUtil.updateProcessByProcessInstanceId(execution.getProcessInstanceId(),busProcessEO);
        }

        return  Result.success();
    }

    public ResponseMessage<String> quiteResult(String taskId) throws Exception {
        //查询当前任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //设置流程审核人
        Authentication.setAuthenticatedUserId("从session取");
        //完成任务
        taskService.complete(taskId);
        //更新业务流程主表
        BusProcessEO busProcessEO = new BusProcessEO();
        busProcessEO.setLastUserId("从session取");
        if(StringUtils.isNotEmpty(task.getOwner())){
            busProcessEO.setLastUserName(task.getOwner()+"委托"+task.getAssignee());
        }else {
            busProcessEO.setLastUserName("从session取");
        }

        busProcessEO.setEndTime(new Date());
        busProcessEO.setProcessStatus("2");
        flowProcessUtil.updateProcessByProcessInstanceId(task.getProcessInstanceId(),busProcessEO);
        return Result.success();
    }

    public ResponseMessage<String> queryProcessVariable(String taskId) {

        /*HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();

        //查询流程实例绑定的流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(task.getProcessInstanceId()).list();
        //查询子流程执行对象绑定 的流程变量
        List<HistoricVariableInstance> historicChildrenVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(task.get).list();
*/     //  historyService.createHistoricVariableInstanceQuery().
        return  Result.success();
    }
}
