package com.adc.da.convert.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>OT_CONVERT_MQ OtConvertMqEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-25 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class OtConvertMqEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String attId;
    private String filePath;
    private String userId;
    private Integer mqState;
    private String mqCode;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>attId -> att_id</li>
     * <li>filePath -> file_path</li>
     * <li>userId -> user_id</li>
     * <li>mqState -> mq_state</li>
     * <li>mqCode -> mq_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "attId": return "att_id";
            case "filePath": return "file_path";
            case "userId": return "user_id";
            case "mqState": return "mq_state";
            case "mqCode": return "mq_code";
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
     * <li>att_id -> attId</li>
     * <li>file_path -> filePath</li>
     * <li>user_id -> userId</li>
     * <li>mq_state -> mqState</li>
     * <li>mq_code -> mqCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "att_id": return "attId";
            case "file_path": return "filePath";
            case "user_id": return "userId";
            case "mq_state": return "mqState";
            case "mq_code": return "mqCode";
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
    public String getAttId() {
        return this.attId;
    }

    /**  **/
    public void setAttId(String attId) {
        this.attId = attId;
    }

    /**  **/
    public String getFilePath() {
        return this.filePath;
    }

    /**  **/
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
    public Integer getMqState() {
        return this.mqState;
    }

    /**  **/
    public void setMqState(Integer mqState) {
        this.mqState = mqState;
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
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

}
