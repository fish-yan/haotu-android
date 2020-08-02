package com.dmeyc.dmestoreyfm.newui.release.action;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ActionPropertyBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.release.match.ReleaseMatchActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.TypeSelectorDialog;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * create by cxg on 2019/11/30
 */
public class ReleaseActionActivity extends BaseMvpActivity<IReleaseActionView, ReleaseActionPresenter> implements IReleaseActionView {


    @Bind(R.id.civ_theme)
    CustomItemView mCivTtheme;
    @Bind(R.id.civ_time)
    CustomItemView mCivTime;
    @Bind(R.id.civ_address)
    CustomItemView mCivAddress;
    @Bind(R.id.civ_person_count)
    CustomItemView mCivPersonCount;
    @Bind(R.id.civ_cost)
    CustomItemView mCivCost;
    @Bind(R.id.civ_save)
    CustomItemView mCivSave;
    @Bind(R.id.civ_action_type)
    CustomItemView mCivActionType;
    @Bind(R.id.civ_addr_type)
    CustomItemView mCivAddrType;

    @Bind(R.id.et_instruction)
    EditText mEtInstruction;

    private String mActivityType;
    private String mActivityProperty;

    private List<ActionPropertyBean> mActionPropertyList = new ArrayList<>();
    private ArrayList<String> mActionPropertyNameList= new ArrayList<>();


    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ReleaseActionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected ReleaseActionPresenter createPresenter() {
        return new ReleaseActionPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_release_action;
    }

    @Override
    protected void initViews() {
        setTitleWithBothText("取消发布", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()) {
                    mPresenter.httpRequestData();
                }
            }
        });
        mActionPropertyList.add(new ActionPropertyBean("1", "固定场"));
        mActionPropertyList.add(new ActionPropertyBean("2", "临时场"));
        for (ActionPropertyBean bean : mActionPropertyList) {
            mActionPropertyNameList.add(bean.name);
        }
    }

    private boolean checkSucc() {
        if (TextUtils.isEmpty(mCivTtheme.getTitle())) {
            ToastUtil.show("请输入活动主题");
            return false;
        }
        if (TextUtils.isEmpty(mCivTime.getTextTitle())) {
            ToastUtil.show("请输入活动时间");
            return false;
        }
        if (TextUtils.isEmpty(mCivAddress.getTitle())) {
            ToastUtil.show("请输入活动地点");
            return false;
        }
        if (TextUtils.isEmpty(mCivPersonCount.getTitle())) {
            ToastUtil.show("请输入可参与人数");
            return false;
        }
        if (TextUtils.isEmpty(mCivCost.getTitle())) {
            ToastUtil.show("请输入活动费用");
            return false;
        }
//        if (TextUtils.isEmpty(mCivSave.getTitle())) {
//            ToastUtil.show("请输入女士优惠金额");
//            return false;
//        }
        if (TextUtils.isEmpty(mActivityType)) {
            ToastUtil.show("请选择活动类型");
            return false;
        }
        if (TextUtils.isEmpty(mActivityProperty)) {
            ToastUtil.show("请选择固定活动");
            return false;
        }
        if (TextUtils.isEmpty(mEtInstruction.getText().toString())) {
            ToastUtil.show("请输入活动介绍");
            return false;
        }
        return true;
    }

    @OnClick({R.id.civ_addr_type, R.id.civ_action_type, R.id.civ_time})
    public void click(View view) {
        switch (view.getId()) {

            case R.id.civ_addr_type:

                new TypeSelectorDialog.Build(ReleaseActionActivity.this).setData(mActionPropertyList).setNameItems(mActionPropertyNameList).setOnItemClickListener(new TypeSelectorDialog.ItemClickListener<ActionPropertyBean>() {
                    @Override
                    public void onItemClick(String title, ActionPropertyBean bean) {
                        mActivityProperty =bean.type;
                        mCivAddrType.setTitle(title);
                    }
                }).create().show();
                break;
            case R.id.civ_action_type:
                mPresenter.getActionType();
                break;
            case R.id.civ_time:
                showDatePickDialog(DateType.TYPE_ALL);
                break;
            default:
        }
    }

    @Override
    public void getActionListSucc(final List<ProjectTypeBean.DataBean> datas) {
        ArrayList<String> banNameList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            banNameList.add(datas.get(i).getItemName());
        }

        new TypeSelectorDialog.Build(ReleaseActionActivity.this).setData(datas).setNameItems(banNameList).setOnItemClickListener(new TypeSelectorDialog.ItemClickListener<ProjectTypeBean.DataBean>() {
            @Override
            public void onItemClick(String title, ProjectTypeBean.DataBean bean) {
                mActivityType = bean.getItemCode();
                mCivActionType.setTitle(title);
            }
        }).create().show();

    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("activity_name",mCivTtheme.getTitle());
        params.put("start_date",mCivTime.getTextTitle());
        params.put("activity_address",mCivAddress.getTitle());
        params.put("total_no",mCivPersonCount.getTitle());
        params.put("m_visitor_amount",mCivCost.getTitle());
        params.put("w_discount_amount",mCivSave.getTitle());
        params.put("activity_property",mActivityProperty);
        params.put("activity_type", mActivityType);
        params.put("remark", mEtInstruction.getText().toString());
        return params;
    }

    @Override
    public void httpRequestSucc() {
        ToastUtil.show("发布成功");
        EventBus.getDefault().post(new RefreshEvent.Release(RefreshEvent.Release.TYPE_ACTIVITY));
        finish();
    }
    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择开始时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
//        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        dialog.setMessageFormat("yyyy-MM-dd HH");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                mCivTime.setTitle(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            }
        });
        dialog.show();
    }
}
