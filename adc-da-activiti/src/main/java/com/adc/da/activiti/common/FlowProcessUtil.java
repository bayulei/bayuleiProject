package com.adc.da.activiti.common;

import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.activiti.service.BusExecuProcessEOService;
import com.adc.da.activiti.service.BusProcessEOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private TaskService taskService;

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
    /**
     *  委托
     * @MethodName:entrust
     * @author: yuzhong
     * @param:[processInstanceId,owner]
     * @return:String
     * date: 2018年9月5日 10:15:04
     */
    public String entrust(String processInstanceId,String owner){
        String oldOwner = taskService.createTaskQuery().processInstanceId(processInstanceId).
                singleResult().getOwner();
        if(!StringUtils.isEmpty(oldOwner)){
            return "单个任务只能委托一次";
        }else{
            Task task=taskService.createTaskQuery().processInstanceId(processInstanceId)
                    .singleResult();
            taskService.delegateTask(task.getId(),owner);
            return "委托成功";
        }
    }

    /**
     *  驳回到上一步
     * @MethodName:reject
     * @author: yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String
     * date: 2018年9月4日 16:25:59
     */
    public String reject(String processInstanceId,String nowUserId){
        try {
            Map<String, Object> variables;
            // 取得当前任务
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            HistoricTaskInstance currTask = historyService
                    .createHistoricTaskInstanceQuery().taskId(task.getId())
                    .singleResult();
            // 取得流程实例
            ProcessInstance instance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(currTask.getProcessInstanceId())
                    .singleResult();
            if (instance == null) {
                return "驳回失败";
            }
            variables = instance.getProcessVariables();
            // 取得流程定义
            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(currTask
                            .getProcessDefinitionId());
            if (definition == null) {
                return "驳回失败";
            }
            // 取得上一步活动
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                    .findActivity(currTask.getTaskDefinitionKey());
            List<PvmTransition> nextTransitionList = currActivity
                    .getIncomingTransitions();
            // 清除当前活动的出口
            List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
            List<PvmTransition> pvmTransitionList = currActivity
                    .getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitionList) {
                oriPvmTransitionList.add(pvmTransition);
            }
            pvmTransitionList.clear();

            // 建立新出口
            List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
            for (PvmTransition nextTransition : nextTransitionList) {
                PvmActivity nextActivity = nextTransition.getSource();
                ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
                        .findActivity(nextActivity.getId());
                TransitionImpl newTransition = currActivity
                        .createOutgoingTransition();
                newTransition.setDestination(nextActivityImpl);
                newTransitions.add(newTransition);
            }
            // 完成任务
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task t : tasks) {
                taskService.complete(t.getId(), variables);
                historyService.deleteHistoricTaskInstance(t.getId());
            }
            // 恢复方向
            for (TransitionImpl transitionImpl : newTransitions) {
                currActivity.getOutgoingTransitions().remove(transitionImpl);
            }
            for (PvmTransition pvmTransition : oriPvmTransitionList) {
                pvmTransitionList.add(pvmTransition);
            }
            //修改业务表的上一操作人
            BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
            busExecuProcessEOPage.setProcInstId(processInstanceId);
            List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
            if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                BusProcessEO busProcessEO = new BusProcessEO();
                busProcessEO.setLastUserId(nowUserId);
                busProcessEO.setLastUserName("查出来是谁");
                busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
            }
            return "驳回成功";
        } catch (Exception e) {
            return "驳回失败";
        }
    }

    @ApiOperation(value = "通过任务ID委托人")
    @GetMapping("/entrustByTaskId")
    public String entrustByTaskId(String taskId,String owner){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String oldOwner = task.getOwner();
        if(!StringUtils.isEmpty(oldOwner)){
            return "单个任务只能委托一次";
        }else{
            taskService.delegateTask(taskId,owner);
            return "委托成功";
        }
    }
}
