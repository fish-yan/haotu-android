package com.dmeyc.dmestoreyfm.newui.menu.apply.group;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/24
 */
public class ApplyGroupActivity extends BaseMvpActivity<IApplyGroupView, ApplyGroupPresenter> implements IApplyGroupView {

    @Bind(R.id.civ_group_name)
    CustomItemView mCivGroupName;
    @Bind(R.id.civ_name)
    CustomItemView mCivName;
    @Bind(R.id.civ_phone)
    CustomItemView mCivPhone;
    @Bind(R.id.et_instruction)
    EditText mEtInstruction;
    @Bind(R.id.iv_logo)
    ImageView mIvLogo;
    private ImageChooserHelper mChooseHelper;
    private String mChooseImagePath;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ApplyGroupActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected ApplyGroupPresenter createPresenter() {
        return new ApplyGroupPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_apply_group;
    }

    @Override
    protected void initViews() {
        setTitleWithRightText("成为俱乐部群主", "提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()) {
                    mPresenter.uploadImage(mChooseImagePath);
                }
            }
        });
    }

    private boolean checkSucc() {
        if (TextUtils.isEmpty(mCivGroupName.getTitle())) {
            ToastUtil.show("请输入俱乐部名称");
        }
        if (TextUtils.isEmpty(mCivName.getTitle())) {
            ToastUtil.show("请输入联系人姓名");
        }

        if (TextUtils.isEmpty(mCivPhone.getTitle())) {
            ToastUtil.show("请输入联系号码");
        }
        if (TextUtils.isEmpty(mEtInstruction.getText().toString())) {
            ToastUtil.show("请输入俱乐部简介");
        }
        if (TextUtils.isEmpty(mChooseImagePath)) {
            ToastUtil.show("请上传俱乐部logo");
        }

        return CheckInfoUtil.isPhoneNo(mCivPhone.getTitle());
    }

    @Override
    protected void initData() {
        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                mChooseImagePath = path;
                GlideUtil.loadLocalImage(ApplyGroupActivity.this, path, mIvLogo);
            }

            @Override
            public void onChooseOver(ArrayList<String> paths, String is_check_original_image) {
                Logger.d("choose paths:" + paths);

            }
        });

        mChooseHelper.setSelectMaxCount(1);
        mChooseHelper.selectFromAblum(false);
        mChooseHelper.setNeedCrop(false);
    }

    @OnClick(R.id.iv_logo)
    public void click(View view) {
        mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("group_name", mCivGroupName.getTitle());
        params.put("publish_name",mCivName.getTitle());
        params.put("publish_telphone",mCivPhone.getTitle());
        params.put("remark",mEtInstruction.getText().toString());
        return params;
    }

    @Override
    public void httpRequestDataSucc() {

        SPUtils.savaStringData(Constant.Config.ROLECODE, CommonConfig.ROLE_GROUP);
        finish();
        EventBus.getDefault().post(new MyEvent.Close.ApplyForActivity());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChooseHelper.onActivityResult(requestCode, resultCode, data);
    }
}
