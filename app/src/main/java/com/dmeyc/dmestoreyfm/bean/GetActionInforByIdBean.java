package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class GetActionInforByIdBean implements Serializable {

    /**
     * code : 200
     * data : {"ActivityDetail":{"activityProperty":"1","activity_address":"国定东路","activity_name":"啦啦啦到家了","activity_phone_no":"17550312284","area":"黄浦区","city":"上海市","coachRemarkImg":["http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAWvvFAAJp2Rzv2Cg502.jpg","http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAGFq_AAL1PypLt2Q270.jpg","http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAXJODAAQGbZ6_YxA810.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAQqfQAAGNb6ZI0kQ935.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAHVHfAAOnDCTbzzc625.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAdE1VAAJP_DqAJWk377.jpg"],"coacheRemark":"不套路噜噜噜娜可露露","duration":6,"groupType":"3","group_id":30,"group_logo":"http://192.168.0.104/group1/M00/00/20/wKgAaF1gB7mANl7eAAFcJMFpPHI545.jpg","group_name":"过滤","isPk":"0","is_join_group":"1","is_sign_no":"0","is_w_discount":"0","latitude":31.299441,"level":1,"longitude":121.521153,"m_member_amount":55,"m_visitor_amount":65,"manager_id":14,"manager_name":"咸咖啡","maxSingUpAmount":65,"member_amount":55,"owner_name":"咸咖啡","projectType":"1","projectTypeName":"羽毛球","province":"上海市","remark":"了啦痛苦不堪他不踏踏","sex":"1","sign_up_list":[],"sign_up_no":0,"sportType":"0","start_date":"2019-09-03 11:00:00","start_date_h5":"2019年09月03日 11:00","status":"1","suitAge":"5-15","total_no":6,"venueName":"龙门","w_discount_amount":10,"w_member_amount":55,"w_visitor_amount":65},"IsThereActivity":1}
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
         * ActivityDetail : {"activityProperty":"1","activity_address":"国定东路","activity_name":"啦啦啦到家了","activity_phone_no":"17550312284","area":"黄浦区","city":"上海市","coachRemarkImg":["http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAWvvFAAJp2Rzv2Cg502.jpg","http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAGFq_AAL1PypLt2Q270.jpg","http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAXJODAAQGbZ6_YxA810.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAQqfQAAGNb6ZI0kQ935.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAHVHfAAOnDCTbzzc625.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAdE1VAAJP_DqAJWk377.jpg"],"coacheRemark":"不套路噜噜噜娜可露露","duration":6,"groupType":"3","group_id":30,"group_logo":"http://192.168.0.104/group1/M00/00/20/wKgAaF1gB7mANl7eAAFcJMFpPHI545.jpg","group_name":"过滤","isPk":"0","is_join_group":"1","is_sign_no":"0","is_w_discount":"0","latitude":31.299441,"level":1,"longitude":121.521153,"m_member_amount":55,"m_visitor_amount":65,"manager_id":14,"manager_name":"咸咖啡","maxSingUpAmount":65,"member_amount":55,"owner_name":"咸咖啡","projectType":"1","projectTypeName":"羽毛球","province":"上海市","remark":"了啦痛苦不堪他不踏踏","sex":"1","sign_up_list":[],"sign_up_no":0,"sportType":"0","start_date":"2019-09-03 11:00:00","start_date_h5":"2019年09月03日 11:00","status":"1","suitAge":"5-15","total_no":6,"venueName":"龙门","w_discount_amount":10,"w_member_amount":55,"w_visitor_amount":65}
         * IsThereActivity : 1
         */

        private ActivityDetailBean ActivityDetail;
        private int IsThereActivity;

        public ActivityDetailBean getActivityDetail() {
            return ActivityDetail;
        }

        public void setActivityDetail(ActivityDetailBean ActivityDetail) {
            this.ActivityDetail = ActivityDetail;
        }

        public int getIsThereActivity() {
            return IsThereActivity;
        }

        public void setIsThereActivity(int IsThereActivity) {
            this.IsThereActivity = IsThereActivity;
        }

        public static class ActivityDetailBean {
            /**
             * activityProperty : 1
             * activity_address : 国定东路
             * activity_name : 啦啦啦到家了
             * activity_phone_no : 17550312284
             * area : 黄浦区
             * city : 上海市
             * coachRemarkImg : ["http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAWvvFAAJp2Rzv2Cg502.jpg","http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAGFq_AAL1PypLt2Q270.jpg","http://192.168.0.104/group1/M00/00/22/wKgAaF1lqyqAXJODAAQGbZ6_YxA810.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAQqfQAAGNb6ZI0kQ935.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAHVHfAAOnDCTbzzc625.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAdE1VAAJP_DqAJWk377.jpg"]
             * coacheRemark : 不套路噜噜噜娜可露露
             * duration : 6
             * groupType : 3
             * group_id : 30
             * group_logo : http://192.168.0.104/group1/M00/00/20/wKgAaF1gB7mANl7eAAFcJMFpPHI545.jpg
             * group_name : 过滤
             * isPk : 0
             * is_join_group : 1
             * is_sign_no : 0
             * is_w_discount : 0
             * latitude : 31.299441
             * level : 1
             * longitude : 121.521153
             * m_member_amount : 55
             * m_visitor_amount : 65
             * manager_id : 14
             * manager_name : 咸咖啡
             * maxSingUpAmount : 65
             * member_amount : 55
             * owner_name : 咸咖啡
             * projectType : 1
             * projectTypeName : 羽毛球
             * province : 上海市
             * remark : 了啦痛苦不堪他不踏踏
             * sex : 1
             * sign_up_list : []
             * sign_up_no : 0
             * sportType : 0
             * start_date : 2019-09-03 11:00:00
             * start_date_h5 : 2019年09月03日 11:00
             * status : 1
             * suitAge : 5-15
             * total_no : 6
             * venueName : 龙门
             * w_discount_amount : 10
             * w_member_amount : 55
             * w_visitor_amount : 65
             */

            private String activityProperty;
            private String activity_address;
            private String activity_name;
            private String activity_phone_no;
            private String area;
            private String city;
            private String coacheRemark;
            private int duration;
            private String groupType;
            private int group_id;
            private String group_logo;
            private String group_name;
            private String isPk;
            private String is_join_group;
            private String is_sign_no;
            private String is_w_discount;
            private double latitude;
            private int level;
            private double longitude;
            private int m_member_amount;
            private int m_visitor_amount;
            private int manager_id;
            private String manager_name;
            private int maxSingUpAmount;
            private int member_amount;
            private String owner_name;
            private String projectType;
            private String projectTypeName;
            private String province;
            private String remark;
            private String sex;
            private int sign_up_no;
            private String sportType;
            private String start_date;
            private String start_date_h5;
            private String status;
            private String suitAge;
            private int total_no;
            private String venueName;
            private int w_discount_amount;
            private int w_member_amount;
            private int w_visitor_amount;
            private List<String> coachRemarkImg;
            private List<?> sign_up_list;
            private List<String>  remarkImg;

            public List<String> getRemarkImg() {
                return remarkImg;
            }

            public void setRemarkImg(List<String> remarkImg) {
                this.remarkImg = remarkImg;
            }

            public String getActivityProperty() {
                return activityProperty;
            }

            public void setActivityProperty(String activityProperty) {
                this.activityProperty = activityProperty;
            }

            public String getActivity_address() {
                return activity_address;
            }

            public void setActivity_address(String activity_address) {
                this.activity_address = activity_address;
            }

            public String getActivity_name() {
                return activity_name;
            }

            public void setActivity_name(String activity_name) {
                this.activity_name = activity_name;
            }

            public String getActivity_phone_no() {
                return activity_phone_no;
            }

            public void setActivity_phone_no(String activity_phone_no) {
                this.activity_phone_no = activity_phone_no;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCoacheRemark() {
                return coacheRemark;
            }

            public void setCoacheRemark(String coacheRemark) {
                this.coacheRemark = coacheRemark;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getGroupType() {
                return groupType;
            }

            public void setGroupType(String groupType) {
                this.groupType = groupType;
            }

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroup_logo() {
                return group_logo;
            }

            public void setGroup_logo(String group_logo) {
                this.group_logo = group_logo;
            }

            public String getGroup_name() {
                return group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public String getIsPk() {
                return isPk;
            }

            public void setIsPk(String isPk) {
                this.isPk = isPk;
            }

            public String getIs_join_group() {
                return is_join_group;
            }

            public void setIs_join_group(String is_join_group) {
                this.is_join_group = is_join_group;
            }

            public String getIs_sign_no() {
                return is_sign_no;
            }

            public void setIs_sign_no(String is_sign_no) {
                this.is_sign_no = is_sign_no;
            }

            public String getIs_w_discount() {
                return is_w_discount;
            }

            public void setIs_w_discount(String is_w_discount) {
                this.is_w_discount = is_w_discount;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
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

            public int getManager_id() {
                return manager_id;
            }

            public void setManager_id(int manager_id) {
                this.manager_id = manager_id;
            }

            public String getManager_name() {
                return manager_name;
            }

            public void setManager_name(String manager_name) {
                this.manager_name = manager_name;
            }

            public int getMaxSingUpAmount() {
                return maxSingUpAmount;
            }

            public void setMaxSingUpAmount(int maxSingUpAmount) {
                this.maxSingUpAmount = maxSingUpAmount;
            }

            public int getMember_amount() {
                return member_amount;
            }

            public void setMember_amount(int member_amount) {
                this.member_amount = member_amount;
            }

            public String getOwner_name() {
                return owner_name;
            }

            public void setOwner_name(String owner_name) {
                this.owner_name = owner_name;
            }

            public String getProjectType() {
                return projectType;
            }

            public void setProjectType(String projectType) {
                this.projectType = projectType;
            }

            public String getProjectTypeName() {
                return projectTypeName;
            }

            public void setProjectTypeName(String projectTypeName) {
                this.projectTypeName = projectTypeName;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getSign_up_no() {
                return sign_up_no;
            }

            public void setSign_up_no(int sign_up_no) {
                this.sign_up_no = sign_up_no;
            }

            public String getSportType() {
                return sportType;
            }

            public void setSportType(String sportType) {
                this.sportType = sportType;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getStart_date_h5() {
                return start_date_h5;
            }

            public void setStart_date_h5(String start_date_h5) {
                this.start_date_h5 = start_date_h5;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSuitAge() {
                return suitAge;
            }

            public void setSuitAge(String suitAge) {
                this.suitAge = suitAge;
            }

            public int getTotal_no() {
                return total_no;
            }

            public void setTotal_no(int total_no) {
                this.total_no = total_no;
            }

            public String getVenueName() {
                return venueName;
            }

            public void setVenueName(String venueName) {
                this.venueName = venueName;
            }

            public int getW_discount_amount() {
                return w_discount_amount;
            }

            public void setW_discount_amount(int w_discount_amount) {
                this.w_discount_amount = w_discount_amount;
            }

            public int getW_member_amount() {
                return w_member_amount;
            }

            public void setW_member_amount(int w_member_amount) {
                this.w_member_amount = w_member_amount;
            }

            public int getW_visitor_amount() {
                return w_visitor_amount;
            }

            public void setW_visitor_amount(int w_visitor_amount) {
                this.w_visitor_amount = w_visitor_amount;
            }

            public List<String> getCoachRemarkImg() {
                return coachRemarkImg;
            }

            public void setCoachRemarkImg(List<String> coachRemarkImg) {
                this.coachRemarkImg = coachRemarkImg;
            }

            public List<?> getSign_up_list() {
                return sign_up_list;
            }

            public void setSign_up_list(List<?> sign_up_list) {
                this.sign_up_list = sign_up_list;
            }
        }
    }
}
