package com.adc.da.activiti.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StandardComplianceVO extends ApprovalProcessVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

    /**
     *  工程师处理截止时间
     */
    private Date endTime;

    /**
     *  项目名称
     */
    private String projectName;

    /**
     *  产品类别
     */
    private String applyArctic;

    /**
     *  能源类型
     */
    private String energyKind;

    /**
     *  项目经理集合
     */
    private List<String> projectManagerList;

    /**
     *  审批意见
     */
    private String approvalOpinion;

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public List<String> getProjectManagerList() {
        return projectManagerList;
    }

    public void setProjectManagerList(List<String> projectManagerList) {
        this.projectManagerList = projectManagerList;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplyArctic() {
        return applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public String getEnergyKind() {
        return energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
