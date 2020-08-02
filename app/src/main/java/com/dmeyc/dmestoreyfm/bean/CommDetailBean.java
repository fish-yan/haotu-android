package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommDetailBean implements Serializable {

    /**
     * code : 200
     * data : {"active_number":0,"activity_id":27,"activity_total_no":4,"activity_venue_address":"国和路500弄","battle_number":0,"follow_count":1,"group_id":10,"group_logo":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHQ5SAH7abAAIdSPiW62U319.jpg","group_member_images":[{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":26},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":27},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":28},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":29},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":30},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":31},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":33},{"user_id":1}],"group_name":"老虎羽毛球俱乐部","is_all_ban":"0","is_follow":"0","is_join":"0","owner_logo":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","owner_name":"Hotu5652","phone_no":"15035755652","remark":"我","sign_up_no":1,"start_date":"2019-05-06 20:40:00"}
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
         * active_number : 0
         * activity_id : 27
         * activity_total_no : 4
         * activity_venue_address : 国和路500弄
         * battle_number : 0
         * follow_count : 1
         * group_id : 10
         * group_logo : http://192.168.0.104/group1/M00/00/04/wKgAaFzHQ5SAH7abAAIdSPiW62U319.jpg
         * group_member_images : [{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":26},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":27},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":28},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":29},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":30},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":31},{"url":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","user_id":33},{"user_id":1}]
         * group_name : 老虎羽毛球俱乐部
         * is_all_ban : 0
         * is_follow : 0
         * is_join : 0
         * owner_logo : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
         * owner_name : Hotu5652
         * phone_no : 15035755652
         * remark : 我
         * sign_up_no : 1
         * start_date : 2019-05-06 20:40:00
         */

        private int active_number;
        private int activity_id;
        private int activity_total_no;
        private String activity_venue_address;
        private int battle_number;
        private int follow_count;
        private int group_id;
        private String group_logo;
        private String group_name;
        private String is_all_ban;
        private String is_follow;
        private String is_join;
        private String owner_logo;
        private String owner_name;
        private String phone_no;
        private String remark;
        private int sign_up_no;
        private String start_date;
        private String notice;
        private String groupType;
        private ArrayList<String> remarkImgs;
        private ArrayList<String> educateImgs;
       private String educateBackground;

       private String create_time;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEducateBackground() {
            return educateBackground;
        }

        public void setEducateBackground(String educateBackground) {
            this.educateBackground = educateBackground;
        }

        public ArrayList<String> getEducateImgs() {
            return educateImgs;
        }

        public void setEducateImgs(ArrayList<String> educateImgs) {
            this.educateImgs = educateImgs;
        }

        public ArrayList<String> getRemarkImgs() {
            return remarkImgs;
        }

        public void setRemarkImgs(ArrayList<String> remarkImgs) {
            this.remarkImgs = remarkImgs;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        private List<GroupMemberImagesBean> group_member_images;

        public int getActive_number() {
            return active_number;
        }

        public void setActive_number(int active_number) {
            this.active_number = active_number;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getActivity_total_no() {
            return activity_total_no;
        }

        public void setActivity_total_no(int activity_total_no) {
            this.activity_total_no = activity_total_no;
        }

        public String getActivity_venue_address() {
            return activity_venue_address;
        }

        public void setActivity_venue_address(String activity_venue_address) {
            this.activity_venue_address = activity_venue_address;
        }

        public int getBattle_number() {
            return battle_number;
        }

        public void setBattle_number(int battle_number) {
            this.battle_number = battle_number;
        }

        public int getFollow_count() {
            return follow_count;
        }

        public void setFollow_count(int follow_count) {
            this.follow_count = follow_count;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getIs_all_ban() {
            return is_all_ban;
        }

        public void setIs_all_ban(String is_all_ban) {
            this.is_all_ban = is_all_ban;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getIs_join() {
            return is_join;
        }

        public void setIs_join(String is_join) {
            this.is_join = is_join;
        }

        public String getOwner_logo() {
            return owner_logo;
        }

        public void setOwner_logo(String owner_logo) {
            this.owner_logo = owner_logo;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public String getPhone_no() {
            return phone_no;
        }

        public void setPhone_no(String phone_no) {
            this.phone_no = phone_no;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSign_up_no() {
            return sign_up_no;
        }

        public void setSign_up_no(int sign_up_no) {
            this.sign_up_no = sign_up_no;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public List<GroupMemberImagesBean> getGroup_member_images() {
            return group_member_images;
        }

        public void setGroup_member_images(List<GroupMemberImagesBean> group_member_images) {
            this.group_member_images = group_member_images;
        }

        public static class GroupMemberImagesBean {
            /**
             * url : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg
             * user_id : 26
             */

            private String url;
            private int user_id;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }
    }
}
