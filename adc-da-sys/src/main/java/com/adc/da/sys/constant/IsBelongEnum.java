package com.adc.da.sys.constant;

public enum IsBelongEnum {
    BELONG(1, "属于");

    private int value;
    private String label;

    IsBelongEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
