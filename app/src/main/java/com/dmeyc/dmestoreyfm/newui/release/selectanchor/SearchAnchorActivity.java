package com.dmeyc.dmestoreyfm.newui.release.selectanchor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * create by cxg on 2019/12/25
 */
public class SearchAnchorActivity extends BaseActivity {
    SearchAnchorFragment mFragment;
    public static void newInstance(Context context, String activityId) {
        Intent intent = new Intent(context, SearchAnchorActivity.class);
        intent.putExtra(ExtraKey.ACTIVITY_ID, activityId);
        context.startActivity(intent);
    }
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_container;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("选择主播");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mFragment = SearchAnchorFragment.newInstance(getIntent().getStringExtra(ExtraKey.ACTIVITY_ID));
        ft.replace(R.id.fm_container, mFragment).commit();
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new MyEvent.SelectorAnchor( mFragment.getCheckList()));
        super.onBackPressed();

    }
}
