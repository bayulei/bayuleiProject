package com.adc.da.activiti.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VehicleApprovalEO extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;
    /**
     *  流程类型
     */
    private  String  processType ;

    /**
     *  相关标准
     */
    private  String relatedStandards ;

    /**
     *  流程描述
     */
    private  String processDescription ;

    /**
     *  项目名称
     */
    private  String projectName ;

    /**
     *  项目名称
     */
    private  String processNumber ;

    /**
     *  选择的多个用户
     */
    private  List<String> assignerUserList = new ArrayList<String>();

    /**
     *  附件
     */
    private  String fileUrl ;

    private  String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<String> getAssignerUserList() {
        return assignerUserList;
    }

    public void setAssignerUserList(List<String> assignerUserList) {
        this.assignerUserList = assignerUserList;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getRelatedStandards() {
        return relatedStandards;
    }

    public void setRelatedStandards(String relatedStandards) {
        this.relatedStandards = relatedStandards;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
