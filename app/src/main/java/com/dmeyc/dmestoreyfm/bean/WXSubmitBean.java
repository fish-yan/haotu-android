package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;

public class WXSubmitBean  extends BaseRespBean {

    /**
     * code : 200
     * data : {"merchantOrderNo":"332","orderCreateTime":"2019-08-06 11:46:49","payInfo":{"nonceStr":"yaP3wJFHmdi9U5Z82rvV0leQSaOZZ1WC","packageMsg":"Sign=WXPay","partnerid":"1505475681","prepayid":"wx06114649509643c11a349a891791623000","sign":"HFgwF0S/WZt9+htcFkbtmQchMthju5R+aQzFcIon614frVSfQ5cx0X29OkWByo64FVnVjVrO21RrR+o7H7F/tobNjbmNjo/fWJUwLTp/74QZsR/xKi5eMwwFSAczcJSOh9abXk66bVzm5qsYus9P5dAvN+omhQRG153qEphIZ57D1j2PY0vd6Hq5t6/Gd6TLdd41DBCy/YB4+ETRSFgVPFMD6IqH10CMaHQdc+2+fTzrJ2CWrl0SCPjn7HoQWg7AVuJ9EaEIJZzDQmZZvV//YH+ZNJ4SxhR7Cb2L+0RmicxiiOL0AzFXOPZ5cp88U4DN8D0gRwP5bPIiZ02BrrXC2A==","timeStamp":"1565063209"},"returnCode":"00","sftOrderNo":"C20190806114648675233"}
     * msg : 操作成功
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * merchantOrderNo : 332
         * orderCreateTime : 2019-08-06 11:46:49
         * payInfo : {"nonceStr":"yaP3wJFHmdi9U5Z82rvV0leQSaOZZ1WC","packageMsg":"Sign=WXPay","partnerid":"1505475681","prepayid":"wx06114649509643c11a349a891791623000","sign":"HFgwF0S/WZt9+htcFkbtmQchMthju5R+aQzFcIon614frVSfQ5cx0X29OkWByo64FVnVjVrO21RrR+o7H7F/tobNjbmNjo/fWJUwLTp/74QZsR/xKi5eMwwFSAczcJSOh9abXk66bVzm5qsYus9P5dAvN+omhQRG153qEphIZ57D1j2PY0vd6Hq5t6/Gd6TLdd41DBCy/YB4+ETRSFgVPFMD6IqH10CMaHQdc+2+fTzrJ2CWrl0SCPjn7HoQWg7AVuJ9EaEIJZzDQmZZvV//YH+ZNJ4SxhR7Cb2L+0RmicxiiOL0AzFXOPZ5cp88U4DN8D0gRwP5bPIiZ02BrrXC2A==","timeStamp":"1565063209"}
         * returnCode : 00
         * sftOrderNo : C20190806114648675233
         */

        private String merchantOrderNo;
        private String orderCreateTime;
        private PayInfoBean payInfo;
        private String returnCode;
        private String sftOrderNo;

        public String getMerchantOrderNo() {
            return merchantOrderNo;
        }

        public void setMerchantOrderNo(String merchantOrderNo) {
            this.merchantOrderNo = merchantOrderNo;
        }

        public String getOrderCreateTime() {
            return orderCreateTime;
        }

        public void setOrderCreateTime(String orderCreateTime) {
            this.orderCreateTime = orderCreateTime;
        }

        public PayInfoBean getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(PayInfoBean payInfo) {
            this.payInfo = payInfo;
        }

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getSftOrderNo() {
            return sftOrderNo;
        }

        public void setSftOrderNo(String sftOrderNo) {
            this.sftOrderNo = sftOrderNo;
        }

        public static class PayInfoBean {
            /**
             * nonceStr : yaP3wJFHmdi9U5Z82rvV0leQSaOZZ1WC
             * packageMsg : Sign=WXPay
             * partnerid : 1505475681
             * prepayid : wx06114649509643c11a349a891791623000
             * sign : HFgwF0S/WZt9+htcFkbtmQchMthju5R+aQzFcIon614frVSfQ5cx0X29OkWByo64FVnVjVrO21RrR+o7H7F/tobNjbmNjo/fWJUwLTp/74QZsR/xKi5eMwwFSAczcJSOh9abXk66bVzm5qsYus9P5dAvN+omhQRG153qEphIZ57D1j2PY0vd6Hq5t6/Gd6TLdd41DBCy/YB4+ETRSFgVPFMD6IqH10CMaHQdc+2+fTzrJ2CWrl0SCPjn7HoQWg7AVuJ9EaEIJZzDQmZZvV//YH+ZNJ4SxhR7Cb2L+0RmicxiiOL0AzFXOPZ5cp88U4DN8D0gRwP5bPIiZ02BrrXC2A==
             * timeStamp : 1565063209
             */

            private String nonceStr;
            private String packageMsg;
            private String partnerid;
            private String prepayid;
            private String sign;
            private String timeStamp;

            public String getNonceStr() {
                return nonceStr;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public String getPackageMsg() {
                return packageMsg;
            }

            public void setPackageMsg(String packageMsg) {
                this.packageMsg = packageMsg;
            }

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

            public String getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(String timeStamp) {
                this.timeStamp = timeStamp;
            }
        }
    }
}
