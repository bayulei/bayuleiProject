package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *  流程列表信息展示
 * @ClassName:ProcessInformationVO
 * @author: DuYunbao
 * date: 2018/8/27 16:21
 */
public class ProcessInformationVO implements Serializable{
    private static final long serialVersionUID = 1253246125657552788L;

    /**
     *  流程编号
     */
    private String processNumber ;

    /**
     *  流程类型
     */
    private String processType ;

    /**
     *  任务处理人
     */
    private String taskProcessor ;

    /**
     *  任务步骤
     */
    private String taskName ;

    /**
     *  上一处理人
     */
    private String lastTaskProcessor ;

    /**
     *  到达时间
     */
    private Date receiveTime ;

    /**
     *  完成时间
     */
    private Date completeTime ;

    /**
     *  状态
     */
    private Integer status ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getTaskProcessor() {
        return taskProcessor;
    }

    public void setTaskProcessor(String taskProcessor) {
        this.taskProcessor = taskProcessor;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getLastTaskProcessor() {
        return lastTaskProcessor;
    }

    public void setLastTaskProcessor(String lastTaskProcessor) {
        this.lastTaskProcessor = lastTaskProcessor;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
