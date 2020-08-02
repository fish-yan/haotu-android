package com.dmeyc.dmestoreyfm.video.systeminfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.video.webh5.H5Activity;
import com.dmeyc.dmestoreyfm.wedgit.PullRecyclerView;
import com.tamic.novate.Throwable;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemInfoActivity extends BaseActivity {

    @Bind(R.id.infoRv)
    PullRecyclerView infoRv;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private int page = 1;

    private List<SystemInfoModel.DataBean> data = new ArrayList<>();
    private InfoAdapter adapter;
    private int type;

    public static void newIntent(Activity activity, int type) {
        Intent intent = new Intent(activity, SystemInfoActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_system_info;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        // 绑定布局管理器
        infoRv.getRecyclerView().setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        infoRv.getRecyclerView().setItemAnimator(null);
        infoRv.setOutSwipeRefreshLayout(mSwipeRefreshLayout);
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            title.setText(getResources().getString(R.string.system_info));
            getSystemInfo(1, page);
        } else {
            title.setText(getResources().getString(R.string.activity_info));
            getSystemInfo(2, page);
        }
        adapter = new InfoAdapter(this, R.layout.item_system_info, data);
        infoRv.setAdapter(adapter);
        //下拉刷新
        infoRv.setPullDownRefreshListener(new PullRecyclerView.PullDownRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新时不清空数据，如果返回为空，则还显示原来的数据
                page = 1;
                if (type == 1) {
                    title.setText(getResources().getString(R.string.system_info));
                    getSystemInfo(1, page);
                } else {
                    title.setText(getResources().getString(R.string.activity_info));
                    getSystemInfo(2, page);
                }
            }
        });
        //上拉加载
        infoRv.setPullUpLoadMoreListener(new PullRecyclerView.PullUpLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                if (type == 1) {
                    title.setText(getResources().getString(R.string.system_info));
                    getSystemInfo(1, page);
                } else {
                    title.setText(getResources().getString(R.string.activity_info));
                    getSystemInfo(2, page);
                }
            }
        });
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                readMsg(data.get(position).getId(), position);
                H5Activity.newIntent(SystemInfoActivity.this, data.get(position).getUrl());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void getSystemInfo(int type, final int page) {

        RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + "/api/v1.0/msg/user/listMsgUser", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("type", type)
                .addParams("page", page)
                .addParams("size", "20")
                .build(), new DmeycBaseSubscriber<SystemInfoModel>() {
            @Override
            public void onSuccess(SystemInfoModel gensign) {
                if (gensign != null && gensign.getData() != null) {
                    if (page == 1) {
                        data.clear();
                    }
                    infoRv.setPullDownRefreshCompleted();
                    infoRv.setPullUpLoadMoreCompleted();
                    data.addAll(gensign.getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void readMsg(int id, final int psoi) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/msg/user/readMsgUser", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("id", id)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    data.get(psoi).setIsRead(1);
                    adapter.notifyItemChanged(psoi);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    @OnClick(R.id.back)
    public void onClick() {
        onBackPressed();
    }

    private class InfoAdapter extends CommonAdapter<SystemInfoModel.DataBean> {

        public InfoAdapter(Context context, int layoutId, List<SystemInfoModel.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, SystemInfoModel.DataBean dataBean, int position) {
            ImageView logo = holder.getConvertView().findViewById(R.id.logo);
            if (type == 1) {
                logo.setImageResource(R.drawable.icon_system_info);
            } else {
                logo.setImageResource(R.drawable.icon_activity_not);
            }
            if (!TextUtils.isEmpty(dataBean.getTitle())) {
                holder.setText(R.id.title, dataBean.getTitle());
            }
            if (!TextUtils.isEmpty(dataBean.getContent())) {
                holder.setText(R.id.content, dataBean.getContent());
            }
            if (!TextUtils.isEmpty(dataBean.getCreateTime())) {
                holder.setText(R.id.createTime, dataBean.getCreateTime());
            }
            View isRead = holder.getConvertView().findViewById(R.id.isRead);
            if (dataBean.getIsRead() == 0) {
                isRead.setVisibility(View.VISIBLE);
            } else {
                isRead.setVisibility(View.GONE);
            }
        }
    }
}
