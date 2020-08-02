package com.dmeyc.dmestoreyfm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/2/2
 * Email:jockie911@gmail.com
 */

public class RecordBean {

    public String iserror;
    public String info;

    public String getIserror() {
        return iserror;
    }

    public void setIserror(String iserror) {
        this.iserror = iserror;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        private String id;
        private String objname;
        private String objurl;
        private String name;
        private String threeshowurl;
        private List<InfoBean> info;

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

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
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

            public static final Creator<InfoBean> CREATOR = new Creator<InfoBean>() {
                @Override
                public InfoBean createFromParcel(Parcel source) {
                    return new InfoBean(source);
                }

                @Override
                public InfoBean[] newArray(int size) {
                    return new InfoBean[size];
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
            this.info = new ArrayList<InfoBean>();
            in.readList(this.info, InfoBean.class.getClassLoader());
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
