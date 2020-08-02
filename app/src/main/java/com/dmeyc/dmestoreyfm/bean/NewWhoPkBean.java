package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class NewWhoPkBean implements Serializable {

    /**
     * code : 200
     * data : [{"address":"政府路","distance":"12958.1km","end_time":"2019-05-16 22:09:00","group_a_id":3,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_a_name":"俱乐部2","group_b_id":12,"group_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzdtj2APIt6AABgoW-KJHU235.jpg","group_b_name":"羽毛球","group_pk_id":11,"group_pk_name":"开奖了","start_time":"2019-05-16 20:09:00","total_no":4,"venueName":"测试场馆"},{"address":"政府路181弄","distance":"12958.1km","end_time":"2019-05-16 22:04:00","group_a_id":3,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_a_name":"俱乐部2","group_b_id":15,"group_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzev1yAZQzOAAEoIG2kBZM126.jpg","group_b_name":"即哦","group_pk_id":10,"group_pk_name":"不吭了","start_time":"2019-05-16 20:04:00","total_no":4,"venueName":"测试场馆"},{"address":"国定东路","distance":"12958.1km","end_time":"2019-05-18 00:04:00","group_a_id":14,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzev1yARNNqAAEoIG2kBZM128.jpg","group_a_name":"即哦","group_pk_id":12,"group_pk_name":"哈哈哈哈","start_time":"2019-05-17 18:04:00","total_no":6,"venueName":"测试场馆"}]
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
         * address : 政府路
         * distance : 12958.1km
         * end_time : 2019-05-16 22:09:00
         * group_a_id : 3
         * group_a_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg
         * group_a_name : 俱乐部2
         * group_b_id : 12
         * group_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzdtj2APIt6AABgoW-KJHU235.jpg
         * group_b_name : 羽毛球
         * group_pk_id : 11
         * group_pk_name : 开奖了
         * start_time : 2019-05-16 20:09:00
         * total_no : 4
         * venueName : 测试场馆
         */
private int  activity_a_id;
private int battleNumber;
private String status;
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
        private String venueName;
          private  int groupAScores;
          private int groupBScores;
          private String isPked;

private String group_pk_subname;
private  String is_group_a_owner;
private int minBattle;
private int maxBattle;
private String groupType;
private String isSystem;
private String isGovernment;

        public String getIsGovernment() {
            return isGovernment;
        }

        public void setIsGovernment(String isGovernment) {
            this.isGovernment = isGovernment;
        }

        public String getIsSystem() {
            return isSystem;
        }

        public void setIsSystem(String isSystem) {
            this.isSystem = isSystem;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public int getMaxBattle() {
            return maxBattle;
        }

        public void setMaxBattle(int maxBattle) {
            this.maxBattle = maxBattle;
        }

        public int getMinBattle() {
            return minBattle;
        }

        public void setMinBattle(int minBattle) {
            this.minBattle = minBattle;
        }

        public String getIs_group_a_owner() {
            return is_group_a_owner;
        }

        public void setIs_group_a_owner(String is_group_a_owner) {
            this.is_group_a_owner = is_group_a_owner;
        }

        public String getGroup_pk_subname() {
            return group_pk_subname;
        }

        public void setGroup_pk_subname(String group_pk_subname) {
            this.group_pk_subname = group_pk_subname;
        }

        public int getBattleNumber() {
            return battleNumber;
        }

        public int getActivity_a_id() {
            return activity_a_id;
        }

        public String getStatus() {
            return status;
        }

        public void setActivity_a_id(int  activity_a_id) {
            this.activity_a_id = activity_a_id;
        }

        public void setBattleNumber(int battleNumber) {
            this.battleNumber = battleNumber;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsPked() {
            return isPked;
        }

        public void setIsPked(String isPked) {
            this.isPked = isPked;
        }

        public int getGroupAScores() {
            return groupAScores;
        }

        public int getGroupBScores() {
            return groupBScores;
        }

        public void setGroupAScores(int groupAScores) {
            this.groupAScores = groupAScores;
        }

        public void setGroupBScores(int groupBScores) {
            this.groupBScores = groupBScores;
        }

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

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }
    }
}
