package com.dmeyc.dmestoreyfm.bean.response;

import java.util.List;

/**
 * create by cxg on 2019/12/2
 */
public class VideoItemBean extends BaseRespBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cover_url : string
         * id : 0
         * like_count : 0
         * type : 0
         */

        private String cover_url;
        private int id;
        private int like_count;
        private int type;

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
