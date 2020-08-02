package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class MatchResultBean implements Serializable {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"group_name":"精英羽毛球俱乐部","group_logo":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHRSCAYryYAACw2xjgW0o230.jpg","start_time":"2019-04-30 16:14","activity_address":"国定东路22号","winner_no":2,"loser_no":0,"matchList":[{"id":null,"team_a_id":264,"team_a_members":"Hotu9999+Hotu6285","team_a_score":21,"team_b_id":265,"team_b_members":"Hotu8888+Hotu7777","team_b_score":20},{"id":null,"team_a_id":264,"team_a_members":"Hotu9999+Hotu6285","team_a_score":21,"team_b_id":266,"team_b_members":"Hotu6666+Hotu5555","team_b_score":19}]}
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
         * group_name : 精英羽毛球俱乐部
         * group_logo : http://192.168.0.104/group1/M00/00/04/wKgAaFzHRSCAYryYAACw2xjgW0o230.jpg
         * start_time : 2019-04-30 16:14
         * activity_address : 国定东路22号
         * winner_no : 2
         * loser_no : 0
         * matchList : [{"id":null,"team_a_id":264,"team_a_members":"Hotu9999+Hotu6285","team_a_score":21,"team_b_id":265,"team_b_members":"Hotu8888+Hotu7777","team_b_score":20},{"id":null,"team_a_id":264,"team_a_members":"Hotu9999+Hotu6285","team_a_score":21,"team_b_id":266,"team_b_members":"Hotu6666+Hotu5555","team_b_score":19}]
         */

        private String group_name;
        private String group_logo;
        private String start_time;
        private String activity_address;
        private int winner_no;
        private int loser_no;
        private List<MatchListBean> matchList;

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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getActivity_address() {
            return activity_address;
        }

        public void setActivity_address(String activity_address) {
            this.activity_address = activity_address;
        }

        public int getWinner_no() {
            return winner_no;
        }

        public void setWinner_no(int winner_no) {
            this.winner_no = winner_no;
        }

        public int getLoser_no() {
            return loser_no;
        }

        public void setLoser_no(int loser_no) {
            this.loser_no = loser_no;
        }

        public List<MatchListBean> getMatchList() {
            return matchList;
        }

        public void setMatchList(List<MatchListBean> matchList) {
            this.matchList = matchList;
        }

        public static class MatchListBean {
            /**
             * id : null
             * team_a_id : 264
             * team_a_members : Hotu9999+Hotu6285
             * team_a_score : 21
             * team_b_id : 265
             * team_b_members : Hotu8888+Hotu7777
             * team_b_score : 20
             */

            private Object id;
            private int team_a_id;
            private String team_a_members;
            private int team_a_score;
            private int team_b_id;
            private String team_b_members;
            private int team_b_score;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public int getTeam_a_id() {
                return team_a_id;
            }

            public void setTeam_a_id(int team_a_id) {
                this.team_a_id = team_a_id;
            }

            public String getTeam_a_members() {
                return team_a_members;
            }

            public void setTeam_a_members(String team_a_members) {
                this.team_a_members = team_a_members;
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

            public String getTeam_b_members() {
                return team_b_members;
            }

            public void setTeam_b_members(String team_b_members) {
                this.team_b_members = team_b_members;
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
