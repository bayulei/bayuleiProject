package com.adc.da.sys.constant;

public enum PersonModelEnum {

	MSG("MSG","我的动态"),COLLECT("COLLECT","我的收藏"),SHARE("SHARE","我的推送"),COOKIES("COOKIES","我的浏览");
	
	private String value;
	private String lable;
	private PersonModelEnum(String value, String lable) {
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
