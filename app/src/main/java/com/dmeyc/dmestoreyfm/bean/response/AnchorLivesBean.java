package com.dmeyc.dmestoreyfm.bean.response;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * create by cxg on 2019/12/26
 */
public class AnchorLivesBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"sportsPoster":"http://101.44.2.178/group1/M00/00/25/wKgAaF32gOWAF-YOAAALv6s6TJk017.png","address":"上海","startTime":"2019-12-15 14:00:00","activityName":"测试","activityId":569,"userId":"493","seeCount":null},{"sportsPoster":"http://101.44.2.178/group1/M00/00/25/wKgAaF32kIKANg2rAAAD4QO-HXc128.png","address":"上海金山","startTime":"2019-12-15 15:00:00","activityName":"玩耍","activityId":571,"userId":"493","seeCount":0}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * sportsPoster : http://101.44.2.178/group1/M00/00/25/wKgAaF32gOWAF-YOAAALv6s6TJk017.png
         * address : 上海
         * startTime : 2019-12-15 14:00:00
         * activityName : 测试
         * activityId : 569
         * userId : 493
         * seeCount : null
         */

        private String sportsPoster;
        private String address;
        private String startTime;
        private String activityName;
        private String activityId;
        private String userId;
        private int seeCount;

        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }

        public String getSportsPoster() {
            return sportsPoster;
        }

        public void setSportsPoster(String sportsPoster) {
            this.sportsPoster = sportsPoster;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        private int itemType;

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
