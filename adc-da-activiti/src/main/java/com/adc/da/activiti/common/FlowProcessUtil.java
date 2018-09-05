package com.adc.da.activiti.common;

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
}
