package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;

import java.util.Date;

public class LawsItemsImportDto {

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

    public String getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(String itemsNum) {
        this.itemsNum = itemsNum;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public Date getTackTime() {
        return tackTime;
    }

    public void setTackTime(Date tackTime) {
        this.tackTime = tackTime;
    }

    public String getApplyArctic() {
        return applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public String getEnergyKind() {
        return energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
