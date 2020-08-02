package com.dmeyc.dmestoreyfm.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.H5LibAdapter;
import com.dmeyc.dmestoreyfm.adapter.H5PicRecycleViewAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.H5TeampBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.tempdata.SpacesItemDecoration;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;

public class H5PicLibActivty extends BaseActivity {
    @Bind(R.id.rl_pic)
    RecyclerView rl_pic;
//    @Bind(R.id.gridview_image)
//    GridView gridview_image;
    H5LibAdapter recycleViewAdapter;
    private ArrayList<String> dataList;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_h5piclib;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        dataList = new ArrayList<String>();
        GridLayoutManager myLayoutManager = new GridLayoutManager(this, 2);
        rl_pic.setLayoutManager(myLayoutManager);

        getData();
    }
    H5TeampBean h5TeampBean;
    public void getData(){

        RestClient.getYfmNovate(H5PicLibActivty.this).get(Constant.API.YFM_GETH5TEAMP, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("isPk", getIntent().getStringExtra("ispk"))
                .build(), new DmeycBaseSubscriber<H5TeampBean>() {
            @Override
            public void onSuccess(H5TeampBean bean) {
                ToastUtil.show(bean.getMsg());
                h5TeampBean=bean;
                for (int i=0;i<bean.getData().size();i++){
                    dataList.add(bean.getData().get(i).getImgUrl());
                }

//                recycleViewAdapter  =new H5LibAdapter(H5PicLibActivty.this,dataList,getIntent().getIntExtra("teampid",-1));
                recycleViewAdapter  =new H5LibAdapter(H5PicLibActivty.this,bean,getIntent().getIntExtra("teampid",-1));
                rl_pic.addItemDecoration(new SpacesItemDecoration(15));
                rl_pic.setAdapter(recycleViewAdapter);
//                recycleViewAdapter.notifyDataSetChanged();
            }});
    }
}
