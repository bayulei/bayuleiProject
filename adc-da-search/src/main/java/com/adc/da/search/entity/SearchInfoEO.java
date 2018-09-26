package com.adc.da.search.entity;

import com.adc.da.att.vo.AttFileVo;
import com.adc.da.base.entity.BaseEntity;
import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>SAR_STANDARDS_INFO SarStandardsInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */

public class SearchInfoEO extends BasePage {

    //标准编号      文件号laws_number     标准编号STAND_CODE
    private String standNumber;
    //标准名称      文件名称laws_name     standNAME
    private String standName;
    //标准英文名称  无                    standEnName
    private String standEnName;
    //标准状态      文件状态laws_status   standStatus
    private String standState;
    //标准文本 支持PDF、doc、docx文件《此项需要按照标准正文内容修改》  无   标准文本文件standFile
    private String standFile;
    //适用车型  适用车型  适用车型
    private String applyArctic;
    //发布日期   发布日期   发布日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueTime;
    //实施日期   实施日期   实施日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    //起草人  无   有发布人和主要编织者putUser,citationUser
    private String draftUser;
    //起草单位   发布单位issueUnit   无，只有责任部门
    private String draftingUnit;
    //代替标准号   代替文件号replaceLawsNum   代替标准号
    private String replaceStandNum;
    //被代替标准号  无   被代替标准号
    private String replacedStandNum;

    //需求文档中还有 《文件名称》laws 中有 laws_name  标准库，企业标准库没有相关字段
    private String lawsName;
    // 《动态标题》，标准库中找不到对应 都没有

    //记录首页中的下拉选择的数据类别
    private String selectKey;
    //记录首页搜索框中对应的数据
    private String selectValue;

    public String getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getStandEnName() {
        return standEnName;
    }

    public void setStandEnName(String standEnName) {
        this.standEnName = standEnName;
    }

    public String getStandState() {
        return standState;
    }

    public void setStandState(String standState) {
        this.standState = standState;
    }

    public String getStandFile() {
        return standFile;
    }

    public void setStandFile(String standFile) {
        this.standFile = standFile;
    }

    public String getApplyArctic() {
        return applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
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

    public String getDraftUser() {
        return draftUser;
    }

    public void setDraftUser(String draftUser) {
        this.draftUser = draftUser;
    }

    public String getDraftingUnit() {
        return draftingUnit;
    }

    public void setDraftingUnit(String draftingUnit) {
        this.draftingUnit = draftingUnit;
    }

    public String getReplaceStandNum() {
        return replaceStandNum;
    }

    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    public String getReplacedStandNum() {
        return replacedStandNum;
    }

    public void setReplacedStandNum(String replacedStandNum) {
        this.replacedStandNum = replacedStandNum;
    }

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }
}
