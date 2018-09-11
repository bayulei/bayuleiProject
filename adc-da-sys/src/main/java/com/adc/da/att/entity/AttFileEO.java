package com.adc.da.att.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>ATT_FILE AttFileEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-07 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AttFileEO extends BaseEntity {

    private String id;
    private String fileName;
    private String oldFileName;
    private String fileSuffix;
    private String filePath;
    private Integer validFlag;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    
    private String tableName;
    
    
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>fileName -> file_name</li>
     * <li>oldFileName -> old_file_name</li>
     * <li>fileSuffix -> file_suffix</li>
     * <li>filePath -> file_path</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationTime -> creation_time</li>
     * <li>modifyTime -> modify_time</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "fileName": return "file_name";
            case "oldFileName": return "old_file_name";
            case "fileSuffix": return "file_suffix";
            case "filePath": return "file_path";
            case "validFlag": return "valid_flag";
            case "creationTime": return "creation_time";
            case "modifyTime": return "modify_time";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>file_name -> fileName</li>
     * <li>old_file_name -> oldFileName</li>
     * <li>file_suffix -> fileSuffix</li>
     * <li>file_path -> filePath</li>
     * <li>valid_flag -> validFlag</li>
     * <li>creation_time -> creationTime</li>
     * <li>modify_time -> modifyTime</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "file_name": return "fileName";
            case "old_file_name": return "oldFileName";
            case "file_suffix": return "fileSuffix";
            case "file_path": return "filePath";
            case "valid_flag": return "validFlag";
            case "creation_time": return "creationTime";
            case "modify_time": return "modifyTime";
            default: return null;
        }
    }
    
    /**  **/
    public String getId() {
    	int tableNameIndex = this.id.lastIndexOf("_");
        String tableName = this.id.substring(0, tableNameIndex);
        this.tableName=tableName;
        return this.id;
    }

    /**  **/
    public void setId(String id) {
    	if(tableName!=null && !tableName.isEmpty()){
    		this.id = tableName+"_"+id;    		
    	}
    	//此处注意保存时表结构是否存在
    	this.id=id;
    }

    /**  **/
    public String getFileName() {
        return this.fileName;
    }

    /**  **/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**  **/
    public String getOldFileName() {
        return this.oldFileName;
    }

    /**  **/
    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    /**  **/
    public String getFileSuffix() {
        return this.fileSuffix;
    }

    /**  **/
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    /**  **/
    public String getFilePath() {
        return this.filePath;
    }

    /**  **/
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**  **/
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**  **/
    public Date getCreationTime() {
        return this.creationTime;
    }

    /**  **/
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**  **/
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**  **/
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
