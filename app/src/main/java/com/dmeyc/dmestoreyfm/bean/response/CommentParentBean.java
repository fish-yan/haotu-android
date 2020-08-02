package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by cxg on 2019/12/8
 */
public class CommentParentBean extends BaseRespBean {
    /**
     * code : 200
     * data : {"commentList":[{"id":"200","userId":"200","userImageUrl":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","userNickName":"Hotu4682","content":"哈嘿","createTime":"2019-12-09 13:39","likeCount":"200","isLike":"200","isDel":"200","videoId":"200","parentId":"200","commentNum":"200"}],"count":"200"}
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
         * commentList : [{"id":"200","userId":"200","userImageUrl":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","userNickName":"Hotu4682","content":"哈嘿","createTime":"2019-12-09 13:39","likeCount":"200","isLike":"200","isDel":"200","videoId":"200","parentId":"200","commentNum":"200"}]
         * count : 200
         */

        private int count;
        private List<CommentListBean> commentList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class CommentListBean {
            /**
             * id : 200
             * userId : 200
             * userImageUrl : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png
             * userNickName : Hotu4682
             * content : 哈嘿
             * createTime : 2019-12-09 13:39
             * likeCount : 200
             * isLike : 200
             * isDel : 200
             * videoId : 200
             * parentId : 200
             * commentNum : 200
             */

            private String id;
            private String userId;
            private String userImageUrl;
            private String userNickName;
            private String content;
            private String createTime;
            private String likeCount;
            private String isLike;
            private String isDel;
            private String videoId;
            private String parentId;
            private int commentNum;
            private String toUserName;

            public String getToUserName() {
                return toUserName;
            }

            public void setToUserName(String toUserName) {
                this.toUserName = toUserName;
            }

            // 内部用的，非接口返回
            private List<CommentListBean> commentListBean;
            private boolean isOpen;

            public boolean isOpen() {
                return isOpen;
            }

            public void setOpen(boolean open) {
                isOpen = open;
            }

            public List<CommentListBean> getCommentListBean() {
                return commentListBean;
            }

            public void setCommentListBean(List<CommentListBean> commentListBean) {
                this.commentListBean = commentListBean;
            }

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

            public String getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(String likeCount) {
                this.likeCount = likeCount;
            }

            public String getIsLike() {
                return isLike;
            }

            public void setIsLike(String isLike) {
                this.isLike = isLike;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }
        }
    }
//    private List<DataBean> data;
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public class DataBean {
//
//    }

}
