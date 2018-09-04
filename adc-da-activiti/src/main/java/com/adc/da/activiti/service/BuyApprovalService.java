package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.dao.BusProcessEODao;
import com.adc.da.activiti.entity.BuyApprovalEO;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <br>
 * <b>功能：</b>BUS_PROCESS BusProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-31 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("buyApprovalService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BuyApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(BuyApprovalService.class);

    @Autowired
    private BusProcessEODao dao;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    public BusProcessEODao getDao() {
        return dao;
    }


    /**
     *  启动流程
     * @MethodName:startBuyApprovalProcess
     * @author: yuzhong
     * @param:[vehicleApprovalEO, userId,processDefinitionKey]
     * @return:void
     * date: 2018年9月4日 10:24:08
     */
    public ProcessInstance startBuyApprovalProcess(BuyApprovalEO buyApprovalEO,
            String userId, String processDefinitionKey,String processInstanceId){
        //如果有流程Id，那么就删除这个流程，重新赋值流程变量再启动流程
        if(!StringUtils.isEmpty(processInstanceId)){
            flowProcessUtil.deleteByProcessInstance(processInstanceId);
        }
        //设置流程发起人id
        identityService.setAuthenticatedUserId(userId);
        //设置此流程的流程变量
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("流程编号",buyApprovalEO.getProcessNumber());
        map.put("流程类型",buyApprovalEO.getProcessType());
        map.put("流程描述",buyApprovalEO.getProcessDescription());
        map.put("applicat",userId);
        map.put("money",buyApprovalEO.getMoney());
        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,map);
        return processInstance;
    }

    /**
     *  驳回
     * @MethodName:reject
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:void
     * date: 2018年9月4日 16:25:59
     */
    public String reject(String processInstanceId){
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
            return "驳回成功";
        } catch (Exception e) {
            return "驳回失败";
        }
    }
}
