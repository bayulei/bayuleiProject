package com.adc.da.lawss.page;

import com.adc.da.base.page.BasePage;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author yangxuenan
 * @Description 法规信息
 * Date 2018/9/3 16:37
 * @Param
 * @return
 **/
public class SarLawsInfoEOPage extends BasePage {

    private Date modifyTime;
    private Date modifyTime1;
    private Date modifyTime2;
    private String modifyTimeOperator = "=";
    private Date creationTime;
    private Date creationTime1;
    private Date creationTime2;
    private String creationTimeOperator = "=";
    private String validFlag;
    private String validFlagOperator = "=";
    private String creationUser;
    private String creationUserOperator = "=";
    private String relevanceFile;
    private String relevanceFileOperator = "=";
    private String opinionFile;
    private String opinionFileOperator = "=";
    private String lawsFile;
    private String lawsFileOperator = "=";
    private String linkUri;
    private String linkUriOperator = "=";
    private String responsibleUnit;
    private String responsibleUnitOperator = "=";
    private String applyAuth;
    private String applyAuthOperator = "like";
    private String energyKind;
    private String energyKindOperator = "like";
    private String applyArctic;
    private String applyArcticOperator = "like";
    private String replaceLawsNum;
    private String replaceLawsNumOperator = "=";
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date putTime1;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date putTime2;
    private String putTimeOperator = "=";
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueTime1;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueTime2;
    private String issueTimeOperator = "=";
    private String lawsState;
    private String lawsStateOperator = "=";
    private String issueUnit;
    private String issueUnitOperator = "=";
    private String lawsName;
    private String lawsNameOperator = "like";
    private String lawsNumber;
    private String lawsNumberOperator = "like";
    private String lawsProperty;
    private String lawsPropertyOperator = "=";
    private String country;
    private String countryOperator = "=";
    private String lawsType;
    private String lawsTypeOperator = "=";
    private String id;
    private String idOperator = "=";

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime1() {
        return modifyTime1;
    }

    public void setModifyTime1(Date modifyTime1) {
        this.modifyTime1 = modifyTime1;
    }

    public Date getModifyTime2() {
        return modifyTime2;
    }

    public void setModifyTime2(Date modifyTime2) {
        this.modifyTime2 = modifyTime2;
    }

    public String getModifyTimeOperator() {
        return modifyTimeOperator;
    }

    public void setModifyTimeOperator(String modifyTimeOperator) {
        this.modifyTimeOperator = modifyTimeOperator;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationTime1() {
        return creationTime1;
    }

    public void setCreationTime1(Date creationTime1) {
        this.creationTime1 = creationTime1;
    }

    public Date getCreationTime2() {
        return creationTime2;
    }

    public void setCreationTime2(Date creationTime2) {
        this.creationTime2 = creationTime2;
    }

    public String getCreationTimeOperator() {
        return creationTimeOperator;
    }

    public void setCreationTimeOperator(String creationTimeOperator) {
        this.creationTimeOperator = creationTimeOperator;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlagOperator() {
        return validFlagOperator;
    }

    public void setValidFlagOperator(String validFlagOperator) {
        this.validFlagOperator = validFlagOperator;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getCreationUserOperator() {
        return creationUserOperator;
    }

    public void setCreationUserOperator(String creationUserOperator) {
        this.creationUserOperator = creationUserOperator;
    }

    public String getRelevanceFile() {
        return relevanceFile;
    }

    public void setRelevanceFile(String relevanceFile) {
        this.relevanceFile = relevanceFile;
    }

    public String getRelevanceFileOperator() {
        return relevanceFileOperator;
    }

    public void setRelevanceFileOperator(String relevanceFileOperator) {
        this.relevanceFileOperator = relevanceFileOperator;
    }

    public String getOpinionFile() {
        return opinionFile;
    }

    public void setOpinionFile(String opinionFile) {
        this.opinionFile = opinionFile;
    }

    public String getOpinionFileOperator() {
        return opinionFileOperator;
    }

    public void setOpinionFileOperator(String opinionFileOperator) {
        this.opinionFileOperator = opinionFileOperator;
    }

    public String getLawsFile() {
        return lawsFile;
    }

    public void setLawsFile(String lawsFile) {
        this.lawsFile = lawsFile;
    }

    public String getLawsFileOperator() {
        return lawsFileOperator;
    }

    public void setLawsFileOperator(String lawsFileOperator) {
        this.lawsFileOperator = lawsFileOperator;
    }

    public String getLinkUri() {
        return linkUri;
    }

    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }

    public String getLinkUriOperator() {
        return linkUriOperator;
    }

    public void setLinkUriOperator(String linkUriOperator) {
        this.linkUriOperator = linkUriOperator;
    }

    public String getResponsibleUnit() {
        return responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public String getResponsibleUnitOperator() {
        return responsibleUnitOperator;
    }

    public void setResponsibleUnitOperator(String responsibleUnitOperator) {
        this.responsibleUnitOperator = responsibleUnitOperator;
    }

    public String getApplyAuth() {
        return applyAuth;
    }

    public void setApplyAuth(String applyAuth) {
        this.applyAuth = applyAuth;
    }

    public String getApplyAuthOperator() {
        return applyAuthOperator;
    }

    public void setApplyAuthOperator(String applyAuthOperator) {
        this.applyAuthOperator = applyAuthOperator;
    }

    public String getEnergyKind() {
        return energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public String getEnergyKindOperator() {
        return energyKindOperator;
    }

    public void setEnergyKindOperator(String energyKindOperator) {
        this.energyKindOperator = energyKindOperator;
    }

    public String getApplyArctic() {
        return applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public String getApplyArcticOperator() {
        return applyArcticOperator;
    }

    public void setApplyArcticOperator(String applyArcticOperator) {
        this.applyArcticOperator = applyArcticOperator;
    }

    public String getReplaceLawsNum() {
        return replaceLawsNum;
    }

    public void setReplaceLawsNum(String replaceLawsNum) {
        this.replaceLawsNum = replaceLawsNum;
    }

    public String getReplaceLawsNumOperator() {
        return replaceLawsNumOperator;
    }

    public void setReplaceLawsNumOperator(String replaceLawsNumOperator) {
        this.replaceLawsNumOperator = replaceLawsNumOperator;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public Date getPutTime1() {
        return putTime1;
    }

    public void setPutTime1(Date putTime1) {
        this.putTime1 = putTime1;
    }

    public Date getPutTime2() {
        return putTime2;
    }

    public void setPutTime2(Date putTime2) {
        this.putTime2 = putTime2;
    }

    public String getPutTimeOperator() {
        return putTimeOperator;
    }

    public void setPutTimeOperator(String putTimeOperator) {
        this.putTimeOperator = putTimeOperator;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Date getIssueTime1() {
        return issueTime1;
    }

    public void setIssueTime1(Date issueTime1) {
        this.issueTime1 = issueTime1;
    }

    public Date getIssueTime2() {
        return issueTime2;
    }

    public void setIssueTime2(Date issueTime2) {
        this.issueTime2 = issueTime2;
    }

    public String getIssueTimeOperator() {
        return issueTimeOperator;
    }

    public void setIssueTimeOperator(String issueTimeOperator) {
        this.issueTimeOperator = issueTimeOperator;
    }

    public String getLawsState() {
        return lawsState;
    }

    public void setLawsState(String lawsState) {
        this.lawsState = lawsState;
    }

    public String getLawsStateOperator() {
        return lawsStateOperator;
    }

    public void setLawsStateOperator(String lawsStateOperator) {
        this.lawsStateOperator = lawsStateOperator;
    }

    public String getIssueUnit() {
        return issueUnit;
    }

    public void setIssueUnit(String issueUnit) {
        this.issueUnit = issueUnit;
    }

    public String getIssueUnitOperator() {
        return issueUnitOperator;
    }

    public void setIssueUnitOperator(String issueUnitOperator) {
        this.issueUnitOperator = issueUnitOperator;
    }

    public String getLawsName() {
        return lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }

    public String getLawsNameOperator() {
        return lawsNameOperator;
    }

    public void setLawsNameOperator(String lawsNameOperator) {
        this.lawsNameOperator = lawsNameOperator;
    }

    public String getLawsNumber() {
        return lawsNumber;
    }

    public void setLawsNumber(String lawsNumber) {
        this.lawsNumber = lawsNumber;
    }

    public String getLawsNumberOperator() {
        return lawsNumberOperator;
    }

    public void setLawsNumberOperator(String lawsNumberOperator) {
        this.lawsNumberOperator = lawsNumberOperator;
    }

    public String getLawsProperty() {
        return lawsProperty;
    }

    public void setLawsProperty(String lawsProperty) {
        this.lawsProperty = lawsProperty;
    }

    public String getLawsPropertyOperator() {
        return lawsPropertyOperator;
    }

    public void setLawsPropertyOperator(String lawsPropertyOperator) {
        this.lawsPropertyOperator = lawsPropertyOperator;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryOperator() {
        return countryOperator;
    }

    public void setCountryOperator(String countryOperator) {
        this.countryOperator = countryOperator;
    }

    public String getLawsType() {
        return lawsType;
    }

    public void setLawsType(String lawsType) {
        this.lawsType = lawsType;
    }

    public String getLawsTypeOperator() {
        return lawsTypeOperator;
    }

    public void setLawsTypeOperator(String lawsTypeOperator) {
        this.lawsTypeOperator = lawsTypeOperator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }
}
