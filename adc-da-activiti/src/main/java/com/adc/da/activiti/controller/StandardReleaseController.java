package com.adc.da.activiti.controller;

import com.adc.da.activiti.service.StandardReleaseService;
import com.adc.da.activiti.vo.StandardReleaseVO;
import com.adc.da.util.http.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/${restPath}/WorkFlow/StandardRelease")
@Api(description = "企业标准发布审批流程")
public class StandardReleaseController {

    private static final Logger logger = LoggerFactory.getLogger(StandardReleaseController.class);

    /**
     *  企业标准制修订计划调整申请流程的流程图Key
     */
    public  static  final String STANDARD_RELEASE_KEY = "StandardReleaseProcess";

    @Autowired
    private StandardReleaseService standardReleaseService;

    @ApiOperation(value = "起草部门标准化员发起流程")
    @PostMapping("/submitOrSaveProcess")
    public ResponseMessage<String> submitOrSaveProcess(StandardReleaseVO standardReleaseVO) throws Exception {

        return  standardReleaseService.submitOrSaveProcess(standardReleaseVO);

    }


    @ApiOperation(value = "提交或退回")
    @PostMapping ("/SubmitOrRetreatProcess")
    public ResponseMessage<String> SubmitOrRetreatProcess(StandardReleaseVO standardReleaseVO, String taskId,String comment) throws Exception {

        return  standardReleaseService.SubmitOrRetreatProcess(standardReleaseVO,taskId,comment);

    }


    @ApiOperation(value = "根据任务id查询流程变量")
    @PostMapping ("/queryProcessVariable")
    public ResponseMessage<Map<String,Object>> queryProcessVariable(String taskId) throws Exception {
        return standardReleaseService.queryProcessVariable(taskId);
    }
}
