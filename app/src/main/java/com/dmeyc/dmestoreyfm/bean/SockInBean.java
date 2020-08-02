package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SockInBean implements Serializable {
    /**
     * code : 200
     * data : {"basicInfo":{"end_time":"2019-08-06 17:00:00","group_a_id":93,"group_a_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMuSAOpU_AAGLqZS9a4E748.jpg","group_a_name":"兔上羽毛球俱乐部","group_a_score":3,"group_b_id":104,"group_b_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J38mANYc4AADlEfhsKlQ992.jpg","group_b_name":"好兔测试俱乐部","group_b_score":1,"pkName":"欢乐喜剧人","start_time":"2019-08-06 17:00:00","status":"2"},"list":[{"id":878913,"team_a_id":27832,"team_a_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEgefCPGLGTo5teOJbEuBbekDiatsKypuBPMok0xOOQ729aObXxrcOiapW6bmBlDibQA4LC2bCvMKbw/132","team_a_member_a_nickname":"兔爸","team_a_member_a_sex":"1","team_a_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/kxEUMfiaAnXg9EtqNdn2qfJCdzVZsicYuQFGmA5sgxexIScqiaRUXFZue46Gp1XfPAUSmf79ic0ODqyEX7Zjl8GapA/132","team_a_member_b_nickname":"fbm","team_a_member_b_sex":"2","team_a_score":21,"team_b_id":27834,"team_b_member_a_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J4baAEzWoAAExxCZ9NQs776.jpg","team_b_member_a_nickname":"彭阳","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J8_OAeA3hAACfNB2-Kw8553.jpg","team_b_member_b_nickname":"Hotu2284","team_b_member_b_sex":"1","team_b_score":15},{"id":878914,"team_a_id":27832,"team_a_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEgefCPGLGTo5teOJbEuBbekDiatsKypuBPMok0xOOQ729aObXxrcOiapW6bmBlDibQA4LC2bCvMKbw/132","team_a_member_a_nickname":"兔爸","team_a_member_a_sex":"1","team_a_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/kxEUMfiaAnXg9EtqNdn2qfJCdzVZsicYuQFGmA5sgxexIScqiaRUXFZue46Gp1XfPAUSmf79ic0ODqyEX7Zjl8GapA/132","team_a_member_b_nickname":"fbm","team_a_member_b_sex":"2","team_a_score":21,"team_b_id":27835,"team_b_member_a_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","team_b_member_a_nickname":"执着","team_b_member_a_sex":"2","team_b_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","team_b_member_b_nickname":"好兔","team_b_member_b_sex":"1","team_b_score":19},{"id":878915,"team_a_id":27833,"team_a_member_a_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","team_a_member_a_nickname":"Hotu1034","team_a_member_a_sex":"1","team_a_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoLicM4BYTU9tsqX4KzNoMxIQ/132","team_a_member_b_nickname":"大巴黎","team_a_member_b_sex":"1","team_a_score":15,"team_b_id":27834,"team_b_member_a_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J4baAEzWoAAExxCZ9NQs776.jpg","team_b_member_a_nickname":"彭阳","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J8_OAeA3hAACfNB2-Kw8553.jpg","team_b_member_b_nickname":"Hotu2284","team_b_member_b_sex":"1","team_b_score":21},{"id":878916,"team_a_id":27833,"team_a_member_a_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","team_a_member_a_nickname":"Hotu1034","team_a_member_a_sex":"1","team_a_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoLicM4BYTU9tsqX4KzNoMxIQ/132","team_a_member_b_nickname":"大巴黎","team_a_member_b_sex":"1","team_a_score":21,"team_b_id":27835,"team_b_member_a_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","team_b_member_a_nickname":"执着","team_b_member_a_sex":"2","team_b_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","team_b_member_b_nickname":"好兔","team_b_member_b_sex":"1","team_b_score":16}]}
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
         * basicInfo : {"end_time":"2019-08-06 17:00:00","group_a_id":93,"group_a_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMuSAOpU_AAGLqZS9a4E748.jpg","group_a_name":"兔上羽毛球俱乐部","group_a_score":3,"group_b_id":104,"group_b_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J38mANYc4AADlEfhsKlQ992.jpg","group_b_name":"好兔测试俱乐部","group_b_score":1,"pkName":"欢乐喜剧人","start_time":"2019-08-06 17:00:00","status":"2"}
         * list : [{"id":878913,"team_a_id":27832,"team_a_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEgefCPGLGTo5teOJbEuBbekDiatsKypuBPMok0xOOQ729aObXxrcOiapW6bmBlDibQA4LC2bCvMKbw/132","team_a_member_a_nickname":"兔爸","team_a_member_a_sex":"1","team_a_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/kxEUMfiaAnXg9EtqNdn2qfJCdzVZsicYuQFGmA5sgxexIScqiaRUXFZue46Gp1XfPAUSmf79ic0ODqyEX7Zjl8GapA/132","team_a_member_b_nickname":"fbm","team_a_member_b_sex":"2","team_a_score":21,"team_b_id":27834,"team_b_member_a_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J4baAEzWoAAExxCZ9NQs776.jpg","team_b_member_a_nickname":"彭阳","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J8_OAeA3hAACfNB2-Kw8553.jpg","team_b_member_b_nickname":"Hotu2284","team_b_member_b_sex":"1","team_b_score":15},{"id":878914,"team_a_id":27832,"team_a_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEgefCPGLGTo5teOJbEuBbekDiatsKypuBPMok0xOOQ729aObXxrcOiapW6bmBlDibQA4LC2bCvMKbw/132","team_a_member_a_nickname":"兔爸","team_a_member_a_sex":"1","team_a_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/kxEUMfiaAnXg9EtqNdn2qfJCdzVZsicYuQFGmA5sgxexIScqiaRUXFZue46Gp1XfPAUSmf79ic0ODqyEX7Zjl8GapA/132","team_a_member_b_nickname":"fbm","team_a_member_b_sex":"2","team_a_score":21,"team_b_id":27835,"team_b_member_a_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","team_b_member_a_nickname":"执着","team_b_member_a_sex":"2","team_b_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","team_b_member_b_nickname":"好兔","team_b_member_b_sex":"1","team_b_score":19},{"id":878915,"team_a_id":27833,"team_a_member_a_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","team_a_member_a_nickname":"Hotu1034","team_a_member_a_sex":"1","team_a_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoLicM4BYTU9tsqX4KzNoMxIQ/132","team_a_member_b_nickname":"大巴黎","team_a_member_b_sex":"1","team_a_score":15,"team_b_id":27834,"team_b_member_a_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J4baAEzWoAAExxCZ9NQs776.jpg","team_b_member_a_nickname":"彭阳","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/19/wKgAaF1J8_OAeA3hAACfNB2-Kw8553.jpg","team_b_member_b_nickname":"Hotu2284","team_b_member_b_sex":"1","team_b_score":21},{"id":878916,"team_a_id":27833,"team_a_member_a_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","team_a_member_a_nickname":"Hotu1034","team_a_member_a_sex":"1","team_a_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoLicM4BYTU9tsqX4KzNoMxIQ/132","team_a_member_b_nickname":"大巴黎","team_a_member_b_sex":"1","team_a_score":21,"team_b_id":27835,"team_b_member_a_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","team_b_member_a_nickname":"执着","team_b_member_a_sex":"2","team_b_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","team_b_member_b_nickname":"好兔","team_b_member_b_sex":"1","team_b_score":16}]
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
             * end_time : 2019-08-06 17:00:00
             * group_a_id : 93
             * group_a_logo : http://192.168.0.104/group1/M00/00/18/wKgAaF1DMuSAOpU_AAGLqZS9a4E748.jpg
             * group_a_name : 兔上羽毛球俱乐部
             * group_a_score : 3
             * group_b_id : 104
             * group_b_logo : http://192.168.0.104/group1/M00/00/19/wKgAaF1J38mANYc4AADlEfhsKlQ992.jpg
             * group_b_name : 好兔测试俱乐部
             * group_b_score : 1
             * pkName : 欢乐喜剧人
             * start_time : 2019-08-06 17:00:00
             * status : 2
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
            private String pkName;
            private String start_time;
            private String status;

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

            public String getPkName() {
                return pkName;
            }

            public void setPkName(String pkName) {
                this.pkName = pkName;
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
             * id : 878913
             * team_a_id : 27832
             * team_a_member_a_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEgefCPGLGTo5teOJbEuBbekDiatsKypuBPMok0xOOQ729aObXxrcOiapW6bmBlDibQA4LC2bCvMKbw/132
             * team_a_member_a_nickname : 兔爸
             * team_a_member_a_sex : 1
             * team_a_member_b_logo : https://thirdwx.qlogo.cn/mmopen/vi_32/kxEUMfiaAnXg9EtqNdn2qfJCdzVZsicYuQFGmA5sgxexIScqiaRUXFZue46Gp1XfPAUSmf79ic0ODqyEX7Zjl8GapA/132
             * team_a_member_b_nickname : fbm
             * team_a_member_b_sex : 2
             * team_a_score : 21
             * team_b_id : 27834
             * team_b_member_a_logo : http://192.168.0.104/group1/M00/00/19/wKgAaF1J4baAEzWoAAExxCZ9NQs776.jpg
             * team_b_member_a_nickname : 彭阳
             * team_b_member_a_sex : 1
             * team_b_member_b_logo : http://192.168.0.104/group1/M00/00/19/wKgAaF1J8_OAeA3hAACfNB2-Kw8553.jpg
             * team_b_member_b_nickname : Hotu2284
             * team_b_member_b_sex : 1
             * team_b_score : 15
             */

            private int id;
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

//    /**
//     * code : 200
//     * data : [{"id":1,"is_join":"1","team_a_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_a_member_a_nickname":"llife","team_a_member_a_sex":"1","team_a_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_a_member_b_nickname":"llife","team_a_member_b_sex":"1","team_a_score":6,"team_b_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_b_member_a_nickname":"llife","team_b_member_a_sex":"1","team_b_member_b_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png","team_b_member_b_nickname":"康","team_b_member_b_sex":"1","team_b_score":5}]
//     * msg : 操作成功
//     */
//
//    private String code;
//    private String msg;
//    private List<DataBean> data;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * id : 1
//         * is_join : 1
//         * team_a_member_a_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
//         * team_a_member_a_nickname : llife
//         * team_a_member_a_sex : 1
//         * team_a_member_b_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
//         * team_a_member_b_nickname : llife
//         * team_a_member_b_sex : 1
//         * team_a_score : 6
//         * team_b_member_a_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
//         * team_b_member_a_nickname : llife
//         * team_b_member_a_sex : 1
//         * team_b_member_b_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png
//         * team_b_member_b_nickname : 康
//         * team_b_member_b_sex : 1
//         * team_b_score : 5
//         */
//
//        private int id;
//        private String is_join;
//        private String team_a_member_a_logo;
//        private String team_a_member_a_nickname;
//        private String team_a_member_a_sex;
//        private String team_a_member_b_logo;
//        private String team_a_member_b_nickname;
//        private String team_a_member_b_sex;
//        private int team_a_score;
//        private String team_b_member_a_logo;
//        private String team_b_member_a_nickname;
//        private String team_b_member_a_sex;
//        private String team_b_member_b_logo;
//        private String team_b_member_b_nickname;
//        private String team_b_member_b_sex;
//        private int team_b_score;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getIs_join() {
//            return is_join;
//        }
//
//        public void setIs_join(String is_join) {
//            this.is_join = is_join;
//        }
//
//        public String getTeam_a_member_a_logo() {
//            return team_a_member_a_logo;
//        }
//
//        public void setTeam_a_member_a_logo(String team_a_member_a_logo) {
//            this.team_a_member_a_logo = team_a_member_a_logo;
//        }
//
//        public String getTeam_a_member_a_nickname() {
//            return team_a_member_a_nickname;
//        }
//
//        public void setTeam_a_member_a_nickname(String team_a_member_a_nickname) {
//            this.team_a_member_a_nickname = team_a_member_a_nickname;
//        }
//
//        public String getTeam_a_member_a_sex() {
//            return team_a_member_a_sex;
//        }
//
//        public void setTeam_a_member_a_sex(String team_a_member_a_sex) {
//            this.team_a_member_a_sex = team_a_member_a_sex;
//        }
//
//        public String getTeam_a_member_b_logo() {
//            return team_a_member_b_logo;
//        }
//
//        public void setTeam_a_member_b_logo(String team_a_member_b_logo) {
//            this.team_a_member_b_logo = team_a_member_b_logo;
//        }
//
//        public String getTeam_a_member_b_nickname() {
//            return team_a_member_b_nickname;
//        }
//
//        public void setTeam_a_member_b_nickname(String team_a_member_b_nickname) {
//            this.team_a_member_b_nickname = team_a_member_b_nickname;
//        }
//
//        public String getTeam_a_member_b_sex() {
//            return team_a_member_b_sex;
//        }
//
//        public void setTeam_a_member_b_sex(String team_a_member_b_sex) {
//            this.team_a_member_b_sex = team_a_member_b_sex;
//        }
//
//        public int getTeam_a_score() {
//            return team_a_score;
//        }
//
//        public void setTeam_a_score(int team_a_score) {
//            this.team_a_score = team_a_score;
//        }
//
//        public String getTeam_b_member_a_logo() {
//            return team_b_member_a_logo;
//        }
//
//        public void setTeam_b_member_a_logo(String team_b_member_a_logo) {
//            this.team_b_member_a_logo = team_b_member_a_logo;
//        }
//
//        public String getTeam_b_member_a_nickname() {
//            return team_b_member_a_nickname;
//        }
//
//        public void setTeam_b_member_a_nickname(String team_b_member_a_nickname) {
//            this.team_b_member_a_nickname = team_b_member_a_nickname;
//        }
//
//        public String getTeam_b_member_a_sex() {
//            return team_b_member_a_sex;
//        }
//
//        public void setTeam_b_member_a_sex(String team_b_member_a_sex) {
//            this.team_b_member_a_sex = team_b_member_a_sex;
//        }
//
//        public String getTeam_b_member_b_logo() {
//            return team_b_member_b_logo;
//        }
//
//        public void setTeam_b_member_b_logo(String team_b_member_b_logo) {
//            this.team_b_member_b_logo = team_b_member_b_logo;
//        }
//
//        public String getTeam_b_member_b_nickname() {
//            return team_b_member_b_nickname;
//        }
//
//        public void setTeam_b_member_b_nickname(String team_b_member_b_nickname) {
//            this.team_b_member_b_nickname = team_b_member_b_nickname;
//        }
//
//        public String getTeam_b_member_b_sex() {
//            return team_b_member_b_sex;
//        }
//
//        public void setTeam_b_member_b_sex(String team_b_member_b_sex) {
//            this.team_b_member_b_sex = team_b_member_b_sex;
//        }
//
//        public int getTeam_b_score() {
//            return team_b_score;
//        }
//
//        public void setTeam_b_score(int team_b_score) {
//            this.team_b_score = team_b_score;
//        }
//    }
}
