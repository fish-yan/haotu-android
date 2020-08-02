package com.dmeyc.dmestoreyfm.newui.pagerdetail.other;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.common.anchorlist.AnchorLivesFragment;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.video.PersonVideoFragment;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/19
 */
public class OtherDetailActivity extends BaseTabNewActivity<IOtherDetailView, OtherDetailPresenter> implements IOtherDetailView {
    @Bind(R.id.civ_header)
    CircleImageView mCivHeader;

    @Bind(R.id.tv_person_name)
    TextView mTvName;
    @Bind(R.id.tv_praise)
    TextView mTvPraise;
    @Bind(R.id.tv_follow)
    TextView mTvFollow;
    @Bind(R.id.tv_like)
    TextView mTvLike;
    @Bind(R.id.tv_follow_status)
    TextView mTvFollowStatus;
    @Bind(R.id.tv_edit_content)
    TextView mTvEditContent;
    @Bind(R.id.tv_person_instruction)
    TextView mTvPersonInstruction;

    private String mUserId;
    private String mStatus;

    public static void newInstance(Context context, String userId) {
        Intent intent = new Intent(context, OtherDetailActivity.class);
        intent.putExtra(ExtraKey.USER_ID, userId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUserId = getIntent().getStringExtra(ExtraKey.USER_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        PersonVideoFragment fragment1 = PersonVideoFragment.newInstance(PersonVideoFragment.TYPE_VIDEO_LIST, mUserId);
        AnchorLivesFragment fragment2 = AnchorLivesFragment.newInstance(AnchorLivesFragment.TYPE_HOME, mUserId);
        PersonVideoFragment fragment3 = PersonVideoFragment.newInstance(PersonVideoFragment.TYPE_VIDEO_LIKE, mUserId);

        mFragmentLists.add(fragment1);
        mFragmentLists.add(fragment2);
        mFragmentLists.add(fragment3);

    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("视频");
        mTitleLists.add("直播");
        mTitleLists.add("点赞");
    }

    @Override
    protected OtherDetailPresenter createPresenter() {
        return new OtherDetailPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_other_detail;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mTvFollowStatus.setVisibility(View.VISIBLE);
        mTvEditContent.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("targetUserId", mUserId);
        return params;
    }

    @Override
    public void httpRequestSucc(AccountInfoBean.DataBean data) {
        GlideUtil.loadImage(this, data.getUser_logo(), mCivHeader);
        if (data.getNick_name() != null) {
            mTvName.setText(data.getNick_name());
        }
        mTvFollow.setText(data.getFollowersNo());
        mTvLike.setText(data.getFansNo());
        mTvPraise.setText(data.getLikedNo());
        mStatus = data.getIsFollowered();
        mTvFollowStatus.setText("1".equals(mStatus) ? "已关注" : "关注");
    }

    @OnClick({R.id.iv_back, R.id.tv_follow_status})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_follow_status:
                if (!CommonUtil.isLogin(this)) {
                    return;
                }
                if (TextUtils.isEmpty(mStatus)) {
                    ToastUtil.show("数据错误");
                    return;
                }
                CommentRequestHelper.httpFollowData(this, mUserId, mStatus, mCallBackAdapter);
                break;
        }
    }

    private CommentRequestHelper.CallBackAdapter mCallBackAdapter = new CommentRequestHelper.CallBackAdapter() {
        @Override
        public void onSuccess() {
            super.onSuccess();
            if ("1".equals(mStatus)) {
                mStatus = "0";
                mTvFollowStatus.setText("关注");
            } else {
                mStatus = "1";
                mTvFollowStatus.setText("已关注");
            }
        }
    };
}
