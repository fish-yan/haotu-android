package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class RoClodBean implements Serializable {

    /**
     * type : RC:challengeInfo
     */

    private String type;
     private int activityId;
private int pkId;
private String groupName;
private int battleNumber;
private String groupLogo;

    public int getBattleNumber() {
        return battleNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupLogo() {
        return groupLogo;
    }

    public int getPkId() {
        return pkId;
    }

    public void setBattleNumber(int battleNumber) {
        this.battleNumber = battleNumber;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupLogo(String groupLogo) {
        this.groupLogo = groupLogo;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
