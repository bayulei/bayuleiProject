package com.adc.da.activiti.controller;

import com.adc.da.activiti.entity.VehicleApprovalEO;
import com.adc.da.activiti.service.StandardAdjustProcessService;
import com.adc.da.util.http.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/${restPath}/WorkFlow/StandardAdjust")
@Api(description = "企业标准制修订计划调整申请流程")
public class StandardAdjustProcessController {

    private static final Logger logger = LoggerFactory.getLogger(StandardAdjustProcessController.class);

    /**
     *  企业标准制修订计划调整申请流程的流程图Key
     */
    public  static  final String ProcessDefinitionKey = "StandardAdjustProcess";

    @Autowired
    private StandardAdjustProcessService standardAdjustProcessService;

   /* @ApiOperation(value = "标准编写部门发起流程")
    @PostMapping("/submitOrSaveStandardApprovalProcess")
    public ResponseMessage<String> submitOrSaveStandardApprovalProcess(@RequestBody VehicleApprovalEO vehicleApprovalEO, MultipartFile file
            , String flag, String ProcessInstanceId) throws Exception {

        //调用上传附件的接口
        String url = "附件地址";
        vehicleApprovalEO.setFileUrl(url);
        return  vehicleApprovalService.submitOrSaveStandardApprovalProcess(vehicleApprovalEO,flag,ProcessInstanceId);

    }*/
}
