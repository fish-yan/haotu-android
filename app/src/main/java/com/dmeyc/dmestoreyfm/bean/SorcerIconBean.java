package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SorcerIconBean implements Serializable {

    /**
     * code : 200
     * data : [{"activityId":34,"groupId":9,"id":1638,"status":"1","uploadTime":1556614872000,"uploadUserId":19,"url":"http://192.168.0.104/group1/M00/00/06/wKgAaFzItpyALKH2AAEQu2UwE4M096.jpg"},{"activityId":34,"groupId":9,"id":1639,"status":"1","uploadTime":1556614872000,"uploadUserId":19,"url":"http://192.168.0.104/group1/M00/00/06/wKgAaFzItp2AA3e7AANl3DjVVX4170.png"},{"activityId":34,"groupId":9,"id":1640,"status":"1","uploadTime":1556614872000,"uploadUserId":19,"url":"http://192.168.0.104/group1/M00/00/06/wKgAaFzItp2Ad-bGAAE8pKT_ugM930.png"}]
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
         * activityId : 34
         * groupId : 9
         * id : 1638
         * status : 1
         * uploadTime : 1556614872000
         * uploadUserId : 19
         * url : http://192.168.0.104/group1/M00/00/06/wKgAaFzItpyALKH2AAEQu2UwE4M096.jpg
         */

        private int activityId;
        private int groupId;
        private int id;
        private String status;
        private long uploadTime;
        private int uploadUserId;
        private String url;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
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

        public long getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(long uploadTime) {
            this.uploadTime = uploadTime;
        }

        public int getUploadUserId() {
            return uploadUserId;
        }

        public void setUploadUserId(int uploadUserId) {
            this.uploadUserId = uploadUserId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
