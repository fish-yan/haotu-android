package com.dmeyc.dmestoreyfm.photolive;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.photolive.adapter.LivingSelectorAdapter;
import com.dmeyc.dmestoreyfm.photolive.mtp.PicInfo;
import com.dmeyc.dmestoreyfm.photolive.utils.LogSaveUtil;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import butterknife.Bind;


/**
 * create by cxg on 2019/12/26
 */
public class LivingActivity extends BaseMvpActivity<ILivingView, LivingPresenter> implements ILivingView {
    private static final String TAG = "LivingActivity";
    private String mActivityId;

    @Bind(R.id.rv_images)
    RecyclerView mRecycleView;
    private LivingSelectorAdapter mAdapter;
    List<PicInfo> mUploadList = new ArrayList<>();
    List<PicInfo> mUploadListTemp = new ArrayList<>();
    List<PicInfo> mSelectorList = new ArrayList<>();


    private boolean isUping = false;
    private int totalCount = 0;
    private int totalUploadCount = 0;

    public static void newInstance(Context context, String activityId) {
        Intent intent = new Intent(context, LivingActivity.class);
        intent.putExtra(ExtraKey.ACTIVITY_ID, activityId);
        context.startActivity(intent);
    }


    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected LivingPresenter createPresenter() {
        return new LivingPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_living;
    }

    @Override
    protected void initViews() {
        LogSaveUtil.d(TAG, ">>>>>>>>>>>>>>>>>> 进入 initViews");
        setTitleWithRightText("直播中。。。", "上传", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtil.isFastClick()) {
                    return;
                }

                List<PicInfo> list = new ArrayList<>();
                LogSaveUtil.d(TAG, "选中上传的个数》》》》》》》》》" + mUploadList.size());
                for (PicInfo bean : mUploadList) {
                    if (bean.isCheck()) {
                        list.add(bean);
                    }
                }
                if (list.size() == 0) {
                    ToastUtil.show("请选择要上传的图片");
                    return;
                }
                mSelectorList.clear();
                mSelectorList.addAll(list);
                mPresenter.uploadImage1(list);
            }
        });
        mActivityId = getIntent().getStringExtra(ExtraKey.ACTIVITY_ID);

        mAdapter = new LivingSelectorAdapter(this);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycleView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecycleView);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void uploadLivingImage1(MyEvent.LivingPath1 event) {
        totalCount += event.picInfo.size();
        if (isUping) {
            mUploadListTemp.addAll(0, event.picInfo);
        } else {
            mUploadList.addAll(0, event.picInfo);
            mAdapter.replaceData(mUploadList);
        }

    }

    @Override
    public String getActivityId() {
        if (mActivityId == null)
            return "";
        return mActivityId;
    }

    @Override
    public void beginUpload() {
        isUping = true;
        LoadingUtils.showProgressDialog(LivingActivity.this, "上传中");
    }

    @Override
    public void endUpload() {
        isUping = false;
        mSelectorList.clear();
        LoadingUtils.cancelProgressDialog();
    }

    @Override
    public void upLoadImageSucc() {
        totalUploadCount += mSelectorList.size();
        CopyOnWriteArrayList<PicInfo> removeList = new CopyOnWriteArrayList<>(mUploadList);
        for (PicInfo picInfo : removeList) {
            if (picInfo.isCheck()) {
                removeList.remove(picInfo);
            }
        }
        mUploadList.clear();
        mUploadList.addAll(removeList);
        if (mUploadListTemp.size() != 0) {
            mUploadList.addAll(0, mUploadListTemp);
            mUploadListTemp.clear();
        }
        mAdapter.replaceData(mUploadList);
        endUpload();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogSaveUtil.d(TAG,"totalImageSize : >>>>>>>>>"  + totalCount);
        LogSaveUtil.d(TAG,"totalUploadCount : >>>>>>>>>"  + totalUploadCount);

    }
}
