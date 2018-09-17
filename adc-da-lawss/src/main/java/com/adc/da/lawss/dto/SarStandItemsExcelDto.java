package com.adc.da.lawss.dto;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>SAR_STAND_ITEMS SarStandItemsEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarStandItemsExcelDto {

    @Excel(name = "条目号", orderNum = "1")
    private String itemsNum;

    @Excel(name = "条目名称", orderNum = "2")
    private String itemsName;

    @Excel(name = "涉及零部件", orderNum = "3")
    private String parts;

    @Excel(name = "特殊生效日期", orderNum = "4")
    private Date tackTime;

    @Excel(name = "适用车型", orderNum = "5")
    private String applyArctic;

    @Excel(name = "能源种类", orderNum = "6")
    private String energyKind;

    @Excel(name = "责任部门", orderNum = "7")
    private String responsibleUnit;

    @Excel(name = "备注", orderNum = "8")
    private String remarks;
    
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getResponsibleUnit() {
        return this.responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public String getEnergyKind() {
        return this.energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public String getApplyArctic() {
        return this.applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public Date getTackTime() {
        return this.tackTime;
    }

    public void setTackTime(Date tackTime) {
        this.tackTime = tackTime;
    }

    public String getParts() {
        return this.parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getItemsName() {
        return this.itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsNum() {
        return this.itemsNum;
    }

    public void setItemsNum(String itemsNum) {
        this.itemsNum = itemsNum;
    }


}
