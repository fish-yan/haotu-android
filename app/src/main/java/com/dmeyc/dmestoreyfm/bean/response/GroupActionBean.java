package com.dmeyc.dmestoreyfm.bean.response;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by cxg on 2019/11/30
 * 群活动
 */
public class GroupActionBean extends BaseRespBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }



    public class DataBean  implements MultiItemEntity{

        /**
         * activityAddress : 徐家汇路500号
         * activity_id : 6
         * groupType : 5
         * group_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzkchOADIabAAF14qL2gV4575.jpg
         * group_name : jjjhhh
         * isGroupPk : 0
         * is_comment : 0
         * loser_no : 0
         * paid : 1
         * project_type : 羽毛球
         * start_date : 2019-06-18 19:00:00.0
         * status : 1
         * winner_no : 0
         * distance : 6.9km
         * activityName=测试活动5
         */

        @SerializedName(value = "activityAddress",alternate = {"activity_address"})
        private String activityAddress;
        @SerializedName(value = "activity_id",alternate = {"activityId"})
        private String activity_id;


        @SerializedName(value = "activityName",alternate = {"activity_name"})
        private String activityName;

        private String groupType;
        @SerializedName(value = "group_logo",alternate = {"groupLogo"})
        private String group_logo;
        @SerializedName(value = "group_name",alternate = {"groupName"})
        private String group_name;
        private String isGroupPk;
        private String is_comment;
        private int loser_no;
        private String paid;
        private String project_type;

        private String group_id;
        private String distance;

        @SerializedName(value = "start_date",alternate = {"startTime"})
        private String start_date;
        private String status;
        private int winner_no;
        private String weekDay;
        private String isPk;
        private String stage;//1没开始、2进行中、3结束


        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        private List<ActivityDetailBean.DataBean.SignUp> sign_up_list;
        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public String getActivityAddress() {
            return activityAddress;
        }

        public void setActivityAddress(String activityAddress) {
            this.activityAddress = activityAddress;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
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

        public String getIsGroupPk() {
            return isGroupPk;
        }

        public void setIsGroupPk(String isGroupPk) {
            this.isGroupPk = isGroupPk;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public int getLoser_no() {
            return loser_no;
        }

        public void setLoser_no(int loser_no) {
            this.loser_no = loser_no;
        }

        public String getPaid() {
            return paid;
        }

        public void setPaid(String paid) {
            this.paid = paid;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getWinner_no() {
            return winner_no;
        }

        public void setWinner_no(int winner_no) {
            this.winner_no = winner_no;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getIsPk() {
            return isPk;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }
        public List<ActivityDetailBean.DataBean.SignUp> getSign_up_list() {
            return sign_up_list;
        }

        public void setSign_up_list(List<ActivityDetailBean.DataBean.SignUp> sign_up_list) {
            this.sign_up_list = sign_up_list;
        }

        @Override
        public int getItemType() {
            // TODO: 2019/12/1 判断条目
            return Constant.AdapterItemType.MATCH;
        }
    }
}
