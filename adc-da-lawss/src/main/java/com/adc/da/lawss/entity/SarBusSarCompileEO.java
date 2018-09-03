package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.sql.Clob;
import java.util.Date;

/**
 * <b>功能：</b>SAR_BUS_SAR_COMPILE SarBusSarCompileEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBusSarCompileEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String creationUser;
    private String dutyUnit;
    private String replaceStandNum;
    private String remarks;
    private String putYear;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date issueTime;
    private String standName;
    private String standYear;
    private String standNumber;
    private String standCode;
    private String classifyCode;
    private String busStandSubclass;
    private String busStandClassify;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>dutyUnit -> duty_unit</li>
     * <li>replaceStandNum -> replace_stand_num</li>
     * <li>remarks -> remarks</li>
     * <li>putYear -> put_year</li>
     * <li>putTime -> put_time</li>
     * <li>issueTime -> issue_time</li>
     * <li>standName -> stand_name</li>
     * <li>standYear -> stand_year</li>
     * <li>standNumber -> stand_number</li>
     * <li>standCode -> stand_code</li>
     * <li>classifyCode -> classify_code</li>
     * <li>busStandSubclass -> bus_stand_subclass</li>
     * <li>busStandClassify -> bus_stand_classify</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "dutyUnit": return "duty_unit";
            case "replaceStandNum": return "replace_stand_num";
            case "remarks": return "remarks";
            case "putYear": return "put_year";
            case "putTime": return "put_time";
            case "issueTime": return "issue_time";
            case "standName": return "stand_name";
            case "standYear": return "stand_year";
            case "standNumber": return "stand_number";
            case "standCode": return "stand_code";
            case "classifyCode": return "classify_code";
            case "busStandSubclass": return "bus_stand_subclass";
            case "busStandClassify": return "bus_stand_classify";
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
     * <li>creation_user -> creationUser</li>
     * <li>duty_unit -> dutyUnit</li>
     * <li>replace_stand_num -> replaceStandNum</li>
     * <li>remarks -> remarks</li>
     * <li>put_year -> putYear</li>
     * <li>put_time -> putTime</li>
     * <li>issue_time -> issueTime</li>
     * <li>stand_name -> standName</li>
     * <li>stand_year -> standYear</li>
     * <li>stand_number -> standNumber</li>
     * <li>stand_code -> standCode</li>
     * <li>classify_code -> classifyCode</li>
     * <li>bus_stand_subclass -> busStandSubclass</li>
     * <li>bus_stand_classify -> busStandClassify</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "duty_unit": return "dutyUnit";
            case "replace_stand_num": return "replaceStandNum";
            case "remarks": return "remarks";
            case "put_year": return "putYear";
            case "put_time": return "putTime";
            case "issue_time": return "issueTime";
            case "stand_name": return "standName";
            case "stand_year": return "standYear";
            case "stand_number": return "standNumber";
            case "stand_code": return "standCode";
            case "classify_code": return "classifyCode";
            case "bus_stand_subclass": return "busStandSubclass";
            case "bus_stand_classify": return "busStandClassify";
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
    public String getCreationUser() {
        return this.creationUser;
    }

    /**  **/
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    /**  **/
    public String getDutyUnit() {
        return this.dutyUnit;
    }

    /**  **/
    public void setDutyUnit(String dutyUnit) {
        this.dutyUnit = dutyUnit;
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
    public String getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**  **/
    public String getPutYear() {
        return this.putYear;
    }

    /**  **/
    public void setPutYear(String putYear) {
        this.putYear = putYear;
    }

    /**  **/
    public Date getPutTime() {
        return this.putTime;
    }

    /**  **/
    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    /**  **/
    public Date getIssueTime() {
        return this.issueTime;
    }

    /**  **/
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
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
    public String getStandYear() {
        return this.standYear;
    }

    /**  **/
    public void setStandYear(String standYear) {
        this.standYear = standYear;
    }

    /**  **/
    public String getStandNumber() {
        return this.standNumber;
    }

    /**  **/
    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
    }

    /**  **/
    public String getStandCode() {
        return this.standCode;
    }

    /**  **/
    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    /**  **/
    public String getClassifyCode() {
        return this.classifyCode;
    }

    /**  **/
    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    /**  **/
    public String getBusStandSubclass() {
        return this.busStandSubclass;
    }

    /**  **/
    public void setBusStandSubclass(String busStandSubclass) {
        this.busStandSubclass = busStandSubclass;
    }

    /**  **/
    public String getBusStandClassify() {
        return this.busStandClassify;
    }

    /**  **/
    public void setBusStandClassify(String busStandClassify) {
        this.busStandClassify = busStandClassify;
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
