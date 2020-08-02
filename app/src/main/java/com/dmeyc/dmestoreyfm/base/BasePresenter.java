package com.dmeyc.dmestoreyfm.base;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */


public abstract class BasePresenter<T extends IBaseView,K extends BaseActivity> {

    protected T mBaseView;
    protected WeakReference<K> mActivity;
//    protected CompositeSubscription mCompositeSubscription;

    public void attachView(T mBaseView) {
        this.mBaseView = mBaseView;
        mActivity = new WeakReference((K)mBaseView);
        onViewAttach();
//        mCompositeSubscription = new CompositeSubscription();
        initSubscription();
    }

    protected void onViewAttach() {
    }

    protected void initSubscription() {

    }

    public void detachView() {
       /* if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }*/
        mBaseView = null;
    }

}
