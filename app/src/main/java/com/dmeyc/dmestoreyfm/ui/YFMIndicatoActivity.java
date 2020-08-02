package com.dmeyc.dmestoreyfm.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.huawei.hms.support.api.push.TokenResult;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.rong.push.platform.hms.HMSAgent;
import io.rong.push.platform.hms.common.handler.ConnectHandler;
import io.rong.push.platform.hms.push.handler.GetTokenHandler;

public class YFMIndicatoActivity extends Activity {
    ViewPager vp_indcator;
    int resoures[]={R.drawable.pageone,R.drawable.pagetwo};
    BGABanner banner_guide ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yfmincator);
        banner_guide = findViewById(R.id.banner_guide);
        banner_guide.setData(null,null,R.drawable.pageone,R.drawable.pagetwo);
        banner_guide.setEnterSkipViewIdAndDelegate(R.id.tv_enter, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(YFMIndicatoActivity.this,YFMSplashActivity.class));
                SPUtils.savaBooleanData("isfirst",true);
                finish();
            }
        });

        if(SPUtils.getBooleanData("isfirst")){
            startActivity(new Intent(YFMIndicatoActivity.this,YFMSplashActivity.class));
            finish();
        }else {

            banner_guide = findViewById(R.id.banner_guide);
            banner_guide.setData(null,null,R.drawable.pageone,R.drawable.pagetwo);
            banner_guide.setEnterSkipViewIdAndDelegate(R.id.tv_enter, 0, new BGABanner.GuideDelegate() {
                @Override
                public void onClickEnterOrSkip() {
                    startActivity(new Intent(YFMIndicatoActivity.this,YFMSplashActivity.class));
                    SPUtils.savaBooleanData("isfirst",true);
                    finish();
                }
            });

//            vp_indcator=findViewById(R.id.vp_indcator);
//            vp_indcator.setAdapter(new PagerAdapter() {
//                @Override
//                public int getCount() {
//                    return resoures.length;
//                }
//
//                @Override
//                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//
//                    return view == object;
//                }
//
//                @NonNull
//                @Override
//                public Object instantiateItem(@NonNull ViewGroup container, int position) {
////                ImageView iv=new ImageView(YFMIndicatoActivity.this);
//                    View view = View.inflate(container.getContext(), R.layout.pageradapter_indcator, null);
//                    ImageView iv_indcator=  view.findViewById(R.id.iv_indcator);
//                    TextView  tv_enter=view.findViewById(R.id.tv_enter);
//                    tv_enter.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            startActivity(new Intent(YFMIndicatoActivity.this,YFMSplashActivity.class));
//                            SPUtils.savaBooleanData("isfirst",true);
//                            finish();
//                        }
//                    });
//                    if(position==resoures.length-1){
//                        tv_enter.setVisibility(View.VISIBLE);
//                    }else {
//                        tv_enter.setVisibility(View.GONE);
//                    }
//                    iv_indcator.setBackground(getResources().getDrawable(resoures[position]));
//                    ((ViewPager) container).addView(view);
////                return super.instantiateItem(container, position);
//                    return view;
//                }
//                @Override
//                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                    ((ViewPager) container).removeView((View) object);
//                }
//            });
        }
        if (!isTaskRoot()) {
            finish();
            return;
        }

        HMSAgent.connect(this, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                Log.e("HMS connect end","HMS connect end:" + rst);
            }
        });
        getToken();
        setBadgeNum(5,this);
    }


    /**
     * 获取token
     */

    private void getToken() {
//        showLog("get token: begin");
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int i) {

            }

//            @Override
//            public void onResult(int rtnCode, TokenResult tokenResult) {
////                showLog("get token: end" + rtnCode);
//            }
        });
    }


    /**
     * 设置角标
     */
    public void setBadgeNum(int num, Context context) {
        try {
            Bundle bunlde = new Bundle();
            bunlde.putString("package", "com.dmeyc.dmestoreyfm");
            bunlde.putString("class", "com.dmeyc.dmestoreyfm.YFMIndicatoActivity");
            bunlde.putInt("badgenumber", num);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
