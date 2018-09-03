package com.adc.da.sys.constant;

public enum ValueStateEnum {
	
	VALUE_TRUE(0,"TRUE"),VALUE_FALSE(1,"FALSE");
	
	private int value;
	private String lable;
	
	
	private ValueStateEnum(int value, String lable) {
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
