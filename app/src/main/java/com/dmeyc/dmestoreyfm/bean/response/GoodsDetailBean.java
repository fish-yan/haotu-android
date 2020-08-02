package com.dmeyc.dmestoreyfm.bean.response;

/**
 * create by cxg on 2020/1/1
 */
public class GoodsDetailBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"user_token":null,"name":"陈1","price":32,"discountPrice":1,"tag":"玩","img":"http://101.44.2.178/group1/M00/00/25/wKgAaF31vvSABgk7AAB9QJhpPnA802.jpg","startTime":null,"endTime":null,"peopleNumber":null,"remark":null,"type":"1","createTime":1576386292000,"address":null,"properties":null,"id":8,"userId":493,"longitude":null,"latitude":null,"phoneNo":"18650288762"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * properties : null
         * id : 8
         * userId : 493
         * longitude : null
         * latitude : null
         * phoneNo : 18650288762
         */

        private String name;
        private String price;
        private String discountPrice;
        private String tag;
        private String img;
        private String remark;
        private String type;
        private long createTime;
        private String address;
        private String properties;
        private int id;
        private int userId;
        private String longitude;
        private String latitude;
        private String phoneNo;
        private String startTime;
        private String endTime;
        private String peopleNumber;

        public String getPeopleNumber() {
            return peopleNumber;
        }

        public void setPeopleNumber(String peopleNumber) {
            this.peopleNumber = peopleNumber;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(String discountPrice) {
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

        public String getProperties() {
            return properties;
        }

        public void setProperties(String properties) {
            this.properties = properties;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
    }
}
