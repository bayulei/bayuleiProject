package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;

public class DictionaryEOPage extends BasePage{
	private String id;
	private String idOperator = "=";
    private String dictionaryCode;
    private String dictionaryCodeOperator = "=";
    private String dictionaryName;
    private String dictionaryNameOperator = "like";
	private Integer delFlag;
	private String delFlagOperator = "=";
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
	public String getDictionaryCode() {
		return dictionaryCode;
	}
	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}
	public String getDictionaryCodeOperator() {
		return dictionaryCodeOperator;
	}
	public void setDictionaryCodeOperator(String dictionaryCodeOperator) {
		this.dictionaryCodeOperator = dictionaryCodeOperator;
	}
	public String getDictionaryName() {
		return dictionaryName;
	}
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	public String getDictionaryNameOperator() {
		return dictionaryNameOperator;
	}
	public void setDictionaryNameOperator(String dictionaryNameOperator) {
		this.dictionaryNameOperator = dictionaryNameOperator;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getDelFlagOperator() {
		return delFlagOperator;
	}
	public void setDelFlagOperator(String delFlagOperator) {
		this.delFlagOperator = delFlagOperator;
	}
	
	
}
