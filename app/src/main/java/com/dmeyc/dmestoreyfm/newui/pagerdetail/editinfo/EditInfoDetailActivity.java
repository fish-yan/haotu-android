package com.dmeyc.dmestoreyfm.newui.pagerdetail.editinfo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

/**
 * create by cxg on 2019/12/21
 */
public class EditInfoDetailActivity extends BaseActivity {
    public static final String TYPE_NAME = "1";
    public static final String TYPE_MARK = "2";

    private String mType;

    @Bind(R.id.tv_tip)
    TextView mTip;
    @Bind(R.id.et_content)
    EditText mEtContent;

    public static void newInstance(Context context, String type, String content) {
        Intent intent = new Intent(context, EditInfoDetailActivity.class);
        intent.putExtra(ExtraKey.TYPE_FROM, type);
        intent.putExtra(ExtraKey.CONTENT, content);
        context.startActivity(intent);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_edit_info_detail;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mType = getIntent().getStringExtra(ExtraKey.TYPE_FROM);
        String content = getIntent().getStringExtra(ExtraKey.CONTENT);
        String tip = "";
        String title = "";
        switch (mType) {
            case TYPE_NAME:
                title = "修改名字";
                tip = "我的名字";
                break;
            case TYPE_MARK:
                title = "修改签名";
                tip = "我的签名";
                break;
            default:
        }
        mTip.setText(tip);
        setTitleWithRightText(title, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyEvent.EditInfo event = new MyEvent.EditInfo();
                switch (mType) {
                    case TYPE_NAME:
                        event.setType(MyEvent.EditInfo.TYPE_NAME);
                        event.setName(mEtContent.getText().toString());
                        break;
                    case TYPE_MARK:
                        event.setType(MyEvent.EditInfo.TYPE_MARK);
                        event.setMark(mEtContent.getText().toString());
                        break;
                    default:
                }
                EventBus.getDefault().post(event);
                finish();
            }
        });
        if (content != null) {
            mEtContent.setText(content);
        }


    }
}
