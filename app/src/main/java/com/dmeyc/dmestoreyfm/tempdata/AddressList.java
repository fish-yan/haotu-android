package com.dmeyc.dmestoreyfm.tempdata;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/23
 * Email:jockie911@gmail.com
 */

public class AddressList {

    public static List<Address> getAddressList(){
        List<Address> list = new ArrayList<>();
        list.add(new Address("Emily_红","18304235643","上海市 奉贤区","绿地翡翠"));
        list.add(new Address("Jockie_jiang","18301673580","上海市 闵行区","科技绿洲三期"));
        return list;
    }

    public static class Address implements Parcelable {
        private  String name;
        private  String phone;
        private  String address;
        private  String detail;

        public Address(String name, String phone, String address, String detail) {
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.detail = detail;
        }

        public String getPhone() {
            return phone;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getDetail() {
            return detail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.phone);
            dest.writeString(this.address);
            dest.writeString(this.detail);
        }

        protected Address(Parcel in) {
            this.name = in.readString();
            this.phone = in.readString();
            this.address = in.readString();
            this.detail = in.readString();
        }

        public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
            @Override
            public Address createFromParcel(Parcel source) {
                return new Address(source);
            }

            @Override
            public Address[] newArray(int size) {
                return new Address[size];
            }
        };
    }
}
