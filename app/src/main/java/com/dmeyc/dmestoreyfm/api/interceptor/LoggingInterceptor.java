package com.dmeyc.dmestoreyfm.api.interceptor;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * create by cxg on 2019/11/24
 * 打印返回的json数据拦截器
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Buffer requestBuffer = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(requestBuffer);
        } else {
            Logger.d("LogTAG-----request.body() == null");
        }
        //打印url信息
        Logger.w(request.url() + (request.body() != null ? "?" + parseParams(request.body(),
                requestBuffer) : ""));

        return chain.proceed(request);
    }

    @NonNull
    private static String parseParams(RequestBody body, Buffer requestBuffer) throws
            UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "GBK");
        }
        return "null";
    }
}
