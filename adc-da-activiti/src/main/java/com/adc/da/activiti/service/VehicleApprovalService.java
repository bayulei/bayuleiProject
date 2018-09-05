package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.entity.VehicleApprovalEO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.activiti.engine.*;
import org.activiti.engine.impl.identity.Authentication;
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
 *
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
     *  启动流程,创建流程实例
     * @MethodName:startStandardApprovalProcess
     * @author: DuYunbao
     * @param:[vehicleApprovalEO]
     * @return:org.activiti.engine.runtime.ProcessInstance
     * date: 2018/9/3 20:15
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

    public ProcessInstance submitStandardApprovalProcess(VehicleApprovalEO vehicleApprovalEO) {
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
     *  提交流程或者保存流程
     * @MethodName:submitOrSaveStandardApprovalProcess
     * @author: DuYunbao
     * @param:[vehicleApprovalEO, flag]
     * @return:com.adc.da.util.http.ResponseMessage<java.lang.String>
     * date: 2018/9/4 14:26
     */
    public ResponseMessage<String> submitOrSaveStandardApprovalProcess(VehicleApprovalEO vehicleApprovalEO, String flag,String ProcessInstanceId) throws Exception {
        //启动流程
       try {
           ProcessInstance pi = this.startStandardApprovalProcess(vehicleApprovalEO);
        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(pi.getProcessInstanceId())
                .singleResult();

        //设置流程变量
        taskService.setVariable(task.getId(),"项目组总体经理发起流程表单",vehicleApprovalEO);

        //设置当前任务受理人
        taskService.setAssignee(task.getId(),"dyb");

        //设置子流程的开启参数 创建多个子流程用的集合
        taskService.setVariable(task.getId(),"assignerUserList",vehicleApprovalEO.getAssignerUserList());

        BusProcessEO  busProcessEO  = new BusProcessEO();
        busProcessEO.setNowUserId("从session取");
        busProcessEO.setNowUesrName("从session取");
        busProcessEO.setProcessStatus("0");
        //flag为2时表示是提交流程
        if(StringUtils.isNotEmpty(flag) && flag.equals("2")){

            //将提交申请第一步任务走完 即向后执行一步
            taskService.complete(task.getId());

            //获取当前执行的任务
            List<Task> taskList = taskService.createTaskQuery()
                    .processInstanceId(pi.getProcessInstanceId())
                    .list();

            //指定任务处理人
            int i = 0;
            for (Task taskNow : taskList){
                taskService.setAssignee(taskNow.getId(),vehicleApprovalEO.getAssignerUserList().get(i));
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
           if(StringUtils.isNotEmpty(ProcessInstanceId)){
               flowProcessUtil.deleteByProcessInstance(ProcessInstanceId);
               //删除上次附件
               //..................
               //更新业务流程实例关系表和业务流程表
               BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
               busExecuProcessEO.setProcessId(busProcessEO.getId());
               busExecuProcessEO.setProcInstId(pi.getId());
               List<BusExecuProcessEO>  ExecuProcessEOList = busExecuProcessEOService.selectByEO(busExecuProcessEO);
               busProcessEO.setId(ExecuProcessEOList.get(0).getProcessId());
               busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
               busExecuProcessEO.setId(ExecuProcessEOList.get(0).getId());
               busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
           }else {

               //busProcessEO.setId(UUIDUtils.randomUUID20());
               busProcessEO.setCreateTime(new Date());
               busProcessEOService.insertSelective(busProcessEO);
               BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
               busExecuProcessEO.setProcessId(busProcessEO.getId());
               busExecuProcessEO.setProcInstId(pi.getId());
               //busExecuProcessEO.setId(UUIDUtils.randomUUID20());
               busExecuProcessEOService.insertSelective(busExecuProcessEO);
           }

           return Result.success();
       }catch (ActivitiException  e) {
           e.printStackTrace();
           return Result.error();
       }

    }
}
