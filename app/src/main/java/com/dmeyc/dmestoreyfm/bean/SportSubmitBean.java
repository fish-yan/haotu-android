package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class SportSubmitBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"item_list":[{"id":1,"code":"1001","name":"羽毛球","type":"1","unit":"桶","specification":"12支装","referencePrice":90,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","createTime":1556618576000,"modifyTime":1556618576000,"remark":"123","projectType":null,"brand":"耐克","size":null,"shopNum":8,"activity_measure_id":57},{"id":2,"code":"1001","name":"羽毛球拍","type":"1","unit":"支","specification":"1支装","referencePrice":200,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","createTime":1556618669000,"modifyTime":1556618669000,"remark":"123","projectType":null,"brand":"李宁","size":null,"shopNum":5,"activity_measure_id":57},{"id":3,"code":"2001","name":"海报","type":"2","unit":"张","specification":"一张装","referencePrice":300,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzIkf2ANA6xAAGKISFBQr4481.jpg","createTime":1556618881000,"modifyTime":1556618881000,"remark":"123","projectType":null,"brand":"李宁","size":"100cmX100cm","shopNum":2,"activity_measure_id":57}],"venueItem":{"id":4,"code":"2001","name":"场馆物料","type":"3","unit":"张","specification":"一张装","referencePrice":100,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzIkf2ANA6xAAGKISFBQr4481.jpg","createTime":1556618880000,"modifyTime":1556618880000,"remark":"123","projectType":null,"brand":"李宁","size":"100cmX100cm","shopNum":1,"activity_measure_id":57},"refereeItem":{"id":5,"item_count":1,"price":13.6},"refereeCostItem":{"id":6,"item_count":2,"price":12.8},"platformServiceItem":{"id":7,"item_count":1,"price":2000},"teamItem":{"id":8,"item_count":1,"price":500},"insuranceItem":{"id":9,"item_count":1,"price":3},"isRestoreShop":"0","id":57,"startTime":"2019-05-08","address":"内蒙古包头市东河区人民广场"}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * item_list : [{"id":1,"code":"1001","name":"羽毛球","type":"1","unit":"桶","specification":"12支装","referencePrice":90,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","createTime":1556618576000,"modifyTime":1556618576000,"remark":"123","projectType":null,"brand":"耐克","size":null,"shopNum":8,"activity_measure_id":57},{"id":2,"code":"1001","name":"羽毛球拍","type":"1","unit":"支","specification":"1支装","referencePrice":200,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg","createTime":1556618669000,"modifyTime":1556618669000,"remark":"123","projectType":null,"brand":"李宁","size":null,"shopNum":5,"activity_measure_id":57},{"id":3,"code":"2001","name":"海报","type":"2","unit":"张","specification":"一张装","referencePrice":300,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzIkf2ANA6xAAGKISFBQr4481.jpg","createTime":1556618881000,"modifyTime":1556618881000,"remark":"123","projectType":null,"brand":"李宁","size":"100cmX100cm","shopNum":2,"activity_measure_id":57}]
         * venueItem : {"id":4,"code":"2001","name":"场馆物料","type":"3","unit":"张","specification":"一张装","referencePrice":100,"referenceImage":"http://192.168.0.104/group1/M00/00/04/wKgAaFzIkf2ANA6xAAGKISFBQr4481.jpg","createTime":1556618880000,"modifyTime":1556618880000,"remark":"123","projectType":null,"brand":"李宁","size":"100cmX100cm","shopNum":1,"activity_measure_id":57}
         * refereeItem : {"id":5,"item_count":1,"price":13.6}
         * refereeCostItem : {"id":6,"item_count":2,"price":12.8}
         * platformServiceItem : {"id":7,"item_count":1,"price":2000}
         * teamItem : {"id":8,"item_count":1,"price":500}
         * insuranceItem : {"id":9,"item_count":1,"price":3}
         * isRestoreShop : 0
         * id : 57
         * startTime : 2019-05-08
         * address : 内蒙古包头市东河区人民广场
         */

        private VenueItemBean venueItem;
        private RefereeItemBean refereeItem;
        private RefereeCostItemBean refereeCostItem;
        private PlatformServiceItemBean platformServiceItem;
        private TeamItemBean teamItem;
        private InsuranceItemBean insuranceItem;
        private String isRestoreShop;
        private int id;
        private String startTime;
        private String address;
        private List<ItemListBean> item_list;

        public VenueItemBean getVenueItem() {
            return venueItem;
        }

        public void setVenueItem(VenueItemBean venueItem) {
            this.venueItem = venueItem;
        }

        public RefereeItemBean getRefereeItem() {
            return refereeItem;
        }

        public void setRefereeItem(RefereeItemBean refereeItem) {
            this.refereeItem = refereeItem;
        }

        public RefereeCostItemBean getRefereeCostItem() {
            return refereeCostItem;
        }

        public void setRefereeCostItem(RefereeCostItemBean refereeCostItem) {
            this.refereeCostItem = refereeCostItem;
        }

        public PlatformServiceItemBean getPlatformServiceItem() {
            return platformServiceItem;
        }

        public void setPlatformServiceItem(PlatformServiceItemBean platformServiceItem) {
            this.platformServiceItem = platformServiceItem;
        }

        public TeamItemBean getTeamItem() {
            return teamItem;
        }

        public void setTeamItem(TeamItemBean teamItem) {
            this.teamItem = teamItem;
        }

        public InsuranceItemBean getInsuranceItem() {
            return insuranceItem;
        }

        public void setInsuranceItem(InsuranceItemBean insuranceItem) {
            this.insuranceItem = insuranceItem;
        }

        public String getIsRestoreShop() {
            return isRestoreShop;
        }

        public void setIsRestoreShop(String isRestoreShop) {
            this.isRestoreShop = isRestoreShop;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<ItemListBean> getItem_list() {
            return item_list;
        }

        public void setItem_list(List<ItemListBean> item_list) {
            this.item_list = item_list;
        }

        public static class VenueItemBean {
            /**
             * id : 4
             * code : 2001
             * name : 场馆物料
             * type : 3
             * unit : 张
             * specification : 一张装
             * referencePrice : 100
             * referenceImage : http://192.168.0.104/group1/M00/00/04/wKgAaFzIkf2ANA6xAAGKISFBQr4481.jpg
             * createTime : 1556618880000
             * modifyTime : 1556618880000
             * remark : 123
             * projectType : null
             * brand : 李宁
             * size : 100cmX100cm
             * shopNum : 1
             * activity_measure_id : 57
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
            private Object projectType;
            private String brand;
            private String size;
            private int shopNum;
            private int activity_measure_id;

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

            public Object getProjectType() {
                return projectType;
            }

            public void setProjectType(Object projectType) {
                this.projectType = projectType;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public int getShopNum() {
                return shopNum;
            }

            public void setShopNum(int shopNum) {
                this.shopNum = shopNum;
            }

            public int getActivity_measure_id() {
                return activity_measure_id;
            }

            public void setActivity_measure_id(int activity_measure_id) {
                this.activity_measure_id = activity_measure_id;
            }
        }

        public static class RefereeItemBean {
            /**
             * id : 5
             * item_count : 1
             * price : 13.6
             */

            private int id;
            private int item_count;
            private double price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_count() {
                return item_count;
            }

            public void setItem_count(int item_count) {
                this.item_count = item_count;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }

        public static class RefereeCostItemBean {
            /**
             * id : 6
             * item_count : 2
             * price : 12.8
             */

            private int id;
            private int item_count;
            private double price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_count() {
                return item_count;
            }

            public void setItem_count(int item_count) {
                this.item_count = item_count;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }

        public static class PlatformServiceItemBean {
            /**
             * id : 7
             * item_count : 1
             * price : 2000
             */

            private int id;
            private int item_count;
            private int price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_count() {
                return item_count;
            }

            public void setItem_count(int item_count) {
                this.item_count = item_count;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }

        public static class TeamItemBean {
            /**
             * id : 8
             * item_count : 1
             * price : 500
             */

            private int id;
            private int item_count;
            private int price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_count() {
                return item_count;
            }

            public void setItem_count(int item_count) {
                this.item_count = item_count;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }

        public static class InsuranceItemBean {
            /**
             * id : 9
             * item_count : 1
             * price : 3
             */

            private int id;
            private int item_count;
            private int price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_count() {
                return item_count;
            }

            public void setItem_count(int item_count) {
                this.item_count = item_count;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }

        public static class ItemListBean {
            /**
             * id : 1
             * code : 1001
             * name : 羽毛球
             * type : 1
             * unit : 桶
             * specification : 12支装
             * referencePrice : 90
             * referenceImage : http://192.168.0.104/group1/M00/00/04/wKgAaFzHYu2AFpkkAAGJIpdXiVo739.jpg
             * createTime : 1556618576000
             * modifyTime : 1556618576000
             * remark : 123
             * projectType : null
             * brand : 耐克
             * size : null
             * shopNum : 8
             * activity_measure_id : 57
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
            private Object projectType;
            private String brand;
            private Object size;
            private int shopNum;
            private int activity_measure_id;

//            private String startTime;
//
//            public String getStartTime() {
//                return startTime;
//            }
//
//            public void setStartTime(String startTime) {
//                this.startTime = startTime;
//            }

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

            public Object getProjectType() {
                return projectType;
            }

            public void setProjectType(Object projectType) {
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

            public int getShopNum() {
                return shopNum;
            }

            public void setShopNum(int shopNum) {
                this.shopNum = shopNum;
            }

            public int getActivity_measure_id() {
                return activity_measure_id;
            }

            public void setActivity_measure_id(int activity_measure_id) {
                this.activity_measure_id = activity_measure_id;
            }
        }
    }
}
