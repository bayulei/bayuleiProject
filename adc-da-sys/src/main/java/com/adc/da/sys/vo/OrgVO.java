package com.adc.da.sys.vo;

import java.util.Date;

public class OrgVO extends TreeVO<OrgVO>{

	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creationTime;
	private Integer validFlag;
	private Integer isShow;
	private String parentIds;
	private String parentId;
	private String shotName;
	private String remarks;
	private Integer orgDesc;
	private String orgType;
	private String orgCode;
	private String orgName;
	private String id;

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

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	@Override
	public String getParentIds() {
		return parentIds;
	}

	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Override
	public String getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getShotName() {
		return shotName;
	}

	public void setShotName(String shotName) {
		this.shotName = shotName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(Integer orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
}
