package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class NOpkScocktRecordBean implements Serializable {

    /**
     * code : 200
     * data : [{"id":1,"is_join":"1","team_a_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_a_member_a_nickname":"llife","team_a_member_a_sex":"1","team_a_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_a_member_b_nickname":"llife","team_a_member_b_sex":"1","team_a_score":6,"team_b_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_b_member_a_nickname":"llife","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png","team_b_member_b_nickname":"康","team_b_member_b_sex":"1","team_b_score":5}]
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
         * id : 1
         * is_join : 1
         * team_a_member_a_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
         * team_a_member_a_nickname : llife
         * team_a_member_a_sex : 1
         * team_a_member_b_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
         * team_a_member_b_nickname : llife
         * team_a_member_b_sex : 1
         * team_a_score : 6
         * team_b_member_a_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
         * team_b_member_a_nickname : llife
         * team_b_member_a_sex : 1
         * team_b_member_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png
         * team_b_member_b_nickname : 康
         * team_b_member_b_sex : 1
         * team_b_score : 5
         */

        private int id;
        private String is_join;
        private String team_a_member_a_logo;
        private String team_a_member_a_nickname;
        private String team_a_member_a_sex;
        private String team_a_member_b_logo;
        private String team_a_member_b_nickname;
        private String team_a_member_b_sex;
        private int team_a_score;
        private String team_b_member_a_logo;
        private String team_b_member_a_nickname;
        private String team_b_member_a_sex;
        private String team_b_member_b_logo;
        private String team_b_member_b_nickname;
        private String team_b_member_b_sex;
        private int team_b_score;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIs_join() {
            return is_join;
        }

        public void setIs_join(String is_join) {
            this.is_join = is_join;
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
