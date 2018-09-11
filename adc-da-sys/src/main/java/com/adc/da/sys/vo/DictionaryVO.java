package com.adc.da.sys.vo;

import com.adc.da.sys.entity.DicTypeEO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DictionaryVO {
	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creationTime;
	private Integer validFlag;
	private Integer dictionarySeq;
	private String dictionaryName;
	private String dictionaryCode;
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

	public Integer getDictionarySeq() {
		return dictionarySeq;
	}

	public void setDictionarySeq(Integer dictionarySeq) {
		this.dictionarySeq = dictionarySeq;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public String getDictionaryCode() {
		return dictionaryCode;
	}

	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
