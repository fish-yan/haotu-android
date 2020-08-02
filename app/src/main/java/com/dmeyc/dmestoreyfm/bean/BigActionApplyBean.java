package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class BigActionApplyBean implements Serializable {

    /**
     * code : 200
     * data : [{"activityGovernmentId":1,"createTime":1569809099000,"groupLogo":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png","groupName":"测试羽毛球","groupSignupId":35,"id":18,"status":"1","userId":4}]
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
         * activityGovernmentId : 1
         * createTime : 1569809099000
         * groupLogo : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1ThzSAA4s9AAAsB0k_Pwk100.png
         * groupName : 测试羽毛球
         * groupSignupId : 35
         * id : 18
         * status : 1
         * userId : 4
         */

        private int activityGovernmentId;
        private long createTime;
        private String groupLogo;
        private String groupName;
        private int groupSignupId;
        private int id;
        private String status;
        private int userId;

        public int getActivityGovernmentId() {
            return activityGovernmentId;
        }

        public void setActivityGovernmentId(int activityGovernmentId) {
            this.activityGovernmentId = activityGovernmentId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getGroupLogo() {
            return groupLogo;
        }

        public void setGroupLogo(String groupLogo) {
            this.groupLogo = groupLogo;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getGroupSignupId() {
            return groupSignupId;
        }

        public void setGroupSignupId(int groupSignupId) {
            this.groupSignupId = groupSignupId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
