package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class RoCluldCommBean implements Serializable {


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
