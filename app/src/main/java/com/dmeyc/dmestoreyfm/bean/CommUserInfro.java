package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CommUserInfro implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":null,"nick_name":"群小秘","user_logo":"http://192.168.0.104/group1/M00/00/17/wKgAaF1AZN-AU40sAAAvY7OvKA4717.png","sex":"1","birthday":null,"idNo":null,"idType":null,"phoneNO":"16638645362","name":null}
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
         * id : null
         * nick_name : 群小秘
         * user_logo : http://192.168.0.104/group1/M00/00/17/wKgAaF1AZN-AU40sAAAvY7OvKA4717.png
         * sex : 1
         * birthday : null
         * idNo : null
         * idType : null
         * phoneNO : 16638645362
         * name : null
         */

        private Object id;
        private String nick_name;
        private String user_logo;
        private String sex;
        private Object birthday;
        private Object idNo;
        private Object idType;
        private String phoneNO;
        private Object name;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getIdNo() {
            return idNo;
        }

        public void setIdNo(Object idNo) {
            this.idNo = idNo;
        }

        public Object getIdType() {
            return idType;
        }

        public void setIdType(Object idType) {
            this.idType = idType;
        }

        public String getPhoneNO() {
            return phoneNO;
        }

        public void setPhoneNO(String phoneNO) {
            this.phoneNO = phoneNO;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }
    }
}
