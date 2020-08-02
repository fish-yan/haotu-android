package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class TeachDetailBean  implements Serializable {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"coach_name":"王六","coach_phone_no":"13888888886","project_type":"游泳","course_list":[{"course_id":"17","group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_amount":"80.00","course_name":"蛙泳课程","sold_count":"0","course_original_amount":"100.00"},{"course_id":"18","group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_amount":"80.00","course_name":"蛙泳课程","sold_count":"0","course_original_amount":"100.00"},{"course_id":"19","group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_amount":"80.00","course_name":"蛙泳课程3","sold_count":"0","course_original_amount":"100.00"}],"comment_list":[{"user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","nick_name":"掌柜的","content":"蛙泳很棒","comment_time":"2019-04-10 16:39:56.0","comment_score":"3"}],"coach_image_list":[{"image_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"},{"image_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"},{"image_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public static class DataBean {
        /**
         * coach_name : 王六
         * coach_phone_no : 13888888886
         * project_type : 游泳
         * course_list : [{"course_id":"17","group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_amount":"80.00","course_name":"蛙泳课程","sold_count":"0","course_original_amount":"100.00"},{"course_id":"18","group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_amount":"80.00","course_name":"蛙泳课程","sold_count":"0","course_original_amount":"100.00"},{"course_id":"19","group_logo":"https://i04picsos.sogoucdn.com/352533c4d37c1573","course_amount":"80.00","course_name":"蛙泳课程3","sold_count":"0","course_original_amount":"100.00"}]
         * comment_list : [{"user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","nick_name":"掌柜的","content":"蛙泳很棒","comment_time":"2019-04-10 16:39:56.0","comment_score":"3"}]
         * coach_image_list : [{"image_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"},{"image_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"},{"image_url":"https://i04picsos.sogoucdn.com/352533c4d37c1573"}]
         */

        private String coach_name;
        private String coach_phone_no;
        private String project_type;
        private List<CourseListBean> course_list;
        private List<CommentListBean> comment_list;
        private List<CoachImageListBean> coach_image_list;

        public String getCoach_name() {
            return coach_name;
        }

        public void setCoach_name(String coach_name) {
            this.coach_name = coach_name;
        }

        public String getCoach_phone_no() {
            return coach_phone_no;
        }

        public void setCoach_phone_no(String coach_phone_no) {
            this.coach_phone_no = coach_phone_no;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public List<CourseListBean> getCourse_list() {
            return course_list;
        }

        public void setCourse_list(List<CourseListBean> course_list) {
            this.course_list = course_list;
        }

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public List<CoachImageListBean> getCoach_image_list() {
            return coach_image_list;
        }

        public void setCoach_image_list(List<CoachImageListBean> coach_image_list) {
            this.coach_image_list = coach_image_list;
        }

        public static class CourseListBean {
            /**
             * course_id : 17
             * group_logo : https://i04picsos.sogoucdn.com/352533c4d37c1573
             * course_amount : 80.00
             * course_name : 蛙泳课程
             * sold_count : 0
             * course_original_amount : 100.00
             */

            private String course_id;
            private String group_logo;
            private String course_amount;
            private String course_name;
            private String sold_count;
            private String course_original_amount;

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            public String getGroup_logo() {
                return group_logo;
            }

            public void setGroup_logo(String group_logo) {
                this.group_logo = group_logo;
            }

            public String getCourse_amount() {
                return course_amount;
            }

            public void setCourse_amount(String course_amount) {
                this.course_amount = course_amount;
            }

            public String getCourse_name() {
                return course_name;
            }

            public void setCourse_name(String course_name) {
                this.course_name = course_name;
            }

            public String getSold_count() {
                return sold_count;
            }

            public void setSold_count(String sold_count) {
                this.sold_count = sold_count;
            }

            public String getCourse_original_amount() {
                return course_original_amount;
            }

            public void setCourse_original_amount(String course_original_amount) {
                this.course_original_amount = course_original_amount;
            }
        }

        public static class CommentListBean {
            /**
             * user_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
             * nick_name : 掌柜的
             * content : 蛙泳很棒
             * comment_time : 2019-04-10 16:39:56.0
             * comment_score : 3
             */

            private String user_logo;
            private String nick_name;
            private String content;
            private String comment_time;
            private String comment_score;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getComment_time() {
                return comment_time;
            }

            public void setComment_time(String comment_time) {
                this.comment_time = comment_time;
            }

            public String getComment_score() {
                return comment_score;
            }

            public void setComment_score(String comment_score) {
                this.comment_score = comment_score;
            }
        }

        public static class CoachImageListBean {
            /**
             * image_url : https://i04picsos.sogoucdn.com/352533c4d37c1573
             */

            private String image_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
    }
}
