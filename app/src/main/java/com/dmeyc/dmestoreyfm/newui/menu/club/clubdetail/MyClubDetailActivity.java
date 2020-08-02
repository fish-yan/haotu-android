package com.dmeyc.dmestoreyfm.newui.menu.club.clubdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.GroupDetailHeaderAdapter;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.common.container.ContainerActivity;
import com.dmeyc.dmestoreyfm.newui.menu.club.notice.NoticeActivity;
import com.dmeyc.dmestoreyfm.newui.menu.club.qrcode.ClubQrCodeActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.about.report.ReportActivity;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/29
 */
public class MyClubDetailActivity extends BaseMvpActivity<IMyClubDetaiView, MyClubDetailPresenter> implements IMyClubDetaiView {

    @Bind(R.id.tv_look_more_member)
    TextView mTvLookMoreMember;

    @Bind(R.id.tv_dissolve)
    TextView mTvDissolve;

    @Bind(R.id.civ_club_name)
    CustomItemView mCivClubName;
    @Bind(R.id.civ_disturb)
    CustomItemView mCivDisturb;

    @Bind(R.id.rv_head_icon)
    RecyclerView mRvHeadIcon;
    private GroupDetailHeaderAdapter mAdapter;
    private List<NewMemberListBean.DataBean.ListBean> mHeaderIcons = new ArrayList<>();

    private String mGroupId;
    private String mQrCode;
    private String mRemark;
    private String mGroupLogo;
    private String mGroupName;
    private String mGroupOwnerType;


    /**
     *
     * @param context
     * @param groupId
     * @param mGroupOwnerType 1 群主，0 其他
     */
    public static void newInstance(Context context, String groupId, String mGroupOwnerType) {
        Intent intent = new Intent(context, MyClubDetailActivity.class);
        intent.putExtra(ExtraKey.GROUP_ID, groupId);
        intent.putExtra(ExtraKey.GROUP_OWNER, mGroupOwnerType);

        context.startActivity(intent);
    }

    @Override
    protected MyClubDetailPresenter createPresenter() {
        return new MyClubDetailPresenter();
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_my_club_detail;
    }

    @Override
    protected void initViews() {
        setTitle("我的俱乐部");
        mGroupId = getIntent().getStringExtra(ExtraKey.GROUP_ID);
        mGroupOwnerType = getIntent().getStringExtra(ExtraKey.GROUP_OWNER);
        if (!CommonConfig.isGroupOwner(mGroupOwnerType)){
            mTvDissolve.setVisibility(View.VISIBLE);
        }

        mCivDisturb.setItemClickListener(new CustomItemView.OnclickListener() {

            @Override
            public void onRightIconClick(CustomItemView view) {
                if (view.getId() == R.id.civ_disturb) {
                    mCivDisturb.setEnabled(false);
                    mPresenter.setNotificationStatus(mGroupId);
                }
            }
        });
        mAdapter = new GroupDetailHeaderAdapter(this);
        mRvHeadIcon.setLayoutManager(new GridLayoutManager(this,5));
        mRvHeadIcon.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRvHeadIcon);

        mPresenter.getNofiticationStatus(mGroupId);
        mPresenter.httpRequestData();
        mPresenter.httpMembersData();
    }

    @OnClick({R.id.civ_club_qrcode, R.id.civ_club_notice, R.id.civ_report, R.id.tv_dissolve,R.id.rl_look_more})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.civ_club_qrcode:
                ClubQrCodeActivity.newInstance(this, mGroupLogo, mGroupName, mQrCode);
                break;
            case R.id.civ_club_notice:
                NoticeActivity.newInstance(this, mRemark, mGroupId,mGroupOwnerType);
                break;
            case R.id.civ_report:
                ReportActivity.startActivity(this, ReportActivity.TYPE_REPORT);
                break;
            case R.id.tv_dissolve:
                mPresenter.quitGroup();
                break;
            case R.id.rl_look_more:
                ContainerActivity.newInstance(this,ContainerActivity.TYPE_GROUP_MEMBER,"群成员",mGroupId);
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("group_id", mGroupId);
        return params;
    }

    @Override
    public void httpDataSucc(GroupDetailBean.DataBean data) {
        mCivClubName.setTitle(data.getGroup_name());
        mQrCode = data.getQrCode();
        mRemark = data.getRemark();
        mGroupLogo = data.getGroup_logo();
        mGroupName = data.getGroup_name();
    }

    @Override
    public void getMemberSucc(List<NewMemberListBean.DataBean.ListBean> groupMemberList) {
        mHeaderIcons.clear();
        if (groupMemberList != null) {
            if (groupMemberList.size() >10) {
                mHeaderIcons.addAll(groupMemberList.subList(0, 10));
            } else {
                mHeaderIcons.addAll(groupMemberList);
            }
        }
        mAdapter.replaceData(mHeaderIcons);

    }

    @Override
    public void quitGroupSucc() {
        EventBus.getDefault().post(new MyEvent.Close.ConversationActivity());
        finish();
    }

    @Override
    public void getNotificationStatusResult(int value) {
        mCivDisturb.setToggleButtonCheck(value == 1);
    }


    /**
     * @param b     修改结果
     * @param value 1 免打扰
     */
    @Override
    public void setNotificationStatusResult(boolean b, int value) {
        mCivDisturb.setEnabled(true);
        if (b) {
            mCivDisturb.setToggleButtonCheck(value == 1);
        } else {
            mCivDisturb.setToggleButtonCheck(!mCivDisturb.getToggleBttonCheck());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateNotice(MyEvent.UpdateNotice event) {
        mRemark = event.notice;
    }
}
