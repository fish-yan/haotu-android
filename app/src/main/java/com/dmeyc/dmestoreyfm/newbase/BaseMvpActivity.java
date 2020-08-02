package com.dmeyc.dmestoreyfm.newbase;

import android.os.Bundle;

import com.dmeyc.dmestoreyfm.utils.LoadingUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * create by cxg on 2019/11/24
 */
public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends BaseActivity implements IBaseView{
    protected P mPresenter;
    protected abstract P createPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V)this);
        }
        super.onCreate(savedInstanceState);
        if (enableEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        if (enableEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    protected boolean enableEventBus(){
        return false;
    }

    @Override
    public void showProgress() {
        LoadingUtils.showProgressDialog(this,"加载中...");
    }

    @Override
    public void hideProgress() {
        LoadingUtils.cancelProgressDialog();
    }
}
