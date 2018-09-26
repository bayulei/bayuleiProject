package com.adc.da.activiti.vo;

import com.adc.da.activiti.entity.BusNoticecheckProcessEO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NoticeAndCheckApprovalVO extends ApprovalProcessVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

    /**
     *  附件
     */
    private List<String> fileIdList;

    /**
     *  分发的接受人（法规接口人）
     */
    private List<String> assignerUserList;

    /**
     *  工程师处理截止时间
     */
    private Date endTime;

    /**
     *  涉及项目
     */
    private String project;

    /**
     *  工作要求
     */
    private String workRequirements;

    /**
     *  审批意见
     */
    private String approvalOpinion;

    /**
     *  决定流程的走向
     */
    private Integer flag;

    /**
     *  流程业务表集合
     */
    private List<BusNoticecheckProcessEO> busNoticecheckProcessEOList;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public List<String> getFileIdList() {
        return fileIdList;
    }

    public void setFileIdList(List<String> fileIdList) {
        this.fileIdList = fileIdList;
    }

    public List<String> getAssignerUserList() {
        return assignerUserList;
    }

    public void setAssignerUserList(List<String> assignerUserList) {
        this.assignerUserList = assignerUserList;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getWorkRequirements() {
        return workRequirements;
    }

    public void setWorkRequirements(String workRequirements) {
        this.workRequirements = workRequirements;
    }

    public List<BusNoticecheckProcessEO> getBusNoticecheckProcessEOList() {

        return busNoticecheckProcessEOList;
    }

    public void setBusNoticecheckProcessEOList(List<BusNoticecheckProcessEO> busNoticecheckProcessEOList) {
        this.busNoticecheckProcessEOList = busNoticecheckProcessEOList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
