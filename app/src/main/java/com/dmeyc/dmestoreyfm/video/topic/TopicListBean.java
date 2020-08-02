package com.dmeyc.dmestoreyfm.video.topic;

import java.io.Serializable;
import java.util.List;

public class TopicListBean implements Serializable {

    private String msg;
    private String code;
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

    public   class DataBean {
//            "content": "string",
//                    "createTime": "2019-10-10 10:03:00",
//                    "id": 1,
//                    "imageUrl": "http://xx",
//                    "usedNum": 1
        private String content;
        private String createTime;
        private int id;
        private String imageUrl;
        private String usedNum;

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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getUsedNum() {
            return usedNum;
        }

        public void setUsedNum(String usedNum) {
            this.usedNum = usedNum;
        }
    }
}
