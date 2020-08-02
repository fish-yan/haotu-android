package com.dmeyc.dmestoreyfm.video.shpodetail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShopDetailModel implements Serializable {

    /**
     * code : 200
     * data : {"activity_venue_address":"string","area":"string","average_price":0,"city":"string","group_logo":"string","group_name":"string","images":[{"activityId":1,"groupId":1,"id":1,"status":0,"uploadTime":201910120221,"uploadUserId":1,"url":"http://xx"}],"latitude_value":"string","longitude_value":"string","phone_no":"string","project_type":"string","province":"string","tel_no":"string"}
     * msg : ok
     */

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * activity_venue_address : string
         * area : string
         * average_price : 0
         * city : string
         * group_logo : string
         * group_name : string
         * images : [{"activityId":1,"groupId":1,"id":1,"status":0,"uploadTime":201910120221,"uploadUserId":1,"url":"http://xx"}]
         * latitude_value : string
         * longitude_value : string
         * phone_no : string
         * project_type : string
         * province : string
         * tel_no : string
         */

        @SerializedName("activity_venue_address")
        private String activityVenueAddress;
        @SerializedName("area")
        private String area;
        @SerializedName("average_price")
        private int averagePrice;
        @SerializedName("city")
        private String city;
        @SerializedName("group_logo")
        private String groupLogo;
        @SerializedName("group_name")
        private String groupName;
        @SerializedName("latitude_value")
        private String latitudeValue;
        @SerializedName("longitude_value")
        private String longitudeValue;
        @SerializedName("phone_no")
        private String phoneNo;
        @SerializedName("project_type")
        private String projectType;
        @SerializedName("province")
        private String province;
        @SerializedName("tel_no")
        private String telNo;
        @SerializedName("images")
        private List<ImagesBean> images;
        @SerializedName("comment_count")
        private int commentCount;

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getActivityVenueAddress() {
            return activityVenueAddress;
        }

        public void setActivityVenueAddress(String activityVenueAddress) {
            this.activityVenueAddress = activityVenueAddress;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getAveragePrice() {
            return averagePrice;
        }

        public void setAveragePrice(int averagePrice) {
            this.averagePrice = averagePrice;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getGroupLogo() {
            return groupLogo;
        }

        public void setGroupLogo(String groupLogo) {
            this.groupLogo = groupLogo;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getLatitudeValue() {
            return latitudeValue;
        }

        public void setLatitudeValue(String latitudeValue) {
            this.latitudeValue = latitudeValue;
        }

        public String getLongitudeValue() {
            return longitudeValue;
        }

        public void setLongitudeValue(String longitudeValue) {
            this.longitudeValue = longitudeValue;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getTelNo() {
            return telNo;
        }

        public void setTelNo(String telNo) {
            this.telNo = telNo;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * activityId : 1
             * groupId : 1
             * id : 1
             * status : 0
             * uploadTime : 201910120221
             * uploadUserId : 1
             * url : http://xx
             */

            @SerializedName("activityId")
            private int activityId;
            @SerializedName("groupId")
            private int groupId;
            @SerializedName("id")
            private int id;
            @SerializedName("status")
            private int status;
            @SerializedName("uploadTime")
            private long uploadTime;
            @SerializedName("uploadUserId")
            private int uploadUserId;
            @SerializedName("url")
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
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
}
