package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_BROWSE_INFO SarBrowseInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBrowseInfoEO extends BaseEntity {

    private String sarId;
    private String sarType;
    private String id;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date browseTime;
    private String browseUser;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>sarId -> sar_id</li>
     * <li>sarType -> sar_type</li>
     * <li>id -> id</li>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>browseTime -> browse_time</li>
     * <li>browseUser -> browse_user</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "sarId": return "sar_id";
            case "sarType": return "sar_type";
            case "id": return "id";
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "browseTime": return "browse_time";
            case "browseUser": return "browse_user";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>sar_id -> sarId</li>
     * <li>sar_type -> sarType</li>
     * <li>id -> id</li>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>browse_time -> browseTime</li>
     * <li>browse_user -> browseUser</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "sar_id": return "sarId";
            case "sar_type": return "sarType";
            case "id": return "id";
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "browse_time": return "browseTime";
            case "browse_user": return "browseUser";
            default: return null;
        }
    }
    
    /**  **/
    public String getSarId() {
        return this.sarId;
    }

    /**  **/
    public void setSarId(String sarId) {
        this.sarId = sarId;
    }

    /**  **/
    public String getSarType() {
        return this.sarType;
    }

    /**  **/
    public void setSarType(String sarType) {
        this.sarType = sarType;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
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
    public Date getBrowseTime() {
        return this.browseTime;
    }

    /**  **/
    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    /**  **/
    public String getBrowseUser() {
        return this.browseUser;
    }

    /**  **/
    public void setBrowseUser(String browseUser) {
        this.browseUser = browseUser;
    }

}
