package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class BClientChangesetBean implements Serializable {

    /**
     * code : 200
     * data : {"activity_address":"国定东路","activity_id":12,"group_id":6,"group_name":"红红火火","member_list":[{"menber_no":"A","team_id":33,"team_member_id":65,"team_name":"第1组"},{"menber_no":"B","team_id":33,"team_member_id":66,"team_name":"第1组"},{"menber_no":"A","team_id":34,"team_member_id":67,"team_name":"第2组"},{"menber_no":"B","team_id":34,"team_member_id":68,"team_name":"第2组"},{"menber_no":"A","team_id":35,"team_member_id":69,"team_name":"第3组"},{"menber_no":"B","team_id":35,"team_member_id":70,"team_name":"第3组"}],"nick_name":"规划","sign_up_count":0,"signupAmount":65,"start_date":"2019-05-16 09:56:00"}
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
         * activity_address : 国定东路
         * activity_id : 12
         * group_id : 6
         * group_name : 红红火火
         * member_list : [{"menber_no":"A","team_id":33,"team_member_id":65,"team_name":"第1组"},{"menber_no":"B","team_id":33,"team_member_id":66,"team_name":"第1组"},{"menber_no":"A","team_id":34,"team_member_id":67,"team_name":"第2组"},{"menber_no":"B","team_id":34,"team_member_id":68,"team_name":"第2组"},{"menber_no":"A","team_id":35,"team_member_id":69,"team_name":"第3组"},{"menber_no":"B","team_id":35,"team_member_id":70,"team_name":"第3组"}]
         * nick_name : 规划
         * sign_up_count : 0
         * signupAmount : 65
         * start_date : 2019-05-16 09:56:00
         */

        private String activity_address;
        private int activity_id;
        private int group_id;
        private String group_name;
        private String nick_name;
        private int sign_up_count;
        private int signupAmount;
        private String start_date;
        private List<MemberListBean> member_list;

        public String getActivity_address() {
            return activity_address;
        }

        public void setActivity_address(String activity_address) {
            this.activity_address = activity_address;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getSign_up_count() {
            return sign_up_count;
        }

        public void setSign_up_count(int sign_up_count) {
            this.sign_up_count = sign_up_count;
        }

        public int getSignupAmount() {
            return signupAmount;
        }

        public void setSignupAmount(int signupAmount) {
            this.signupAmount = signupAmount;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public List<MemberListBean> getMember_list() {
            return member_list;
        }

        public void setMember_list(List<MemberListBean> member_list) {
            this.member_list = member_list;
        }

        public static class MemberListBean {
            /**
             * menber_no : A
             * team_id : 33
             * team_member_id : 65
             * team_name : 第1组
             */

            private String menber_no;
            private int team_id;
            private int team_member_id;
            private String team_name;
            private String sex;
            private int signUpId;
            private String user_logo;
            private String user_nickname;

            public int getSignUpId() {
                return signUpId;
            }

            public String getSex() {
                return sex;
            }

            public String getUser_logo() {
                return user_logo;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void setSignUpId(int signUpId) {
                this.signUpId = signUpId;
            }

            public void setUser_logo(String user_logo) {
                this.user_logo = user_logo;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public String getMenber_no() {
                return menber_no;
            }

            public void setMenber_no(String menber_no) {
                this.menber_no = menber_no;
            }

            public int getTeam_id() {
                return team_id;
            }

            public void setTeam_id(int team_id) {
                this.team_id = team_id;
            }

            public int getTeam_member_id() {
                return team_member_id;
            }

            public void setTeam_member_id(int team_member_id) {
                this.team_member_id = team_member_id;
            }

            public String getTeam_name() {
                return team_name;
            }

            public void setTeam_name(String team_name) {
                this.team_name = team_name;
            }
        }
    }
}
