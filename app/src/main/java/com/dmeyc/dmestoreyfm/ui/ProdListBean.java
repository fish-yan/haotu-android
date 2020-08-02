package com.dmeyc.dmestoreyfm.ui;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;
import java.util.List;

public class ProdListBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"activityId":18,"date":1562666400000,"endDate":"2019-07-10","groupId":9,"groupName":"聂聂普通群","id":25,"isRealName":"0","name":"彭阳","safeAcount":3,"startDate":"2019-07-09","status":"3","type":"1","userId":11},{"activityId":19,"date":1562666400000,"endDate":"2019-07-10","groupId":9,"groupName":"聂聂普通群","id":26,"isRealName":"0","name":"彭阳","safeAcount":3,"startDate":"2019-07-09","status":"3","type":"1","userId":11},{"activityId":52,"date":1562666400000,"endDate":"2019-07-10","groupId":20,"groupName":"风云羽毛球俱乐部","id":34,"isRealName":"0","name":"彭阳","safeAcount":3,"startDate":"2019-07-09","status":"3","type":"2","userId":11},{"activityId":53,"date":1562666400000,"endDate":"2019-07-10","groupId":20,"groupName":"风云羽毛球俱乐部","id":37,"isRealName":"0","name":"彭阳","safeAcount":3,"startDate":"2019-07-09","status":"3","type":"2","userId":11},{"activityId":55,"date":1562666400000,"endDate":"2019-07-10","groupId":15,"groupName":"彭阳测试俱乐部","id":39,"isRealName":"0","name":"彭阳","safeAcount":3,"startDate":"2019-07-09","status":"3","type":"2","userId":11}]
     * msg : 操作成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * activityId : 18
         * date : 1562666400000
         * endDate : 2019-07-10
         * groupId : 9
         * groupName : 聂聂普通群
         * id : 25
         * isRealName : 0
         * name : 彭阳
         * safeAcount : 3
         * startDate : 2019-07-09
         * status : 3
         * type : 1
         * userId : 11
         */

        private int activityId;
        private long date;
        private String endDate;
        private int groupId;
        private String groupName;
        private int id;
        private String isRealName;
        private String name;
        private int safeAcount;
        private String startDate;
        private String status;
        private String type;
        private int userId;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsRealName() {
            return isRealName;
        }

        public void setIsRealName(String isRealName) {
            this.isRealName = isRealName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSafeAcount() {
            return safeAcount;
        }

        public void setSafeAcount(int safeAcount) {
            this.safeAcount = safeAcount;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
