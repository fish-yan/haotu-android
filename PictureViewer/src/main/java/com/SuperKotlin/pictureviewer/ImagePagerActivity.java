package com.SuperKotlin.pictureviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片查看器
 * Created by ytq on 2018/11/7.
 */
public class ImagePagerActivity extends FragmentActivity {
    private static final String TAG = "ImagePagerActivity";
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    private TextView delete;
    private static boolean mIsShowNumber = true;//是否显示数字下标
    private static boolean needDelelete = false;//是否显示删除
    private boolean isFirst = true;//第一次加载数据
    private ArrayList<String> urls = new ArrayList<>();
    private ImagePagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明状态栏
        }
        setContentView(R.layout.activity_image_detail_pager);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        mPager = (HackyViewPager) findViewById(R.id.pager);
//            final ImagePagerAdapter mAdapter = new ImagePagerAdapter(
//                    getSupportFragmentManager(),urls);
//            mPager.setAdapter(mAdapter);
//            mAdapter.setData(urls);
        indicator = (TextView) findViewById(R.id.indicator);
        delete = (TextView) findViewById(R.id.delete);
        initPager();
        if(needDelelete){
            delete.setVisibility(View.VISIBLE);
        }else{
            delete.setVisibility(View.GONE);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urls.remove(mPager.getCurrentItem());
                if (urls.size() == 0) {
                    finish();
                } else {
                    initPager();
                }
            }
        });
        // 更新下标
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator,
                        arg0 + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
        indicator.setVisibility(mIsShowNumber ? View.VISIBLE : View.GONE);
    }

    public void initPager() {
        if(isFirst){
            mPager = (HackyViewPager) findViewById(R.id.pager);
            mAdapter = new ImagePagerAdapter(
                    getSupportFragmentManager());
            mPager.setAdapter(mAdapter);
            mAdapter.setData(urls);
        }else{
            mAdapter.setData(urls);
        }
        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager.getAdapter().getCount());
        indicator.setText(text);
        isFirst = false;
    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().postSticky(urls);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public List<String> fileList;


        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setData(List<String> fileList) {
            this.fileList = fileList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position).toString();
            return ImageDetailFragment.newInstance(url);
        }
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        }

    public static void startActivity(Context context, PictureConfig config) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, config.list);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, config.position);
        ImageDetailFragment.mImageLoading = config.resId;
        ImageDetailFragment.mNeedDownload = config.needDownload;
        mIsShowNumber = config.mIsShowNumber;
        needDelelete = config.needDelelete;
        ImageUtil.path = config.path;
        context.startActivity(intent);
    }
}