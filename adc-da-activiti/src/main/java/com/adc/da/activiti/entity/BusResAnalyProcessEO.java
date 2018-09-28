package com.adc.da.activiti.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>BUS_RES_ANALY_PROCESS BusResAnalyProcessEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BusResAnalyProcessEO extends BaseEntity {

    private Integer isagree;
    private String resFlag;
    private String procInstId;
    private String parentId;
    private String reasonExplain;
    private String proveMaterial;
    private Integer resResult;
    private String engineer;
    private String projectManager;
    private String deptName;
    private String resName;
    private String resNum;
    private String resId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>isagree -> isagree</li>
     * <li>resFlag -> res_flag</li>
     * <li>procInstId -> proc_inst_id</li>
     * <li>parentId -> parent_id</li>
     * <li>reasonExplain -> reason_explain</li>
     * <li>proveMaterial -> prove_material</li>
     * <li>resResult -> res_result</li>
     * <li>engineer -> engineer</li>
     * <li>projectManager -> project_manager</li>
     * <li>deptName -> dept_name</li>
     * <li>resName -> res_name</li>
     * <li>resNum -> res_num</li>
     * <li>resId -> res_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "isagree": return "isagree";
            case "resFlag": return "res_flag";
            case "procInstId": return "proc_inst_id";
            case "parentId": return "parent_id";
            case "reasonExplain": return "reason_explain";
            case "proveMaterial": return "prove_material";
            case "resResult": return "res_result";
            case "engineer": return "engineer";
            case "projectManager": return "project_manager";
            case "deptName": return "dept_name";
            case "resName": return "res_name";
            case "resNum": return "res_num";
            case "resId": return "res_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>isagree -> isagree</li>
     * <li>res_flag -> resFlag</li>
     * <li>proc_inst_id -> procInstId</li>
     * <li>parent_id -> parentId</li>
     * <li>reason_explain -> reasonExplain</li>
     * <li>prove_material -> proveMaterial</li>
     * <li>res_result -> resResult</li>
     * <li>engineer -> engineer</li>
     * <li>project_manager -> projectManager</li>
     * <li>dept_name -> deptName</li>
     * <li>res_name -> resName</li>
     * <li>res_num -> resNum</li>
     * <li>res_id -> resId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "isagree": return "isagree";
            case "res_flag": return "resFlag";
            case "proc_inst_id": return "procInstId";
            case "parent_id": return "parentId";
            case "reason_explain": return "reasonExplain";
            case "prove_material": return "proveMaterial";
            case "res_result": return "resResult";
            case "engineer": return "engineer";
            case "project_manager": return "projectManager";
            case "dept_name": return "deptName";
            case "res_name": return "resName";
            case "res_num": return "resNum";
            case "res_id": return "resId";
            case "id": return "id";
            default: return null;
        }
    }
    
    /**  **/
    public Integer getIsagree() {
        return this.isagree;
    }

    /**  **/
    public void setIsagree(Integer isagree) {
        this.isagree = isagree;
    }

    /**  **/
    public String getResFlag() {
        return this.resFlag;
    }

    /**  **/
    public void setResFlag(String resFlag) {
        this.resFlag = resFlag;
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
    public String getParentId() {
        return this.parentId;
    }

    /**  **/
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**  **/
    public String getReasonExplain() {
        return this.reasonExplain;
    }

    /**  **/
    public void setReasonExplain(String reasonExplain) {
        this.reasonExplain = reasonExplain;
    }

    /**  **/
    public String getProveMaterial() {
        return this.proveMaterial;
    }

    /**  **/
    public void setProveMaterial(String proveMaterial) {
        this.proveMaterial = proveMaterial;
    }

    /**  **/
    public Integer getResResult() {
        return this.resResult;
    }

    /**  **/
    public void setResResult(Integer resResult) {
        this.resResult = resResult;
    }

    /**  **/
    public String getEngineer() {
        return this.engineer;
    }

    /**  **/
    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    /**  **/
    public String getProjectManager() {
        return this.projectManager;
    }

    /**  **/
    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    /**  **/
    public String getDeptName() {
        return this.deptName;
    }

    /**  **/
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**  **/
    public String getResName() {
        return this.resName;
    }

    /**  **/
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**  **/
    public String getResNum() {
        return this.resNum;
    }

    /**  **/
    public void setResNum(String resNum) {
        this.resNum = resNum;
    }

    /**  **/
    public String getResId() {
        return this.resId;
    }

    /**  **/
    public void setResId(String resId) {
        this.resId = resId;
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
