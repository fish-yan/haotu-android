package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class MacthRoleFragment extends BaseFragment {
    @Bind(R.id.tv_intro)
    TextView tv_intro;
    private String remank;
    public MacthRoleFragment(String remank){
        this.remank=remank;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_matchrole;
    }

    @Override
    protected void initData() {
        tv_intro.setText(remank);
    }

    @Override
    protected void initData(View view) {

    }

    public void setData(String remake){

    }
}
