package com.dmeyc.dmestoreyfm.ui.look.section;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

public class FindDetailAdapter extends PagerAdapter {
    private ArrayList<String> mPicLists;
    private Context context;

    public FindDetailAdapter(ArrayList<String> mPicLists, Context context) {
        this.mPicLists = mPicLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mPicLists.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final View container, final int position) {
        View view = View.inflate(container.getContext(), R.layout.item_vp_product, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);

        GlideUtil.loadImage(container.getContext(), mPicLists.get(position), iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browsePic(mPicLists, position);
            }
        });
        ((ViewPager) container).addView(view);
        return view;
    }

    private void browsePic(List<String> list, int position) {
        //使用方式
        PictureConfig config = new PictureConfig.Builder()
                .setListData((ArrayList<String>) list)
                .setPosition(position)    //图片下标（从第position张图片开始浏览）
                .setDownloadPath("juneyaoair")    //图片下载文件夹地址
                .setIsShowNumber(true)//是否显示数字下标
                .needDownload(true)    //是否支持图片下载
                .needDelelete(false)    //是否支持图片下载
//                .setPlacrHolder(R.dra.logo)    //占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                .build();
        ImagePagerActivity.startActivity(context, config);
    }

    @Override
    public void destroyItem(View container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }
}
