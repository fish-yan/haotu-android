package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CommperInBean implements Serializable {
    String coppername;
    String cityname;
    String phonenumber;
    String  detailadress;
    String backtext;
    String province;
    String city;
    String area;

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getBacktext() {
        return backtext;
    }

    public String getCityname() {
        return cityname;
    }

    public String getCoppername() {
        return coppername;
    }

    public String getDetailadress() {
        return detailadress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setBacktext(String backtext) {
        this.backtext = backtext;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public void setCoppername(String coppername) {
        this.coppername = coppername;
    }

    public void setDetailadress(String detailadress) {
        this.detailadress = detailadress;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
