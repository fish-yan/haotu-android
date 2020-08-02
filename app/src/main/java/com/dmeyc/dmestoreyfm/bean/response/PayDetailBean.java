package com.dmeyc.dmestoreyfm.bean.response;

import java.util.List;

/**
 * create by cxg on 2019/12/22
 */
public class PayDetailBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"womanPrice":null,"manPice":2,"safeList":[{"id":141,"itemCode":"2","itemName":"太平保险","parentCode":"SAFE_TYPE","sortNo":2,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null,"ext":"3"}],"payList":[{"id":26,"itemCode":"1","itemName":"微信","parentCode":"PAY_METHOD","sortNo":1,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null,"ext":null}]}
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
         * womanPrice : null
         * manPice : 2
         * safeList : [{"id":141,"itemCode":"2","itemName":"太平保险","parentCode":"SAFE_TYPE","sortNo":2,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null,"ext":"3"}]
         * payList : [{"id":26,"itemCode":"1","itemName":"微信","parentCode":"PAY_METHOD","sortNo":1,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null,"ext":null}]
         */

        private String womanPrice;
        private String manPice;
        private List<SafeListBean> safeList;
        private List<PayListBean> payList;

        public String getWomanPrice() {
            return womanPrice;
        }

        public void setWomanPrice(String womanPrice) {
            this.womanPrice = womanPrice;
        }

        public String getManPice() {
            return manPice;
        }

        public void setManPice(String manPice) {
            this.manPice = manPice;
        }

        public List<SafeListBean> getSafeList() {
            return safeList;
        }

        public void setSafeList(List<SafeListBean> safeList) {
            this.safeList = safeList;
        }

        public List<PayListBean> getPayList() {
            return payList;
        }

        public void setPayList(List<PayListBean> payList) {
            this.payList = payList;
        }

        public static class SafeListBean {
            /**
             * id : 141
             * itemCode : 2
             * itemName : 太平保险
             * parentCode : SAFE_TYPE
             * sortNo : 2
             * isLeaf : null
             * isRoot : null
             * createTime : null
             * modifyTime : null
             * ext : 3
             */

            private String id;
            private String itemCode;
            private String itemName;
            private String parentCode;
            private String sortNo;
            private String ext;

            // 条目中用到的，非后台返回
            private int buyCount=0;

            public int getBuyCount() {
                return buyCount;
            }

            public void setBuyCount(int buyCount) {
                this.buyCount = buyCount;
            }

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

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }
        }

        public static class PayListBean {
            /**
             * id : 26
             * itemCode : 1
             * itemName : 微信
             * parentCode : PAY_METHOD
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
            private Object modifyTime;
            private Object ext;

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

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
            }

            public Object getExt() {
                return ext;
            }

            public void setExt(Object ext) {
                this.ext = ext;
            }
        }
    }
}
