package com.dmeyc.dmestoreyfm.ui;

import java.io.Serializable;
import java.util.List;

public class GetH5PicBean implements Serializable {

    /**
     * code : 200
     * data : {"activityId":10,"createTime":1562100972000,"h5ShareImageList":[{"content":"娜可露露","createTime":1562100972000,"id":154,"shareId":81,"url":"http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSARebhAAEmZoLG-tQ722.jpg"},{"content":"努力","createTime":1562100972000,"id":155,"shareId":81,"url":"http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSAMkScAAGU6Srumos524.jpg"},{"content":"网络科技","createTime":1562100972000,"id":156,"shareId":81,"url":"http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSAZQhNAAGSW3iepU8559.jpg"}],"id":81,"isPk":"1","userId":39}
     * msg : 操作成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * activityId : 10
         * createTime : 1562100972000
         * h5ShareImageList : [{"content":"娜可露露","createTime":1562100972000,"id":154,"shareId":81,"url":"http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSARebhAAEmZoLG-tQ722.jpg"},{"content":"努力","createTime":1562100972000,"id":155,"shareId":81,"url":"http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSAMkScAAGU6Srumos524.jpg"},{"content":"网络科技","createTime":1562100972000,"id":156,"shareId":81,"url":"http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSAZQhNAAGSW3iepU8559.jpg"}]
         * id : 81
         * isPk : 1
         * userId : 39
         */

        private int activityId;
        private long createTime;
        private int id;
        private String isPk;
        private int userId;
        private List<H5ShareImageListBean> h5ShareImageList;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

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

        public String getIsPk() {
            return isPk;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<H5ShareImageListBean> getH5ShareImageList() {
            return h5ShareImageList;
        }

        public void setH5ShareImageList(List<H5ShareImageListBean> h5ShareImageList) {
            this.h5ShareImageList = h5ShareImageList;
        }

        public static class H5ShareImageListBean {
            /**
             * content : 娜可露露
             * createTime : 1562100972000
             * id : 154
             * shareId : 81
             * url : http://192.168.0.104/group1/M00/00/0D/wKgAaF0bxOSARebhAAEmZoLG-tQ722.jpg
             */

            private String content;
            private long createTime;
            private int id;
            private int shareId;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

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

            public int getShareId() {
                return shareId;
            }

            public void setShareId(int shareId) {
                this.shareId = shareId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
