package com.dmeyc.dmestoreyfm.ui.show;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.SpecialBean;
import com.dmeyc.dmestoreyfm.ui.show.section.SpecialAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SpecialFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener {

    protected RecyclerView contentRecycler;
    protected SmartRefreshLayout mSmartRefresh;
    //    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private List<SpecialBean.DataBean> beanList = new ArrayList<>();
    int flag = 0;
    private SpecialAdapter specialAdpter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initData() {
        contentRecycler = (RecyclerView) mRootView.findViewById(R.id.recycleview); //使用bind 会出现空指针
        mSmartRefresh = (SmartRefreshLayout) mRootView.findViewById(R.id.smartRl); //使用bind 会出现空指针
        initRecyclerView();
        getData();
    }

    @Override
    protected void initData(View view) {

    }


    private void getData() {
        if (flag == 0) {
            beanList.clear();
        }
        setData();
        closeRefresh();
        initSection();
    }

    private void setData() {
        SpecialBean.DataBean specialBean_1 = new SpecialBean.DataBean();
        specialBean_1.setTitle("易经大师教你怎么穿衣");
        specialBean_1.setUrl("https://storeapi.91moshow.com:8078/shop-controller/special_03");
        beanList.add(specialBean_1);

        SpecialBean.DataBean specialBean_2 = new SpecialBean.DataBean();
        specialBean_2.setTitle("你的“星”范儿全在这里了");
        specialBean_2.setUrl("https://storeapi.91moshow.com:8078/shop-controller/special_04");
        beanList.add(specialBean_2);

        SpecialBean.DataBean specialBean_3 = new SpecialBean.DataBean();
        specialBean_3.setTitle("十月精选上新品牌,满足时髦百变的你");
        specialBean_3.setUrl("https://storeapi.91moshow.com:8078/shop-controller/special_03");
        beanList.add(specialBean_3);

        SpecialBean.DataBean specialBean_4 = new SpecialBean.DataBean();
        specialBean_4.setTitle("十月精选上新品牌,满足时髦百变的你");
        specialBean_4.setUrl("https://storeapi.91moshow.com:8078/shop-controller/special_04");
        beanList.add(specialBean_4);

        SpecialBean.DataBean specialBean_5 = new SpecialBean.DataBean();
        specialBean_5.setTitle("十月精选上新品牌,满足时髦百变的你");
        specialBean_5.setUrl("https://storeapi.91moshow.com:8078/shop-controller/special_03");
        beanList.add(specialBean_5);

    }

    private void initRecyclerView() {
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        contentRecycler.setLayoutManager(myLayoutManager);
        specialAdpter = new SpecialAdapter(getContext());
        mSmartRefresh.setOnRefreshListener(this);
        mSmartRefresh.setOnLoadmoreListener(this);
    }

    private void initSection() {
        specialAdpter.setData(beanList, flag);
        contentRecycler.setAdapter(specialAdpter);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        flag = 0;
        getData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        flag++;
        getData();
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = 0;
    }

    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        if (mSmartRefresh.isRefreshing())
            mSmartRefresh.finishRefresh();
        if (mSmartRefresh.isLoading())
            mSmartRefresh.finishLoadmore();
    }
}
