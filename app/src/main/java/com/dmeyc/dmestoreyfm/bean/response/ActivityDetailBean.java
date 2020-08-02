package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * create by cxg on 2019/12/11
 */
public class ActivityDetailBean extends BaseRespBean {
    private DataBean data;
    /**
     * code : 200
     * data : {"group_id":"8","group_name":"热带羽林","activity_name":"娱乐场","sportType":null,"start_date":"2019-10-07 20:00:00","is_w_discount":"0","duration":2,"province":"上海市","city":"上海市","area":"普陀区","w_discount_amount":5,"maxSingUpAmount":40,"m_member_amount":35,"w_member_amount":35,"m_visitor_amount":40,"w_visitor_amount":40,"sex":"2","level":null,"activity_address":"北石路208号","total_no":12,"activity_phone_no":"17811896367","manager_id":27,"manager_name":"米凯拉","member_amount":40,"remark":"娱乐场","group_total_no":null,"sign_up_no":0,"owner_name":"米凯拉","longitude":121.399132,"latitude":31.248328,"group_logo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","is_sign_no":"0","groupType":"1","projectType":"1","projectTypeName":"羽毛球","is_join_group":"0","start_date_h5":"2019年10月07日 20:00","isPk":"0","isSponser":null,"opponentGroup":null,"weekDay":null,"status":"3","suitAge":"18-65","venueName":"真如体育场羽毛球馆地下2号3号","remarkImg":null,"coacheRemark":"","coachRemarkImg":null,"activityProperty":"1","sign_up_list":[]}
     */


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * group_id : 8
         * group_name : 热带羽林
         * activity_name : 娱乐场
         * sportType : null
         * start_date : 2019-10-07 20:00:00
         * is_w_discount : 0
         * duration : 2.0
         * province : 上海市
         * city : 上海市
         * area : 普陀区
         * w_discount_amount : 5.0
         * maxSingUpAmount : 40.0
         * m_member_amount : 35.0
         * w_member_amount : 35.0
         * m_visitor_amount : 40.0
         * w_visitor_amount : 40.0
         * sex : 2
         * level : null
         * activity_address : 北石路208号
         * total_no : 12
         * activity_phone_no : 17811896367
         * manager_id : 27
         * manager_name : 米凯拉
         * member_amount : 40.0
         * remark : 娱乐场
         * group_total_no : null
         * sign_up_no : 0
         * owner_name : 米凯拉
         * longitude : 121.399132
         * latitude : 31.248328
         * group_logo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png
         * is_sign_no : 0
         * groupType : 1
         * projectType : 1
         * projectTypeName : 羽毛球
         * is_join_group : 0
         * start_date_h5 : 2019年10月07日 20:00
         * isPk : 0
         * isSponser : null
         * opponentGroup : null
         * weekDay : null
         * status : 3
         * suitAge : 18-65
         * venueName : 真如体育场羽毛球馆地下2号3号
         * remarkImg : null
         * coacheRemark :
         * coachRemarkImg : null
         * activityProperty : 1
         * sign_up_list : []
         *
         *
         */

        private String group_id;
        private String group_name;
        private String activity_name;
        private Object sportType;
        private String start_date;
        private String is_w_discount;
        private double duration;
        private String province;
        private String city;
        private String area;
        private double w_discount_amount;
        private double maxSingUpAmount;
        private double m_member_amount;
        private double w_member_amount;
        private String m_visitor_amount;
        private String w_visitor_amount;
        private String sex;
        private Object level;
        private String activity_address;
        private int total_no;
        private String activity_phone_no;
        private int manager_id;
        private String manager_name;
        private double member_amount;
        private String remark;
        private Object group_total_no;
        private int sign_up_no;
        private String owner_name;
        private String longitude;
        private String latitude;
        private String group_logo;
        private String is_sign_no;
        private String groupType;
        private String projectType;
        @SerializedName(value = "projectTypeName",alternate = {"activity_type"})
        private String projectTypeName;
        private String is_join_group;
        private String start_date_h5;
        private String isPk;
        private String isSponser;
        private Object opponentGroup;
        private Object weekDay;
        private String status;
        private String suitAge;
        private String venueName;
        private Object remarkImg;
        private String coacheRemark;
        private Object coachRemarkImg;
        private String activityProperty;
        private String sportsPoster;
        private List<SignUp> sign_up_list;

        public String getSportsPoster() {
            return sportsPoster;
        }

        public void setSportsPoster(String sportsPoster) {
            this.sportsPoster = sportsPoster;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
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

        public Object getSportType() {
            return sportType;
        }

        public void setSportType(Object sportType) {
            this.sportType = sportType;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getIs_w_discount() {
            return is_w_discount;
        }

        public void setIs_w_discount(String is_w_discount) {
            this.is_w_discount = is_w_discount;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
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

        public double getW_discount_amount() {
            return w_discount_amount;
        }

        public void setW_discount_amount(double w_discount_amount) {
            this.w_discount_amount = w_discount_amount;
        }

        public double getMaxSingUpAmount() {
            return maxSingUpAmount;
        }

        public void setMaxSingUpAmount(double maxSingUpAmount) {
            this.maxSingUpAmount = maxSingUpAmount;
        }

        public double getM_member_amount() {
            return m_member_amount;
        }

        public void setM_member_amount(double m_member_amount) {
            this.m_member_amount = m_member_amount;
        }

        public double getW_member_amount() {
            return w_member_amount;
        }

        public void setW_member_amount(double w_member_amount) {
            this.w_member_amount = w_member_amount;
        }

        public String getM_visitor_amount() {
            return m_visitor_amount;
        }

        public void setM_visitor_amount(String m_visitor_amount) {
            this.m_visitor_amount = m_visitor_amount;
        }

        public String getW_visitor_amount() {
            return w_visitor_amount;
        }

        public void setW_visitor_amount(String w_visitor_amount) {
            this.w_visitor_amount = w_visitor_amount;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
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

        public double getMember_amount() {
            return member_amount;
        }

        public void setMember_amount(double member_amount) {
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
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

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
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

        public String getIs_join_group() {
            return is_join_group;
        }

        public void setIs_join_group(String is_join_group) {
            this.is_join_group = is_join_group;
        }

        public String getStart_date_h5() {
            return start_date_h5;
        }

        public void setStart_date_h5(String start_date_h5) {
            this.start_date_h5 = start_date_h5;
        }

        public String getIsPk() {
            return isPk;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        public String getIsSponser() {
            return isSponser;
        }

        public void setIsSponser(String isSponser) {
            this.isSponser = isSponser;
        }

        public Object getOpponentGroup() {
            return opponentGroup;
        }

        public void setOpponentGroup(Object opponentGroup) {
            this.opponentGroup = opponentGroup;
        }

        public Object getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(Object weekDay) {
            this.weekDay = weekDay;
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

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }

        public Object getRemarkImg() {
            return remarkImg;
        }

        public void setRemarkImg(Object remarkImg) {
            this.remarkImg = remarkImg;
        }

        public String getCoacheRemark() {
            return coacheRemark;
        }

        public void setCoacheRemark(String coacheRemark) {
            this.coacheRemark = coacheRemark;
        }

        public Object getCoachRemarkImg() {
            return coachRemarkImg;
        }

        public void setCoachRemarkImg(Object coachRemarkImg) {
            this.coachRemarkImg = coachRemarkImg;
        }

        public String getActivityProperty() {
            return activityProperty;
        }

        public void setActivityProperty(String activityProperty) {
            this.activityProperty = activityProperty;
        }

        public List<SignUp> getSign_up_list() {
            return sign_up_list;
        }

        public void setSign_up_list(List<SignUp> sign_up_list) {
            this.sign_up_list = sign_up_list;
        }

        public static class SignUp implements Serializable {
            private String nick_name;
            private String sex;
            private String url;
            private String user_id;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
