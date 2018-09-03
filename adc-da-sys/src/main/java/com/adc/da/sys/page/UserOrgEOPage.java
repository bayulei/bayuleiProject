package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>TS_USER_ORG UserOrgEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserOrgEOPage extends BasePage {

    private String orgId;
    private String orgIdOperator = "=";
    private String userId;
    private String userIdOperator = "=";

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgIdOperator() {
        return this.orgIdOperator;
    }

    public void setOrgIdOperator(String orgIdOperator) {
        this.orgIdOperator = orgIdOperator;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdOperator() {
        return this.userIdOperator;
    }

    public void setUserIdOperator(String userIdOperator) {
        this.userIdOperator = userIdOperator;
    }

}
