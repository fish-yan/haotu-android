package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class AddPromotionBean implements Serializable {

    private String endDate;
    private Double discountAmount;
    private String useRule;

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getUseRule() {
        return useRule;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }
}
