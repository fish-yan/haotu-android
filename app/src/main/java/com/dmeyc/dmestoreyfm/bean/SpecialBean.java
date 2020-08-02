package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class SpecialBean {


    /**
     * code : 0
     * msg : 成功
     * data : [{"id":1,"createDate":"2017-12-01 19:29:36","modifyDate":"2017-12-09 19:29:53","version":1,"image":"special_1.gif","title":"易经大师教你怎么穿衣","info":"易经大师教你怎么穿衣","url":"https://storeapi.91moshow.com:8078/shop-controller/special_03"},{"id":2,"createDate":"2017-12-08 19:29:40","modifyDate":"2017-12-22 19:29:57","version":1,"image":"main_zhuanti2.jpeg","title":"你的\u201c星\u201d范儿全在这里了","info":"你的\u201c星\u201d范儿全在这里了","url":"https://storeapi.91moshow.com:8078/shop-controller/special_04"}]
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

    public static class DataBean {
        /**
         * id : 1
         * createDate : 2017-12-01 19:29:36
         * modifyDate : 2017-12-09 19:29:53
         * version : 1
         * image : special_1.gif
         * title : 易经大师教你怎么穿衣
         * info : 易经大师教你怎么穿衣
         * url : https://storeapi.91moshow.com:8078/shop-controller/special_03
         */

        private int id;
        private String createDate;
        private String modifyDate;
        private int version;
        private String image;
        private String title;
        private String info;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
