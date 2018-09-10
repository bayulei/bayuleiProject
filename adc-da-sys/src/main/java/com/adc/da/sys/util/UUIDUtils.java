package com.adc.da.sys.util;


import java.util.Random;

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
	
	public static String getUUIDPath(String uuid){
		StringBuilder builder=new StringBuilder();
		builder.append("/");
		builder.append((uuid.substring(0, 3).hashCode())%100+"").append("/");
		builder.append((uuid.substring(7,10).hashCode())%100+"").append("/");
		builder.append((uuid.substring(11,14).hashCode())%100+"").append("/");
		return builder.toString();
	}
	
	public static String getAttTable(){
		Random rand = new Random();
		int nextInt = rand.nextInt(10)+1;
		StringBuilder builder=new StringBuilder();
		builder.append("ATT_FILE_").append(String.format("%02d", nextInt));
		return builder.toString();
	}
	
	
}
