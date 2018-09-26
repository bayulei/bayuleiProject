package com.adc.da.activiti.vo;

import java.io.Serializable;

public class TakeAdviceEngineersOpinionVO implements Serializable{

    private static final long serialVersionUID = -6095689610293830473L;

    private String departmentId ;

    private  String roomId;

    private  String engineerId;

    private String clauseNumber;

    private String termsAndContents;

    private String productComplianceExplain;

    private String riskAnalysis ;

    private  String feedBackSuggest;

    private  String reasonExplain;


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getFeedBackSuggest() {
        return feedBackSuggest;
    }

    public void setFeedBackSuggest(String feedBackSuggest) {
        this.feedBackSuggest = feedBackSuggest;
    }

    public String getReasonExplain() {
        return reasonExplain;
    }

    public void setReasonExplain(String reasonExplain) {
        this.reasonExplain = reasonExplain;
    }

    public String getClauseNumber() {
        return clauseNumber;
    }

    public void setClauseNumber(String clauseNumber) {
        this.clauseNumber = clauseNumber;
    }

    public String getTermsAndContents() {
        return termsAndContents;
    }

    public void setTermsAndContents(String termsAndContents) {
        this.termsAndContents = termsAndContents;
    }

    public String getProductComplianceExplain() {
        return productComplianceExplain;
    }

    public void setProductComplianceExplain(String productComplianceExplain) {
        this.productComplianceExplain = productComplianceExplain;
    }

    public String getRiskAnalysis() {
        return riskAnalysis;
    }

    public void setRiskAnalysis(String riskAnalysis) {
        this.riskAnalysis = riskAnalysis;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
