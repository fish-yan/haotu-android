package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class PKPublicEditeBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_id":6,"group_name":"红红火火","activity_type":"1","activity_type_name":"羽毛球","activity_name":"家家户户","start_date":"2019-05-16 09:56:00","duration":5,"m_visitor_amount":78,"manager_user_id":2,"manager_user_name":"llife","activity_phone_no":"16856685555","province":"上海市","city":"上海市","area":"黄浦区","activity_address":"国定东路","venue_name":"测试场馆","remark":null,"status":"1","group_num":3,"brand_name":"测试用球","brand_ball_url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","min_battle":100,"max_battle":200}
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
         * activity_type : 1
         * activity_type_name : 羽毛球
         * activity_name : 家家户户
         * start_date : 2019-05-16 09:56:00
         * duration : 5
         * m_visitor_amount : 78
         * manager_user_id : 2
         * manager_user_name : llife
         * activity_phone_no : 16856685555
         * province : 上海市
         * city : 上海市
         * area : 黄浦区
         * activity_address : 国定东路
         * venue_name : 测试场馆
         * remark : null
         * status : 1
         * group_num : 3
         * brand_name : 测试用球
         * brand_ball_url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
         * min_battle : 100
         * max_battle : 200
         */
        private int group_id;
        private String group_name;
        private String activity_type;
        private String activity_type_name;
        private String activity_name;
        private String start_date;
        private int duration;
        private int m_visitor_amount;
        private int manager_user_id;
        private String manager_user_name;
        private String activity_phone_no;
        private String province;
        private String city;
        private String area;
        private String activity_address;
        private String venue_name;
        private String remark;
        private String status;
        private int group_num;
        private String brand_name;
        private String brand_ball_url;
        private int min_battle;
        private int max_battle;
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

        public String getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(String activity_type) {
            this.activity_type = activity_type;
        }

        public String getActivity_type_name() {
            return activity_type_name;
        }

        public void setActivity_type_name(String activity_type_name) {
            this.activity_type_name = activity_type_name;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getM_visitor_amount() {
            return m_visitor_amount;
        }

        public void setM_visitor_amount(int m_visitor_amount) {
            this.m_visitor_amount = m_visitor_amount;
        }

        public int getManager_user_id() {
            return manager_user_id;
        }

        public void setManager_user_id(int manager_user_id) {
            this.manager_user_id = manager_user_id;
        }

        public String getManager_user_name() {
            return manager_user_name;
        }

        public void setManager_user_name(String manager_user_name) {
            this.manager_user_name = manager_user_name;
        }

        public String getActivity_phone_no() {
            return activity_phone_no;
        }

        public void setActivity_phone_no(String activity_phone_no) {
            this.activity_phone_no = activity_phone_no;
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

        public String getActivity_address() {
            return activity_address;
        }

        public void setActivity_address(String activity_address) {
            this.activity_address = activity_address;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getGroup_num() {
            return group_num;
        }

        public void setGroup_num(int group_num) {
            this.group_num = group_num;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getBrand_ball_url() {
            return brand_ball_url;
        }

        public void setBrand_ball_url(String brand_ball_url) {
            this.brand_ball_url = brand_ball_url;
        }

        public int getMin_battle() {
            return min_battle;
        }

        public void setMin_battle(int min_battle) {
            this.min_battle = min_battle;
        }

        public int getMax_battle() {
            return max_battle;
        }

        public void setMax_battle(int max_battle) {
            this.max_battle = max_battle;
        }
    }
}
