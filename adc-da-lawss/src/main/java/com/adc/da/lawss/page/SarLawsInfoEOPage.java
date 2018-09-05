package com.adc.da.lawss.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;

/**
 * @Author yangxuenan
 * @Description 法规信息
 * Date 2018/9/3 16:37
 * @Param
 * @return
 **/
public class SarLawsInfoEOPage extends BasePage {

    private String modifyTime;
    private String modifyTime1;
    private String modifyTime2;
    private String modifyTimeOperator = "=";
    private String creationTime;
    private String creationTime1;
    private String creationTime2;
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
    private String putTime;
    private String putTime1;
    private String putTime2;
    private String putTimeOperator = "=";
    private String issueTime;
    private String issueTime1;
    private String issueTime2;
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

    public String getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyTime1() {
        return this.modifyTime1;
    }

    public void setModifyTime1(String modifyTime1) {
        this.modifyTime1 = modifyTime1;
    }

    public String getModifyTime2() {
        return this.modifyTime2;
    }

    public void setModifyTime2(String modifyTime2) {
        this.modifyTime2 = modifyTime2;
    }

    public String getModifyTimeOperator() {
        return this.modifyTimeOperator;
    }

    public void setModifyTimeOperator(String modifyTimeOperator) {
        this.modifyTimeOperator = modifyTimeOperator;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTime1() {
        return this.creationTime1;
    }

    public void setCreationTime1(String creationTime1) {
        this.creationTime1 = creationTime1;
    }

    public String getCreationTime2() {
        return this.creationTime2;
    }

    public void setCreationTime2(String creationTime2) {
        this.creationTime2 = creationTime2;
    }

    public String getCreationTimeOperator() {
        return this.creationTimeOperator;
    }

    public void setCreationTimeOperator(String creationTimeOperator) {
        this.creationTimeOperator = creationTimeOperator;
    }

    public String getValidFlag() {
        return this.validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlagOperator() {
        return this.validFlagOperator;
    }

    public void setValidFlagOperator(String validFlagOperator) {
        this.validFlagOperator = validFlagOperator;
    }

    public String getCreationUser() {
        return this.creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public String getCreationUserOperator() {
        return this.creationUserOperator;
    }

    public void setCreationUserOperator(String creationUserOperator) {
        this.creationUserOperator = creationUserOperator;
    }

    public String getRelevanceFile() {
        return this.relevanceFile;
    }

    public void setRelevanceFile(String relevanceFile) {
        this.relevanceFile = relevanceFile;
    }

    public String getRelevanceFileOperator() {
        return this.relevanceFileOperator;
    }

    public void setRelevanceFileOperator(String relevanceFileOperator) {
        this.relevanceFileOperator = relevanceFileOperator;
    }

    public String getOpinionFile() {
        return this.opinionFile;
    }

    public void setOpinionFile(String opinionFile) {
        this.opinionFile = opinionFile;
    }

    public String getOpinionFileOperator() {
        return this.opinionFileOperator;
    }

    public void setOpinionFileOperator(String opinionFileOperator) {
        this.opinionFileOperator = opinionFileOperator;
    }

    public String getLawsFile() {
        return this.lawsFile;
    }

    public void setLawsFile(String lawsFile) {
        this.lawsFile = lawsFile;
    }

    public String getLawsFileOperator() {
        return this.lawsFileOperator;
    }

    public void setLawsFileOperator(String lawsFileOperator) {
        this.lawsFileOperator = lawsFileOperator;
    }

    public String getLinkUri() {
        return this.linkUri;
    }

    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }

    public String getLinkUriOperator() {
        return this.linkUriOperator;
    }

    public void setLinkUriOperator(String linkUriOperator) {
        this.linkUriOperator = linkUriOperator;
    }

    public String getResponsibleUnit() {
        return this.responsibleUnit;
    }

    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    public String getResponsibleUnitOperator() {
        return this.responsibleUnitOperator;
    }

    public void setResponsibleUnitOperator(String responsibleUnitOperator) {
        this.responsibleUnitOperator = responsibleUnitOperator;
    }

    public String getApplyAuth() {
        return this.applyAuth;
    }

    public void setApplyAuth(String applyAuth) {
        this.applyAuth = applyAuth;
    }

    public String getApplyAuthOperator() {
        return this.applyAuthOperator;
    }

    public void setApplyAuthOperator(String applyAuthOperator) {
        this.applyAuthOperator = applyAuthOperator;
    }

    public String getEnergyKind() {
        return this.energyKind;
    }

    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    public String getEnergyKindOperator() {
        return this.energyKindOperator;
    }

    public void setEnergyKindOperator(String energyKindOperator) {
        this.energyKindOperator = energyKindOperator;
    }

    public String getApplyArctic() {
        return this.applyArctic;
    }

    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    public String getApplyArcticOperator() {
        return this.applyArcticOperator;
    }

    public void setApplyArcticOperator(String applyArcticOperator) {
        this.applyArcticOperator = applyArcticOperator;
    }

    public String getReplaceLawsNum() {
        return this.replaceLawsNum;
    }

    public void setReplaceLawsNum(String replaceLawsNum) {
        this.replaceLawsNum = replaceLawsNum;
    }

    public String getReplaceLawsNumOperator() {
        return this.replaceLawsNumOperator;
    }

    public void setReplaceLawsNumOperator(String replaceLawsNumOperator) {
        this.replaceLawsNumOperator = replaceLawsNumOperator;
    }

    public String getPutTime() {
        return this.putTime;
    }

    public void setPutTime(String putTime) {
        this.putTime = putTime;
    }

    public String getPutTime1() {
        return this.putTime1;
    }

    public void setPutTime1(String putTime1) {
        this.putTime1 = putTime1;
    }

    public String getPutTime2() {
        return this.putTime2;
    }

    public void setPutTime2(String putTime2) {
        this.putTime2 = putTime2;
    }

    public String getPutTimeOperator() {
        return this.putTimeOperator;
    }

    public void setPutTimeOperator(String putTimeOperator) {
        this.putTimeOperator = putTimeOperator;
    }

    public String getIssueTime() {
        return this.issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssueTime1() {
        return this.issueTime1;
    }

    public void setIssueTime1(String issueTime1) {
        this.issueTime1 = issueTime1;
    }

    public String getIssueTime2() {
        return this.issueTime2;
    }

    public void setIssueTime2(String issueTime2) {
        this.issueTime2 = issueTime2;
    }

    public String getIssueTimeOperator() {
        return this.issueTimeOperator;
    }

    public void setIssueTimeOperator(String issueTimeOperator) {
        this.issueTimeOperator = issueTimeOperator;
    }

    public String getLawsState() {
        return this.lawsState;
    }

    public void setLawsState(String lawsState) {
        this.lawsState = lawsState;
    }

    public String getLawsStateOperator() {
        return this.lawsStateOperator;
    }

    public void setLawsStateOperator(String lawsStateOperator) {
        this.lawsStateOperator = lawsStateOperator;
    }

    public String getIssueUnit() {
        return this.issueUnit;
    }

    public void setIssueUnit(String issueUnit) {
        this.issueUnit = issueUnit;
    }

    public String getIssueUnitOperator() {
        return this.issueUnitOperator;
    }

    public void setIssueUnitOperator(String issueUnitOperator) {
        this.issueUnitOperator = issueUnitOperator;
    }

    public String getLawsName() {
        return this.lawsName;
    }

    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }

    public String getLawsNameOperator() {
        return this.lawsNameOperator;
    }

    public void setLawsNameOperator(String lawsNameOperator) {
        this.lawsNameOperator = lawsNameOperator;
    }

    public String getLawsNumber() {
        return this.lawsNumber;
    }

    public void setLawsNumber(String lawsNumber) {
        this.lawsNumber = lawsNumber;
    }

    public String getLawsNumberOperator() {
        return this.lawsNumberOperator;
    }

    public void setLawsNumberOperator(String lawsNumberOperator) {
        this.lawsNumberOperator = lawsNumberOperator;
    }

    public String getLawsProperty() {
        return this.lawsProperty;
    }

    public void setLawsProperty(String lawsProperty) {
        this.lawsProperty = lawsProperty;
    }

    public String getLawsPropertyOperator() {
        return this.lawsPropertyOperator;
    }

    public void setLawsPropertyOperator(String lawsPropertyOperator) {
        this.lawsPropertyOperator = lawsPropertyOperator;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryOperator() {
        return this.countryOperator;
    }

    public void setCountryOperator(String countryOperator) {
        this.countryOperator = countryOperator;
    }

    public String getLawsType() {
        return this.lawsType;
    }

    public void setLawsType(String lawsType) {
        this.lawsType = lawsType;
    }

    public String getLawsTypeOperator() {
        return this.lawsTypeOperator;
    }

    public void setLawsTypeOperator(String lawsTypeOperator) {
        this.lawsTypeOperator = lawsTypeOperator;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

}
