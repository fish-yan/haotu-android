package com.dmeyc.dmestoreyfm.ui;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.BaseBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import butterknife.Bind;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity {

    @Bind(R.id.et_content)
    EditText etContent;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_advice_back;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_submit})
    public void onClick(View view){
        if(view.getId() == R.id.tv_submit){
            if(TextUtils.isEmpty(etContent.getText().toString())){
                SnackBarUtil.showShortSnackbar(etContent,"请输入要反馈的内容");
                return;
            }

            RestClient.getNovate(this).post(Constant.API.FEED_BACK, new ParamMap.Build()
                    .addParams("opinion",etContent.getText().toString())
                    .addParams("version", Util.getLocalVersionName(this))
                    .addParams("system","Android")
                    .build(), new DmeycBaseSubscriber<BaseBean>(this) {
                @Override
                public void onSuccess(BaseBean bean) {
                    SnackBarUtil.showShortSnackbar(etContent,"感谢您的反馈");
                    handler.sendEmptyMessageDelayed(0,1500);
                }
            });
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            finish();
        }
    };
}
