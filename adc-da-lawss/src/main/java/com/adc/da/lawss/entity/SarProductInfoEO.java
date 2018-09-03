package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_PRODUCT_INFO SarProductInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarProductInfoEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private String creationUser;
    private String carModeFile;
    private String energyKind;
    private String productType;
    private String productBrand;
    private String productName;
    private String productSet;
    private String prodectCode;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>creationUser -> creation_user</li>
     * <li>carModeFile -> car_mode_file</li>
     * <li>energyKind -> energy_kind</li>
     * <li>productType -> product_type</li>
     * <li>productBrand -> product_brand</li>
     * <li>productName -> product_name</li>
     * <li>productSet -> product_set</li>
     * <li>prodectCode -> prodect_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "creationUser": return "creation_user";
            case "carModeFile": return "car_mode_file";
            case "energyKind": return "energy_kind";
            case "productType": return "product_type";
            case "productBrand": return "product_brand";
            case "productName": return "product_name";
            case "productSet": return "product_set";
            case "prodectCode": return "prodect_code";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>creation_user -> creationUser</li>
     * <li>car_mode_file -> carModeFile</li>
     * <li>energy_kind -> energyKind</li>
     * <li>product_type -> productType</li>
     * <li>product_brand -> productBrand</li>
     * <li>product_name -> productName</li>
     * <li>product_set -> productSet</li>
     * <li>prodect_code -> prodectCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "creation_user": return "creationUser";
            case "car_mode_file": return "carModeFile";
            case "energy_kind": return "energyKind";
            case "product_type": return "productType";
            case "product_brand": return "productBrand";
            case "product_name": return "productName";
            case "product_set": return "productSet";
            case "prodect_code": return "prodectCode";
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
    public String getCreationUser() {
        return this.creationUser;
    }

    /**  **/
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    /**  **/
    public String getCarModeFile() {
        return this.carModeFile;
    }

    /**  **/
    public void setCarModeFile(String carModeFile) {
        this.carModeFile = carModeFile;
    }

    /**  **/
    public String getEnergyKind() {
        return this.energyKind;
    }

    /**  **/
    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    /**  **/
    public String getProductType() {
        return this.productType;
    }

    /**  **/
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**  **/
    public String getProductBrand() {
        return this.productBrand;
    }

    /**  **/
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    /**  **/
    public String getProductName() {
        return this.productName;
    }

    /**  **/
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**  **/
    public String getProductSet() {
        return this.productSet;
    }

    /**  **/
    public void setProductSet(String productSet) {
        this.productSet = productSet;
    }

    /**  **/
    public String getProdectCode() {
        return this.prodectCode;
    }

    /**  **/
    public void setProdectCode(String prodectCode) {
        this.prodectCode = prodectCode;
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
