package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;

import java.util.Date;

public class SarCompileExportDto {

    @Excel(name = "标准大类名称")
    private String busStandClassify;

    @Excel(name = "标准细类名称")
    private String busStandSubClass;

    @Excel(name = "分类代号")
    private String classifyCode;

    @Excel(name = "标准编号")
    private String standCode ;

    @Excel(name = "标准名称")
    private String standName;

    @Excel(name = "发布日期")
    private Date issueTime;

    @Excel(name = "实施日期")
    private Date putTime;

    @Excel(name = "实施年份")
    private String putYear;

    @Excel(name = "备注")
    private String remarks;

    @Excel(name = "代替标准号")
    private String replaceStandNum;

    @Excel(name = "责任部门")
    private String dutyUnit;

    public String getBusStandClassify() {
        return busStandClassify;
    }

    public void setBusStandClassify(String busStandClassify) {
        this.busStandClassify = busStandClassify;
    }

    public String getBusStandSubClass() {
        return busStandSubClass;
    }

    public void setBusStandSubClass(String busStandSubClass) {
        this.busStandSubClass = busStandSubClass;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getStandCode() {
        return standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
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

    public String getPutYear() {
        return putYear;
    }

    public void setPutYear(String putYear) {
        this.putYear = putYear;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReplaceStandNum() {
        return replaceStandNum;
    }

    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    public String getDutyUnit() {
        return dutyUnit;
    }

    public void setDutyUnit(String dutyUnit) {
        this.dutyUnit = dutyUnit;
    }
}
