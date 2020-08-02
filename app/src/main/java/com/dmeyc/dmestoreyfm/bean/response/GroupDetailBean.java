package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * create by cxg on 2019/12/16
 */
public class GroupDetailBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"group_id":"142","group_name":"万象俱乐部","group_logo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLibCduzxW1UkvAxRauehicKtF0oCNC4icDQhozbkIogdkealvuBic5pASKY1kbF6FuCvzjuyGgqwGiaGg/132","province":"","city":"","area":"","activity_venue_address":"","notice":"","remark":"简介","isExamine":"0","phoneNo":"13627275116","groupType":"1","username":"Hotu5116","battleNumber":"","remarkImage":"","educateImg":"","educateBackground":"","qrCode":"http://101.44.2.178/group1/M00/00/24/wKgAaF3lL_CAaopSAABQvxYDJgw079.jpg","userId":"492"}
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
         * group_id : 142
         * group_name : 万象俱乐部
         * group_logo : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLibCduzxW1UkvAxRauehicKtF0oCNC4icDQhozbkIogdkealvuBic5pASKY1kbF6FuCvzjuyGgqwGiaGg/132
         * province :
         * city :
         * area :
         * activity_venue_address :
         * notice :
         * remark : 简介
         * isExamine : 0
         * phoneNo : 13627275116
         * groupType : 1
         * username : Hotu5116
         * battleNumber :
         * remarkImage :
         * educateImg :
         * educateBackground :
         * qrCode : http://101.44.2.178/group1/M00/00/24/wKgAaF3lL_CAaopSAABQvxYDJgw079.jpg
         * userId : 492
         */

        private String group_id;
        private String group_name;
        private String group_logo;
        private String province;
        private String city;
        private String area;
        private String activity_venue_address;
        private String notice;
        private String remark;
        private String isExamine;
        private String phoneNo;
        private String groupType;
        private String username;
        private String battleNumber;
        private String remarkImage;
        private String educateImg;
        private String educateBackground;
        private String qrCode;
        private String userId;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_logo() {
            return group_logo;
        }

        public void setGroup_logo(String group_logo) {
            this.group_logo = group_logo;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getActivity_venue_address() {
            return activity_venue_address;
        }

        public void setActivity_venue_address(String activity_venue_address) {
            this.activity_venue_address = activity_venue_address;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIsExamine() {
            return isExamine;
        }

        public void setIsExamine(String isExamine) {
            this.isExamine = isExamine;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBattleNumber() {
            return battleNumber;
        }

        public void setBattleNumber(String battleNumber) {
            this.battleNumber = battleNumber;
        }

        public String getRemarkImage() {
            return remarkImage;
        }

        public void setRemarkImage(String remarkImage) {
            this.remarkImage = remarkImage;
        }

        public String getEducateImg() {
            return educateImg;
        }

        public void setEducateImg(String educateImg) {
            this.educateImg = educateImg;
        }

        public String getEducateBackground() {
            return educateBackground;
        }

        public void setEducateBackground(String educateBackground) {
            this.educateBackground = educateBackground;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
