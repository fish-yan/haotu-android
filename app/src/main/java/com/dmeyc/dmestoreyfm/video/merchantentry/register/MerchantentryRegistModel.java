package com.dmeyc.dmestoreyfm.video.merchantentry.register;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MerchantentryRegistModel implements Serializable {

    /**
     * code : 200
     * data : {"business_token":"string","isBusiness":1}
     * msg : ok
     */

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * business_token : string
         * isBusiness : 1
         */

        @SerializedName("business_token")
        private String businessToken;
        @SerializedName("isBusiness")
        private int isBusiness;

        public String getBusinessToken() {
            return businessToken;
        }

        public void setBusinessToken(String businessToken) {
            this.businessToken = businessToken;
        }

        public int getIsBusiness() {
            return isBusiness;
        }

        public void setIsBusiness(int isBusiness) {
            this.isBusiness = isBusiness;
        }
    }
}
