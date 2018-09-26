package com.adc.da.activiti.controller;

import com.adc.da.activiti.service.TakeAdviceService;
import com.adc.da.activiti.vo.TakeAdviceVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/${restPath}/WorkFlow/TakeAdvice")
@Api(description = "标准征求意见审批流程")
public class TakeAdviceController {

    private static final Logger logger = LoggerFactory.getLogger(TakeAdviceController.class);

    /**
     *  企业标准制修订计划调整申请流程的流程图Key
     */
    public  static  final String TAKE_ADVICE_KEY = "TakeAdviceProcess";

    @Autowired
    private TaskService taskService;

    @Autowired
    private TakeAdviceService takeAdviceService;



    @ApiOperation(value = "保存、提交、退回、无关联")
    @PostMapping ("/SaveOrSubmitOrRetreatProcess")
    public ResponseMessage<String> SubmitOrRetreatProcess(TakeAdviceVO takeAdviceVO) throws Exception {
        Task task = null;
        String taskId = takeAdviceVO.getTaskId();
        if(StringUtils.isEmpty(taskId)){
            //初次发起流程申请
            takeAdviceService.engineerSubmitOrSaveProcess(takeAdviceVO,null);
            return Result.success();
        }else {
            //查询当前任务
             task = taskService.createTaskQuery().taskId(takeAdviceVO.getTaskId()).singleResult();
        }
       switch (task.getName()){
           case "法规管理工程师发起流程":{
             return   takeAdviceService.engineerSubmitOrSaveProcess(takeAdviceVO,task.getProcessInstanceId());
           }

           case "法规接口人分发":{
               return   takeAdviceService.rulePersonDistribute(takeAdviceVO.getFlag(),takeAdviceVO.getEngineerIdList(),task);
           }

           case "工程师填写反馈意见":{
               return   takeAdviceService.engineerFillSuggest(takeAdviceVO.getAnalyList(),takeAdviceVO.getSuggestList(),takeAdviceVO.getFlag(),task);
           }

           case "法规接口人汇总反馈意见":{
               return   takeAdviceService.gatherSuggest(takeAdviceVO.getFlag(),takeAdviceVO.getAnalyMap(),takeAdviceVO.getSuggestMap(),task);
           }

           case "法规接口人科长审核":{
               return   takeAdviceService.roomOrDepartmentCheck(takeAdviceVO.getFlag(),task,takeAdviceVO.getComment());
           }

           case "部门负责人审批":{
               return   takeAdviceService.roomOrDepartmentCheck(takeAdviceVO.getFlag(),task,takeAdviceVO.getComment());
           }

           case "法规管理工程师汇总":{
               return   takeAdviceService.gatherSuggest(takeAdviceVO.getFlag(),takeAdviceVO.getAnalyMap(),takeAdviceVO.getSuggestMap(),task);
           }

            default:return null;
       }

    }


    @ApiOperation(value = "根据任务id查询流程变量")
    @PostMapping ("/queryProcessVariable")
    public ResponseMessage<Map<String,Object>> queryProcessVariable(String taskId) throws Exception {
        return takeAdviceService.queryProcessVariable(taskId);
    }
}
