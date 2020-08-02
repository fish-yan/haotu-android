package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class WareActivityBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"user_token":null,"activity_phone_no":"133455665","activity_id":37,"group_id":16,"activity_name":"公告","activity_type":"1","start_date":"2019-04-15 15:34:00.0","duration":5,"province":"上海市","city":"上海市","area":"黄浦区","activity_address":"国定东路257号","total_no":66,"replace_no":32,"is_w_discount":"1","m_member_amount":56,"m_visitor_amount":60,"w_discount_amount":2,"remark":"风风光光给哥哥哥哥","is_invite_discount":null,"invite_discount_amount":null,"field_no":"45"}
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
         * user_token : null
         * activity_phone_no : 133455665
         * activity_id : 37
         * group_id : 16
         * activity_name : 公告
         * activity_type : 1
         * start_date : 2019-04-15 15:34:00.0
         * duration : 5
         * province : 上海市
         * city : 上海市
         * area : 黄浦区
         * activity_address : 国定东路257号
         * total_no : 66
         * replace_no : 32
         * is_w_discount : 1
         * m_member_amount : 56
         * m_visitor_amount : 60
         * w_discount_amount : 2
         * remark : 风风光光给哥哥哥哥
         * is_invite_discount : null
         * invite_discount_amount : null
         * field_no : 45
         */

        private Object user_token;
        private String activity_phone_no;
        private int activity_id;
        private int group_id;
        private String activity_name;
        private String activity_type;
        private String start_date;
        private int duration;
        private String province;
        private String city;
        private String area;
        private String activity_address;
        private int total_no;
        private int replace_no;
        private String is_w_discount;
        private int m_member_amount;
        private int m_visitor_amount;
        private int w_discount_amount;
        private String remark;
        private Object is_invite_discount;
        private Object invite_discount_amount;
        private String field_no;
        private String is_door;

        public String getIs_door() {
            return is_door;
        }

        public void setIs_door(String is_door) {
            this.is_door = is_door;
        }

        public Object getUser_token() {
            return user_token;
        }

        public void setUser_token(Object user_token) {
            this.user_token = user_token;
        }

        public String getActivity_phone_no() {
            return activity_phone_no;
        }

        public void setActivity_phone_no(String activity_phone_no) {
            this.activity_phone_no = activity_phone_no;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(String activity_type) {
            this.activity_type = activity_type;
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

        public int getTotal_no() {
            return total_no;
        }

        public void setTotal_no(int total_no) {
            this.total_no = total_no;
        }

        public int getReplace_no() {
            return replace_no;
        }

        public void setReplace_no(int replace_no) {
            this.replace_no = replace_no;
        }

        public String getIs_w_discount() {
            return is_w_discount;
        }

        public void setIs_w_discount(String is_w_discount) {
            this.is_w_discount = is_w_discount;
        }

        public int getM_member_amount() {
            return m_member_amount;
        }

        public void setM_member_amount(int m_member_amount) {
            this.m_member_amount = m_member_amount;
        }

        public int getM_visitor_amount() {
            return m_visitor_amount;
        }

        public void setM_visitor_amount(int m_visitor_amount) {
            this.m_visitor_amount = m_visitor_amount;
        }

        public int getW_discount_amount() {
            return w_discount_amount;
        }

        public void setW_discount_amount(int w_discount_amount) {
            this.w_discount_amount = w_discount_amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getIs_invite_discount() {
            return is_invite_discount;
        }

        public void setIs_invite_discount(Object is_invite_discount) {
            this.is_invite_discount = is_invite_discount;
        }

        public Object getInvite_discount_amount() {
            return invite_discount_amount;
        }

        public void setInvite_discount_amount(Object invite_discount_amount) {
            this.invite_discount_amount = invite_discount_amount;
        }

        public String getField_no() {
            return field_no;
        }

        public void setField_no(String field_no) {
            this.field_no = field_no;
        }
    }
}
