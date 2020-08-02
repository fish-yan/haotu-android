package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class HeroListBean implements Serializable {

    /**
     * group_name : 垂直网络运动群
     * battle_number : 200
     * project_type : 羽毛球
     * activity_venue_address : 杨浦区
     * img_url : http://192.168.0.104/group1/M00/00/09/wKgAaFza4--AWS_wAADFustiZb4810.jpg
     * successRate : 100.0%
     * distance : 4.2km
     * rank : 第1名
     * groupId : 2
     */

    private String group_name;
    private String battle_number;
    private String project_type;
    private String activity_venue_address;
    private String img_url;
    private String successRate;
    private String distance;
    private String rank;
    private int groupId;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getBattle_number() {
        return battle_number;
    }

    public void setBattle_number(String battle_number) {
        this.battle_number = battle_number;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getActivity_venue_address() {
        return activity_venue_address;
    }

    public void setActivity_venue_address(String activity_venue_address) {
        this.activity_venue_address = activity_venue_address;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
