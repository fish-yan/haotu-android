package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.TailorRecordAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.RecordBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.photo.FrontTailorActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.ResponseBody;

public class TailorRecordActivity extends BaseActivity {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    private boolean mIsForResult;   //点击事件是否是startActivityForResult
    private TailorRecordAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_tailor_record;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvRightTitle.setText("开始量身");
        tvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TailorRecordActivity.this, FrontTailorActivity.class);
                startActivity(intent);
            }
        });
        mIsForResult = getIntent().getBooleanExtra(Constant.Config.IS_FOR_RESULT,false);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TailorRecordAdapter(TailorRecordActivity.this, R.layout.item_rv_tailor_record, new ArrayList());
        mRecycleView.setAdapter(mAdapter);

        String mobile = SPUtils.getStringData(Constant.Config.MOBILE, "");
        RestClient.getNovate(this, Constant.API.TBASE_URL).get(Constant.API.TAILOR_RECORD, new ParamMap.Build()
                .addParams(Constant.Config.MOBILE, mobile)
                .addParams("string", Util.MD5(mobile + Constant.Config.MD5_KEY + mobile)).build(), new DmeycBaseSubscriber<RecordBean>(this) {
            @Override
            public void onNext(ResponseBody t) {
                try {
                    JSONObject object = new JSONObject(t.string());
                    if(object.has("iserror")){
                        String code = object.getString("iserror");
                        if(TextUtils.equals("0",code)){
                            RecordBean recordBean = new GsonTools().changeGsonToBean(object.toString(), RecordBean.class);
                            onSuccess(recordBean);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(RecordBean bean) {
                if(Util.objEmpty(bean.getData()))
                    return;
                mAdapter.addData(bean.getData());
            }
        });

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(mIsForResult){   //点击需要返回
                    Intent intent = new Intent();
                    intent.putExtra(Constant.Config.ITEM, mAdapter.getItem(position).getName());
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Intent intent = new Intent(TailorRecordActivity.this, TailorDetailActivity.class);
                    intent.putExtra(Constant.Config.ITEM,mAdapter.getItem(position));
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}
