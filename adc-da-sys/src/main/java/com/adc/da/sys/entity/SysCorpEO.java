package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SYS_CORP SysCorpEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-05-05 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SysCorpEO extends BaseEntity {

    private String id;
    private String corpName;
    private String corpAddress;
    private String corpUser;
    private String corpPhone;
    private String corpEmail;
    private String corpDuty;
    private String corpInfo;
    private Integer activeFlag;
    private Integer delFlag;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String corpType;
    private String corpCode;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>corpName -> corp_name</li>
     * <li>corpAddress -> corp_address</li>
     * <li>corpUser -> corp_user</li>
     * <li>corpPhone -> corp_phone</li>
     * <li>corpEmail -> corp_email</li>
     * <li>corpDuty -> corp_duty</li>
     * <li>corpInfo -> corp_info</li>
     * <li>activeFlag -> active_flag</li>
     * <li>delFlag -> del_flag</li>
     * <li>insertTime -> insert_time</li>
     * <li>updateTime -> update_time</li>
     * <li>corpType -> corp_type</li>
     * <li>corpCode -> corp_code</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "corpName": return "corp_name";
            case "corpAddress": return "corp_address";
            case "corpUser": return "corp_user";
            case "corpPhone": return "corp_phone";
            case "corpEmail": return "corp_email";
            case "corpDuty": return "corp_duty";
            case "corpInfo": return "corp_info";
            case "activeFlag": return "active_flag";
            case "delFlag": return "del_flag";
            case "insertTime": return "insert_time";
            case "updateTime": return "update_time";
            case "corpType": return "corp_type";
            case "corpCode": return "corp_code";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>corp_name -> corpName</li>
     * <li>corp_address -> corpAddress</li>
     * <li>corp_user -> corpUser</li>
     * <li>corp_phone -> corpPhone</li>
     * <li>corp_email -> corpEmail</li>
     * <li>corp_duty -> corpDuty</li>
     * <li>corp_info -> corpInfo</li>
     * <li>active_flag -> activeFlag</li>
     * <li>del_flag -> delFlag</li>
     * <li>insert_time -> insertTime</li>
     * <li>update_time -> updateTime</li>
     * <li>corp_type -> corpType</li>
     * <li>corp_code -> corpCode</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "corp_name": return "corpName";
            case "corp_address": return "corpAddress";
            case "corp_user": return "corpUser";
            case "corp_phone": return "corpPhone";
            case "corp_email": return "corpEmail";
            case "corp_duty": return "corpDuty";
            case "corp_info": return "corpInfo";
            case "active_flag": return "activeFlag";
            case "del_flag": return "delFlag";
            case "insert_time": return "insertTime";
            case "update_time": return "updateTime";
            case "corp_type": return "corpType";
            case "corp_code": return "corpCode";
            default: return null;
        }
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
    public String getCorpName() {
        return this.corpName;
    }

    /**  **/
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**  **/
    public String getCorpAddress() {
        return this.corpAddress;
    }

    /**  **/
    public void setCorpAddress(String corpAddress) {
        this.corpAddress = corpAddress;
    }

    /**  **/
    public String getCorpUser() {
        return this.corpUser;
    }

    /**  **/
    public void setCorpUser(String corpUser) {
        this.corpUser = corpUser;
    }

    /**  **/
    public String getCorpPhone() {
        return this.corpPhone;
    }

    /**  **/
    public void setCorpPhone(String corpPhone) {
        this.corpPhone = corpPhone;
    }

    /**  **/
    public String getCorpEmail() {
        return this.corpEmail;
    }

    /**  **/
    public void setCorpEmail(String corpEmail) {
        this.corpEmail = corpEmail;
    }

    /**  **/
    public String getCorpDuty() {
        return this.corpDuty;
    }

    /**  **/
    public void setCorpDuty(String corpDuty) {
        this.corpDuty = corpDuty;
    }

    /**  **/
    public String getCorpInfo() {
        return this.corpInfo;
    }

    /**  **/
    public void setCorpInfo(String corpInfo) {
        this.corpInfo = corpInfo;
    }

    /**  **/
    public Integer getActiveFlag() {
        return this.activeFlag;
    }

    /**  **/
    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public Date getInsertTime() {
        return this.insertTime;
    }

    /**  **/
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**  **/
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**  **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**  **/
    public String getCorpType() {
        return this.corpType;
    }

    /**  **/
    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    /**  **/
    public String getCorpCode() {
        return this.corpCode;
    }

    /**  **/
    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

}
