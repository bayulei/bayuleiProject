package com.adc.da.sys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDatas {
	
	/** 
     * @param 待验证的字符串 
     * @return 如果是符合邮箱格式的字符串,返回<b>true</b>,否则为<b>false</b> 
     */  
    public static boolean isEmail(String str) {
        //String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        String regex = "([a-zA-Z0-9_-])*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+(com|cn|net|org)";
        return match(regex, str);
    }
    
    /**
     * 汉字和字母
     * @param str
     * @return
     */
    public static boolean isenOrch(String str) {
    	String pattern = "[\u4e00-\u9fa5A-Za-z]+";
    	return match(pattern, str);
    }
    
    /**
     * 汉字，字母，括号，-，下划线
     * @param str
     * @return
     */
    public static boolean isDuty(String str) {
    	String pattern = "[\u4e00-\u9fa5A-Za-z0-9_\\-\\(\\)\\（\\）]+";
    	return match(pattern, str);
    }
    
    /**
     * 是否是手机号
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
    	String pattern = "0?(13|14|15|18)[0-9]{9}";
    	return match(pattern, str);
    }
    
    /** 
     * @param regex 正则表达式字符串 
     * @param str   要匹配的字符串 
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false; 
     */  
    private static boolean match(String regex ,String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher(str);
        return matcher.matches();
    }  
	
    public static boolean isNumber(String str) {
    	String pattern = "-?[1-9]\\d*";
    	return match(pattern, str);
    }


    /**
     * 汉字、字母、（）
     *
     * @MethodName:isLetterOrChineseOrChar1
     * @author: DuYunbao
     * @date: 2018/5/30 15:53
     */
    public static boolean isLetterOrChineseOrChar1(String str) {
        String pattern = "^[\\u4E00-\\u9FA5A-Za-z\\（\\）]+$";
        return !str.matches(pattern);
    }

    /**
     * 汉字、数字、字母、（）、-、下划线
     *
     * @MethodName:isChineseOrNumberOrLetterOrUnderlineOrChar
     * @author: DuYunbao
     * @date: 2018/5/30 16:45
     */
    public static boolean isChineseOrNumberOrLetterOrUnderlineOrChar(String str) {
        String regex = "^[\\u4E00-\\u9FA5A-Za-z0-9_\\-\\（\\）]+$";
        return !str.matches(regex);
    }


    public static void main(String[] args) {
		String aa = "9aa";
		System.out.println(isNumber(aa));
	}
}
