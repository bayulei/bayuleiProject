package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>SAR_BUSS_STAND_MENU SarBussStandMenuEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBussStandMenuEO extends BaseEntity {

    private String menuId;
    private String bussStandId;
    private String id;
    private Integer validFlag;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>menuId -> menu_id</li>
     * <li>bussStandId -> buss_stand_id</li>
     * <li>id -> id</li>
     * <li>validFlag -> valid_flag</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "menuId": return "menu_id";
            case "bussStandId": return "buss_stand_id";
            case "id": return "id";
            case "validFlag": return "valid_flag";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>menu_id -> menuId</li>
     * <li>buss_stand_id -> bussStandId</li>
     * <li>id -> id</li>
     * <li>valid_flag -> validFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "menu_id": return "menuId";
            case "buss_stand_id": return "bussStandId";
            case "id": return "id";
            case "valid_flag": return "validFlag";
            default: return null;
        }
    }
    
    /**  **/
    public String getMenuId() {
        return this.menuId;
    }

    /**  **/
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**  **/
    public String getBussStandId() {
        return this.bussStandId;
    }

    /**  **/
    public void setBussStandId(String bussStandId) {
        this.bussStandId = bussStandId;
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
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

}
