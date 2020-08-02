package com.dmeyc.dmestoreyfm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jockie on 2018/3/5
 * Email:jockie911@gmail.com
 */

public class CouponBean {

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 1
         * createDate : 2017-04-05
         * modifyDate : 2017-04-17
         * version : 1
         * beginDate : 2018-01-03
         * endDate : 2018-05-01
         * introduction : 满5000可用
         * minimumPrice : 5000.0
         * name : 500元优惠券
         * price : 500.0
         */

        private int id;
        private String createDate;
        private String modifyDate;
        private int version;
        private String beginDate;
        private String endDate;
        private String introduction;
        private double minimumPrice;
        private String name;
        private double price;

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

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public double getMinimumPrice() {
            return minimumPrice;
        }

        public void setMinimumPrice(double minimumPrice) {
            this.minimumPrice = minimumPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
            dest.writeString(this.createDate);
            dest.writeString(this.modifyDate);
            dest.writeInt(this.version);
            dest.writeString(this.beginDate);
            dest.writeString(this.endDate);
            dest.writeString(this.introduction);
            dest.writeDouble(this.minimumPrice);
            dest.writeString(this.name);
            dest.writeDouble(this.price);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.createDate = in.readString();
            this.modifyDate = in.readString();
            this.version = in.readInt();
            this.beginDate = in.readString();
            this.endDate = in.readString();
            this.introduction = in.readString();
            this.minimumPrice = in.readDouble();
            this.name = in.readString();
            this.price = in.readDouble();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
