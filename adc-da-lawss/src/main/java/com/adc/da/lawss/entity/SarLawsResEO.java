package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_LAWS_RES SarLawsResEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarLawsResEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String fileSuffix;
    private String fileName;
    private String lawsFileClassify;
    private String lawsId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>fileSuffix -> file_suffix</li>
     * <li>fileName -> file_name</li>
     * <li>lawsFileClassify -> laws_file_classify</li>
     * <li>lawsId -> laws_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "fileSuffix": return "file_suffix";
            case "fileName": return "file_name";
            case "lawsFileClassify": return "laws_file_classify";
            case "lawsId": return "laws_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>file_suffix -> fileSuffix</li>
     * <li>file_name -> fileName</li>
     * <li>laws_file_classify -> lawsFileClassify</li>
     * <li>laws_id -> lawsId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "file_suffix": return "fileSuffix";
            case "file_name": return "fileName";
            case "laws_file_classify": return "lawsFileClassify";
            case "laws_id": return "lawsId";
            case "id": return "id";
            default: return null;
        }
    }
    
    /**  **/
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**  **/
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
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
    public String getFileName() {
        return this.fileName;
    }

    /**  **/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**  **/
    public String getLawsFileClassify() {
        return this.lawsFileClassify;
    }

    /**  **/
    public void setLawsFileClassify(String lawsFileClassify) {
        this.lawsFileClassify = lawsFileClassify;
    }

    /**  **/
    public String getLawsId() {
        return this.lawsId;
    }

    /**  **/
    public void setLawsId(String lawsId) {
        this.lawsId = lawsId;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

}