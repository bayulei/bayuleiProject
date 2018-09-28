package com.adc.da.person.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>TS_PERSON_NOTE PersonNoteEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PersonNoteEO extends BaseEntity {

    //@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    //@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creartionTime;
    private Integer validFlag;
    private Object noteContent;
    private String resType;
    private String resId;
    private String collectId;
    private String userId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creartionTime -> creartion_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>noteContent -> note_content</li>
     * <li>resType -> res_type</li>
     * <li>resId -> res_id</li>
     * <li>collectId -> collect_id</li>
     * <li>userId -> user_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creartionTime": return "creartion_time";
            case "validFlag": return "valid_flag";
            case "noteContent": return "note_content";
            case "resType": return "res_type";
            case "resId": return "res_id";
            case "collectId": return "collect_id";
            case "userId": return "user_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creartion_time -> creartionTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>note_content -> noteContent</li>
     * <li>res_type -> resType</li>
     * <li>res_id -> resId</li>
     * <li>collect_id -> collectId</li>
     * <li>user_id -> userId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creartion_time": return "creartionTime";
            case "valid_flag": return "validFlag";
            case "note_content": return "noteContent";
            case "res_type": return "resType";
            case "res_id": return "resId";
            case "collect_id": return "collectId";
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
    public Date getCreartionTime() {
        return this.creartionTime;
    }

    /**  **/
    public void setCreartionTime(Date creartionTime) {
        this.creartionTime = creartionTime;
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
    public Object getNoteContent() {
        return this.noteContent;
    }

    /**  **/
    public void setNoteContent(Object noteContent) {
        this.noteContent = noteContent;
    }

    /**  **/
    public String getResType() {
        return this.resType;
    }

    /**  **/
    public void setResType(String resType) {
        this.resType = resType;
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
    public String getCollectId() {
        return this.collectId;
    }

    /**  **/
    public void setCollectId(String collectId) {
        this.collectId = collectId;
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
