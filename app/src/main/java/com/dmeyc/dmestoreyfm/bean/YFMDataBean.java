package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class YFMDataBean implements Serializable {

    private String role_code;
    private String token_sys;
    private String token_third;
    private String user_id;

    public String getRole_code() {
        return role_code;
    }

    public String getToken_sys() {
        return token_sys;
    }

    public String getToken_third() {
        return token_third;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public void setToken_sys(String token_sys) {
        this.token_sys = token_sys;
    }

    public void setToken_third(String token_third) {
        this.token_third = token_third;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

        private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
