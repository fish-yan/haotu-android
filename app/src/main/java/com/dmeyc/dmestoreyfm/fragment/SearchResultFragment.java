package com.dmeyc.dmestoreyfm.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.GoodsAdaper;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.ChooseClothBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.decoration.SpaceItemDecoration;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by jockie on 2017/9/5
 * Email:jockie911@gmail.com
 */

public class SearchResultFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    @Bind(R.id.ll_empty)
    LinearLayout llEmpty;
    private GoodsAdaper mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search_resutl;
    }

    @Override
    protected void initData() {
        mAdapter = new GoodsAdaper(getActivity(), R.layout.item_lv_gv_item_single,new ArrayList<GoodsBean>());
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(false));
        llEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(View view) {

    }

    public void search(String keyword) {
        mAdapter.clear();
        RestClient.getNovate(getActivity()).get(Constant.API.PRODUCT_SEARCH, new ParamMap.Build().addParams("keyword", keyword).build(), new DmeycBaseSubscriber<ChooseClothBean>(getActivity()) {
            @Override
            public void onSuccess(ChooseClothBean bean) {
                if(bean.getData() == null || bean.getData().getGoods() == null || bean.getData().getGoods().size() == 0){
                    mAdapter.clear();
                    llEmpty.setVisibility(View.VISIBLE);
                }else{
                    llEmpty.setVisibility(View.GONE);
                    mAdapter.addRefreshData(bean.getData().getGoods());
                }
            }
        });
    }
}
