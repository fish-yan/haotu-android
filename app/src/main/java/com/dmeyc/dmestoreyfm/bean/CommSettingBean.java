package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CommSettingBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_id":16,"group_name":"哈哈哈","group_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyy2-mAPArLAAECCIC6yRc199.jpg","province":"上海市","city":"上海市","area":"黄浦区","activity_venue_address":"国定东路257号","notice":"","remark":"红果果给哥哥哥哥"}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * group_id : 16
         * group_name : 哈哈哈
         * group_logo : http://192.168.0.104/group1/M00/00/01/wKgAaFyy2-mAPArLAAECCIC6yRc199.jpg
         * province : 上海市
         * city : 上海市
         * area : 黄浦区
         * activity_venue_address : 国定东路257号
         * notice :
         * remark : 红果果给哥哥哥哥
         */

        private int group_id;
        private String group_name;
        private String group_logo;
        private String province;
        private String city;
        private String area;
        private String activity_venue_address;
        private String notice;
        private String remark;

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getActivity_venue_address() {
            return activity_venue_address;
        }

        public void setActivity_venue_address(String activity_venue_address) {
            this.activity_venue_address = activity_venue_address;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
