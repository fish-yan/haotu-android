package com.dmeyc.dmestoreyfm.newui.release.friend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;

/**
 * create by cxg on 2019/11/30
 */
public class AtFriendActivity extends BaseTabNewActivity<IBaseView, BasePresenter<IBaseView>> {
    public static final String TYPE_RELEASE = "TYPE_RELEASE";
    public static final String TYPE_COMMENT = "TYPE_COMMENT";

    public static void newInstance(Context context, String type) {
        Intent intent = new Intent(context, AtFriendActivity.class);
        intent.putExtra(ExtraKey.TYPE_FROM, type);
        context.startActivity(intent);
    }

    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mType = getIntent().getStringExtra(ExtraKey.TYPE_FROM);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.add(new AtFriendFragment());
        if (!isFromComment()) {
            mFragmentLists.add(new ContactFragment());
        }
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("好友");
        mTitleLists.add("通讯录");
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
        return R.layout.activity_at_friend;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("我的好友");
    }

    @Override
    protected void initData() {
        super.initData();
        if (isFromComment()) {
            mTabLayout.setVisibility(View.GONE);
        }
    }

    private boolean isFromComment() {
        return TYPE_COMMENT.equals(mType);
    }
}
