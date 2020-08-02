package com.dmeyc.dmestoreyfm.bean.response;

import java.util.List;

/**
 * create by cxg on 2019/12/17
 */
public class GroupMemberBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"group_name":"方法","total_amount":0,"groupMemberList":[{"user_group_account_id":855,"user_id":493,"user_logo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","nick_name":"Hotu4682","create_time":"2019-12-05 21:42:17.0","level":1,"amount":0,"is_ban":"0","group_mark":"1","user_token":"365c858061f2484bad3b9f63fc9f56e2","sex":"1"},{"user_group_account_id":871,"user_id":492,"user_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/SCBpWZw1RbZI4njD7ScaXpL9YZ1ibJD11nUibbiayibjhjiaFBeh1xZ4As97W6k3FjGTbSAciboiaYnUicNOtKsny0ENYg/132","nick_name":"Yan.X","create_time":"2019-12-17 10:35:56.0","level":0,"amount":0,"is_ban":"0","group_mark":"3","user_token":"d05a21363ad4405190cb3ffea5ec5b52","sex":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * group_name : 方法
         * total_amount : 0
         * groupMemberList : [{"user_group_account_id":855,"user_id":493,"user_logo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","nick_name":"Hotu4682","create_time":"2019-12-05 21:42:17.0","level":1,"amount":0,"is_ban":"0","group_mark":"1","user_token":"365c858061f2484bad3b9f63fc9f56e2","sex":"1"},{"user_group_account_id":871,"user_id":492,"user_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/SCBpWZw1RbZI4njD7ScaXpL9YZ1ibJD11nUibbiayibjhjiaFBeh1xZ4As97W6k3FjGTbSAciboiaYnUicNOtKsny0ENYg/132","nick_name":"Yan.X","create_time":"2019-12-17 10:35:56.0","level":0,"amount":0,"is_ban":"0","group_mark":"3","user_token":"d05a21363ad4405190cb3ffea5ec5b52","sex":"1"}]
         */

        private String group_name;
        private String total_amount;
        private List<GroupMemberListBean> groupMemberList;

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public List<GroupMemberListBean> getGroupMemberList() {
            return groupMemberList;
        }

        public void setGroupMemberList(List<GroupMemberListBean> groupMemberList) {
            this.groupMemberList = groupMemberList;
        }

        public static class GroupMemberListBean {
            /**
             * user_group_account_id : 855
             * user_id : 493
             * user_logo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png
             * nick_name : Hotu4682
             * create_time : 2019-12-05 21:42:17.0
             * level : 1
             * amount : 0
             * is_ban : 0
             * group_mark : 1
             * user_token : 365c858061f2484bad3b9f63fc9f56e2
             * sex : 1
             */

            private String user_group_account_id;
            private String user_id;
            private String user_logo;
            private String nick_name;
            private String create_time;
            private String level;
            private String amount;
            private String is_ban;
            private String group_mark;
            private String user_token;
            private String sex;

            public String getUser_group_account_id() {
                return user_group_account_id;
            }

            public void setUser_group_account_id(String user_group_account_id) {
                this.user_group_account_id = user_group_account_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_logo() {
                return user_logo;
            }

            public void setUser_logo(String user_logo) {
                this.user_logo = user_logo;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getIs_ban() {
                return is_ban;
            }

            public void setIs_ban(String is_ban) {
                this.is_ban = is_ban;
            }

            public String getGroup_mark() {
                return group_mark;
            }

            public void setGroup_mark(String group_mark) {
                this.group_mark = group_mark;
            }

            public String getUser_token() {
                return user_token;
            }

            public void setUser_token(String user_token) {
                this.user_token = user_token;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }
}
