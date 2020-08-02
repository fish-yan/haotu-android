package com.dmeyc.dmestoreyfm.video.systeminfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SystemInfoModel implements Serializable {


    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":653,"url":"1","title":"1","content":"2","createTime":"2019-10-13 11:28","isRead":0},{"id":490,"url":"1","title":"1","content":"2","createTime":"2019-10-13 11:25","isRead":0},{"id":327,"url":"1","title":"1","content":"2","createTime":"2019-10-13 11:25","isRead":0}]
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private String code;
    @SerializedName("data")
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
         * id : 653
         * url : 1
         * title : 1
         * content : 2
         * createTime : 2019-10-13 11:28
         * isRead : 0
         */

        @SerializedName("id")
        private int id;
        @SerializedName("url")
        private String url;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("isRead")
        private int isRead;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }
    }
}
