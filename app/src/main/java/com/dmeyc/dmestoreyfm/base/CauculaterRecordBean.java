package com.dmeyc.dmestoreyfm.base;

import java.io.Serializable;
import java.util.List;

public class CauculaterRecordBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":57,"company_name":null,"projectType":null,"activityTime":"2019-05-08 10:44:00","status":"1"},{"id":58,"company_name":null,"projectType":null,"activityTime":"2019-05-08 10:50:00","status":"1"},{"id":60,"company_name":null,"projectType":null,"activityTime":"2019-05-08 10:50:00","status":"1"},{"id":61,"company_name":null,"projectType":null,"activityTime":"2019-05-08 10:57:00","status":"1"},{"id":62,"company_name":null,"projectType":null,"activityTime":"2019-05-08 11:26:00","status":"1"},{"id":63,"company_name":null,"projectType":null,"activityTime":"2019-05-08 11:37:00","status":"1"},{"id":64,"company_name":null,"projectType":null,"activityTime":"2019-05-08 11:39:00","status":"1"},{"id":65,"company_name":null,"projectType":null,"activityTime":"2019-05-08 11:44:00","status":"1"},{"id":66,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:06:00","status":"1"},{"id":67,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:09:00","status":"1"},{"id":68,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:13:00","status":"1"},{"id":69,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:23:00","status":"1"},{"id":70,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:26:00","status":"1"},{"id":71,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:26:00","status":"1"},{"id":72,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:26:00","status":"1"},{"id":73,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:30:00","status":"1"},{"id":74,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:32:00","status":"1"},{"id":75,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:32:00","status":"1"},{"id":76,"company_name":null,"projectType":null,"activityTime":"2019-05-08 12:38:00","status":"1"},{"id":77,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:00:00","status":"1"},{"id":78,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:00:00","status":"1"},{"id":79,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:28:00","status":"1"},{"id":80,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:28:00","status":"1"},{"id":81,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:34:00","status":"1"},{"id":82,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:34:00","status":"1"},{"id":83,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:46:00","status":"1"},{"id":84,"company_name":null,"projectType":null,"activityTime":"2019-05-08 13:54:00","status":"1"},{"id":85,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:00:00","status":"1"},{"id":86,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:00:00","status":"1"},{"id":87,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:00:00","status":"1"},{"id":88,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:06:00","status":"1"},{"id":89,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:06:00","status":"1"},{"id":90,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:10:00","status":"1"},{"id":91,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:10:00","status":"1"},{"id":92,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:15:00","status":"1"},{"id":93,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:19:00","status":"1"},{"id":94,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:21:00","status":"1"},{"id":95,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:49:00","status":"1"},{"id":96,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:51:00","status":"1"},{"id":97,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:55:00","status":"1"},{"id":98,"company_name":null,"projectType":null,"activityTime":"2019-05-08 14:58:00","status":"1"},{"id":99,"company_name":null,"projectType":null,"activityTime":"2019-05-08 15:04:00","status":"1"},{"id":100,"company_name":null,"projectType":null,"activityTime":"2019-05-08 15:31:00","status":"1"},{"id":101,"company_name":null,"projectType":null,"activityTime":"2019-05-08 15:36:00","status":"1"},{"id":102,"company_name":null,"projectType":null,"activityTime":"2019-05-08 15:41:00","status":"2"}]
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
         * id : 57
         * company_name : null
         * projectType : null
         * activityTime : 2019-05-08 10:44:00
         * status : 1
         */

        private int id;
        private String company_name;
        private String projectType;
        private String activityTime;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getActivityTime() {
            return activityTime;
        }

        public void setActivityTime(String activityTime) {
            this.activityTime = activityTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
