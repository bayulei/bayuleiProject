package com.adc.da.activiti;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/${restPath}/testActiviti")
@Api(description = "|activiti|使用类")
public class HelloWord {
    @Autowired
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @ApiOperation(value = "|activiti|部署测试")
    @GetMapping("/deploymentProcessDefinition")
    public void deploymentProcessDefinition(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("activiti/Desktop.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("流程定义")//添加部署的名称
                .addZipInputStream(zipInputStream)//指定zip格式的文件完成部署
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//
        System.out.println("部署名称："+deployment.getName());//
    }

    @ApiOperation(value = "|activiti|启动流程")
    @GetMapping("/startProcessInstance")
    public void startProcessInstance(){
        //流程定义的key
        String processDefinitionKey = "helloworld";
        ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
        System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4
    }

}
