package com.dmeyc.dmestoreyfm.net;

import android.content.Context;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.tamic.novate.Novate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jockie on 2017/12/25
 * Email:jockie911@gmail.com
 */

public class RestClient {

    private static Novate sNovatenew;

    public static Novate getNovate(Context context) {
        Map<String, String> mHeaderMap = new ConcurrentHashMap<>();
        mHeaderMap.put("Content-Type", "application/json");
        sNovatenew = new Novate.Builder(context)
                .connectTimeout(10)  //连接时间 可以忽略
                .addCookie(false)  //是否同步cooike 默认不同步
                .addCache(false)  //是否缓存 默认缓存
                .baseUrl(Constant.API.BASE_URL) //base URL
                .addLog(true) //是否开启log
                .header(mHeaderMap)
                .build();
        return sNovatenew;
    }

    public static Novate getYfmNovate(Context context) {
        Map<String, String> mHeaderMap = new ConcurrentHashMap<>();
        mHeaderMap.put("Content-Type",  "application/json");
        sNovatenew = new Novate.Builder(context)
                .connectTimeout(100)  //连接时间 可以忽略
                .readTimeout(8000)
                .writeTimeout(8000)
                .addCookie(false)  //是否同步cooike 默认不同步
                .addCache(false)  //是否缓存 默认缓存
                .baseUrl(Constant.API.YFM_BASE_URL) //base URL
                .addLog(true) //是否开启log
                .header(mHeaderMap)
                .build();
        return sNovatenew;
    }

    public static Novate getYfmNovateTest(Context context) {
        Map<String, String> mHeaderMap = new ConcurrentHashMap<>();
        mHeaderMap.put("Content-Type",  "application/json");
        sNovatenew = new Novate.Builder(context)
                .connectTimeout(100)  //连接时间 可以忽略
                .readTimeout(8000)
                .writeTimeout(8000)
                .addCookie(false)  //是否同步cooike 默认不同步
                .addCache(false)  //是否缓存 默认缓存
                .baseUrl(Constant.API.YFMBIGACTION_URL) //base URL
                .addLog(true) //是否开启log
                .header(mHeaderMap)
                .build();
        return sNovatenew;
    }

    public static Novate getYfmNovateheader(Context context) {
        Map<String, String> mHeaderMap = new ConcurrentHashMap<>();
        mHeaderMap.put("Content-Type",  "multipart/*");
        sNovatenew = new Novate.Builder(context)
                .connectTimeout(10)  //连接时间 可以忽略
                .addCookie(false)  //是否同步cooike 默认不同步
                .addCache(false)  //是否缓存 默认缓存
                .baseUrl(Constant.API.YFM_BASE_URL) //base URL
                .addLog(true) //是否开启log
                .header(mHeaderMap)
                .build();
        return sNovatenew;
    }

    public static Novate getYfmNovateForm(Context context) {
        Map<String, String> mHeaderMap = new ConcurrentHashMap<>();
        mHeaderMap.put("Content-Type",  "multipart/form-data");
        sNovatenew = new Novate.Builder(context)
                .connectTimeout(10)  //连接时间 可以忽略
                .addCookie(false)  //是否同步cooike 默认不同步
                .addCache(false)  //是否缓存 默认缓存
                .baseUrl(Constant.API.YFM_BASE_URL) //base URL
                .addLog(true) //是否开启log
                .header(mHeaderMap)
                .build();
        return sNovatenew;
    }

    public static Novate getNovate(Context context, String baseUrl) {
        Map<String, String> mHeaderMap = new ConcurrentHashMap<>();
        mHeaderMap.put("Content-Type", "application/json");
        sNovatenew = new Novate.Builder(context)
                .connectTimeout(10)  //连接时间 可以忽略
                .addCookie(false) //是否同步cooike 默认不同步
                .addCache(false)  //是否缓存 默认缓存
                .baseUrl(baseUrl) //base URL
                .addLog(true) //是否开启log
                .header(mHeaderMap)
                .build();
        return sNovatenew;
    }
}
