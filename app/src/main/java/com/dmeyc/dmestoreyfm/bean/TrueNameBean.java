package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class TrueNameBean implements Serializable {

    /**
     * code : 200
     * data : {"birthday":"5560-11-13","idNo":"555887555658445555","idType":"1","name":"还不","nick_name":"咸咖啡","phoneNO":"17182701034","sex":"1","user_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eprJeCDDj1BzR9EkiczIdZ6DkyJYMYpSojXezNTwSnXzuibRafy0MJVFK3zSic4j0fwBAjIF5yCHfh5Q/132"}
     * msg : 操作成功
     */

    private String code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * birthday : 5560-11-13
         * idNo : 555887555658445555
         * idType : 1
         * name : 还不
         * nick_name : 咸咖啡
         * phoneNO : 17182701034
         * sex : 1
         * user_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eprJeCDDj1BzR9EkiczIdZ6DkyJYMYpSojXezNTwSnXzuibRafy0MJVFK3zSic4j0fwBAjIF5yCHfh5Q/132
         */

        private String birthday;
        private String idNo;
        private String idType;
        private String name;
        private String nick_name;
        private String phoneNO;
        private String sex;
        private String user_logo;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPhoneNO() {
            return phoneNO;
        }

        public void setPhoneNO(String phoneNO) {
            this.phoneNO = phoneNO;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUser_logo() {
            return user_logo;
        }

        public void setUser_logo(String user_logo) {
            this.user_logo = user_logo;
        }
    }
}
