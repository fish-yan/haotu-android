package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.dmeyc.dmestoreyfm.R;

/**
 * Created by jockie on 2017/9/12
 * Email:jockie911@gmail.com
 */

public class GlideUtil {
    public static void loadImage(Context context, Object object, ImageView targetImg) {
        Glide.with(context).load(object).into(targetImg);
    }
    public static void loadImageCache(Context context, Object object, ImageView targetImg) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(object).apply(requestOptions).into(targetImg);
    }

    public static void loadAfterLoadImage(Context context,String url,final ImageView imageView){
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                imageView.setImageDrawable(resource);
            }
        });
    }

    public static void loadHeadImage(Context context, Object object, ImageView targetImg) {
        //头像url地址一致 不清除缓存会错乱
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);///不使用磁盘缓存
        requestOptions.skipMemoryCache(true);
        Glide.with(context).load(object).apply(requestOptions).into(targetImg);
    }

    private static RequestOptions options = new RequestOptions()
            .centerCrop()
            .error(R.drawable.image_default);
    private static RequestOptions options1 = new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .error(R.drawable.image_default);
    public static void loadLocalImage(Context context,Object object,ImageView targetView){
        Glide.with(context).load(object).apply(options).into(targetView);
    }

    public static void loacImageCenterCrop(Context context,Object object,ImageView targetView){
        Glide.with(context).load(object).apply(options1).into(targetView);
    }

    public static void preLoadImage(Context context, Object object){
        Glide.with(context).load(object).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)).preload();
    }
}
