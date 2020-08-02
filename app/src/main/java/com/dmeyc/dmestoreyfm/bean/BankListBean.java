package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;
import java.util.List;

public class BankListBean  extends BaseRespBean{


    /**
     * code : 200
     * data : [{"bank_account":"6214850278946623","bank_logo":"http://img1.mm131.me/pic/1695/1.jpg","bank_name":"交通银行"}]
     * msg : 操作成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bank_account : 6214850278946623
         * bank_logo : http://img1.mm131.me/pic/1695/1.jpg
         * bank_name : 交通银行
         */

        private String bank_account;
        private String bank_logo;
        private String bank_name;
         private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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
    }
}
