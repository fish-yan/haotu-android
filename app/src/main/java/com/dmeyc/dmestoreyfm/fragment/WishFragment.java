package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.BaseRvAdapter;
import com.dmeyc.dmestoreyfm.adapter.GoodsAdaper;
import com.dmeyc.dmestoreyfm.adapter.WishBrandAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.WishBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.decoration.SpaceItemDecoration;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jockie on 2017/11/29
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class WishFragment extends BaseRefreshFragment<WishBean, BaseRvAdapter> {

    @Bind(R.id.ll_empty)
    LinearLayout relEmpty;
    @Bind(R.id.empty_text)
    TextView emptyText;
    @Bind(R.id.empty_click)
    TextView emptyClick;
    public final static int TYPE_PRODUCT = 1;
    public final static int TYPE_BRAND = 2;
    @Bind(R.id.smartRl)
    SmartRefreshLayout smartRl;

    private int type;

    @SuppressLint("ValidFragment")
    public WishFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void initData() {
        super.initData();
        if (getType() == TYPE_BRAND) {
            emptyText.setText("您还没有收藏心仪的单品哟,快去将它们保存到这里吧~");
            emptyClick.setText("添加单品");
        } else {
            emptyText.setText("您还没有收藏心仪的品牌哟,快去将它们保存到这里吧~");
            emptyClick.setText("添加品牌");
        }

        emptyClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                if (getType() == TYPE_BRAND) {
                    intent.putExtra(Constant.Config.POSITION, 1);
                } else {
                    intent.putExtra(Constant.Config.POSITION, 1);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected BaseRvAdapter getAdapter() {
        if (getType() == TYPE_PRODUCT) {
            mRecycleView.addItemDecoration(new SpaceItemDecoration(false));
            return new GoodsAdaper(getActivity(), R.layout.item_lv_gv_item_single, new ArrayList());
        } else {
            return new WishBrandAdapter(getActivity(), R.layout.item_rv_attention_brand, new ArrayList());
        }
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        if (getType() == TYPE_PRODUCT) {
            return new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        }
        return super.getLayoutManager();
    }

    @Override
    protected WishBean parseData(String result) {
        return GsonTools.changeGsonToBean(result, WishBean.class);
    }

    @Override
    protected void setData(WishBean wishBean, boolean isRefresh) {
        if (getType() == TYPE_BRAND) {
            if (wishBean.getData() == null || Util.objEmpty(wishBean.getData().getBrandsAndDesigner())) {
                relEmpty.setVisibility(View.VISIBLE);
                adapter.clear();
            } else {
                adapter.addData(wishBean.getData().getBrandsAndDesigner(), isRefresh);
            }
        } else if (getType() == TYPE_PRODUCT) {
            if (wishBean.getData() == null || Util.objEmpty(wishBean.getData().getGoods())) {
                smartRl.setVisibility(View.GONE);
                relEmpty.setVisibility(View.VISIBLE);
                adapter.clear();
            } else {
                smartRl.setVisibility(View.VISIBLE);
                relEmpty.setVisibility(View.GONE);
                adapter.addData(wishBean.getData().getGoods(), isRefresh);
            }
        }
    }

    @Override
    protected String getUrl() {
        return Constant.API.WISH;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        return new ParamMap.Build()
                .addParams(Constant.Config.TYPE, getType())
                .build();
    }

    /**
     * type:1代表商品,2代表品牌设计师
     *
     * @return
     */
    protected int getType() {
        return type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
