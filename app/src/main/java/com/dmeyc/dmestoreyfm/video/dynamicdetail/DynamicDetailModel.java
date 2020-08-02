package com.dmeyc.dmestoreyfm.video.dynamicdetail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DynamicDetailModel implements Serializable {


    /**
     * code : 200
     * data : {"address":"string","commentCount":1,"companyDetailDTO":{"activity_venue_address":"string","area":"string","average_price":0,"city":"string","group_logo":"string","group_name":"string","images":[{"activityId":1,"groupId":1,"id":1,"status":0,"uploadTime":201910120221,"uploadUserId":1,"url":"http://xx"}],"latitude_value":"string","longitude_value":"string","phone_no":"string","project_type":"string","province":"string","tel_no":"string"},"content":"string","createTime":"2019-10-10 10:03:00","id":1,"imageUrls":["http://xx","http://xx"],"isLike":"1 是 0否","isStore":"1 是 0否","likeCount":1,"seeCount":1,"storeCount":1,"topicImageDTOs":[{"content":"string","createTime":"2019-10-10 10:03:00","id":1,"imageUrl":"http://xx","usedNum":1}],"type":1,"url":"http://xx","userImageUrl":"http://xx","userNickName":"张三"}
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
         * address : string
         * commentCount : 1
         * companyDetailDTO : {"activity_venue_address":"string","area":"string","average_price":0,"city":"string","group_logo":"string","group_name":"string","images":[{"activityId":1,"groupId":1,"id":1,"status":0,"uploadTime":201910120221,"uploadUserId":1,"url":"http://xx"}],"latitude_value":"string","longitude_value":"string","phone_no":"string","project_type":"string","province":"string","tel_no":"string"}
         * content : string
         * createTime : 2019-10-10 10:03:00
         * id : 1
         * imageUrls : ["http://xx","http://xx"]
         * isLike : 1 是 0否
         * isStore : 1 是 0否
         * likeCount : 1
         * seeCount : 1
         * storeCount : 1
         * topicImageDTOs : [{"content":"string","createTime":"2019-10-10 10:03:00","id":1,"imageUrl":"http://xx","usedNum":1}]
         * type : 1
         * url : http://xx
         * userImageUrl : http://xx
         * userNickName : 张三
         */

        @SerializedName("address")
        private String address;
        @SerializedName("commentCount")
        private int commentCount;
        @SerializedName("companyDetailDTO")
        private CompanyDetailDTOBean companyDetailDTO;
        @SerializedName("content")
        private String content;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("id")
        private int id;
        @SerializedName("isLike")
        private int isLike;
        @SerializedName("isStore")
        private int isStore;
        @SerializedName("likeCount")
        private int likeCount;
        @SerializedName("seeCount")
        private int seeCount;
        @SerializedName("storeCount")
        private int storeCount;
        @SerializedName("type")
        private int type;
        @SerializedName("url")
        private String url;
        @SerializedName("userImageUrl")
        private String userImageUrl;
        @SerializedName("userNickName")
        private String userNickName;
        @SerializedName("imageUrls")
        private List<ImageUrlBean> imageUrls;
        @SerializedName("topicImageDTOs")
        private List<TopicImageDTOsBean> topicImageDTOs;
        @SerializedName("txId")
        private String txId;
        @SerializedName("isFollow")
        private int isFollow;
        @SerializedName("userId")
        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public String getTxId() {
            return txId;
        }

        public void setTxId(String txId) {
            this.txId = txId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public CompanyDetailDTOBean getCompanyDetailDTO() {
            return companyDetailDTO;
        }

        public void setCompanyDetailDTO(CompanyDetailDTOBean companyDetailDTO) {
            this.companyDetailDTO = companyDetailDTO;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsLike() {
            return isLike;
        }

        public void setIsLike(int isLike) {
            this.isLike = isLike;
        }

        public int getIsStore() {
            return isStore;
        }

        public void setIsStore(int isStore) {
            this.isStore = isStore;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }

        public int getStoreCount() {
            return storeCount;
        }

        public void setStoreCount(int storeCount) {
            this.storeCount = storeCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUserImageUrl() {
            return userImageUrl;
        }

        public void setUserImageUrl(String userImageUrl) {
            this.userImageUrl = userImageUrl;
        }

        public String getUserNickName() {
            return userNickName;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }

        public List<ImageUrlBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlBean> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public List<TopicImageDTOsBean> getTopicImageDTOs() {
            return topicImageDTOs;
        }

        public void setTopicImageDTOs(List<TopicImageDTOsBean> topicImageDTOs) {
            this.topicImageDTOs = topicImageDTOs;
        }

        public static class ImageUrlBean{
            @SerializedName("id")
            private int id;
            @SerializedName("url")
            private String url;
            @SerializedName("videoId")
            private int videoId;
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }
        }

        public static class CompanyDetailDTOBean {
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
            @SerializedName("id")
            private int id;
            @SerializedName("groupType")
            private int groupType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGroupType() {
                return groupType;
            }

            public void setGroupType(int groupType) {
                this.groupType = groupType;
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

        public static class TopicImageDTOsBean {
            /**
             * content : string
             * createTime : 2019-10-10 10:03:00
             * id : 1
             * imageUrl : http://xx
             * usedNum : 1
             */

            @SerializedName("content")
            private String content;
            @SerializedName("createTime")
            private String createTime;
            @SerializedName("id")
            private int id;
            @SerializedName("imageUrl")
            private String imageUrl;
            @SerializedName("usedNum")
            private int usedNum;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getUsedNum() {
                return usedNum;
            }

            public void setUsedNum(int usedNum) {
                this.usedNum = usedNum;
            }
        }
    }
}
