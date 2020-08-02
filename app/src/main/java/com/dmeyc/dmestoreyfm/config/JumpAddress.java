package com.dmeyc.dmestoreyfm.config;

import com.dmeyc.dmestoreyfm.BuildConfig;

/**
 * create by cxg on 2019/11/24
 */
public class JumpAddress {
    private static String BASE_URL;
    static {
        if (BuildConfig.DEBUG){
            BASE_URL = "http://101.44.2.178:1234/";
        }else {
            BASE_URL = "http://www.hotu.club:9595/";
        }
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
