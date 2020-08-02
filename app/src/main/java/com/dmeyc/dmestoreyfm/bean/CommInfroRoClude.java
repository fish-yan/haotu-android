package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class CommInfroRoClude implements Serializable {

    /**
     * code : 200
     * data : {"battle_number":100,"groupTotalNo":3,"groupType":"1","group_id":96,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg","group_member_images":[{"url":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","user_id":4},{"url":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","user_id":6},{"url":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DTZCAKJTsAAEuICbOP2M576.jpg","user_id":8}],"group_name":"安卓开发","is_all_ban":"0","is_follow":"0","is_join":"2","owner_logo":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","owner_name":"Hotu1034","phone_no":"17182701034","remark":"安吉拉考虑考虑了可口可乐了住呢看看"}
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
         * battle_number : 100
         * groupTotalNo : 3
         * groupType : 1
         * group_id : 96
         * group_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFzlu02ATtEKAAGOqZwaDqY293.jpg
         * group_member_images : [{"url":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg","user_id":4},{"url":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","user_id":6},{"url":"http://192.168.0.104/group1/M00/00/18/wKgAaF1DTZCAKJTsAAEuICbOP2M576.jpg","user_id":8}]
         * group_name : 安卓开发
         * is_all_ban : 0
         * is_follow : 0
         * is_join : 2
         * owner_logo : http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg
         * owner_name : Hotu1034
         * phone_no : 17182701034
         * remark : 安吉拉考虑考虑了可口可乐了住呢看看
         */

        private int battle_number;
        private int groupTotalNo;
        private String groupType;
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
        private List<GroupMemberImagesBean> group_member_images;

        public int getBattle_number() {
            return battle_number;
        }

        public void setBattle_number(int battle_number) {
            this.battle_number = battle_number;
        }

        public int getGroupTotalNo() {
            return groupTotalNo;
        }

        public void setGroupTotalNo(int groupTotalNo) {
            this.groupTotalNo = groupTotalNo;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
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

        public List<GroupMemberImagesBean> getGroup_member_images() {
            return group_member_images;
        }

        public void setGroup_member_images(List<GroupMemberImagesBean> group_member_images) {
            this.group_member_images = group_member_images;
        }

        public static class GroupMemberImagesBean {
            /**
             * url : http://192.168.0.104/group1/M00/00/18/wKgAaF1DMmqAZO9FAAFWtgVJd6U777.jpg
             * user_id : 4
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
