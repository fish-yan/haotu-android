package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * create by cxg on 2019/12/7
 */
public class AccountInfoBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"id":null,"nick_name":"Hotu4682","user_logo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","sex":"1","birthday":"1991-01-01","idNo":null,"idType":null,"phoneNO":"15921084682","name":null,"isRealName":"0"}
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
         * id : null
         * nick_name : Hotu4682
         * user_logo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png
         * sex : 1
         * birthday : 1991-01-01
         * idNo : null
         * idType : null
         * phoneNO : 15921084682
         * name : null
         * isRealName : 0
         */

        private Object id;
        private String nick_name;
        private String user_logo;
        private String sex;
        private String birthday;
        private Object idNo;
        private Object idType;
        private String phoneNO;
        private Object name;
        private String isRealName;
        private String followersNo;
        private String likedNo;
        private String fansNo;
        private String autograph;
        private String isFollowered;//0是没有关注 1是关注了

        public String getIsFollowered() {
            return isFollowered;
        }

        public void setIsFollowered(String isFollowered) {
            this.isFollowered = isFollowered;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
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

        public String getIsRealName() {
            return isRealName;
        }

        public void setIsRealName(String isRealName) {
            this.isRealName = isRealName;
        }

        public String getFollowersNo() {
            return followersNo;
        }

        public void setFollowersNo(String followersNo) {
            this.followersNo = followersNo;
        }

        public String getLikedNo() {
            return likedNo;
        }

        public void setLikedNo(String likedNo) {
            this.likedNo = likedNo;
        }

        public String getFansNo() {
            return fansNo;
        }

        public void setFansNo(String fansNo) {
            this.fansNo = fansNo;
        }
    }
}
