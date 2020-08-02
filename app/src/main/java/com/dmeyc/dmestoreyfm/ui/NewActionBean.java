package com.dmeyc.dmestoreyfm.ui;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;
import java.util.List;

public class NewActionBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"activityAddress":"徐家汇路500号","activity_id":6,"groupType":"5","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzkchOADIabAAF14qL2gV4575.jpg","group_name":"jjjhhh","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-06-18 19:00:00.0","status":"1","winner_no":0},{"activityAddress":"国定东路","activity_id":21,"groupType":"1","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcXYyAePSCAAFOqkBasc0100.jpg","group_name":"上海羽毛球安卓测试群","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-05-22 15:05:00.0","status":"3","winner_no":0},{"activityAddress":"徐家汇路500号","activity_id":41,"groupType":"1","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbFXCAGDmkAAB-9frQ0wk540.jpg","group_name":"兔子跑","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-05-30 19:00:00.0","status":"3","winner_no":0},{"activityAddress":"世界路1号","activity_id":52,"groupType":"1","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4_aAL6GzAADPpKNgEZs923.jpg","group_name":"政府羽毛球俱乐部","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-05-24 14:10:00.0","status":"3","winner_no":0},{"activityAddress":"上海","activity_id":102,"groupType":"1","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4--AWS_wAADFustiZb4810.jpg","group_name":"垂直网络运动群","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-06-03 18:11:00.0","status":"3","winner_no":0},{"activityAddress":"政府路18号","activity_id":106,"groupType":"1","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4_aAL6GzAADPpKNgEZs923.jpg","group_name":"政府羽毛球俱乐部","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-06-04 18:00:00.0","status":"3","winner_no":0},{"activityAddress":"徐家汇路500号","activity_id":117,"groupType":"1","group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbFXCAGDmkAAB-9frQ0wk540.jpg","group_name":"兔子跑","isGroupPk":"0","is_comment":"0","loser_no":0,"paid":"1","project_type":"羽毛球","start_date":"2019-06-06 19:00:00.0","status":"3","winner_no":0}]
     * msg : 操作成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
         */

        private String activityAddress;
        private int activity_id;
        private String groupType;
        private String group_logo;
        private String group_name;
        private String isGroupPk;
        private String is_comment;
        private int loser_no;
        private String paid;
        private String project_type;
        private String start_date;
        private String status;
        private int winner_no;
        private String weekDay;

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

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
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
    }
}
