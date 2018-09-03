package com.adc.da.sys.constant;

public enum ValidFlagEnum {
	VALID_TRUE(0, "有效"), VALID_FALSE(1, "无效");

	private int value;
	private String lable;

	private ValidFlagEnum(int value, String lable) {
		this.value = value;
		this.lable = lable;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

}
