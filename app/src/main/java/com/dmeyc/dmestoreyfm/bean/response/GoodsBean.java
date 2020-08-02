package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by cxg on 2019/12/9
 */
public class GoodsBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"user_token":null,"name":"陈1","price":32,"discountPrice":1,"tag":"玩","img":"http://101.44.2.178/group1/M00/00/25/wKgAaF31vvSABgk7AAB9QJhpPnA802.jpg","startTime":null,"endTime":null,"peopleNumber":null,"remark":null,"type":"1","createTime":1576386292000,"address":null,"id":8,"userId":493}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_token : null
         * name : 陈1
         * price : 32.0
         * discountPrice : 1.0
         * tag : 玩
         * img : http://101.44.2.178/group1/M00/00/25/wKgAaF31vvSABgk7AAB9QJhpPnA802.jpg
         * startTime : null
         * endTime : null
         * peopleNumber : null
         * remark : null
         * type : 1
         * createTime : 1576386292000
         * address : null
         * id : 8
         * userId : 493
         */

        private String name;
        private double price;
        private double discountPrice;
        private String tag;
        private String img;
        private String startTime;
        private String endTime;
        private String peopleNumber;
        private String remark;
        private String type;
        private long createTime;
        private String address;
        private String id;
        private int userId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPeopleNumber() {
            return peopleNumber;
        }

        public void setPeopleNumber(String peopleNumber) {
            this.peopleNumber = peopleNumber;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
