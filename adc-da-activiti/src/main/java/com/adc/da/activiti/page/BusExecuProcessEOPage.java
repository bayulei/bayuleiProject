package com.adc.da.activiti.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>BUS_EXECU_PROCESS BusExecuProcessEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-04 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BusExecuProcessEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String procInstId;
    private String procInstIdOperator = "=";
    private String processId;
    private String processIdOperator = "=";

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

    public String getProcInstId() {
        return this.procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcInstIdOperator() {
        return this.procInstIdOperator;
    }

    public void setProcInstIdOperator(String procInstIdOperator) {
        this.procInstIdOperator = procInstIdOperator;
    }

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessIdOperator() {
        return this.processIdOperator;
    }

    public void setProcessIdOperator(String processIdOperator) {
        this.processIdOperator = processIdOperator;
    }

}
