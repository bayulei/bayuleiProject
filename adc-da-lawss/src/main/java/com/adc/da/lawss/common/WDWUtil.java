package com.adc.da.lawss.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @des : excel版本判断类
 * @author: duyunbao
 * @email: 1114808306@qq.com
 * @date 2017/10/27 16:59
 **/
public class WDWUtil {

    /**
     * @method_name: isExcel2003
     * @des :是否是2003的excel，返回true是2003
     * @author: duyunbao
     * @param: [filePath]
     * @return: boolean
     * @date: 2017/10/27 16:57
     **/
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @method_name: isExcel2007
     * @des :  是否是2007的excel，返回true是2007
     * @author: duyunbao
     * @param: [filePath]
     * @return: boolean
     * @date: 2017/10/27 16:57
     **/
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }




    /**
     * 字母或数字
     *
     * @MethodName:isLetterDigitOrChinese
     * @author: DuYunbao
     * @date: 2018/5/30 15:26
     */
    public static boolean isLetterOrNumber(String str) {
        String regex = "^(\\d|[a-zA-Z])+$";
        return !str.matches(regex);
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
     * 汉字、字母、数字
     *
     * @MethodName:isLetterOrChineseOrNumber
     * @author: DuYunbao
     * @date: 2018/5/30 15:56
     */
    public static boolean isLetterOrChineseOrNumber(String str) {
        String regex = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$";
        return !str.matches(regex);
    }

    /**
     * 汉字、字母、（）、-、下划线
     *
     * @MethodName:isChineseOrLetterOrUnderlineOrChar1
     * @author: DuYunbao
     * @date: 2018/5/30 16:03
     */
    public static boolean isChineseOrLetterOrUnderlineOrChar1(String str) {
        String regex = "^[\\u4E00-\\u9FA5A-Za-z_\\-\\（\\）]+$";
        return !str.matches(regex);
    }

    /**
     * 数字
     *
     * @MethodName:isNumbee
     * @author: DuYunbao
     * @date: 2018/5/30 16:09
     */
    public static boolean isNumber(String str) {
        String regex = "[\\d]+";
        return !str.matches(regex);
    }

    /**
     * 数字、大写字母、-
     *
     * @MethodName:isNumberOrLowerLetterOrChar1
     * @author: DuYunbao
     * @date: 2018/5/30 16:41
     */
    public static boolean isNumberOrLowerLetterOrChar1(String str) {
        String regex = "^[A-Z0-9\\-]+$";
        return !str.matches(regex);
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


    /**
     *  去除整数含有小数点
     * @MethodName:subStr
     * @author: DuYunbao
     * @date: 2018/5/31 11:19
     */
    public static String subStr(String text) {
        if (StringUtils.isNotEmpty(text)) {
            if(text.contains(".0") && text.substring(text.length()-2,text.length()).equals(".0")) {
                text = text.substring(0,text.length()-2);
            }
        }
        return text;
    }

    /**
     *  判断字符串是否是整数
     * @MethodName:isInteger
     * @author: DuYunbao
     * @date: 2018/5/22 18:00
     */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
