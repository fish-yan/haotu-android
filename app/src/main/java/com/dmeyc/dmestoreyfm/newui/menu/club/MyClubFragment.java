package com.dmeyc.dmestoreyfm.newui.menu.club;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.MyClubAdapter;
import com.dmeyc.dmestoreyfm.bean.response.MyClubBean;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.menu.club.clubdetail.MyClubDetailActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/29
 */
public class MyClubFragment extends BaseRefreshFragment<IMyClubView,MyClubPresenter, MyClubBean.DataBean, MyClubAdapter> implements IMyClubView {
    @Override
    protected MyClubAdapter getAdapter() {
        return new MyClubAdapter();
    }

    @Override
    protected MyClubPresenter createPresenter() {
        return new MyClubPresenter();
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyClubDetailActivity.newInstance(mContent,mData.get(position).getGroup_id(), "1");
            }
        });



        mPresenter.httpRequestData();

    }

    @Override
    protected void initData() {
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("status","1");//（1、我创建的2、我关注的、3我加入的0、全部）
        return params;
    }
}
