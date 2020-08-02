package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class PkResultDetailBean implements Serializable {


    /**
     * code : 200
     * data : {"accepts":[{"activityPkId":1,"createTime":1560804728000,"groupChallengeId":46,"groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","groupName":"笨笨熊普通群","id":1,"status":"1"},{"activityPkId":1,"createTime":1560804790000,"groupChallengeId":47,"groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","groupName":"天天宠普通群","id":2,"status":"1"}],"address":"政府路181弄","area":"杨浦区","battle_a_num":20,"battle_b_num":20,"brand_ball_url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","brand_name":"测试用球","city":"上海市","group_a_id":3,"group_a_name":"俱乐部2","group_b_id":5,"group_b_name":"挑战你","isSponsor":"0","is_group_a_owner":"0","is_group_b_owner":"0","max_battle":200,"min_battle":100,"organizerName":"康","organizerPhone":"18301732235","province":"上海市","remark":"男让女8分","start_date":"2019-05-15 09:43-11:43","status":"1","venueName":"测试场馆"}
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
         * accepts : [{"activityPkId":1,"createTime":1560804728000,"groupChallengeId":46,"groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","groupName":"笨笨熊普通群","id":1,"status":"1"},{"activityPkId":1,"createTime":1560804790000,"groupChallengeId":47,"groupLogo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","groupName":"天天宠普通群","id":2,"status":"1"}]
         * address : 政府路181弄
         * area : 杨浦区
         * battle_a_num : 20
         * battle_b_num : 20
         * brand_ball_url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
         * brand_name : 测试用球
         * city : 上海市
         * group_a_id : 3
         * group_a_name : 俱乐部2
         * group_b_id : 5
         * group_b_name : 挑战你
         * isSponsor : 0
         * is_group_a_owner : 0
         * is_group_b_owner : 0
         * max_battle : 200
         * min_battle : 100
         * organizerName : 康
         * organizerPhone : 18301732235
         * province : 上海市
         * remark : 男让女8分
         * start_date : 2019-05-15 09:43-11:43
         * status : 1
         * venueName : 测试场馆
         */

        private String address;
        private String area;
        private int battle_a_num;
        private int battle_b_num;
        private String brand_ball_url;
        private String brand_name;
        private String city;
        private int group_a_id;
        private String group_a_name;
        private int group_b_id;
        private String group_b_name;
        private String isSponsor;
        private String is_group_a_owner;
        private String is_group_b_owner;
        private int max_battle;
        private int min_battle;
        private String organizerName;
        private String organizerPhone;
        private String province;
        private String remark;
        private String start_date;
        private String status;
        private String venueName;
        private String latitude;
        private String longitude;
private String group_pk_name;

        public String getGroup_pk_name() {
            return group_pk_name;
        }

        public void setGroup_pk_name(String group_pk_name) {
            this.group_pk_name = group_pk_name;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        private List<AcceptsBean> accepts;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getBattle_a_num() {
            return battle_a_num;
        }

        public void setBattle_a_num(int battle_a_num) {
            this.battle_a_num = battle_a_num;
        }

        public int getBattle_b_num() {
            return battle_b_num;
        }

        public void setBattle_b_num(int battle_b_num) {
            this.battle_b_num = battle_b_num;
        }

        public String getBrand_ball_url() {
            return brand_ball_url;
        }

        public void setBrand_ball_url(String brand_ball_url) {
            this.brand_ball_url = brand_ball_url;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getGroup_a_id() {
            return group_a_id;
        }

        public void setGroup_a_id(int group_a_id) {
            this.group_a_id = group_a_id;
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

        public String getGroup_b_name() {
            return group_b_name;
        }

        public void setGroup_b_name(String group_b_name) {
            this.group_b_name = group_b_name;
        }

        public String getIsSponsor() {
            return isSponsor;
        }

        public void setIsSponsor(String isSponsor) {
            this.isSponsor = isSponsor;
        }

        public String getIs_group_a_owner() {
            return is_group_a_owner;
        }

        public void setIs_group_a_owner(String is_group_a_owner) {
            this.is_group_a_owner = is_group_a_owner;
        }

        public String getIs_group_b_owner() {
            return is_group_b_owner;
        }

        public void setIs_group_b_owner(String is_group_b_owner) {
            this.is_group_b_owner = is_group_b_owner;
        }

        public int getMax_battle() {
            return max_battle;
        }

        public void setMax_battle(int max_battle) {
            this.max_battle = max_battle;
        }

        public int getMin_battle() {
            return min_battle;
        }

        public void setMin_battle(int min_battle) {
            this.min_battle = min_battle;
        }

        public String getOrganizerName() {
            return organizerName;
        }

        public void setOrganizerName(String organizerName) {
            this.organizerName = organizerName;
        }

        public String getOrganizerPhone() {
            return organizerPhone;
        }

        public void setOrganizerPhone(String organizerPhone) {
            this.organizerPhone = organizerPhone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }

        public List<AcceptsBean> getAccepts() {
            return accepts;
        }

        public void setAccepts(List<AcceptsBean> accepts) {
            this.accepts = accepts;
        }

        public static class AcceptsBean {
            /**
             * activityPkId : 1
             * createTime : 1560804728000
             * groupChallengeId : 46
             * groupLogo : http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg
             * groupName : 笨笨熊普通群
             * id : 1
             * status : 1
             */

            private int activityPkId;
            private long createTime;
            private int groupChallengeId;
            private String groupLogo;
            private String groupName;
            private int id;
            private String status;

            public int getActivityPkId() {
                return activityPkId;
            }

            public void setActivityPkId(int activityPkId) {
                this.activityPkId = activityPkId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getGroupChallengeId() {
                return groupChallengeId;
            }

            public void setGroupChallengeId(int groupChallengeId) {
                this.groupChallengeId = groupChallengeId;
            }

            public String getGroupLogo() {
                return groupLogo;
            }

            public void setGroupLogo(String groupLogo) {
                this.groupLogo = groupLogo;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
