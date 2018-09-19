package com.adc.da.lawss.common;

/**
 * 标准法规分类
 * @author gaoyan
 * date 2018/09/19
 */
public enum SorDivideEnum {

	INLAND_STAND("INLAND_STAND","国内标准库"),INLAND_LAWS("INLAND_LAWS","国内法规库"),
	FOREIGN_STAND("FOREIGN_STAND","国外标准库"),FOREIGN_LAWS("FOREIGN_LAWS","国外法规库"),
	BUSINESS_STAND("BUSINESS_STAND","企业标准库") ;

	private String value;
	private String lable;

	private SorDivideEnum(String value, String lable) {
		this.value = value;
		this.lable = lable;
	}
	public String getValue() {
		return value;
	}
	public String getLable() {
		return lable;
	}
	
	
	
	
}
