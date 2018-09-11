package com.adc.da.activiti.vo;

import java.io.Serializable;

public class BuyApprovalVO extends ApprovalProcessVO implements Serializable {


    private static final long serialVersionUID = 3362990175004982375L;

    /**
     *  购买费用
     */
    private Integer money;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
