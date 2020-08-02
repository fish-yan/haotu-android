package com.dmeyc.dmestoreyfm.ui;

import java.io.Serializable;
import java.util.List;

public class NewPKResultBean implements Serializable {

    /**
     * code : 200
     * data : {"basicInfo":{"end_time":"2019-05-15 09:43:00","group_a_id":3,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_a_name":"俱乐部2","group_a_score":10,"group_b_id":5,"group_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGFiAJiOoAAAvJ2sEydo970.jpg","group_b_name":"挑战你","group_b_score":20,"start_time":"2019-05-15 09:43:00","status":"1"},"list":[{"team_a_id":29,"team_a_member_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-BaAFIN_AAEIxhxMqIE148.jpg","team_a_member_a_nickname":"Hotu2222","team_a_member_a_sex":"1","team_a_member_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-SaARwGkAAFu8yiyxck380.jpg","team_a_member_b_nickname":"Hotu5555","team_a_member_b_sex":"1","team_a_score":21,"team_b_id":30,"team_b_member_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-JCAfgsYAADPpKNgEZs296.jpg","team_b_member_a_nickname":"Hotu3333","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-OmAY8uAAADcWT1JDCo890.jpg","team_b_member_b_nickname":"Hotu4444","team_b_member_b_sex":"1","team_b_score":12}]}
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
         * basicInfo : {"end_time":"2019-05-15 09:43:00","group_a_id":3,"group_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_a_name":"俱乐部2","group_a_score":10,"group_b_id":5,"group_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcGFiAJiOoAAAvJ2sEydo970.jpg","group_b_name":"挑战你","group_b_score":20,"start_time":"2019-05-15 09:43:00","status":"1"}
         * list : [{"team_a_id":29,"team_a_member_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-BaAFIN_AAEIxhxMqIE148.jpg","team_a_member_a_nickname":"Hotu2222","team_a_member_a_sex":"1","team_a_member_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-SaARwGkAAFu8yiyxck380.jpg","team_a_member_b_nickname":"Hotu5555","team_a_member_b_sex":"1","team_a_score":21,"team_b_id":30,"team_b_member_a_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-JCAfgsYAADPpKNgEZs296.jpg","team_b_member_a_nickname":"Hotu3333","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-OmAY8uAAADcWT1JDCo890.jpg","team_b_member_b_nickname":"Hotu4444","team_b_member_b_sex":"1","team_b_score":12}]
         */

        private BasicInfoBean basicInfo;
        private List<ListBean> list;

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class BasicInfoBean {
            /**
             * end_time : 2019-05-15 09:43:00
             * group_a_id : 3
             * group_a_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg
             * group_a_name : 俱乐部2
             * group_a_score : 10
             * group_b_id : 5
             * group_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzcGFiAJiOoAAAvJ2sEydo970.jpg
             * group_b_name : 挑战你
             * group_b_score : 20
             * start_time : 2019-05-15 09:43:00
             * status : 1
             */

            private String end_time;
            private int group_a_id;
            private String group_a_logo;
            private String group_a_name;
            private int group_a_score;
            private int group_b_id;
            private String group_b_logo;
            private String group_b_name;
            private int group_b_score;
            private String start_time;
            private String status;
private String pkName;

            public String getPkName() {
                return pkName;
            }

            public void setPkName(String pkName) {
                this.pkName = pkName;
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

            public int getGroup_a_score() {
                return group_a_score;
            }

            public void setGroup_a_score(int group_a_score) {
                this.group_a_score = group_a_score;
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

            public int getGroup_b_score() {
                return group_b_score;
            }

            public void setGroup_b_score(int group_b_score) {
                this.group_b_score = group_b_score;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class ListBean {
            /**
             * team_a_id : 29
             * team_a_member_a_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFze-BaAFIN_AAEIxhxMqIE148.jpg
             * team_a_member_a_nickname : Hotu2222
             * team_a_member_a_sex : 1
             * team_a_member_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFze-SaARwGkAAFu8yiyxck380.jpg
             * team_a_member_b_nickname : Hotu5555
             * team_a_member_b_sex : 1
             * team_a_score : 21
             * team_b_id : 30
             * team_b_member_a_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFze-JCAfgsYAADPpKNgEZs296.jpg
             * team_b_member_a_nickname : Hotu3333
             * team_b_member_a_sex : 1
             * team_b_member_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFze-OmAY8uAAADcWT1JDCo890.jpg
             * team_b_member_b_nickname : Hotu4444
             * team_b_member_b_sex : 1
             * team_b_score : 12
             */

            private int team_a_id;
            private String team_a_member_a_logo;
            private String team_a_member_a_nickname;
            private String team_a_member_a_sex;
            private String team_a_member_b_logo;
            private String team_a_member_b_nickname;
            private String team_a_member_b_sex;
            private int team_a_score;
            private int team_b_id;
            private String team_b_member_a_logo;
            private String team_b_member_a_nickname;
            private String team_b_member_a_sex;
            private String team_b_member_b_logo;
            private String team_b_member_b_nickname;
            private String team_b_member_b_sex;
            private int team_b_score;

            public int getTeam_a_id() {
                return team_a_id;
            }

            public void setTeam_a_id(int team_a_id) {
                this.team_a_id = team_a_id;
            }

            public String getTeam_a_member_a_logo() {
                return team_a_member_a_logo;
            }

            public void setTeam_a_member_a_logo(String team_a_member_a_logo) {
                this.team_a_member_a_logo = team_a_member_a_logo;
            }

            public String getTeam_a_member_a_nickname() {
                return team_a_member_a_nickname;
            }

            public void setTeam_a_member_a_nickname(String team_a_member_a_nickname) {
                this.team_a_member_a_nickname = team_a_member_a_nickname;
            }

            public String getTeam_a_member_a_sex() {
                return team_a_member_a_sex;
            }

            public void setTeam_a_member_a_sex(String team_a_member_a_sex) {
                this.team_a_member_a_sex = team_a_member_a_sex;
            }

            public String getTeam_a_member_b_logo() {
                return team_a_member_b_logo;
            }

            public void setTeam_a_member_b_logo(String team_a_member_b_logo) {
                this.team_a_member_b_logo = team_a_member_b_logo;
            }

            public String getTeam_a_member_b_nickname() {
                return team_a_member_b_nickname;
            }

            public void setTeam_a_member_b_nickname(String team_a_member_b_nickname) {
                this.team_a_member_b_nickname = team_a_member_b_nickname;
            }

            public String getTeam_a_member_b_sex() {
                return team_a_member_b_sex;
            }

            public void setTeam_a_member_b_sex(String team_a_member_b_sex) {
                this.team_a_member_b_sex = team_a_member_b_sex;
            }

            public int getTeam_a_score() {
                return team_a_score;
            }

            public void setTeam_a_score(int team_a_score) {
                this.team_a_score = team_a_score;
            }

            public int getTeam_b_id() {
                return team_b_id;
            }

            public void setTeam_b_id(int team_b_id) {
                this.team_b_id = team_b_id;
            }

            public String getTeam_b_member_a_logo() {
                return team_b_member_a_logo;
            }

            public void setTeam_b_member_a_logo(String team_b_member_a_logo) {
                this.team_b_member_a_logo = team_b_member_a_logo;
            }

            public String getTeam_b_member_a_nickname() {
                return team_b_member_a_nickname;
            }

            public void setTeam_b_member_a_nickname(String team_b_member_a_nickname) {
                this.team_b_member_a_nickname = team_b_member_a_nickname;
            }

            public String getTeam_b_member_a_sex() {
                return team_b_member_a_sex;
            }

            public void setTeam_b_member_a_sex(String team_b_member_a_sex) {
                this.team_b_member_a_sex = team_b_member_a_sex;
            }

            public String getTeam_b_member_b_logo() {
                return team_b_member_b_logo;
            }

            public void setTeam_b_member_b_logo(String team_b_member_b_logo) {
                this.team_b_member_b_logo = team_b_member_b_logo;
            }

            public String getTeam_b_member_b_nickname() {
                return team_b_member_b_nickname;
            }

            public void setTeam_b_member_b_nickname(String team_b_member_b_nickname) {
                this.team_b_member_b_nickname = team_b_member_b_nickname;
            }

            public String getTeam_b_member_b_sex() {
                return team_b_member_b_sex;
            }

            public void setTeam_b_member_b_sex(String team_b_member_b_sex) {
                this.team_b_member_b_sex = team_b_member_b_sex;
            }

            public int getTeam_b_score() {
                return team_b_score;
            }

            public void setTeam_b_score(int team_b_score) {
                this.team_b_score = team_b_score;
            }
        }
    }
}
