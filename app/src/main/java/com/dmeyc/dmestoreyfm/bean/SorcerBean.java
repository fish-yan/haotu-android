package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SorcerBean implements Serializable {

    /**
     * code : 200
     * data : [{"id":1306,"team_a_id":292,"team_a_members":"李四+李三","team_a_score":0,"team_b_id":293,"team_b_members":"李二+李五","team_b_score":0},{"id":1307,"team_a_id":292,"team_a_members":"李四+李三","team_a_score":0,"team_b_id":294,"team_b_members":"李一+Hotu5652","team_b_score":0},{"id":1308,"team_a_id":293,"team_a_members":"李二+李五","team_a_score":0,"team_b_id":294,"team_b_members":"李一+Hotu5652","team_b_score":0}]
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
         * id : 1306
         * team_a_id : 292
         * team_a_members : 李四+李三
         * team_a_score : 0
         * team_b_id : 293
         * team_b_members : 李二+李五
         * team_b_score : 0
         */

        private int id;
        private int team_a_id;
        private String team_a_members;
        private int team_a_score;
        private int team_b_id;
        private String team_b_members;
        private int team_b_score;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
