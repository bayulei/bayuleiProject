package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>MSG_DYNAMIC_INFO MsgDynamicInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class MsgDynamicInfoEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String pubOrg;
    private String pubUser;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pubTime;
    private String linkUri;
    private String content;
    private String title;
    private String msgMode;
    private String msgType;
    private String id;

    private String pubUserName;

    private Integer isPicMsg;


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>pubOrg -> pub_org</li>
     * <li>pubUser -> pub_user</li>
     * <li>pubTime -> pub_time</li>
     * <li>linkUri -> link_uri</li>
     * <li>content -> content</li>
     * <li>title -> title</li>
     * <li>msgMode -> msg_mode</li>
     * <li>msgType -> msg_type</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "pubOrg": return "pub_org";
            case "pubUser": return "pub_user";
            case "pubTime": return "pub_time";
            case "linkUri": return "link_uri";
            case "content": return "content";
            case "title": return "title";
            case "msgMode": return "msg_mode";
            case "msgType": return "msg_type";
            case "id": return "id";
            case "isPicMsg": return "is_pic_msg";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>pub_org -> pubOrg</li>
     * <li>pub_user -> pubUser</li>
     * <li>pub_time -> pubTime</li>
     * <li>link_uri -> linkUri</li>
     * <li>content -> content</li>
     * <li>title -> title</li>
     * <li>msg_mode -> msgMode</li>
     * <li>msg_type -> msgType</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "pub_org": return "pubOrg";
            case "pub_user": return "pubUser";
            case "pub_time": return "pubTime";
            case "link_uri": return "linkUri";
            case "content": return "content";
            case "title": return "title";
            case "msg_mode": return "msgMode";
            case "msg_type": return "msgType";
            case "id": return "id";
            case "is_pic_msg": return "isPicMsg";
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
    public String getPubOrg() {
        return this.pubOrg;
    }

    /**  **/
    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg;
    }

    /**  **/
    public String getPubUser() {
        return this.pubUser;
    }

    /**  **/
    public void setPubUser(String pubUser) {
        this.pubUser = pubUser;
    }

    /**  **/
    public Date getPubTime() {
        return this.pubTime;
    }

    /**  **/
    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    /**  **/
    public String getLinkUri() {
        return this.linkUri;
    }

    /**  **/
    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**  **/
    public String getTitle() {
        return this.title;
    }

    /**  **/
    public void setTitle(String title) {
        this.title = title;
    }

    /**  **/
    public String getMsgMode() {
        return this.msgMode;
    }

    /**  **/
    public void setMsgMode(String msgMode) {
        this.msgMode = msgMode;
    }

    /**  **/
    public String getMsgType() {
        return this.msgType;
    }

    /**  **/
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    public void setPubUserName(String pubUserName){
        this.pubUserName=pubUserName;
    }

    public String getPubUserName(){
        return this.pubUserName;
    }

    public void setIsPicMsg(Integer isPicMsg){
        this.isPicMsg=isPicMsg;
    }

    public Integer getIsPicMsg(){
        return this.isPicMsg;
    }

}
