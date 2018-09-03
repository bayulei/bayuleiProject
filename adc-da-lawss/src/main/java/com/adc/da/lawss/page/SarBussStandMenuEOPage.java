package com.adc.da.lawss.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>SAR_BUSS_STAND_MENU SarBussStandMenuEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBussStandMenuEOPage extends BasePage {

    private String menuId;
    private String menuIdOperator = "=";
    private String bussStandId;
    private String bussStandIdOperator = "=";
    private String id;
    private String idOperator = "=";
    private String validFlag;
    private String validFlagOperator = "=";

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

    public String getBussStandId() {
        return this.bussStandId;
    }

    public void setBussStandId(String bussStandId) {
        this.bussStandId = bussStandId;
    }

    public String getBussStandIdOperator() {
        return this.bussStandIdOperator;
    }

    public void setBussStandIdOperator(String bussStandIdOperator) {
        this.bussStandIdOperator = bussStandIdOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getValidFlag() {
        return this.validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlagOperator() {
        return this.validFlagOperator;
    }

    public void setValidFlagOperator(String validFlagOperator) {
        this.validFlagOperator = validFlagOperator;
    }

}
