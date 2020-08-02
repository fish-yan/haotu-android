package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class BankDataBean implements Serializable {

    /**
     * code : 200
     * data : [{"bank_account":"621********0258","bank_logo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1SIISAXEhiAAAIvmeYG5o814.ico","bank_name":"工商银行","id":21}]
     * msg : 操作成功
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bank_account : 621********0258
         * bank_logo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1SIISAXEhiAAAIvmeYG5o814.ico
         * bank_name : 工商银行
         * id : 21
         */

        private String bank_account;
        private String bank_logo;
        private String bank_name;
        private int id;

        public String getBank_account() {
            return bank_account;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
