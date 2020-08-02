package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class MyCommListBean implements Serializable {

    /**
     * code : 200
     * data : [{"address":"国定东路","group_id":6,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","group_mark":"1","group_name":"红红火火","group_type":"1","project_name":"羽毛球","project_type":"1"},{"group_id":30,"group_logo":"http://192.168.0.104/group1/M00/00/0A/wKgAaFzm-oaAbVBaAACmWbETt4I800.jpg","group_mark":"1","group_name":"闹哄哄","group_type":"1","project_name":"羽毛球","project_type":"1"},{"address":"国定东路","group_id":31,"group_logo":"http://192.168.0.104/group1/M00/00/0A/wKgAaFznA2qACWzBAAEn1zrAgoA647.jpg","group_mark":"1","group_name":"句句","group_type":"3","project_name":"羽毛球","project_type":"1"},{"address":"国定东路688","group_id":50,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","group_mark":"1","group_name":"基金","group_type":"5","project_name":"羽毛球","project_type":"1"},{"address":"国定东路","group_id":51,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","group_mark":"1","group_name":"结果","group_type":"3","project_name":"羽毛球","project_type":"1"},{"address":"国定东路","group_id":52,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","group_mark":"1","group_name":"哈哈","group_type":"1","project_name":"羽毛球","project_type":"1"}]
     * msg : 操作成功
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 国定东路
         * group_id : 6
         * group_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg
         * group_mark : 1
         * group_name : 红红火火
         * group_type : 1
         * project_name : 羽毛球
         * project_type : 1
         */

        private String address;
        private int group_id;
        private String group_logo;
        private String group_mark;
        private String group_name;
        private String group_type;
        private String project_name;
        private String project_type;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getGroup_mark() {
            return group_mark;
        }

        public void setGroup_mark(String group_mark) {
            this.group_mark = group_mark;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }
    }
}
