package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SpecialActionBean implements Serializable {


    /**
     * code : 200
     * data : {"accepts":[{"activityGovernmentId":1,"createTime":1569800413000,"groupLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","groupName":"徐泾羽毛球俱乐部","groupSignupId":32,"id":1,"status":"1","userId":3}],"activityAddress":"宝山","activityName":"好兔官方活动","activityPhoneNo":"16638645363","activityPrice":"50","area":"宝山","award":"测试","city":"上海市","duration":8,"fiveStageTimes":0,"fiveStageTotalTimes":1,"foreSatgeTotalTimes":2,"foreStageTimes":0,"gameRule":"测试","groupBattle":500,"groupId":1,"groupLogo":"http://47.100.223.153:8888/group1/M00/00/03/rBNsuF1jrBKAfk2UAAAXB3oVaPk703.jpg","groupName":"好兔官方群","groupNum":5,"groupSignUpNum":0,"groupTotalNum":32,"id":1,"isPublishUser":"1","joinGroupId":32,"joinStatus":"1","oneStageTimes":0,"oneStageTotalTimes":48,"province":"上海市","publishUserId":3,"publishUserName":"好兔品台","startDate":"2019-09-29 10:00:00","status":"1","threeStageTimes":0,"threeStageTotalTimes":4,"twoStageTimes":0,"twoStageTotalTimes":8,"venueName":"宝山体育局"}
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
        /**
         * accepts : [{"activityGovernmentId":1,"createTime":1569800413000,"groupLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","groupName":"徐泾羽毛球俱乐部","groupSignupId":32,"id":1,"status":"1","userId":3}]
         * activityAddress : 宝山
         * activityName : 好兔官方活动
         * activityPhoneNo : 16638645363
         * activityPrice : 50
         * area : 宝山
         * award : 测试
         * city : 上海市
         * duration : 8
         * fiveStageTimes : 0
         * fiveStageTotalTimes : 1
         * foreSatgeTotalTimes : 2
         * foreStageTimes : 0
         * gameRule : 测试
         * groupBattle : 500
         * groupId : 1
         * groupLogo : http://47.100.223.153:8888/group1/M00/00/03/rBNsuF1jrBKAfk2UAAAXB3oVaPk703.jpg
         * groupName : 好兔官方群
         * groupNum : 5
         * groupSignUpNum : 0
         * groupTotalNum : 32
         * id : 1
         * isPublishUser : 1
         * joinGroupId : 32
         * joinStatus : 1
         * oneStageTimes : 0
         * oneStageTotalTimes : 48
         * province : 上海市
         * publishUserId : 3
         * publishUserName : 好兔品台
         * startDate : 2019-09-29 10:00:00
         * status : 1
         * threeStageTimes : 0
         * threeStageTotalTimes : 4
         * twoStageTimes : 0
         * twoStageTotalTimes : 8
         * venueName : 宝山体育局
         */

        private String activityAddress;
        private String activityName;
        private String activityPhoneNo;
        private String activityPrice;
        private String area;
        private String award;
        private String city;
        private int duration;
        private int fiveStageTimes;
        private int fiveStageTotalTimes;
        private int foreSatgeTotalTimes;
        private int foreStageTimes;
        private String gameRule;
        private int groupBattle;
        private int groupId;
        private String groupLogo;
        private String groupName;
        private int groupNum;
        private int groupSignUpNum;
        private int groupTotalNum;
        private int id;
        private String isPublishUser;
        private int joinGroupId;
        private String joinStatus;
        private int oneStageTimes;
        private int oneStageTotalTimes;
        private String province;
        private int publishUserId;
        private String publishUserName;
        private String startDate;
        private String status;
        private int threeStageTimes;
        private int threeStageTotalTimes;
        private int twoStageTimes;
        private int twoStageTotalTimes;
        private String venueName;
        private List<AcceptsBean> accepts;

        public String getActivityAddress() {
            return activityAddress;
        }

        public void setActivityAddress(String activityAddress) {
            this.activityAddress = activityAddress;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityPhoneNo() {
            return activityPhoneNo;
        }

        public void setActivityPhoneNo(String activityPhoneNo) {
            this.activityPhoneNo = activityPhoneNo;
        }

        public String getActivityPrice() {
            return activityPrice;
        }

        public void setActivityPrice(String activityPrice) {
            this.activityPrice = activityPrice;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAward() {
            return award;
        }

        public void setAward(String award) {
            this.award = award;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getFiveStageTimes() {
            return fiveStageTimes;
        }

        public void setFiveStageTimes(int fiveStageTimes) {
            this.fiveStageTimes = fiveStageTimes;
        }

        public int getFiveStageTotalTimes() {
            return fiveStageTotalTimes;
        }

        public void setFiveStageTotalTimes(int fiveStageTotalTimes) {
            this.fiveStageTotalTimes = fiveStageTotalTimes;
        }

        public int getForeSatgeTotalTimes() {
            return foreSatgeTotalTimes;
        }

        public void setForeSatgeTotalTimes(int foreSatgeTotalTimes) {
            this.foreSatgeTotalTimes = foreSatgeTotalTimes;
        }

        public int getForeStageTimes() {
            return foreStageTimes;
        }

        public void setForeStageTimes(int foreStageTimes) {
            this.foreStageTimes = foreStageTimes;
        }

        public String getGameRule() {
            return gameRule;
        }

        public void setGameRule(String gameRule) {
            this.gameRule = gameRule;
        }

        public int getGroupBattle() {
            return groupBattle;
        }

        public void setGroupBattle(int groupBattle) {
            this.groupBattle = groupBattle;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
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

        public int getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(int groupNum) {
            this.groupNum = groupNum;
        }

        public int getGroupSignUpNum() {
            return groupSignUpNum;
        }

        public void setGroupSignUpNum(int groupSignUpNum) {
            this.groupSignUpNum = groupSignUpNum;
        }

        public int getGroupTotalNum() {
            return groupTotalNum;
        }

        public void setGroupTotalNum(int groupTotalNum) {
            this.groupTotalNum = groupTotalNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsPublishUser() {
            return isPublishUser;
        }

        public void setIsPublishUser(String isPublishUser) {
            this.isPublishUser = isPublishUser;
        }

        public int getJoinGroupId() {
            return joinGroupId;
        }

        public void setJoinGroupId(int joinGroupId) {
            this.joinGroupId = joinGroupId;
        }

        public String getJoinStatus() {
            return joinStatus;
        }

        public void setJoinStatus(String joinStatus) {
            this.joinStatus = joinStatus;
        }

        public int getOneStageTimes() {
            return oneStageTimes;
        }

        public void setOneStageTimes(int oneStageTimes) {
            this.oneStageTimes = oneStageTimes;
        }

        public int getOneStageTotalTimes() {
            return oneStageTotalTimes;
        }

        public void setOneStageTotalTimes(int oneStageTotalTimes) {
            this.oneStageTotalTimes = oneStageTotalTimes;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getPublishUserId() {
            return publishUserId;
        }

        public void setPublishUserId(int publishUserId) {
            this.publishUserId = publishUserId;
        }

        public String getPublishUserName() {
            return publishUserName;
        }

        public void setPublishUserName(String publishUserName) {
            this.publishUserName = publishUserName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getThreeStageTimes() {
            return threeStageTimes;
        }

        public void setThreeStageTimes(int threeStageTimes) {
            this.threeStageTimes = threeStageTimes;
        }

        public int getThreeStageTotalTimes() {
            return threeStageTotalTimes;
        }

        public void setThreeStageTotalTimes(int threeStageTotalTimes) {
            this.threeStageTotalTimes = threeStageTotalTimes;
        }

        public int getTwoStageTimes() {
            return twoStageTimes;
        }

        public void setTwoStageTimes(int twoStageTimes) {
            this.twoStageTimes = twoStageTimes;
        }

        public int getTwoStageTotalTimes() {
            return twoStageTotalTimes;
        }

        public void setTwoStageTotalTimes(int twoStageTotalTimes) {
            this.twoStageTotalTimes = twoStageTotalTimes;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }

        public List<AcceptsBean> getAccepts() {
            return accepts;
        }

        public void setAccepts(List<AcceptsBean> accepts) {
            this.accepts = accepts;
        }

        public static class AcceptsBean {
            /**
             * activityGovernmentId : 1
             * createTime : 1569800413000
             * groupLogo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png
             * groupName : 徐泾羽毛球俱乐部
             * groupSignupId : 32
             * id : 1
             * status : 1
             * userId : 3
             */

            private int activityGovernmentId;
            private long createTime;
            private String groupLogo;
            private String groupName;
            private int groupSignupId;
            private int id;
            private String status;
            private int userId;

            public int getActivityGovernmentId() {
                return activityGovernmentId;
            }

            public void setActivityGovernmentId(int activityGovernmentId) {
                this.activityGovernmentId = activityGovernmentId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public int getGroupSignupId() {
                return groupSignupId;
            }

            public void setGroupSignupId(int groupSignupId) {
                this.groupSignupId = groupSignupId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
