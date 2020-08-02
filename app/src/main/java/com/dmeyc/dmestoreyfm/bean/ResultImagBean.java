package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ResultImagBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":33,"groupId":3,"activityId":10,"url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzccHKAcyK7AAAfG2XBMC4767.jpg","uploadTime":1557955701000,"uploadUserId":34,"status":"1"},{"id":34,"groupId":3,"activityId":10,"url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzccHKAHN0SAABBQK9DucA976.jpg","uploadTime":1557955701000,"uploadUserId":34,"status":"1"},{"id":35,"groupId":3,"activityId":10,"url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzccHKASGtEAABKH8FGX2c257.jpg","uploadTime":1557955701000,"uploadUserId":34,"status":"1"}]
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
         * id : 33
         * groupId : 3
         * activityId : 10
         * url : http://192.168.0.104/group1/M00/00/09/wKgAaFzccHKAcyK7AAAfG2XBMC4767.jpg
         * uploadTime : 1557955701000
         * uploadUserId : 34
         * status : 1
         */

        private int id;
        private int groupId;
        private int activityId;
        private String url;
        private long uploadTime;
        private int uploadUserId;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
