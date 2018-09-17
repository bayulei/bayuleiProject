package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>SAR_BUSS_STAND_PLAN SarBussStandPlanEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBussStandPlanEO extends BaseEntity {

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDraftTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startFlowTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewModifyTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewMeetTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewSubmitTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishReviewTime;
    private String compilersUser;
    private String reviewLevel;
    private String compileUnit;
    private String replaceStandNum;
    private String makeRevisonType;
    private String standName;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>approvalDraftTime -> approval_draft_time</li>
     * <li>startFlowTime -> start_flow_time</li>
     * <li>reviewModifyTime -> review_modify_time</li>
     * <li>reviewMeetTime -> review_meet_time</li>
     * <li>reviewSubmitTime -> review_submit_time</li>
     * <li>finishReviewTime -> finish_review_time</li>
     * <li>compilersUser -> compilers_user</li>
     * <li>reviewLevel -> review_level</li>
     * <li>compileUnit -> compile_unit</li>
     * <li>replaceStandNum -> replace_stand_num</li>
     * <li>makeRevisonType -> make_revison_type</li>
     * <li>standName -> stand_name</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "approvalDraftTime": return "approval_draft_time";
            case "startFlowTime": return "start_flow_time";
            case "reviewModifyTime": return "review_modify_time";
            case "reviewMeetTime": return "review_meet_time";
            case "reviewSubmitTime": return "review_submit_time";
            case "finishReviewTime": return "finish_review_time";
            case "compilersUser": return "compilers_user";
            case "reviewLevel": return "review_level";
            case "compileUnit": return "compile_unit";
            case "replaceStandNum": return "replace_stand_num";
            case "makeRevisonType": return "make_revison_type";
            case "standName": return "stand_name";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>approval_draft_time -> approvalDraftTime</li>
     * <li>start_flow_time -> startFlowTime</li>
     * <li>review_modify_time -> reviewModifyTime</li>
     * <li>review_meet_time -> reviewMeetTime</li>
     * <li>review_submit_time -> reviewSubmitTime</li>
     * <li>finish_review_time -> finishReviewTime</li>
     * <li>compilers_user -> compilersUser</li>
     * <li>review_level -> reviewLevel</li>
     * <li>compile_unit -> compileUnit</li>
     * <li>replace_stand_num -> replaceStandNum</li>
     * <li>make_revison_type -> makeRevisonType</li>
     * <li>stand_name -> standName</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "approval_draft_time": return "approvalDraftTime";
            case "start_flow_time": return "startFlowTime";
            case "review_modify_time": return "reviewModifyTime";
            case "review_meet_time": return "reviewMeetTime";
            case "review_submit_time": return "reviewSubmitTime";
            case "finish_review_time": return "finishReviewTime";
            case "compilers_user": return "compilersUser";
            case "review_level": return "reviewLevel";
            case "compile_unit": return "compileUnit";
            case "replace_stand_num": return "replaceStandNum";
            case "make_revison_type": return "makeRevisonType";
            case "stand_name": return "standName";
            case "id": return "id";
            default: return null;
        }
    }
    
    /**  **/
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**  **/
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**  **/
    public Date getCreationTime() {
        return this.creationTime;
    }

    /**  **/
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**  **/
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**  **/
    public Date getApprovalDraftTime() {
        return this.approvalDraftTime;
    }

    /**  **/
    public void setApprovalDraftTime(Date approvalDraftTime) {
        this.approvalDraftTime = approvalDraftTime;
    }

    /**  **/
    public Date getStartFlowTime() {
        return this.startFlowTime;
    }

    /**  **/
    public void setStartFlowTime(Date startFlowTime) {
        this.startFlowTime = startFlowTime;
    }

    /**  **/
    public Date getReviewModifyTime() {
        return this.reviewModifyTime;
    }

    /**  **/
    public void setReviewModifyTime(Date reviewModifyTime) {
        this.reviewModifyTime = reviewModifyTime;
    }

    /**  **/
    public Date getReviewMeetTime() {
        return this.reviewMeetTime;
    }

    /**  **/
    public void setReviewMeetTime(Date reviewMeetTime) {
        this.reviewMeetTime = reviewMeetTime;
    }

    /**  **/
    public Date getReviewSubmitTime() {
        return this.reviewSubmitTime;
    }

    /**  **/
    public void setReviewSubmitTime(Date reviewSubmitTime) {
        this.reviewSubmitTime = reviewSubmitTime;
    }

    /**  **/
    public Date getFinishReviewTime() {
        return this.finishReviewTime;
    }

    /**  **/
    public void setFinishReviewTime(Date finishReviewTime) {
        this.finishReviewTime = finishReviewTime;
    }

    /**  **/
    public String getCompilersUser() {
        return this.compilersUser;
    }

    /**  **/
    public void setCompilersUser(String compilersUser) {
        this.compilersUser = compilersUser;
    }

    /**  **/
    public String getReviewLevel() {
        return this.reviewLevel;
    }

    /**  **/
    public void setReviewLevel(String reviewLevel) {
        this.reviewLevel = reviewLevel;
    }

    /**  **/
    public String getCompileUnit() {
        return this.compileUnit;
    }

    /**  **/
    public void setCompileUnit(String compileUnit) {
        this.compileUnit = compileUnit;
    }

    /**  **/
    public String getReplaceStandNum() {
        return this.replaceStandNum;
    }

    /**  **/
    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    /**  **/
    public String getMakeRevisonType() {
        return this.makeRevisonType;
    }

    /**  **/
    public void setMakeRevisonType(String makeRevisonType) {
        this.makeRevisonType = makeRevisonType;
    }

    /**  **/
    public String getStandName() {
        return this.standName;
    }

    /**  **/
    public void setStandName(String standName) {
        this.standName = standName;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

}
