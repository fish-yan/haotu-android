package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CourseOrderBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"order_id":"1","coach_name":"王六","coach_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","project_type":"游泳","duration":"3.0","paid_time":"2019-04-12 17:14:33.0","sequence_no":"2323232","validate_no":"5656566","qr_code_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 1
         * coach_name : 王六
         * coach_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
         * project_type : 游泳
         * duration : 3.0
         * paid_time : 2019-04-12 17:14:33.0
         * sequence_no : 2323232
         * validate_no : 5656566
         * qr_code_url : https://i04picsos.sogoucdn.com/352533c4d37c1573
         */

        private String order_id;
        private String coach_name;
        private String coach_logo;
        private String project_type;
        private String duration;
        private String paid_time;
        private String sequence_no;
        private String validate_no;
        private String qr_code_url;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCoach_name() {
            return coach_name;
        }

        public void setCoach_name(String coach_name) {
            this.coach_name = coach_name;
        }

        public String getCoach_logo() {
            return coach_logo;
        }

        public void setCoach_logo(String coach_logo) {
            this.coach_logo = coach_logo;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPaid_time() {
            return paid_time;
        }

        public void setPaid_time(String paid_time) {
            this.paid_time = paid_time;
        }

        public String getSequence_no() {
            return sequence_no;
        }

        public void setSequence_no(String sequence_no) {
            this.sequence_no = sequence_no;
        }

        public String getValidate_no() {
            return validate_no;
        }

        public void setValidate_no(String validate_no) {
            this.validate_no = validate_no;
        }

        public String getQr_code_url() {
            return qr_code_url;
        }

        public void setQr_code_url(String qr_code_url) {
            this.qr_code_url = qr_code_url;
        }
    }
}
