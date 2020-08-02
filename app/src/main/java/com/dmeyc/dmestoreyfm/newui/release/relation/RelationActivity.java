package com.dmeyc.dmestoreyfm.newui.release.relation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionFragment;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.List;

/**
 * create by cxg on 2019/11/30
 */
public class RelationActivity extends BaseTabNewActivity<IBaseView, BasePresenter<IBaseView>> {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, RelationActivity.class);
        context.startActivity(intent);
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(15));
    }
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {

        GroupActionFragment ftGroup =GroupActionFragment.newInstance(GroupActionFragment.FROM_RELATION);
        RelationFragment ftClub = new RelationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ExtraKey.TYPE_FROM,RelationFragment.TYPE_CLUB);
        ftClub.setArguments(bundle);

        RelationFragment ftShop = new RelationFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt(ExtraKey.TYPE_FROM,RelationFragment.TYPE_SHOP);
        ftShop.setArguments(bundle1);

        RelationFragment ftCoach = new RelationFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt(ExtraKey.TYPE_FROM,RelationFragment.TYPE_COACH);
        ftCoach.setArguments(bundle2);

        mFragmentLists.add(ftGroup);
        mFragmentLists.add(ftClub);
        mFragmentLists.add(ftShop);
        mFragmentLists.add(ftCoach);
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("活动赛事");
        mTitleLists.add("俱乐部");
        mTitleLists.add("商铺");
        mTitleLists.add("教练");

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
        return R.layout.activity_relation;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("关联");
    }
}
