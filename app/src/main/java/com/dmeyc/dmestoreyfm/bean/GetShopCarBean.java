package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class GetShopCarBean implements Serializable {

    /**
     * code : 200
     * data : [{"brand":"耐克","code":"1001","createTime":1556661776000,"id":1,"modifyTime":1556661776000,"name":"羽毛球","projectType":"1","referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","referencePrice":90,"remark":"123","shopNum":1,"specification":"12支装","type":"1","unit":"桶"},{"brand":"李宁","code":"1001","createTime":1556661869000,"id":2,"modifyTime":1556661869000,"name":"羽毛球拍","projectType":"1","referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","referencePrice":200,"remark":"123","shopNum":1,"specification":"1支装","type":"1","unit":"支"}]
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
         * brand : 耐克
         * code : 1001
         * createTime : 1556661776000
         * id : 1
         * modifyTime : 1556661776000
         * name : 羽毛球
         * projectType : 1
         * referenceImage : http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg
         * referencePrice : 90
         * remark : 123
         * shopNum : 1
         * specification : 12支装
         * type : 1
         * unit : 桶
         */

        private String brand;
        private String code;
        private long createTime;
        private int id;
        private long modifyTime;
        private String name;
        private String projectType;
        private String referenceImage;
        private int referencePrice;
        private String remark;
        private int shopNum;
        private String specification;
        private String type;
        private String unit;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getReferenceImage() {
            return referenceImage;
        }

        public void setReferenceImage(String referenceImage) {
            this.referenceImage = referenceImage;
        }

        public int getReferencePrice() {
            return referencePrice;
        }

        public void setReferencePrice(int referencePrice) {
            this.referencePrice = referencePrice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getShopNum() {
            return shopNum;
        }

        public void setShopNum(int shopNum) {
            this.shopNum = shopNum;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
