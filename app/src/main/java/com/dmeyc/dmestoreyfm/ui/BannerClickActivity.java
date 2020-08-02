package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.video.merchantentry.register.MerchantentryRegisterActivity;
import com.dmeyc.dmestoreyfm.video.merchantentry.register.TeachRegisterActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class BannerClickActivity extends BaseActivity {
    @Bind(R.id.view_back)
    View view_back;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bannerclick;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.view_back,R.id.ll_comm_in,R.id.ll_teachin,R.id.ll_commperin})
    public void onClcik(View view){
       int viewid= view.getId();
        if(viewid==R.id.view_back){
            finish();
        }else if(R.id.ll_comm_in==viewid){
            startActivity(new Intent(BannerClickActivity.this, CommInActivity.class));
        }else if(R.id.ll_teachin==viewid){
            TeachRegisterActivity.newIntent(BannerClickActivity.this);
//          startActivity(new Intent(BannerClickActivity.this, TeachInActivity.class));
        }else if(R.id.ll_commperin==viewid){
            SPUtils.savaStringData(Constant.Config.BUSINESS_REGISTER_TYPE,"4");
            MerchantentryRegisterActivity.newIntent(BannerClickActivity.this);
//            startActivity(new Intent(BannerClickActivity.this,CommperInActiivty.class));
        }
    }
}
