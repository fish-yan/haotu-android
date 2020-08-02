package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ActionRecordListBean implements Serializable {

    /**
     * code : 200
     * data : {"activity_list":[{"activity_id":13,"group_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_name":"冠军羽毛球俱乐部","loser_no":1,"project_type":"羽毛球","start_date":"2019-04-04 18:00:00.0","winner_no":9}]}
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
        private List<ActivityListBean> activity_list;

        public List<ActivityListBean> getActivity_list() {
            return activity_list;
        }

        public void setActivity_list(List<ActivityListBean> activity_list) {
            this.activity_list = activity_list;
        }

        public static class ActivityListBean {
            /**
             * activity_id : 13
             * group_logo : http://img1.mm131.me/pic/1695/1.jpg
             * group_name : 冠军羽毛球俱乐部
             * loser_no : 1
             * project_type : 羽毛球
             * start_date : 2019-04-04 18:00:00.0
             * winner_no : 9
             */

            private int activity_id;
            private String group_logo;
            private String group_name;
            private int loser_no;
            private String project_type;
            private String start_date;
            private int winner_no;
             private String groupType;
             private String isGroupPk;
private String  is_safe;

            public String getIs_safe() {
                return is_safe;
            }

            public void setIs_safe(String is_safe) {
                this.is_safe = is_safe;
            }

            public String getGroupType() {
                return groupType;
            }

            public String getIsGroupPk() {
                return isGroupPk;
            }

            public void setGroupType(String groupType) {
                this.groupType = groupType;
            }

            public void setIsGroupPk(String isGroupPk) {
                this.isGroupPk = isGroupPk;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
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

            public int getLoser_no() {
                return loser_no;
            }

            public void setLoser_no(int loser_no) {
                this.loser_no = loser_no;
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

            public int getWinner_no() {
                return winner_no;
            }

            public void setWinner_no(int winner_no) {
                this.winner_no = winner_no;
            }
        }
    }
}
