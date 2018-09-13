package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.List;

public class CorrigendaApprovalVO extends ApprovalProcessVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

    /**
     *  附件
     */
    private String fileUrl;

    /**
     *  标准编号/名称
     */
    private String standardNum;

    /**
     *  申请类别
     */
    private String approvalType;

    /**
     *  修订（勘误）内容描述
     */
    private String contentDescription;

    /**
     *  原因分析说明
     */
    private String causeAnalysis;


    private List<CorrigendaApprovalVO> corrigendaApprovalVOList;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getStandardNum() {
        return standardNum;
    }

    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getCauseAnalysis() {
        return causeAnalysis;
    }

    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }

    public List<CorrigendaApprovalVO> getCorrigendaApprovalVOList() {
        return corrigendaApprovalVOList;
    }

    public void setCorrigendaApprovalVOList(List<CorrigendaApprovalVO> corrigendaApprovalVOList) {
        this.corrigendaApprovalVOList = corrigendaApprovalVOList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
