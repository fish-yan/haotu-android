package com.dmeyc.dmestoreyfm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;

import java.util.List;

/**
 * Created by jockie on 2018/1/24
 * Email:jockie911@gmail.com
 */

public class CarListBean {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cartItems : [[{"id":41,"createDate":"2018-01-23","modifyDate":"2018-01-23","version":0,"quantity":1,"product":58,"cart":10,"productInfo":{"id":58,"createDate":null,"modifyDate":null,"version":null,"goods":1,"price":386,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg","name":"LILY","size":"","material":"","color":"","stock":0,"allocatedStock":0,"availableStock":0,"brandDesignerId":1,"brandDesigner":"AMH","freight":10,"weight":200,"type":1,"isDelivery":true,"isCustom":true,"sn":null},"customs":"19,18,14,16,24","customProductList":[{"id":14,"goods":1,"custom":6,"customName":null,"name":"内里2","image":"http://c7.gg/Bfnq","price":20},{"id":16,"goods":1,"custom":7,"customName":null,"name":"填充物b","image":"http://c7.gg/BfnS","price":20},{"id":18,"goods":1,"custom":8,"customName":null,"name":"织带2","image":"http://c7.gg/BfnY","price":20},{"id":19,"goods":1,"custom":9,"customName":null,"name":"绣花1","image":"http://c7.gg/Bfpb","price":20},{"id":24,"goods":1,"custom":10,"customName":null,"name":"贴标3","image":"http://c7.gg/Bfpj","price":20}]},{"id":40,"createDate":"2018-01-23","modifyDate":"2018-01-23","version":0,"quantity":1,"product":58,"cart":10,"productInfo":{"id":58,"createDate":null,"modifyDate":null,"version":null,"goods":1,"price":386,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg","name":"LILY","size":"","material":"","color":"","stock":0,"allocatedStock":0,"availableStock":0,"brandDesignerId":1,"brandDesigner":"AMH","freight":10,"weight":200,"type":1,"isDelivery":true,"isCustom":true,"sn":null},"customs":"24,18,14,16,21","customProductList":[{"id":14,"goods":1,"custom":6,"customName":null,"name":"内里2","image":"http://c7.gg/Bfnq","price":20},{"id":16,"goods":1,"custom":7,"customName":null,"name":"填充物b","image":"http://c7.gg/BfnS","price":20},{"id":18,"goods":1,"custom":8,"customName":null,"name":"织带2","image":"http://c7.gg/BfnY","price":20},{"id":21,"goods":1,"custom":9,"customName":null,"name":"绣花3","image":"http://c7.gg/Bfpb","price":20},{"id":24,"goods":1,"custom":10,"customName":null,"name":"贴标3","image":"http://c7.gg/Bfpj","price":20}]},{"id":39,"createDate":"2018-01-23","modifyDate":"2018-01-23","version":0,"quantity":2,"product":58,"cart":10,"productInfo":{"id":58,"createDate":null,"modifyDate":null,"version":null,"goods":1,"price":386,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg","name":"LILY","size":"","material":"","color":"","stock":0,"allocatedStock":0,"availableStock":0,"brandDesignerId":1,"brandDesigner":"AMH","freight":10,"weight":200,"type":1,"isDelivery":true,"isCustom":true,"sn":null},"customs":"23,18,13,15,19","customProductList":[{"id":13,"goods":1,"custom":6,"customName":null,"name":"内里1","image":"http://c7.gg/Bfnq","price":20},{"id":15,"goods":1,"custom":7,"customName":null,"name":"填充物a","image":"http://c7.gg/BfnQ","price":20},{"id":18,"goods":1,"custom":8,"customName":null,"name":"织带2","image":"http://c7.gg/BfnY","price":20},{"id":19,"goods":1,"custom":9,"customName":null,"name":"绣花1","image":"http://c7.gg/Bfpb","price":20},{"id":23,"goods":1,"custom":10,"customName":null,"name":"贴标2","image":"http://c7.gg/Bfpj","price":20}]},{"id":38,"createDate":"2018-01-22","modifyDate":"2018-01-22","version":0,"quantity":1,"product":58,"cart":10,"productInfo":{"id":58,"createDate":null,"modifyDate":null,"version":null,"goods":1,"price":386,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg","name":"LILY","size":"","material":"","color":"","stock":0,"allocatedStock":0,"availableStock":0,"brandDesignerId":1,"brandDesigner":"AMH","freight":10,"weight":200,"type":1,"isDelivery":true,"isCustom":true,"sn":null},"customs":"24,17,14,16,19","customProductList":[{"id":14,"goods":1,"custom":6,"customName":null,"name":"内里2","image":"http://c7.gg/Bfnq","price":20},{"id":16,"goods":1,"custom":7,"customName":null,"name":"填充物b","image":"http://c7.gg/BfnS","price":20},{"id":17,"goods":1,"custom":8,"customName":null,"name":"织带1","image":"http://c7.gg/BfnV","price":20},{"id":19,"goods":1,"custom":9,"customName":null,"name":"绣花1","image":"http://c7.gg/Bfpb","price":20},{"id":24,"goods":1,"custom":10,"customName":null,"name":"贴标3","image":"http://c7.gg/Bfpj","price":20}]}]]
         * paginator : null
         */

        private PaginatorBean paginator;
        private List<List<CartItemsBean>> cartItems;

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public List<List<CartItemsBean>> getCartItems() {
            return cartItems;
        }

        public void setCartItems(List<List<CartItemsBean>> cartItems) {
            this.cartItems = cartItems;
        }

        public static class CartItemsBean implements Parcelable {

            private int id;
            private String createDate;
            private String modifyDate;
            private int version;
            private int quantity;
            private int product;
            private int cart;
            private ProductInfoBean productInfo;
            private String customs;
            private boolean isChecked;
            private int tempTargetPostion;
            private double couponPrice;
            private List<CustomProductListBean> customProductList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getProduct() {
                return product;
            }

            public void setProduct(int product) {
                this.product = product;
            }

            public int getCart() {
                return cart;
            }

            public void setCart(int cart) {
                this.cart = cart;
            }

            public ProductInfoBean getProductInfo() {
                return productInfo;
            }

            public void setProductInfo(ProductInfoBean productInfo) {
                this.productInfo = productInfo;
            }

            public String getCustoms() {
                return customs;
            }

            public void setCustoms(String customs) {
                this.customs = customs;
            }

            public List<CustomProductListBean> getCustomProductList() {
                return customProductList;
            }

            public void setCustomProductList(List<CustomProductListBean> customProductList) {
                this.customProductList = customProductList;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public int getTempTargetPostion() {
                return tempTargetPostion;
            }

            public void setTempTargetPostion(int tempTargetPostion) {
                this.tempTargetPostion = tempTargetPostion;
            }

            public double getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(double couponPrice) {
                this.couponPrice = couponPrice;
            }

            public static class ProductInfoBean implements Parcelable {

                private int id;
                private String createDate;
                private String modifyDate;
                private String version;
                private int goods;
                private double price;
                private String image;
                private String name;
                private String size;
                private String material;
                private String color;
                private String productInfo;
                private int stock;
                private int allocatedStock;
                private int availableStock;
                private int brandDesignerId;
                private String brandDesigner;
                private int freight;
                private int weight;
                private int type;
                private boolean isDelivery;
                private boolean isCustom;
                private String sn;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public String getModifyDate() {
                    return modifyDate;
                }

                public void setModifyDate(String modifyDate) {
                    this.modifyDate = modifyDate;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public int getGoods() {
                    return goods;
                }

                public void setGoods(int goods) {
                    this.goods = goods;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getMaterial() {
                    return material;
                }

                public void setMaterial(String material) {
                    this.material = material;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public int getAllocatedStock() {
                    return allocatedStock;
                }

                public void setAllocatedStock(int allocatedStock) {
                    this.allocatedStock = allocatedStock;
                }

                public int getAvailableStock() {
                    return availableStock;
                }

                public void setAvailableStock(int availableStock) {
                    this.availableStock = availableStock;
                }

                public int getBrandDesignerId() {
                    return brandDesignerId;
                }

                public void setBrandDesignerId(int brandDesignerId) {
                    this.brandDesignerId = brandDesignerId;
                }

                public String getBrandDesigner() {
                    return brandDesigner;
                }

                public void setBrandDesigner(String brandDesigner) {
                    this.brandDesigner = brandDesigner;
                }

                public int getFreight() {
                    return freight;
                }

                public void setFreight(int freight) {
                    this.freight = freight;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public boolean isIsDelivery() {
                    return isDelivery;
                }

                public void setIsDelivery(boolean isDelivery) {
                    this.isDelivery = isDelivery;
                }

                public boolean isIsCustom() {
                    return isCustom;
                }

                public void setIsCustom(boolean isCustom) {
                    this.isCustom = isCustom;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public ProductInfoBean() {
                }

                public String getProductInfo() {
                    return productInfo;
                }

                public void setProductInfo(String productInfo) {
                    this.productInfo = productInfo;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.createDate);
                    dest.writeString(this.modifyDate);
                    dest.writeString(this.version);
                    dest.writeInt(this.goods);
                    dest.writeDouble(this.price);
                    dest.writeString(this.image);
                    dest.writeString(this.name);
                    dest.writeString(this.size);
                    dest.writeString(this.material);
                    dest.writeString(this.color);
                    dest.writeString(this.productInfo);
                    dest.writeInt(this.stock);
                    dest.writeInt(this.allocatedStock);
                    dest.writeInt(this.availableStock);
                    dest.writeInt(this.brandDesignerId);
                    dest.writeString(this.brandDesigner);
                    dest.writeInt(this.freight);
                    dest.writeInt(this.weight);
                    dest.writeInt(this.type);
                    dest.writeByte(this.isDelivery ? (byte) 1 : (byte) 0);
                    dest.writeByte(this.isCustom ? (byte) 1 : (byte) 0);
                    dest.writeString(this.sn);
                }

                protected ProductInfoBean(Parcel in) {
                    this.id = in.readInt();
                    this.createDate = in.readString();
                    this.modifyDate = in.readString();
                    this.version = in.readString();
                    this.goods = in.readInt();
                    this.price = in.readDouble();
                    this.image = in.readString();
                    this.name = in.readString();
                    this.size = in.readString();
                    this.material = in.readString();
                    this.color = in.readString();
                    this.productInfo = in.readString();
                    this.stock = in.readInt();
                    this.allocatedStock = in.readInt();
                    this.availableStock = in.readInt();
                    this.brandDesignerId = in.readInt();
                    this.brandDesigner = in.readString();
                    this.freight = in.readInt();
                    this.weight = in.readInt();
                    this.type = in.readInt();
                    this.isDelivery = in.readByte() != 0;
                    this.isCustom = in.readByte() != 0;
                    this.sn = in.readString();
                }

                public static final Creator<ProductInfoBean> CREATOR = new Creator<ProductInfoBean>() {
                    @Override
                    public ProductInfoBean createFromParcel(Parcel source) {
                        return new ProductInfoBean(source);
                    }

                    @Override
                    public ProductInfoBean[] newArray(int size) {
                        return new ProductInfoBean[size];
                    }
                };
            }

            public static class CustomProductListBean implements Parcelable {
                /**
                 * id : 14
                 * goods : 1
                 * custom : 6
                 * customName : null
                 * name : 内里2
                 * image : http://c7.gg/Bfnq
                 * price : 20.0
                 */

                private int id;
                private int goods;
                private int custom;
                private String customName;
                private String name;
                private String image;
                private double price;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGoods() {
                    return goods;
                }

                public void setGoods(int goods) {
                    this.goods = goods;
                }

                public int getCustom() {
                    return custom;
                }

                public void setCustom(int custom) {
                    this.custom = custom;
                }

                public String getCustomName() {
                    return customName;
                }

                public void setCustomName(String customName) {
                    this.customName = customName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeInt(this.goods);
                    dest.writeInt(this.custom);
                    dest.writeString(this.customName);
                    dest.writeString(this.name);
                    dest.writeString(this.image);
                    dest.writeDouble(this.price);
                }

                public CustomProductListBean() {
                }

                protected CustomProductListBean(Parcel in) {
                    this.id = in.readInt();
                    this.goods = in.readInt();
                    this.custom = in.readInt();
                    this.customName = in.readString();
                    this.name = in.readString();
                    this.image = in.readString();
                    this.price = in.readDouble();
                }

                public static final Creator<CustomProductListBean> CREATOR = new Creator<CustomProductListBean>() {
                    @Override
                    public CustomProductListBean createFromParcel(Parcel source) {
                        return new CustomProductListBean(source);
                    }

                    @Override
                    public CustomProductListBean[] newArray(int size) {
                        return new CustomProductListBean[size];
                    }
                };
            }

            public CartItemsBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.createDate);
                dest.writeString(this.modifyDate);
                dest.writeInt(this.version);
                dest.writeInt(this.quantity);
                dest.writeInt(this.product);
                dest.writeInt(this.cart);
                dest.writeParcelable(this.productInfo, flags);
                dest.writeString(this.customs);
                dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
                dest.writeInt(this.tempTargetPostion);
                dest.writeDouble(this.couponPrice);
                dest.writeTypedList(this.customProductList);
            }

            protected CartItemsBean(Parcel in) {
                this.id = in.readInt();
                this.createDate = in.readString();
                this.modifyDate = in.readString();
                this.version = in.readInt();
                this.quantity = in.readInt();
                this.product = in.readInt();
                this.cart = in.readInt();
                this.productInfo = in.readParcelable(ProductInfoBean.class.getClassLoader());
                this.customs = in.readString();
                this.isChecked = in.readByte() != 0;
                this.tempTargetPostion = in.readInt();
                this.couponPrice = in.readDouble();
                this.customProductList = in.createTypedArrayList(CustomProductListBean.CREATOR);
            }

            public static final Creator<CartItemsBean> CREATOR = new Creator<CartItemsBean>() {
                @Override
                public CartItemsBean createFromParcel(Parcel source) {
                    return new CartItemsBean(source);
                }

                @Override
                public CartItemsBean[] newArray(int size) {
                    return new CartItemsBean[size];
                }
            };
        }
    }
}
