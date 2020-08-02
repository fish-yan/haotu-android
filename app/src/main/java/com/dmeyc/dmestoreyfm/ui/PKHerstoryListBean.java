package com.dmeyc.dmestoreyfm.ui;

import java.io.Serializable;
import java.util.List;

public class PKHerstoryListBean implements Serializable {

    /**
     * code : 200
     * data : [{"activity_address":"国定东路","activity_id":12,"end_time":"2019-05-16 14:56:00","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","group_a_win_num":10,"group_b_win_num":20,"group_pk_id":2,"isSuccess":"0","pk_activity_name":"家家户户","pk_group_name":"红红火火","start_time":"2019-05-16 09:56:00","venueName":"测试场馆"},{"activity_address":"国定东路","activity_id":13,"end_time":"2019-05-16 16:08:00","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg","group_a_win_num":10,"group_b_win_num":20,"group_pk_id":3,"isSuccess":"0","pk_activity_name":"救济金","pk_group_name":"红红火火","start_time":"2019-05-16 10:08:00","venueName":"测试场馆"},{"activity_address":"政府路181弄","activity_id":34,"end_time":"2019-05-16 22:04:00","groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_a_win_num":10,"group_b_win_num":20,"group_pk_id":9,"isSuccess":"0","pk_activity_name":"不吭了","pk_group_name":"俱乐部2","start_time":"2019-05-16 20:04:00","venueName":"测试场馆"}]
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
         * activity_address : 国定东路
         * activity_id : 12
         * end_time : 2019-05-16 14:56:00
         * groupLogo : http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg
         * group_a_win_num : 10
         * group_b_win_num : 20
         * group_pk_id : 2
         * isSuccess : 0
         * pk_activity_name : 家家户户
         * pk_group_name : 红红火火
         * start_time : 2019-05-16 09:56:00
         * venueName : 测试场馆
         */

        private String activity_address;
        private int activity_id;
        private String end_time;
        private String groupLogo;
        private int group_a_win_num;
        private int group_b_win_num;
        private int group_pk_id;
        private String isSuccess;
        private String pk_activity_name;
        private String pk_group_name;
        private String start_time;
        private String venueName;

        public String getActivity_address() {
            return activity_address;
        }

        public void setActivity_address(String activity_address) {
            this.activity_address = activity_address;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getGroupLogo() {
            return groupLogo;
        }

        public void setGroupLogo(String groupLogo) {
            this.groupLogo = groupLogo;
        }

        public int getGroup_a_win_num() {
            return group_a_win_num;
        }

        public void setGroup_a_win_num(int group_a_win_num) {
            this.group_a_win_num = group_a_win_num;
        }

        public int getGroup_b_win_num() {
            return group_b_win_num;
        }

        public void setGroup_b_win_num(int group_b_win_num) {
            this.group_b_win_num = group_b_win_num;
        }

        public int getGroup_pk_id() {
            return group_pk_id;
        }

        public void setGroup_pk_id(int group_pk_id) {
            this.group_pk_id = group_pk_id;
        }

        public String getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(String isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getPk_activity_name() {
            return pk_activity_name;
        }

        public void setPk_activity_name(String pk_activity_name) {
            this.pk_activity_name = pk_activity_name;
        }

        public String getPk_group_name() {
            return pk_group_name;
        }

        public void setPk_group_name(String pk_group_name) {
            this.pk_group_name = pk_group_name;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }
    }
}
