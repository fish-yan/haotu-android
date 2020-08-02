package com.dmeyc.dmestoreyfm.api.exception;

import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        Logger.e(e.getMessage());
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            ResponseBody responseBody = httpException.response().errorBody();
            String resErrorJson = "";
            try {
                resErrorJson = responseBody.string();
                Logger.json(resErrorJson);
                Map map = new Gson().fromJson(resErrorJson, Map.class);
                if (map != null) {
                    ex.message = map.get("message").toString();
                    return ex;
                }
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (JsonSyntaxException e1) {
                Logger.e(resErrorJson);
            } catch (IOException | NullPointerException e1) {
                e.printStackTrace();
            }
            switch (httpException.code()) {
                case GATEWAY_TIMEOUT:
                    ex.message = "似乎已断开与互联网的连接" ;
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "服务器异常：" + httpException.code();  //均视为网络错误
                    break;
            }
            return ex;

        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            ex.message = "网络连接出现错误";  //视为网络错误
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";            //均视为解析错误
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            ex.message = "网络连接出现错误";  //视为网络错误
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            ex.message = "网络连接超时";
            return ex;
        } else {
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.message = "";          //未知错误
            return ex;
        }
    }

    /**
     * 约定异常
     */

    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;
    }
}