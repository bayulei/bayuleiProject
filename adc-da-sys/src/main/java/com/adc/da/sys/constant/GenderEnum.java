package com.adc.da.sys.constant;

public enum GenderEnum {
	
	MAN(0,"男"),WOMAN(1,"女");
	
	private int value;
	private String lable;
	private GenderEnum(int value, String lable) {
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
