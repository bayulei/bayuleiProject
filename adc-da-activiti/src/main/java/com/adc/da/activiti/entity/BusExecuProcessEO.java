package com.adc.da.activiti.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>BUS_EXECU_PROCESS BusExecuProcessEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-04 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BusExecuProcessEO extends BaseEntity {

    private String id;
    private String procInstId;
    private String processId;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>procInstId -> proc_inst_id</li>
     * <li>processId -> process_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "procInstId": return "proc_inst_id";
            case "processId": return "process_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>proc_inst_id -> procInstId</li>
     * <li>process_id -> processId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "proc_inst_id": return "procInstId";
            case "process_id": return "processId";
            default: return null;
        }
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
    public String getProcInstId() {
        return this.procInstId;
    }

    /**  **/
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**  **/
    public String getProcessId() {
        return this.processId;
    }

    /**  **/
    public void setProcessId(String processId) {
        this.processId = processId;
    }

}
