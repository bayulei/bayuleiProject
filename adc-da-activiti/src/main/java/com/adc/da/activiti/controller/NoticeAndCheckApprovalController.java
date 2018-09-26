package com.adc.da.activiti.controller;

import com.adc.da.activiti.common.FlowProcessUtil;
import com.adc.da.activiti.service.NoticeAndCheckApprovalService;
import com.adc.da.activiti.vo.NoticeAndCheckApprovalVO;
import com.adc.da.activiti.vo.StandardApprovalVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/${restPath}/WorkFlow/NoticeAndCheckApproval")
@Api(description = "标准法规实施通知及排查流程")
public class NoticeAndCheckApprovalController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeAndCheckApprovalController.class);


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private NoticeAndCheckApprovalService noticeAndCheckApprovalService;

    @Autowired
    private FlowProcessUtil flowProcessUtil;

    /**
     *  部署流程Key
     */
    private   static  final String processDefinitionKey = "NoticeAndCheckApproval";

    /**
     *  点击保存按钮，保存流程信息
     * @MethodName:saveProcessInfo
     * @author: yuzhong
     * @param:[vehicleApprovalEO,userId]
     * @return:void
     * date: 2018/8/27 15:01
     */
    @ApiOperation(value = "保存流程信息")
    @PostMapping ("/saveProcessInfo")
    public ResponseMessage<String> saveProcessInfo(@RequestBody NoticeAndCheckApprovalVO noticeAndCheckApprovalVO, String userId,String processInstanceId){
        //启动流程（为了把信息放入流程变量中）
        try{
            processInstanceId = noticeAndCheckApprovalService.startProcess(noticeAndCheckApprovalVO,userId,processDefinitionKey,processInstanceId);
            return Result.success(processInstanceId);
        }catch(Exception e){
            logger.error(e.getMessage());
            return Result.error("保存失败");
        }
    }

    /**
     *  流程完成审批
     * @MethodName:completeProcess
     * @author:yuzhong
     * @param:[processInstanceId,nowUserId]
     * @return:String
     * date: 2018年9月4日 14:37:45
     */
    @ApiOperation(value = "流程完成审批")
    @PostMapping ("/completeProcess")
    public ResponseMessage<String> completeProcess(@RequestBody NoticeAndCheckApprovalVO noticeAndCheckApprovalVO,String processInstanceId,String nowUserId){
        try {
            processInstanceId = noticeAndCheckApprovalService.startProcess(noticeAndCheckApprovalVO,nowUserId,processDefinitionKey,processInstanceId);

            Task task = noticeAndCheckApprovalService.completeProcess(noticeAndCheckApprovalVO,processInstanceId,nowUserId);

            processInstanceId = noticeAndCheckApprovalService.addAssignee(task,noticeAndCheckApprovalVO,processInstanceId,nowUserId);
            return Result.success(processInstanceId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return Result.error("审批失败");
        }
    }

    /**
     * 各个任务页面展示所需数据
     * @MethodName:getTaskInfo
     * @author:yuzhong
     * @param:[nowUserId,processInstanceId]
     * @return:Map
     * date: 2018年9月6日 19:07:04
     */
    @ApiOperation(value = "各个任务页面展示所需数据")
    @PostMapping ("/getTaskInfo")
    public ResponseMessage<Map<String,Object>> getTaskInfo(String nowUserId,String processInstanceId) throws Exception {
        Map<String,Object> map = noticeAndCheckApprovalService.getTaskInfo(nowUserId,processInstanceId);
        return Result.success(map);
    }


    /**
     * 无关联跳过任务节点
     * @MethodName:NoRelationship
     * @author:yuzhong
     * @param:[nowUserId,processInstanceId]
     * @return:String
     * date: 2018年9月17日 10:34:37
     */
    @ApiOperation(value = "无关联跳过任务节点")
    @PostMapping ("/NoRelationship")
    public ResponseMessage<String> NoRelationship(String nowUserId,String processInstanceId,Integer flag) {

        String message = noticeAndCheckApprovalService.NoRelationship(nowUserId,processInstanceId,flag);
        return Result.success(message);
    }

    /**
     * 驳回
     * @MethodName:reject
     * @author:yuzhong
     * @param:[processInstanceId]
     * @return:String
     * date: 2018年9月4日 14:37:19
     */
    @ApiOperation(value = "驳回")
    @PostMapping ("/reject")
    public ResponseMessage<String> reject(String processInstanceId,String nowUserId,String comment)throws Exception {
        String message = noticeAndCheckApprovalService.reject(processInstanceId,nowUserId,comment);
        //String message = flowProcessUtil.reject(processInstanceId,nowUserId,comment);
        return Result.success(message);
    }

    /**
     * 委托
     * @MethodName:entrust
     * @author: yuzhong
     * @param:[processInstanceId]
     * @return:String
     * date: 2018年9月5日 10:12:36
     */
    @ApiOperation(value = "委托")
    @PostMapping ("/entrust")
    public ResponseMessage<String> entrust(String processInstanceId,String nowUserId,String owner) {
        String message = flowProcessUtil.entrust(processInstanceId,nowUserId,owner);
        return Result.success(message);
    }
}
