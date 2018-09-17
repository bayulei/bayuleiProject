package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;

public class StandPlanExportDto {

    @Excel(name = "标准名称")
    private String standName;

    @Excel(name = "制修订类别")
    private String makeRevisonType;

    @Excel(name = "代替标准代号")
    private String replaceStandNum;

    @Excel(name = "编制部门")
    private String compileUnit;

    @Excel(name = "评审级别")
    private String reviewLevel;

    @Excel(name = "主要编制者")
    private String compilersUser;

    @Excel(name = "完成部门评审提交质量部时间")
    private String finishReviewTime;

    @Excel(name = "评审稿提交日期")
    private String reviewSubmitTime;

    @Excel(name = "评审会日期")
    private String reviewMeetTime;

    @Excel(name = "评审后修改稿提交日期")
    private String reviewModifyTime;

    @Excel(name = "开始流程日期")
    private String startFlowTime;

    @Excel(name = "报批稿完成")
    private String approvalDraftTime;

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getMakeRevisonType() {
        return makeRevisonType;
    }

    public void setMakeRevisonType(String makeRevisonType) {
        this.makeRevisonType = makeRevisonType;
    }

    public String getReplaceStandNum() {
        return replaceStandNum;
    }

    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    public String getCompileUnit() {
        return compileUnit;
    }

    public void setCompileUnit(String compileUnit) {
        this.compileUnit = compileUnit;
    }

    public String getReviewLevel() {
        return reviewLevel;
    }

    public void setReviewLevel(String reviewLevel) {
        this.reviewLevel = reviewLevel;
    }

    public String getCompilersUser() {
        return compilersUser;
    }

    public void setCompilersUser(String compilersUser) {
        this.compilersUser = compilersUser;
    }

    public String getFinishReviewTime() {
        return finishReviewTime;
    }

    public void setFinishReviewTime(String finishReviewTime) {
        this.finishReviewTime = finishReviewTime;
    }

    public String getReviewSubmitTime() {
        return reviewSubmitTime;
    }

    public void setReviewSubmitTime(String reviewSubmitTime) {
        this.reviewSubmitTime = reviewSubmitTime;
    }

    public String getReviewMeetTime() {
        return reviewMeetTime;
    }

    public void setReviewMeetTime(String reviewMeetTime) {
        this.reviewMeetTime = reviewMeetTime;
    }

    public String getReviewModifyTime() {
        return reviewModifyTime;
    }

    public void setReviewModifyTime(String reviewModifyTime) {
        this.reviewModifyTime = reviewModifyTime;
    }

    public String getStartFlowTime() {
        return startFlowTime;
    }

    public void setStartFlowTime(String startFlowTime) {
        this.startFlowTime = startFlowTime;
    }

    public String getApprovalDraftTime() {
        return approvalDraftTime;
    }

    public void setApprovalDraftTime(String approvalDraftTime) {
        this.approvalDraftTime = approvalDraftTime;
    }
}
