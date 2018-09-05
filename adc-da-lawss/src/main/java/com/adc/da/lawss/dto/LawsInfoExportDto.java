package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;

public class LawsInfoExportDto {
    @Excel(name = "文件名称")
    private String lawsName;

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }
}
