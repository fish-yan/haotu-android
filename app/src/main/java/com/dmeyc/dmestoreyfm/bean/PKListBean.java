package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class PKListBean extends BaseYFMBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_pk_list":[{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/00/wKgAaFyunXeATKs6AABlHOIVZLo504.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/00/wKgAaFywM-KARu7qAABlHOIVZLo532.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1gl2AU4c-AAFulOBxFCg891.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyy2_-AGdDQAAECCIC6yRc606.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyy3AiAJCjsAAECCIC6yRc065.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyy3A6AC6cIAAECCIC6yRc850.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyy3BaAHReTAAECCIC6yRc261.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBjOAGKriAADLR3wTTMI048.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBjOAGKriAADLR3wTTMI048.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBjOAGKriAADLR3wTTMI048.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1gVyALi9oAACswXvpFcA119.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy1mOOADYwCAADMsA3Nf00845.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy2vLWAOuubAACK3zuJHyg896.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":4,"group_a_id":7,"group_b_id":7,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"羽毛球俱乐部-邹","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFy2znGAcpaTAACK3zuJHyg499.jpg","group_pk_name":"通天塔","address":"政府路181弄14号","start_time":"2019-04-12 13:00:00","end_time":"2019-04-12 15:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null},{"group_pk_id":2,"group_a_id":7,"group_b_id":6,"group_a_name":"羽毛球俱乐部-邹","group_b_name":"张三俱乐部","group_a_logo":"http://img1.mm131.me/pic/1695/1.jpg","group_b_logo":null,"group_pk_name":"羽毛球1","address":"政府路181弄14号","start_time":"2019-04-11 18:00:00","end_time":"2019-04-11 20:00:00","duration":2,"total_no":null,"is_safe":null,"is_sponsor":null}]}
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





        private List<GroupPkListBean> group_pk_list;
        private List<RankListBean> rankList;

        public List<GroupPkListBean> getGroup_pk_list() {
            return group_pk_list;
        }

        public void setGroup_pk_list(List<GroupPkListBean> group_pk_list) {
            this.group_pk_list = group_pk_list;
        }

        public List<RankListBean> getRankList() {
            return rankList;
        }

        public void setRankList(List<RankListBean> rankList) {
            this.rankList = rankList;
        }

        public static class GroupPkListBean {
            /**
             * group_pk_id : 4
             * group_a_id : 7
             * group_b_id : 7
             * group_a_name : 羽毛球俱乐部-邹
             * group_b_name : 羽毛球俱乐部-邹
             * group_a_logo : http://img1.mm131.me/pic/1695/1.jpg
             * group_b_logo : http://img1.mm131.me/pic/1695/1.jpg
             * group_pk_name : 通天塔
             * address : 政府路181弄14号
             * start_time : 2019-04-12 13:00:00
             * end_time : 2019-04-12 15:00:00
             * duration : 2
             * total_no : null
             * is_safe : null
             * is_sponsor : null
             */



           private  int activity_id;
            private int group_pk_id;
            private int group_a_id;
            private int group_b_id;
            private String group_a_name;
            private String group_b_name;
            private String group_a_logo;
            private String group_b_logo;
            private String group_pk_name;
            private String address;
            private String start_time;
            private String end_time;
            private int duration;
            private int total_no;
            private Object is_safe;
            private Object is_sponsor;
           private String venueName;


            public String getVenueName() {
                return venueName;
            }

            public void setVenueName(String venueName) {
                this.venueName = venueName;
            }

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public int getGroup_pk_id() {
                return group_pk_id;
            }

            public void setGroup_pk_id(int group_pk_id) {
                this.group_pk_id = group_pk_id;
            }

            public int getGroup_a_id() {
                return group_a_id;
            }

            public void setGroup_a_id(int group_a_id) {
                this.group_a_id = group_a_id;
            }

            public int getGroup_b_id() {
                return group_b_id;
            }

            public void setGroup_b_id(int group_b_id) {
                this.group_b_id = group_b_id;
            }

            public String getGroup_a_name() {
                return group_a_name;
            }

            public void setGroup_a_name(String group_a_name) {
                this.group_a_name = group_a_name;
            }

            public String getGroup_b_name() {
                return group_b_name;
            }

            public void setGroup_b_name(String group_b_name) {
                this.group_b_name = group_b_name;
            }

            public String getGroup_a_logo() {
                return group_a_logo;
            }

            public void setGroup_a_logo(String group_a_logo) {
                this.group_a_logo = group_a_logo;
            }

            public String getGroup_b_logo() {
                return group_b_logo;
            }

            public void setGroup_b_logo(String group_b_logo) {
                this.group_b_logo = group_b_logo;
            }

            public String getGroup_pk_name() {
                return group_pk_name;
            }

            public void setGroup_pk_name(String group_pk_name) {
                this.group_pk_name = group_pk_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getTotal_no() {
                return total_no;
            }

            public void setTotal_no(int total_no) {
                this.total_no = total_no;
            }

            public Object getIs_safe() {
                return is_safe;
            }

            public void setIs_safe(Object is_safe) {
                this.is_safe = is_safe;
            }

            public Object getIs_sponsor() {
                return is_sponsor;
            }

            public void setIs_sponsor(Object is_sponsor) {
                this.is_sponsor = is_sponsor;
            }


            /**
             * activity_venue_address : 国定东路11
             * battle_number : 888
             * distance : 430m
             * group_name : 23德不
             * img_url : http://192.168.0.104/group1/M00/00/00/wKgAaFywM-KARu7qAABlHOIVZLo532.jpg
             * project_type : 羽毛球
             * rank : 第1名
             */

            private String activity_venue_address;
            private String battle_number;
            private String distance;
            private String group_name;
            private String img_url;
            private String project_type;
            private String rank;

            public String getActivity_venue_address() {
                return activity_venue_address;
            }

            public void setActivity_venue_address(String activity_venue_address) {
                this.activity_venue_address = activity_venue_address;
            }

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

        public static class RankListBean {
            /**
             * activity_venue_address : 国定东路11
             * battle_number : 888
             * distance : 430m
             * group_name : 23德不
             * img_url : http://192.168.0.104/group1/M00/00/00/wKgAaFywM-KARu7qAABlHOIVZLo532.jpg
             * project_type : 羽毛球
             * rank : 第1名
             */

            private String activity_venue_address;
            private String battle_number;
            private String distance;
            private String group_name;
            private String img_url;
            private String project_type;
            private String rank;

            public String getActivity_venue_address() {
                return activity_venue_address;
            }

            public void setActivity_venue_address(String activity_venue_address) {
                this.activity_venue_address = activity_venue_address;
            }

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
    }
}
