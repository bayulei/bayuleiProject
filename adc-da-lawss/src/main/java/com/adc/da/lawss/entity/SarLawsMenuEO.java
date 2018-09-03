package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>SAR_LAWS_MENU SarLawsMenuEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarLawsMenuEO extends BaseEntity {

    private Integer validFlag;
    private String menuId;
    private String lawsId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>validFlag -> valid_flag</li>
     * <li>menuId -> menu_id</li>
     * <li>lawsId -> laws_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "validFlag": return "valid_flag";
            case "menuId": return "menu_id";
            case "lawsId": return "laws_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>valid_flag -> validFlag</li>
     * <li>menu_id -> menuId</li>
     * <li>laws_id -> lawsId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "valid_flag": return "validFlag";
            case "menu_id": return "menuId";
            case "laws_id": return "lawsId";
            case "id": return "id";
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
    public String getMenuId() {
        return this.menuId;
    }

    /**  **/
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**  **/
    public String getLawsId() {
        return this.lawsId;
    }

    /**  **/
    public void setLawsId(String lawsId) {
        this.lawsId = lawsId;
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
