package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>UT_CAR_NOTICE UtCarNoticeEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UtCarNoticeEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String creationUser;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date effectionDate;
    private String productCode;
    private String arcticModel;
    private String noticeBatch;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>effectionDate -> effection_date</li>
     * <li>productCode -> product_code</li>
     * <li>arcticModel -> arctic_model</li>
     * <li>noticeBatch -> notice_batch</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "effectionDate": return "effection_date";
            case "productCode": return "product_code";
            case "arcticModel": return "arctic_model";
            case "noticeBatch": return "notice_batch";
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
     * <li>creation_user -> creationUser</li>
     * <li>effection_date -> effectionDate</li>
     * <li>product_code -> productCode</li>
     * <li>arctic_model -> arcticModel</li>
     * <li>notice_batch -> noticeBatch</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "effection_date": return "effectionDate";
            case "product_code": return "productCode";
            case "arctic_model": return "arcticModel";
            case "notice_batch": return "noticeBatch";
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
    public String getCreationUser() {
        return this.creationUser;
    }

    /**  **/
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    /**  **/
    public Date getEffectionDate() {
        return this.effectionDate;
    }

    /**  **/
    public void setEffectionDate(Date effectionDate) {
        this.effectionDate = effectionDate;
    }

    /**  **/
    public String getProductCode() {
        return this.productCode;
    }

    /**  **/
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**  **/
    public String getArcticModel() {
        return this.arcticModel;
    }

    /**  **/
    public void setArcticModel(String arcticModel) {
        this.arcticModel = arcticModel;
    }

    /**  **/
    public String getNoticeBatch() {
        return this.noticeBatch;
    }

    /**  **/
    public void setNoticeBatch(String noticeBatch) {
        this.noticeBatch = noticeBatch;
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
