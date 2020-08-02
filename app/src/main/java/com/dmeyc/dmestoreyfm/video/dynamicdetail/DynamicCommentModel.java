package com.dmeyc.dmestoreyfm.video.dynamicdetail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DynamicCommentModel implements Serializable {


    /**
     * code : 200
     * data : [{"content":"string","createTime":"2019-10-10 10:03:00","id":1,"isDel":"1 是 0否","isLike":"1 是 0否","likeCount":1,"userId":1,"userImageUrl":"http://xx","userNickName":"张三"}]
     * msg : ok
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : string
         * createTime : 2019-10-10 10:03:00
         * id : 1
         * isDel : 1 是 0否
         * isLike : 1 是 0否
         * likeCount : 1
         * userId : 1
         * userImageUrl : http://xx
         * userNickName : 张三
         */

        @SerializedName("content")
        private String content;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("id")
        private int id;
        @SerializedName("isDel")
        private int isDel;
        @SerializedName("isLike")
        private int isLike;
        @SerializedName("likeCount")
        private int likeCount;
        @SerializedName("userId")
        private int userId;
        @SerializedName("userImageUrl")
        private String userImageUrl;
        @SerializedName("userNickName")
        private String userNickName;

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

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getIsLike() {
            return isLike;
        }

        public void setIsLike(int isLike) {
            this.isLike = isLike;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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
    }
}
