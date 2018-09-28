package com.adc.da.activiti.service;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.entity.BusResAnalyProcessEO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.activiti.page.BusResAnalyProcessEOPage;
import com.adc.da.activiti.vo.StandardComplianceVO;
import com.adc.da.sys.util.UUIDUtils;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * <br>
 * <b>功能：</b>BUS_PROCESS BusProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-31 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Component
//@Service("noticeAndCheckApprovalService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class StandardComplianceProcessService {

    private static final Logger logger = LoggerFactory.getLogger(StandardComplianceProcessService.class);

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    @Autowired
    private BusExecuProcessEOService busExecuProcessEOService;

    @Autowired
    private BusProcessEOService busProcessEOService;

    @Autowired
    private BusResAnalyProcessEOService busResAnalyProcessEOService;

    /**
     * 启动流程
     *
     * @MethodName:startProcess
     * @author: yuzhong
     * @param:[standardComplianceVO,userId,processDefinitionKey,processInstanceId]
     * @return:void date: 2018年9月4日 10:24:08
     */
    public String startProcess(StandardComplianceVO standardComplianceVO,
            String userId, String processDefinitionKey, String processInstanceId) throws Exception {
        //如果有流程Id，那么修改流程变量，不要重复启动流程
        if (!StringUtils.isEmpty(processInstanceId)) {
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(userId)
                    .singleResult();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("processNumber", standardComplianceVO.getProcessNumber());
            map.put("processType", standardComplianceVO.getProcessType());
            map.put("processDescription", standardComplianceVO.getProcessDescription());
            map.put("endTime",standardComplianceVO.getEndTime());
            map.put("projectName",standardComplianceVO.getProjectName());
            if (!StringUtils.isEmpty(standardComplianceVO.getApprovalOpinion())) {
                //设置意见
                task.setDescription(standardComplianceVO.getApprovalOpinion());
                taskService.saveTask(task);
            }
            switch (task.getName()){
                case "法规管理工程师发起流程":
                    map.put("applicat", userId);
                    List<String> projectManagerList = new ArrayList<String>();
                    BusResAnalyProcessEOPage page = new BusResAnalyProcessEOPage();
                    page.setProcInstId(processInstanceId);
                    List<BusResAnalyProcessEO> list = busResAnalyProcessEOService.queryByList(page);
                    if(list!=null && !list.isEmpty()){
                        busResAnalyProcessEOService.deleteAnalyInfo(processInstanceId);
                    }
                    List<BusResAnalyProcessEO> busResAnalyProcessEOList = standardComplianceVO.getBusResAnalyProcessEOList();
                    if(busResAnalyProcessEOList!=null && !busResAnalyProcessEOList.isEmpty()){
                        for(BusResAnalyProcessEO busResAnalyProcessEO : busResAnalyProcessEOList){
                            if(busResAnalyProcessEO.getProjectManager()!=null) {
                                projectManagerList.add(busResAnalyProcessEO.getProjectManager());
                            }
                            busResAnalyProcessEO.setId(UUIDUtils.randomUUID20());
                            busResAnalyProcessEOService.insertSelective(busResAnalyProcessEO);
                        }
                    }
                    map.put("projectManagerList",projectManagerList);
                    BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
                    busExecuProcessEOPage.setProcInstId(processInstanceId);
                    List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
                    if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                        //往流程业务和流程的关联表添加数据
                        BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
                        busExecuProcessEO.setProcInstId(processInstanceId);
                        busExecuProcessEO.setId(busExecuProcessEOList.get(0).getId());
                        busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
                        //往流程业务表添加数据
                        BusProcessEO busProcessEO = new BusProcessEO();
                        busProcessEO.setProcessNumber(standardComplianceVO.getProcessNumber());
                        busProcessEO.setProcessType(standardComplianceVO.getProcessType());
                        busProcessEO.setCreateUserId(userId);
                        busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                        //1代表未完成
                        busProcessEO.setProcessStatus("1");
                        busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
                    }
                    break;
                case "项目经理分发":
                    List<String> engineerList = new ArrayList<String>();

                    List<BusResAnalyProcessEO> busResAnalyProcessEOS = standardComplianceVO.getBusResAnalyProcessEOList();
                    if (busResAnalyProcessEOS != null && !busResAnalyProcessEOS.isEmpty()) {
                        for (BusResAnalyProcessEO busResAnalyProcessEO : busResAnalyProcessEOS) {
                            if(busResAnalyProcessEO.getEngineer()!=null){
                                engineerList.add(busResAnalyProcessEO.getEngineer());
                            }
                            busResAnalyProcessEOService.updateByPrimaryKeySelective(busResAnalyProcessEO);
                        }
                    }
                    taskService.setVariable(task.getId(),"engineerList",engineerList);
                    break;
                case "责任工程师填写反馈意见":
                    List<BusResAnalyProcessEO> busResAnalyProcessEOS1 = standardComplianceVO.getBusResAnalyProcessEOList();
                    if (busResAnalyProcessEOS1 != null && !busResAnalyProcessEOS1.isEmpty()) {
                        for (BusResAnalyProcessEO busResAnalyProcessEO : busResAnalyProcessEOS1) {
                            busResAnalyProcessEOService.updateByPrimaryKeySelective(busResAnalyProcessEO);
                        }
                    }
                    break;
                case "项目经理审核":
                    List<BusResAnalyProcessEO> busResAnalyProcessEOS2 = standardComplianceVO.getBusResAnalyProcessEOList();
                    if(busResAnalyProcessEOS2!=null && !busResAnalyProcessEOS2.isEmpty()){
                        for(BusResAnalyProcessEO busResAnalyProcessEO : busResAnalyProcessEOS2){
                            busResAnalyProcessEOService.updateByPrimaryKeySelective(busResAnalyProcessEO);
                        }
                    }
                    break;
            }
            runtimeService.setVariables(processInstanceId,map);
        }else{
            //设置流程发起人id
            identityService.setAuthenticatedUserId(userId);
            //设置此流程的流程变量
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("processNumber", standardComplianceVO.getProcessNumber());
            map.put("processType", standardComplianceVO.getProcessType());
            map.put("processDescription", standardComplianceVO.getProcessDescription());
            map.put("endTime",standardComplianceVO.getEndTime());
            map.put("projectName",standardComplianceVO.getProjectName());
            map.put("applicat", userId);
            List<String> projectManagerList = new ArrayList<String>();
            List<BusResAnalyProcessEO> busResAnalyProcessEOList = standardComplianceVO.getBusResAnalyProcessEOList();
            if(busResAnalyProcessEOList!=null && !busResAnalyProcessEOList.isEmpty()){
                for(BusResAnalyProcessEO busResAnalyProcessEO : busResAnalyProcessEOList){
                    if(busResAnalyProcessEO.getProjectManager()!=null) {
                        projectManagerList.add(busResAnalyProcessEO.getProjectManager());
                    }
                    busResAnalyProcessEO.setId(UUIDUtils.randomUUID20());
                    busResAnalyProcessEOService.insertSelective(busResAnalyProcessEO);
                }
            }
            projectManagerList = new ArrayList<>(new HashSet<>(projectManagerList));

            map.put("projectManagerList",projectManagerList);
            //启动流程
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
            processInstanceId = processInstance.getId();
            //获取当前执行的任务
            Task task = taskService.createTaskQuery()
                    .processInstanceId(processInstance.getProcessInstanceId())
                    .singleResult();

            taskService.setAssignee(task.getId(),userId);
            //往流程业务表添加数据
            BusProcessEO busProcessEO = new BusProcessEO();
            busProcessEO.setProcessNumber(standardComplianceVO.getProcessNumber());
            busProcessEO.setProcessType(standardComplianceVO.getProcessType());
            busProcessEO.setCreateUserId(userId);
            String busprocessId = UUIDUtils.randomUUID20();
            busProcessEO.setId(busprocessId);
            //1代表未完成
            busProcessEO.setProcessStatus("1");
            busProcessEO.setCreateTime(new Date(System.currentTimeMillis()));
            busProcessEOService.insertSelective(busProcessEO);
            //往流程业务和流程的关联表添加数据
            BusExecuProcessEO busExecuProcessEO = new BusExecuProcessEO();
            busExecuProcessEO.setProcessId(busprocessId);
            busExecuProcessEO.setProcInstId(processInstance.getId());
            busExecuProcessEO.setId(UUIDUtils.randomUUID20());
            busExecuProcessEOService.insertSelective(busExecuProcessEO);

        }
        return processInstanceId;
    }

    /**
     * 完成后赋值待办人
     *
     * @MethodName:addAssignee
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public String addAssignee(Task task,StandardComplianceVO standardComplianceVO,String processInstanceId, String nowUserId) throws Exception {
        if("质量组项目经理审批".equals(task.getName())){
            //获取当前执行的任务(已经是第二步了)
            List<Task> taskList = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .list();
            List<String> projectManagerList = (List<String>) runtimeService.getVariable(processInstanceId,"projectManagerList");
            if (taskList != null && !taskList.isEmpty()) {
                //设置下一步的代办人
                int i = 0;
                for (Task taskNow : taskList) {
                    taskService.setAssignee(taskNow.getId(),projectManagerList.get(i));
                    i++;
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
            }
        }else if("项目经理分发".equals(task.getName())){
            //获取当前执行的任务(已经是第二步了)
            String parentTaskId = task.getId();
            String parentExecutionId = task.getExecutionId();
            //通过上一任务的执行对象Id获取开启子任务的总执行对象
            Execution execution = runtimeService.createExecutionQuery().parentId(parentExecutionId).singleResult();
            //通过获得子任务的执行对象获取具体开启多少个的子任务
            List<Execution> executionList = runtimeService.createExecutionQuery().parentId(execution.getId()).list();
            if(executionList!=null && !executionList.isEmpty()){
                int i = 0;
                for(Execution ex : executionList){
                    Task t = taskService.createTaskQuery().executionId(ex.getId()).singleResult();
                    List<String> engineerList = (List<String>) taskService.getVariable(t.getId(),"engineerList");
                    t.setAssignee(engineerList.get(i));
                    t.setParentTaskId(parentTaskId);
                    taskService.saveTask(t);
                    i++;
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
            }
        }else if("责任工程师填写反馈意见".equals(task.getName())){
            List<HistoricTaskInstance> childTaskList = historyService.createHistoricTaskInstanceQuery().taskParentTaskId(task.getParentTaskId()).list();
            if(childTaskList!=null && !childTaskList.isEmpty()){
                //用num来计算未完成的有多少个子任务
                int num = 0;
                for(HistoricTaskInstance historicTaskInstance : childTaskList){
                    if(historicTaskInstance.getEndTime()==null){
                        num++;
                    }
                }
                //如果num是0，那么证明此子流程的子任务已经全部完成，那么就进去子任务的下一步
                if(num==0){
                    HistoricTaskInstance parentTask = historyService.createHistoricTaskInstanceQuery().taskId(task.getParentTaskId()).singleResult();
                    Task t = taskService.createTaskQuery().executionId(parentTask.getExecutionId()).singleResult();
                    t.setAssignee(parentTask.getAssignee());
                    taskService.saveTask(t);
                }
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
        }else {
            Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId)
                    .parentId(processInstanceId).singleResult();
            if (execution != null) {
                //查询完成上一次任务后当前任务
                Task taskNow = taskService.createTaskQuery().executionId(task.getExecutionId()).singleResult();
                if (taskNow != null) {
                    //设置下一步的代办人
                    taskService.setAssignee(taskNow.getId(), "下一个人");
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
                }
            } else {
//                Map map = runtimeService.getVariables(processInstanceId);
//                map.put("userId","下一个人");
//                runtimeService.setVariables(processInstanceId,map);
                Task taskNow = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
                if (taskNow != null) {
                    //设置下一步的代办人
                    taskService.setAssignee(taskNow.getId(), "下一个人");
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
                } else {
                    //修改业务表的上一操作人，完成状态，完成时间
                    BusExecuProcessEOPage busExecuProcessEOPage = new BusExecuProcessEOPage();
                    busExecuProcessEOPage.setProcInstId(processInstanceId);
                    List<BusExecuProcessEO> busExecuProcessEOList = busExecuProcessEOService.queryByList(busExecuProcessEOPage);
                    if (busExecuProcessEOList != null && !busExecuProcessEOList.isEmpty()) {
                        BusProcessEO busProcessEO = new BusProcessEO();
                        busProcessEO.setLastUserId(nowUserId);
                        busProcessEO.setLastUserName("查出来是谁");
                        busProcessEO.setEndTime(new Date(System.currentTimeMillis()));
                        busProcessEO.setProcessStatus("2");
                        busProcessEO.setId(busExecuProcessEOList.get(0).getProcessId());
                        busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
                    }
                }
            }
        }
        return processInstanceId;
    }

    /**
     * 流程完成审批
     *
     * @MethodName:completeProcess
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String date: 2018年9月5日 16:40:26
     */
    public Task completeProcess(StandardComplianceVO standardComplianceVO,String processInstanceId, String nowUserId) throws Exception {
        //通过代办人，获取该人需要执行的任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(nowUserId)
                .singleResult();
        if(task!=null) {
            //将提交申请第一步任务走完 即向后执行一步
            taskService.complete(task.getId());
        }
        return task;
    }

    /**
     * 查看任务详情
     *
     * @MethodName:getTaskInfo
     * @author:yuzhong
     * @param:[taskId]
     * @return:Map date: 2018年9月5日 16:40:26
     */
    public Map<String, Object> getTaskInfo(String nowUserId, String processInstanceId) throws Exception{
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                .taskAssignee(nowUserId).singleResult();
        //获取历史的流程变量
        List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery()
                .executionId(processInstanceId).list();
        if (historicVariableInstanceList != null && !historicVariableInstanceList.isEmpty()) {
            for (HistoricVariableInstance hvi : historicVariableInstanceList) {
                resultMap.put(hvi.getVariableName(),hvi.getValue());
            }
        }

        //获取工程师审批的列表（根据流程进行到哪一步，来判断需要展现列表数据）
        switch (task.getName()){
            case "质量组项目经理审批":
                //获取法规管理工程师分发的项目经理
                BusResAnalyProcessEOPage busResAnalyProcessEOPage = new BusResAnalyProcessEOPage();
                busResAnalyProcessEOPage.setProcInstId(processInstanceId);
                List<BusResAnalyProcessEO> busResAnalyProcessEOList = busResAnalyProcessEOService.queryByList(busResAnalyProcessEOPage);
                resultMap.put("ApprovalList",busResAnalyProcessEOList);
                break;
            case "项目经理分发":
            case "项目经理审核":
                /*获取项目经理登录展现的数据*/
                BusResAnalyProcessEOPage busResAnalyProcessEOPage1 = new BusResAnalyProcessEOPage();
                busResAnalyProcessEOPage1.setProcInstId(processInstanceId);
                busResAnalyProcessEOPage1.setProjectManager(nowUserId);
                List<BusResAnalyProcessEO> busResAnalyProcessEOList1 = busResAnalyProcessEOService.queryByList(busResAnalyProcessEOPage1);
                resultMap.put("ApprovalList",busResAnalyProcessEOList1);
                break;
            case "责任工程师填写反馈意见":
                /*获取工程师登录所展现的数据*/
                BusResAnalyProcessEOPage busResAnalyProcessEOPage2 = new BusResAnalyProcessEOPage();
                busResAnalyProcessEOPage2.setProcInstId(processInstanceId);
                busResAnalyProcessEOPage2.setEngineer(nowUserId);
                List<BusResAnalyProcessEO> busResAnalyProcessEOList2 = busResAnalyProcessEOService.queryByList(busResAnalyProcessEOPage2);
                resultMap.put("ApprovalList",busResAnalyProcessEOList2);
                break;
            case "发起人审批":
                //此流程下所有的部门所选工程师所有结果
//                BusNoticecheckProcessEOPage busNoticecheckProcessEOPage4 = new BusNoticecheckProcessEOPage();
//                busNoticecheckProcessEOPage4.setProcInstId(processInstanceId);
//                busNoticecheckProcessEOPage4.setTaskId(task.getId());
//                List<BusNoticecheckProcessEO> busNoticecheckProcessEOList4 = busNoticecheckProcessEOService.queryByList(busNoticecheckProcessEOPage4);
//                resultMap.put("engineerList",busNoticecheckProcessEOList4);
                break;
        }


        //获取当前人的审批意见（只是保存了，并没有提交审批）
        HistoricTaskInstance currHistoricTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
        resultMap.put("当前审批意见",currHistoricTaskInstance.getDescription());

        //获取历史的审批意见
        List<Map<String,String>> commentList = new ArrayList<Map<String,String>>();

        List<HistoricTaskInstance> historicTaskInstanceList = new ArrayList<HistoricTaskInstance>();
        //如果执行对象和流程实例不相同，证明此时是子流程环节，子流程只能查看当前流程的审批历史和子流程开启前的审批历史
        if(!task.getExecutionId().equals(processInstanceId)) {
            historicTaskInstanceList.addAll(historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc()
                    .executionId(processInstanceId).list());
            if("收文部门负责人审核".equals(task.getName())) {
                historicTaskInstanceList.addAll(historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc()
                        .executionId(task.getExecutionId()).list());
            }
        }else{
            //如果两者相同，那么就是主流程，那么需要查看之前所有的子流程和主流程的审批历史
            historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc()
                    .processInstanceId(processInstanceId).list();
        }
        if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
            for(HistoricTaskInstance historicTaskInstance : historicTaskInstanceList){
                if(historicTaskInstance.getEndTime()!=null) {
                    Map<String, String> map = new HashMap<String, String>();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if(historicTaskInstance.getDescription()!=null && !StringUtils.isEmpty(historicTaskInstance.getDescription())){
                        map.put("审批人", historicTaskInstance.getAssignee());
                        map.put("审批日期", simpleDateFormat.format(historicTaskInstance.getStartTime()));
                        map.put("审批意见", historicTaskInstance.getDescription());
                    }else{
                        map.put("发起人", historicTaskInstance.getAssignee());
                        map.put("发起日期", simpleDateFormat.format(historicTaskInstance.getStartTime()));
                    }
                    commentList.add(map);
                }
            }
        }
        resultMap.put("comment",commentList);
        return resultMap;
    }

    /**
     * 驳回到任意节点
     *
     * @MethodName:reject
     * @author:yuzhong
     * @param:[processInstanceId,comment]
     * @return:void
     * date:2018年9月17日 14:12:08
     */
    public String reject(String processInstanceId,String nowUserId,String comment) throws Exception {
        String message = "";
        Task taskEntity =  taskService.createTaskQuery().processInstanceId(processInstanceId).taskAssignee(nowUserId).singleResult();
        String destTaskKey = "";
        String nowTaskKey = taskEntity.getTaskDefinitionKey();
        //当前任务key
        switch (nowTaskKey){
        case "usertask2":
            destTaskKey = "usertask1";
            break;
//        case "usertask3":
//            destTaskKey = "usertask1";
//            break;
//        case "usertask4":
//            break;
//        case "usertask5":
//            destTaskKey = "usertask4";
//            break;
//        case "usertask7":
//            destTaskKey = "usertask6";
//            break;
//        case "usertask8":
//            destTaskKey = "usertask6";
//            break;
        default:
            message = "无需驳回";
        }
        if(message.equals("无需驳回")){
            return message;
        }
        if("usertask4".equals(nowTaskKey)){
            List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().taskDefinitionKey("usertask1").orderByTaskCreateTime().desc().list();
            if(historicTaskInstanceList!=null && !historicTaskInstanceList.isEmpty()){
                for(HistoricTaskInstance historicTaskInstance : historicTaskInstanceList){
                    if(historicTaskInstance.getEndTime()!=null){
                        Map map = runtimeService.getVariables(processInstanceId);
                        map.put("userId",historicTaskInstance.getAssignee());
                        map.put("flag",2);
                        runtimeService.setVariables(processInstanceId,map);
                        break;
                    }
                }
            }

            List<Task> taskList =  taskService.createTaskQuery().processInstanceId(processInstanceId).list();
            if(taskList!=null && !taskList.isEmpty()){
                for(Task task : taskList){
                    task.setDescription(comment);//设置驳回意见
                    taskService.saveTask(task);
                    taskService.complete(task.getId());
                }
            }
        }else {
            message = flowProcessUtil.rejectTask(processInstanceId, nowUserId, destTaskKey, comment);
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
        return message;
    }
}
