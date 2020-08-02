package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class YUEbean implements Serializable  {

    /**
     * code : 200
     * data : {"avaliableAmount":0,"frozenAmount":0,"totalAmount":0}
     * msg : 操作成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * avaliableAmount : 0
         * frozenAmount : 0
         * totalAmount : 0
         */

        private double avaliableAmount;
        private double frozenAmount;
        private double totalAmount;

        public double getTotalAmount() {
            return totalAmount;
        }

        public double getFrozenAmount() {
            return frozenAmount;
        }

        public double getAvaliableAmount() {
            return avaliableAmount;
        }

        public void setFrozenAmount(double frozenAmount) {
            this.frozenAmount = frozenAmount;
        }

        public void setAvaliableAmount(double avaliableAmount) {
            this.avaliableAmount = avaliableAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
