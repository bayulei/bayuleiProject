package com.adc.da.sys.util;

import com.adc.da.util.utils.RandomUtils;

public class UUIDUtils {

	
	public static String randomUUID10() {
		return RandomUtils.randomString(10);
	}
	
	public static String randomUUID20() {
		return RandomUtils.randomString(20);
	}
	
	public static String randomUUID(int length) {
		return RandomUtils.randomString(length);
	}
	
	
}
