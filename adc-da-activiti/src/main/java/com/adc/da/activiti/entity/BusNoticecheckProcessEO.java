package com.adc.da.activiti.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * <b>功能：</b>BUS_NOTICECHECK_PROCESS BusNoticecheckProcessEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class BusNoticecheckProcessEO extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 3362990175004982375L;
    private String project;
    private String plan;
    private String solution;
    private Integer isqualified;
    private String clausecontent;
    private String clausenum;
    private String office;
    private String dept;
    private String engineerid;
    private String responsiblemanid;
    private String taskId;
    private String procInstId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>project -> project</li>
     * <li>plan -> plan</li>
     * <li>solution -> solution</li>
     * <li>isqualified -> isqualified</li>
     * <li>clausecontent -> clausecontent</li>
     * <li>clausenum -> clausenum</li>
     * <li>office -> office</li>
     * <li>dept -> dept</li>
     * <li>engineerid -> engineerid</li>
     * <li>responsiblemanid -> responsiblemanid</li>
     * <li>taskId -> task_id</li>
     * <li>procInstId -> proc_inst_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "project": return "project";
            case "plan": return "plan";
            case "solution": return "solution";
            case "isqualified": return "isqualified";
            case "clausecontent": return "clausecontent";
            case "clausenum": return "clausenum";
            case "office": return "office";
            case "dept": return "dept";
            case "engineerid": return "engineerid";
            case "responsiblemanid": return "responsiblemanid";
            case "taskId": return "task_id";
            case "procInstId": return "proc_inst_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>project -> project</li>
     * <li>plan -> plan</li>
     * <li>solution -> solution</li>
     * <li>isqualified -> isqualified</li>
     * <li>clausecontent -> clausecontent</li>
     * <li>clausenum -> clausenum</li>
     * <li>office -> office</li>
     * <li>dept -> dept</li>
     * <li>engineerid -> engineerid</li>
     * <li>responsiblemanid -> responsiblemanid</li>
     * <li>task_id -> taskId</li>
     * <li>proc_inst_id -> procInstId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "project": return "project";
            case "plan": return "plan";
            case "solution": return "solution";
            case "isqualified": return "isqualified";
            case "clausecontent": return "clausecontent";
            case "clausenum": return "clausenum";
            case "office": return "office";
            case "dept": return "dept";
            case "engineerid": return "engineerid";
            case "responsiblemanid": return "responsiblemanid";
            case "task_id": return "taskId";
            case "proc_inst_id": return "procInstId";
            case "id": return "id";
            default: return null;
        }
    }
    
    /**  **/
    public String getProject() {
        return this.project;
    }

    /**  **/
    public void setProject(String project) {
        this.project = project;
    }

    /**  **/
    public String getPlan() {
        return this.plan;
    }

    /**  **/
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**  **/
    public String getSolution() {
        return this.solution;
    }

    /**  **/
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**  **/
    public Integer getIsqualified() {
        return this.isqualified;
    }

    /**  **/
    public void setIsqualified(Integer isqualified) {
        this.isqualified = isqualified;
    }

    /**  **/
    public String getClausecontent() {
        return this.clausecontent;
    }

    /**  **/
    public void setClausecontent(String clausecontent) {
        this.clausecontent = clausecontent;
    }

    /**  **/
    public String getClausenum() {
        return this.clausenum;
    }

    /**  **/
    public void setClausenum(String clausenum) {
        this.clausenum = clausenum;
    }

    /**  **/
    public String getOffice() {
        return this.office;
    }

    /**  **/
    public void setOffice(String office) {
        this.office = office;
    }

    /**  **/
    public String getDept() {
        return this.dept;
    }

    /**  **/
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**  **/
    public String getEngineerid() {
        return this.engineerid;
    }

    /**  **/
    public void setEngineerid(String engineerid) {
        this.engineerid = engineerid;
    }

    /**  **/
    public String getResponsiblemanid() {
        return this.responsiblemanid;
    }

    /**  **/
    public void setResponsiblemanid(String responsiblemanid) {
        this.responsiblemanid = responsiblemanid;
    }

    /**  **/
    public String getTaskId() {
        return this.taskId;
    }

    /**  **/
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
