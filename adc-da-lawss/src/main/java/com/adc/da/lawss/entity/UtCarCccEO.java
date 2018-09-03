package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>UT_CAR_CCC UtCarCccEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UtCarCccEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String creationUser;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date effectiveDate;
    private String arcticModel;
    private String ccieNum;
    private String ccieCode;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyDate -> modify_date</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>effectiveDate -> effective_date</li>
     * <li>arcticModel -> arctic_model</li>
     * <li>ccieNum -> ccie_num</li>
     * <li>ccieCode -> ccie_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyDate": return "modify_date";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "effectiveDate": return "effective_date";
            case "arcticModel": return "arctic_model";
            case "ccieNum": return "ccie_num";
            case "ccieCode": return "ccie_code";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_date -> modifyDate</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>creation_user -> creationUser</li>
     * <li>effective_date -> effectiveDate</li>
     * <li>arctic_model -> arcticModel</li>
     * <li>ccie_num -> ccieNum</li>
     * <li>ccie_code -> ccieCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_date": return "modifyDate";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "effective_date": return "effectiveDate";
            case "arctic_model": return "arcticModel";
            case "ccie_num": return "ccieNum";
            case "ccie_code": return "ccieCode";
            case "id": return "id";
            default: return null;
        }
    }
    
    /**  **/
    public Date getModifyDate() {
        return this.modifyDate;
    }

    /**  **/
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
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
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    /**  **/
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**  **/
    public String getArcticModel() {
        return this.arcticModel;
    }

    /**  **/
    public void setArcticModel(String arcticModel) {
        this.arcticModel = arcticModel;
    }

    /**  **/
    public String getCcieNum() {
        return this.ccieNum;
    }

    /**  **/
    public void setCcieNum(String ccieNum) {
        this.ccieNum = ccieNum;
    }

    /**  **/
    public String getCcieCode() {
        return this.ccieCode;
    }

    /**  **/
    public void setCcieCode(String ccieCode) {
        this.ccieCode = ccieCode;
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
