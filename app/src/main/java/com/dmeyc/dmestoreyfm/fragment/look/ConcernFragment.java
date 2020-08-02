package com.dmeyc.dmestoreyfm.fragment.look;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.FindListBean;
import com.dmeyc.dmestoreyfm.bean.SpecialBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.look.section.ConcernAdapter;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shadyY on 2018/12/3
 * 关注_Fragment
 */

public class ConcernFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener {

    protected RecyclerView contentRecycler;
    protected SmartRefreshLayout mSmartRefresh;
    private ConcernAdapter concernAdapter;

    int flag = 0;
    private List<SpecialBean.DataBean> beanList = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_concern;
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
        String useId = SPUtils.getStringData(Constant.Config.USER_ID, "2");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", useId);//未修改
        RestClient.getNovate(getActivity()).get(Constant.API.ATTEND_LIST, map, new DmeycBaseSubscriber<FindListBean>() {
            @Override
            public void onSuccess(FindListBean bean) {

            }
        });
        beanList.clear();
        SpecialBean.DataBean specialBean = new SpecialBean.DataBean();
        List<SpecialBean.DataBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            specialBean.setTitle("易经大师教你怎么穿衣");
            specialBean.setUrl("https://storeapi.91moshow.com:8078/shop-controller/special_03");
            list.add(specialBean);
        }
        beanList.addAll(list);
        closeRefresh();
        initSection();
    }

    private void initSection() {
        concernAdapter.setData(beanList, flag);
        contentRecycler.setAdapter(concernAdapter);
        if (flag != 0) {
            contentRecycler.scrollToPosition(concernAdapter.getItemCount() - beanList.size()-1);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        contentRecycler.setLayoutManager(myLayoutManager);
        concernAdapter = new ConcernAdapter(getContext());
        mSmartRefresh.setOnRefreshListener(this);
        mSmartRefresh.setOnLoadmoreListener(this);
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
