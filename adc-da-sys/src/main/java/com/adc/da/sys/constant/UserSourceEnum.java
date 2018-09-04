package com.adc.da.sys.constant;

public enum UserSourceEnum {

	SSO_USER("SSO","SSO用户"),LOCAL_USER("LOCAL","本地用户");
	
	private String value;
	private String lable;
	private UserSourceEnum(String value, String lable) {
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
