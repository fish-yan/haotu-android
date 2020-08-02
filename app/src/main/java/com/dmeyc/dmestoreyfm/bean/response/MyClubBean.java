package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by cxg on 2019/11/29
 */
public class MyClubBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"group_id":148,"group_logo":"http://101.44.2.178/group1/M00/00/24/wKgAaF3pwAiAN0s5AADL70qkrAE449.jpg","group_name":"方法","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":149,"group_logo":"http://101.44.2.178/group1/M00/00/24/wKgAaF3pwdGAP36iAADL70qkrAE188.jpg","group_name":"让","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":150,"group_logo":"http://101.44.2.178/group1/M00/00/24/wKgAaF3pwjqAd61GAADL70qkrAE784.jpg","group_name":"让","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":151,"group_logo":"http://101.44.2.178/group1/M00/00/24/wKgAaF3pxpuAGvhFAADL70qkrAE641.jpg","group_name":"同意","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":152,"group_logo":"http://101.44.2.178/group1/M00/00/24/wKgAaF3p1vqAF0BVABfdkAYi5QY833.jpg","group_name":"有","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":155,"group_logo":null,"group_name":"v","group_type":"3","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":156,"group_logo":"http://101.44.2.178/group1/M00/00/25/wKgAaF3sLheAM6WIAB9AXnYFWRE620.jpg","group_name":"她","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":157,"group_logo":"http://101.44.2.178/group1/M00/00/25/wKgAaF3sLwOAO0nwACo0Gwc5Rwg938.jpg","group_name":"和","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":158,"group_logo":"http://101.44.2.178/group1/M00/00/25/wKgAaF3sL-6AT6uEAADL70qkrAE784.jpg","group_name":"团","group_type":"5","address":"挂","project_type":null,"project_name":null,"group_mark":"1"},{"group_id":159,"group_logo":null,"group_name":"好好","group_type":"3","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":162,"group_logo":"http://101.44.2.178/group1/M00/00/25/wKgAaF32wR-AMsY2AAB9h_3PQco889.jpg","group_name":"陈佳佳的店","group_type":"5","address":"上海","project_type":null,"project_name":null,"group_mark":"1"},{"group_id":163,"group_logo":null,"group_name":"陈先生昵称","group_type":"3","address":null,"project_type":null,"project_name":null,"group_mark":"1"},{"group_id":164,"group_logo":"http://101.44.2.178/group1/M00/00/26/wKgAaF32xBOABMYTAABMAsyaozE987.png","group_name":"刚刚","group_type":"1","address":null,"project_type":null,"project_name":null,"group_mark":"1"}]
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
         * group_id : 148
         * group_logo : http://101.44.2.178/group1/M00/00/24/wKgAaF3pwAiAN0s5AADL70qkrAE449.jpg
         * group_name : 方法
         * group_type : 1
         * address : null
         * project_type : null
         * project_name : null
         * group_mark : 1
         */

        private String group_id;
        private String group_logo;
        private String group_name;
        private String group_type;
        private Object address;
        private Object project_type;
        private Object project_name;
        private String group_mark;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
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

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getProject_type() {
            return project_type;
        }

        public void setProject_type(Object project_type) {
            this.project_type = project_type;
        }

        public Object getProject_name() {
            return project_name;
        }

        public void setProject_name(Object project_name) {
            this.project_name = project_name;
        }

        public String getGroup_mark() {
            return group_mark;
        }

        public void setGroup_mark(String group_mark) {
            this.group_mark = group_mark;
        }
    }
}
