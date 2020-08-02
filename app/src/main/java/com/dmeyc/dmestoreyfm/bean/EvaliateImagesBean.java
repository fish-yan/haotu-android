package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class EvaliateImagesBean implements Serializable{

    /**
     * code : 200
     * msg : 操作成功
     * data : [{"title":null,"source":"https://storeapi.91moshow.com:8078/images/upload/source/70151545633257883181.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/70151545633257883181.jpg"},{"title":null,"source":"https://storeapi.91moshow.com:8078/images/upload/source/70151545633258294519.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/70151545633258294519.jpg"}]
     */

    private int code;
    private String msg;
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

    public static class DataBean implements Serializable{
        /**
         * title : null
         * source : https://storeapi.91moshow.com:8078/images/upload/source/70151545633257883181.jpg
         * thumbnail : https://storeapi.91moshow.com:8078/images/upload/thumbnail/70151545633257883181.jpg
         */

        private Object title;
        private String source;
        private String thumbnail;

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title=" + title +
                    ", source='" + source + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    '}';
        }
    }
}
