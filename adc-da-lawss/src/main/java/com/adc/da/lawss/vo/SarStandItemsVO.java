package com.adc.da.lawss.vo;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <b>功能：</b>SAR_STAND_ITEMS SarStandItemsEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarStandItemsVO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private String creationUser;
    private Integer validFlag;
    private String remarks;
    private String responsibleUnit;
    //能源种类
    private String energyKind;
    //试用车型
    private String applyArctic;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tackTime;
    private String parts;
    private String itemsName;
    private String itemsNum;
    private String standId;
    private String id;

    public List<Map<String, Object>> getApplyArcticlist() {
        return applyArcticlist;
    }

    public void setApplyArcticlist(List<Map<String, Object>> applyArcticlist) {
        this.applyArcticlist = applyArcticlist;
    }

    public List<Map<String, Object>> getEnergyKindlist() {
        return energyKindlist;
    }

    public void setEnergyKindlist(List<Map<String, Object>> energyKindlist) {
        this.energyKindlist = energyKindlist;
    }

    private List<Map<String,Object>> applyArcticlist;
    private List<Map<String,Object>> energyKindlist;

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
    public String getStandId() {
        return this.standId;
    }

    /**  **/
    public void setStandId(String standId) {
        this.standId = standId;
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
