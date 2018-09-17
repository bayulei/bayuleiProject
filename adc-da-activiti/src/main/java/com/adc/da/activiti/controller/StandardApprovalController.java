package com.adc.da.activiti.controller;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.service.StandardApprovalService;
import com.adc.da.activiti.vo.StandardApprovalVO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/${restPath}/WorkFlow/StandardApproval")
@Api(description = "企业技术标准制修订年度计划审批发布流程")
public class StandardApprovalController {

    private static final Logger logger = LoggerFactory.getLogger(StandardApprovalController.class);


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private StandardApprovalService standardApprovalService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    /**
     *  部署流程Key
     */
    private   static  final String processDefinitionKey = "StandardApproval";

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
    public ResponseMessage<String> saveProcessInfo(StandardApprovalVO standardApprovalVO, String userId,String processInstanceId){
        //启动流程（为了把信息放入流程变量中）
        try{
            processInstanceId = standardApprovalService.startProcess(standardApprovalVO,userId,processDefinitionKey,processInstanceId);
            return Result.success(processInstanceId);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("保存失败");
        }
    }

    /**
     *  标准化工程师发起
     * @MethodName:startApproval
     * @author: yuzhong
     * @param:[processInstanceId,userId]
     * @return:S
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "标准化工程师发起")
    @PostMapping ("/startApproval")
    public ResponseMessage<String> startApproval(StandardApprovalVO standardApprovalVO, String userId,String processInstanceId,String comment){
        try{
            processInstanceId = standardApprovalService.startProcess(standardApprovalVO,userId,processDefinitionKey,processInstanceId);
            //完成第一步的发起审批
            standardApprovalService.completeFirstApproval(standardApprovalVO,processInstanceId,userId,comment);
            return Result.success(processInstanceId);
        }catch(Exception e){
            e.printStackTrace();
            return Result.error("审批失败");
        }
    }

    /**
     *  流程完成审批
     * @MethodName:completeProcess
     * @author: yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:S
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "流程完成审批")
    @PostMapping ("/completeProcess")
    public ResponseMessage<String> completeProcess(String processInstanceId,String nowUserId,String comment){
        try {
            standardApprovalService.completeProcess(processInstanceId,nowUserId,comment);
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
        //List list = taskService.getTaskComments("1");
        return Result.success(map);
    }

    /**
     * 查看审批历史
     * @MethodName:getTaskInfo
     * @author:yuzhong
     * @param:[nowUserId,processInstanceId]
     * @return:Map
     * date: 2018年9月6日 19:07:04
     */
    @ApiOperation(value = "查看审批历史")
    @PostMapping ("/getTaskInfo")
    public ResponseMessage<Map<String,Object>> getTaskInfo(String nowUserId,String processInstanceId) {
        Map<String,Object> map = standardApprovalService.getTaskInfo(nowUserId,processInstanceId);
        return Result.success(map);
    }
}
