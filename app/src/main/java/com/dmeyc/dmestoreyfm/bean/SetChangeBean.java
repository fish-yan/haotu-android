package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SetChangeBean implements Serializable {

    /**
     * code : 200
     * data : {"activity_id":44,"group_id":32,"member_list":[{"menber_no":"A","team_member_id":623,"team_name":"第1组"},{"menber_no":"B","team_member_id":624,"team_name":"第1组"},{"menber_no":"A","team_member_id":625,"team_name":"第2组"},{"menber_no":"B","team_member_id":626,"team_name":"第2组"},{"menber_no":"A","team_member_id":627,"team_name":"第3组"},{"menber_no":"B","team_member_id":628,"team_name":"第3组"}],"sign_up_count":0}
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
         * activity_id : 44
         * group_id : 32
         * member_list : [{"menber_no":"A","team_member_id":623,"team_name":"第1组"},{"menber_no":"B","team_member_id":624,"team_name":"第1组"},{"menber_no":"A","team_member_id":625,"team_name":"第2组"},{"menber_no":"B","team_member_id":626,"team_name":"第2组"},{"menber_no":"A","team_member_id":627,"team_name":"第3组"},{"menber_no":"B","team_member_id":628,"team_name":"第3组"}]
         * sign_up_count : 0
         */

        private int activity_id;
        private int group_id;
        private int sign_up_count;

        private List<MemberListBean> member_list;




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

        public int getSign_up_count() {
            return sign_up_count;
        }

        public void setSign_up_count(int sign_up_count) {
            this.sign_up_count = sign_up_count;
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
             * team_member_id : 623
             * team_name : 第1组
             */
            private String user_nickname;
            private String menber_no;
            private int team_member_id;
            private String team_name;
            private String signUpId;

            public String getSignUpId() {
                return signUpId;
            }

            public void setSignUpId(String signUpId) {
                this.signUpId = signUpId;
            }

            public String getUser_nickname() {
                return user_nickname;
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
