package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_SAR_ACCESS_INFO SarSarAccessInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarSarAccessInfoEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private Integer displaySeq;
    private String resId;
    private String sarType;
    private String accessId;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>displaySeq -> display_seq</li>
     * <li>resId -> res_id</li>
     * <li>sarType -> sar_type</li>
     * <li>accessId -> access_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "displaySeq": return "display_seq";
            case "resId": return "res_id";
            case "sarType": return "sar_type";
            case "accessId": return "access_id";
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
     * <li>display_seq -> displaySeq</li>
     * <li>res_id -> resId</li>
     * <li>sar_type -> sarType</li>
     * <li>access_id -> accessId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "display_seq": return "displaySeq";
            case "res_id": return "resId";
            case "sar_type": return "sarType";
            case "access_id": return "accessId";
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
    public Integer getDisplaySeq() {
        return this.displaySeq;
    }

    /**  **/
    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
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
    public String getSarType() {
        return this.sarType;
    }

    /**  **/
    public void setSarType(String sarType) {
        this.sarType = sarType;
    }

    /**  **/
    public String getAccessId() {
        return this.accessId;
    }

    /**  **/
    public void setAccessId(String accessId) {
        this.accessId = accessId;
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