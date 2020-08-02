package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.NewWhoPkBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.Bind;

public class PkingActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {

    @Bind(R.id.lv_pkinglist)
    ListView recycleview_pking;

    @Bind(R.id.smartRl)
    SmartRefreshLayout smartRl;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pking;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        smartRl.setOnLoadmoreListener(this);
        smartRl.setOnRefreshListener(this);
        smartRl.setEnableLoadmore(true);
        smartRl.setEnableRefresh(true);
        recycleview_pking.setAdapter(mAdapter);
        recycleview_pking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(PkingActivity.this, NewPkResultActivity.class)
                        .putExtra("activityid",mListData.get(i).getActivity_a_id())
                        .putExtra("isgover",mListData.get(i).getIsGovernment())
                        .putExtra("groverpkid",mListData.get(i).getGroup_pk_id()));
            }
        });
        getData();
    }

    private int mPage = 1;

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPage++;
        getData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPage = 1;
        getData();
    }

    private ArrayList<NewWhoPkBean.DataBean> mListData = new ArrayList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void getData(){
        RestClient.getYfmNovate(PkingActivity.this).post(Constant.API.YFM_PKLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY))
                .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE))
                .addParams("pageIndex", mPage)
                .addParams("pageSize", 20)
                .addParams("status", 1)
                .addParams("project_type", 1)
                .build(), new DmeycBaseSubscriber<NewWhoPkBean>(){
            @Override
            public void onSuccess(NewWhoPkBean bean) {
                smartRl.finishRefresh();
                smartRl.finishLoadmore();
                if(mPage == 1){
                    mListData.clear();
                }
                if(bean != null && bean.getData() != null && bean.getData().size() > 0 ){
                    mListData.addAll(bean.getData());
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        PkingViewHorder viewHorder;
        @Override
        public int getCount() {
            return mListData.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_pkingitem,null);
                viewHorder=new PkingViewHorder(view);
                view.setTag(viewHorder);
            }else {
                viewHorder=(PkingViewHorder) view.getTag();
            }
            GlideUtil.loadImage(PkingActivity.this,mListData.get(i).getGroup_a_logo(),viewHorder.iv_teamone);
            GlideUtil.loadImage(PkingActivity.this,mListData.get(i).getGroup_b_logo(),viewHorder.iv_teamtwo);
            viewHorder.tv_teantwonaem.setText(mListData.get(i).getGroup_b_name());
            viewHorder.tv_teamonename.setText(mListData.get(i).getGroup_a_name());
            String daysplide=mListData.get(i).getEnd_time().split(" ")[0];
            String endday=daysplide.split("-")[1]+"月"+daysplide.split("-")[2]+"日";
            viewHorder.item_tv_date.setText(endday);
            viewHorder.tv_cubnam.setText(mListData.get(i).getGroup_pk_name());
            viewHorder.tv_socker.setText(mListData.get(i).getGroupAScores()+":"+mListData.get(i).getGroupBScores());
            return view;
        }
    };

    public class PkingViewHorder{
        CircleImageView iv_teamone,iv_teamtwo;
        TextView tv_teamonename,tv_teantwonaem,item_tv_date,tv_cubnam,tv_socker;
        public PkingViewHorder(View view){
            iv_teamone=view.findViewById(R.id.iv_teamone);
            iv_teamtwo=view.findViewById(R.id.iv_teamtwo);
            tv_teamonename=view.findViewById(R.id.tv_teamonename);
            tv_teantwonaem=view.findViewById(R.id.tv_teantwonaem);
            item_tv_date=view.findViewById(R.id.item_tv_date);
            tv_cubnam=view.findViewById(R.id.tv_cubnam);
            tv_socker=view.findViewById(R.id.tv_socker);
        }
    }
}
