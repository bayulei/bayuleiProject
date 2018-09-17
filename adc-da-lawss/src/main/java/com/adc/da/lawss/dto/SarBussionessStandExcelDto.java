package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SarBussionessStandExcelDto {

    @Excel(name = "主要编制者", orderNum = "1")
    private String citationUser;
    @Excel(name = "责任部门", orderNum = "1")
    private String responsibleUnit;
    @Excel(name = "标准文本", orderNum = "1")
    private String standFile;
    @Excel(name = "关键词", orderNum = "1")
    private String tags;
    @Excel(name = "标准状态", orderNum = "1")
    private String standStatus;
    @Excel(name = "被代替标准号", orderNum = "1")
    private String replacedStandNum;
    @Excel(name = "代替标准号", orderNum = "1")
    private String replaceStandNum;
    @Excel(name = "引用标准", orderNum = "1")
    private String quoteStand;
    @Excel(name = "首发日期", orderNum = "1")
    private Date firstPutTime;
    @Excel(name = "实施年份", orderNum = "1")
    private Date putYear;
    @Excel(name = "实施日期", orderNum = "1")
    private Date putTime;
    @Excel(name = "发布日期", orderNum = "1")
    private Date issueTime;
    @Excel(name = "能源种类", orderNum = "1")
    private String energyKind;
    @Excel(name = "适用车型", orderNum = "1")
    private String applyArctic;
    @Excel(name = "标准英文名称", orderNum = "1")
    private String standEnName;
    @Excel(name = "标准名称", orderNum = "1")
    private String standName;
    @Excel(name = "标准编号", orderNum = "1")
    private String standCode;
    @Excel(name = "分类代号", orderNum = "1")
    private String classifyCode;
    @Excel(name = "细类", orderNum = "1")
    private String standSubclass;
    @Excel(name = "大类", orderNum = "1")
    private String standGenera;

    public String getCitationUser() {
        return citationUser;
    }

    public void setCitationUser(String citationUser) {
        this.citationUser = citationUser;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public String getStandFile() {
        return standFile;
    }

    public void setStandFile(String standFile) {
        this.standFile = standFile;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStandStatus() {
        return standStatus;
    }

    public void setStandStatus(String standStatus) {
        this.standStatus = standStatus;
    }

    public String getReplacedStandNum() {
        return replacedStandNum;
    }

    public void setReplacedStandNum(String replacedStandNum) {
        this.replacedStandNum = replacedStandNum;
    }

    public String getReplaceStandNum() {
        return replaceStandNum;
    }

    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    public String getQuoteStand() {
        return quoteStand;
    }

    public void setQuoteStand(String quoteStand) {
        this.quoteStand = quoteStand;
    }

    public Date getFirstPutTime() {
        return firstPutTime;
    }

    public void setFirstPutTime(Date firstPutTime) {
        this.firstPutTime = firstPutTime;
    }

    public Date getPutYear() {
        return putYear;
    }

    public void setPutYear(Date putYear) {
        this.putYear = putYear;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getEnergyKind() {
        return energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public String getApplyArctic() {
        return applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public String getStandEnName() {
        return standEnName;
    }

    public void setStandEnName(String standEnName) {
        this.standEnName = standEnName;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getStandCode() {
        return standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getStandSubclass() {
        return standSubclass;
    }

    public void setStandSubclass(String standSubclass) {
        this.standSubclass = standSubclass;
    }

    public String getStandGenera() {
        return standGenera;
    }

    public void setStandGenera(String standGenera) {
        this.standGenera = standGenera;
    }
}
