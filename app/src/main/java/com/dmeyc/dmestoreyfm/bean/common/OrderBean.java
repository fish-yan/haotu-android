package com.dmeyc.dmestoreyfm.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by jockie on 2018/2/8
 * Email:jockie911@gmail.com
 */

public class OrderBean implements Parcelable {

    public String getMeasureId() {
        return measureId;
    }

    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    public BigDecimal getCustomDetailPrice() {
        return customDetailPrice;
    }

    public void setCustomDetailPrice(BigDecimal customDetailPrice) {
        this.customDetailPrice = customDetailPrice;
    }

    public Integer getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Integer couponCode) {
        this.couponCode = couponCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLockExpire() {
        return lockExpire;
    }

    public void setLockExpire(String lockExpire) {
        this.lockExpire = lockExpire;
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public String getRefundDeliveryCode() {
        return refundDeliveryCode;
    }

    public void setRefundDeliveryCode(String refundDeliveryCode) {
        this.refundDeliveryCode = refundDeliveryCode;
    }

    public String getRefundDeliveryNumber() {
        return refundDeliveryNumber;
    }

    public void setRefundDeliveryNumber(String refundDeliveryNumber) {
        this.refundDeliveryNumber = refundDeliveryNumber;
    }

    public String getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(String receivingTime) {
        this.receivingTime = receivingTime;
    }

    public String getAgreeTimeRefundTime() {
        return agreeTimeRefundTime;
    }

    public void setAgreeTimeRefundTime(String agreeTimeRefundTime) {
        this.agreeTimeRefundTime = agreeTimeRefundTime;
    }

    public String getAgreeConfirmTimeRefundTime() {
        return agreeConfirmTimeRefundTime;
    }

    public void setAgreeConfirmTimeRefundTime(String agreeConfirmTimeRefundTime) {
        this.agreeConfirmTimeRefundTime = agreeConfirmTimeRefundTime;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public boolean isExtend() {
        return isExtend;
    }

    public void setExtend(boolean extend) {
        isExtend = extend;
    }

    /** 量身Id*/
    private String measureId;
    /** 商品定制类别明细价格*/
    private BigDecimal customDetailPrice;
    /** 优惠券*/
    private Integer couponCode;
    /** 附言 */
    private String memo;
    private String lockExpire;

    private String lockKey;

    /** 退货快递编码和单号 */
    private String refundDeliveryCode;
    private String refundDeliveryNumber;
    private String  receivingTime;

    /** 买家申请退款卖家最晚确认时间 */
    private String  agreeTimeRefundTime;
    /** 买家寄出商品卖家最晚确认时间 */
    private String  agreeConfirmTimeRefundTime;
    private int id;
    private String createDate;
    private String modifyDate;
    private String payTime;
    private Object refundTime;
    private Object refundSendDate;
    private String automaticReceivingDate;
    private int version;
    private int type;
    private int orderStatus;
    private int orderItemStatus;
    private int orderItemId;
    private int product;
    private int goods;
    private String thumbnail;
    private int amount;
    private int orderRefundCode;
    private int orderItemRefundCode;
    private double realAmount;
    private int refundAmountOrder;
    private int refundQuantityOrder;
    private int refundQuantityOrderItem;
    private String name;
    private String size;
    private String material;
    private String color;
    private String customNames;
    private double price;
    private int discountAmount;
    private int freight;
    private int quantity;
    private int totalQuantity;
    private boolean isCustom;
    private int brandDesignerId;
    private String brandDesigner;
    private String sn;
    private String expire;
    private String areaName;
    private String address;
    private String phone;
    private String receiverPeople;
    private String completeDate;
    private String timesTamp;
    private String tradeNo;
    private String paymentMethodName;
    private String paymentMethodType;
    private int member;
    private String deliveryCode;
    private String deliveryNumber;
    private String deliveryTime;
    private boolean isExtend;

    private int store;
    private int amountItem;
    private Object customNamesIds;
    private Object cancelOrder;
    private Object refundAmountOrderItem;
    private Object refundPeople;
    private Object refundPhone;
    private Object refundReason;
    private Object refundSn;
    private String deliveryTimeLimit;
    private String deliveryTimeExtend;
    private int invoice;



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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getAutomaticReceivingDate() {
        return automaticReceivingDate;
    }

    public void setAutomaticReceivingDate(String automaticReceivingDate) {
        this.automaticReceivingDate = automaticReceivingDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderItemStatus() {
        return orderItemStatus;
    }

    public void setOrderItemStatus(int orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderRefundCode() {
        return orderRefundCode;
    }

    public void setOrderRefundCode(int orderRefundCode) {
        this.orderRefundCode = orderRefundCode;
    }

    public int getOrderItemRefundCode() {
        return orderItemRefundCode;
    }

    public void setOrderItemRefundCode(int orderItemRefundCode) {
        this.orderItemRefundCode = orderItemRefundCode;
    }

    public double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(double realAmount) {
        this.realAmount = realAmount;
    }

    public int getRefundAmountOrder() {
        return refundAmountOrder;
    }

    public void setRefundAmountOrder(int refundAmountOrder) {
        this.refundAmountOrder = refundAmountOrder;
    }

    public int getRefundQuantityOrder() {
        return refundQuantityOrder;
    }

    public void setRefundQuantityOrder(int refundQuantityOrder) {
        this.refundQuantityOrder = refundQuantityOrder;
    }

    public int getRefundQuantityOrderItem() {
        return refundQuantityOrderItem;
    }

    public void setRefundQuantityOrderItem(int refundQuantityOrderItem) {
        this.refundQuantityOrderItem = refundQuantityOrderItem;
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

    public String getCustomNames() {
        return customNames;
    }

    public void setCustomNames(String customNames) {
        this.customNames = customNames;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIsCustom() {
        return isCustom;
    }

    public void setIsCustom(boolean isCustom) {
        this.isCustom = isCustom;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiverPeople() {
        return receiverPeople;
    }

    public void setReceiverPeople(String receiverPeople) {
        this.receiverPeople = receiverPeople;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isIsExtend() {
        return isExtend;
    }

    public void setIsExtend(boolean isExtend) {
        this.isExtend = isExtend;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.measureId);
        dest.writeSerializable(this.customDetailPrice);
        dest.writeValue(this.couponCode);
        dest.writeString(this.memo);
        dest.writeString(this.lockExpire);
        dest.writeString(this.lockKey);
        dest.writeString(this.refundDeliveryCode);
        dest.writeString(this.refundDeliveryNumber);
        dest.writeString(this.receivingTime);
        dest.writeString(this.agreeTimeRefundTime);
        dest.writeString(this.agreeConfirmTimeRefundTime);
        dest.writeInt(this.id);
        dest.writeString(this.createDate);
        dest.writeString(this.modifyDate);
        dest.writeString(this.payTime);
        dest.writeString(this.automaticReceivingDate);
        dest.writeInt(this.version);
        dest.writeInt(this.type);
        dest.writeInt(this.orderStatus);
        dest.writeInt(this.orderItemStatus);
        dest.writeInt(this.orderItemId);
        dest.writeInt(this.product);
        dest.writeInt(this.goods);
        dest.writeString(this.thumbnail);
        dest.writeInt(this.amount);
        dest.writeInt(this.orderRefundCode);
        dest.writeInt(this.orderItemRefundCode);
        dest.writeDouble(this.realAmount);
        dest.writeInt(this.refundAmountOrder);
        dest.writeInt(this.refundQuantityOrder);
        dest.writeInt(this.refundQuantityOrderItem);
        dest.writeString(this.name);
        dest.writeString(this.size);
        dest.writeString(this.material);
        dest.writeString(this.color);
        dest.writeString(this.customNames);
        dest.writeDouble(this.price);
        dest.writeInt(this.discountAmount);
        dest.writeInt(this.freight);
        dest.writeInt(this.quantity);
        dest.writeInt(this.totalQuantity);
        dest.writeByte(this.isCustom ? (byte) 1 : (byte) 0);
        dest.writeInt(this.brandDesignerId);
        dest.writeString(this.brandDesigner);
        dest.writeString(this.sn);
        dest.writeString(this.expire);
        dest.writeString(this.areaName);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.receiverPeople);
        dest.writeString(this.completeDate);
        dest.writeString(this.timesTamp);
        dest.writeString(this.tradeNo);
        dest.writeString(this.paymentMethodName);
        dest.writeString(this.paymentMethodType);
        dest.writeInt(this.member);
        dest.writeString(this.deliveryCode);
        dest.writeString(this.deliveryNumber);
        dest.writeString(this.deliveryTime);
        dest.writeByte(this.isExtend ? (byte) 1 : (byte) 0);
    }

    public OrderBean() {
    }

    protected OrderBean(Parcel in) {
        this.measureId = in.readString();
        this.customDetailPrice = (BigDecimal) in.readSerializable();
        this.couponCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.memo = in.readString();
        this.lockExpire = in.readString();
        this.lockKey = in.readString();
        this.refundDeliveryCode = in.readString();
        this.refundDeliveryNumber = in.readString();
        long tmpReceivingTime = in.readLong();
        this.receivingTime = in.readString();
        this.agreeTimeRefundTime = in.readString();
        this.agreeConfirmTimeRefundTime = in.readString();
        this.id = in.readInt();
        this.createDate = in.readString();
        this.modifyDate = in.readString();
        this.payTime = in.readString();
        this.automaticReceivingDate = in.readString();
        this.version = in.readInt();
        this.type = in.readInt();
        this.orderStatus = in.readInt();
        this.orderItemStatus = in.readInt();
        this.orderItemId = in.readInt();
        this.product = in.readInt();
        this.goods = in.readInt();
        this.thumbnail = in.readString();
        this.amount = in.readInt();
        this.orderRefundCode = in.readInt();
        this.orderItemRefundCode = in.readInt();
        this.realAmount = in.readInt();
        this.refundAmountOrder = in.readInt();
        this.refundQuantityOrder = in.readInt();
        this.refundQuantityOrderItem = in.readInt();
        this.name = in.readString();
        this.size = in.readString();
        this.material = in.readString();
        this.color = in.readString();
        this.customNames = in.readString();
        this.price = in.readInt();
        this.discountAmount = in.readInt();
        this.freight = in.readInt();
        this.quantity = in.readInt();
        this.totalQuantity = in.readInt();
        this.isCustom = in.readByte() != 0;
        this.brandDesignerId = in.readInt();
        this.brandDesigner = in.readString();
        this.sn = in.readString();
        this.expire = in.readString();
        this.areaName = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.receiverPeople = in.readString();
        this.completeDate = in.readString();
        this.timesTamp = in.readString();
        this.tradeNo = in.readString();
        this.paymentMethodName = in.readString();
        this.paymentMethodType = in.readString();
        this.member = in.readInt();
        this.deliveryCode = in.readString();
        this.deliveryNumber = in.readString();
        this.deliveryTime = in.readString();
        this.isExtend = in.readByte() != 0;
    }

    public static final Parcelable.Creator<OrderBean> CREATOR = new Parcelable.Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel source) {
            return new OrderBean(source);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };
}
