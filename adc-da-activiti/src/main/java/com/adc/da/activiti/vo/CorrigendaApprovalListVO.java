package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.List;

public class CorrigendaApprovalListVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
