package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>TS_FEEDBACK_INFO FeedbackInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class FeedbackInfoEO extends BaseEntity {

    private String id;
    private String userId;
    private String feedbackInfo;
    private Integer readFlag;
    private Integer validFlag;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>userId -> user_id</li>
     * <li>feedbackInfo -> feedback_info</li>
     * <li>readFlag -> read_flag</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationTime -> creation_time</li>
     * <li>modifyTime -> modify_time</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "userId": return "user_id";
            case "feedbackInfo": return "feedback_info";
            case "readFlag": return "read_flag";
            case "validFlag": return "valid_flag";
            case "creationTime": return "creation_time";
            case "modifyTime": return "modify_time";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>user_id -> userId</li>
     * <li>feedback_info -> feedbackInfo</li>
     * <li>read_flag -> readFlag</li>
     * <li>valid_flag -> validFlag</li>
     * <li>creation_time -> creationTime</li>
     * <li>modify_time -> modifyTime</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "user_id": return "userId";
            case "feedback_info": return "feedbackInfo";
            case "read_flag": return "readFlag";
            case "valid_flag": return "validFlag";
            case "creation_time": return "creationTime";
            case "modify_time": return "modifyTime";
            default: return null;
        }
    }
    
    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getUserId() {
        return this.userId;
    }

    /**  **/
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**  **/
    public String getFeedbackInfo() {
        return this.feedbackInfo;
    }

    /**  **/
    public void setFeedbackInfo(String feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

    /**  **/
    public Integer getReadFlag() {
        return this.readFlag;
    }

    /**  **/
    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
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
    public Date getCreationTime() {
        return this.creationTime;
    }

    /**  **/
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**  **/
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**  **/
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
