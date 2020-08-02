package com.dmeyc.dmestoreyfm.video.dynamicdetail.digs;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.wedgit.PullRecyclerView;
import java.util.ArrayList;

import butterknife.Bind;

public class DigsActivity extends BaseActivity{

    public static void newIntent(Activity activity){
        Intent intent = new Intent(activity,DigsActivity.class);
        activity.startActivity(intent);
    }

    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.rv_digs)
    PullRecyclerView rv_digs;
    @Bind(R.id.tv_title)
    TextView tv_title;
    private ArrayList<DigsUserBean> mListData = new ArrayList<>();
    private DigsAdapter mAdapter;
    @Override
    protected int getLayoutRes() {
        return R.layout.ac_digs_layout;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData(){
        tv_title.setText("点赞");
        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new DigsAdapter(DigsActivity.this,R.layout.item_dynamic_digs,mListData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DigsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_digs.getRecyclerView().setLayoutManager(layoutManager);
        rv_digs.setAdapter(mAdapter);
        mAdapter.setOnFollowClickListener(new DigsAdapter.OnFollowClickListener() {
            @Override
            public void OnFollowClick(int position) {
                // 关注

            }
        });
        rv_digs.setOutSwipeRefreshLayout(mSwipeRefreshLayout);
        //下拉刷新
        rv_digs.setPullDownRefreshListener(new PullRecyclerView.PullDownRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新时不清空数据，如果返回为空，则还显示原来的数据

            }
        });
        //上拉加载
        rv_digs.setPullUpLoadMoreListener(new PullRecyclerView.PullUpLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }
}
