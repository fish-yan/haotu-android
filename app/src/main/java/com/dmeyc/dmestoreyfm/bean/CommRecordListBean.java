package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class CommRecordListBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"activity_list":[{"group_name":"发发疯","group_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1gl2AU4c-AAFulOBxFCg891.jpg","activity_id":37,"project_type":"羽毛球","start_date":"2019-04-15 15:34:00","status":"1","sign_up_no":0,"total_no":66,"isGroupPk":"0","groupType":"1","isEdit":1,"isDelete":1,"isEnterSocre":0}]}
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
        private List<ActivityListBean> activity_list;

        public List<ActivityListBean> getActivity_list() {
            return activity_list;
        }

        public void setActivity_list(List<ActivityListBean> activity_list) {
            this.activity_list = activity_list;
        }

        public static class ActivityListBean {
            /**
             * group_name : 发发疯
             * group_logo : http://192.168.0.104/group1/M00/00/01/wKgAaFy1gl2AU4c-AAFulOBxFCg891.jpg
             * activity_id : 37
             * project_type : 羽毛球
             * start_date : 2019-04-15 15:34:00
             * status : 1
             * sign_up_no : 0
             * total_no : 66
             * isGroupPk : 0
             * groupType : 1
             * isEdit : 1
             * isDelete : 1
             * isEnterSocre : 0
             */

            private String group_name;
            private String group_logo;
            private int activity_id;
            private String project_type;
            private String start_date;
            private String status;
            private int sign_up_no;
            private int total_no;
            private String isGroupPk;
            private String groupType;
            private int isEdit;
            private int isDelete;
            private int isEnterSocre;
            private String group_type;
            private String is_safe;

            public String getIs_safe() {
                return is_safe;
            }

            public String getGroup_type() {
                return group_type;
            }

            public void setIs_safe(String is_safe) {
                this.is_safe = is_safe;
            }

            public void setGroup_type(String group_type) {
                this.group_type = group_type;
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

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
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

            public int getSign_up_no() {
                return sign_up_no;
            }

            public void setSign_up_no(int sign_up_no) {
                this.sign_up_no = sign_up_no;
            }

            public int getTotal_no() {
                return total_no;
            }

            public void setTotal_no(int total_no) {
                this.total_no = total_no;
            }

            public String getIsGroupPk() {
                return isGroupPk;
            }

            public void setIsGroupPk(String isGroupPk) {
                this.isGroupPk = isGroupPk;
            }

            public String getGroupType() {
                return groupType;
            }

            public void setGroupType(String groupType) {
                this.groupType = groupType;
            }

            public int getIsEdit() {
                return isEdit;
            }

            public void setIsEdit(int isEdit) {
                this.isEdit = isEdit;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public int getIsEnterSocre() {
                return isEnterSocre;
            }

            public void setIsEnterSocre(int isEnterSocre) {
                this.isEnterSocre = isEnterSocre;
            }
        }
    }
}
