package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>SAR_LAWS_ITEMS SarLawsItemsEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarLawsItemsEO extends BaseEntity {

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private String creationUser;
    private Integer validFlag;
    private String remarks;
    private String responsibleUnit;
    private String energyKind;
    private String applyArctic;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tackTime;
    private String parts;
    private String itemsName;
    private String itemsNum;
    private String lawsId;
    private String id;
    private String applyArcticShow;
    private String energyKindShow;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>creationUser -> creation_user</li>
     * <li>validFlag -> valid_flag</li>
     * <li>remarks -> remarks</li>
     * <li>responsibleUnit -> responsible_unit</li>
     * <li>energyKind -> energy_kind</li>
     * <li>applyArctic -> apply_arctic</li>
     * <li>tackTime -> tack_time</li>
     * <li>parts -> parts</li>
     * <li>itemsName -> items_name</li>
     * <li>itemsNum -> items_num</li>
     * <li>lawsId -> laws_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "creationUser": return "creation_user";
            case "validFlag": return "valid_flag";
            case "remarks": return "remarks";
            case "responsibleUnit": return "responsible_unit";
            case "energyKind": return "energy_kind";
            case "applyArctic": return "apply_arctic";
            case "tackTime": return "tack_time";
            case "parts": return "parts";
            case "itemsName": return "items_name";
            case "itemsNum": return "items_num";
            case "lawsId": return "laws_id";
            case "id": return "id";
            case "applyArcticShow": return "applyArcticShow";
            case "energyKindShow": return "energyKindShow";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>creation_user -> creationUser</li>
     * <li>valid_flag -> validFlag</li>
     * <li>remarks -> remarks</li>
     * <li>responsible_unit -> responsibleUnit</li>
     * <li>energy_kind -> energyKind</li>
     * <li>apply_arctic -> applyArctic</li>
     * <li>tack_time -> tackTime</li>
     * <li>parts -> parts</li>
     * <li>items_name -> itemsName</li>
     * <li>items_num -> itemsNum</li>
     * <li>laws_id -> lawsId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "creation_user": return "creationUser";
            case "valid_flag": return "validFlag";
            case "remarks": return "remarks";
            case "responsible_unit": return "responsibleUnit";
            case "energy_kind": return "energyKind";
            case "apply_arctic": return "applyArctic";
            case "tack_time": return "tackTime";
            case "parts": return "parts";
            case "items_name": return "itemsName";
            case "items_num": return "itemsNum";
            case "laws_id": return "lawsId";
            case "id": return "id";
            case "applyArcticShow": return "applyArcticShow";
            case "energyKindShow": return "energyKindShow";
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
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**  **/
    public String getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**  **/
    public String getResponsibleUnit() {
        return this.responsibleUnit;
    }

    /**  **/
    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
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
    public String getApplyArctic() {
        return this.applyArctic;
    }

    /**  **/
    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    /**  **/
    public Date getTackTime() {
        return this.tackTime;
    }

    /**  **/
    public void setTackTime(Date tackTime) {
        this.tackTime = tackTime;
    }

    /**  **/
    public String getParts() {
        return this.parts;
    }

    /**  **/
    public void setParts(String parts) {
        this.parts = parts;
    }

    /**  **/
    public String getItemsName() {
        return this.itemsName;
    }

    /**  **/
    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    /**  **/
    public String getItemsNum() {
        return this.itemsNum;
    }

    /**  **/
    public void setItemsNum(String itemsNum) {
        this.itemsNum = itemsNum;
    }

    /**  **/
    public String getLawsId() {
        return this.lawsId;
    }

    /**  **/
    public void setLawsId(String lawsId) {
        this.lawsId = lawsId;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    public String getApplyArcticShow() {
        return applyArcticShow;
    }

    public void setApplyArcticShow(String applyArcticShow) {
        this.applyArcticShow = applyArcticShow;
    }

    public String getEnergyKindShow() {
        return energyKindShow;
    }

    public void setEnergyKindShow(String energyKindShow) {
        this.energyKindShow = energyKindShow;
    }
}
