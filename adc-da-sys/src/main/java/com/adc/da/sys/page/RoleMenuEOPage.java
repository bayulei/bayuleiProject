package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>TS_ROLE_MENU RoleMenuEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class RoleMenuEOPage extends BasePage {

    private String menuId;
    private String menuIdOperator = "=";
    private String roleId;
    private String roleIdOperator = "=";

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuIdOperator() {
        return this.menuIdOperator;
    }

    public void setMenuIdOperator(String menuIdOperator) {
        this.menuIdOperator = menuIdOperator;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleIdOperator() {
        return this.roleIdOperator;
    }

    public void setRoleIdOperator(String roleIdOperator) {
        this.roleIdOperator = roleIdOperator;
    }

}
