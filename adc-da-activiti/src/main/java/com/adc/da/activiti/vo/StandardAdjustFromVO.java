package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.Date;

public class StandardAdjustFromVO implements Serializable{

    private static final long serialVersionUID = -8069308169973973955L;

    /**
     *  标准编号/名称
     */
    private  String standardNumber  ;

    /**
     *  责任人
     */
    private  String userId ;

    /**
     *  申请类别
     */
    private  String applyType ;

    /**
     *  原因分析说明
     */
    private  String reason ;


    /**
     *  原评审稿完成时间
     */
    private Date origExamineTime;

    /**
     *  原报批稿完成时间
     */
    private Date origApproveTime;

    /**
     *  新评审稿完成时间
     */
    private Date newExamineTime;

    /**
     *  新评审稿完成时间
     */
    private Date newApproveTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getOrigExamineTime() {
        return origExamineTime;
    }

    public void setOrigExamineTime(Date origExamineTime) {
        this.origExamineTime = origExamineTime;
    }

    public Date getOrigApproveTime() {
        return origApproveTime;
    }

    public void setOrigApproveTime(Date origApproveTime) {
        this.origApproveTime = origApproveTime;
    }

    public Date getNewExamineTime() {
        return newExamineTime;
    }

    public void setNewExamineTime(Date newExamineTime) {
        this.newExamineTime = newExamineTime;
    }

    public Date getNewApproveTime() {
        return newApproveTime;
    }

    public void setNewApproveTime(Date newApproveTime) {
        this.newApproveTime = newApproveTime;
    }
}
