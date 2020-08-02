package com.dmeyc.dmestoreyfm.newui.release.video;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.photo.NoScrollGridView;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.release.friend.AtFriendActivity;
import com.dmeyc.dmestoreyfm.newui.release.relation.RelationActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.CustomDialogTools;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.video.releasedynamic.ChoiceImgAdapter;
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;
import com.dmeyc.dmestoreyfm.video.topic.TopicListActivity;
import com.dmeyc.dmestoreyfm.video.videolist.HtVideoEditChooseActivity;
import com.dmeyc.dmestoreyfm.video.videoupload.TXUGCPublishTypeDef;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;
import com.tencent.liteav.demo.videorecord.TCVideoRecordActivity;
import com.tencent.liteav.demo.videorecord.utils.TCConstants;
import com.tencent.liteav.demo.videorecord.videopreview.TCVideoPreviewActivity;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.ugc.TXRecordCommon;
import com.xsm.library.TEditText;
import com.xsm.library.TObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseVideoActivity extends BaseMvpActivity<IReleaseVideoView, ReleaseVideoPresenter> implements IReleaseVideoView {

    public final static int TYPE_OF_PIC = 0;
    public final static int TYPE_OF_VIDEO = 1;
    private int mType = TYPE_OF_PIC;

    /**
     * 初始化视频相关
     */
    private String[] items;
    private int[] itemTags;
    private String mVideoPath;
    private String mVideoCoverPath;

    private String mGroupId;
    private String mGroupType;

    private ImageChooserHelper mChooseHelper;
    private ArrayList<String> mLocalSelectedPic = new ArrayList<>();// 我选中的本地的图片，纯路径
    private ArrayList<String> mShowList = new ArrayList<>();//展示用

    @Bind(R.id.gv_choose_av)
    NoScrollGridView mShowChoosePicGridView;

    @Bind(R.id.ed_content)
    TEditText mEdContent;

    @Bind(R.id.tv_relation)
    TextView mTvRelation;
    @Bind(R.id.tv_address_detail)
    TextView mTvAddressDetail;

    @Bind(R.id.iv_add_location)
    ImageView mIvAddLocation;

    private ChoiceImgAdapter mImgAdapter;

    public static void newInstance(Context context, int type) {
        Intent intent = new Intent(context, ReleaseVideoActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    @Override
    protected ReleaseVideoPresenter createPresenter() {
        return new ReleaseVideoPresenter();
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
        return R.layout.activity_release_video;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithBothText("取消发布", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release();
            }
        });

        mType = getIntent().getIntExtra("type", TYPE_OF_PIC);
        switch (mType) {
            case TYPE_OF_PIC:
                initReleasePicType();
                break;
            case TYPE_OF_VIDEO:
// 初始化视频相关控件
                initReleaseVideoType();
                break;
            default:
        }
    }

    private void release() {
        // 发布
        if (mType == TYPE_OF_PIC) {
            // 图片
            if (mLocalSelectedPic.size() == 0) {
                ToastUtil.show("请上传您要发布的图片");
                return;
            } else {
                // 先上传图片
                mPresenter.setType(TYPE_OF_PIC);
                mPresenter.upLoadImages(mLocalSelectedPic);
            }
        } else {
            // 视频
            mPresenter.setType(TYPE_OF_VIDEO);
            if (!TextUtils.isEmpty(mVideoPath)) {
                // 获取签名
                mPresenter.getGenSign();
            } else {
                ToastUtil.show("请上传您要发布的视频");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventVideoBean event) {
        String msg = event.getKey();
        if (Constant.HotUEventKeys.VIDEO_EDIT_CHOICE_SUCCESS.equals(msg)) {
            // 获取数据成功
            mVideoPath = event.getVideopath();
            mVideoCoverPath = event.getImgpath();
            if (!TextUtils.isEmpty(mVideoPath)) {
                mShowList.clear();
            }
            if (!TextUtils.isEmpty(mVideoCoverPath)) {
                mShowList.add(mVideoCoverPath);
            } else {
                mShowList.add("-1");
            }
            if (mImgAdapter != null) {
                mImgAdapter.notifyDataSetChanged();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(EventVideoBean event) {
        String msg = event.getKey();
        if (Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC.equals(msg) || Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_CREATE.equals(msg)) {
            //选择完话题
            String topic = event.getTopic();
            if (!TextUtils.isEmpty(topic)) {
                TopicInEditBean object = new TopicInEditBean();
                object.setObjectRule("#");
                object.setTopicId(event.getTopicId());
                object.setObjectText(topic);
                object.setType(TopicInEditBean.TYPE_TOPIC);
                mEdContent.setObject(object);
            }
        }

    }

    // @ 好友
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAtFriend(MyEvent.ReleaseVideo event) {
        mEdContent.setObject(event.mTopicInEditBean);
    }

    // 关联商户
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRelationResult(MyEvent.ReleaseRelation event) {
        mGroupId = event.groupId;
        mGroupType = event.type;
        if (event.groupName != null) {
            mTvRelation.setText(event.groupName);
        }
    }


    @OnClick({R.id.iv_to_friend, R.id.iv_topic, R.id.iv_add_location, R.id.cl_relation,R.id.tv_address_detail})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_to_friend:
                AtFriendActivity.newInstance(this,AtFriendActivity.TYPE_RELEASE);
                break;

            case R.id.iv_topic:
                TopicListActivity.newIntent(ReleaseVideoActivity.this);
                break;

            case R.id.iv_add_location:
                mIvAddLocation.setVisibility(View.GONE);
                mTvAddressDetail.setVisibility(View.VISIBLE);
                mTvAddressDetail.setText(DataClass.LocationAddress);
                break;
            case R.id.tv_address_detail:
                mIvAddLocation.setVisibility(View.VISIBLE);
                mTvAddressDetail.setVisibility(View.GONE);
                break;

            case R.id.cl_relation:
                RelationActivity.newInstance(this);
                break;

            default:
        }
    }

    @Override
    public void upLoadSucc() {

    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("content", mEdContent.getText().toString().trim());
        getTopicIds(params);
        if (mGroupId != null)
            params.put("groupId", mGroupId);// 商户id,与groupType匹配，2模板商户id，其他，真实商户id
        if (mGroupType != null)
            params.put("groupType", mGroupType);// 商户类别（1真实商户 2模板商户）
        return params;
    }

    @Override
    public void publishDynamicSucc() {
        ToastUtil.show("发布成功");
        EventBus.getDefault().post(new RefreshEvent.Release(RefreshEvent.Release.TYPE_VIDEO));
        finish();
    }

    @Override
    public TXUGCPublishTypeDef.TXPublishParam getTXPublishParam() {

        // 文件发布默认是采用断点续传
        TXUGCPublishTypeDef.TXPublishParam param = new TXUGCPublishTypeDef.TXPublishParam();
        // 录制生成的视频文件路径, ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
        param.videoPath = mVideoPath;
        // 录制生成的视频首帧预览图，ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
        param.coverPath = mVideoCoverPath;
        return param;
    }

    /**
     * 发布图片初始化相关
     */
    private void initReleasePicType() {
        // 初始化图片相关
        mImgAdapter = new ChoiceImgAdapter(ReleaseVideoActivity.this, R.layout.item_dynamic_topic_layout, mShowList, TYPE_OF_PIC);
        mShowChoosePicGridView.setAdapter(mImgAdapter);
        initChooseHelper();
        setShowList();
        mShowChoosePicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (ChoiceImgAdapter.IS_ADD_PIC.equals(mShowList.get(i))) {
                    if (mType == TYPE_OF_PIC) {
                        mChooseHelper.setChoosedImages(mLocalSelectedPic);
                        mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
                    }
                }
            }
        });
        mImgAdapter.setOnDeleteClickListener(new ChoiceImgAdapter.onDeleteListener() {
            @Override
            public void onDelete(int position) {
                mLocalSelectedPic.remove(position);
                setShowList();
            }
        });
    }

    /**
     * 初始化图片选择器
     */
    private void initChooseHelper() {
        mChooseHelper = new ImageChooserHelper(ReleaseVideoActivity.this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                mLocalSelectedPic.add(path);
                setShowList();
            }

            @Override
            public void onChooseOver(ArrayList<String> paths, String is_check_original_image) {
                mLocalSelectedPic.clear();
                if (paths != null) {
                    mLocalSelectedPic.addAll(paths);
                    setShowList();
                }
            }
        });
        mChooseHelper.setSelectMaxCount(9);
        mChooseHelper.selectFromAblum(true);
        mChooseHelper.setNeedCrop(false);
    }

    /***
     * 设置展示图片
     */
    private void setShowList() {
        mShowList.clear();
        mShowList.addAll(mLocalSelectedPic);
        if (mLocalSelectedPic.size() < 9) {
            mShowList.add(ChoiceImgAdapter.IS_ADD_PIC);
        }
        mImgAdapter.notifyDataSetChanged();
    }


    /**
     * 视频相关
     */
    private void initReleaseVideoType() {
        items = new String[]{"录制", "相册选择", "取消"};
        itemTags = new int[]{1, 2, 3};
        mShowList.add(ChoiceImgAdapter.IS_ADD_PIC);
        mImgAdapter = new ChoiceImgAdapter(ReleaseVideoActivity.this, R.layout.item_dynamic_topic_layout, mShowList, TYPE_OF_VIDEO);
        mShowChoosePicGridView.setAdapter(mImgAdapter);
        mImgAdapter.setOnDeleteClickListener(new ChoiceImgAdapter.onDeleteListener() {
            @Override
            public void onDelete(int position) {
                mShowList.remove(position);
                mVideoPath = null;
                mVideoCoverPath = null;
                if (mShowList.size() == 0) {
                    mShowList.add(ChoiceImgAdapter.IS_ADD_PIC);
                }
                mImgAdapter.notifyDataSetChanged();
            }
        });
        mShowChoosePicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (ChoiceImgAdapter.IS_ADD_PIC.equals(mShowList.get(i))) {
                    if (mType == TYPE_OF_VIDEO) {
                        //
                        CustomDialogTools.createCustomDialog(ReleaseVideoActivity.this, items, itemTags, "", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final int idx = (Integer) view.getTag();
                                switch (idx) {
                                    case 1:
                                        getConfigData();
                                        startVideoRecordActivity();
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent(ReleaseVideoActivity.this, HtVideoEditChooseActivity.class);
                                        startActivity(intent2);
                                        // 视频列表页
                                        break;
                                    case 3:
                                        break;
                                }
                                Dialog dlg = (Dialog) view.getTag(R.id.tag_first);
                                dlg.dismiss();
                            }
                        });
                    }
                } else {
                    if (!TextUtils.isEmpty(mVideoPath)) {
                        // 跳转至视频预览界面
                        startPreview();
                    }
                }
            }
        });
    }

    private void startPreview() {
        Intent intent = new Intent(getApplicationContext(), TCVideoPreviewActivity.class);
        intent.putExtra(TCConstants.VIDEO_RECORD_TYPE, TCConstants.VIDEO_RECORD_TYPE_UGC_RECORD);
        intent.putExtra(TCConstants.VIDEO_RECORD_VIDEPATH, mVideoPath);
        intent.putExtra(TCConstants.VIDEO_RECORD_COVERPATH, mVideoCoverPath);
        if (mRecommendQuality == TXRecordCommon.VIDEO_QUALITY_LOW) {
            intent.putExtra(TCConstants.VIDEO_RECORD_RESOLUTION, TXRecordCommon.VIDEO_RESOLUTION_360_640);
        } else if (mRecommendQuality == TXRecordCommon.VIDEO_QUALITY_MEDIUM) {
            intent.putExtra(TCConstants.VIDEO_RECORD_RESOLUTION, TXRecordCommon.VIDEO_RESOLUTION_540_960);
        } else if (mRecommendQuality == TXRecordCommon.VIDEO_QUALITY_HIGH) {
            intent.putExtra(TCConstants.VIDEO_RECORD_RESOLUTION, TXRecordCommon.VIDEO_RESOLUTION_720_1280);
        } else {
            intent.putExtra(TCConstants.VIDEO_RECORD_RESOLUTION, mRecordResolution);
        }
        startActivity(intent);
    }

    /**
     * 录制参数配置
     */
    private int mRecommendQuality = -1;
    private int mAspectRatio; // 视频比例
    private int mRecordResolution; // 录制分辨率
    private int mBiteRate = 2400; // 码率
    private int mFps = 20; // 帧率
    private int mGop = 3; // 关键帧间隔

    private void getConfigData() {
        // 使用提供的三挡质量设置，不需要传以下参数，sdk内部已定义
        if (mRecommendQuality != -1) {
            return;
        }
        String fps = "20";
        String gop = "3";
        String bitrate = "6500";

        if (!TextUtils.isEmpty(bitrate)) {
            try {
                mBiteRate = Integer.parseInt(bitrate);
                if (mBiteRate < 600) {
                    mBiteRate = 600;
                } else if (mBiteRate > 12000) {
                    mBiteRate = 12000;
                }
            } catch (NumberFormatException e) {
                TXCLog.e("TAG", "NumberFormatException");
            }
        } else {
            mBiteRate = 6500;
        }

        if (!TextUtils.isEmpty(fps)) {
            try {
                mFps = Integer.parseInt(fps);
                if (mFps < 15) {
                    mFps = 15;
                } else if (mFps > 30) {
                    mFps = 20;
                }
            } catch (NumberFormatException e) {
                TXCLog.e("TAG", "NumberFormatException");
            }
        } else {
            mFps = 20;
        }
        if (!TextUtils.isEmpty(gop)) {
            try {
                mGop = Integer.parseInt(gop);
                if (mGop < 1) {
                    mGop = 1;
                } else if (mGop > 10) {
                    mGop = 3;
                }
            } catch (NumberFormatException e) {
                TXCLog.e("TAG", "NumberFormatException");
            }
        } else {
            mGop = 3;
        }
    }

    /**
     * 跳转录制页面
     */
    private void startVideoRecordActivity() {
        Intent intent = new Intent(this, TCVideoRecordActivity.class);
        intent.putExtra(TCConstants.RECORD_CONFIG_MIN_DURATION, 3 * 1000);
        intent.putExtra(TCConstants.RECORD_CONFIG_MAX_DURATION, 15 * 1000);
        intent.putExtra(TCConstants.RECORD_CONFIG_ASPECT_RATIO, mAspectRatio);
        if (mRecommendQuality != -1) {
            // 提供的三挡设置
            intent.putExtra(TCConstants.RECORD_CONFIG_RECOMMEND_QUALITY, mRecommendQuality);
        } else {
            // 自定义设置
            intent.putExtra(TCConstants.RECORD_CONFIG_RESOLUTION, mRecordResolution);
            intent.putExtra(TCConstants.RECORD_CONFIG_BITE_RATE, mBiteRate);
            intent.putExtra(TCConstants.RECORD_CONFIG_FPS, mFps);
            intent.putExtra(TCConstants.RECORD_CONFIG_GOP, mGop);
        }
        intent.putExtra(TCConstants.RECORD_CONFIG_HOME_ORIENTATION, TXLiveConstants.VIDEO_ANGLE_HOME_DOWN); // 竖屏录制
        intent.putExtra(TCConstants.RECORD_CONFIG_TOUCH_FOCUS, true);
        intent.putExtra(TCConstants.RECORD_CONFIG_NEED_EDITER, true);
        startActivity(intent);
    }


    /**
     * 设置topicId、atFriend、atSystem
     */
    private void getTopicIds(Map<String, String> params) {
        StringBuffer sbTopic = new StringBuffer();
        StringBuffer sbPhone = new StringBuffer();
        StringBuffer sbSystemUser = new StringBuffer();

        List<TObject> mTopicList = mEdContent.getObjects();
        if (mTopicList.size() > 0) {
            for (int i = 0; i < mTopicList.size(); i++) {
                TObject tObject = mTopicList.get(i);
                if (tObject instanceof TopicInEditBean) {
                    TopicInEditBean bean = (TopicInEditBean) tObject;
                    switch (bean.getType()) {
                        case TopicInEditBean.TYPE_TOPIC:
                            sbTopic.append(bean.getTopicId()).append(",");
                            break;
                        case TopicInEditBean.TYPE_SYSTEM_FRIEND:
                            sbSystemUser.append(bean.getName()).append(",");
                            break;
                        case TopicInEditBean.TYPE_CONTACT:
                            sbPhone.append(bean.getName()).append(":").append(bean.getPhone()).append(",");
                            break;
                    }
                }
            }
        }

        if (sbTopic.length() > 0) {
            params.put("topicids", sbTopic.substring(0, sbTopic.length() - 1));// 话题id，以逗号分割
        }
        if (sbPhone.length() > 0) {
            params.put("atPhoneNos", sbPhone.substring(0, sbPhone.length() - 1));// @电话号码，多个以逗号隔开，格式为：备注:电话号码,备注:电话号码
        }
        if (sbSystemUser.length() > 0) {
            params.put("atSystemUser", sbSystemUser.substring(0, sbSystemUser.length() - 1));// @系统用户，多个以逗号隔开
        }
    }
}
