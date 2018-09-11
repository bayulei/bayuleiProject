package com.adc.da.lawss.dto;

import com.adc.da.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * 条件查询符合标准数据导出Excel模板
 * @author gaoyan
 * date 2018/09/04
 */
public class SarStandExcelDto {

	//标准分类 国内国外的区别，后续根据需求更改
	//private String standType;
	//国家、地区
	@Excel(name = "国家地区", orderNum = "1")
	private String country;
	//标准类别
	@Excel(name = "标准类别", orderNum = "1")
	private String standSort;
	//适用车型 多选，用“，”隔开
	@Excel(name = "适用车型", orderNum = "1")
	private String applyArctic;
	//标准编号
	@Excel(name = "标准编号", orderNum = "1")
	private String standNumber;
	//标准年份
	@Excel(name = "标准年份", orderNum = "1")
	private String standYear;
	//标准名称
	@Excel(name = "标准名称", orderNum = "1")
	private String standName;
	//标准英文名称
	@Excel(name = "标准英文名称", orderNum = "1")
	private String standEnName;
	//标准状态
	@Excel(name = "标准状态", orderNum = "1")
	private String standState;
	//标准性质
	@Excel(name = "标准性质", orderNum = "1")
	private String standNature;
	//代替标准号   手动输入可以多个，用","隔开
	@Excel(name = "代替标准号", orderNum = "1")
	private String replaceStandNum;
	//被代替标准号
	@Excel(name = "被代替标准号", orderNum = "1")
	private String replacedStandNum;
	//采用国际标准号
	@Excel(name = "采用国际标准号", orderNum = "1")
	private String interStandNum;
	//采标程度
	@Excel(name = "采标程度", orderNum = "1")
	private String adoptExtent;
	//能源种类  多选
	@Excel(name = "能源种类", orderNum = "1")
	private String emergyKind;
	//适用认证  多选
	@Excel(name = "适用认证", orderNum = "1")
	private String applyAuth;
	//发布日期
	@Excel(name = "发布日期", orderNum = "1")
	private Date issueTime;
	//实施日期
	@Excel(name = "实施日期", orderNum = "1")
	private Date putTime;
	//新定型车实施日期
	@Excel(name = "新定型车实施日期", orderNum = "1")
	private Date newcarPutTime;
	//在产车实施日期
	@Excel(name = "在产车实施日期", orderNum = "1")
	private Date productPutTime;
	//新生产车实施日期
	@Excel(name = "新生产车实施日期", orderNum = "1")
	private Date newproductPutTime;
	//起草单位
	@Excel(name = "起草单位", orderNum = "1")
	private String draftingUnit;
	//起草人
	@Excel(name = "起草人", orderNum = "1")
	private String draftUser;
	//标准文本 支持PDF、doc、docx文件，
	@Excel(name = "标准文本", orderNum = "1")
	private String standFile;
	//标准修改单 支持PDF、doc、docx文件，
	@Excel(name = "标准修改单", orderNum = "1")
	private String standModifyFile;
	//草案 支持PDF、doc、docx文件，
	@Excel(name = "草案", orderNum = "1")
	private String draftFile;
	//征求意见稿  支持PDF、doc、docx文件，
	@Excel(name = "征求意见稿", orderNum = "1")
	private String opinionFile;
	//送审稿  支持PDF、doc、docx文件，
	@Excel(name = "送审稿", orderNum = "1")
	private String sentScreenFile;
	//报批稿  支持PDF、doc、docx文件，
	@Excel(name = "报批稿", orderNum = "1")
	private String approvalFile;
	//关联文件  支持PDF、doc、docx文件，
	@Excel(name = "关联文件", orderNum = "1")
	private String relevanceFile;
	//关键词
	@Excel(name = "关键词", orderNum = "1")
	private String tags;
	//内容摘要
	@Excel(name = "内容摘要", orderNum = "1")
	private String  synopsis;
	//责任部门
	@Excel(name = "责任部门", orderNum = "1")
	private String responsibleUnit;
	//所属类别     多选
	@Excel(name = "所属类别", orderNum = "1")
	private String category;
	//备注
	@Excel(name = "备注", orderNum = "1")
	private String remark;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStandSort() {
		return standSort;
	}

	public void setStandSort(String standSort) {
		this.standSort = standSort;
	}

	public String getApplyArctic() {
		return applyArctic;
	}

	public void setApplyArctic(String applyArctic) {
		this.applyArctic = applyArctic;
	}

	public String getStandNumber() {
		return standNumber;
	}

	public void setStandNumber(String standNumber) {
		this.standNumber = standNumber;
	}

	public String getStandYear() {
		return standYear;
	}

	public void setStandYear(String standYear) {
		this.standYear = standYear;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public String getStandEnName() {
		return standEnName;
	}

	public void setStandEnName(String standEnName) {
		this.standEnName = standEnName;
	}

	public String getStandState() {
		return standState;
	}

	public void setStandState(String standState) {
		this.standState = standState;
	}

	public String getStandNature() {
		return standNature;
	}

	public void setStandNature(String standNature) {
		this.standNature = standNature;
	}

	public String getReplaceStandNum() {
		return replaceStandNum;
	}

	public void setReplaceStandNum(String replaceStandNum) {
		this.replaceStandNum = replaceStandNum;
	}

	public String getReplacedStandNum() {
		return replacedStandNum;
	}

	public void setReplacedStandNum(String replacedStandNum) {
		this.replacedStandNum = replacedStandNum;
	}

	public String getInterStandNum() {
		return interStandNum;
	}

	public void setInterStandNum(String interStandNum) {
		this.interStandNum = interStandNum;
	}

	public String getAdoptExtent() {
		return adoptExtent;
	}

	public void setAdoptExtent(String adoptExtent) {
		this.adoptExtent = adoptExtent;
	}

	public String getEmergyKind() {
		return emergyKind;
	}

	public void setEmergyKind(String emergyKind) {
		this.emergyKind = emergyKind;
	}

	public String getApplyAuth() {
		return applyAuth;
	}

	public void setApplyAuth(String applyAuth) {
		this.applyAuth = applyAuth;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}

	public Date getNewcarPutTime() {
		return newcarPutTime;
	}

	public void setNewcarPutTime(Date newcarPutTime) {
		this.newcarPutTime = newcarPutTime;
	}

	public Date getProductPutTime() {
		return productPutTime;
	}

	public void setProductPutTime(Date productPutTime) {
		this.productPutTime = productPutTime;
	}

	public Date getNewproductPutTime() {
		return newproductPutTime;
	}

	public void setNewproductPutTime(Date newproductPutTime) {
		this.newproductPutTime = newproductPutTime;
	}

	public String getDraftingUnit() {
		return draftingUnit;
	}

	public void setDraftingUnit(String draftingUnit) {
		this.draftingUnit = draftingUnit;
	}

	public String getDraftUser() {
		return draftUser;
	}

	public void setDraftUser(String draftUser) {
		this.draftUser = draftUser;
	}

	public String getStandFile() {
		return standFile;
	}

	public void setStandFile(String standFile) {
		this.standFile = standFile;
	}

	public String getStandModifyFile() {
		return standModifyFile;
	}

	public void setStandModifyFile(String standModifyFile) {
		this.standModifyFile = standModifyFile;
	}

	public String getDraftFile() {
		return draftFile;
	}

	public void setDraftFile(String draftFile) {
		this.draftFile = draftFile;
	}

	public String getOpinionFile() {
		return opinionFile;
	}

	public void setOpinionFile(String opinionFile) {
		this.opinionFile = opinionFile;
	}

	public String getSentScreenFile() {
		return sentScreenFile;
	}

	public void setSentScreenFile(String sentScreenFile) {
		this.sentScreenFile = sentScreenFile;
	}

	public String getApprovalFile() {
		return approvalFile;
	}

	public void setApprovalFile(String approvalFile) {
		this.approvalFile = approvalFile;
	}

	public String getRelevanceFile() {
		return relevanceFile;
	}

	public void setRelevanceFile(String relevanceFile) {
		this.relevanceFile = relevanceFile;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getResponsibleUnit() {
		return responsibleUnit;
	}

	public void setResponsibleUnit(String responsibleUnit) {
		this.responsibleUnit = responsibleUnit;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
