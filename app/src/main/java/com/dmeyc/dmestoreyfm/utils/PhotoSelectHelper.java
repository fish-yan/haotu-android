package com.dmeyc.dmestoreyfm.utils;

import android.app.Activity;
import android.content.Intent;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ActionSheetDialog;
import com.dmeyc.dmestoreyfm.ui.SelectPhotoActivity;

/**
 * Created by jockie on 2018/5/21
 * Email:jockie911@gmail.com
 */

public class PhotoSelectHelper {

    private Activity activity;
    private int PHOTO_PIC_REQUEST_CODE = Constant.Config.PHOTO_PIC_REQUEST_CODE;
    private boolean isUseCrop;
    private int maxPicCount = 1;

    private int type=0;
    public PhotoSelectHelper(Activity activity,int type){
        this.activity = activity;
        this.type=type;
    }

//    public PhotoSelectHelper setFragment(K fragment){
//        this.fragment = fragment;
//        return this;
//    }

    public PhotoSelectHelper setCrop(boolean isUseCrop){
        this.isUseCrop = isUseCrop;
        return this;
    }

    public PhotoSelectHelper setMaxPicCount(int maxPicCount){
        this.maxPicCount = maxPicCount;
        return this;
    }

    public int getMaxPicCount(){
        return maxPicCount;
    }

    public PhotoSelectHelper setRequestCode(int code){
        this.PHOTO_PIC_REQUEST_CODE = code;
        return this;
    }

    public int getRequestCode(){
        return PHOTO_PIC_REQUEST_CODE;
    }

    public void show(){
        new ActionSheetDialog(activity).builder()
                .addSheetItem("相机拍照", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        stepIntoPhoto(0);
                    }
                }).addSheetItem("相册选取", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int position) {
                stepIntoPhoto(1);
            }
        }).show();
    }

    private void stepIntoPhoto(int which) {
        Intent intent = new Intent(activity, SelectPhotoActivity.class);
        if(isUseCrop){
            intent.putExtra(Constant.Config.IS_USER_CROP,true);
            if(which == 0){     // 相机 剪裁
                intent.putExtra(Constant.Config.IS_USE_PHOTO,true);
            }
        }else{
            intent.putExtra(Constant.Config.MAX_PIC_COUNT,getMaxPicCount());
            if(which == 0){
                intent.putExtra(Constant.Config.IS_USE_PHOTO,true);
            }
        }
//        if(fragment == null){
        if(type==0){
            intent.putExtra(Constant.Config.IS_CROPTYPE,0);
        }else {
            intent.putExtra(Constant.Config.IS_CROPTYPE,1);
        }

        activity.startActivityForResult(intent,getRequestCode());
//        }else{
//            fragment.startActivityForResult(intent,getRequestCode());
//        }
    }
}
