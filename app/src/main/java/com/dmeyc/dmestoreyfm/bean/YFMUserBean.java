package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class YFMUserBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"nick_name":"张飞大笑","user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","account_amount":500,"activity_count":0,"group_count":0}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public static class DataBean {
        /**
         * nick_name : 张飞大笑
         * user_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
         * account_amount : 500
         * activity_count : 0
         * group_count : 0
         */

        private String nick_name;
        private String user_logo;
        private int account_amount;
        private int activity_count;
        private int group_count;
        private int  measure_count;

        public int getMeasure_count() {
            return measure_count;
        }

        public void setMeasure_count(int measure_count) {
            this.measure_count = measure_count;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getUser_logo() {
            return user_logo;
        }

        public void setUser_logo(String user_logo) {
            this.user_logo = user_logo;
        }

        public int getAccount_amount() {
            return account_amount;
        }

        public void setAccount_amount(int account_amount) {
            this.account_amount = account_amount;
        }

        public int getActivity_count() {
            return activity_count;
        }

        public void setActivity_count(int activity_count) {
            this.activity_count = activity_count;
        }

        public int getGroup_count() {
            return group_count;
        }

        public void setGroup_count(int group_count) {
            this.group_count = group_count;
        }
    }
}
