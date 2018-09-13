package com.adc.da.activiti.common;

import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.activiti.service.BusExecuProcessEOService;
import com.adc.da.activiti.service.BusProcessEOService;
import com.adc.da.sys.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
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
import java.util.Date;
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
            //更新业务流程表
            BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
            busExecuProcessEO.setProcInstId(processInstanceId);
            List<BusExecuProcessEO> ExecuProcessEOList = busExecuProcessEOService.selectByEO(busExecuProcessEO);
            busProcessEO.setId(ExecuProcessEOList.get(0).getProcessId());
            busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
    }

    @ApiOperation(value = "业务流程主表新增")
    @GetMapping("/addProcess")
    public void addProcess(String processInstanceId,BusProcessEO  busProcessEO) throws Exception {
        busProcessEO.setId(UUIDUtils.randomUUID20());
        busProcessEO.setCreateTime(new Date());
        busProcessEOService.insertSelective(busProcessEO);
        BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
        busExecuProcessEO.setProcessId(busProcessEO.getId());
        busExecuProcessEO.setProcInstId(processInstanceId);
        busExecuProcessEO.setId(UUIDUtils.randomUUID20());
        busExecuProcessEOService.insertSelective(busExecuProcessEO);
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

   /* *//**
     *  驳回到上一步
     * @MethodName:reject
     * @author: yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String
     * date: 2018年9月4日 16:25:59
     *//*
    public String reject(String processInstanceId,String nowUserId,String comment){
        try {
            Map<String, Object> variables;
            // 取得当前任务
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
//            if (!StringUtils.isEmpty(comment)) {
//                // 由于流程用户上下文对象是线程独立的，所以要在需要的位置设置，要保证设置和获取操作在同一个线程中
//                Authentication.setAuthenticatedUserId(nowUserId);//批注人的名称  一定要写，不然查看的时候不知道人物信息
//                // 添加批注信息
//                taskService.addComment(task.getId(), null, comment);//comment为批注内容
//            }
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

    public String rejectToStart(String processInstanceId){
        try {
            Map<String, Object> variables;
            // 取得当前任务
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            HistoricTaskInstance currTask = historyService
                    .createHistoricTaskInstanceQuery().taskId(task.getId())
                    .singleResult();
            // 取得流程实例j
            ProcessInstance instance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            variables = instance.getProcessVariables();
            // 取得流程定义
            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(instance.getProcessDefinitionId());

            // 取得初始活动
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .list();
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                    .findActivity(historicActivityInstanceList.get(1).getActivityId());

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

            return "驳回成功";
        } catch (Exception e) {
            return "驳回失败";
        }
    }*/

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

    /**
     * 启动流程,创建流程实例
     *
     * @MethodName:startStandardApprovalProcess
     * @author: DuYunbao
     * @param:[vehicleApprovalEO]
     * @return:org.activiti.engine.runtime.ProcessInstance date: 2018/9/3 20:15
     */
    public ProcessInstance startStandardApprovalProcess(String key) {
        //从session取当前登录人Id
        String userId = "dyb";

        //设置流程发起人id
        Authentication.setAuthenticatedUserId(userId);
        //与正在执行的流程实例和执行对象相关的Service
        ProcessInstance pi = runtimeService
                //使用流程定义的key启动流程实例，key对应bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
                .startProcessInstanceByKey(key);
        return pi;
    }



    /**  驳回到任意节点
     * @MethodName:rejectTask
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId,destTaskKey,rejectMessage]
     * @return:String
     * date: 2018年9月12日 13:31:02
     */
    public String rejectTask(String processInstanceId,String nowUserId,String destTaskKey,String rejectMessage){
        //获得当前任务的对应实列
        Task taskEntity =  taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(nowUserId).singleResult();
//        if (!StringUtils.isEmpty(rejectMessage)) {
//            // 由于流程用户上下文对象是线程独立的，所以要在需要的位置设置，要保证设置和获取操作在同一个线程中
//            Authentication.setAuthenticatedUserId("");//批注人的名称  一定要写，不然查看的时候不知道人物信息
//            // 添加批注信息
//            taskService.addComment(taskEntity.getId(), null, rejectMessage);//comment为批注内容
//        }
        if(taskEntity!=null) {
            //当前任务key
            String taskDefKey = taskEntity.getTaskDefinitionKey();
            //获得当前流程的定义模型

            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(taskEntity.getProcessDefinitionId());

            //获得当前流程定义模型的所有任务节点

            List<ActivityImpl> activitilist = processDefinition.getActivities();
            //获得当前活动节点和驳回的目标节点"draft"
            ActivityImpl currActiviti = null;//当前活动节点
            ActivityImpl destActiviti = null;//驳回目标节点
            int sign = 0;
            for (ActivityImpl activityImpl : activitilist) {
                //确定当前活动activiti节点

                if (taskDefKey.equals(activityImpl.getId())) {
                    currActiviti = activityImpl;

                    sign++;
                } else if (destTaskKey.equals(activityImpl.getId())) {
                    destActiviti = activityImpl;

                    sign++;
                }
                //System.out.println("//-->activityImpl.getId():"+activityImpl.getId());
                if (sign == 2) {
                    break;//如果两个节点都获得,退出跳出循环
                }
            }
            //保存当前活动节点的流程想参数

            List<PvmTransition> hisPvmTransitionList = new ArrayList<PvmTransition>(0);

            for (PvmTransition pvmTransition : currActiviti.getOutgoingTransitions()) {
                hisPvmTransitionList.add(pvmTransition);
            }
            //清空当前活动几点的所有流出项

            currActiviti.getOutgoingTransitions().clear();
            System.out.println("//-->currActiviti.getOutgoingTransitions().clear():" + currActiviti.getOutgoingTransitions().size());
            //为当前节点动态创建新的流出项

            TransitionImpl newTransitionImpl = currActiviti.createOutgoingTransition();
            //为当前活动节点新的流出目标指定流程目标
            newTransitionImpl.setDestination(destActiviti);
            //保存驳回意见

            taskEntity.setDescription(rejectMessage);//设置驳回意见
            taskService.saveTask(taskEntity);
            //设定驳回标志

//        Map<String, Object> variables = new HashMap<String, Object>(0);
//        variables.put(WfConstant.WF_VAR_IS_REJECTED.value(), WfConstant.IS_REJECTED.value());
            //执行当前任务驳回到目标任务draft
            taskService.complete(taskEntity.getId());

            //获取驳回到目的节点（最新审批的）之前的审批人，并将审批人赋值给最新的节点上
            Task nowTask = taskService.createTaskQuery().executionId(taskEntity.getExecutionId()).singleResult();
            List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().taskDefinitionKey(destTaskKey)
                    .orderByTaskCreateTime().desc().list();
            if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
                //第二条才是最新的目的节点上次历史记录，因为第一条是最新的一条，不是历史
                taskService.setAssignee(nowTask.getId(),historicTaskInstanceList.get(1).getAssignee());
            }

//            //清除目标节点的新流入项
//            destActiviti.getIncomingTransitions().remove(newTransitionImpl);
//            //清除原活动节点的临时流程项
//            currActiviti.getOutgoingTransitions().clear();
//            //还原原活动节点流出项参数
//            currActiviti.getOutgoingTransitions().addAll(hisPvmTransitionList);

            // 清空现有流向
            List<PvmTransition> pvmTransitionList = currActiviti.getOutgoingTransitions();
            pvmTransitionList.clear();
            // 还原以前流向
            for (PvmTransition pvmTransition : hisPvmTransitionList) {
                pvmTransitionList.add(pvmTransition);
            }

            return "驳回成功";
        }else{
            return "您不是此节点的审批人";
        }
    }
}
