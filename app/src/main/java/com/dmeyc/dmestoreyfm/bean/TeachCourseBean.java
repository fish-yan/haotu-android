package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class TeachCourseBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_name":"蛙泳教学","course_amount":"88.00","project_type":"游泳","course_remark":"蛙泳介绍","payList":[{"pay_code":"1","pay_name":"微信"},{"pay_code":"2","pay_name":"支付宝"},{"pay_code":"3","pay_name":"银行卡"},{"pay_code":"4","pay_name":"会员"}]}
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
         * group_logo : https://i04picsos.sogoucdn.com/352533c4d37c1573
         * course_name : 蛙泳教学
         * course_amount : 88.00
         * project_type : 游泳
         * course_remark : 蛙泳介绍
         * payList : [{"pay_code":"1","pay_name":"微信"},{"pay_code":"2","pay_name":"支付宝"},{"pay_code":"3","pay_name":"银行卡"},{"pay_code":"4","pay_name":"会员"}]
         */

        private String group_logo;
        private String course_name;
        private String course_amount;
        private String project_type;
        private String course_remark;
        private List<PayListBean> payList;

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_amount() {
            return course_amount;
        }

        public void setCourse_amount(String course_amount) {
            this.course_amount = course_amount;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getCourse_remark() {
            return course_remark;
        }

        public void setCourse_remark(String course_remark) {
            this.course_remark = course_remark;
        }

        public List<PayListBean> getPayList() {
            return payList;
        }

        public void setPayList(List<PayListBean> payList) {
            this.payList = payList;
        }

        public static class PayListBean {
            /**
             * pay_code : 1
             * pay_name : 微信
             */

            private String pay_code;
            private String pay_name;

            public String getPay_code() {
                return pay_code;
            }

            public void setPay_code(String pay_code) {
                this.pay_code = pay_code;
            }

            public String getPay_name() {
                return pay_name;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }
        }
    }
}
