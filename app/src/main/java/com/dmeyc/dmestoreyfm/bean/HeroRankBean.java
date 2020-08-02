package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class HeroRankBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"group_name":"垂直网络运动群","battle_number":"200","project_type":"羽毛球","activity_venue_address":"杨浦区","img_url":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4--AWS_wAADFustiZb4810.jpg","successRate":"100.0%","distance":"4.2km","rank":"第1名","groupId":2},{"group_name":"企业","battle_number":"100","project_type":"羽毛球","activity_venue_address":null,"img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzr9dGAN7NKAABjpRLl0NI969.jpg","successRate":"0%","distance":"12958.1km","rank":"第2名","groupId":32},{"group_name":"闹哄哄","battle_number":"100","project_type":"羽毛球","activity_venue_address":null,"img_url":"http://192.168.0.104/group1/M00/00/0A/wKgAaFzm-oaAbVBaAACmWbETt4I800.jpg","successRate":"0%","distance":"12958.1km","rank":"第3名","groupId":30},{"group_name":"一定","battle_number":"100","project_type":"羽毛球","activity_venue_address":null,"img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzsXxSAQS8AAAEzPCddzEU517.jpg","successRate":"0%","distance":"12958.1km","rank":"第4名","groupId":34},{"group_name":"商户","battle_number":"100","project_type":"羽毛球","activity_venue_address":null,"img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzr9uWAEdwWAADYuO64I-Q167.jpg","successRate":"0%","distance":"12958.1km","rank":"第5名","groupId":33},{"group_name":"挑战你","battle_number":"20","project_type":"羽毛球","activity_venue_address":"政府路181弄","img_url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGFiAJiOoAAAvJ2sEydo970.jpg","successRate":"0%","distance":"1.6km","rank":"第6名","groupId":5},{"group_name":"俱乐部2","battle_number":"20","project_type":"羽毛球","activity_venue_address":"政府路181弄1号","img_url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","successRate":"0%","distance":"1.6km","rank":"第7名","groupId":3},{"group_name":"红红火火","battle_number":"0","project_type":"羽毛球","activity_venue_address":"国定东路","img_url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","successRate":"0%","distance":"260m","rank":"第8名","groupId":6},{"group_name":"哈哈哈","battle_number":"0","project_type":"排球","activity_venue_address":"国定东路","img_url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzdnjuAEsTSAAFy3A5py6A858.jpg","successRate":"0%","distance":"260m","rank":"第9名","groupId":10},{"group_name":"弄死","battle_number":"0","project_type":"台球","activity_venue_address":"政府路181弄","img_url":"http://192.168.0.104/group1/M00/00/09/wKgAaFzd1_2ALzWsAABET6Nie0k913.jpg","successRate":"0%","distance":"1.6km","rank":"第10名","groupId":13}]
     */

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

    public static class DataBean {
        /**
         * group_name : 垂直网络运动群
         * battle_number : 200
         * project_type : 羽毛球
         * activity_venue_address : 杨浦区
         * img_url : http://192.168.0.104/group1/M00/00/09/wKgAaFza4--AWS_wAADFustiZb4810.jpg
         * successRate : 100.0%
         * distance : 4.2km
         * rank : 第1名
         * groupId : 2
         */

        private String group_name;
        private String battle_number;
        private String project_type;
        private String activity_venue_address;
        private String img_url;
        private String successRate;
        private String distance;
        private String rank;
        private int groupId;

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getBattle_number() {
            return battle_number;
        }

        public void setBattle_number(String battle_number) {
            this.battle_number = battle_number;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getActivity_venue_address() {
            return activity_venue_address;
        }

        public void setActivity_venue_address(String activity_venue_address) {
            this.activity_venue_address = activity_venue_address;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(String successRate) {
            this.successRate = successRate;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }
    }
}
