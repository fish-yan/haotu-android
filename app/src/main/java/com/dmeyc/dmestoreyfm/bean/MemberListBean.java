package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;
import java.util.List;

public class MemberListBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"managerList":[{"group_id":"1","head_icon":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png","id":1,"identify_status":"1","nick_name":"康","sex":"1"},{"group_id":"1","head_icon":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","id":2,"identify_status":"2","nick_name":"Hotu0000","sex":"2"}],"normalList":[{"group_id":"1","head_icon":"http://192.168.0.104/group1/M00/00/09/wKgAaFzcOnSAS0CRAADRAAzRZaM860.jpg","id":32,"identify_status":"3","nick_name":"Hotu3402","sex":"1"},{"group_id":"1","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","id":38,"identify_status":"3","nick_name":"llife","sex":"1"},{"group_id":"1","head_icon":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","id":122,"identify_status":"3","nick_name":"Hotu1111","sex":"2"},{"group_id":"1","head_icon":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-BaAFIN_AAEIxhxMqIE148.jpg","id":123,"identify_status":"3","nick_name":"Hotu2222","sex":"1"},{"group_id":"1","head_icon":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-JCAfgsYAADPpKNgEZs296.jpg","id":124,"identify_status":"3","nick_name":"Hotu3333","sex":"1"},{"group_id":"1","head_icon":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-OmAY8uAAADcWT1JDCo890.jpg","id":125,"identify_status":"3","nick_name":"Hotu4444","sex":"1"},{"group_id":"1","head_icon":"http://192.168.0.104/group1/M00/00/09/wKgAaFze-SaARwGkAAFu8yiyxck380.jpg","id":126,"identify_status":"3","nick_name":"Hotu5555","sex":"1"},{"group_id":"1","head_icon":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3593615691,3699649323&fm=26&gp=0.jpg","id":129,"identify_status":"3","nick_name":"Hotu8921","sex":"2"},{"group_id":"1","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eprJeCDDj1BzR9EkiczIdZ6DkyJYMYpSojXezNTwSnXzuibRafy0MJVFK3zSic4j0fwBAjIF5yCHfh5Q/132","id":182,"identify_status":"3","nick_name":"咸咖啡","sex":"1"}]}
     * msg : 操作成功
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        private List<ManagerListBean> managerList;
        private List<NormalListBean> normalList;

        public List<ManagerListBean> getManagerList() {
            return managerList;
        }

        public void setManagerList(List<ManagerListBean> managerList) {
            this.managerList = managerList;
        }

        public List<NormalListBean> getNormalList() {
            return normalList;
        }

        public void setNormalList(List<NormalListBean> normalList) {
            this.normalList = normalList;
        }

        public static class ManagerListBean {
            /**
             * group_id : 1
             * head_icon : http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png
             * id : 1
             * identify_status : 1
             * nick_name : 康
             * sex : 1
             */

            private String group_id;
            private String head_icon;
            private int id;
            private String identify_status;
            private String nick_name;
            private String sex;
            private String is_follower;
            private int user_id;

            public String getIs_follower() {
                return is_follower;
            }

            public void setIs_follower(String is_follower) {
                this.is_follower = is_follower;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getHead_icon() {
                return head_icon;
            }

            public void setHead_icon(String head_icon) {
                this.head_icon = head_icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdentify_status() {
                return identify_status;
            }

            public void setIdentify_status(String identify_status) {
                this.identify_status = identify_status;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }

        public static class NormalListBean {
            /**
             * group_id : 1
             * head_icon : http://192.168.0.104/group1/M00/00/09/wKgAaFzcOnSAS0CRAADRAAzRZaM860.jpg
             * id : 32
             * identify_status : 3
             * nick_name : Hotu3402
             * sex : 1
             */

            private String group_id;
            private String head_icon;
            private int id;
            private String identify_status;
            private String nick_name;
            private String sex;
              private int user_id;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getHead_icon() {
                return head_icon;
            }

            public void setHead_icon(String head_icon) {
                this.head_icon = head_icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdentify_status() {
                return identify_status;
            }

            public void setIdentify_status(String identify_status) {
                this.identify_status = identify_status;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
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
