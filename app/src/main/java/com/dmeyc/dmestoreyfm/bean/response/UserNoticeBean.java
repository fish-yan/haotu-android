package com.dmeyc.dmestoreyfm.bean.response;

import java.util.List;

/**
 * create by cxg on 2019/12/22
 */
public class UserNoticeBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"id":3,"userId":3,"videoId":167,"userLogo":"http://47.100.223.153:8888/group1/M00/00/03/rBNsuF1jrBKAfk2UAAAXB3oVaPk703.jpg","content":"亲","faceUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF34BpOAdfctAAA-Yp2Z8WE653.jpg","createTime":1576696024000,"timeStr":"1小时前","type":"4","operateContent":"","targetUserId":493,"nickName":"子元"},{"id":6,"userId":493,"videoId":167,"userLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","content":"亲","faceUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF34BpOAdfctAAA-Yp2Z8WE653.jpg","createTime":1576714052000,"timeStr":"1小时前","type":"4","operateContent":"","targetUserId":493,"nickName":"好兔5554"},{"id":7,"userId":493,"videoId":166,"userLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","content":"@Yan@ #我知道# @Yan.X@ 核桃煲汤核桃","faceUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF335DeARKxlAABh7in67s8755.png","createTime":1576714691000,"timeStr":"1小时前","type":"4","operateContent":"","targetUserId":493,"nickName":"好兔5554"},{"id":8,"userId":493,"videoId":166,"userLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","content":"@Yan@ #我知道# @Yan.X@ 核桃煲汤核桃","faceUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF335DeARKxlAABh7in67s8755.png","createTime":1576714694000,"timeStr":"1小时前","type":"4","operateContent":"","targetUserId":493,"nickName":"好兔5554"},{"id":9,"userId":493,"videoId":166,"userLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","content":"@Yan@ #我知道# @Yan.X@ 核桃煲汤核桃","faceUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF335DeARKxlAABh7in67s8755.png","createTime":1576714757000,"timeStr":"1小时前","type":"4","operateContent":"","targetUserId":493,"nickName":"好兔5554"}]
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
         * id : 3
         * userId : 3
         * videoId : 167
         * userLogo : http://47.100.223.153:8888/group1/M00/00/03/rBNsuF1jrBKAfk2UAAAXB3oVaPk703.jpg
         * content : 亲
         * faceUrl : http://101.44.2.178/group1/M00/00/26/wKgAaF34BpOAdfctAAA-Yp2Z8WE653.jpg
         * createTime : 1576696024000
         * timeStr : 1小时前
         * type : 4
         * operateContent :
         * targetUserId : 493
         * nickName : 子元
         */

        private String id;
        private String userId;
        private String videoId;
        private String userLogo;
        private String content;
        private String faceUrl;
        private String createTime;
        private String timeStr;
        private String type;
        private String operateContent;
        private String targetUserId;
        private String nickName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOperateContent() {
            return operateContent;
        }

        public void setOperateContent(String operateContent) {
            this.operateContent = operateContent;
        }

        public String getTargetUserId() {
            return targetUserId;
        }

        public void setTargetUserId(String targetUserId) {
            this.targetUserId = targetUserId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
