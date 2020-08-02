package com.dmeyc.dmestoreyfm.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class PicassoImageLoader implements com.lzy.imagepicker.loader.ImageLoader {

    @Override
    public void displayImage(Activity activity, String s, ImageView imageView, int i, int i1) {
        Glide.with(activity).load(s).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String s, ImageView imageView, int i, int i1) {

    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
