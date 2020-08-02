package com.dmeyc.dmestoreyfm.newui.common.living;

import com.dmeyc.dmestoreyfm.adapter.LivingAdapter;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;

import java.util.Map;

/**
 * create by cxg on 2019/12/30
 * 直播列表
 */
public class LivingFragment extends BaseRefreshFragment<ILivingFmView,LivingFmPresenter, UserListBean.DataBean, LivingAdapter> implements ILivingFmView {
    @Override
    protected LivingAdapter getAdapter() {
        return new LivingAdapter(getContext(),null);
    }

    @Override
    protected LivingFmPresenter createPresenter() {
        return new LivingFmPresenter();
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected void initData() {
        mPresenter.httpRequestData();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }
}
