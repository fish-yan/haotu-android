package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class FirstActivityListBean implements Serializable {

    /**
     * code : 200
     * data : [{"activity_address":"国定东路","activity_id":104,"activity_type":"羽毛球","distance":"7.8km","group_id":2,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4--AWS_wAADFustiZb4810.jpg","group_name":"垂直网络运动群","group_type":"1","headIcon":"http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132","is_safe":"0","owner_name":"llife","sign_up_list":[],"sign_up_no":0,"startDateSort":1559663580000,"start_date":"2019-06-04 11:53:00","status":"3","total_no":4},{"activity_address":"政府路18号","activity_id":106,"activity_type":"羽毛球","distance":"8.9km","group_id":1,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4_aAL6GzAADPpKNgEZs923.jpg","group_name":"政府羽毛球俱乐部","group_type":"1","headIcon":"http://192.168.0.104/group1/M00/00/09/wKgAaFza4-SAPF0-AAAWeApgbg4782.png","is_safe":"0","owner_name":"康","sign_up_list":[{"nick_name":"咸咖啡","sex":"1","url":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eprJeCDDj1BzR9EkiczIdZ6DkyJYMYpSojXezNTwSnXzuibRafy0MJVFK3zSic4j0fwBAjIF5yCHfh5Q/132","user_id":39}],"sign_up_no":1,"startDateSort":1559685600000,"start_date":"2019-06-04 18:00:00","status":"1","total_no":4},{"activity_address":"政府路181弄","activity_id":105,"activity_type":"羽毛球","distance":"9.0km","group_id":3,"group_logo":"http://192.168.0.104/group1/M00/00/09/wKgAaFzbDIWAOuyLAABgoW-KJHU401.jpg","group_name":"俱乐部2","group_type":"1","headIcon":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIkso8rTkzbg5rKnATibpaavlLoQgeY6tFM7KZdlm5Vs4PzrPO5ib5uLWMsOCbvPGy6X9icY0GDxiaD0Q/132","is_safe":"0","owner_name":"灬苦中作樂ヾ","sign_up_list":[],"sign_up_no":0,"startDateSort":1559674800000,"start_date":"2019-06-04 15:00:00","status":"1","total_no":4}]
     * msg : 操作成功
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * activity_address : 国定东路
         * activity_id : 104
         * activity_type : 羽毛球
         * distance : 7.8km
         * group_id : 2
         * group_logo : http://192.168.0.104/group1/M00/00/09/wKgAaFza4--AWS_wAADFustiZb4810.jpg
         * group_name : 垂直网络运动群
         * group_type : 1
         * headIcon : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
         * is_safe : 0
         * owner_name : llife
         * sign_up_list : []
         * sign_up_no : 0
         * startDateSort : 1559663580000
         * start_date : 2019-06-04 11:53:00
         * status : 3
         * total_no : 4
         */

        private String activity_address;
        private int activity_id;
        private String activity_type;
        private String distance;
        private int group_id;
        private String group_logo;
        private String group_name;
        private String group_type;
        private String headIcon;
        private String is_safe;
        private String owner_name;
        private int sign_up_no;
        private long startDateSort;
        private String start_date;
        private String status;
        private int total_no;
        private String isPk;
 private  String sport_type;

        public String getSport_type() {
            return sport_type;
        }

        public void setSport_type(String sport_type) {
            this.sport_type = sport_type;
        }

        public String getIsPk() {
            return isPk;
        }

        public void setIsPk(String isPk) {
            this.isPk = isPk;
        }

        private List<ActivityDeatilBean.DataBean.SignUpListBean> sign_up_list;

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

        public String getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(String activity_type) {
            this.activity_type = activity_type;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public String getHeadIcon() {
            return headIcon;
        }

        public void setHeadIcon(String headIcon) {
            this.headIcon = headIcon;
        }

        public String getIs_safe() {
            return is_safe;
        }

        public void setIs_safe(String is_safe) {
            this.is_safe = is_safe;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public int getSign_up_no() {
            return sign_up_no;
        }

        public void setSign_up_no(int sign_up_no) {
            this.sign_up_no = sign_up_no;
        }

        public long getStartDateSort() {
            return startDateSort;
        }

        public void setStartDateSort(long startDateSort) {
            this.startDateSort = startDateSort;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotal_no() {
            return total_no;
        }

        public void setTotal_no(int total_no) {
            this.total_no = total_no;
        }

        public List<ActivityDeatilBean.DataBean.SignUpListBean> getSign_up_list() {
            return sign_up_list;
        }

        public void setSign_up_list(List<ActivityDeatilBean.DataBean.SignUpListBean> sign_up_list) {
            this.sign_up_list = sign_up_list;
        }
    }
}
