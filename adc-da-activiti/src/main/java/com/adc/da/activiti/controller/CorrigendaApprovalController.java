package com.adc.da.activiti.controller;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.service.CorrigendaApprovalService;
import com.adc.da.activiti.vo.CorrigendaApprovalVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/${restPath}/WorkFlow/CorrigendaApproval")
@Api(description = "企业标准勘误审批流程")
public class CorrigendaApprovalController {

    private static final Logger logger = LoggerFactory.getLogger(CorrigendaApprovalController.class);


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CorrigendaApprovalService corrigendaApprovalService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    /**
     *  部署流程Key
     */
    private   static  final String processDefinitionKey = "corrigendaApproval";

    /**
     *  点击保存按钮，保存流程信息
     * @MethodName:saveProcessInfo
     * @author: yuzhong
     * @param:[vehicleApprovalEO,userId]
     * @return:void
     * date: 2018/8/27 15:01
     */
    @ApiOperation(value = "保存流程信息")
    @PostMapping ("/saveProcessInfo")
    public ResponseMessage<String> saveProcessInfo(CorrigendaApprovalVO corrigendaApprovalVO, String userId,String processInstanceId){
        //启动流程（为了把信息放入流程变量中）
        try{
            processInstanceId = corrigendaApprovalService.startProcess(corrigendaApprovalVO,userId,processDefinitionKey,processInstanceId);
            return Result.success(processInstanceId);
        }catch(Exception e){
            logger.error(e.getMessage());
            return Result.error("保存失败");
        }
    }

    /**
     *  标准编写人员发起流程
     * @MethodName:startApproval
     * @author:yuzhong
     * @param:[processInstanceId,userId]
     * @return:String
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "标准编写人员发起流程")
    @PostMapping ("/startApproval")
    public ResponseMessage<String> startApproval(CorrigendaApprovalVO corrigendaApprovalVO, String userId,String processInstanceId,String comment){
        try{
            processInstanceId = corrigendaApprovalService.startProcess(corrigendaApprovalVO,userId,processDefinitionKey,processInstanceId);
            corrigendaApprovalService.completeApproval(processInstanceId,userId,comment);
            return Result.success(processInstanceId);
        }catch(Exception e){
            logger.error(e.getMessage());
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
    public ResponseMessage<String> completeApproval(String processInstanceId,String nowUserId,String comment){
        try {
            corrigendaApprovalService.completeApproval(processInstanceId,nowUserId,comment);
            return Result.success(processInstanceId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.error("审批失败");
        }
    }

    /**
     * 各个任务页面展示所需数据
     * @MethodName:getTaskInfo
     * @author:yuzhong
     * @param:[taskId]
     * @return:Map
     * date: 2018年9月6日 19:07:04
     */
    @ApiOperation(value = "各个任务页面展示所需数据")
    @PostMapping ("/getTaskInfo")
    public ResponseMessage<Map<String,Object>> getTaskInfo(String taskId,String processInstanceId) {
        Map<String,Object> map = corrigendaApprovalService.getTaskInfo(taskId,processInstanceId);
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
    public ResponseMessage<String> reject(String processInstanceId,String nowUserId,String comment)throws Exception {
        String message = corrigendaApprovalService.reject(processInstanceId,nowUserId,comment);
        return Result.success(message);
    }

    /**
     * 委托
     * @MethodName:entrust
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:String
     * date: 2018年9月5日 10:12:36
     */
    @ApiOperation(value = "委托")
    @PostMapping ("/entrust")
    public ResponseMessage<String> entrust(String processInstanceId,String nowUserId,String owner) {
        String message = flowProcessUtil.entrust(processInstanceId,nowUserId,owner);
        return Result.success(message);
    }
}
