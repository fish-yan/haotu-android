package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;

import java.io.Serializable;
import java.util.List;

public class ProjectTypeBean extends BaseRespBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":51,"itemCode":"1","itemName":"羽毛球","parentCode":"PROJECT_TYPE","sortNo":1,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":52,"itemCode":"2","itemName":"游泳","parentCode":"PROJECT_TYPE","sortNo":2,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":53,"itemCode":"3","itemName":"瑜伽","parentCode":"PROJECT_TYPE","sortNo":3,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":54,"itemCode":"4","itemName":"太极","parentCode":"PROJECT_TYPE","sortNo":4,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":55,"itemCode":"5","itemName":"乒乓球","parentCode":"PROJECT_TYPE","sortNo":5,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":56,"itemCode":"6","itemName":"篮球","parentCode":"PROJECT_TYPE","sortNo":6,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":57,"itemCode":"7","itemName":"足球","parentCode":"PROJECT_TYPE","sortNo":7,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":58,"itemCode":"8","itemName":"跑步","parentCode":"PROJECT_TYPE","sortNo":8,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null},{"id":59,"itemCode":"9","itemName":"户外","parentCode":"PROJECT_TYPE","sortNo":9,"isLeaf":null,"isRoot":null,"createTime":null,"modifyTime":null}]
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
         * id : 51
         * itemCode : 1
         * itemName : 羽毛球
         * parentCode : PROJECT_TYPE
         * sortNo : 1
         * isLeaf : null
         * isRoot : null
         * createTime : null
         * modifyTime : null
         */

        private int id;
        private String itemCode;
        private String itemName;
        private String parentCode;
        private int sortNo;
        private Object isLeaf;
        private Object isRoot;
        private Object createTime;
        private Object modifyTime;
        private String ext;

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getSortNo() {
            return sortNo;
        }

        public void setSortNo(int sortNo) {
            this.sortNo = sortNo;
        }

        public Object getIsLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(Object isLeaf) {
            this.isLeaf = isLeaf;
        }

        public Object getIsRoot() {
            return isRoot;
        }

        public void setIsRoot(Object isRoot) {
            this.isRoot = isRoot;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }
    }
}
