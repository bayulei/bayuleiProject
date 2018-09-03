package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_STAND_ITEM_VAL SarStandItemValEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarStandItemValEO extends BaseEntity {

    private String propertyType;
    private String standId;
    private String id;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String propertyVal;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>propertyType -> property_type</li>
     * <li>standId -> stand_id</li>
     * <li>id -> id</li>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>propertyVal -> property_val</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "propertyType": return "property_type";
            case "standId": return "stand_id";
            case "id": return "id";
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "propertyVal": return "property_val";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>property_type -> propertyType</li>
     * <li>stand_id -> standId</li>
     * <li>id -> id</li>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>property_val -> propertyVal</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "property_type": return "propertyType";
            case "stand_id": return "standId";
            case "id": return "id";
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "property_val": return "propertyVal";
            default: return null;
        }
    }
    
    /**  **/
    public String getPropertyType() {
        return this.propertyType;
    }

    /**  **/
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    /**  **/
    public String getStandId() {
        return this.standId;
    }

    /**  **/
    public void setStandId(String standId) {
        this.standId = standId;
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
    public String getPropertyVal() {
        return this.propertyVal;
    }

    /**  **/
    public void setPropertyVal(String propertyVal) {
        this.propertyVal = propertyVal;
    }

}
