package com.dmeyc.dmestoreyfm.bean.response;

import java.util.List;

/**
 * create by cxg on 2019/12/29
 */
public class LivingBannerBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"id":143,"itemCode":"1","itemName":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1SNuaADJYBAAKJTrKGMF4786.png","parentCode":"LIVEBROADCAST_ROATAIN","sortNo":1,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null,"ext":null},{"id":144,"itemCode":"2","itemName":"http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1SGgqAZQmHAAG9LIBhr2k933.jpg","parentCode":"LIVEBROADCAST_ROATAIN","sortNo":2,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null,"ext":null}]
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
         * id : 143
         * itemCode : 1
         * itemName : http://47.100.223.153:8888/group1/M00/00/00/rBNsuF1SNuaADJYBAAKJTrKGMF4786.png
         * parentCode : LIVEBROADCAST_ROATAIN
         * sortNo : 1
         * isLeaf : null
         * isRoot : null
         * createTime : null
         * modifyTime : null
         * ext : null
         */

        private String id;
        private String itemCode;
        private String itemName;
        private String parentCode;
        private String sortNo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getParentCode() {
            return parentCode;
        }

        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        public String getSortNo() {
            return sortNo;
        }

        public void setSortNo(String sortNo) {
            this.sortNo = sortNo;
        }
    }
}
