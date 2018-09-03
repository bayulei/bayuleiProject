package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>TS_ROLE_MENU RoleMenuEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class RoleMenuEO extends BaseEntity {

    private String menuId;
    private String roleId;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>menuId -> menu_id</li>
     * <li>roleId -> role_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "menuId": return "menu_id";
            case "roleId": return "role_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>menu_id -> menuId</li>
     * <li>role_id -> roleId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "menu_id": return "menuId";
            case "role_id": return "roleId";
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
    public String getRoleId() {
        return this.roleId;
    }

    /**  **/
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
