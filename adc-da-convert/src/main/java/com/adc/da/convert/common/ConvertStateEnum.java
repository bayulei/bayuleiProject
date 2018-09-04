package com.adc.da.convert.common;

public enum ConvertStateEnum {
	NO_CONVERT(0,"未转换"),CONVERING(1,"转换中"),CONVERT_SUCCESS(2,"转换成功"),CONVERT_ERROR(3,"转换失败");
	
	private int value;
	private String lable;
	
	private ConvertStateEnum(int value, String lable) {
		this.value = value;
		this.lable = lable;
	}
	public int getValue() {
		return value;
	}
	public String getLable() {
		return lable;
	}
}
