package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class RouleBean implements Serializable {

    /**
     * discountAmount : 10
     * endDate : 2019-06-31 10:20:00
     * name : 优惠券立减
     * userRule : 恭喜你，本群赠送你一张优惠券
     */

    private int discountAmount;
    private String endDate;
    private String name;
    private String userRule;

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserRule() {
        return userRule;
    }

    public void setUserRule(String userRule) {
        this.userRule = userRule;
    }
}
