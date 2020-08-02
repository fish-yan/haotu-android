package com.dmeyc.dmestoreyfm.newui.pagerdetail.editinfo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/21
 */
public class EditInfoActivity extends BaseMvpActivity<IEditInfoView, EditInfoPresenter> implements IEditInfoView {

    @Bind(R.id.iv_header)
    CircleImageView mIvHeader;
    @Bind(R.id.civ_name)
    CustomItemView mCivName;
    @Bind(R.id.civ_mark)
    CustomItemView mCivMark;

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    private ImageChooserHelper mChooseHelper;
    private String mChooseImagePath;

    public static void newInstance(Context context, String Name, String autograph, String headIcon) {
        Intent intent = new Intent(context, EditInfoActivity.class);
        intent.putExtra(ExtraKey.USER_NAME, Name);
        intent.putExtra(ExtraKey.USER_AUTOGRAPH, autograph);
        intent.putExtra(ExtraKey.USER_HEADER_ICON, headIcon);
        context.startActivity(intent);
    }

    @Override
    protected EditInfoPresenter createPresenter() {
        return new EditInfoPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_edit_info;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("编辑个人资料", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mChooseImagePath)) {
                    mPresenter.httpRequestData();
                } else {
                    mPresenter.uploadImage(mChooseImagePath);
                }
            }
        });


        Intent intent = getIntent();
        mCivMark.setRightText(intent.getStringExtra(ExtraKey.USER_AUTOGRAPH));
        mCivName.setRightText(intent.getStringExtra(ExtraKey.USER_NAME));
        GlideUtil.loacImageCenterCrop(this, intent.getStringExtra(ExtraKey.USER_HEADER_ICON), mIvHeader);
        mCivName.setRightText(intent.getStringExtra(ExtraKey.USER_NAME));


        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                mChooseImagePath = path;
                GlideUtil.loadLocalImage(EditInfoActivity.this, path, mIvHeader);
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


    @OnClick({R.id.iv_header, R.id.civ_name, R.id.civ_mark})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_header:
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
                break;
            case R.id.civ_name:
                EditInfoDetailActivity.newInstance(this, EditInfoDetailActivity.TYPE_NAME, mCivName.getRightText());
                break;
            case R.id.civ_mark:
                EditInfoDetailActivity.newInstance(this, EditInfoDetailActivity.TYPE_MARK, mCivMark.getRightText());
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshInfo(MyEvent.EditInfo event) {
        if (MyEvent.EditInfo.TYPE_MARK.equals(event.getType())) {
            mCivMark.setRightText(event.getMark());
        } else if (MyEvent.EditInfo.TYPE_NAME.equals(event.getType())) {
            mCivName.setRightText(event.getName());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChooseHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void httpRequestDataSucc() {
        MyEvent.EditInfo event = new MyEvent.EditInfo();
        event.setType(MyEvent.EditInfo.TYPE_ALL);
        event.setName(mCivName.getRightText());
        event.setMark(mCivMark.getRightText());
        if (!TextUtils.isEmpty(mPresenter.getImgUrl())) {
            event.setImageUrl(mPresenter.getImgUrl());
            SPUtils.savaStringData(Constant.Config.AVATAR, mPresenter.getImgUrl());
        }
        SPUtils.savaStringData(Constant.Config.NICK_NAME, mCivName.getRightText());
        EventBus.getDefault().post(event);
        finish();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("nick_name", mCivName.getRightText());
        params.put("autograph", mCivMark.getRightText());
        return params;
    }
}
