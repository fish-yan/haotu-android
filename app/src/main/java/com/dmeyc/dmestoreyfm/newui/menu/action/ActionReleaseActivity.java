package com.dmeyc.dmestoreyfm.newui.menu.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;

import java.util.List;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionFragment;
import com.dmeyc.dmestoreyfm.newui.home.video.VideoHomeFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * create by cxg on 2019/12/6
 */
public class ActionReleaseActivity extends BaseTabNewActivity<IBaseView, BasePresenter<IBaseView>> {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ActionReleaseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        GroupActionFragment groupActionFragment = GroupActionFragment.newInstance(GroupActionFragment.FROM_MENU_ACTION);
        GroupActionFragment groupMatchFragment = GroupActionFragment.newInstance(GroupActionFragment.FROM_MENU_MATCH);

        mFragmentLists.add(groupActionFragment);
        mFragmentLists.add(groupMatchFragment);
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("活动");
        mTitleLists.add("赛事");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_action_release;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("我的发布");
    }
}
