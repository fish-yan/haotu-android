package com.dmeyc.dmestoreyfm.newui.menu.apply.anchor;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.menu.apply.coach.ApplyCoachActivity;
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
 * todo 身份证字段后台没有，后台多了一个
 */
public class ApplyAnchorActivity extends BaseMvpActivity<IAnchorView, AnchorPresenter> implements IAnchorView {

    public static final String IDCARD_FRONT = "IDCARD_FRONT";
    public static final String IDCARD_BACK = "IDCARD_BACK";
    private String mCheck = IDCARD_FRONT;

    private ImageChooserHelper mChooseHelper;
    private String mFrontPath;
    private String mBackPath;


    @Bind(R.id.civ_name)
    CustomItemView mCivName;
    @Bind(R.id.civ_id_card)
    CustomItemView mCividCard;
    @Bind(R.id.civ_phone)
    CustomItemView mCivPhone;
    @Bind(R.id.iv_idcard_front)
    ImageView mIvIdcardFront;
    @Bind(R.id.iv_idcard_back)
    ImageView mIvIdcardBack;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ApplyAnchorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected AnchorPresenter createPresenter() {
        return new AnchorPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_apply_anchor;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("成为主播", "提交", new View.OnClickListener() {

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
                    case IDCARD_FRONT:
                        mFrontPath = path;
                        GlideUtil.loadLocalImage(ApplyAnchorActivity.this, path, mIvIdcardFront);
                        break;
                    case IDCARD_BACK:
                        mBackPath = path;
                        GlideUtil.loadLocalImage(ApplyAnchorActivity.this, path, mIvIdcardBack);
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
        if (TextUtils.isEmpty(mCivName.getTitle())) {
            ToastUtil.show("请填您的真实姓名");
            return false;
        }
        if (TextUtils.isEmpty(mCividCard.getTitle())) {
            ToastUtil.show("请填写您的身份证号");
            return false;
        }
        if (TextUtils.isEmpty(mCivPhone.getTitle())) {
            ToastUtil.show("请填写联系号码");
            return false;
        }
        if (TextUtils.isEmpty(mFrontPath)) {
            ToastUtil.show("请上传身份证正面照");
            return false;
        }
        if (TextUtils.isEmpty(mBackPath)) {
            ToastUtil.show("请上传身份证反面照");
            return false;
        }
        return CheckInfoUtil.isPhoneNo(mCivPhone.getTitle());
    }

    @OnClick({R.id.iv_idcard_front,
            R.id.iv_idcard_back})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_idcard_front:
                mCheck = IDCARD_FRONT;
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
                break;

            case R.id.iv_idcard_back:
                mCheck = IDCARD_BACK;
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
    public void httpRequestSucc() {
        SPUtils.savaStringData(Constant.Config.ROLECODE, CommonConfig.ROLE_ANCHOR);
        finish();
        EventBus.getDefault().post(new MyEvent.Close.ApplyForActivity());
    }

    @Override
    public Map<String, String> getImageParams() {
        Map<String, String> images = new HashMap<>();
        images.put(IDCARD_FRONT, mFrontPath);
        images.put(IDCARD_BACK, mBackPath);
        return images;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("archor_name", mCivPhone.getTitle());
        params.put("telephone", mCivPhone.getTitle());
        params.put("indentity_no", mCividCard.getTitle());
        return params;
    }
}
