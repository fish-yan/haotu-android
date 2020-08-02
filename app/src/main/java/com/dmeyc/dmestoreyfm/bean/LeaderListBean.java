package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class LeaderListBean implements Serializable {

    /**
     * code : 200
     * data : [{"id":39,"identify_status":"1","nick_name":"咸咖啡"}]
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
         * id : 39
         * identify_status : 1
         * nick_name : 咸咖啡
         */

        private int id;
        private String identify_status;
        private String nick_name;
private String head_icon;
private String sex;
private int user_id;

        public String getSex() {
            return sex;
        }

        public int getUser_id() {
            return user_id;
        }

        public String getHead_icon() {
            return head_icon;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setHead_icon(String head_icon) {
            this.head_icon = head_icon;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdentify_status() {
            return identify_status;
        }

        public void setIdentify_status(String identify_status) {
            this.identify_status = identify_status;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }
    }
}
