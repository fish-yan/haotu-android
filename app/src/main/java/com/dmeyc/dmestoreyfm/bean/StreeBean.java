package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class StreeBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":10849,"parentId":null,"name":"南京东路街道","type":true,"agencyId":null},{"id":10850,"parentId":null,"name":"外滩街道","type":true,"agencyId":null},{"id":10851,"parentId":null,"name":"半淞园路街道","type":true,"agencyId":null},{"id":10852,"parentId":null,"name":"小东门街道","type":true,"agencyId":null},{"id":10853,"parentId":null,"name":"豫园街道","type":true,"agencyId":null},{"id":10854,"parentId":null,"name":"老西门街道","type":true,"agencyId":null},{"id":10855,"parentId":null,"name":"五里桥街道","type":true,"agencyId":null},{"id":10856,"parentId":null,"name":"打浦桥街道","type":true,"agencyId":null},{"id":10857,"parentId":null,"name":"淮海中路街道","type":true,"agencyId":null},{"id":10858,"parentId":null,"name":"瑞金二路街道","type":true,"agencyId":null}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10849
         * parentId : null
         * name : 南京东路街道
         * type : true
         * agencyId : null
         */

        private int id;
        private Object parentId;
        private String name;
        private boolean type;
        private Object agencyId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        public Object getAgencyId() {
            return agencyId;
        }

        public void setAgencyId(Object agencyId) {
            this.agencyId = agencyId;
        }
    }
}
