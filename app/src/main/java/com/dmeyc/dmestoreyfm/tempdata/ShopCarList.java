package com.dmeyc.dmestoreyfm.tempdata;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jockie on 2017/11/28
 * Email:jockie911@gmail.com
 */

public class ShopCarList {

    public static List<ShopCar> getShopCarList(){
        List<ShopCar> list = new ArrayList<>();
        list.add(new ShopCar("Gucci", Arrays.asList(new ShopCar.ItemCar("","黑色毛衣",1243,1),new ShopCar.ItemCar("","黄色毛衣",3921,1))));
        list.add(new ShopCar("Amani", Arrays.asList(new ShopCar.ItemCar("","军大衣",42351,2))));
        list.add(new ShopCar("Lily", Arrays.asList(new ShopCar.ItemCar("","afdfda",951,3),
                new ShopCar.ItemCar("","hahalf",345,1),
                new ShopCar.ItemCar("","hagdfgdfhalf",141,1))));
        return list;
    }

    public static class ShopCar implements Parcelable {
        private String brandName;
        private List<ItemCar> car;

        public ShopCar(String brandName,List<ItemCar> car){
            this.brandName = brandName;
            this.car = car;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName){
            this.brandName = brandName;
        }

        public List<ItemCar> getCar() {
            return car;
        }

        public void setCar(List<ItemCar> itemCar){
            car = itemCar;
        }

        public static class ItemCar implements Parcelable {
            private String itempic;
            private String name;
            private int price;
            private int count;
            private boolean isChecked;

            public ItemCar(String itempic,String name,int price,int count){
                this.itempic = itempic;
                this.name = name;
                this.price = price;
                this.count = count;
            }

            public String getItempic() {
                return itempic;
            }

            public String getName() {
                return name;
            }

            public int getPrice() {
                return price;
            }

            public int getCount() {
                return count;
            }

            public int setCount(int count) {
                return this.count = count;
            }

            public boolean isIsChecked(){
                return isChecked;
            }

            public void setIsChecked(boolean isChecked){
                this.isChecked = isChecked;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.itempic);
                dest.writeString(this.name);
                dest.writeInt(this.price);
                dest.writeInt(this.count);
                dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
            }

            protected ItemCar(Parcel in) {
                this.itempic = in.readString();
                this.name = in.readString();
                this.price = in.readInt();
                this.count = in.readInt();
                this.isChecked = in.readByte() != 0;
            }

            public static final Creator<ItemCar> CREATOR = new Creator<ItemCar>() {
                @Override
                public ItemCar createFromParcel(Parcel source) {
                    return new ItemCar(source);
                }

                @Override
                public ItemCar[] newArray(int size) {
                    return new ItemCar[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.brandName);
            dest.writeTypedList(this.car);
        }

        protected ShopCar(Parcel in) {
            this.brandName = in.readString();
            this.car = in.createTypedArrayList(ItemCar.CREATOR);
        }

        public static final Creator<ShopCar> CREATOR = new Creator<ShopCar>() {
            @Override
            public ShopCar createFromParcel(Parcel source) {
                return new ShopCar(source);
            }

            @Override
            public ShopCar[] newArray(int size) {
                return new ShopCar[size];
            }
        };
    }
}
