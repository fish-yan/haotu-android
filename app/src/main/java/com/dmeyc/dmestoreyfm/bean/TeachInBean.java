package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TeachInBean implements Serializable {
    String clubname;
    String projecttype;
    String  detailadress;
    String backtext;
    String province;
    String city;
    String area;
    String teachname;
    String teachsex;
    String teachlog;
    String teachage;
    String teacheducation;

    String teachintro;
    ArrayList<String> teachimags;
    ArrayList<String> teachintroimages;


    public String getTeachage() {
        return teachage;
    }

    public String getTeacheducation() {
        return teacheducation;
    }

    public String getTeachlog() {
        return teachlog;
    }

    public String getTeachname() {
        return teachname;
    }

    public ArrayList<String> getTeachimags() {
        return teachimags;
    }

    public String getTeachsex() {
        return teachsex;
    }

    public String getTeachintro() {
        return teachintro;
    }

    public ArrayList<String> getTeachintroimages() {
        return teachintroimages;
    }

    public void setTeachage(String teachage) {
        this.teachage = teachage;
    }

    public void setTeacheducation(String teacheducation) {
        this.teacheducation = teacheducation;
    }

    public void setTeachlog(String teachlog) {
        this.teachlog = teachlog;
    }

    public void setTeachimags(ArrayList<String> teachimags) {
        this.teachimags = teachimags;
    }

    public void setTeachname(String teachname) {
        this.teachname = teachname;
    }

    public void setTeachsex(String teachsex) {
        this.teachsex = teachsex;
    }

    public void setTeachintro(String teachintro) {
        this.teachintro = teachintro;
    }

    public void setTeachintroimages(ArrayList<String> teachintroimages) {
        this.teachintroimages = teachintroimages;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getDetailadress() {
        return detailadress;
    }

    public String getBacktext() {
        return backtext;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public String getClubname() {
        return clubname;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDetailadress(String detailadress) {
        this.detailadress = detailadress;
    }

    public void setBacktext(String backtext) {
        this.backtext = backtext;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }
}
