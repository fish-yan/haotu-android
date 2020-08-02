package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    /**
     * avatar : https://storeapi.91moshow.com:8078/images/heading/image.jpg
     * birthday : 2018-12-12
     * buyPoints : 0
     * createDate : 2018-12-12 11:31:51
     * email :
     * isLogin : true
     * mobile : 17182701034
     * modifyDate : 2018-12-12 11:31:51
     * nickname : 4FAS8yScca55KMEMNX
     * sex :
     * userId : 67
     * version : 1
     */

    private String avatar;
    private String birthday;
    private int buyPoints;
    private String createDate;
    private String email;
    private boolean isLogin;
    private String mobile;
    private String modifyDate;
    private String nickname;
    private String sex;
    private int userId;
    private int version;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getBuyPoints() {
        return buyPoints;
    }

    public void setBuyPoints(int buyPoints) {
        this.buyPoints = buyPoints;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
