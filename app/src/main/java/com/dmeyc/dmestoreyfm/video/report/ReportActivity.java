package com.dmeyc.dmestoreyfm.video.report;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.releasedynamic.GenSignBean;
import com.dmeyc.dmestoreyfm.video.topic.TopicListActivity;
import com.dmeyc.dmestoreyfm.video.topic.TopicListBean;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.tamic.novate.Throwable;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ReportActivity extends BaseActivity {

    public static void newIntent(Activity activity,String id){
        Intent intent = new Intent(activity,ReportActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.lv_report)
    NoScrollListView mLivReport;

    @Bind(R.id.et_content)
    EditText mEtContent;

    @OnClick(R.id.tv_commit)
    void toCommitReprotClick(){
        //
        LoadingUtils.showProgressDialog(ReportActivity.this,"举报中...");
        toReport();
    }

    private ReportAdapter mAdapter;
    private ArrayList<ReportBean.DataBean> mListData = new ArrayList<>();

    private String dynamicId;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_report;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        dynamicId = getIntent().getStringExtra("id");
        tv_title.setText("投诉/举报");
        mAdapter = new ReportAdapter(ReportActivity.this,R.layout.item_report,mListData);
        mLivReport.setAdapter(mAdapter);
        getReportList();

        mLivReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for(int i = 0 ; i < mListData.size() ; i++){
                    mListData.get(i).setSelect(false);
                }
                mListData.get(position).setSelect(true);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getReportList() {
        final ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        RestClient.getYfmNovate(ReportActivity.this).get(Constant.API.YFM_GET_TYPE_OF_REPORT, mParams.build(),
                new DmeycBaseSubscriber<ReportBean>() {
                    @Override
                    public void onSuccess(ReportBean bean) {
                        //
                        mListData.clear();
                        if(bean.getData() != null && bean.getData().size() > 0){
                            mListData.addAll(bean.getData());
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void toReport() {
        int mSelectIndex = -1;
        for(int i = 0 ; i < mListData.size() ; i++){
            ReportBean.DataBean bean = mListData.get(i);
            if(bean.isSelect()){
                mSelectIndex = i;
            }
        }
        if(mSelectIndex == -1){
            ToastUtil.show("请选择您要投诉的类型");
            return;
        }
        String content = mEtContent.getText().toString().trim();
        final ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("content_type", 1);
        mParams.addParams("type", mListData.get(mSelectIndex).getId());
        mParams.addParams("content_id",dynamicId);
        mParams.addParams("content",content);
        RestClient.getYfmNovate(ReportActivity.this).post(Constant.API.YFM_TO_REPORT, mParams.build(),
                new DmeycBaseSubscriber<GenSignBean>() {
                    @Override
                    public void onSuccess(GenSignBean bean) {
                        //
                        LoadingUtils.cancelProgressDialog();
                        ToastUtil.show("举报成功");
                        finish();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LoadingUtils.cancelProgressDialog();
                    }
                });
    }
}
