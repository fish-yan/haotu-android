package com.dmeyc.dmestoreyfm.newbase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    private View mRootView;

    protected Context mContent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContent = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(setContentView(), null);
            ButterKnife.bind(this, mRootView);
            initViews(mRootView);
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    protected void initViews(View mRootView) {
    }

    protected abstract int setContentView();

    protected abstract void initViews();


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}