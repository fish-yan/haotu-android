package com.dmeyc.dmestoreyfm.video.topic.addtopic;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

public class AddTopicActivity extends BaseActivity {

    public static void newIntent(Activity activity) {
        Intent intent = new Intent(activity, AddTopicActivity.class);
        activity.startActivity(intent);
    }

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.edit_topic)
    EditText edit_topic;

    @Bind(R.id.tv_show_count)
    TextView tv_show_count;

    @OnClick(R.id.tv_to_sure)
    void OnToSureClick() {
        //
        toAddTopic();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_add_topic;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_title.setText("创建话题");
        edit_topic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = edit_topic.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    int count = s.length();
                    tv_show_count.setText(count + "/20");
                } else {
                    tv_show_count.setText("0/20");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void toAddTopic() {
        String content = edit_topic.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show("请输入您要创建的话题");
            return;
        }
        final ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("image_url", "-1");
        mParams.addParams("content", content);
        RestClient.getYfmNovate(AddTopicActivity.this).post(Constant.API.ADD_TOPIC, mParams.build(),
                new DmeycBaseSubscriber<AddTopicBean>() {
                    @Override
                    public void onSuccess(AddTopicBean addTopicBean){
                        String topic = addTopicBean.getData().getContent();
                        EventVideoBean bean = new EventVideoBean();
                        bean.setTopic(topic);
                        bean.setTopicId(addTopicBean.getData().getId());
                        bean.setKey(Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_CREATE);
                        EventBus.getDefault().post(bean);
                        finish();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.show(e.getMessage());
                    }
                });
    }
}
