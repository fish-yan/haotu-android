package com.dmeyc.dmestoreyfm.video.index;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IndexDynamicBean extends BaseRespBean {
    private List<DataBean> data;

    /**
     * code : 200
     * data : {"id":167,"userId":493,"url":null,"content":"亲","address":null,"createTime":1576535700000,"commentCount":4,"likeCount":2,"storeCount":0,"forwardCount":0,"seeCount":0,"isLike":1,"isStore":0,"isFollow":0,"userImageUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF3-bGCARQR3AAA0mp_but8439.png","userNickName":"好兔5554","imageUrls":[{"id":null,"videoId":167,"url":"http://101.44.2.178/group1/M00/00/26/wKgAaF34BpKAezAtAAR5cyeDnEg298.jpg","thumbnailUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF34BpOAdfctAAA-Yp2Z8WE653.jpg"}],"type":2,"geoHash":"uxypyzupzxvr","coverUrl":null,"txId":null,"linkedType":3,"linkedId":145,"linkedTitle":null,"linkedImage":null,"linkedUserId":null,"topicImageDTOs":[],"companyDetailDTO":null}
     */
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    public class DataBean implements Serializable, MultiItemEntity {
        //           "address": "string",
//             "commentCount": 1,
//             "content": "string",
//             "createTime": "2019-10-10 10:03:00",
//             "id": 1,
//             "imageUrls": [
//                   "http://xx",
//                   "http://xx"
//                   ],
//                   "isLike": "1 是 0否",
//                   "isStore": "1 是 0否",
//                   "likeCount": 1,
//                   "storeCount": 1,
//                   "type": 1,
//                   "url": "http://xx",
//                   "userImageUrl": "http://xx",
//                   "userNickName": "张三"
        private String address;
        private String commentCount;
        private String content;
        private String createTime;
        private int id;
        private List<ImageUrlBean> imageUrls;
        private int isLike;
        private int isStore;
        private int likeCount;
        private String storeCount;
        private int type;
        private String url;
        private String userImageUrl;
        private String userNickName;
        private String coverUrl;
        private String distance;
        private String linkedType;
        private String userId;
        private int seeCount;
        private String forwardCount;
        private String linkedId;
        private String linkedUserId;
        private String linkedTitle;
        private String linkedImage;
        private String isLiveBroadCast;// 1 直播中
        private String liveBroadCastActivityId;// 直播activityId

        public String getIsLiveBroadCast() {
            return isLiveBroadCast;
        }

        public void setIsLiveBroadCast(String isLiveBroadCast) {
            this.isLiveBroadCast = isLiveBroadCast;
        }

        public String getLiveBroadCastActivityId() {
            return liveBroadCastActivityId;
        }

        public void setLiveBroadCastActivityId(String liveBroadCastActivityId) {
            this.liveBroadCastActivityId = liveBroadCastActivityId;
        }

        public String getLinkedImage() {
            return linkedImage;
        }

        public void setLinkedImage(String linkedImage) {
            this.linkedImage = linkedImage;
        }

        public String getLinkedTitle() {
            return linkedTitle;
        }

        public void setLinkedTitle(String linkedTitle) {
            this.linkedTitle = linkedTitle;
        }

        public String getLinkedUserId() {
            return linkedUserId;
        }

        public void setLinkedUserId(String linkedUserId) {
            this.linkedUserId = linkedUserId;
        }

        public String getLinkedId() {
            return linkedId;
        }

        public void setLinkedId(String linkedId) {
            this.linkedId = linkedId;
        }

        public String getForwardCount() {
            return forwardCount;
        }

        public void setForwardCount(String forwardCount) {
            this.forwardCount = forwardCount;
        }

        public String getLinkedType() {
            return linkedType;
        }

        public void setLinkedType(String linkedType) {
            this.linkedType = linkedType;
        }

        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
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

        public List<ImageUrlBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlBean> imageUrls) {
            this.imageUrls = imageUrls;
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

        public String getStoreCount() {
            return storeCount;
        }

        public void setStoreCount(String storeCount) {
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

        private int itemType = Constant.AdapterItemType.TYPE_HOME_VIDEO;

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }


        public class ImageUrlBean implements Serializable {
            private int id;
            private String url;
            private String thumbnailUrl;
            private int videoId;

            public String getThumbnailUrl() {
                return thumbnailUrl;
            }

            public void setThumbnailUrl(String thumbnailUrl) {
                this.thumbnailUrl = thumbnailUrl;
            }

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
    }
}
