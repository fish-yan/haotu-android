package com.dmeyc.dmestoreyfm.bean;

/**
 * Created by jockie on 2018/2/6
 * Email:jockie911@gmail.com
 */

public class AliPayBean {
    /**
     * alipay_trade_app_pay_response : {"code":"10000","msg":"Success","app_id":"2018010701679339","auth_app_id":"2018010701679339","charset":"utf-8","timestamp":"2018-02-06 15:49:11","total_amount":"0.01","trade_no":"2018020621001004240223418238","seller_id":"2088821482350492","out_trade_no":"786747562769715200"}
     * sign : hqRRjIOPrECNEowzTeStEHnbdmqJlAmlhqRzkEPiQ9vUXaw+/gwUOFhui0/zkVJgzhVj7G76yyYiXLbQhkgja5rXmRd1X8IJ/Y4Sm2RNha0yf6iNDRkAa2XCxW6Bv5qIIt9ZqKWQAQ+j3fB+CYeMYOXfo2fouxia/ITz0CAZbLfkond3md8mW2ISfZ3HaVAh/kfcxO9u7XDgKaW+XebsK+VfR+vfqiBkq7c4kPQr+6iKJr9JbLaYf76hda3McJH/YGDBs9H14/kyG8B1wngTYFXwg669FiAUmf4XPZEWygroBo59GRGhFObGXgZ6iW+xJlq8SPykO+gXC26nbFDJaA==
     * sign_type : RSA2
     */

    private AlipayTradeAppPayResponseBean alipay_trade_app_pay_response;
    private String sign;
    private String sign_type;

    public AlipayTradeAppPayResponseBean getAlipay_trade_app_pay_response() {
        return alipay_trade_app_pay_response;
    }

    public void setAlipay_trade_app_pay_response(AlipayTradeAppPayResponseBean alipay_trade_app_pay_response) {
        this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public static class AlipayTradeAppPayResponseBean {
        /**
         * code : 10000
         * msg : Success
         * app_id : 2018010701679339
         * auth_app_id : 2018010701679339
         * charset : utf-8
         * timestamp : 2018-02-06 15:49:11
         * total_amount : 0.01
         * trade_no : 2018020621001004240223418238
         * seller_id : 2088821482350492
         * out_trade_no : 786747562769715200
         */

        private String code;
        private String msg;
        private String app_id;
        private String auth_app_id;
        private String charset;
        private String timestamp;
        private String total_amount;
        private String trade_no;
        private String seller_id;
        private String out_trade_no;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getAuth_app_id() {
            return auth_app_id;
        }

        public void setAuth_app_id(String auth_app_id) {
            this.auth_app_id = auth_app_id;
        }

        public String getCharset() {
            return charset;
        }

        public void setCharset(String charset) {
            this.charset = charset;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }
    }
}
