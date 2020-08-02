package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class MyCreatCommListBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"group_id":11,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzdnu-AG1GNAAACd0gdoRA092.jpg","group_name":"基金会","last_time":null,"last_content":null,"group_mark":"1","project_type":"1","group_type":"1","address":"国定东路","battle_number":0}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;
    private List<Integer>pkgroupid;
    private List<Integer>activityid;

    public List<Integer> getActivityid() {
        return activityid;
    }

    public void setActivityid(List<Integer> activityid) {
        this.activityid = activityid;
    }

    public List<Integer> getPkgroupid() {
        return pkgroupid;
    }

    public void setPkgroupid(List<Integer> pkgroupid) {
        this.pkgroupid = pkgroupid;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * group_id : 11
         * group_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzdnu-AG1GNAAACd0gdoRA092.jpg
         * group_name : 基金会
         * last_time : null
         * last_content : null
         * group_mark : 1
         * project_type : 1
         * group_type : 1
         * address : 国定东路
         * battle_number : 0
         */

        private int group_id;
        private String group_logo;
        private String group_name;
        private Object last_time;
        private Object last_content;
        private String group_mark;
        private String project_type;
        private String group_type;
        private String address;
        private int battle_number;
         private String project_name;

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
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

        public Object getLast_time() {
            return last_time;
        }

        public void setLast_time(Object last_time) {
            this.last_time = last_time;
        }

        public Object getLast_content() {
            return last_content;
        }

        public void setLast_content(Object last_content) {
            this.last_content = last_content;
        }

        public String getGroup_mark() {
            return group_mark;
        }

        public void setGroup_mark(String group_mark) {
            this.group_mark = group_mark;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getBattle_number() {
            return battle_number;
        }

        public void setBattle_number(int battle_number) {
            this.battle_number = battle_number;
        }
    }
}
