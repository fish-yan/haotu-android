package com.dmeyc.dmestoreyfm.newui.menu.club.notice;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * create by cxg on 2019/11/30
 */
public class NoticeActivity extends BaseMvpActivity<INoticeView, NoticePresenter> implements INoticeView {
    @Bind(R.id.et_notice)
    EditText mEtNotice;

    private String mGroupId;

    public static void newInstance(Context context, String preNotice, String groupId, String mGroupOwnerType) {
        Intent intent = new Intent(context, NoticeActivity.class);
        intent.putExtra(ExtraKey.NOTICE, preNotice);
        intent.putExtra(ExtraKey.GROUP_ID, groupId);
        intent.putExtra(ExtraKey.GROUP_ACTION_FROM, mGroupOwnerType);
        context.startActivity(intent);
    }

    @Override
    protected NoticePresenter createPresenter() {
        return new NoticePresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_notice;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        if (CommonConfig.isGroupOwner(getIntent().getStringExtra(ExtraKey.GROUP_OWNER))) {
            setTitleWithRightText("俱乐部群公告", "发布", new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(mEtNotice.getText().toString())) {
                        ToastUtil.show("请输入俱乐部群公告请输入俱乐部群公告");
                    } else {
                        mPresenter.httpRequestData();
                    }
                }
            });
        } else {
            setTitle("俱乐部群公告");
            mEtNotice.setEnabled(false);
        }

        String notice = getIntent().getStringExtra(ExtraKey.NOTICE);
        if (notice != null) {
            mEtNotice.setText(notice);
        }
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("group_id", mGroupId);
        params.put("notice", mEtNotice.getText().toString());
        return params;
    }

    @Override
    public void httpDataSucc() {
        ToastUtil.show("发布成功");
        EventBus.getDefault().post(new MyEvent.UpdateNotice(mEtNotice.getText().toString()));
        finish();
    }
}
