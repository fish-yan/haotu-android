package com.dmeyc.dmestoreyfm.newui.menu.policy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;

/**
 * create by cxg on 2019/11/29
 */
public class MyPolicyActivity extends BaseTabNewActivity<IBaseView, BasePresenter<IBaseView>> {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MyPolicyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        PolicyFragment policyFragment1 = new PolicyFragment();
//        Bundle bundle1 = new Bundle();
//        bundle1.putString(SPKey.TYPE_POLICY, PolicyFragment.TYPE_ALL);
//        policyFragment1.setArguments(bundle1);
//        mFragmentLists.add(policyFragment1);
        PolicyFragment policyFragment2 = new PolicyFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(SPKey.TYPE_POLICY, PolicyFragment.TYPE_ME);
        policyFragment2.setArguments(bundle2);
        mFragmentLists.add(policyFragment2);
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
//        mTitleLists.add("全部");
        mTitleLists.add("我自己");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_my_policy;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("我的保单");
    }

    @Override
    protected void initData() {
        super.initData();
        mTabLayout.setVisibility(View.GONE);

    }
}
