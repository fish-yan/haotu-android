package com.dmeyc.dmestoreyfm.receiver;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.model.MessageContent;

@SuppressLint("ParcelCreator")
public class CustomizeMessage extends MessageContent {

    private String content;//消息属性，可随意定
    public CustomizeMessage(String content){
        this.content=content;
    }

//    public CustomizeMessage(byte[] data) {
//        String jsonStr = null;
//
//        try {
//            jsonStr = new String(data, "UTF-8");
//        } catch (UnsupportedEncodingException e1) {
//
//        }
//
//        try {
//            JSONObject jsonObj = new JSONObject(jsonStr);
//
////            if (jsonObj.has("content"))
////                content = jsonObj.optString("content");
//
//        } catch (JSONException e) {
////            RLog.e(this, "JSONException", e.getMessage());
//        }
//
//    }

    public CustomizeMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("content"))
                content = jsonObj.optString("content");

        } catch (JSONException e) {
            Log.e("TAG", e.getMessage());
        }

    }

    //给消息赋值。
    public CustomizeMessage(Parcel in) {
        content= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性

        //这里可继续增加你消息的属性
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<CustomizeMessage> CREATOR = new Creator<CustomizeMessage>() {

        @Override
        public CustomizeMessage createFromParcel(Parcel source) {
            return new CustomizeMessage(source);
        }

        @Override
        public CustomizeMessage[] newArray(int size) {
            return new CustomizeMessage[size];
        }
    };


    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("content",content);
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public byte[] encode() {
//        JSONObject jsonObj = new JSONObject();
//
//        try {
//            jsonObj.put("content", "这是一条消息内容");
//        } catch (JSONException e) {
//            Log.e("JSONException", e.getMessage());
//        }
//
//        try {
//            return jsonObj.toString().getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, content);//该类为工具类，对消息中属性进行序列化

        //这里可继续增加你消息的属性
    }


    public String getContent() {
        return content;
    }

}
