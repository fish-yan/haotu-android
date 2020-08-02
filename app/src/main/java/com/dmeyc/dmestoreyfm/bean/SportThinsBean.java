package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SportThinsBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":1,"code":"1001","name":"羽毛球","type":"1","unit":"桶","specification":"12支装","referencePrice":90,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","createTime":1556661776000,"modifyTime":1556661776000,"remark":"123","projectType":"1","brand":"耐克","size":null},{"id":2,"code":"1001","name":"羽毛球拍","type":"1","unit":"支","specification":"1支装","referencePrice":200,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","createTime":1556661869000,"modifyTime":1556661869000,"remark":"123","projectType":"1","brand":"李宁","size":null},{"id":3,"code":"2001","name":"海报","type":"2","unit":"张","specification":"一张装","referencePrice":300,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzIkf2ANA6xAAGKISFBQr4481.jpg","createTime":1556662081000,"modifyTime":1556662081000,"remark":"123","projectType":"2","brand":"李宁","size":"100cmX100cm"}]
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
         * id : 1
         * code : 1001
         * name : 羽毛球
         * type : 1
         * unit : 桶
         * specification : 12支装
         * referencePrice : 90
         * referenceImage : http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg
         * createTime : 1556661776000
         * modifyTime : 1556661776000
         * remark : 123
         * projectType : 1
         * brand : 耐克
         * size : null
         */

        private int id;
        private String code;
        private String name;
        private String type;
        private String unit;
        private String specification;
        private int referencePrice;
        private String referenceImage;
        private long createTime;
        private long modifyTime;
        private String remark;
        private String projectType;
        private String brand;
        private Object size;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public int getReferencePrice() {
            return referencePrice;
        }

        public void setReferencePrice(int referencePrice) {
            this.referencePrice = referencePrice;
        }

        public String getReferenceImage() {
            return referenceImage;
        }

        public void setReferenceImage(String referenceImage) {
            this.referenceImage = referenceImage;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getSize() {
            return size;
        }

        public void setSize(Object size) {
            this.size = size;
        }
    }
}
