package com.dmeyc.dmestoreyfm.utils;

import com.dmeyc.dmestoreyfm.bean.MyCreatCommListBean;
import com.dmeyc.dmestoreyfm.bean.SportSubmitBean;
import com.dmeyc.dmestoreyfm.bean.common.ProductCategoryBean;

import java.util.List;

import io.rong.imlib.model.Message;

public class DataClass {
    public static List<ProductCategoryBean> data;
    public static List<String>data0 ;
    public static List<String> data1;
    public static List<String> data2;
    public static String LocationCity = "上海市";
    public static String LocationProvince = "上海市";// 当前省
    public static String LocationDistrict ; // 当前区
    public static String LocationAddress; // 当前详细地址
    public static SportSubmitBean sportSubmitBean;
    public static MyCreatCommListBean myCreatCommListBean;
    public static String commidinity="";
    public static String bankname="";
    public static String bankcardnumber="";
    public static int  bankid=-1;
    public static String banklogo="";
    public static Message message=null;
}
