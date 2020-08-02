package com.dmeyc.dmestoreyfm.ui;

import java.io.Serializable;
import java.util.List;

public class CommApplyBean implements Serializable {

    /**
     * code : 200
     * data : [{"create_time":"2019-05-17 17:59:58.0","group_name":"锝","id":6,"nick_name":"llife","status":"2","user_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132"},{"create_time":"2019-05-17 10:43:01.0","group_name":"锝","id":3,"nick_name":"康","status":"3","user_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png"},{"create_time":"2019-05-17 10:47:33.0","group_name":"锝","id":4,"status":"2","user_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIkso8rTkzbg5rKnATibpaavlLoQgeY6tFM7KZdlm5Vs4PzrPO5ib5uLWMsOCbvPGy6X9icY0GDxiaD0Q/132"},{"create_time":"2019-05-16 10:37:11.0","group_name":"锝","id":2,"nick_name":"规划","status":"2","user_logo":"http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg"},{"create_time":"2019-05-27 18:04:45.0","group_name":"锝","id":8,"nick_name":"规划","status":"3","user_logo":"http://192.168.0.104/group1/M00/00/0C/wKgAaF0TyBOAG027AACa5dm7wOA216.jpg"},{"create_time":"2019-05-23 16:43:10.0","group_name":"锝","id":7,"nick_name":"Hotu2284","status":"1","user_logo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg"},{"create_time":"2019-05-28 11:42:02.0","group_name":"锝","id":9,"nick_name":"Hotu8921","status":"1","user_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132"},{"create_time":"2019-05-28 11:42:03.0","group_name":"锝","id":10,"nick_name":"Hotu8921","status":"1","user_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132"}]
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
         * create_time : 2019-05-17 17:59:58.0
         * group_name : 锝
         * id : 6
         * nick_name : llife
         * status : 2
         * user_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
         */

        private String create_time;
        private String group_name;
        private int id;
        private String nick_name;
        private String status;
        private String user_logo;
        private String groupLogo;
        private int groupChallengeId;
        private int activityPkId;
         private String groupName;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getActivityPkId() {
            return activityPkId;
        }

        public void setActivityPkId(int activityPkId) {
            this.activityPkId = activityPkId;
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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_logo() {
            return user_logo;
        }

        public void setUser_logo(String user_logo) {
            this.user_logo = user_logo;
        }
    }
}
