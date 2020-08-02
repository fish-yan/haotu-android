package com.dmeyc.dmestoreyfm.newui.menu.apply.coach;

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
 * create by cxg on 2019/11/30
 */
public class ApplyCoachActivity extends BaseMvpActivity<IApplyCoachView, ApplyCoachPresenter> implements IApplyCoachView {

    public static final String CHECK_PHONE = "1";
    public static final String CHECK_QUALIFICATIONS = "2";

    private String mCheck = CHECK_PHONE;
    @Bind(R.id.civ_merc_name)
    CustomItemView mCivMercName;
    @Bind(R.id.civ_merc_alis)
    CustomItemView mCivMercAlis;
    @Bind(R.id.civ_phone)
    CustomItemView mCivPhone;
    @Bind(R.id.et_instruction)
    EditText mEtInstruction;

    @Bind(R.id.iv_photo)
    ImageView mIvPhoto;
    @Bind(R.id.iv_qualifications)
    ImageView mIvQualifications;

    private ImageChooserHelper mChooseHelper;
    private String mPhotoPath;
    private String mQualificationsPath;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ApplyCoachActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected ApplyCoachPresenter createPresenter() {
        return new ApplyCoachPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_apply_coach;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("成为教练", "提交", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkSucc()) {

                    mPresenter.uploadImages();
                }
            }


        });
        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                switch (mCheck) {
                    case CHECK_PHONE:
                        mPhotoPath = path;
                        GlideUtil.loadLocalImage(ApplyCoachActivity.this, path, mIvPhoto);

                        break;
                    case CHECK_QUALIFICATIONS:
                        mQualificationsPath = path;
                        GlideUtil.loadLocalImage(ApplyCoachActivity.this, path, mIvQualifications);
                        break;
                    default:
                }
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
        if (TextUtils.isEmpty(mCivMercName.getTitle())) {
            ToastUtil.show("请填您的姓名");
            return false;
        }
        if (TextUtils.isEmpty(mCivMercAlis.getTitle())) {
            ToastUtil.show("请填写您的昵称");
            return false;
        }
        if (TextUtils.isEmpty(mCivPhone.getTitle())) {
            ToastUtil.show("请填写联系号码");
            return false;
        }
        if (TextUtils.isEmpty(mEtInstruction.getText().toString())) {
            // TODO: 2019/12/5 文案错误
            ToastUtil.show("请介绍一下您的俱乐部吧～");
        }
        if (TextUtils.isEmpty(mPhotoPath)) {
            ToastUtil.show("请上传一寸白底照");
            return false;
        }
        if (TextUtils.isEmpty(mQualificationsPath)) {
            ToastUtil.show("请上传资质证书");
            return false;
        }
        return CheckInfoUtil.isPhoneNo(mCivPhone.getTitle());
    }

    @OnClick({R.id.iv_photo,
            R.id.iv_qualifications})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
                mCheck = CHECK_PHONE;
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
                break;

            case R.id.iv_qualifications:
                mCheck = CHECK_QUALIFICATIONS;
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
                break;

            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChooseHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Map<String, String> getImageParams() {
        Map<String, String> images = new HashMap<>();
        images.put(CHECK_PHONE, mPhotoPath);
        images.put(CHECK_QUALIFICATIONS, mQualificationsPath);
        return images;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", mCivMercName.getTitle());
        params.put("nick_name", mCivMercAlis.getTitle());
        params.put("telephone", mCivPhone.getTitle());
        params.put("remark", mEtInstruction.getText().toString());
        return params;
    }

    @Override
    public void httpRequestDataSucc() {

        SPUtils.savaStringData(Constant.Config.ROLECODE, CommonConfig.ROLE_COCAH);
        finish();
        EventBus.getDefault().post(new MyEvent.Close.ApplyForActivity());
    }
}
