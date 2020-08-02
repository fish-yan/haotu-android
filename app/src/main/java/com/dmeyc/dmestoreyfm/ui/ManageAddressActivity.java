package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.AddressAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.AddressBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.wedgit.CustomDialog;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ManageAddressActivity extends BaseActivity {

    public static final int REQUEST_ADD_ADDRESS = 0;   //添加新地址
    public static final int REQUEST_EDIT_ADDRESS = 1;  //编辑地址

    private boolean mIsForResult;
    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    private AddressAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_manage_address;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvRightTitle.setText("+新增地址");
        mIsForResult = getIntent().getBooleanExtra(Constant.Config.IS_FOR_RESULT,false);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(this, R.layout.item_rv_address,new ArrayList<AddressBean.DataBean.ReceiverBean>());
        mRecycleView.setAdapter(adapter);

        RestClient.getNovate(this).get(Constant.API.SHOW_RECEIVER_ADDRESS, new ParamMap.Build().build(), new DmeycBaseSubscriber<AddressBean>(this) {
            @Override
            public void onSuccess(AddressBean bean) {
                adapter.addData(bean.getData().getReceiver());
            }
        });

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, final int position) {
                if(mIsForResult){
                    AddressBean.DataBean.ReceiverBean item = adapter.getItem(position);
                    Intent intent = new Intent();
                    intent.putExtra("item",item);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    new CustomDialog(ManageAddressActivity.this).builder()
                            .setMsg("删除该条地址吗?")
                            .setNegativeButton("",null)
                            .setPositiveButton("", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    RestClient.getNovate(ManageAddressActivity.this).get(Constant.API.DELETE_RECEIVER_ADDRESS, new ParamMap.Build()
                                            .addParams("areaIds",adapter.getItem(position).getId()).build(), new DmeycBaseSubscriber<CommonBean>() {
                                        @Override
                                        public void onSuccess(CommonBean bean) {
                                            adapter.delete(position);
                                        }
                                    });
                                }
                            }).show();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @OnClick({R.id.tv_right_title_bar})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right_title_bar:
                startActivityForResult(new Intent(ManageAddressActivity.this,EditAddressActivity.class),REQUEST_ADD_ADDRESS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            AddressBean.DataBean.ReceiverBean address = data.getParcelableExtra("item");
            if(requestCode == REQUEST_ADD_ADDRESS){
//                adapter.addData(address);
                RestClient.getNovate(this).get(Constant.API.SHOW_RECEIVER_ADDRESS, new ParamMap.Build().build(), new DmeycBaseSubscriber<AddressBean>(this) {
                    @Override
                    public void onSuccess(AddressBean bean) {
                        adapter.addRefreshData(bean.getData().getReceiver());
                    }
                });
            }else if(requestCode == REQUEST_EDIT_ADDRESS){
                adapter.replaceData(data.getIntExtra("position",0),address);
            }
        }
    }
}
