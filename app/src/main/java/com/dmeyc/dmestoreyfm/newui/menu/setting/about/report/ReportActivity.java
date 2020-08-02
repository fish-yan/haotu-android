package com.dmeyc.dmestoreyfm.newui.menu.setting.about.report;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * create by cxg on 2019/11/24
 * 举报 反馈
 */
public class ReportActivity extends BaseMvpActivity<IReportView, ReportPresenter> implements IReportView {

    public static final String TYPE_FEEDBACK = "1";// 反馈
    public static final String TYPE_REPORT = "2";//投诉

    @Bind(R.id.et_content)
    EditText mEtContent;

    private String mType = "";

    public static void startActivity(Context context, String type) {
        Intent intent = new Intent(context, ReportActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected ReportPresenter createPresenter() {
        return new ReportPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_report_new;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getStringExtra("type");
        setTitleWithRightText("举报", "提交", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtContent.getText().toString())) {
                    ToastUtil.show("请输入内容");
                    return;
                }
                mPresenter.report();
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("type", mType);
        params.put("content", mEtContent.getText().toString());
        return params;
    }

    @Override
    public void httpDataSuc() {
        if (TYPE_FEEDBACK.equals(mType)) {
            ToastUtil.show("意见反馈成功");
        } else {
            ToastUtil.show("投诉提交成功");
        }
        finish();
    }
}
