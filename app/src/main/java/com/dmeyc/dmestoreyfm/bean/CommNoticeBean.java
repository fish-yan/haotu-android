package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class CommNoticeBean implements Serializable {

    /**
     * code : 200
     * data : {"activityList":[{"activityName":"你好","activity_id":45,"duration":2,"end_time":"2019-07-22 22:00:00","isPk":"0","sign_up_no":0,"start_time":"2019-07-22 20:00:00","status":"1","total_no":4,"venueName":"我不"}]}
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
        private List<ActivityListBean> activityList;
          private String notice;
          private String groupTotalNo;
          private String groupName;

        public String getGroupName() {
            return groupName;
        }

        public String getGroupTotalNo() {
            return groupTotalNo;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public void setGroupTotalNo(String groupTotalNo) {
            this.groupTotalNo = groupTotalNo;
        }

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
            this.activityList = activityList;
        }

        public static class ActivityListBean {
            /**
             * activityName : 你好
             * activity_id : 45
             * duration : 2
             * end_time : 2019-07-22 22:00:00
             * isPk : 0
             * sign_up_no : 0
             * start_time : 2019-07-22 20:00:00
             * status : 1
             * total_no : 4
             * venueName : 我不
             */

            private String activityName;
            private int activity_id;
            private int duration;
            private String end_time;
            private String isPk;
            private int sign_up_no;
            private String start_time;
            private String status;
            private int total_no;
            private String venueName;

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getIsPk() {
                return isPk;
            }

            public void setIsPk(String isPk) {
                this.isPk = isPk;
            }

            public int getSign_up_no() {
                return sign_up_no;
            }

            public void setSign_up_no(int sign_up_no) {
                this.sign_up_no = sign_up_no;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getTotal_no() {
                return total_no;
            }

            public void setTotal_no(int total_no) {
                this.total_no = total_no;
            }

            public String getVenueName() {
                return venueName;
            }

            public void setVenueName(String venueName) {
                this.venueName = venueName;
            }
        }
    }
}
