package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ClubResultBean implements Serializable {
    /**
     * code : 200
     * data : {"basicInfo":{"battleNumber":100,"groupId":10,"groupLogo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr5-ABo37AAFNMj6_93Y349.jpg","groupName":"白老师羽毛球俱乐部"},"matchList":[{"id":67,"team_a_member_a_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","team_a_member_a_nickname":"好兔","team_a_member_a_sex":"1","team_a_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","team_a_member_b_nickname":"执着","team_a_member_b_sex":"2","team_a_score":21,"team_b_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoyQYTZupbwbPhud5rdoE2Sg/132","team_b_member_a_nickname":"白老师","team_b_member_a_sex":"1","team_b_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_b_member_b_nickname":"llife","team_b_member_b_sex":"1","team_b_score":10}]}
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
         * basicInfo : {"battleNumber":100,"groupId":10,"groupLogo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr5-ABo37AAFNMj6_93Y349.jpg","groupName":"白老师羽毛球俱乐部"}
         * matchList : [{"id":67,"team_a_member_a_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","team_a_member_a_nickname":"好兔","team_a_member_a_sex":"1","team_a_member_b_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","team_a_member_b_nickname":"执着","team_a_member_b_sex":"2","team_a_score":21,"team_b_member_a_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoyQYTZupbwbPhud5rdoE2Sg/132","team_b_member_a_nickname":"白老师","team_b_member_a_sex":"1","team_b_member_b_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","team_b_member_b_nickname":"llife","team_b_member_b_sex":"1","team_b_score":10}]
         */

        private BasicInfoBean basicInfo;
        private List<MatchListBean> matchList;

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public List<MatchListBean> getMatchList() {
            return matchList;
        }

        public void setMatchList(List<MatchListBean> matchList) {
            this.matchList = matchList;
        }

        public static class BasicInfoBean {
            /**
             * battleNumber : 100
             * groupId : 10
             * groupLogo : http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr5-ABo37AAFNMj6_93Y349.jpg
             * groupName : 白老师羽毛球俱乐部
             */

            private int battleNumber;
            private int groupId;
            private String groupLogo;
            private String groupName;

            public int getBattleNumber() {
                return battleNumber;
            }

            public void setBattleNumber(int battleNumber) {
                this.battleNumber = battleNumber;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
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
        }

        public static class MatchListBean {
            /**
             * id : 67
             * team_a_member_a_logo : https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132
             * team_a_member_a_nickname : 好兔
             * team_a_member_a_sex : 1
             * team_a_member_b_logo : https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132
             * team_a_member_b_nickname : 执着
             * team_a_member_b_sex : 2
             * team_a_score : 21
             * team_b_member_a_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoyQYTZupbwbPhud5rdoE2Sg/132
             * team_b_member_a_nickname : 白老师
             * team_b_member_a_sex : 1
             * team_b_member_b_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
             * team_b_member_b_nickname : llife
             * team_b_member_b_sex : 1
             * team_b_score : 10
             */

            private int id;
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
//
//    /**
//     * code : 200
//     * data : {"basicInfo":{"battleNumber":100,"groupId":9,"groupLogo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr4CAcZCgAAGOBhPV6qc958.jpg","groupName":"聂聂普"},"matchList":[]}
//     * msg : 操作成功
//     */
//
//    private String code;
//    private DataBean data;
//    private String msg;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
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
//    public static class DataBean {
//        /**
//         * basicInfo : {"battleNumber":100,"groupId":9,"groupLogo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr4CAcZCgAAGOBhPV6qc958.jpg","groupName":"聂聂普"}
//         * matchList : []
//         */
//
//        private BasicInfoBean basicInfo;
//        private List<?> matchList;
//
//        public BasicInfoBean getBasicInfo() {
//            return basicInfo;
//        }
//
//        public void setBasicInfo(BasicInfoBean basicInfo) {
//            this.basicInfo = basicInfo;
//        }
//
//        public List<?> getMatchList() {
//            return matchList;
//        }
//
//        public void setMatchList(List<?> matchList) {
//            this.matchList = matchList;
//        }
//
//        public static class BasicInfoBean {
//            /**
//             * battleNumber : 100
//             * groupId : 9
//             * groupLogo : http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr4CAcZCgAAGOBhPV6qc958.jpg
//             * groupName : 聂聂普
//             */
//
//            private int battleNumber;
//            private int groupId;
//            private String groupLogo;
//            private String groupName;
//
//            public int getBattleNumber() {
//                return battleNumber;
//            }
//
//            public void setBattleNumber(int battleNumber) {
//                this.battleNumber = battleNumber;
//            }
//
//            public int getGroupId() {
//                return groupId;
//            }
//
//            public void setGroupId(int groupId) {
//                this.groupId = groupId;
//            }
//
//            public String getGroupLogo() {
//                return groupLogo;
//            }
//
//            public void setGroupLogo(String groupLogo) {
//                this.groupLogo = groupLogo;
//            }
//
//            public String getGroupName() {
//                return groupName;
//            }
//
//            public void setGroupName(String groupName) {
//                this.groupName = groupName;
//            }
//        }
//    }


}
