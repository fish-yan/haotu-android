package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SystemResultBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : []
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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
         * groupName : A
         * groupPlans : [{"clearWinScore":315,"governmentActivityId":5,"groupId":4,"groupLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1cwAWAA2TpAACbHcb3e1U646.jpg","groupName":"沙登羽毛球俱乐部","groupNo":1,"id":296,"loseTimes":0,"originGroupNo":1,"originTeamId":4,"sort":1,"stage":"2","teamId":4,"winTimes":3},{"clearWinScore":105,"governmentActivityId":5,"groupId":3,"groupLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1aJf-ARZdsAAA_WItSvV4403.jpg","groupName":"皇室羽毛球俱乐部","groupNo":1,"id":295,"loseTimes":1,"originGroupNo":1,"originTeamId":3,"sort":2,"stage":"2","teamId":3,"winTimes":2}]
         */
            private String groupName;
            private List<GroupPlansBean> groupPlans;

            public String getGroupName () {
                return groupName;
            }

            public void setGroupName (String groupName){
                this.groupName = groupName;
            }

            public List<GroupPlansBean> getGroupPlans () {
                return groupPlans;
            }

            public void setGroupPlans(List < GroupPlansBean > groupPlans){
                this.groupPlans = groupPlans;
            }

            public static class GroupPlansBean {
                /**
                 * clearWinScore : 315
                 * governmentActivityId : 5
                 * groupId : 4
                 * groupLogo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1cwAWAA2TpAACbHcb3e1U646.jpg
                 * groupName : 沙登羽毛球俱乐部
                 * groupNo : 1
                 * id : 296
                 * loseTimes : 0
                 * originGroupNo : 1
                 * originTeamId : 4
                 * sort : 1
                 * stage : 2
                 * teamId : 4
                 * winTimes : 3
                 */

                private int clearWinScore;
                private int governmentActivityId;
                private int groupId;
                private String groupLogo;
                private String groupName;
                private int groupNo;
                private int id;
                private int loseTimes;
                private int originGroupNo;
                private int originTeamId;
                private int sort;
                private String stage;
                private int teamId;
                private int winTimes;

                public int getClearWinScore() {
                    return clearWinScore;
                }

                public void setClearWinScore(int clearWinScore) {
                    this.clearWinScore = clearWinScore;
                }

                public int getGovernmentActivityId() {
                    return governmentActivityId;
                }

                public void setGovernmentActivityId(int governmentActivityId) {
                    this.governmentActivityId = governmentActivityId;
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

                public int getGroupNo() {
                    return groupNo;
                }

                public void setGroupNo(int groupNo) {
                    this.groupNo = groupNo;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getLoseTimes() {
                    return loseTimes;
                }

                public void setLoseTimes(int loseTimes) {
                    this.loseTimes = loseTimes;
                }

                public int getOriginGroupNo() {
                    return originGroupNo;
                }

                public void setOriginGroupNo(int originGroupNo) {
                    this.originGroupNo = originGroupNo;
                }

                public int getOriginTeamId() {
                    return originTeamId;
                }

                public void setOriginTeamId(int originTeamId) {
                    this.originTeamId = originTeamId;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getStage() {
                    return stage;
                }

                public void setStage(String stage) {
                    this.stage = stage;
                }

                public int getTeamId() {
                    return teamId;
                }

                public void setTeamId(int teamId) {
                    this.teamId = teamId;
                }

                public int getWinTimes() {
                    return winTimes;
                }

                public void setWinTimes(int winTimes) {
                    this.winTimes = winTimes;
                }
            }
        }
    }

