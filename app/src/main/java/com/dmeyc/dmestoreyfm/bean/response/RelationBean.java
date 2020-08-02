package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by cxg on 2019/11/30
 * 俱乐部bean
 */
public class RelationBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"groupId":146,"groupName":"教练昵称111","groupLogo":null,"battleNumber":null,"address":null}]
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
         * groupId : 146
         * groupName : 教练昵称111
         * groupLogo : null
         * battleNumber : null
         * address : null
         */

        private String groupId;
        private String groupName;
        private Object groupLogo;
        private Object battleNumber;
        private Object address;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Object getGroupLogo() {
            return groupLogo;
        }

        public void setGroupLogo(Object groupLogo) {
            this.groupLogo = groupLogo;
        }

        public Object getBattleNumber() {
            return battleNumber;
        }

        public void setBattleNumber(Object battleNumber) {
            this.battleNumber = battleNumber;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }
    }
}
