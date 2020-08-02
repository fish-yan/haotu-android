package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class MyJoinCommBean implements Serializable {

    /**
     * code : 200
     * data : [{"address":"双阳北路395-1号","group_id":"2","group_logo":"http://192.168.0.104/group1/M00/00/1D/wKgAaF1UJXWAYpNiAAFy98anLcQ493.jpg","group_name":"雷神羽毛球俱乐部","group_type":"1"},{"address":"金园六路319号","group_id":"8","group_logo":"http://192.168.0.104/group1/M00/00/17/wKgAaF1AZWSAQTIQAAAsB8Sa4gM078.png","group_name":"好兔羽毛球俱乐部","group_type":"1"},{"address":"国定东路","group_id":"22","group_logo":"http://192.168.0.104/group1/M00/00/1F/wKgAaF1cQGqAerpLAADaQ20WtHQ950.jpg","group_name":"教练机构","group_type":"3"},{"address":"国定东路","group_id":"23","group_logo":"http://192.168.0.104/group1/M00/00/1F/wKgAaF1fDS2AY0-bAAFSOZfAhJE055.jpg","group_name":"这两款","group_type":"3"},{"address":"国定东路","group_id":"30","group_logo":"http://192.168.0.104/group1/M00/00/20/wKgAaF1gB7mANl7eAAFcJMFpPHI545.jpg","group_name":"过滤","group_type":"3"},{"address":"国定东路","group_id":"35","group_logo":"http://192.168.0.104/group1/M00/00/20/wKgAaF1kBg2AGx5VAADApbRFsRQ993.jpg","group_name":"商户","group_type":"5"},{"address":"国定东路","group_id":"36","group_logo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","group_name":"普通安卓群","group_type":"1"},{"address":"新镇路","group_id":"41","group_logo":"http://192.168.0.104/group1/M00/00/22/wKgAaF1oKKWAPXZIAAA62qPiWrM833.jpg","group_name":"羽多","group_type":"1"},{"address":"新镇路388号","group_id":"44","group_logo":"http://192.168.0.104/group1/M00/00/22/wKgAaF1oU0eAbxlyAAf_v3QbacM335.jpg","group_name":"沙棘\u2026\u2026","group_type":"5"}]
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
         * address : 双阳北路395-1号
         * group_id : 2
         * group_logo : http://192.168.0.104/group1/M00/00/1D/wKgAaF1UJXWAYpNiAAFy98anLcQ493.jpg
         * group_name : 雷神羽毛球俱乐部
         * group_type : 1
         */

        private String address;
        private String group_id;
        private String group_logo;
        private String group_name;
        private String group_type;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }
    }
}
