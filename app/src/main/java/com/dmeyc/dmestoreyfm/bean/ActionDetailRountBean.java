package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ActionDetailRountBean implements Serializable {

    /**
     * code : 200
     * data : [{"activityId":190,"activityName":"一起来","address":"国定东路","distance":"260m","groupType":"1","members":[{"indentity":"1","nick_name":"规划","sex":"1","url":"http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg","user_id":39}],"signupNumber":0,"startTime":"2019-06-28 09:52:00","totalNumber":6}]
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
         * activityId : 190
         * activityName : 一起来
         * address : 国定东路
         * distance : 260m
         * groupType : 1
         * members : [{"indentity":"1","nick_name":"规划","sex":"1","url":"http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg","user_id":39}]
         * signupNumber : 0
         * startTime : 2019-06-28 09:52:00
         * totalNumber : 6
         */

        private int activityId;
        private String activityName;
        private String address;
        private String distance;
        private String groupType;
        private int signupNumber;
        private String startTime;
        private int totalNumber;
        private List<MembersBean> members;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public int getSignupNumber() {
            return signupNumber;
        }

        public void setSignupNumber(int signupNumber) {
            this.signupNumber = signupNumber;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public List<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(List<MembersBean> members) {
            this.members = members;
        }

        public static class MembersBean {
            /**
             * indentity : 1
             * nick_name : 规划
             * sex : 1
             * url : http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg
             * user_id : 39
             */

            private String indentity;
            private String nick_name;
            private String sex;
            private String url;
            private int user_id;

            public String getIndentity() {
                return indentity;
            }

            public void setIndentity(String indentity) {
                this.indentity = indentity;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }
    }
}
