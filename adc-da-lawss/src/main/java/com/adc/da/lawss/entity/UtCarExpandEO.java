package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>UT_CAR_EXPAND UtCarExpandEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UtCarExpandEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String creationUser;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date effectiveDate;
    private String configId;
    private String arcticModel;
    private String expandCode;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>effectiveDate -> effective_date</li>
     * <li>configId -> config_id</li>
     * <li>arcticModel -> arctic_model</li>
     * <li>expandCode -> expand_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "effectiveDate": return "effective_date";
            case "configId": return "config_id";
            case "arcticModel": return "arctic_model";
            case "expandCode": return "expand_code";
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
     * <li>effective_date -> effectiveDate</li>
     * <li>config_id -> configId</li>
     * <li>arctic_model -> arcticModel</li>
     * <li>expand_code -> expandCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "effective_date": return "effectiveDate";
            case "config_id": return "configId";
            case "arctic_model": return "arcticModel";
            case "expand_code": return "expandCode";
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
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    /**  **/
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**  **/
    public String getConfigId() {
        return this.configId;
    }

    /**  **/
    public void setConfigId(String configId) {
        this.configId = configId;
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
    public String getExpandCode() {
        return this.expandCode;
    }

    /**  **/
    public void setExpandCode(String expandCode) {
        this.expandCode = expandCode;
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
