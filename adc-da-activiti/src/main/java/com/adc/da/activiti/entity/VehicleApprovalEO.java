package com.adc.da.activiti.entity;

import com.adc.da.base.entity.BaseEntity;

import java.io.Serializable;

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
