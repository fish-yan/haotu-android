package com.dmeyc.dmestoreyfm.bean.response;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * create by cxg on 2019/12/9
 */
public class UserListBean extends BaseRespBean {
    /**
     * code : 200
     * data : [{"nickName":"Yan.X","userLogo":"http://thirdwx.qlogo.cn/mmopen/vi_32/SCBpWZw1RbZI4njD7ScaXpL9YZ1ibJD11nUibbiayibjhjiaFBeh1xZ4As97W6k3FjGTbSAciboiaYnUicNOtKsny0ENYg/132","sex":"1","phoneNo":"13627275116","userId":492,"status":null}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable, MultiItemEntity {
        /**
         * nickName : Yan.X
         * userLogo : http://thirdwx.qlogo.cn/mmopen/vi_32/SCBpWZw1RbZI4njD7ScaXpL9YZ1ibJD11nUibbiayibjhjiaFBeh1xZ4As97W6k3FjGTbSAciboiaYnUicNOtKsny0ENYg/132
         * sex : 1
         * phoneNo : 13627275116
         * userId : 492
         * status : null
         */

        private String nickName;
        private String userLogo;
        private String sex;
        private String phoneNo;
        private String status;//1：我关注的，2：粉丝，3:相互关注
        private String userId;

        //按钮文字，本地使用
        private String localRightString;

        //本地使用，是否选中
        private boolean isCheck;
        //本地使用，type类型
        private int localType;

        public int getLocalType() {
            return localType;
        }

        public void setLocalType(int localType) {
            this.localType = localType;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getLocalRightString() {
            return localRightString;
        }

        public void setLocalRightString(String localRightString) {
            this.localRightString = localRightString;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }


}
