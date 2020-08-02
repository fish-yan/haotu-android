package com.dmeyc.dmestoreyfm.newui.release.course;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.release.goods.IReleaseGoodsView;
import com.dmeyc.dmestoreyfm.newui.release.goods.ReleaseGoodsPresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.UploadPictureView;
import com.dmeyc.dmestoreyfm.wedgit.dialog.DateSelectorDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/1
 */
public class ReleaseCourseActivity extends BaseMvpActivity<IReleaseGoodsView, ReleaseGoodsPresenter> implements IReleaseGoodsView, DateSelectorDialog.OnClickListener {

    @Bind(R.id.civ_course_theme)
    CustomItemView mCivCourseTheme;
    @Bind(R.id.civ_person_count)
    CustomItemView mCivPersonCount;
    @Bind(R.id.civ_cost)
    CustomItemView mCivCost;
    @Bind(R.id.civ_address)
    CustomItemView mCivAddress;
    @Bind(R.id.civ_time)
    CustomItemView mCivTime;
    @Bind(R.id.civ_validity)
    CustomItemView mCivValidity;
    @Bind(R.id.civ_tag)
    CustomItemView mCivTag;
    @Bind(R.id.et_instruction)
    EditText mEtInstruction;
    @Bind(R.id.uploadPictureView)
    UploadPictureView mUploadPictureView;

    private ImageChooserHelper mChooseHelper;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ReleaseCourseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected ReleaseGoodsPresenter createPresenter() {
        return new ReleaseGoodsPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_release_course;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithBothText("取消发布", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()) {
                    mPresenter.uploadImage(mUploadPictureView.getImgPath());
                }
            }
        });
        mUploadPictureView.setListener(new UploadPictureView.AddClickListerner() {
            @Override
            public void onAddClick() {
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
            }
        });
        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                mUploadPictureView.loadImage(path);
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

        if (TextUtils.isEmpty(mCivCourseTheme.getTitle())) {
            ToastUtil.show("请输入课程主题");
            return false;
        }

        if (TextUtils.isEmpty(mCivPersonCount.getTitle())) {
            ToastUtil.show("请输入上课人数");
            return false;
        }

        if (TextUtils.isEmpty(mCivCost.getTitle())) {
            ToastUtil.show("请输入课程费用");
            return false;
        }

        if (TextUtils.isEmpty(mCivAddress.getTitle())) {
            ToastUtil.show("请输入上课地址");
            return false;
        }

        if (TextUtils.isEmpty(mCivTime.getTextTitle())) {
            ToastUtil.show("请选择上课时间");
            return false;
        }

        if (TextUtils.isEmpty(mCivValidity.getTextTitle())) {
            ToastUtil.show("请选择课程有效期");
            return false;
        }

        if (TextUtils.isEmpty(mCivTag.getTitle())) {
            ToastUtil.show("请选择课程标签");
            return false;
        }

        if (TextUtils.isEmpty(mEtInstruction.getText().toString())) {
            ToastUtil.show("请输入课程介绍");
            return false;
        }

        if (TextUtils.isEmpty(mUploadPictureView.getImgPath())) {
            ToastUtil.show("请上传课程封面");
            return false;
        }

        return true;
    }

    @Override
    public void httpDataSucc() {
        ToastUtil.show("发布成功");
        EventBus.getDefault().post(new RefreshEvent.Release(RefreshEvent.Release.TYPE_COURSE));
        finish();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", mCivCourseTheme.getTitle());
        params.put("peopleNumber", mCivPersonCount.getTitle());
        params.put("price", mCivCost.getTitle());
        params.put("address", mCivAddress.getTitle());
        params.put("startTime", mCivTime.getTextTitle());
        params.put("endTime", mCivValidity.getTextTitle());
        params.put("tag", mCivTag.getTitle());
        params.put("remark", mEtInstruction.getText().toString());
        params.put("type", "2");// 1商品、2课程
        return params;
    }


    @OnClick({R.id.civ_time, R.id.civ_validity})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.civ_time:
                new DateSelectorDialog.Build(this).setView(view).setListener(this).create().show();
                break;
            case R.id.civ_validity:
                new DateSelectorDialog.Build(this).setView(view).setListener(this).create().show();
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
    public void onConfirm(View view, String time) {
        ((CustomItemView) view).setTitle(time);
    }
}
