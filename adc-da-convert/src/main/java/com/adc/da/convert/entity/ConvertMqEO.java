package com.adc.da.convert.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>CONVERT_MQ ConvertMqEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class ConvertMqEO extends BaseEntity {

    private String id;
    private String mqCode;
    private Integer mqState;
    private Integer delFlag;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String userId;
    private String filePath;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>mqCode -> mq_code</li>
     * <li>mqState -> mq_state</li>
     * <li>delFlag -> del_flag</li>
     * <li>insertTime -> insert_time</li>
     * <li>updateTime -> update_time</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "mqCode": return "mq_code";
            case "mqState": return "mq_state";
            case "delFlag": return "del_flag";
            case "insertTime": return "insert_time";
            case "updateTime": return "update_time";
            case "userId": return "user_id";
            case "filePath": return "file_path";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>mq_code -> mqCode</li>
     * <li>mq_state -> mqState</li>
     * <li>del_flag -> delFlag</li>
     * <li>insert_time -> insertTime</li>
     * <li>update_time -> updateTime</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "mq_code": return "mqCode";
            case "mq_state": return "mqState";
            case "del_flag": return "delFlag";
            case "insert_time": return "insertTime";
            case "update_time": return "updateTime";
            case "user_id": return "userId";
            case "file_path": return "filePath";
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
    public String getMqCode() {
        return this.mqCode;
    }

    /**  **/
    public void setMqCode(String mqCode) {
        this.mqCode = mqCode;
    }

    /**  **/
    public Integer getMqState() {
        return this.mqState;
    }

    /**  **/
    public void setMqState(Integer mqState) {
        this.mqState = mqState;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public Date getInsertTime() {
        return this.insertTime;
    }

    /**  **/
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**  **/
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
