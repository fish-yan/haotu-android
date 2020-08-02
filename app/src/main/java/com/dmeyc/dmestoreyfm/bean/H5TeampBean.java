package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class H5TeampBean implements Serializable {

    /**
     * code : 200
     * data : [{"createTime":1562013004000,"id":1,"imgUrl":"http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg","isPk":"1","url":"http://icdwebsite.cn"}]
     * msg : 操作成功
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * createTime : 1562013004000
         * id : 1
         * imgUrl : http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg
         * isPk : 1
         * url : http://icdwebsite.cn
         */

        private long createTime;
        private int id;
        private String imgUrl;
        private String isPk;
        private String url;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getIsPk() {
            return isPk;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
