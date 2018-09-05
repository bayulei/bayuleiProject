package com.adc.da.activiti.controller;


import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BuyApprovalEO;
import com.adc.da.activiti.service.BuyApprovalService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
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
    private RuntimeService runtimeService;

    @Autowired
    private BuyApprovalService buyApprovalService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

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
        try{
            ProcessInstance processInstance = buyApprovalService.startBuyApprovalProcess(buyApprovalEO,userId,processDefinitionKey,processInstanceId);
            return Result.success(processInstance.getId());
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("保存失败");
        }
    }

    /**
     *  点击申请购买来发起审批
     * @MethodName:startApproval
     * @author: yuzhong
     * @param:[processInstanceId,userId]
     * @return:S
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "点击申请购买来发起审批")
    @PostMapping ("/startApproval")
    public ResponseMessage<String> startApproval(BuyApprovalEO buyApprovalEO, String userId,String processInstanceId){
        try{
            ProcessInstance processInstance = buyApprovalService.startBuyApprovalProcess(buyApprovalEO,userId,processDefinitionKey,processInstanceId);
            buyApprovalService.completeApproval(processInstanceId,userId);
            return Result.success(processInstanceId);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("审批失败");
        }
    }

    /**
     *  完成审批
     * @MethodName:completeApproval
     * @author: yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:S
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "完成审批")
    @PostMapping ("/completeApproval")
    public ResponseMessage<String> completeApproval(String processInstanceId,String nowUserId){
        try {
            buyApprovalService.completeApproval(processInstanceId,nowUserId);
            return Result.success(processInstanceId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("审批失败");
        }
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
     * @MethodName:reject
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:String
     * date: 2018年9月4日 14:37:19
     */
    @ApiOperation(value = "驳回")
    @PostMapping ("/reject")
    public ResponseMessage<String> reject(String processInstanceId,String nowUserId) {
        String message = flowProcessUtil.reject(processInstanceId,nowUserId);
        return Result.success(message);
    }

    /**
     * 委托
     * @MethodName:entrust
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:Map
     * date: 2018年9月5日 10:12:36
     */
    @ApiOperation(value = "委托")
    @PostMapping ("/entrust")
    public ResponseMessage<String> entrust(String processInstanceId,String owner) {
        String message = flowProcessUtil.entrust(processInstanceId,owner);
        return Result.success(message);
    }
}
