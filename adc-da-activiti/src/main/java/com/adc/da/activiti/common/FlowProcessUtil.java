package com.adc.da.activiti.common;

import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.service.BusExecuProcessEOService;
import com.adc.da.activiti.service.BusProcessEOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/${restPath}/WorkFlow/VehicleApproval")
@Api(description = "流程公共类")
public class FlowProcessUtil {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private BusExecuProcessEOService busExecuProcessEOService;

    @Autowired
    private BusProcessEOService busProcessEOService;

    @ApiOperation(value = "部署流程")
    @GetMapping("/deploymentProcessDefinition")
    public String deploymentProcessDefinition(String name){
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("activiti/"+name+".zip");
            ZipInputStream zipInputStream = new ZipInputStream(in);
            //与流程定义和部署对象相关的Service
            Deployment deployment = repositoryService
                    //创建一个部署对象
                    .createDeployment()
                    //添加部署的名称
                    .name(name+"流程定义")
                    //指定zip格式的文件完成部署
                    .addZipInputStream(zipInputStream)
                    //完成部署
                    .deploy();
            System.out.println("部署ID："+deployment.getId());
            System.out.println("部署名称："+deployment.getName());
            return "成功";
        }catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
    }


    @ApiOperation(value = "删除流程实例")
    @GetMapping("/deleteByProcessInstance")
    public void deleteByProcessInstance(String processInstanceId){
        runtimeService.deleteProcessInstance(processInstanceId,"想删");
        historyService.deleteHistoricProcessInstance(processInstanceId);
    }

    @ApiOperation(value = "删除流程部署")
    @GetMapping("/deleteByeployment")
    public void deleteByDeployment(String deployment){
        repositoryService.deleteDeployment(deployment,true);
       // historyService.deleteHistoricProcessInstance(processInstanceId);
    }

    @ApiOperation(value = "更新业务流程主表")
    @GetMapping("/updateProcessByProcessInstanceId")
    public void updateProcessByProcessInstanceId(String processInstanceId,BusProcessEO  busProcessEO) throws Exception {
       //查询流程和业务关系表
        BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
        busExecuProcessEO.setProcInstId(processInstanceId);
        List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.selectByEO(busExecuProcessEO);
        //更新业务流程主表
        busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
        busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
    }
}
