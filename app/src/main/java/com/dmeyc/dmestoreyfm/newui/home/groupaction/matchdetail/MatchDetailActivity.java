package com.dmeyc.dmestoreyfm.newui.home.groupaction.matchdetail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail.ActionDetailActivity;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail.ActionDetailPresenter;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail.IActionDetailView;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ShareDialogHelper;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * create by cxg on 2019/12/2
 */
public class MatchDetailActivity extends BaseMvpActivity<IActionDetailView, ActionDetailPresenter> implements IActionDetailView {

    private String mActivityID;

    @Bind(R.id.civ_name)
    CustomItemView mCivName;
    @Bind(R.id.civ_time)
    CustomItemView mCivTime;
    @Bind(R.id.civ_addr)
    CustomItemView mCivAddr;
    @Bind(R.id.civ_type)
    CustomItemView mCivType;

    @Bind(R.id.iv_logo)
    ImageView mIvLogo;
    private ActivityDetailBean.DataBean mDataBean;
    private View mShareView;

    public static void newInstance(Context context, String groupId) {
        Intent intent = new Intent(context, MatchDetailActivity.class);
        intent.putExtra(ExtraKey.ACTIVITY_ID, groupId);
        context.startActivity(intent);
    }

    @Override
    protected ActionDetailPresenter createPresenter() {
        return new ActionDetailPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_match_detail;
    }

    @Override
    protected void initViews() {
        setTitleWithRightIcon("赛事活动详情", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataBean != null) {
                    mShareView = v;
                    mPresenter.getShareUrl(mActivityID,ActionDetailPresenter.TYPE_MATCH);
                }
            }
        }, R.drawable.right_iconmore);

        mActivityID = getIntent().getStringExtra(ExtraKey.ACTIVITY_ID);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("group_activity_id", mActivityID);
        return params;
    }
    @Override
    public void getShareUrlSucc(String data) {
        ShareDialogHelper.shareAction(MatchDetailActivity.this, mDataBean, mActivityID, mShareView,data);
    }

    @Override
    public void httpDataSucc(ActivityDetailBean.DataBean bean) {
        mDataBean = bean;
        mCivName.setTitle(bean.getGroup_name());
        mCivTime.setTitle(bean.getStart_date());
        mCivAddr.setTitle(bean.getActivity_address());
        mCivType.setTitle(bean.getProjectType());

        mCivAddr.setmAddress(bean.getActivity_address()).setmLongitude(bean.getLongitude()).setmLatitude(bean.getLatitude());
        GlideUtil.loadImage(this, bean.getGroup_logo(), mIvLogo);
    }
}
