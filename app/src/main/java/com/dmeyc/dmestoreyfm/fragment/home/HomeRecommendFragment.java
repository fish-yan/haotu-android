package com.dmeyc.dmestoreyfm.fragment.home;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.RecommendAdapter;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.wedgit.pulltozoomview.PullToZoomListViewEx;

import butterknife.Bind;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

public class HomeRecommendFragment extends BaseFragment{

    @Bind(R.id.listview)
    PullToZoomListViewEx listViewEx;

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_home_recommend;
    }

    @Override
    protected void initData() {
        final RecommendAdapter adapter = new RecommendAdapter(getActivity());
        listViewEx.setAdapter(adapter);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        listViewEx.setHeaderLayoutParams(localObject);

        RestClient.getNovate(getActivity()).get(Constant.API.RECOMMEND, new ParamMap.Build().build(), new DmeycBaseSubscriber<RecommendBean>(getActivity()) {
            @Override
            public void onSuccess(RecommendBean bean) {
                adapter.addData(bean.getData());
            }
        });
    }
    @Override
    protected void initData(View view) {

    }
}
