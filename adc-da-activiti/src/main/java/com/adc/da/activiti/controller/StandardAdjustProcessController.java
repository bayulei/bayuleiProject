package com.adc.da.activiti.controller;

import com.adc.da.activiti.service.StandardAdjustProcessService;
import com.adc.da.activiti.vo.StandardAdjustVO;
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
@RequestMapping("/${restPath}/WorkFlow/StandardAdjust")
@Api(description = "企业标准制修订计划调整申请流程")
public class StandardAdjustProcessController {

    private static final Logger logger = LoggerFactory.getLogger(StandardAdjustProcessController.class);

    /**
     *  企业标准制修订计划调整申请流程的流程图Key
     */
    public  static  final String STANDARD_ADJUST_KEY = "StandardAdjustProcess";

    @Autowired
    private StandardAdjustProcessService standardAdjustProcessService;

    @ApiOperation(value = "标准编写部门发起流程")
    @PostMapping("/submitOrSaveStandardAdjustProcess")
    public ResponseMessage<String> submitOrSaveStandardApprovalProcess(StandardAdjustVO standardAdjustVO) throws Exception {

        return  standardAdjustProcessService.submitOrSaveStandardApprovalProcess(standardAdjustVO);

    }


    @ApiOperation(value = "提交或退回")
    @PostMapping ("/SubmitOrRetreatProcess")
    public ResponseMessage<String> SubmitOrRetreatProcess(String flag, String taskId,String comment) throws Exception {

        return  standardAdjustProcessService.SubmitOrRetreatProcess(flag,taskId,comment);

    }


    @ApiOperation(value = "根据任务id查询流程变量")
    @PostMapping ("/queryProcessVariable")
    public ResponseMessage<Map<String,Object>> queryProcessVariable(String taskId) throws Exception {
        return standardAdjustProcessService.queryProcessVariable(taskId);
    }
}
