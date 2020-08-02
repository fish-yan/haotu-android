package com.dmeyc.dmestoreyfm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jockie on 2018/4/28
 * Email:jockie911@gmail.com
 */

public class SelectInfo implements Parcelable {

    private boolean isTailorRecord; //是否来自量身记录
    private String tailorRecordId; //量身记录的id
    private String sizeInfo;    //标准尺码名字或者量身记录名称


    public SelectInfo(boolean isTailorRecord, String sizeInfo) {
        this.isTailorRecord = isTailorRecord;
        this.sizeInfo = sizeInfo;
    }

    public SelectInfo(boolean isTailorRecord, String sizeInfo, String tailorRecordId) {
        this.isTailorRecord = isTailorRecord;
        this.tailorRecordId = tailorRecordId;
        this.sizeInfo = sizeInfo;
    }

    public boolean isTailorRecord() {
        return isTailorRecord;
    }

    public void setTailorRecord(boolean tailorRecord) {
        isTailorRecord = tailorRecord;
    }

    public String getSizeInfo() {
        return sizeInfo;
    }

    public void setSizeInfo(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public String getTailorRecordId() {
        return tailorRecordId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isTailorRecord ? (byte) 1 : (byte) 0);
        dest.writeString(this.tailorRecordId);
        dest.writeString(this.sizeInfo);
    }

    protected SelectInfo(Parcel in) {
        this.isTailorRecord = in.readByte() != 0;
        this.tailorRecordId = in.readString();
        this.sizeInfo = in.readString();
    }

    public static final Creator<SelectInfo> CREATOR = new Creator<SelectInfo>() {
        @Override
        public SelectInfo createFromParcel(Parcel source) {
            return new SelectInfo(source);
        }

        @Override
        public SelectInfo[] newArray(int size) {
            return new SelectInfo[size];
        }
    };
}
