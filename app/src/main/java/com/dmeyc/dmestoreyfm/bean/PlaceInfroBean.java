package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class PlaceInfroBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"venue_name":"你","venue_linkman":"那","venue_phone_no":"15026843402","venue_address":"人民广场","venueImageList":[{"image_url":"http://192.168.0.104/group1/M00/00/00/wKgAaFyu0XWAK1HkAADI7W42InY638.jpg"}],"venueCommentList":[{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBlyADED1AAGKISFBQr4980.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBmWAePiWAADLTF29n0g545.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBm-AHsQwAAGJIpdXiVo019.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBlyADED1AAGKISFBQr4980.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBmWAePiWAADLTF29n0g545.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBm-AHsQwAAGJIpdXiVo019.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBlyADED1AAGKISFBQr4980.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBmWAePiWAADLTF29n0g545.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBm-AHsQwAAGJIpdXiVo019.jpg","nick_name":"王一","comment_score":"5"}]}
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
         * venue_name : 你
         * venue_linkman : 那
         * venue_phone_no : 15026843402
         * venue_address : 人民广场
         * venueImageList : [{"image_url":"http://192.168.0.104/group1/M00/00/00/wKgAaFyu0XWAK1HkAADI7W42InY638.jpg"}]
         * venueCommentList : [{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBlyADED1AAGKISFBQr4980.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBmWAePiWAADLTF29n0g545.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBm-AHsQwAAGJIpdXiVo019.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBlyADED1AAGKISFBQr4980.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBmWAePiWAADLTF29n0g545.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBm-AHsQwAAGJIpdXiVo019.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBlyADED1AAGKISFBQr4980.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBmWAePiWAADLTF29n0g545.jpg","nick_name":"王一","comment_score":"5"},{"content":"好","comment_time":"2019-04-03 19:50:18.0","user_logo":"http://192.168.0.104/group1/M00/00/01/wKgAaFyzBm-AHsQwAAGJIpdXiVo019.jpg","nick_name":"王一","comment_score":"5"}]
         */

        private String venue_name;
        private String venue_linkman;
        private String venue_phone_no;
        private String venue_address;
        private List<VenueImageListBean> venueImageList;
        private List<VenueCommentListBean> venueCommentList;

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getVenue_linkman() {
            return venue_linkman;
        }

        public void setVenue_linkman(String venue_linkman) {
            this.venue_linkman = venue_linkman;
        }

        public String getVenue_phone_no() {
            return venue_phone_no;
        }

        public void setVenue_phone_no(String venue_phone_no) {
            this.venue_phone_no = venue_phone_no;
        }

        public String getVenue_address() {
            return venue_address;
        }

        public void setVenue_address(String venue_address) {
            this.venue_address = venue_address;
        }

        public List<VenueImageListBean> getVenueImageList() {
            return venueImageList;
        }

        public void setVenueImageList(List<VenueImageListBean> venueImageList) {
            this.venueImageList = venueImageList;
        }

        public List<VenueCommentListBean> getVenueCommentList() {
            return venueCommentList;
        }

        public void setVenueCommentList(List<VenueCommentListBean> venueCommentList) {
            this.venueCommentList = venueCommentList;
        }

        public static class VenueImageListBean {
            /**
             * image_url : http://192.168.0.104/group1/M00/00/00/wKgAaFyu0XWAK1HkAADI7W42InY638.jpg
             */

            private String image_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }

        public static class VenueCommentListBean {
            /**
             * content : 好
             * comment_time : 2019-04-03 19:50:18.0
             * user_logo : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1205100974,216662483&fm=26&gp=0.jpg
             * nick_name : 王一
             * comment_score : 5
             */

            private String content;
            private String comment_time;
            private String user_logo;
            private String nick_name;
            private String comment_score;

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

            public String getComment_score() {
                return comment_score;
            }

            public void setComment_score(String comment_score) {
                this.comment_score = comment_score;
            }
        }
    }
}
