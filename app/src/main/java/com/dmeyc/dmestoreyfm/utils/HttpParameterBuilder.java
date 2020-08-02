package com.dmeyc.dmestoreyfm.utils;

import android.net.Uri;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 描述:ApiStores
 * 创建人:kong
 * 创建时间:2017/3/11
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class HttpParameterBuilder {
    private static HttpParameterBuilder mParameterBuilder;
    private static Map<String, RequestBody> params;

    /**
     * 构建私有方法
     */
    private HttpParameterBuilder() {

    }

    /**
     * 初始化对象
     */
    public static HttpParameterBuilder newBuilder(){
        if (mParameterBuilder==null){
            mParameterBuilder = new HttpParameterBuilder();
        }
        if (params==null){
            params = new HashMap<>();
        }else {
            params.clear();
        }
        return mParameterBuilder;
    }

    /**
     * 添加参数
     * 根据传进来的Object对象来判断是String还是File类型的参数
     */
    public HttpParameterBuilder addParameter(String key, Object o) {
//        params.clear();
        if (o instanceof String) {
            RequestBody body = RequestBody.create(MediaType.parse("text/plain"), (String) o);
            params.put(key, body);
        } else if (o instanceof File) {
//            RequestBody body = RequestBody.create(MediaType.parse("image/*"), (File) o);
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), (File) o);

            params.put( key, body);
//            params.put( "file", body);
        }
        return this;
    }

    /**
     * 初始化图片的Uri来构建参数
     * 一般不常用
     * 主要用在拍照和图库中获取图片路径的时候
     */
    public HttpParameterBuilder addFilesByUri(String key, List<Uri> uris) {
        for (int i = 0; i < uris.size(); i++) {
            File file = new File(uris.get(i).getPath());
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
//            params.put(key + i + "\"; filename=\"" + file.getName() + "", body);
//            if(uris.size() == 1){
//                params.put(key + "\"; filename=\"" + file.getName() + "", body);
//            }else{
//                params.put(key+"["+i+"]" + "\"; filename=\"" + file.getName() + "", body);
            params.put( "file", body);
//            }
        }
        return this;
    }

    /**
     * 构建RequestBody
     */
    public Map<String, RequestBody> bulider() {

        return params;
    }
}
