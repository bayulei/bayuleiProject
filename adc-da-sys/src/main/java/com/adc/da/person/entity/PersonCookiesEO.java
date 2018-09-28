package com.adc.da.person.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>TS_PERSON_COOKIES PersonCookiesEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersonCookiesEO extends BaseEntity {

    //@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    //@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")

    private Date creationTime;
    private Integer validFlag;
    private String resUri;
    private String resId;
    private String resTitle;
    private String cookieType;
    private String userId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>resUri -> res_uri</li>
     * <li>resId -> res_id</li>
     * <li>resTitle -> res_title</li>
     * <li>cookieType -> cookie_type</li>
     * <li>userId -> user_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "resUri": return "res_uri";
            case "resId": return "res_id";
            case "resTitle": return "res_title";
            case "cookieType": return "cookie_type";
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
     * <li>res_uri -> resUri</li>
     * <li>res_id -> resId</li>
     * <li>res_title -> resTitle</li>
     * <li>cookie_type -> cookieType</li>
     * <li>user_id -> userId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "res_uri": return "resUri";
            case "res_id": return "resId";
            case "res_title": return "resTitle";
            case "cookie_type": return "cookieType";
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
    public String getResUri() {
        return this.resUri;
    }

    /**  **/
    public void setResUri(String resUri) {
        this.resUri = resUri;
    }

    /**  **/
    public String getResId() {
        return this.resId;
    }

    /**  **/
    public void setResId(String resId) {
        this.resId = resId;
    }

    /**  **/
    public String getResTitle() {
        return this.resTitle;
    }

    /**  **/
    public void setResTitle(String resTitle) {
        this.resTitle = resTitle;
    }

    /**  **/
    public String getCookieType() {
        return this.cookieType;
    }

    /**  **/
    public void setCookieType(String cookieType) {
        this.cookieType = cookieType;
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
