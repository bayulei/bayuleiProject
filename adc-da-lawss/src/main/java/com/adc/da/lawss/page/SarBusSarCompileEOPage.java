package com.adc.da.lawss.page;

import com.adc.da.base.page.BasePage;

import java.sql.Clob;
import java.util.Date;

/**
 * <b>功能：</b>SAR_BUS_SAR_COMPILE SarBusSarCompileEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBusSarCompileEOPage extends BasePage {

    private String modifyTime;
    private String modifyTime1;
    private String modifyTime2;
    private String modifyTimeOperator = "=";
    private String creationTime;
    private String creationTime1;
    private String creationTime2;
    private String creationTimeOperator = "=";
    private String validFlag;
    private String validFlagOperator = "=";
    private String creationUser;
    private String creationUserOperator = "=";
    private String dutyUnit;
    private String dutyUnitOperator = "=";
    private String replaceStandNum;
    private String replaceStandNumOperator = "=";
    private String remarks;
    private String remarksOperator = "=";
    private String putYear;
    private String putYearOperator = "=";
    private String putTime;
    private String putTime1;
    private String putTime2;
    private String putTimeOperator = "=";
    private String issueTime;
    private String issueTime1;
    private String issueTime2;
    private String issueTimeOperator = "=";
    private String standName;
    private String standNameOperator = "like";
    private String standYear;
    private String standYearOperator = "=";
    private String standNumber;
    private String standNumberOperator = "like";
    private String standCode;
    private String standCodeOperator = "=";
    private String classifyCode;
    private String classifyCodeOperator = "like";
    private String busStandSubclass;
    private String busStandSubclassOperator = "like";
    private String busStandClassify;
    private String busStandClassifyOperator = "=";
    private String id;
    private String idOperator = "=";

    public String getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyTime1() {
        return this.modifyTime1;
    }

    public void setModifyTime1(String modifyTime1) {
        this.modifyTime1 = modifyTime1;
    }

    public String getModifyTime2() {
        return this.modifyTime2;
    }

    public void setModifyTime2(String modifyTime2) {
        this.modifyTime2 = modifyTime2;
    }

    public String getModifyTimeOperator() {
        return this.modifyTimeOperator;
    }

    public void setModifyTimeOperator(String modifyTimeOperator) {
        this.modifyTimeOperator = modifyTimeOperator;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTime1() {
        return this.creationTime1;
    }

    public void setCreationTime1(String creationTime1) {
        this.creationTime1 = creationTime1;
    }

    public String getCreationTime2() {
        return this.creationTime2;
    }

    public void setCreationTime2(String creationTime2) {
        this.creationTime2 = creationTime2;
    }

    public String getCreationTimeOperator() {
        return this.creationTimeOperator;
    }

    public void setCreationTimeOperator(String creationTimeOperator) {
        this.creationTimeOperator = creationTimeOperator;
    }

    public String getValidFlag() {
        return this.validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlagOperator() {
        return this.validFlagOperator;
    }

    public void setValidFlagOperator(String validFlagOperator) {
        this.validFlagOperator = validFlagOperator;
    }

    public String getCreationUser() {
        return this.creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getCreationUserOperator() {
        return this.creationUserOperator;
    }

    public void setCreationUserOperator(String creationUserOperator) {
        this.creationUserOperator = creationUserOperator;
    }

    public String getDutyUnit() {
        return this.dutyUnit;
    }

    public void setDutyUnit(String dutyUnit) {
        this.dutyUnit = dutyUnit;
    }

    public String getDutyUnitOperator() {
        return this.dutyUnitOperator;
    }

    public void setDutyUnitOperator(String dutyUnitOperator) {
        this.dutyUnitOperator = dutyUnitOperator;
    }

    public String getReplaceStandNum() {
        return this.replaceStandNum;
    }

    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    public String getReplaceStandNumOperator() {
        return this.replaceStandNumOperator;
    }

    public void setReplaceStandNumOperator(String replaceStandNumOperator) {
        this.replaceStandNumOperator = replaceStandNumOperator;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarksOperator() {
        return this.remarksOperator;
    }

    public void setRemarksOperator(String remarksOperator) {
        this.remarksOperator = remarksOperator;
    }

    public String getPutYear() {
        return this.putYear;
    }

    public void setPutYear(String putYear) {
        this.putYear = putYear;
    }

    public String getPutYearOperator() {
        return this.putYearOperator;
    }

    public void setPutYearOperator(String putYearOperator) {
        this.putYearOperator = putYearOperator;
    }

    public String getPutTime() {
        return this.putTime;
    }

    public void setPutTime(String putTime) {
        this.putTime = putTime;
    }

    public String getPutTime1() {
        return this.putTime1;
    }

    public void setPutTime1(String putTime1) {
        this.putTime1 = putTime1;
    }

    public String getPutTime2() {
        return this.putTime2;
    }

    public void setPutTime2(String putTime2) {
        this.putTime2 = putTime2;
    }

    public String getPutTimeOperator() {
        return this.putTimeOperator;
    }

    public void setPutTimeOperator(String putTimeOperator) {
        this.putTimeOperator = putTimeOperator;
    }

    public String getIssueTime() {
        return this.issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssueTime1() {
        return this.issueTime1;
    }

    public void setIssueTime1(String issueTime1) {
        this.issueTime1 = issueTime1;
    }

    public String getIssueTime2() {
        return this.issueTime2;
    }

    public void setIssueTime2(String issueTime2) {
        this.issueTime2 = issueTime2;
    }

    public String getIssueTimeOperator() {
        return this.issueTimeOperator;
    }

    public void setIssueTimeOperator(String issueTimeOperator) {
        this.issueTimeOperator = issueTimeOperator;
    }

    public String getStandName() {
        return this.standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getStandNameOperator() {
        return this.standNameOperator;
    }

    public void setStandNameOperator(String standNameOperator) {
        this.standNameOperator = standNameOperator;
    }

    public String getStandYear() {
        return this.standYear;
    }

    public void setStandYear(String standYear) {
        this.standYear = standYear;
    }

    public String getStandYearOperator() {
        return this.standYearOperator;
    }

    public void setStandYearOperator(String standYearOperator) {
        this.standYearOperator = standYearOperator;
    }

    public String getStandNumber() {
        return this.standNumber;
    }

    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
    }

    public String getStandNumberOperator() {
        return this.standNumberOperator;
    }

    public void setStandNumberOperator(String standNumberOperator) {
        this.standNumberOperator = standNumberOperator;
    }

    public String getStandCode() {
        return this.standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public String getStandCodeOperator() {
        return this.standCodeOperator;
    }

    public void setStandCodeOperator(String standCodeOperator) {
        this.standCodeOperator = standCodeOperator;
    }

    public String getClassifyCode() {
        return this.classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyCodeOperator() {
        return this.classifyCodeOperator;
    }

    public void setClassifyCodeOperator(String classifyCodeOperator) {
        this.classifyCodeOperator = classifyCodeOperator;
    }

    public String getBusStandSubclass() {
        return this.busStandSubclass;
    }

    public void setBusStandSubclass(String busStandSubclass) {
        this.busStandSubclass = busStandSubclass;
    }

    public String getBusStandSubclassOperator() {
        return this.busStandSubclassOperator;
    }

    public void setBusStandSubclassOperator(String busStandSubclassOperator) {
        this.busStandSubclassOperator = busStandSubclassOperator;
    }

    public String getBusStandClassify() {
        return this.busStandClassify;
    }

    public void setBusStandClassify(String busStandClassify) {
        this.busStandClassify = busStandClassify;
    }

    public String getBusStandClassifyOperator() {
        return this.busStandClassifyOperator;
    }

    public void setBusStandClassifyOperator(String busStandClassifyOperator) {
        this.busStandClassifyOperator = busStandClassifyOperator;
    }

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

}
