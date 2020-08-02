package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class ClubIntroBean implements Serializable {

    /**
     * code : 200
     * data : {"activity_venue_address":"国定东路","area":"黄浦区","battleNumber":100,"city":"上海市","educateBackground":"夸土巴兔阿拉阿基拉饿极了","educateImg":["http://192.168.0.104/group1/M00/00/20/wKgAaF1j_0qALOgpAAEXPKZj57k860.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1j_0qAKrHgAACTOPLMT2c791.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1j_0qAFVPuAACQV5-4Dq8775.jpg"],"groupType":"3","group_id":30,"group_logo":"http://192.168.0.104/group1/M00/00/20/wKgAaF1gB7mANl7eAAFcJMFpPHI545.jpg","group_name":"过滤","isExamine":"0","notice":"","phoneNo":"17182701034","province":"上海市","remark":"不套路噜噜噜","remarkImage":["http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAQqfQAAGNb6ZI0kQ935.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAHVHfAAOnDCTbzzc625.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAdE1VAAJP_DqAJWk377.jpg"],"username":"咸咖啡"}
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
         * activity_venue_address : 国定东路
         * area : 黄浦区
         * battleNumber : 100
         * city : 上海市
         * educateBackground : 夸土巴兔阿拉阿基拉饿极了
         * educateImg : ["http://192.168.0.104/group1/M00/00/20/wKgAaF1j_0qALOgpAAEXPKZj57k860.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1j_0qAKrHgAACTOPLMT2c791.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1j_0qAFVPuAACQV5-4Dq8775.jpg"]
         * groupType : 3
         * group_id : 30
         * group_logo : http://192.168.0.104/group1/M00/00/20/wKgAaF1gB7mANl7eAAFcJMFpPHI545.jpg
         * group_name : 过滤
         * isExamine : 0
         * notice :
         * phoneNo : 17182701034
         * province : 上海市
         * remark : 不套路噜噜噜
         * remarkImage : ["http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAQqfQAAGNb6ZI0kQ935.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAHVHfAAOnDCTbzzc625.jpg","http://192.168.0.104/group1/M00/00/20/wKgAaF1kAUGAdE1VAAJP_DqAJWk377.jpg"]
         * username : 咸咖啡
         */

        private String activity_venue_address;
        private String area;
        private int battleNumber;
        private String city;
        private String educateBackground;
        private String groupType;
        private int group_id;
        private String group_logo;
        private String group_name;
        private String isExamine;
        private String notice;
        private String phoneNo;
        private String province;
        private String remark;
        private String username;
        private List<String> educateImg;
        private List<String> remarkImage;

        public String getActivity_venue_address() {
            return activity_venue_address;
        }

        public void setActivity_venue_address(String activity_venue_address) {
            this.activity_venue_address = activity_venue_address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getBattleNumber() {
            return battleNumber;
        }

        public void setBattleNumber(int battleNumber) {
            this.battleNumber = battleNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEducateBackground() {
            return educateBackground;
        }

        public void setEducateBackground(String educateBackground) {
            this.educateBackground = educateBackground;
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

        public String getIsExamine() {
            return isExamine;
        }

        public void setIsExamine(String isExamine) {
            this.isExamine = isExamine;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getEducateImg() {
            return educateImg;
        }

        public void setEducateImg(List<String> educateImg) {
            this.educateImg = educateImg;
        }

        public List<String> getRemarkImage() {
            return remarkImage;
        }

        public void setRemarkImage(List<String> remarkImage) {
            this.remarkImage = remarkImage;
        }
    }
}
