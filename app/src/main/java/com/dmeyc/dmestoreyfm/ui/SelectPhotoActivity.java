package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;

public class SelectPhotoActivity extends TakePhotoActivity {
    int  croptype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        Intent intent = getIntent();
        boolean isUsePhoto = intent.getBooleanExtra(Constant.Config.IS_USE_PHOTO, false); //  是否是使用摄像头拍照
        boolean isUserCrop = intent.getBooleanExtra(Constant.Config.IS_USER_CROP, false); //  是否需要剪裁 -- 剪裁为上传头像
        croptype = intent.getIntExtra(Constant.Config.IS_CROPTYPE, 0); //  是否需要剪裁 -- 剪裁为上传头像
//        boolean isUserCrop = false; //  是否需要剪裁 -- 剪裁为上传头像
        int maxPicCount = intent.getIntExtra(Constant.Config.MAX_PIC_COUNT, 1);
        setCompressConfig(isUserCrop);
        if(isUsePhoto){
            if(isUserCrop){
                getTakePhoto().onPickFromCaptureWithCrop(getUrlPath(),getCropOptions());
            }else{
                getTakePhoto().onPickFromCapture(getUrlPath());
            }
        }else{
            if(isUserCrop){
                getTakePhoto().onPickFromGalleryWithCrop(getUrlPath(),getCropOptions());
            }else{
                getTakePhoto().onPickMultiple(maxPicCount);
//                getTakePhoto().onPickFromGallery();
            }
        }
    }

    private Uri getUrlPath(){
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }

    private void setCompressConfig(boolean isAvatar){
        CompressConfig compressConfig=new CompressConfig.Builder().setMaxSize(isAvatar?(100*1024):(500*1024)).setMaxPixel(1024).create();
        getTakePhoto().onEnableCompress(compressConfig,true);
    }

    private CropOptions getCropOptions(){
        CropOptions.Builder builder=new CropOptions.Builder();
        if(0==croptype){
            builder.setAspectX(1).setAspectY(1);
            builder.setWithOwnCrop(false);  //是否用第三方的剪裁工具
        }else {
            builder.setAspectX(4).setAspectY(3);
            builder.setWithOwnCrop(true);  //是否用第三方的剪裁工具
        }
        return builder.create();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        resultData(true,result.getImages());
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        resultData(false,null);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        resultData(false,null);
    }

    private void resultData(boolean isSuccess, ArrayList<TImage> images){
        Intent intent = new Intent();
        if(isSuccess){
            intent.putExtra(Constant.Config.SUCCESS,true);
            intent.putExtra("images",images);
        }else{
            intent.putExtra(Constant.Config.SUCCESS, false);
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}

