package com.dmeyc.dmestoreyfm.newui.menu.apply.merchant;

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
import com.dmeyc.dmestoreyfm.newui.menu.apply.group.ApplyGroupActivity;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/30
 */
public class ApplyMerchantActivity extends BaseMvpActivity<IApplyMerchantView,ApplyMerchantPresenter> implements IApplyMerchantView {

    @Bind(R.id.civ_merc_name)
    CustomItemView mCivMercName;
    @Bind(R.id.civ_merc_addr)
    CustomItemView mCivMercAddr;
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

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ApplyMerchantActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected ApplyMerchantPresenter createPresenter() {
        return new ApplyMerchantPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_apply_merchant;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("成为商家","提交",new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (checkSucc()){
                    mPresenter.uploadImage(mChooseImagePath);
                }
            }
        });
        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                mChooseImagePath = path;
                GlideUtil.loadLocalImage(ApplyMerchantActivity.this, path, mIvLogo);
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

    private boolean checkSucc() {
        if(TextUtils.isEmpty(mCivMercName.getTitle())){
            ToastUtil.show("请填写商户名称");
            return false;
        }
        if(TextUtils.isEmpty(mCivMercAddr.getTitle())){
            ToastUtil.show("请填写商户地址");
            return false;
        }
        if(TextUtils.isEmpty(mCivName.getTitle())){
            ToastUtil.show("请填写联系人姓名");
            return false;
        }
        if(TextUtils.isEmpty(mCivPhone.getTitle())){
            ToastUtil.show("请填写联系号码");
            return false;
        }
        if(TextUtils.isEmpty(mEtInstruction.getText().toString())){
            ToastUtil.show("请输入介绍");
            return false;
        }
        if(TextUtils.isEmpty(mChooseImagePath)){
            ToastUtil.show("请上传店铺logo");
            return false;
        }
        return CheckInfoUtil.isPhoneNo(mCivPhone.getTitle());
    }

    @OnClick(R.id.iv_logo)
    public void click(View view) {
        mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChooseHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("business_name",mCivMercName.getTitle());
        params.put("business_address",mCivMercAddr.getTitle());
        params.put("name",mCivName.getTitle());
        params.put("telephone",mCivPhone.getTitle());
        params.put("remark",mEtInstruction.getText().toString());
        return params;
    }

    @Override
    public void httpRequestDataSucc() {

        SPUtils.savaStringData(Constant.Config.ROLECODE, CommonConfig.ROLE_MERCHANT);
        finish();
        EventBus.getDefault().post(new MyEvent.Close.ApplyForActivity());
    }
}
