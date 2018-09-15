package com.adc.da.lawss.page;

import com.adc.da.base.page.BasePage;

import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>SAR_STANDARDS_INFO SarStandardsInfoEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarStandardsInfoEOPage extends BasePage {

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
    private String remark;
    private String remarkOperator = "=";
    private String category;
    private String categoryOperator = "=";
    private String responsibleUnit;
    private String responsibleUnitOperator = "=";
    private String synopsis;
    private String synopsisOperator = "=";
    private String tags;
    private String tagsOperator = "=";
    private String relevanceFile;
    private String relevanceFileOperator = "=";
    private String approvalFile;
    private String approvalFileOperator = "=";
    private String sentScreenFile;
    private String sentScreenFileOperator = "=";
    private String opinionFile;
    private String opinionFileOperator = "=";
    private String draftFile;
    private String draftFileOperator = "=";
    private String standModifyFile;
    private String standModifyFileOperator = "=";
    private String standFile;
    private String standFileOperator = "=";
    private String draftUser;
    private String draftUserOperator = "=";
    private String draftingUnit;
    private String draftingUnitOperator = "=";
    private String newproductPutTime;
    private String newproductPutTime1;
    private String newproductPutTime2;
    private String newproductPutTimeOperator = "=";
    private String productPutTime;
    private String productPutTime1;
    private String productPutTime2;
    private String productPutTimeOperator = "=";
    private String newcarPutTime;
    private String newcarPutTime1;
    private String newcarPutTime2;
    private String newcarPutTimeOperator = "=";
    private String putTime;
    private String putTime1;
    private String putTime2;
    private String putTimeOperator = "=";
    private String issueTime;
    private String issueTime1;
    private String issueTime2;
    private String issueTimeOperator = "=";
    private String applyAuth;
    private String applyAuthOperator = "=";
    private String emergyKind;
    private String emergyKindOperator = "=";
    private String adoptExtent;
    private String adoptExtentOperator = "=";
    private String interStandNum;
    private String interStandNumOperator = "=";
    private String replacedStandNum;
    private String replacedStandNumOperator = "=";
    private String replaceStandNum;
    private String replaceStandNumOperator = "=";
    private String standNature;
    private String standNatureOperator = "=";
    private String standState;
    private String standStateOperator = "=";
    private String standEnName;
    private String standEnNameOperator = "=";
    //标准名称
    private String standName;
    private String standNameOperator = "=";
    private String standYear;
    private String standYearOperator = "=";
    //标准编号
    private String standNumber;
    private String standNumberOperator = "=";
    private String applyArctic;
    private String applyArcticOperator = "=";
    //标准种类
    private String standSort;
    private String standSortOperator = "=";
    private String country;
    private String countryOperator = "=";
    private String standType;
    private String standTypeOperator = "=";
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkOperator() {
        return this.remarkOperator;
    }

    public void setRemarkOperator(String remarkOperator) {
        this.remarkOperator = remarkOperator;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryOperator() {
        return this.categoryOperator;
    }

    public void setCategoryOperator(String categoryOperator) {
        this.categoryOperator = categoryOperator;
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

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getSynopsisOperator() {
        return this.synopsisOperator;
    }

    public void setSynopsisOperator(String synopsisOperator) {
        this.synopsisOperator = synopsisOperator;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagsOperator() {
        return this.tagsOperator;
    }

    public void setTagsOperator(String tagsOperator) {
        this.tagsOperator = tagsOperator;
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

    public String getApprovalFile() {
        return this.approvalFile;
    }

    public void setApprovalFile(String approvalFile) {
        this.approvalFile = approvalFile;
    }

    public String getApprovalFileOperator() {
        return this.approvalFileOperator;
    }

    public void setApprovalFileOperator(String approvalFileOperator) {
        this.approvalFileOperator = approvalFileOperator;
    }

    public String getSentScreenFile() {
        return this.sentScreenFile;
    }

    public void setSentScreenFile(String sentScreenFile) {
        this.sentScreenFile = sentScreenFile;
    }

    public String getSentScreenFileOperator() {
        return this.sentScreenFileOperator;
    }

    public void setSentScreenFileOperator(String sentScreenFileOperator) {
        this.sentScreenFileOperator = sentScreenFileOperator;
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

    public String getDraftFile() {
        return this.draftFile;
    }

    public void setDraftFile(String draftFile) {
        this.draftFile = draftFile;
    }

    public String getDraftFileOperator() {
        return this.draftFileOperator;
    }

    public void setDraftFileOperator(String draftFileOperator) {
        this.draftFileOperator = draftFileOperator;
    }

    public String getStandModifyFile() {
        return this.standModifyFile;
    }

    public void setStandModifyFile(String standModifyFile) {
        this.standModifyFile = standModifyFile;
    }

    public String getStandModifyFileOperator() {
        return this.standModifyFileOperator;
    }

    public void setStandModifyFileOperator(String standModifyFileOperator) {
        this.standModifyFileOperator = standModifyFileOperator;
    }

    public String getStandFile() {
        return this.standFile;
    }

    public void setStandFile(String standFile) {
        this.standFile = standFile;
    }

    public String getStandFileOperator() {
        return this.standFileOperator;
    }

    public void setStandFileOperator(String standFileOperator) {
        this.standFileOperator = standFileOperator;
    }

    public String getDraftUser() {
        return this.draftUser;
    }

    public void setDraftUser(String draftUser) {
        this.draftUser = draftUser;
    }

    public String getDraftUserOperator() {
        return this.draftUserOperator;
    }

    public void setDraftUserOperator(String draftUserOperator) {
        this.draftUserOperator = draftUserOperator;
    }

    public String getDraftingUnit() {
        return this.draftingUnit;
    }

    public void setDraftingUnit(String draftingUnit) {
        this.draftingUnit = draftingUnit;
    }

    public String getDraftingUnitOperator() {
        return this.draftingUnitOperator;
    }

    public void setDraftingUnitOperator(String draftingUnitOperator) {
        this.draftingUnitOperator = draftingUnitOperator;
    }

    public String getNewproductPutTime() {
        return this.newproductPutTime;
    }

    public void setNewproductPutTime(String newproductPutTime) {
        this.newproductPutTime = newproductPutTime;
    }

    public String getNewproductPutTime1() {
        return this.newproductPutTime1;
    }

    public void setNewproductPutTime1(String newproductPutTime1) {
        this.newproductPutTime1 = newproductPutTime1;
    }

    public String getNewproductPutTime2() {
        return this.newproductPutTime2;
    }

    public void setNewproductPutTime2(String newproductPutTime2) {
        this.newproductPutTime2 = newproductPutTime2;
    }

    public String getNewproductPutTimeOperator() {
        return this.newproductPutTimeOperator;
    }

    public void setNewproductPutTimeOperator(String newproductPutTimeOperator) {
        this.newproductPutTimeOperator = newproductPutTimeOperator;
    }

    public String getProductPutTime() {
        return this.productPutTime;
    }

    public void setProductPutTime(String productPutTime) {
        this.productPutTime = productPutTime;
    }

    public String getProductPutTime1() {
        return this.productPutTime1;
    }

    public void setProductPutTime1(String productPutTime1) {
        this.productPutTime1 = productPutTime1;
    }

    public String getProductPutTime2() {
        return this.productPutTime2;
    }

    public void setProductPutTime2(String productPutTime2) {
        this.productPutTime2 = productPutTime2;
    }

    public String getProductPutTimeOperator() {
        return this.productPutTimeOperator;
    }

    public void setProductPutTimeOperator(String productPutTimeOperator) {
        this.productPutTimeOperator = productPutTimeOperator;
    }

    public String getNewcarPutTime() {
        return this.newcarPutTime;
    }

    public void setNewcarPutTime(String newcarPutTime) {
        this.newcarPutTime = newcarPutTime;
    }

    public String getNewcarPutTime1() {
        return this.newcarPutTime1;
    }

    public void setNewcarPutTime1(String newcarPutTime1) {
        this.newcarPutTime1 = newcarPutTime1;
    }

    public String getNewcarPutTime2() {
        return this.newcarPutTime2;
    }

    public void setNewcarPutTime2(String newcarPutTime2) {
        this.newcarPutTime2 = newcarPutTime2;
    }

    public String getNewcarPutTimeOperator() {
        return this.newcarPutTimeOperator;
    }

    public void setNewcarPutTimeOperator(String newcarPutTimeOperator) {
        this.newcarPutTimeOperator = newcarPutTimeOperator;
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

    public String getEmergyKind() {
        return this.emergyKind;
    }

    public void setEmergyKind(String emergyKind) {
        this.emergyKind = emergyKind;
    }

    public String getEmergyKindOperator() {
        return this.emergyKindOperator;
    }

    public void setEmergyKindOperator(String emergyKindOperator) {
        this.emergyKindOperator = emergyKindOperator;
    }

    public String getAdoptExtent() {
        return this.adoptExtent;
    }

    public void setAdoptExtent(String adoptExtent) {
        this.adoptExtent = adoptExtent;
    }

    public String getAdoptExtentOperator() {
        return this.adoptExtentOperator;
    }

    public void setAdoptExtentOperator(String adoptExtentOperator) {
        this.adoptExtentOperator = adoptExtentOperator;
    }

    public String getInterStandNum() {
        return this.interStandNum;
    }

    public void setInterStandNum(String interStandNum) {
        this.interStandNum = interStandNum;
    }

    public String getInterStandNumOperator() {
        return this.interStandNumOperator;
    }

    public void setInterStandNumOperator(String interStandNumOperator) {
        this.interStandNumOperator = interStandNumOperator;
    }

    public String getReplacedStandNum() {
        return this.replacedStandNum;
    }

    public void setReplacedStandNum(String replacedStandNum) {
        this.replacedStandNum = replacedStandNum;
    }

    public String getReplacedStandNumOperator() {
        return this.replacedStandNumOperator;
    }

    public void setReplacedStandNumOperator(String replacedStandNumOperator) {
        this.replacedStandNumOperator = replacedStandNumOperator;
    }

    public String getReplaceStandNum() {
        return this.replaceStandNum;
    }

    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    public String getReplaceStandNumOperator() {
        return this.replaceStandNumOperator;
    }

    public void setReplaceStandNumOperator(String replaceStandNumOperator) {
        this.replaceStandNumOperator = replaceStandNumOperator;
    }

    public String getStandNature() {
        return this.standNature;
    }

    public void setStandNature(String standNature) {
        this.standNature = standNature;
    }

    public String getStandNatureOperator() {
        return this.standNatureOperator;
    }

    public void setStandNatureOperator(String standNatureOperator) {
        this.standNatureOperator = standNatureOperator;
    }

    public String getStandState() {
        return this.standState;
    }

    public void setStandState(String standState) {
        this.standState = standState;
    }

    public String getStandStateOperator() {
        return this.standStateOperator;
    }

    public void setStandStateOperator(String standStateOperator) {
        this.standStateOperator = standStateOperator;
    }

    public String getStandEnName() {
        return this.standEnName;
    }

    public void setStandEnName(String standEnName) {
        this.standEnName = standEnName;
    }

    public String getStandEnNameOperator() {
        return this.standEnNameOperator;
    }

    public void setStandEnNameOperator(String standEnNameOperator) {
        this.standEnNameOperator = standEnNameOperator;
    }

    public String getStandName() {
        return this.standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getStandNameOperator() {
        return this.standNameOperator;
    }

    public void setStandNameOperator(String standNameOperator) {
        this.standNameOperator = standNameOperator;
    }

    public String getStandYear() {
        return this.standYear;
    }

    public void setStandYear(String standYear) {
        this.standYear = standYear;
    }

    public String getStandYearOperator() {
        return this.standYearOperator;
    }

    public void setStandYearOperator(String standYearOperator) {
        this.standYearOperator = standYearOperator;
    }

    public String getStandNumber() {
        return this.standNumber;
    }

    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
    }

    public String getStandNumberOperator() {
        return this.standNumberOperator;
    }

    public void setStandNumberOperator(String standNumberOperator) {
        this.standNumberOperator = standNumberOperator;
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

    public String getStandSort() {
        return this.standSort;
    }

    public void setStandSort(String standSort) {
        this.standSort = standSort;
    }

    public String getStandSortOperator() {
        return this.standSortOperator;
    }

    public void setStandSortOperator(String standSortOperator) {
        this.standSortOperator = standSortOperator;
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

    public String getStandType() {
        return this.standType;
    }

    public void setStandType(String standType) {
        this.standType = standType;
    }

    public String getStandTypeOperator() {
        return this.standTypeOperator;
    }

    public void setStandTypeOperator(String standTypeOperator) {
        this.standTypeOperator = standTypeOperator;
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

    private String  menuId;
    private List<String> applyArctics;
    private List<String> replaceStandNums;
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<String> getApplyArctics() {
        return applyArctics;
    }

    public void setApplyArctics(List<String> applyArctics) {
        this.applyArctics = applyArctics;
    }

    public List<String> getReplaceStandNums() {
        return replaceStandNums;
    }

    public void setReplaceStandNums(List<String> replaceStandNums) {
        this.replaceStandNums = replaceStandNums;
    }
}
