package com.adc.da.person.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TS_PERSON_COLLECT PersonCollectEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersonCollectEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String collectResId;
    private String collectInfoUri;
    private String collectTitle;
    private String collectType;
    private String userId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>collectResId -> collect_res_id</li>
     * <li>collectInfoUri -> collect_info_uri</li>
     * <li>collectTitle -> collect_title</li>
     * <li>collectType -> collect_type</li>
     * <li>userId -> user_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "collectResId": return "collect_res_id";
            case "collectInfoUri": return "collect_info_uri";
            case "collectTitle": return "collect_title";
            case "collectType": return "collect_type";
            case "userId": return "user_id";
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
     * <li>collect_res_id -> collectResId</li>
     * <li>collect_info_uri -> collectInfoUri</li>
     * <li>collect_title -> collectTitle</li>
     * <li>collect_type -> collectType</li>
     * <li>user_id -> userId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "collect_res_id": return "collectResId";
            case "collect_info_uri": return "collectInfoUri";
            case "collect_title": return "collectTitle";
            case "collect_type": return "collectType";
            case "user_id": return "userId";
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
    public String getCollectResId() {
        return this.collectResId;
    }

    /**  **/
    public void setCollectResId(String collectResId) {
        this.collectResId = collectResId;
    }

    /**  **/
    public String getCollectInfoUri() {
        return this.collectInfoUri;
    }

    /**  **/
    public void setCollectInfoUri(String collectInfoUri) {
        this.collectInfoUri = collectInfoUri;
    }

    /**  **/
    public String getCollectTitle() {
        return this.collectTitle;
    }

    /**  **/
    public void setCollectTitle(String collectTitle) {
        this.collectTitle = collectTitle;
    }

    /**  **/
    public String getCollectType() {
        return this.collectType;
    }

    /**  **/
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**  **/
    public String getUserId() {
        return this.userId;
    }

    /**  **/
    public void setUserId(String userId) {
        this.userId = userId;
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
