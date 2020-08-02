package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CreatCommEditeBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_id":6,"group_name":"红红火火","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","province":"上海市","city":"上海市","area":"黄浦区","activity_venue_address":"国定东路","notice":"那种","remark":"家家户户哈哈哈哈后悔哈哈哈哈","isExamine":"0","phoneNo":"17182701034","groupType":"1","username":null,"battleNumber":0}
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
         * group_id : 6
         * group_name : 红红火火
         * group_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg
         * province : 上海市
         * city : 上海市
         * area : 黄浦区
         * activity_venue_address : 国定东路
         * notice : 那种
         * remark : 家家户户哈哈哈哈后悔哈哈哈哈
         * isExamine : 0
         * phoneNo : 17182701034
         * groupType : 1
         * username : null
         * battleNumber : 0
         */

        private int group_id;
        private String group_name;
        private String group_logo;
        private String province;
        private String city;
        private String area;
        private String activity_venue_address;
        private String notice;
        private String remark;
        private String isExamine;
        private String phoneNo;
        private String groupType;
        private String username;
        private int battleNumber;

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getActivity_venue_address() {
            return activity_venue_address;
        }

        public void setActivity_venue_address(String activity_venue_address) {
            this.activity_venue_address = activity_venue_address;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIsExamine() {
            return isExamine;
        }

        public void setIsExamine(String isExamine) {
            this.isExamine = isExamine;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getBattleNumber() {
            return battleNumber;
        }

        public void setBattleNumber(int battleNumber) {
            this.battleNumber = battleNumber;
        }
    }
}
