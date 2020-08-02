package com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actionpay.ActionPayActivity;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.joinmember.JoinMemberActivity;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ShareDialogHelper;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CountdownTime;
import com.dmeyc.dmestoreyfm.wedgit.CountdownView;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView1;
import com.dmeyc.dmestoreyfm.wedgit.HeaderIconView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/1
 */
public class ActionDetailActivity extends BaseMvpActivity<IActionDetailView, ActionDetailPresenter> implements IActionDetailView {

    private String mActivityId;

    @Bind(R.id.civ_club_name)
    CustomItemView mCivClubName;
    @Bind(R.id.civ_match_site)
    CustomItemView mCivMatchSite;
    @Bind(R.id.civ_time)
    CustomItemView mCivTime;
    @Bind(R.id.civ1_pay)
    CustomItemView1 mCiv1Pay;
    @Bind(R.id.civ_addr)
    CustomItemView mCivAddr;
    @Bind(R.id.headerView)
    HeaderIconView mHeaderView;

    @Bind(R.id.tv_instruction)
    TextView mTvInstruction;
    @Bind(R.id.cb_protocol)
    CheckBox mCbProtocol;
    @Bind(R.id.tv_enter)
    TextView mTvEnter;

    @Bind(R.id.tv_tip_title)
    TextView mTvTipTitle;

    @Bind(R.id.countdownView)
    CountdownView countdownView;

    private View mShareView;
    private ActivityDetailBean.DataBean mDataBean;

    private List<ActivityDetailBean.DataBean.SignUp> mJoinList;

    public static void newInstance(Context context, String groupId) {
        Intent intent = new Intent(context, ActionDetailActivity.class);
        intent.putExtra(ExtraKey.ACTIVITY_ID, groupId);
        context.startActivity(intent);
    }

    @Override
    protected ActionDetailPresenter createPresenter() {
        return new ActionDetailPresenter();
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_action_detail;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightIcon("活动详情", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataBean != null) {
                    mShareView = v;
                    mPresenter.getShareUrl(mActivityId,ActionDetailPresenter.TYPE_ACTIVITY);
                }
            }
        }, R.drawable.right_iconmore);
        mActivityId = getIntent().getStringExtra(ExtraKey.ACTIVITY_ID);
        countdownView.setTextSize(ScreenUtil.dp2px(ActionDetailActivity.this, 4));
    }

    @Override
    protected void initData() {
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("group_activity_id", mActivityId);
        return params;
    }

    @Override
    public void httpDataSucc(ActivityDetailBean.DataBean bean) {
        mDataBean = bean;
        mCivClubName.loadLeftIcon(bean.getGroup_logo());
        mCivClubName.setTitle(bean.getGroup_name());
        mCivClubName.setPhoneNo(bean.getActivity_phone_no());

        mCivMatchSite.setTitle(bean.getActivity_name());
        mCivTime.setTitle(bean.getStart_date());
        mCiv1Pay.setTitle(bean.getM_visitor_amount() + "元/位");
        mCivAddr.setTitle(bean.getActivity_address());
        mCivAddr.setmAddress(bean.getActivity_address()).setmLongitude(bean.getLongitude()).setmLatitude(bean.getLatitude());
        mTvInstruction.setText(bean.getRemark());

        List<ActivityDetailBean.DataBean.SignUp> sign_up_list = bean.getSign_up_list();
        if (sign_up_list == null || sign_up_list.size() == 0) {
            mHeaderView.setCount("0" + "人报名");
            mHeaderView.setEnabled(false);
        } else {
            mHeaderView.setEnabled(true);
            mHeaderView.setCount(sign_up_list.size() + "人报名");
            mHeaderView.setData(sign_up_list);
            mJoinList = sign_up_list;
        }

        if ("1".equals(bean.getIs_sign_no())) {
            mTvEnter.setText("已报名");
            mTvEnter.setEnabled(false);
        } else {
            mTvEnter.setEnabled(false);
            String text = "立即报名";
            switch (bean.getStatus()) {
                case "1":
                    text = "立即报名";
                    mTvEnter.setEnabled(true);
                    break;
                case "2":
                    text = "报名结束";
                    break;
                case "3":
                    text = "活动结束";
                    break;
                case "5":
                    text = "活动暂停";
                    break;
                case "6":
                    text = "活动取消";
                    break;
                case "8":
                    text = "活动待退款";
                default:
                    break;
            }
            mTvEnter.setText(text);
        }
        resetDownTime(bean.getStart_date());

    }

    @Override
    public void getShareUrlSucc(String data) {
        ShareDialogHelper.shareAction(ActionDetailActivity.this, mDataBean, mActivityId, mShareView, data);
    }


    private void resetDownTime(String startDate) {
        long stattime = DateUtil.getlongToDate(startDate);
        int downtime = (int) (stattime / 1000) - (int) (System.currentTimeMillis() / 1000);
        if (downtime <= 0) {
            actionEnd();
        } else {
            CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
                @Override
                public void onTimeCountdownOver() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            actionEnd();
                        }
                    });
                }
            };
            countdownView.setCountdownTime(downtime, "");
        }

    }

    private void actionEnd() {
        mTvEnter.setEnabled(false);
        mTvEnter.setText("报名截止");
        mTvTipTitle.setText("活动报名已截止..");
        mTvTipTitle.setTextColor(getResources().getColor(R.color.red));
        countdownView.setVisibility(View.GONE);

        mCbProtocol.setEnabled(false);
        mCbProtocol.setChecked(false);
        mCbProtocol.setFocusable(false);
    }


    @OnClick({R.id.tv_enter, R.id.tv_protocol, R.id.headerView})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_enter:
                if (!mCbProtocol.isChecked()) {
                    ToastUtil.show("请勾选协议");
                    return;
                }

                if (mDataBean == null) {
                    ToastUtil.show("网络异常，请返回重进");
                    return;
                }
                if ("0".equals(mDataBean.getIs_join_group()) && "4".equals(mDataBean.getGroupType())) {
                    // TODO: 2019/12/17 这个逻辑还要吗
                    ToastUtil.show("请先加入到该群内  否则无法报名");
                    return;
                }
                ActionPayActivity.newInstance(ActionDetailActivity.this, mActivityId);
                break;
            case R.id.tv_protocol:
                Intent intent = new Intent(this, WebviewActivity.class);
                intent.putExtra(Constant.Config.TITLE, "免责条款");
                intent.putExtra(Constant.Config.URL, "http://www.hotu.club:9595/agreement/liability.html");
                startActivity(intent);
                break;
            case R.id.headerView:
                if (mJoinList != null) {
                    JoinMemberActivity.newInstance(this, mJoinList);
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(RefreshEvent.ActionDetailActivity event){
        mPresenter.httpRequestData();
    }

}
