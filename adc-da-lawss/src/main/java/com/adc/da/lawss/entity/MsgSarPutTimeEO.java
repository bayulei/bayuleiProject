package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>MSG_SAR_PUT_TIME MsgSarPutTimeEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class MsgSarPutTimeEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    private String sarId;
    private String sarPutType;
    private String sarType;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>putTime -> put_time</li>
     * <li>sarId -> sar_id</li>
     * <li>sarPutType -> sar_put_type</li>
     * <li>sarType -> sar_type</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "putTime": return "put_time";
            case "sarId": return "sar_id";
            case "sarPutType": return "sar_put_type";
            case "sarType": return "sar_type";
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
     * <li>put_time -> putTime</li>
     * <li>sar_id -> sarId</li>
     * <li>sar_put_type -> sarPutType</li>
     * <li>sar_type -> sarType</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "put_time": return "putTime";
            case "sar_id": return "sarId";
            case "sar_put_type": return "sarPutType";
            case "sar_type": return "sarType";
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
    public Date getPutTime() {
        return this.putTime;
    }

    /**  **/
    public void setPutTime(Date putTime) {
        this.putTime = putTime;
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
    public String getSarPutType() {
        return this.sarPutType;
    }

    /**  **/
    public void setSarPutType(String sarPutType) {
        this.sarPutType = sarPutType;
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

}
