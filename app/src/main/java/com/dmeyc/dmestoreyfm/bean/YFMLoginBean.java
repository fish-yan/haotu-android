package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;

public class YFMLoginBean extends BaseRespBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"token_sys":"4069e8030b354070b37df3a6c4cea087","user_id":"11","img_url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","token_third":"pDHvTOOqlYvzBYjalsLk4ALgI0QoKoL/NTL+iT3lzINm1qg297pW7OjcQMYrh6m8kTvUVeroONjk95Gn9soiuA==","nick_name":"Hotu2284","role_code":"1"}
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
         * token_sys : 4069e8030b354070b37df3a6c4cea087
         * user_id : 11
         * img_url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
         * token_third : pDHvTOOqlYvzBYjalsLk4ALgI0QoKoL/NTL+iT3lzINm1qg297pW7OjcQMYrh6m8kTvUVeroONjk95Gn9soiuA==
         * nick_name : Hotu2284
         * role_code : 1
         */

        private String token_sys;
        private String user_id;
        private String img_url;
        private String token_third;
        private String nick_name;
        private String role_code;
        private String sex;

        private String isBinding;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIsBinding() {
            return isBinding;
        }

        public void setIsBinding(String isBinding) {
            this.isBinding = isBinding;
        }

        public String getToken_sys() {
            return token_sys;
        }

        public void setToken_sys(String token_sys) {
            this.token_sys = token_sys;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getToken_third() {
            return token_third;
        }

        public void setToken_third(String token_third) {
            this.token_third = token_third;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getRole_code() {
            return role_code;
        }

        public void setRole_code(String role_code) {
            this.role_code = role_code;
        }
    }
}
