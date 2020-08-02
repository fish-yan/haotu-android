package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class TeachListBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"group_id":"11","coach_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","coach_name":"王六","project_type":"游泳","amount":null,"distance":12958.078601489393},{"group_id":"12","coach_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","coach_name":"王六","project_type":"游泳","amount":"80.00","distance":12958.078601489393},{"group_id":"9","coach_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","coach_name":"掌柜的","project_type":"瑜伽","amount":null,"distance":12958.078601489393},{"group_id":"10","coach_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","coach_name":"掌柜的","project_type":"瑜伽","amount":null,"distance":12958.078601489393}]
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
         * group_id : 11
         * coach_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
         * coach_name : 王六
         * project_type : 游泳
         * amount : null
         * distance : 12958.078601489393
         */

        private String group_id;
        private String coach_logo;
        private String coach_name;
        private String project_type;
        private Object amount;
        private String distance;
        /**
         * address : 临汾路88号
         * average_amount : 50
         * comment_score : 4.9
         * project_type_name : 羽毛球
         * venue_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
         * venue_name : 张三羽毛球馆
         */

        private String address;
        private String average_amount;
        private String comment_score;
        private String project_type_name;
        private String venue_logo;
        private String venue_name;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
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

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAverage_amount() {
            return average_amount;
        }

        public void setAverage_amount(String average_amount) {
            this.average_amount = average_amount;
        }

        public String getComment_score() {
            return comment_score;
        }

        public void setComment_score(String comment_score) {
            this.comment_score = comment_score;
        }

        public String getProject_type_name() {
            return project_type_name;
        }

        public void setProject_type_name(String project_type_name) {
            this.project_type_name = project_type_name;
        }

        public String getVenue_logo() {
            return venue_logo;
        }

        public void setVenue_logo(String venue_logo) {
            this.venue_logo = venue_logo;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }
    }
}
