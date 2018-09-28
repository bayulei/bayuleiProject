package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;

import java.util.Date;

/**
 * @Auther: renxu
 * @Date: 2018/9/28 20:59
 * @Description: 测试项目库数据导出Excel模板
 */
public class SarTestItemExportDto {

    @Excel(name = "修改时间", orderNum = "1")
    private Date modifyTime;
    @Excel(name = "创建时间", orderNum = "2")
    private Date creationTime;
    @Excel(name = "创建人", orderNum = "3")
    private String creationUser;
    @Excel(name = "能源类型", orderNum = "4")
    private String energyKind;
    @Excel(name = "适用车型", orderNum = "5")
    private String applyArctic;
    @Excel(name = "标准名称", orderNum = "6")
    private String standName;
    @Excel(name = "标准编号", orderNum = "7")
    private String standCode;
    @Excel(name = "认证类型", orderNum = "8")
    private String authType;
    @Excel(name = "试验项目名称", orderNum = "9")
    private String testItemName;
    @Excel(name = "试验对象", orderNum = "10")
    private String testObj;
    @Excel(name = "项目编码", orderNum = "11")
    private String testItemCode;

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getEnergyKind() {
        return energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public String getApplyArctic() {
        return applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getStandCode() {
        return standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getTestItemName() {
        return testItemName;
    }

    public void setTestItemName(String testItemName) {
        this.testItemName = testItemName;
    }

    public String getTestObj() {
        return testObj;
    }

    public void setTestObj(String testObj) {
        this.testObj = testObj;
    }

    public String getTestItemCode() {
        return testItemCode;
    }

    public void setTestItemCode(String testItemCode) {
        this.testItemCode = testItemCode;
    }

}
