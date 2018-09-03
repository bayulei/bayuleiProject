package com.adc.da.search.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>PART_PRODUCT PartProductEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-22 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PartProductEO extends BaseEntity {

    private String corpId;
    private String certNumber;
    private String corpModel;
    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }
    public void setCorpModel(String corpModel) {
        this.corpModel = corpModel;
    }

    public String getCorpId() {
        return corpId;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public String getCorpModel() {
        return corpModel;
    }
}
