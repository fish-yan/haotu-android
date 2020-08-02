package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class PkDataBean implements Serializable {

    /**
     * code : 200
     * data : {"herosList":[{"battle_number":"100","distance":"12958.1km","group_name":"一定","img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzsXxSAQS8AAAEzPCddzEU517.jpg","project_type":"羽毛球","rank":"第1名"},{"battle_number":"100","distance":"12958.1km","group_name":"闹哄哄","img_url":"http://192.168.0.104/group1/M00/00/0A/wKgAaFzm-oaAbVBaAACmWbETt4I800.jpg","project_type":"羽毛球","rank":"第2名"},{"battle_number":"100","distance":"12958.1km","group_name":"商户","img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzr9uWAEdwWAADYuO64I-Q167.jpg","project_type":"羽毛球","rank":"第3名"}],"pkActivity":{"address":"国定东路","distance":"260m","end_time":"2019-05-16 14:56:00","group_a_id":6,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","group_a_name":"红红火火","group_b_id":3,"group_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_b_name":"俱乐部2","group_pk_id":2,"group_pk_name":"家家户户","start_time":"2019-05-16 09:56:00","total_no":6},"rotationList":[{"id":125,"isLeaf":"1","itemCode":"1","itemName":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","parentCode":"PK_ROTATION","sortNo":1}]}
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
         * herosList : [{"battle_number":"100","distance":"12958.1km","group_name":"一定","img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzsXxSAQS8AAAEzPCddzEU517.jpg","project_type":"羽毛球","rank":"第1名"},{"battle_number":"100","distance":"12958.1km","group_name":"闹哄哄","img_url":"http://192.168.0.104/group1/M00/00/0A/wKgAaFzm-oaAbVBaAACmWbETt4I800.jpg","project_type":"羽毛球","rank":"第2名"},{"battle_number":"100","distance":"12958.1km","group_name":"商户","img_url":"http://192.168.0.104/group1/M00/00/0B/wKgAaFzr9uWAEdwWAADYuO64I-Q167.jpg","project_type":"羽毛球","rank":"第3名"}]
         * pkActivity : {"address":"国定东路","distance":"260m","end_time":"2019-05-16 14:56:00","group_a_id":6,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","group_a_name":"红红火火","group_b_id":3,"group_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_b_name":"俱乐部2","group_pk_id":2,"group_pk_name":"家家户户","start_time":"2019-05-16 09:56:00","total_no":6}
         * rotationList : [{"id":125,"isLeaf":"1","itemCode":"1","itemName":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","parentCode":"PK_ROTATION","sortNo":1}]
         */

        private PkActivityBean pkActivity;
        private List<HerosListBean> herosList;
        private List<RotationListBean> rotationList;

        public PkActivityBean getPkActivity() {
            return pkActivity;
        }

        public void setPkActivity(PkActivityBean pkActivity) {
            this.pkActivity = pkActivity;
        }

        public List<HerosListBean> getHerosList() {
            return herosList;
        }

        public void setHerosList(List<HerosListBean> herosList) {
            this.herosList = herosList;
        }

        public List<RotationListBean> getRotationList() {
            return rotationList;
        }

        public void setRotationList(List<RotationListBean> rotationList) {
            this.rotationList = rotationList;
        }

        public static class PkActivityBean {
            /**
             * address : 国定东路
             * distance : 260m
             * end_time : 2019-05-16 14:56:00
             * group_a_id : 6
             * group_a_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg
             * group_a_name : 红红火火
             * group_b_id : 3
             * group_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg
             * group_b_name : 俱乐部2
             * group_pk_id : 2
             * group_pk_name : 家家户户
             * start_time : 2019-05-16 09:56:00
             * total_no : 6
             */

            private String address;
            private String distance;
            private String end_time;
            private int group_a_id;
            private String group_a_logo;
            private String group_a_name;
            private int group_b_id;
            private String group_b_logo;
            private String group_b_name;
            private int group_pk_id;
            private String group_pk_name;
            private String start_time;
            private int total_no;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getGroup_a_id() {
                return group_a_id;
            }

            public void setGroup_a_id(int group_a_id) {
                this.group_a_id = group_a_id;
            }

            public String getGroup_a_logo() {
                return group_a_logo;
            }

            public void setGroup_a_logo(String group_a_logo) {
                this.group_a_logo = group_a_logo;
            }

            public String getGroup_a_name() {
                return group_a_name;
            }

            public void setGroup_a_name(String group_a_name) {
                this.group_a_name = group_a_name;
            }

            public int getGroup_b_id() {
                return group_b_id;
            }

            public void setGroup_b_id(int group_b_id) {
                this.group_b_id = group_b_id;
            }

            public String getGroup_b_logo() {
                return group_b_logo;
            }

            public void setGroup_b_logo(String group_b_logo) {
                this.group_b_logo = group_b_logo;
            }

            public String getGroup_b_name() {
                return group_b_name;
            }

            public void setGroup_b_name(String group_b_name) {
                this.group_b_name = group_b_name;
            }

            public int getGroup_pk_id() {
                return group_pk_id;
            }

            public void setGroup_pk_id(int group_pk_id) {
                this.group_pk_id = group_pk_id;
            }

            public String getGroup_pk_name() {
                return group_pk_name;
            }

            public void setGroup_pk_name(String group_pk_name) {
                this.group_pk_name = group_pk_name;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public int getTotal_no() {
                return total_no;
            }

            public void setTotal_no(int total_no) {
                this.total_no = total_no;
            }
        }

        public static class HerosListBean {
            /**
             * battle_number : 100
             * distance : 12958.1km
             * group_name : 一定
             * img_url : http://192.168.0.104/group1/M00/00/0B/wKgAaFzsXxSAQS8AAAEzPCddzEU517.jpg
             * project_type : 羽毛球
             * rank : 第1名
             */

            private String battle_number;
            private String distance;
            private String group_name;
            private String img_url;
            private String project_type;
            private String rank;

            public String getBattle_number() {
                return battle_number;
            }

            public void setBattle_number(String battle_number) {
                this.battle_number = battle_number;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getGroup_name() {
                return group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getProject_type() {
                return project_type;
            }

            public void setProject_type(String project_type) {
                this.project_type = project_type;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }
        }

        public static class RotationListBean {
            /**
             * id : 125
             * isLeaf : 1
             * itemCode : 1
             * itemName : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
             * parentCode : PK_ROTATION
             * sortNo : 1
             */

            private int id;
            private String isLeaf;
            private String itemCode;
            private String itemName;
            private String parentCode;
            private int sortNo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(String isLeaf) {
                this.isLeaf = isLeaf;
            }

            public String getItemCode() {
                return itemCode;
            }

            public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getParentCode() {
                return parentCode;
            }

            public void setParentCode(String parentCode) {
                this.parentCode = parentCode;
            }

            public int getSortNo() {
                return sortNo;
            }

            public void setSortNo(int sortNo) {
                this.sortNo = sortNo;
            }
        }
    }
}
