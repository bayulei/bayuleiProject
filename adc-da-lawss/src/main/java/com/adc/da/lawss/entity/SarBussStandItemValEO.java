package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_BUSS_STAND_ITEM_VAL SarBussStandItemValEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBussStandItemValEO extends BaseEntity {

    private Integer validFlag;
    private String propertyValue;
    private String propertyType;
    private String itemId;
    private String id;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>validFlag -> valid_flag</li>
     * <li>propertyValue -> property_value</li>
     * <li>propertyType -> property_type</li>
     * <li>itemId -> item_id</li>
     * <li>id -> id</li>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "validFlag": return "valid_flag";
            case "propertyValue": return "property_value";
            case "propertyType": return "property_type";
            case "itemId": return "item_id";
            case "id": return "id";
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>valid_flag -> validFlag</li>
     * <li>property_value -> propertyValue</li>
     * <li>property_type -> propertyType</li>
     * <li>item_id -> itemId</li>
     * <li>id -> id</li>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "valid_flag": return "validFlag";
            case "property_value": return "propertyValue";
            case "property_type": return "propertyType";
            case "item_id": return "itemId";
            case "id": return "id";
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            default: return null;
        }
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
    public String getPropertyValue() {
        return this.propertyValue;
    }

    /**  **/
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
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
    public String getItemId() {
        return this.itemId;
    }

    /**  **/
    public void setItemId(String itemId) {
        this.itemId = itemId;
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

}
