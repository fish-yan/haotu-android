package com.dmeyc.dmestoreyfm.newui.home.groupaction;

import android.os.Bundle;

import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseNewTabFragment;

import java.util.List;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.home.time.SelectorTimeFragment;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * create by cxg on 2019/12/1
 */
public class GroupActionSelectFragment extends BaseNewTabFragment {
    /**
     * tab title list
     *
     * @param mTitleLists
     */
    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("全部");
        mTitleLists.add("活动");
        mTitleLists.add("赛事");
        mTitleLists.add("时间");
    }


    /**
     * fragment list
     *
     * @param mFragmentLists
     */
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        GroupActionFragment fragment1 = GroupActionFragment.newInstance(GroupActionFragment.FROM_GROUP_ALL);
        GroupActionFragment fragment2 = GroupActionFragment.newInstance(GroupActionFragment.FROM_GROUP_ACTION);
        GroupActionFragment fragment3 = GroupActionFragment.newInstance(GroupActionFragment.FROM_GROUP_MATCH);

        mFragmentLists.add(fragment1);
        mFragmentLists.add(fragment2);
        mFragmentLists.add(fragment3);
        mFragmentLists.add(new SelectorTimeFragment());
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(15));
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_group_action;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void checkAll(MyEvent.GroupActionTabClick event) {
        mTabLayout.getTabAt(0).select();
    }
}
