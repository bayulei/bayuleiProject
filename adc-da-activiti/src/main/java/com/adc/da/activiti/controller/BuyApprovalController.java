package com.adc.da.activiti.controller;


import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.entity.BuyApprovalEO;
import com.adc.da.activiti.entity.VehicleApprovalEO;
import com.adc.da.activiti.service.BusProcessEOService;
import com.adc.da.activiti.service.BuyApprovalService;
import com.adc.da.activiti.vo.ProcessInformationVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/${restPath}/WorkFlow/BuyApproval")
@Api(description = "标准购买审批流程")
public class BuyApprovalController {

    private static final Logger logger = LoggerFactory.getLogger(BuyApprovalController.class);


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
    private BuyApprovalService buyApprovalService;

    /**
     *  部署流程Key
     */
    private   static  final String processDefinitionKey = "buyApproval";

    /**
     *  点击保存按钮，保存流程信息
     * @MethodName:startStandardApprovalProcess
     * @author: yuzhong
     * @param:[vehicleApprovalEO, userId]
     * @return:void
     * date: 2018/8/27 15:01
     */
    @ApiOperation(value = "保存流程信息")
    @PostMapping ("/saveProcessInfo")
    public ResponseMessage<String> saveProcessInfo(BuyApprovalEO buyApprovalEO, String userId,String processInstanceId){
        //启动流程（为了把信息放入流程变量中）
        ProcessInstance processInstance = buyApprovalService.startBuyApprovalProcess(buyApprovalEO,userId,processDefinitionKey,processInstanceId);
        return Result.success(processInstance.getId());
    }

    /**
     *  点击申请购买来发起审批
     * @MethodName:startApproval
     * @author: yuzhong
     * @param:[processInstanceId, userId]
     * @return:S
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "点击申请购买来发起审批")
    @PostMapping ("/startApproval")
    public ResponseMessage<String> startApproval(String processInstanceId,String userId){

        //获取当前执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        //将提交申请第一步任务走完 即向后执行一步
        taskService.complete(task.getId());

        //获取当前执行的任务(已经是第二步了)
        Task task2 = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        //设置下一步的代办人
        task2.setAssignee(userId);

        return Result.success(processInstanceId);
    }

    /**
     *  获取流程变量的信息，填充表单
     * @MethodName:getVariables
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:Map
     * date: 2018年9月4日 14:37:19
     */
    @ApiOperation(value = "获取流程变量的信息，填充表单")
    @PostMapping ("/getVariables")
    public ResponseMessage<Map<String,Object>> getVariables(String processInstanceId) {
        Map<String,Object> map = runtimeService.getVariables(processInstanceId);
        return Result.success(map);
    }

    /**
     * 驳回
     * @MethodName:getVariables
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:Map
     * date: 2018年9月4日 14:37:19
     */
    @ApiOperation(value = "驳回")
    @PostMapping ("/reject")
    public ResponseMessage<String> reject(String processInstanceId) {
        String message = buyApprovalService.reject(processInstanceId);
        return Result.success(message);
    }
}
