package com.dmeyc.dmestoreyfm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2017/7/4.
 */

public class MeasureBean{

    public String iserror;
    public String info;

    public String getIserror() {
        return iserror;
    }

    public void setIserror(String iserror) {
        this.iserror = iserror;
    }
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        private String id;
        private String objname;
        private String objurl;
        private String name;
        private String threeshowurl;
        private List<RecordBean.DataBean.InfoBean> info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getObjname() {
            return objname;
        }

        public void setObjname(String objname) {
            this.objname = objname;
        }

        public String getObjurl() {
            return objurl;
        }

        public void setObjurl(String objurl) {
            this.objurl = objurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThreeshowurl() {
            return threeshowurl;
        }

        public void setThreeshowurl(String threeshowurl) {
            this.threeshowurl = threeshowurl;
        }

        public List<RecordBean.DataBean.InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<RecordBean.DataBean.InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Parcelable {

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.value);
            }

            public InfoBean() {
            }

            protected InfoBean(Parcel in) {
                this.name = in.readString();
                this.value = in.readString();
            }

            public static final Creator<RecordBean.DataBean.InfoBean> CREATOR = new Creator<RecordBean.DataBean.InfoBean>() {
                @Override
                public RecordBean.DataBean.InfoBean createFromParcel(Parcel source) {
                    return new RecordBean.DataBean.InfoBean(source);
                }

                @Override
                public RecordBean.DataBean.InfoBean[] newArray(int size) {
                    return new RecordBean.DataBean.InfoBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.objname);
            dest.writeString(this.objurl);
            dest.writeString(this.name);
            dest.writeString(this.threeshowurl);
            dest.writeList(this.info);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.objname = in.readString();
            this.objurl = in.readString();
            this.name = in.readString();
            this.threeshowurl = in.readString();
            this.info = new ArrayList<RecordBean.DataBean.InfoBean>();
            in.readList(this.info, RecordBean.DataBean.InfoBean.class.getClassLoader());
        }

        public static final Creator<RecordBean.DataBean> CREATOR = new Creator<RecordBean.DataBean>() {
            @Override
            public RecordBean.DataBean createFromParcel(Parcel source) {
                return new RecordBean.DataBean(source);
            }

            @Override
            public RecordBean.DataBean[] newArray(int size) {
                return new RecordBean.DataBean[size];
            }
        };
    }
}
