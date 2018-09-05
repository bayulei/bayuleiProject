package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;

import java.util.Date;

public class LawsInfoImportDto {

    @Excel(name = "国家地区", orderNum = "1")
    private String country;

    @Excel(name = "文件性质", orderNum = "2")
    private String lawsProperty;

    @Excel(name = "文件号", orderNum = "3")
    private String lawsNumber;

    @Excel(name = "文件名称", orderNum = "4")
    private String lawsName;

    @Excel(name = "发布单位", orderNum = "5")
    private String issueUnit;

    @Excel(name = "文件状态", orderNum = "6")
    private String lawsStatus;

    @Excel(name = "发布日期", orderNum = "7")
    private Date issueTime;

    @Excel(name = "实施日期", orderNum = "8")
    private Date putTime;

    @Excel(name = "代替文件号", orderNum = "9")
    private String replaceLawsNum;

    @Excel(name = "适用车型", orderNum = "10")
    private String applyArctic;

    @Excel(name = "能源种类", orderNum = "11")
    private String energyKind;

    @Excel(name = "适用认证", orderNum = "12")
    private String applyAuth;

    @Excel(name = "责任部门", orderNum = "13")
    private String responsibleUnit;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLawsProperty() {
        return lawsProperty;
    }

    public void setLawsProperty(String lawsProperty) {
        this.lawsProperty = lawsProperty;
    }

    public String getLawsNumber() {
        return lawsNumber;
    }

    public void setLawsNumber(String lawsNumber) {
        this.lawsNumber = lawsNumber;
    }

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }

    public String getIssueUnit() {
        return issueUnit;
    }

    public void setIssueUnit(String issueUnit) {
        this.issueUnit = issueUnit;
    }

    public String getLawsStatus() {
        return lawsStatus;
    }

    public void setLawsStatus(String lawsStatus) {
        this.lawsStatus = lawsStatus;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public String getReplaceLawsNum() {
        return replaceLawsNum;
    }

    public void setReplaceLawsNum(String replaceLawsNum) {
        this.replaceLawsNum = replaceLawsNum;
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

    public String getApplyAuth() {
        return applyAuth;
    }

    public void setApplyAuth(String applyAuth) {
        this.applyAuth = applyAuth;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }
}
