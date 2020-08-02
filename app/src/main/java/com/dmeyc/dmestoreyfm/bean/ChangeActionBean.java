package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ChangeActionBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_id":9,"group_name":"聂聂普通群","activity_name":"诺","sportType":"2","start_date":"2019-07-10 16:00:00","is_w_discount":"0","duration":6,"province":"上海市","city":"上海市","area":"黄浦区","w_discount_amount":10,"maxSingUpAmount":65,"m_member_amount":55,"w_member_amount":55,"m_visitor_amount":65,"w_visitor_amount":65,"sex":"1","level":1,"activity_address":"国定东路","total_no":6,"activity_phone_no":"17182701034","manager_id":3,"manager_name":"hghhy5","member_amount":55,"remark":"看看咯啦啦啦","group_total_no":null,"sign_up_no":0,"owner_name":"hghhy5","longitude":121.521153,"latitude":31.299441,"group_logo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr4CAcZCgAAGOBhPV6qc958.jpg","is_sign_no":"0","groupType":"1","projectType":"1","projectTypeName":"羽毛球","is_join_group":"1","start_date_h5":"2019年07月10日 16:00","isPk":"0","isSponser":null,"opponentGroup":null,"weekDay":null,"status":"3","suitAge":null,"venueName":"哦NO","remarkImg":null,"coacheRemark":"","coachRemarkImg":null,"sign_up_list":[]}
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
         * group_id : 9
         * group_name : 聂聂普通群
         * activity_name : 诺
         * sportType : 2
         * start_date : 2019-07-10 16:00:00
         * is_w_discount : 0
         * duration : 6
         * province : 上海市
         * city : 上海市
         * area : 黄浦区
         * w_discount_amount : 10
         * maxSingUpAmount : 65
         * m_member_amount : 55
         * w_member_amount : 55
         * m_visitor_amount : 65
         * w_visitor_amount : 65
         * sex : 1
         * level : 1
         * activity_address : 国定东路
         * total_no : 6
         * activity_phone_no : 17182701034
         * manager_id : 3
         * manager_name : hghhy5
         * member_amount : 55
         * remark : 看看咯啦啦啦
         * group_total_no : null
         * sign_up_no : 0
         * owner_name : hghhy5
         * longitude : 121.521153
         * latitude : 31.299441
         * group_logo : http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr4CAcZCgAAGOBhPV6qc958.jpg
         * is_sign_no : 0
         * groupType : 1
         * projectType : 1
         * projectTypeName : 羽毛球
         * is_join_group : 1
         * start_date_h5 : 2019年07月10日 16:00
         * isPk : 0
         * isSponser : null
         * opponentGroup : null
         * weekDay : null
         * status : 3
         * suitAge : null
         * venueName : 哦NO
         * remarkImg : null
         * coacheRemark :
         * coachRemarkImg : null
         * sign_up_list : []
         */

        private int group_id;
        private String group_name;
        private String activity_name;
        private String sportType;
        private String start_date;
        private String is_w_discount;
        private int duration;
        private String province;
        private String city;
        private String area;
        private int w_discount_amount;
        private int maxSingUpAmount;
        private int m_member_amount;
        private int w_member_amount;
        private int m_visitor_amount;
        private int w_visitor_amount;
        private String sex;
        private int level;
        private String activity_address;
        private int total_no;
        private String activity_phone_no;
        private int manager_id;
        private String manager_name;
        private int member_amount;
        private String remark;
        private Object group_total_no;
        private int sign_up_no;
        private String owner_name;
        private double longitude;
        private double latitude;
        private String group_logo;
        private String is_sign_no;
        private String groupType;
        private String projectType;
        private String projectTypeName;
        private String is_join_group;
        private String start_date_h5;
        private String isPk;
        private Object isSponser;
        private Object opponentGroup;
        private Object weekDay;
        private String status;

        private String venueName;
        private List<String>  remarkImg;
        private String coacheRemark;
        private List<String> coachRemarkImg;
        private List<?> sign_up_list;
       private String suitAge;


        public List<String> getCoachRemarkImg() {
            return coachRemarkImg;
        }

        public List<String> getRemarkImg() {
            return remarkImg;
        }

        public void setCoachRemarkImg(List<String> coachRemarkImg) {
            this.coachRemarkImg = coachRemarkImg;
        }

        public void setRemarkImg(List<String> remarkImg) {
            this.remarkImg = remarkImg;
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

        public String getIs_w_discount() {
            return is_w_discount;
        }

        public void setIs_w_discount(String is_w_discount) {
            this.is_w_discount = is_w_discount;
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

        public int getW_discount_amount() {
            return w_discount_amount;
        }

        public void setW_discount_amount(int w_discount_amount) {
            this.w_discount_amount = w_discount_amount;
        }

        public int getMaxSingUpAmount() {
            return maxSingUpAmount;
        }

        public void setMaxSingUpAmount(int maxSingUpAmount) {
            this.maxSingUpAmount = maxSingUpAmount;
        }

        public int getM_member_amount() {
            return m_member_amount;
        }

        public void setM_member_amount(int m_member_amount) {
            this.m_member_amount = m_member_amount;
        }

        public int getW_member_amount() {
            return w_member_amount;
        }

        public void setW_member_amount(int w_member_amount) {
            this.w_member_amount = w_member_amount;
        }

        public int getM_visitor_amount() {
            return m_visitor_amount;
        }

        public void setM_visitor_amount(int m_visitor_amount) {
            this.m_visitor_amount = m_visitor_amount;
        }

        public int getW_visitor_amount() {
            return w_visitor_amount;
        }

        public void setW_visitor_amount(int w_visitor_amount) {
            this.w_visitor_amount = w_visitor_amount;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
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

        public Object getIsSponser() {
            return isSponser;
        }

        public void setIsSponser(Object isSponser) {
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






        public String getCoacheRemark() {
            return coacheRemark;
        }

        public void setCoacheRemark(String coacheRemark) {
            this.coacheRemark = coacheRemark;
        }





        public List<?> getSign_up_list() {
            return sign_up_list;
        }

        public void setSign_up_list(List<?> sign_up_list) {
            this.sign_up_list = sign_up_list;
        }
    }
}
