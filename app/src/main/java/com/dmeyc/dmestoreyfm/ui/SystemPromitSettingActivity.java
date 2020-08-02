package com.dmeyc.dmestoreyfm.ui;

import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.SportSubmitBean;
import com.dmeyc.dmestoreyfm.utils.PermissionPageUtils;
import com.dmeyc.dmestoreyfm.wedgit.SlideButton;

import butterknife.Bind;

import static android.graphics.Color.parseColor;


public class SystemPromitSettingActivity extends BaseActivity {

    @Bind(R.id.slb_buton)
    SlideButton slb_buton;
    @Bind(R.id.slb_buton1)
    SlideButton slb_buton1;
    @Bind(R.id.slb_buton2)
    SlideButton slb_buton2;
    @Bind(R.id.slb_buton3)
    SlideButton slb_buton3;
    @Bind(R.id.slb_buton4)
    SlideButton slb_buton4;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_systempromitsetting;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        slb_buton.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));
        slb_buton1.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));
        slb_buton2.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));
        slb_buton3.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));
        slb_buton4.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));

        slb_buton.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){
                    PermissionPageUtils pm=new PermissionPageUtils(SystemPromitSettingActivity.this);
                    pm.jumpPermissionPage();
//                    ll_switch.setVisibility(View.VISIBLE);
//                    check=1;
                }else {
//                    ll_switch.setVisibility(View.GONE);
//                    check=0;
                }
            }
        });

        slb_buton1.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){
                    PermissionPageUtils pm=new PermissionPageUtils(SystemPromitSettingActivity.this);
                    pm.jumpPermissionPage();
//                    ll_switch.setVisibility(View.VISIBLE);
//                    check=1;
                }else {
//                    ll_switch.setVisibility(View.GONE);
//                    check=0;
                }
            }
        });

        slb_buton2.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){
                    PermissionPageUtils pm=new PermissionPageUtils(SystemPromitSettingActivity.this);
                    pm.jumpPermissionPage();
//                    ll_switch.setVisibility(View.VISIBLE);
//                    check=1;
                }else {
//                    ll_switch.setVisibility(View.GONE);
//                    check=0;
                }
            }
        });


        slb_buton3.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){
                    PermissionPageUtils pm=new PermissionPageUtils(SystemPromitSettingActivity.this);
                    pm.jumpPermissionPage();
//                    ll_switch.setVisibility(View.VISIBLE);
//                    check=1;
                }else {
//                    ll_switch.setVisibility(View.GONE);
//                    check=0;
                }
            }
        });


        slb_buton4.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){
                    PermissionPageUtils pm=new PermissionPageUtils(SystemPromitSettingActivity.this);
                    pm.jumpPermissionPage();
//                    ll_switch.setVisibility(View.VISIBLE);
//                    check=1;
                }else {
//                    ll_switch.setVisibility(View.GONE);
//                    check=0;
                }
            }
        });
    }
}
