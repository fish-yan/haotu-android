package com.dmeyc.dmestoreyfm.newui.menu.wallet.addbankcard;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/25
 */
public class AddBankCardActivity extends BaseMvpActivity<IAddBankCardView, AddBankCardPresenter> implements IAddBankCardView {

    @Bind(R.id.civ_bank_card)
    CustomItemView mCivBankBard;
    @Bind(R.id.civ_selector_bank)
    CustomItemView mCivSelectorBank;
    @Bind(R.id.civ_phone_no)
    CustomItemView mCivPhoneNo;
    @Bind(R.id.civ_code)
    CustomItemView mCivCode;

    private PopupMenu mPopupMenu;
    private ProjectTypeBean.DataBean mSelectorBank;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected AddBankCardPresenter createPresenter() {
        return new AddBankCardPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_add_bank_card;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("添加银行卡", "提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()) {
                    mPresenter.addBankCard();
                }
            }
        });

        mCivPhoneNo.setItemClickListener(new CustomItemView.OnclickListener() {
            @Override
            public void onCodeClick(CustomItemView view) {
                if (CheckInfoUtil.isPhoneNo(mCivPhoneNo.getTitle())) {
                    mPresenter.getCode();
                }
            }
        });

    }

    private boolean checkSucc() {
        if (TextUtils.isEmpty(mCivBankBard.getTitle())) {
            ToastUtil.show("请填银行卡号");
            return false;
        }
        if (TextUtils.isEmpty(mCivSelectorBank.getTextTitle())) {
            ToastUtil.show("请选择银行");
            return false;
        }
        if (TextUtils.isEmpty(mCivPhoneNo.getTitle())) {
            ToastUtil.show("请填预留银行手机号");
            return false;
        }
        if (TextUtils.isEmpty(mCivCode.getTitle())) {
            ToastUtil.show("请输入验证码");
            return false;
        }
        return CheckInfoUtil.isPhoneNo(mCivPhoneNo.getTitle());
    }


    @OnClick(R.id.civ_selector_bank)
    public void click() {
        mPresenter.httpBankList();
    }

    @Override
    public Map<String, String> getCodeParams() {
        Map<String, String> params = new HashMap<>();
        params.put("phone_no", mCivPhoneNo.getTitle());
        return params;
    }

    @Override
    public void getCodeSucc() {
        mCivPhoneNo.startTimer();
    }

    @Override
    public Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("bank_name",mSelectorBank.getItemName());
        params.put("bank_logo",mSelectorBank.getItemCode());
        params.put("bank_code",mSelectorBank.getExt());
        params.put("bank_account",mCivBankBard.getTitle());
        params.put("bank_phone_no",mCivPhoneNo.getTitle());
        params.put("valid_code",mCivCode.getTitle());

        return params;
    }

    @Override
    public void addBankCardSucc() {
        EventBus.getDefault().post(new RefreshEvent.BankCard());
        finish();
    }

    @Override
    public void getBandListSucc(final List<ProjectTypeBean.DataBean> datas) {
        ArrayList<String> banNameList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            banNameList.add(datas.get(i).getItemName());
        }
        mPopupMenu = new PopupMenu(AddBankCardActivity.this, banNameList);
        mPopupMenu.showLocation(R.id.civ_selector_bank);// 设置弹出菜单弹出的位置
        // 设置回调监听，获取点击事件
        mPopupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
            @Override
            public void onClick(PopupMenu.MENUITEM item, String str) {

            }

            @Override
            public void onClick(String str, int pos) {
                mSelectorBank = datas.get(pos);
                mCivSelectorBank.setTitle(str);
                mPopupMenu.dismiss();
            }
        });
    }
}
