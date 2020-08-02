package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class BClientActionBean implements Serializable {

    /**
     * code : 200
     * data : [{"activityAddress":"国定东路","activityId":14,"activityName":"看看看","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-17 10:09:00","status":"3"},{"activityAddress":"国定东路","activityId":17,"activityName":"积极","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-15 16:46:00","status":"3"},{"activityAddress":"国定东路","activityId":24,"activityName":"积极","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-22 16:46:00","status":"3"},{"activityAddress":"国定东路","activityId":46,"activityName":"看看看","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-24 10:09:00","status":"3"},{"activityAddress":"国定东路","activityId":55,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-20 14:07:00","status":"3"},{"activityAddress":"国定东路","activityId":71,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-27 14:07:00","status":"3"},{"activityAddress":"国定东路","activityId":86,"activityName":"积极","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-29 16:46:00","status":"3"},{"activityAddress":"国定东路","activityId":93,"activityName":"看看看","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-05-31 10:09:00","status":"3"},{"activityAddress":"国定东路","activityId":101,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-03 14:07:00","status":"3"},{"activityAddress":"国定东路","activityId":111,"activityName":"积极","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-05 16:46:00","status":"3"},{"activityAddress":"国定东路","activityId":119,"activityName":"看看看","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-07 10:09:00","status":"3"},{"activityAddress":"国定东路","activityId":124,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-10 14:07:00","status":"3"},{"activityAddress":"国定东路","activityId":138,"activityName":"积极","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-12 16:46:00","status":"1"},{"activityAddress":"国定东路","activityId":145,"activityName":"看看看","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-14 10:09:00","status":"1"},{"activityAddress":"国定东路","activityId":150,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-17 14:07:00","status":"1"},{"activityAddress":"国定东路","activityId":156,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-03 14:07:00","status":"3"},{"activityAddress":"国定东路","activityId":157,"activityName":"努力","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"0","managerUserId":2,"projectType":"1","projectTypeName":"羽毛球","startTime":"2019-06-10 14:07:00","status":"3"},{"activityAddress":"国定东路","activityId":12,"activityName":"家家户户","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"1","managerUserId":2,"projectType":"2","projectTypeName":"台球","startTime":"2019-05-16 09:56:00","status":"3"},{"activityAddress":"国定东路","activityId":13,"activityName":"救济金","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"1","managerUserId":2,"projectType":"2","projectTypeName":"台球","startTime":"2019-05-16 10:08:00","status":"3"},{"activityAddress":"政府路181弄","activityId":78,"activityName":"不吭了","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","groupName":"红红火火","groupType":"1","groupUserId":39,"isOwer":"1","isPk":"1","managerUserId":2,"projectType":"2","projectTypeName":"台球","startTime":"2019-05-16 20:04:00","status":"3"}]
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
         * activityAddress : 国定东路
         * activityId : 14
         * activityName : 看看看
         * groupLogo : http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg
         * groupName : 红红火火
         * groupType : 1
         * groupUserId : 39
         * isOwer : 1
         * isPk : 0
         * managerUserId : 2
         * projectType : 1
         * projectTypeName : 羽毛球
         * startTime : 2019-05-17 10:09:00
         * status : 3
         */

        private String activityAddress;
        private int activityId;
        private String activityName;
        private String groupLogo;
        private String groupName;
        private String groupType;
        private int groupUserId;
        private String isOwer;
        private String isPk;
        private int managerUserId;
        private String projectType;
        private String projectTypeName;
        private String startTime;
        private String status;
         private String isSponser;
          private String weekDay;
            private String isSigned;
            private String activityProperty;
         private String isBeginSaveScore;
         private int groupPkId;
        private String isGovernment;
        private String governmentIsStart;
        private String governmentActivityStage;

        public String getGovernmentActivityStage() {
            return governmentActivityStage;
        }

        public void setGovernmentActivityStage(String governmentActivityStage) {
            this.governmentActivityStage = governmentActivityStage;
        }

        public String getGovernmentIsStart() {
            return governmentIsStart;
        }

        public void setGovernmentIsStart(String governmentIsStart) {
            this.governmentIsStart = governmentIsStart;
        }

        public String getIsGovernment() {
            return isGovernment;
        }

        public void setIsGovernment(String isGovernment) {
            this.isGovernment = isGovernment;
        }

        public int getGroupPkId() {
            return groupPkId;
        }

        public void setGroupPkId(int groupPkId) {
            this.groupPkId = groupPkId;
        }

        public String getIsBeginSaveScore() {
            return isBeginSaveScore;
        }

        public void setIsBeginSaveScore(String isBeginSaveScore) {
            this.isBeginSaveScore = isBeginSaveScore;
        }

        public String getActivityProperty() {
            return activityProperty;
        }

        public void setActivityProperty(String activityProperty) {
            this.activityProperty = activityProperty;
        }

        public String getIsSigned() {
            return isSigned;
        }

        public void setIsSigned(String isSigned) {
            this.isSigned = isSigned;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public String getIsSponser() {
            return isSponser;
        }

        public void setIsSponser(String isSponser) {
            this.isSponser = isSponser;
        }

        public String getActivityAddress() {
            return activityAddress;
        }

        public void setActivityAddress(String activityAddress) {
            this.activityAddress = activityAddress;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getGroupLogo() {
            return groupLogo;
        }

        public void setGroupLogo(String groupLogo) {
            this.groupLogo = groupLogo;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public int getGroupUserId() {
            return groupUserId;
        }

        public void setGroupUserId(int groupUserId) {
            this.groupUserId = groupUserId;
        }

        public String getIsOwer() {
            return isOwer;
        }

        public void setIsOwer(String isOwer) {
            this.isOwer = isOwer;
        }

        public String getIsPk() {
            return isPk;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        public int getManagerUserId() {
            return managerUserId;
        }

        public void setManagerUserId(int managerUserId) {
            this.managerUserId = managerUserId;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
