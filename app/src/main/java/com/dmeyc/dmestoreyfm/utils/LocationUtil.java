package com.dmeyc.dmestoreyfm.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * create by cxg on 2019/12/1
 */

public class LocationUtil {

    public static final int TYPE_CUSTOM_ITEM = 2;
    public static final int TYPE_HOME_VIDEO= 5;



    private AMapLocationClient mLocationClient;
    private AMapLocationListener mLocationListener;



    public LocationUtil() {
    }


    class MyAMapLocationListener implements AMapLocationListener {
        private int mFromType;

        public MyAMapLocationListener(int fromType) {
            mFromType = fromType;
        }

        @Override
        public void onLocationChanged(final AMapLocation amapLocation) {
            mLocationClient.stopLocation();

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    Log.e("location==", "getDistrict==" + amapLocation.getDistrict() + ".getStreet(==" + amapLocation.getStreet() + "address==" + amapLocation.getAddress());
                    SPUtils.savaStringData(Constant.Config.CURRENT_CITY, amapLocation.getCity());
                    SPUtils.savaStringData(Constant.Config.CURRENT_PROVINCE, amapLocation.getProvince());
                    SPUtils.savaStringData(Constant.Config.LONGTUDE, amapLocation.getLongitude() + "");
                    SPUtils.savaStringData(Constant.Config.LATITUDE, amapLocation.getLatitude() + "");
                    DataClass.LocationCity = amapLocation.getCity();
                    DataClass.LocationProvince = amapLocation.getProvince();
                    DataClass.LocationDistrict = amapLocation.getDistrict();
                    DataClass.LocationAddress = amapLocation.getAddress();
                    EventBus.getDefault().post(new MyEvent.Location(mFromType, amapLocation.getProvince()));
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    setDefaultCitysInfo();
                }
            } else {
               setDefaultCitysInfo();
            }
        }
    }

    public void requestLocation(int typeFrom) {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
        }
        mLocationClient = new AMapLocationClient(BaseApp.getContext());
        mLocationListener = new MyAMapLocationListener(typeFrom);
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();
    }
    /**
     * 设置默认定位信息
     */
    private void setDefaultCitysInfo() {
        SPUtils.savaStringData(Constant.Config.CURRENT_CITY, "上海市");
        SPUtils.savaStringData(Constant.Config.CURRENT_PROVINCE, "上海市");
        SPUtils.savaStringData(Constant.Config.LONGTUDE, 121.520050 + "");
        SPUtils.savaStringData(Constant.Config.LATITUDE, 31.228833+ "");
        DataClass.LocationProvince = "上海市";
        DataClass.LocationCity = "上海市";
        // TODO: 2019/12/8 详细地址、区
        DataClass.LocationDistrict = "";
        DataClass.LocationAddress ="";
    }
}
