package com.dmeyc.dmestoreyfm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class AddressBean {

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
        private List<ReceiverBean> receiver;

        public List<ReceiverBean> getReceiver() {
            return receiver;
        }

        public void setReceiver(List<ReceiverBean> receiver) {
            this.receiver = receiver;
        }

        public static class ReceiverBean implements Parcelable {

            private int id;
            private String createDate;
            private String modifyDate;
            private int version;
            private int member;
            private boolean isDefault;
            private String area;
            private String address;
            private String mobile;
            private String receiverPeople;

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

            public int getMember() {
                return member;
            }

            public void setMember(int member) {
                this.member = member;
            }

            public boolean isIsDefault() {
                return isDefault;
            }

            public void setIsDefault(boolean isDefault) {
                this.isDefault = isDefault;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getReceiverPeople() {
                return receiverPeople;
            }

            public void setReceiverPeople(String receiverPeople) {
                this.receiverPeople = receiverPeople;
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
                dest.writeInt(this.member);
                dest.writeByte(this.isDefault ? (byte) 1 : (byte) 0);
                dest.writeString(this.area);
                dest.writeString(this.address);
                dest.writeString(this.mobile);
                dest.writeString(this.receiverPeople);
            }

            public ReceiverBean() {
            }

            protected ReceiverBean(Parcel in) {
                this.id = in.readInt();
                this.createDate = in.readString();
                this.modifyDate = in.readString();
                this.version = in.readInt();
                this.member = in.readInt();
                this.isDefault = in.readByte() != 0;
                this.area = in.readString();
                this.address = in.readString();
                this.mobile = in.readString();
                this.receiverPeople = in.readString();
            }

            public static final Parcelable.Creator<ReceiverBean> CREATOR = new Parcelable.Creator<ReceiverBean>() {
                @Override
                public ReceiverBean createFromParcel(Parcel source) {
                    return new ReceiverBean(source);
                }

                @Override
                public ReceiverBean[] newArray(int size) {
                    return new ReceiverBean[size];
                }
            };
        }
    }
}
