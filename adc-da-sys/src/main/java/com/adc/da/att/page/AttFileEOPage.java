package com.adc.da.att.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;

/**
 * <b>功能：</b>ATT_FILE AttFileEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-07 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AttFileEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String fileName;
    private String fileNameOperator = "=";
    private String oldFileName;
    private String oldFileNameOperator = "=";
    private String fileSuffix;
    private String fileSuffixOperator = "=";
    private String filePath;
    private String filePathOperator = "=";
    private String validFlag;
    private String validFlagOperator = "=";
    private String creationTime;
    private String creationTime1;
    private String creationTime2;
    private String creationTimeOperator = "=";
    private String modifyTime;
    private String modifyTime1;
    private String modifyTime2;
    private String modifyTimeOperator = "=";

    private String tableName;
    
    public String getId() {
    	int tableNameIndex = this.id.lastIndexOf("_");
        String tableName = this.id.substring(0, tableNameIndex);
        this.tableName=tableName;
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
        int tableNameIndex = id.lastIndexOf("_");
        String tableName = id.substring(0, tableNameIndex);
        this.tableName=tableName;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameOperator() {
        return this.fileNameOperator;
    }

    public void setFileNameOperator(String fileNameOperator) {
        this.fileNameOperator = fileNameOperator;
    }

    public String getOldFileName() {
        return this.oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getOldFileNameOperator() {
        return this.oldFileNameOperator;
    }

    public void setOldFileNameOperator(String oldFileNameOperator) {
        this.oldFileNameOperator = oldFileNameOperator;
    }

    public String getFileSuffix() {
        return this.fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffixOperator() {
        return this.fileSuffixOperator;
    }

    public void setFileSuffixOperator(String fileSuffixOperator) {
        this.fileSuffixOperator = fileSuffixOperator;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePathOperator() {
        return this.filePathOperator;
    }

    public void setFilePathOperator(String filePathOperator) {
        this.filePathOperator = filePathOperator;
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

	public String getTableName() {
		int tableNameIndex = this.id.lastIndexOf("_");
        String tableName = this.id.substring(0, tableNameIndex);
        this.tableName=tableName;
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

    
    
}
