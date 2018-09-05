package com.adc.da.activiti.controller;


import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.VehicleApprovalEO;
import com.adc.da.activiti.service.VehicleApprovalService;
import com.adc.da.activiti.vo.ProcessInformationVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${restPath}/WorkFlow/VehicleApproval")
@Api(description = "整车认可试验计划下达及验证流程")
public class VehicleApprovalController{

    private static final Logger logger = LoggerFactory.getLogger(VehicleApprovalController.class);


    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    @Autowired
    private VehicleApprovalService vehicleApprovalService;

    /**
     *  整车认可试验计划下达及验证流程的流程图Key
     */
    public  static  final String ProcessDefinitionKey = "VerificationProcess";




    /**
     *  查询项目组总体经理自己发起的流程
     * @MethodName:queryStandardApprovalProcess
     * @author: DuYunbao
     * @param:[]
     * @return:com.adc.da.util.http.ResponseMessage<java.util.List<org.activiti.engine.history.HistoricProcessInstance>>
     * date: 2018/8/27 15:47
     */
    @ApiOperation(value = "查询项目组总体经理自己发起的流程")
    @PostMapping("/queryStandardApprovalProcess")
    public ResponseMessage<List<ProcessInformationVO>> queryStandardApprovalProcess(){

        //从session取当前登录人Id
        String userId = "dyb";
        //查询当前登录人历史流程
         List<HistoricProcessInstance>  historyProcess = historyService
                                                        .createHistoricProcessInstanceQuery()
                                                        .startedBy(userId).list();

        List<ProcessInformationVO>  processInformationVOList = new ArrayList<>();
        //查询各流程的运行情况
        for (HistoricProcessInstance historicProcessInstance : historyProcess){

            ProcessInformationVO informationVO = new ProcessInformationVO();
            informationVO.setProcessNumber(historicProcessInstance.getProcessDefinitionId());

            //查询历史任务
            List<HistoricTaskInstance> historicTaskList = historyService.createHistoricTaskInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId())
                    .orderByHistoricTaskInstanceEndTime().desc().list();
            if(historicTaskList.size() > 1){
                informationVO.setLastTaskProcessor(historicTaskList.get(1).getAssignee());
            }else {
                informationVO.setLastTaskProcessor(historicTaskList.get(0).getAssignee());
            }
            informationVO.setTaskProcessor(historicTaskList.get(0).getAssignee());
            informationVO.setTaskName(historicTaskList.get(0).getName());
            informationVO.setReceiveTime(historicTaskList.get(0).getCreateTime());
            informationVO.setCompleteTime(historicProcessInstance.getEndTime());

            HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId()).variableName("项目组总体经理发起流程表单").singleResult();
            VehicleApprovalEO vehicleApprovalEO = (VehicleApprovalEO)historicVariableInstance.getValue();
            informationVO.setProcessType(vehicleApprovalEO.getProcessType());

            if(historicProcessInstance.getEndTime()!= null){
                //任务已完成
                informationVO.setStatus(1);
            }else{
                informationVO.setStatus(0);
            }
            processInformationVOList.add(informationVO);
        }
        return  Result.success(processInformationVOList);
    }


    /**
     *  项目组总体经理提交或者保存流程
     * @MethodName:startStandardApprovalProcess
     * @author: DuYunbao
     * @param:[vehicleApprovalEO, userIds]
     * @return:void
     * date: 2018/8/27 15:01
     */
    @ApiOperation(value = "项目组总体经理提交或者保存流程")
    @PostMapping ("/submitOrSaveStandardApprovalProcess")
    public ResponseMessage<String> submitOrSaveStandardApprovalProcess(VehicleApprovalEO vehicleApprovalEO, MultipartFile file
    ,String flag,String ProcessInstanceId) throws Exception {

        //调用上传附件的接口
        String url = "附件地址";
        vehicleApprovalEO.setFileUrl(url);
        return  vehicleApprovalService.submitOrSaveStandardApprovalProcess(vehicleApprovalEO,flag,ProcessInstanceId);

    }


}
