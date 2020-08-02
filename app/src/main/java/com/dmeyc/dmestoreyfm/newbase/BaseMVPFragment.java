package com.dmeyc.dmestoreyfm.newbase;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmeyc.dmestoreyfm.utils.LoadingUtils;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseMVPFragment<V, P extends BasePresenter<V>> extends BaseFragment implements IBaseView {
    protected P mPresenter;
    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isViewCreated;
    /**
     * Fragment对用户可见的标记
     */
    private boolean isUIVisible;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (enableEventBus()){
            EventBus.getDefault().register(this);
        }
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (enableEventBus()){
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        boolean change = isVisibleToUser != getUserVisibleHint();

        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示：该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
//            curFragmentVisible();
        } else {
            isUIVisible = false;
//            curFragmentInVisible();
        }
        // 在viewpager中，创建fragment时就会调用这个方法，但这时还没有resume，为了避免重复调用visible和invisible，
        // 只有当fragment状态是resumed并且初始化完毕后才进行visible和invisible的回调
        if (isResumed() && change) {
            if (getUserVisibleHint()) {
                curFragmentVisible();
            } else {
                curFragmentInVisible();
            }
        }
    }
    /**
     * 当使用show/hide方法时，会触发此回调
     *
     * @param hidden fragment是否被隐藏
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            curFragmentInVisible();
        } else {
            curFragmentVisible();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // onResume并不代表fragment可见
        // 如果是在viewpager里，就需要判断getUserVisibleHint，不在viewpager时，getUserVisibleHint默认为true
        // 如果是其它情况，就通过isHidden判断，因为show/hide时会改变isHidden的状态
        // 所以，只有当fragment原来是可见状态时，进入onResume就回调onVisible
        if (getUserVisibleHint() && !isHidden()) {
            curFragmentVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // onPause时也需要判断，如果当前fragment在viewpager中不可见，就已经回调过了，onPause时也就不需要再次回调onInvisible了
        // 所以，只有当fragment是可见状态时进入onPause才加调onInvisible
        if (getUserVisibleHint() && !isHidden()) {
            curFragmentInVisible();
        }
    }
    private void lazyLoad() {
        //这里进行双重标记判断，是因为setUserVisibleHint会多次回调，并且会在onCreateView执行前回调，必须确保onCreateView加载完毕且页面可见，才加载数据
        if (isViewCreated && isUIVisible) {
            initData();
            curFragmentVisible();
            //数据加载完毕，恢复标记，防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    protected boolean enableEventBus(){
        return false;
    }

    protected abstract P createPresenter();
    /**
     * 懒加载数据
     */
    protected void initData() {
    }
    /**
     * 当前Fragment可见
     */
    protected void curFragmentVisible() {
    }

    /**
     * 当前Fragment不可见
     */
    protected void curFragmentInVisible() {
    }
    @Override
    public void showProgress() {
        LoadingUtils.showProgressDialog((Activity) mContent,"加载中...");
    }

    @Override
    public void hideProgress() {
        LoadingUtils.cancelProgressDialog();
    }
}