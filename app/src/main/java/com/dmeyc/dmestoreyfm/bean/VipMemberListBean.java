package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class VipMemberListBean implements Serializable {

    /**
     * code : 200
     * data : {"groupMemberList":[{"amount":0,"create_time":"2019-04-14 15:12:04.0","level":1,"nick_name":"王一","user_group_account_id":21,"user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg"}],"group_name":"哈哈哈","total_amount":0}
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
         * groupMemberList : [{"amount":0,"create_time":"2019-04-14 15:12:04.0","level":1,"nick_name":"王一","user_group_account_id":21,"user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg"}]
         * group_name : 哈哈哈
         * total_amount : 0
         */

        private String group_name;
        private double total_amount;
        private List<GroupMemberListBean> groupMemberList;

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
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
             * amount : 0
             * create_time : 2019-04-14 15:12:04.0
             * level : 1
             * nick_name : 王一
             * user_group_account_id : 21
             * user_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
             */

            private double amount;
            private String create_time;
            private int level;
            private String nick_name;
            private int user_group_account_id;
            private String user_logo;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getUser_group_account_id() {
                return user_group_account_id;
            }

            public void setUser_group_account_id(int user_group_account_id) {
                this.user_group_account_id = user_group_account_id;
            }

            public String getUser_logo() {
                return user_logo;
            }

            public void setUser_logo(String user_logo) {
                this.user_logo = user_logo;
            }
        }
    }
}
