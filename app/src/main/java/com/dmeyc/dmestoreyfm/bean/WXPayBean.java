package com.dmeyc.dmestoreyfm.bean;

/**
 * Created by jockie on 2018/2/2
 * Email:jockie911@gmail.com
 */

public class WXPayBean {
    /**
     * code : 200
     * msg : 操作成功
     * data : {"appid":"wx75ad3c75ea0efbb2","noncestr":"MC4xMzg2Njc2NTM3NDk4NjE2Nzo6Rn","package":"Sign=WXPay","partnerid":"1498155492","prepayid":"wx2018020211271952c445c78c0401721447","sign":"4D69104D458E3F4F470CF17B2F4985E2","timestamp":1517542039}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appid : wx75ad3c75ea0efbb2
         * noncestr : MC4xMzg2Njc2NTM3NDk4NjE2Nzo6Rn
         * package : Sign=WXPay
         * partnerid : 1498155492
         * prepayid : wx2018020211271952c445c78c0401721447
         * sign : 4D69104D458E3F4F470CF17B2F4985E2
         * timestamp : 1517542039
         */

        private String appid;
        private String noncestr;
//        private String package;
        private String partnerid;
        private String prepayid;
        private String sign;
        private int timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

//        public String getPackageX() {
//            return packageX;
//        }
//
//        public void setPackageX(String packageX) {
//            this.packageX = packageX;
//        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}
