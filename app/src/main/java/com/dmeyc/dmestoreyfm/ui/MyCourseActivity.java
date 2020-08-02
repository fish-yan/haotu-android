package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CourseOrderBean;
import com.dmeyc.dmestoreyfm.bean.MyCourseListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

import butterknife.Bind;

public class MyCourseActivity extends BaseActivity {
    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_credit)
    TextView tv_credit;
    @Bind(R.id.tv_projecttype)
    TextView tv_projecttype;
    @Bind(R.id.tv_coursedution)
    TextView tv_coursedution;
    @Bind(R.id.tv_courtime)
    TextView tv_courtime;
    @Bind(R.id.tv_sernum)
    TextView tv_sernum;
    @Bind(R.id.tv_valnum)
    TextView tv_valnum;
    @Bind(R.id.iv_erwem)
    ImageView iv_erwem;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mycourse;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
         setData();
    }

    private void setData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_COURSEORDER, new ParamMap.Build()
                .addParams("user_token", "b1fe67bb673049b6991be92966e99ce9")
                .addParams("order_id", getIntent().getStringExtra("orderid"))
                .build(), new DmeycBaseSubscriber<CourseOrderBean>() {
            @Override
            public void onSuccess(final CourseOrderBean bean) {
                ToastUtil.show(bean.getMsg());
                GlideUtil.loadImage(MyCourseActivity.this,bean.getData().getCoach_logo(),civ_avatar);
                tv_credit.setText(bean.getData().getCoach_name());
                tv_projecttype.setText("项目："+bean.getData().getProject_type());
                tv_coursedution.setText("课时"+bean.getData().getDuration()+"小时");
                tv_courtime.setText(bean.getData().getPaid_time());
                tv_sernum.setText("序列号："+bean.getData().getSequence_no());
                tv_valnum.setText("验证码："+bean.getData().getSequence_no());
                GlideUtil.loadImage(MyCourseActivity.this,bean.getData().getQr_code_url(),iv_erwem);

//                timerTaskTextView.startTimer();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
