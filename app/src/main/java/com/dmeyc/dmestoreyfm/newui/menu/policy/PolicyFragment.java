package com.dmeyc.dmestoreyfm.newui.menu.policy;

import android.os.Bundle;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.PolicyAdapter;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.ui.ProdListBean;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/29
 */
public class PolicyFragment extends BaseRefreshFragment<IPolicyView, PolicyPresenter, ProdListBean.DataBean, PolicyAdapter> implements IPolicyView {
    public static final String TYPE_ALL = "0";//全部
    public static final String TYPE_ME = "";//我的 todo 我的type是多少

    public String mType = TYPE_ALL;

    @Override
    protected PolicyAdapter getAdapter() {
        return new PolicyAdapter();
    }

    @Override
    protected PolicyPresenter createPresenter() {
        return new PolicyPresenter();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        Bundle b = getArguments();
        if (b != null) {
            mType = b.getString(SPKey.TYPE_POLICY, TYPE_ALL);
        }
        mAdapter.setEmptyView(R.layout.view_empty_data);
    }

    @Override
    protected void initData() {
        mPresenter.httpRequest();
    }


    @Override
    public Map<String, String> getParams() {
        Map<String,String> params = new HashMap<>();
        params.put("pageIndex",String.valueOf(mPageNo));
        params.put("pageSize",PAGE_SIZE);
        params.put("type", mType);
        return params;
    }



    @Override
    public void requestData() {
        mPresenter.httpRequest();
    }


}
