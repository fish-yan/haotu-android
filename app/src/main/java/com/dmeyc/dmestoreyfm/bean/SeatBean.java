package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SeatBean implements Serializable {

    /**
     * code : 200
     * data : {"activity_address":"金园六路319号","activity_id":99,"group_id":66,"group_name":"马来西亚沙登羽毛球俱乐部","member_list":[{"menber_no":"A","sex":"2","signUpId":70,"team_id":473,"team_member_id":945,"team_name":"第1组","user_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","user_nickname":"执着"},{"menber_no":"B","team_id":473,"team_member_id":946,"team_name":"第1组"},{"menber_no":"A","team_id":474,"team_member_id":947,"team_name":"第2组"},{"menber_no":"B","team_id":474,"team_member_id":948,"team_name":"第2组"}],"nick_name":"Hotu1034","payList":[{"pay_code":"1","pay_name":"微信"},{"pay_code":"2","pay_name":"支付宝"},{"pay_code":"3","pay_name":"银行卡"},{"amount":1000,"pay_code":"4","pay_name":"会费"}],"safeAmount":3,"sign_up_count":3,"signupAmount":10,"start_date":"2019-07-24 19:00:00","totalAmount":13}
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
         * activity_address : 金园六路319号
         * activity_id : 99
         * group_id : 66
         * group_name : 马来西亚沙登羽毛球俱乐部
         * member_list : [{"menber_no":"A","sex":"2","signUpId":70,"team_id":473,"team_member_id":945,"team_name":"第1组","user_logo":"https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132","user_nickname":"执着"},{"menber_no":"B","team_id":473,"team_member_id":946,"team_name":"第1组"},{"menber_no":"A","team_id":474,"team_member_id":947,"team_name":"第2组"},{"menber_no":"B","team_id":474,"team_member_id":948,"team_name":"第2组"}]
         * nick_name : Hotu1034
         * payList : [{"pay_code":"1","pay_name":"微信"},{"pay_code":"2","pay_name":"支付宝"},{"pay_code":"3","pay_name":"银行卡"},{"amount":1000,"pay_code":"4","pay_name":"会费"}]
         * safeAmount : 3
         * sign_up_count : 3
         * signupAmount : 10
         * start_date : 2019-07-24 19:00:00
         * totalAmount : 13
         */

        private String activity_address;
        private int activity_id;
        private int group_id;
        private String group_name;
        private String nick_name;
        private int safeAmount;
        private int sign_up_count;
        private int signupAmount;
        private String start_date;
        private int totalAmount;
        private List<MemberListBean> member_list;
        private List<PayListBean> payList;
        private String groupType;
         private String activity_name;
private String isSigned;

        public String getIsSigned() {
            return isSigned;
        }

        public void setIsSigned(String isSigned) {
            this.isSigned = isSigned;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

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

        public int getSafeAmount() {
            return safeAmount;
        }

        public void setSafeAmount(int safeAmount) {
            this.safeAmount = safeAmount;
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

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<MemberListBean> getMember_list() {
            return member_list;
        }

        public void setMember_list(List<MemberListBean> member_list) {
            this.member_list = member_list;
        }

        public List<PayListBean> getPayList() {
            return payList;
        }

        public void setPayList(List<PayListBean> payList) {
            this.payList = payList;
        }

        public static class MemberListBean {
            /**
             * menber_no : A
             * sex : 2
             * signUpId : 70
             * team_id : 473
             * team_member_id : 945
             * team_name : 第1组
             * user_logo : https://thirdwx.qlogo.cn/mmopen/vi_32/Uol7Rvt3IjnBjIk0zDEIl1GrN0YnkzmfqLiatzB8G3oSabbtoH7kGM9ricD4xbXIzAAprw5qB5pO1QCxFvXXHa1A/132
             * user_nickname : 执着
             */

            private String menber_no;
            private String sex;
            private int signUpId;
            private int team_id;
            private int team_member_id;
            private String team_name;
            private String user_logo;
            private String user_nickname;

            public String getMenber_no() {
                return menber_no;
            }

            public void setMenber_no(String menber_no) {
                this.menber_no = menber_no;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getSignUpId() {
                return signUpId;
            }

            public void setSignUpId(int signUpId) {
                this.signUpId = signUpId;
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

            public String getUser_logo() {
                return user_logo;
            }

            public void setUser_logo(String user_logo) {
                this.user_logo = user_logo;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }
        }

        public static class PayListBean {
            /**
             * pay_code : 1
             * pay_name : 微信
             * amount : 1000
             */

            private String pay_code;
            private String pay_name;
            private int amount;

            public String getPay_code() {
                return pay_code;
            }

            public void setPay_code(String pay_code) {
                this.pay_code = pay_code;
            }

            public String getPay_name() {
                return pay_name;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }
        }
    }
}
