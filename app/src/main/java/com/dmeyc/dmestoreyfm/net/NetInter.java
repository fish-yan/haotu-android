package com.dmeyc.dmestoreyfm.net;

import android.database.Observable;

import com.dmeyc.dmestoreyfm.bean.ImagePathBean;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface NetInter  {
//    //图片上传1004
//    @Multipart
//    @POST("v1/user/img?")
//    Observable<ImagePathBean> updateImage(@Query("time") String time, @PartMap Map<String, RequestBody> params);

    //图片上传1004
//    @Multipart
//    @POST("api/v1.0/user/uploadUserImage")
//    Call<ImagePathBean> getPic(@PartMap Map<String, RequestBody> params);


//    @Multipart
//    @POST("api/v1.0/file/uploadSingle")
//    Call<ImagePathBean> getPic(@PartMap Map<String, RequestBody> params);
    @Multipart
    @POST("api/v1.0/file/uploadSingle")
    Call<ImagePathBean> getPic(@PartMap Map<String, RequestBody> params);
//    @Multipart
//    @POST("api/v1.0/file/uploadSingle")
//    Call<ImagePathBean> getPic(@For("file") File file,@Field("user_token") String user_token );
}
