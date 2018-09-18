package com.adc.da.sys.constant;
/***
 *  预警时间枚举
 * @MethodName:
 * @author: zhangyanduan
 * @param:
 * @return:
 * date: 2018/9/17 17:26
 */
public enum WarnTimeEnum {
    THREEMONTH("THREEMONTH","3个月"),
    SIXMONTH("SIXMONTH","6个月"),
    ONEYEAR("SIXMONTH","1年"),
    TWOYEAR("TWOYEAR","2年");

    private String label;
    private String value;

    private WarnTimeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }


}
