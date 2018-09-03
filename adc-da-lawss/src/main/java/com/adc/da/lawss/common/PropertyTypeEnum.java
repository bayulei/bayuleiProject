package com.adc.da.lawss.common;

/**
 * 标准库参数类型枚举设置
 * @author gaoyan
 * date 2018/09/03
 */
public enum PropertyTypeEnum {

	APPLY_ARCTIC("APPLY_ARCTIC","适用车型"),ENERGY_KIND("ENERGY_KIND","能源种类"),APPLY_AUTH("APPLY_AUTH","适用认证"),CATEGORY("CATEGORY","所属类别");
	
	private String value;
	private String lable;
	
	private PropertyTypeEnum(String value, String lable) {
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
