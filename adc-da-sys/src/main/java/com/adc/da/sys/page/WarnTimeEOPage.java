package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>TS_WARN_TIME WarnTimeEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class WarnTimeEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String warnType;
    private String warnTypeOperator = "=";

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

    public String getWarnType() {
        return this.warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnTypeOperator() {
        return this.warnTypeOperator;
    }

    public void setWarnTypeOperator(String warnTypeOperator) {
        this.warnTypeOperator = warnTypeOperator;
    }

}
