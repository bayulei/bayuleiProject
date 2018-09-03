package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>UT_CAR_ENVIRON UtCarEnvironEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UtCarEnvironEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String creationUser;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date openDate;
    private String engineCode;
    private String arcticModel;
    private String environCode;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>openDate -> open_date</li>
     * <li>engineCode -> engine_code</li>
     * <li>arcticModel -> arctic_model</li>
     * <li>environCode -> environ_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "openDate": return "open_date";
            case "engineCode": return "engine_code";
            case "arcticModel": return "arctic_model";
            case "environCode": return "environ_code";
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
     * <li>open_date -> openDate</li>
     * <li>engine_code -> engineCode</li>
     * <li>arctic_model -> arcticModel</li>
     * <li>environ_code -> environCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "open_date": return "openDate";
            case "engine_code": return "engineCode";
            case "arctic_model": return "arcticModel";
            case "environ_code": return "environCode";
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
    public Date getOpenDate() {
        return this.openDate;
    }

    /**  **/
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**  **/
    public String getEngineCode() {
        return this.engineCode;
    }

    /**  **/
    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
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
    public String getEnvironCode() {
        return this.environCode;
    }

    /**  **/
    public void setEnvironCode(String environCode) {
        this.environCode = environCode;
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
