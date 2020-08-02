package com.dmeyc.dmestoreyfm.newui.common.container;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionFragment;
import com.dmeyc.dmestoreyfm.newui.home.message.container.FunsFragment;
import com.dmeyc.dmestoreyfm.newui.home.message.container.MessageInfoFragment;
import com.dmeyc.dmestoreyfm.newui.menu.club.clubmember.MemberFragment;
import com.dmeyc.dmestoreyfm.newui.common.anchorlist.AnchorLivesFragment;

/**
 * create by cxg on 2019/12/18
 * 包裹内容
 */
public class ContainerActivity extends BaseActivity {
    public static final String TYPE_MESSAGE_AT_ME = "TYPE_MESSAGE_AT_ME";// 消息 @我
    public static final String TYPE_MESSAGE_LIKE = "TYPE_MESSAGE_LIKE";// 消息 赞
    public static final String TYPE_MESSAGE_COMMON = "TYPE_MESSAGE_COMMON";// 消息 评论

    public static final String TYPE_MESSAGE_FUNS = "TYPE_MESSAGE_FUNS";//消息 粉丝
    public static final String TYPE_GROUP_MEMBER = "TYPE_GROUP_MEMBER";//群成员列表

    public static final String TYPE_SELECTOR_MATCH = "TYPE_SELECTOR_MATCH";//选择赛事
    public static final String TYPE_LIVE_LIST = "TYPE_LIVE_LIST";//可直播赛事列表

    private String mType;

    public static void newInstance(Context context, String type, String title) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(ExtraKey.TYPE_FROM, type);
        intent.putExtra(ExtraKey.TITLE, title);
        context.startActivity(intent);
    }

    public static void newInstance(Context context, String type, String title, String groupId) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(ExtraKey.TYPE_FROM, type);
        intent.putExtra(ExtraKey.TITLE, title);
        intent.putExtra(ExtraKey.GROUP_ID, groupId);
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
        String title = getIntent().getStringExtra(ExtraKey.TITLE);
        setTitle(title);
        mType = getIntent().getStringExtra(ExtraKey.TYPE_FROM);

        BaseFragment fragment;
        switch (mType) {
            case TYPE_MESSAGE_AT_ME:
            case TYPE_MESSAGE_LIKE:
            case TYPE_MESSAGE_COMMON:
                fragment = MessageInfoFragment.newInstance(mType);
                break;
            case TYPE_MESSAGE_FUNS:
                fragment = new FunsFragment();
                break;
            case TYPE_GROUP_MEMBER:
                fragment = MemberFragment.newInstance(getIntent().getStringExtra(ExtraKey.GROUP_ID));
                break;
            case TYPE_SELECTOR_MATCH:
                fragment = GroupActionFragment.newInstance(GroupActionFragment.FROM_SELECTOR_MATCH);
                break;

            case TYPE_LIVE_LIST:
                fragment = AnchorLivesFragment.newInstance(AnchorLivesFragment.TYPE_LIST,"");
                break;

            default:
                throw new RuntimeException("ContainerActivity 传入非法type值:" + mType);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fm_container, fragment).commit();
    }
}
