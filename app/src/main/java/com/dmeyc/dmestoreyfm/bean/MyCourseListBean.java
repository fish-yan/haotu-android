package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class MyCourseListBean implements Serializable {


    /**
     * code : 200
     * data : [{"amount":"88.00","coach_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","coach_name":"王六","course_id":"36","duration":"3.0","order_id":"1","project_type":"游泳","status":"已预约"}]
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
         * amount : 88.00
         * coach_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
         * coach_name : 王六
         * course_id : 36
         * duration : 3.0
         * order_id : 1
         * project_type : 游泳
         * status : 已预约
         */

        private String amount;
        private String coach_logo;
        private String coach_name;
        private String course_id;
        private String duration;
        private String order_id;
        private String project_type;
        private String status;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCoach_logo() {
            return coach_logo;
        }

        public void setCoach_logo(String coach_logo) {
            this.coach_logo = coach_logo;
        }

        public String getCoach_name() {
            return coach_name;
        }

        public void setCoach_name(String coach_name) {
            this.coach_name = coach_name;
        }

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
