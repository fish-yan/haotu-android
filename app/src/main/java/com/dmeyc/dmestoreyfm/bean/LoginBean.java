package com.dmeyc.dmestoreyfm.bean;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class LoginBean {

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

        private UserBean user;
        private TokenBean token;
        private String serviceToken; //融云token

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public TokenBean getToken() {
            return token;
        }

        public void setToken(TokenBean token) {
            this.token = token;
        }

        public String getServiceToken() {
            return serviceToken;
        }

        public void setServiceToken(String serviceToken) {
            this.serviceToken = serviceToken;
        }

        public static class UserBean {

            private int userId;
            private String createDate;
            private String modifyDate;
            private String avatar;
            private String mobile;
            private String nickname;
            private String email;
            private String sex;
            private String birthday;
            private boolean isLogin;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public boolean isIsLogin() {
                return isLogin;
            }

            public void setIsLogin(boolean isLogin) {
                this.isLogin = isLogin;
            }
        }

        public static class TokenBean {

            private Object key;
            private String value;

            public Object getKey() {
                return key;
            }

            public void setKey(Object key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
