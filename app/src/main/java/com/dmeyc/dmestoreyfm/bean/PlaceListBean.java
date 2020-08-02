package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class PlaceListBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"venue_id":"1","venue_name":"张三羽毛球馆","venue_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","project_type_name":"羽毛球","average_amount":"50","comment_score":"4.9","address":"临汾路88号","distance":"19.4km"}]
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
         * venue_id : 1
         * venue_name : 张三羽毛球馆
         * venue_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
         * project_type_name : 羽毛球
         * average_amount : 50
         * comment_score : 4.9
         * address : 临汾路88号
         * distance : 19.4km
         */

        private String venue_id;
        private String venue_name;
        private String venue_logo;
        private String project_type_name;
        private String average_amount;
        private String comment_score;
        private String address;
        private String distance;

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getVenue_logo() {
            return venue_logo;
        }

        public void setVenue_logo(String venue_logo) {
            this.venue_logo = venue_logo;
        }

        public String getProject_type_name() {
            return project_type_name;
        }

        public void setProject_type_name(String project_type_name) {
            this.project_type_name = project_type_name;
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
    }
}
