package com.dmeyc.dmestoreyfm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */

public abstract class BaseFragment extends Fragment  /*implements LazyFragmentPagerAdapter.Laziable*/{

    protected View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutRes(), null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initData(view);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
    }
    protected abstract int getLayoutRes();
    protected abstract void initData();
    protected abstract void initData(View view);
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    //colorPrimaryDark
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    protected void setStatusBarTranslucent() {
//        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
//                !(this instanceof HomesFragment /*|| this instanceof PhotoActivity
//                        || this instanceof PhotoDetailActivity*/))
//                || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
//                && this instanceof HomeFragment)) {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//          SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.color.colorPrimary);
//        }
//    }
}
