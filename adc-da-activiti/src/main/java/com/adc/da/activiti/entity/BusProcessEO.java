package com.adc.da.activiti.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>BUS_PROCESS BusProcessEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-31 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BusProcessEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String processStatus;
    private String nowUserId;
    private String nowUesrName;
    private String lastUserId;
    private String lastUserName;
    private String createUserId;
    private String standardId;
    private String processType;
    private String processNumber;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>endTime -> end_time</li>
     * <li>createTime -> create_time</li>
     * <li>processStatus -> process_status</li>
     * <li>nowUserId -> now_user_id</li>
     * <li>nowUesrName -> now_uesr_name</li>
     * <li>lastUserId -> last_user_id</li>
     * <li>lastUserName -> last_user_name</li>
     * <li>createUserId -> create_user_id</li>
     * <li>standardId -> standard_id</li>
     * <li>processType -> process_type</li>
     * <li>processNumber -> process_number</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "endTime": return "end_time";
            case "createTime": return "create_time";
            case "processStatus": return "process_status";
            case "nowUserId": return "now_user_id";
            case "nowUesrName": return "now_uesr_name";
            case "lastUserId": return "last_user_id";
            case "lastUserName": return "last_user_name";
            case "createUserId": return "create_user_id";
            case "standardId": return "standard_id";
            case "processType": return "process_type";
            case "processNumber": return "process_number";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>end_time -> endTime</li>
     * <li>create_time -> createTime</li>
     * <li>process_status -> processStatus</li>
     * <li>now_user_id -> nowUserId</li>
     * <li>now_uesr_name -> nowUesrName</li>
     * <li>last_user_id -> lastUserId</li>
     * <li>last_user_name -> lastUserName</li>
     * <li>create_user_id -> createUserId</li>
     * <li>standard_id -> standardId</li>
     * <li>process_type -> processType</li>
     * <li>process_number -> processNumber</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "end_time": return "endTime";
            case "create_time": return "createTime";
            case "process_status": return "processStatus";
            case "now_user_id": return "nowUserId";
            case "now_uesr_name": return "nowUesrName";
            case "last_user_id": return "lastUserId";
            case "last_user_name": return "lastUserName";
            case "create_user_id": return "createUserId";
            case "standard_id": return "standardId";
            case "process_type": return "processType";
            case "process_number": return "processNumber";
            case "id": return "id";
            default: return null;
        }
    }
    
    /**  **/
    public Date getEndTime() {
        return this.endTime;
    }

    /**  **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**  **/
    public Date getCreateTime() {
        return this.createTime;
    }

    /**  **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**  **/
    public String getProcessStatus() {
        return this.processStatus;
    }

    /**  **/
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    /**  **/
    public String getNowUserId() {
        return this.nowUserId;
    }

    /**  **/
    public void setNowUserId(String nowUserId) {
        this.nowUserId = nowUserId;
    }

    /**  **/
    public String getNowUesrName() {
        return this.nowUesrName;
    }

    /**  **/
    public void setNowUesrName(String nowUesrName) {
        this.nowUesrName = nowUesrName;
    }

    /**  **/
    public String getLastUserId() {
        return this.lastUserId;
    }

    /**  **/
    public void setLastUserId(String lastUserId) {
        this.lastUserId = lastUserId;
    }

    /**  **/
    public String getLastUserName() {
        return this.lastUserName;
    }

    /**  **/
    public void setLastUserName(String lastUserName) {
        this.lastUserName = lastUserName;
    }

    /**  **/
    public String getCreateUserId() {
        return this.createUserId;
    }

    /**  **/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**  **/
    public String getStandardId() {
        return this.standardId;
    }

    /**  **/
    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    /**  **/
    public String getProcessType() {
        return this.processType;
    }

    /**  **/
    public void setProcessType(String processType) {
        this.processType = processType;
    }

    /**  **/
    public String getProcessNumber() {
        return this.processNumber;
    }

    /**  **/
    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
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
