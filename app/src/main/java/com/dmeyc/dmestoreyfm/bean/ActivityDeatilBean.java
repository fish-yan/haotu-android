package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityDeatilBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_id":24,"group_name":"帅帅羽毛球俱乐部","activity_name":"123456","start_date":"2019-04-17 18:00:00","activity_address":"国和路500弄","total_no":6,"activity_phone_no":"15035755652","member_amount":40,"remark":"比赛了快来报名","group_total_no":null,"sign_up_no":6,"owner_name":"李帅","longitude":121.518358,"latitude":31.311502,"group_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1gVyALi9oAACswXvpFcA119.jpg","is_sign_no":"0","sign_up_list":[{"user_id":32,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"user_id":19,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"user_id":20,"url":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1jGiAUGCoAAC74ByXxn8779.jpg"},{"user_id":21,"url":"http://192.168.0.104/group1/M00/00/00/wKgAaFyuACaAdpqTAAN0aYfsS4k806.jpg"},{"user_id":22,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"user_id":23,"url":"http://192.168.0.104/group1/M00/00/00/wKgAaFywC8SAOk4cAABlHOIVZLo315.jpg"}]}
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
         * group_id : 24
         * group_name : 帅帅羽毛球俱乐部
         * activity_name : 123456
         * start_date : 2019-04-17 18:00:00
         * activity_address : 国和路500弄
         * total_no : 6
         * activity_phone_no : 15035755652
         * member_amount : 40
         * remark : 比赛了快来报名
         * group_total_no : null
         * sign_up_no : 6
         * owner_name : 李帅
         * longitude : 121.518358
         * latitude : 31.311502
         * group_logo : http://192.168.0.104/group1/M00/00/01/wKgAaFy1gVyALi9oAACswXvpFcA119.jpg
         * is_sign_no : 0
         * sign_up_list : [{"user_id":32,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"user_id":19,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"user_id":20,"url":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1jGiAUGCoAAC74ByXxn8779.jpg"},{"user_id":21,"url":"http://192.168.0.104/group1/M00/00/00/wKgAaFyuACaAdpqTAAN0aYfsS4k806.jpg"},{"user_id":22,"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"user_id":23,"url":"http://192.168.0.104/group1/M00/00/00/wKgAaFywC8SAOk4cAABlHOIVZLo315.jpg"}]
         */

        private int group_id;
        private String group_name;
        private String activity_name;
        private String start_date;
        private String activity_address;
        private int total_no;
        private String activity_phone_no;
        private int member_amount;
        private String remark;
        private Object group_total_no;
        private int sign_up_no;
        private String owner_name;
        private double longitude;
        private double latitude;
        private String group_logo;
        private String is_sign_no;
        private List<SignUpListBean> sign_up_list;

        private String coacheRemark;
private String groupType;
private String isPk;
private String is_join_group;
private int  maxSingUpAmount;
private String projectType;
private String start_date_h5;
private String status;
private String suitAge;
private ArrayList<String> remarkImg;
private int w_visitor_amount;
private int w_member_amount;
private  int m_member_amount;
private int m_visitor_amount;
private int w_discount_amount;
private int duration;
private String venueName;
private String is_w_discount;
private ArrayList<String>coachRemarkImg;
private String isSponser;

        public String getIsSponser() {
            return isSponser;
        }

        public void setIsSponser(String isSponser) {
            this.isSponser = isSponser;
        }

        public ArrayList<String> getCoachRemarkImg() {
            return coachRemarkImg;
        }

        public void setCoachRemarkImg(ArrayList<String> coachRemarkImg) {
            this.coachRemarkImg = coachRemarkImg;
        }

        public String getIs_w_discount() {
            return is_w_discount;
        }

        public void setIs_w_discount(String is_w_discount) {
            this.is_w_discount = is_w_discount;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }


        public void setM_member_amount(int m_member_amount) {
            this.m_member_amount = m_member_amount;
        }

        public void setM_visitor_amount(int m_visitor_amount) {
            this.m_visitor_amount = m_visitor_amount;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getW_discount_amount() {
            return w_discount_amount;
        }

        public void setW_discount_amount(int w_discount_amount) {
            this.w_discount_amount = w_discount_amount;
        }

        public int getM_member_amount() {
            return m_member_amount;
        }

        public int getM_visitor_amount() {
            return m_visitor_amount;
        }

        public int getW_member_amount() {
            return w_member_amount;
        }

        public int getW_visitor_amount() {
            return w_visitor_amount;
        }

        public void setW_member_amount(int w_member_amount) {
            this.w_member_amount = w_member_amount;
        }

        public void setW_visitor_amount(int w_visitor_amount) {
            this.w_visitor_amount = w_visitor_amount;
        }

        public String getStatus() {
            return status;
        }

        public ArrayList<String> getRemarkImg() {
            return remarkImg;
        }

        public int getMaxSingUpAmount() {
            return maxSingUpAmount;
        }

        public String getCoacheRemark() {
            return coacheRemark;
        }

        public String getGroupType() {
            return groupType;
        }

        public String getIs_join_group() {
            return is_join_group;
        }

        public String getIsPk() {
            return isPk;
        }

        public String getProjectType() {
            return projectType;
        }

        public String getStart_date_h5() {
            return start_date_h5;
        }

        public String getSuitAge() {
            return suitAge;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setCoacheRemark(String coacheRemark) {
            this.coacheRemark = coacheRemark;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public void setIs_join_group(String is_join_group) {
            this.is_join_group = is_join_group;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        public void setMaxSingUpAmount(int maxSingUpAmount) {
            this.maxSingUpAmount = maxSingUpAmount;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public void setRemarkImg(ArrayList<String> remarkImg) {
            this.remarkImg = remarkImg;
        }

        public void setStart_date_h5(String start_date_h5) {
            this.start_date_h5 = start_date_h5;
        }

        public void setSuitAge(String suitAge) {
            this.suitAge = suitAge;
        }

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

        public String getActivity_phone_no() {
            return activity_phone_no;
        }

        public void setActivity_phone_no(String activity_phone_no) {
            this.activity_phone_no = activity_phone_no;
        }

        public int getMember_amount() {
            return member_amount;
        }

        public void setMember_amount(int member_amount) {
            this.member_amount = member_amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getGroup_total_no() {
            return group_total_no;
        }

        public void setGroup_total_no(Object group_total_no) {
            this.group_total_no = group_total_no;
        }

        public int getSign_up_no() {
            return sign_up_no;
        }

        public void setSign_up_no(int sign_up_no) {
            this.sign_up_no = sign_up_no;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getIs_sign_no() {
            return is_sign_no;
        }

        public void setIs_sign_no(String is_sign_no) {
            this.is_sign_no = is_sign_no;
        }

        public List<SignUpListBean> getSign_up_list() {
            return sign_up_list;
        }

        public void setSign_up_list(List<SignUpListBean> sign_up_list) {
            this.sign_up_list = sign_up_list;
        }

        public static class SignUpListBean {
            /**
             * user_id : 32
             * url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
             */

            private int user_id;
            private String url;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
