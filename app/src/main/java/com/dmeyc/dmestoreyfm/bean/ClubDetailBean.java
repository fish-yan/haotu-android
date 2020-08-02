package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ClubDetailBean implements Serializable {

    /**
     * code : 200
     * data : {"ActivityRankResult":[{"activityId":2,"loserNo":1,"memberALogo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","memberANickName":"好兔","memberASex":"1","memberBLogo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","memberBNickName":"执着","memberBSex":"2","rankingNo":1,"team_id":13,"winnerNo":9},{"activityId":2,"loserNo":5,"memberALogo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoyQYTZupbwbPhud5rdoE2Sg/132","memberANickName":"白老师","memberASex":"1","memberBLogo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","memberBNickName":"llife","memberBSex":"1","rankingNo":2,"team_id":14,"winnerNo":5}],"activitybaseInfo":{"activityId":2,"activityName":"中高级娱乐场","groupName":"白老师羽毛球俱乐部","organizedName":"白老师","startTime":"2019-07-08 19:00:00"},"groupBaseInfo":{"battleNumber":100,"groupId":10,"groupLogo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr5-ABo37AAFNMj6_93Y349.jpg","groupName":"白老师羽毛球俱乐部"}}
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
         * ActivityRankResult : [{"activityId":2,"loserNo":1,"memberALogo":"https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132","memberANickName":"好兔","memberASex":"1","memberBLogo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","memberBNickName":"执着","memberBSex":"2","rankingNo":1,"team_id":13,"winnerNo":9},{"activityId":2,"loserNo":5,"memberALogo":"http://thirdwx.qlogo.cn/mmopen/vi_32/WRQA0OmlePephOQznVQSyXUDE2RibKKicV768eETauuUIZOXkPms4R8weveN9j0DXoyQYTZupbwbPhud5rdoE2Sg/132","memberANickName":"白老师","memberASex":"1","memberBLogo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","memberBNickName":"llife","memberBSex":"1","rankingNo":2,"team_id":14,"winnerNo":5}]
         * activitybaseInfo : {"activityId":2,"activityName":"中高级娱乐场","groupName":"白老师羽毛球俱乐部","organizedName":"白老师","startTime":"2019-07-08 19:00:00"}
         * groupBaseInfo : {"battleNumber":100,"groupId":10,"groupLogo":"http://192.168.0.104/group1/M00/00/0E/wKgAaF0fr5-ABo37AAFNMj6_93Y349.jpg","groupName":"白老师羽毛球俱乐部"}
         */

        private ActivitybaseInfoBean activitybaseInfo;
        private GroupBaseInfoBean groupBaseInfo;
        private List<ActivityRankResultBean> ActivityRankResult;

        public ActivitybaseInfoBean getActivitybaseInfo() {
            return activitybaseInfo;
        }

        public void setActivitybaseInfo(ActivitybaseInfoBean activitybaseInfo) {
            this.activitybaseInfo = activitybaseInfo;
        }

        public GroupBaseInfoBean getGroupBaseInfo() {
            return groupBaseInfo;
        }

        public void setGroupBaseInfo(GroupBaseInfoBean groupBaseInfo) {
            this.groupBaseInfo = groupBaseInfo;
        }

        public List<ActivityRankResultBean> getActivityRankResult() {
            return ActivityRankResult;
        }

        public void setActivityRankResult(List<ActivityRankResultBean> ActivityRankResult) {
            this.ActivityRankResult = ActivityRankResult;
        }

        public static class ActivitybaseInfoBean {
            /**
             * activityId : 2
             * activityName : 中高级娱乐场
             * groupName : 白老师羽毛球俱乐部
             * organizedName : 白老师
             * startTime : 2019-07-08 19:00:00
             */

            private int activityId;
            private String activityName;
            private String groupName;
            private String organizedName;
            private String startTime;
          private String activityAddress;

            public String getActivityAddress() {
                return activityAddress;
            }

            public void setActivityAddress(String activityAddress) {
                this.activityAddress = activityAddress;
            }

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getOrganizedName() {
                return organizedName;
            }

            public void setOrganizedName(String organizedName) {
                this.organizedName = organizedName;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }
        }

        public static class GroupBaseInfoBean {
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

        public static class ActivityRankResultBean {
            /**
             * activityId : 2
             * loserNo : 1
             * memberALogo : https://thirdwx.qlogo.cn/mmopen/vi_32/KqPuMLU8JmQfheRlZNPuVqtRmEdb9envv7qszA6CWMGW3djHr4YibC86pGUS7OPlDeR8xng4wxwh3poQUoFGaCQ/132
             * memberANickName : 好兔
             * memberASex : 1
             * memberBLogo : https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132
             * memberBNickName : 执着
             * memberBSex : 2
             * rankingNo : 1
             * team_id : 13
             * winnerNo : 9
             */

            private int activityId;
            private int loserNo;
            private String memberALogo;
            private String memberANickName;
            private String memberASex;
            private String memberBLogo;
            private String memberBNickName;
            private String memberBSex;
            private int rankingNo;
            private int team_id;
            private int winnerNo;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public int getLoserNo() {
                return loserNo;
            }

            public void setLoserNo(int loserNo) {
                this.loserNo = loserNo;
            }

            public String getMemberALogo() {
                return memberALogo;
            }

            public void setMemberALogo(String memberALogo) {
                this.memberALogo = memberALogo;
            }

            public String getMemberANickName() {
                return memberANickName;
            }

            public void setMemberANickName(String memberANickName) {
                this.memberANickName = memberANickName;
            }

            public String getMemberASex() {
                return memberASex;
            }

            public void setMemberASex(String memberASex) {
                this.memberASex = memberASex;
            }

            public String getMemberBLogo() {
                return memberBLogo;
            }

            public void setMemberBLogo(String memberBLogo) {
                this.memberBLogo = memberBLogo;
            }

            public String getMemberBNickName() {
                return memberBNickName;
            }

            public void setMemberBNickName(String memberBNickName) {
                this.memberBNickName = memberBNickName;
            }

            public String getMemberBSex() {
                return memberBSex;
            }

            public void setMemberBSex(String memberBSex) {
                this.memberBSex = memberBSex;
            }

            public int getRankingNo() {
                return rankingNo;
            }

            public void setRankingNo(int rankingNo) {
                this.rankingNo = rankingNo;
            }

            public int getTeam_id() {
                return team_id;
            }

            public void setTeam_id(int team_id) {
                this.team_id = team_id;
            }

            public int getWinnerNo() {
                return winnerNo;
            }

            public void setWinnerNo(int winnerNo) {
                this.winnerNo = winnerNo;
            }
        }
    }
}
