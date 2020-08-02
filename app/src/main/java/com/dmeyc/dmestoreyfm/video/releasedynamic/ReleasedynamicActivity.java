package com.dmeyc.dmestoreyfm.video.releasedynamic;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.photo.NoScrollGridView;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.CustomDialogTools;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.video.releasedynamic.associated.AssociatedBean;
import com.dmeyc.dmestoreyfm.video.releasedynamic.associated.AssociatedListActivity;
import com.dmeyc.dmestoreyfm.video.releasedynamic.associated.AssociatedListAdapter;
import com.dmeyc.dmestoreyfm.video.topic.TopicListActivity;
import com.dmeyc.dmestoreyfm.video.videolist.HtVideoEditChooseActivity;
import com.dmeyc.dmestoreyfm.video.videoupload.TXUGCPublish;
import com.dmeyc.dmestoreyfm.video.videoupload.TXUGCPublishTypeDef;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.google.gson.Gson;
import com.tamic.novate.Throwable;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.demo.videoediter.TCVideoEditChooseActivity;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReleasedynamicActivity extends BaseActivity {
    public final static int TYPE_OF_PIC = 0;
    public final static int TYPE_OF_VIDEO = 1;
    private int mType = TYPE_OF_PIC;

    public static void newIntent(Activity activity, int type) {
        Intent intent = new Intent(activity, ReleasedynamicActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private ReleaseDynamicRelationAdapter mAdapter;
    private ArrayList<RelationBean> mListData = new ArrayList<>();

    private ArrayList<AssociatedBean.DataBean> mAssociateListData = new ArrayList<>();
    private AssociatedListAdapter mAssociatedAdapter;

    private int[] mTopicIds;
    private String groupId; // 关联的商户id

    @Bind(R.id.ed_content)
    TEditText ed_content;

    @OnClick(R.id.tv_title)
    void onToSaveClick() {
        // 保存
    }

    @OnClick(R.id.tv_publish)
    void onToPublishClick() {
        // 发布
        if (mType == TYPE_OF_PIC) {
            // 图片
            if (mLocalSelectedPic.size() == 0) {
                ToastUtil.show("请上传您要发布的图片");
                return;
            } else {
                // 先上传图片
              upLoadFile(mLocalSelectedPic);
            }
        } else {
            // 视频
            if (!TextUtils.isEmpty(mVideoPath)) {
                // 获取签名
                getGenSign();
            } else {
                ToastUtil.show("请上传您要发布的视频");
            }
        }
    }

    @OnClick(R.id.tv_to_more)
    void toRelationClick() {
        AssociatedListActivity.newIntent(ReleasedynamicActivity.this,mAssociateBean);
    }

    @OnClick(R.id.tv_addTopic)
    void toAddTopicClick() {
        // 去关联话题
        TopicListActivity.newIntent(ReleasedynamicActivity.this);
    }

    @Bind(R.id.mShowChoosePicGridView)
    NoScrollGridView mShowChoosePicGridView;
    private ChoiceImgAdapter mImgAdapter;

    @Bind(R.id.lv_relation)
    NoScrollListView mLvRelation;

    @Bind(R.id.tv_show_asso)
    TextView mAssoTv;

    /**
     图片上传相关
     */

    private String mImages;
    private ImageChooserHelper mChooseHelper;
    private ArrayList<String> mLocalSelectedPic = new ArrayList<>();// 我选中的本地的图片，纯路径
    private ArrayList<String> mShowList = new ArrayList<>();//展示用

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_release_dynamic_layout;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(ReleasedynamicActivity.this);
        mType = getIntent().getIntExtra("type", TYPE_OF_PIC);
        if (mType == TYPE_OF_PIC) {
            initReleasePicType();
        } else {
            // 初始化视频相关控件
            initReleaseVideoType();
        }
        initAssoList();
        getTopicList();
    }

    private String mVideoPath;
    private String mVideoCoverPath;

    /**
     * 事件响应方法
     * 接收消息
     * @param event
     */
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
        if (Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC.equals(msg) || Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_CREATE.equals(msg)) {
            //选择完话题
            String topic = event.getTopic();
            if (!TextUtils.isEmpty(topic)) {
                TopicInEditBean object = new TopicInEditBean();
                object.setObjectRule("#");
                object.setTopicId(event.getTopicId());
                object.setObjectText(topic);
                ed_content.setObject(object);
            }
        }

        if (Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_ASSOCIATED.equals(msg) || Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_ASSOCIATED_CREATE.equals(msg)){
            //选择完关联商户
            AssociatedBean.DataBean bean = new AssociatedBean.DataBean();
            bean.setId(event.getAssobusinessId());
            bean.setType(event.getType());
            bean.setName(event.getName());
            bean.setDistance(event.getDistance());
            setmChoiceAssociateBean(bean);
            getTopicList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private AssociatedBean.DataBean mAssociateBean;
    private int mCurrentSelectPosition = -1;

    /**
     * 初始化关联列表
     */
    private void initAssoList() {
        mAssociatedAdapter = new AssociatedListAdapter(ReleasedynamicActivity.this, R.layout.item_associated_list_layout, mAssociateListData);
        mLvRelation.setAdapter(mAssociatedAdapter);
        mLvRelation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 设置选中的关联商户
                AssociatedBean.DataBean bean = mAssociateListData.get(position);
                if (mCurrentSelectPosition == -1) {
                    mAssociateListData.get(position).setSelected(true);
                    mCurrentSelectPosition = position;
                    mAssociatedAdapter.notifyDataSetChanged();
                    setmChoiceAssociateBean(bean);
                } else {
                    if (mCurrentSelectPosition == position) {
                        // 点击的是同一个
                        if (bean.isSelected()) {
                            mAssociateListData.get(position).setSelected(false);
                        } else {
                            mAssociateListData.get(position).setSelected(true);
                        }
                        mAssociatedAdapter.notifyDataSetChanged();
                        if (bean.isSelected()) {
                            mCurrentSelectPosition = position;
                            setmChoiceAssociateBean(bean);
                        } else {
                            mCurrentSelectPosition = -1;
                            setmChoiceAssociateBean(null);
                        }
                    } else {
                        mAssociateListData.get(mCurrentSelectPosition).setSelected(false);
                        mAssociateListData.get(position).setSelected(true);
                        mCurrentSelectPosition = position;
                        mAssociatedAdapter.notifyDataSetChanged();
                        setmChoiceAssociateBean(bean);
                    }
                }
            }
        });
    }

    /**
     * 设置当前选中的商户，及文字显示
     *
     * @param bean
     */
    private void setmChoiceAssociateBean(AssociatedBean.DataBean bean) {
        if (bean != null) {
            mAssociateBean = bean;
            String name = mAssociateBean.getName();
            mAssoTv.setText("@" + name);
            mAssoTv.setTextColor(Color.parseColor("#32aaff"));
        } else {
            mAssoTv.setText("请选择你要关联的门店");
            mAssoTv.setTextColor(Color.parseColor("#777777"));
        }
    }

    /**
     * 获取关联列表数据
     */
    private void getTopicList() {
        final ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("page", 1);
        mParams.addParams("size", 50);
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LONGTUDE))) {
            mParams.addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LATITUDE))) {
            mParams.addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        }
        RestClient.getYfmNovate(ReleasedynamicActivity.this).post(Constant.API.GET_ASSOCIATED_DATA, mParams.build(),
                new DmeycBaseSubscriber<AssociatedBean>() {
                    @Override
                    public void onSuccess(AssociatedBean bean) {
                        //
                        mAssociateListData.clear();
                        if (bean.getData() != null && bean.getData().size() > 0) {
                            mAssociateListData.addAll(bean.getData());
                        }
//                        private AssociatedBean.DataBean mAssociateBean;
//                        private int mCurrentSelectPosition = -1;
                        // 选择完成关联后，回显
                        if(mAssociateBean != null){
                             for(int i = 0 ; i < mAssociateListData.size() ; i++){
                                 mAssociateListData.get(i).setSelected(false);
                                 if(mAssociateBean.getId() == mAssociateListData.get(i).getId()){
                                     mCurrentSelectPosition = i;
                                     mAssociateListData.get(i).setSelected(true);
                                 }
                             }
                        }else{
                            if (mAssociateListData.size() == 0) {
                                mAssoTv.setText("附近暂无可关联商户");
                                mAssoTv.setTextColor(Color.parseColor("#777777"));
                            }
                        }
                        mAssociatedAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 发布图片初始化相关
     */
    private void initReleasePicType() {
        // 初始化图片相关
        mImgAdapter = new ChoiceImgAdapter(ReleasedynamicActivity.this, R.layout.item_dynamic_topic_layout, mShowList, TYPE_OF_PIC);
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
     * 初始化图片选择器
     */
    private void initChooseHelper() {
        mChooseHelper = new ImageChooserHelper((BaseActivity) ReleasedynamicActivity.this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
//                reFreshAdapter();
                mLocalSelectedPic.add(path);
                setShowList();
            }

            @Override
            public void onChooseOver(ArrayList<String> paths, String is_check_original_image) {
                mLocalSelectedPic.clear();
                if (paths != null) {
//                    reFreshAdapter();
                    mLocalSelectedPic.addAll(paths);
                    setShowList();
                }
            }
        });
        mChooseHelper.setSelectMaxCount(9);
        mChooseHelper.selectFromAblum(true);
        mChooseHelper.setNeedCrop(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mChooseHelper != null) {
            mChooseHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 通过上传的文件的完整路径生成RequestBody
     * 多文件
     * @param fileNames 完整的文件路径
     * @return
     */
    private RequestBody getRequestBody(List<String> fileNames) {
        //创建MultipartBody.Builder，用于添加请求的数据
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (int i = 0; i < fileNames.size(); i++) { //对文件进行遍历
            File file = new File(fileNames.get(i)); //生成文件
            //根据文件的后缀名，获得文件类型
            builder.addFormDataPart( //给Builder添加上传的文件
                    "file", file.getName(), //文件的文字，服务器端用来解析的
                    RequestBody.create(MediaType.parse("image/*"), file)); //创建RequestBody，把上传的文件放入
        }
        builder.addFormDataPart("isLogo", 0 + "")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        return builder.build(); //根据Builder创建请求
    }

    /**
     * 通过上传的文件的完整路径生成RequestBody
     * 单个文件
     * @param fileNames 完整的文件路径
     * @return
     */
    private RequestBody getRequestSingleBody(String fileNames) {
        //创建MultipartBody.Builder，用于添加请求的数据
        MultipartBody.Builder builder = new MultipartBody.Builder();
        File file = new File(fileNames); //生成文件
            //根据文件的后缀名，获得文件类型
            builder.addFormDataPart( //给Builder添加上传的文件a
                    "file", file.getName(), //文件的文字，服务器端用来解析的
                    RequestBody.create(MediaType.parse("image/*"), file)); //创建RequestBody，把上传的文件放入
        builder.addFormDataPart("isLogo", 1 + "")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
//                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        return builder.build(); //根据Builder创建请求
    }

    /**
     * 获得Request实例
     * @param fileName 完整的文件路径
     * @return
     */
    private Request getRequest(String fileName) {
        Request.Builder builder = new Request.Builder();
        builder.url(
                 Constant.API.YFM_BASE_URL+ "api/v1.0/file/uploadSingle")
                .post(getRequestSingleBody(fileName));
        return builder.build();
    }

    private int upLoadPosition = 0;
    private List<String> mNeedUploadFiles = new ArrayList<>();
    private ArrayList<String> mUpLoadResultImgs = new ArrayList<>();

    /**
     * 根据url，发送异步Post请求
     * @param fileNames 完整的上传的文件的路径名
     */
    public void upLoadFile(List<String> fileNames){
        mNeedUploadFiles.clear();
        LoadingUtils.showProgressDialog(ReleasedynamicActivity.this,"发布中...");
        String fileName = fileNames.get(upLoadPosition);
        mNeedUploadFiles.addAll(fileNames);
        toUpload(fileName);
    }

    public void toUpload(String fileName){
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(getRequest(fileName));
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoadingUtils.cancelProgressDialog();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG==", "success");
                Gson gs = new Gson();
                final ImagePathBean imap = gs.fromJson(response.body().string(), ImagePathBean.class);
                Log.e("upLoadPosition=="+upLoadPosition, imap.getMsg());
                mUpLoadResultImgs.add(imap.getData());
                if(upLoadPosition < (mNeedUploadFiles.size()-1)){
                    upLoadPosition++;
                    toUpload(mNeedUploadFiles.get(upLoadPosition));
                }else{
                    // 上传完成
                    if(mUpLoadResultImgs.size() > 0){
                        publishDynamic(null,null,null,mUpLoadResultImgs,2);
                    }else {
                        ToastUtil.show("获取图片失败，请稍后重试");
                    }
                }
            }
        });
    }


    // 视频相关 start
    /**
     * 初始化视频相关
     */
    String[] items;
    int[] itemTags;

    private void initReleaseVideoType() {
        items = new String[]{"录制", "相册选择", "取消"};
        itemTags = new int[]{1, 2, 3};
        mShowList.add(ChoiceImgAdapter.IS_ADD_PIC);
        mImgAdapter = new ChoiceImgAdapter(ReleasedynamicActivity.this, R.layout.item_dynamic_topic_layout, mShowList, TYPE_OF_VIDEO);
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
                        CustomDialogTools.createCustomDialog(ReleasedynamicActivity.this, items, itemTags, "", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final int idx = (Integer) view.getTag();
                                switch (idx) {
                                    case 1:
                                        getConfigData();
                                        startVideoRecordActivity();
//                                        Intent intent1 = new Intent(ReleasedynamicActivity.this, TCVideoSettingActivity.class);
//                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent(ReleasedynamicActivity.this, HtVideoEditChooseActivity.class);
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

//        String fps = etFps.getText().toString();
//        String gop = etGop.getText().toString();
//        String bitrate = etBitrate.getText().toString();

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
     * 获取上传视频签名
     */
    private void getGenSign() {
        RestClient.getYfmNovate(ReleasedynamicActivity.this).get(Constant.API.VIDEO_GET_GENSIGN,
                new ParamMap.Build()
                        .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .build(), new DmeycBaseSubscriber<GenSignBean>() {
                    @Override
                    public void onSuccess(GenSignBean gensign) {
                        // 获取签名成功，开始上传视频
                        if (!TextUtils.isEmpty(gensign.getData())) {
                            uploadVideoToTencent(gensign.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private TXUGCPublish mVideoPublish;

    /**
     * 上传视频到腾讯云服务器
     */
    private void uploadVideoToTencent(String sign) {
        LoadingUtils.showProgressDialog(ReleasedynamicActivity.this, "发布中...");
        mVideoPublish = new TXUGCPublish(ReleasedynamicActivity.this.getApplicationContext());
        // 文件发布默认是采用断点续传
        TXUGCPublishTypeDef.TXPublishParam param = new TXUGCPublishTypeDef.TXPublishParam();
        param.signature = sign;                        // 需要填写第四步中计算的上传签名
        // 录制生成的视频文件路径, ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
        param.videoPath = mVideoPath;
        // 录制生成的视频首帧预览图，ITXVideoRecordListener 的 onRecordComplete 回调中可以获取
        param.coverPath = mVideoCoverPath;
        mVideoPublish.publishVideo(param);
        mVideoPublish.setListener(new TXUGCPublishTypeDef.ITXVideoPublishListener() {
            @Override
            public void onPublishProgress(long uploadBytes, long totalBytes) {
            }

            @Override
            public void onPublishComplete(TXUGCPublishTypeDef.TXPublishResult result) {
                if (!TextUtils.isEmpty(result.videoURL)) {
                    publishDynamic(result.videoURL, result.coverURL, result.videoId, null,1);
//                   publishDynamic(" http://1300375154.vod2.myqcloud.com/8b1f76ffvodcq1300375154/2b3960825285890794779082739/W2tTQjJ3aFIA.mp4", "http://1300375154.vod2.myqcloud.com/8b1f76ffvodcq1300375154/2b3960825285890794779082739/5285890794779082740.jpg", 1);
                } else {
                    LoadingUtils.cancelProgressDialog();
                    ToastUtil.show(result.descMsg);
                }
            }
        });
    }

    private int groupType = 1;
    private void publishDynamic(String path, String coverurl, String videoid, ArrayList<String> imgLists,int type) {
        if (mAssociateBean != null && mAssociateBean.getId() != 0) {//关联
            groupId = mAssociateBean.getId() + "";
            groupType = mAssociateBean.getType();
        }
        String content = ed_content.getText().toString().trim();
        ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("type", type);
        if (!TextUtils.isEmpty(content)) {
            mParams.addParams("content", content);
        }
        if (!TextUtils.isEmpty(groupId)) {
            mParams.addParams("groupId", groupId);
            mParams.addParams("groupType", groupType);
        }
        getTopicIds();
        if (mTopicIds != null && mTopicIds.length > 0) {
             mParams.addParams("topicids", getTopicIdsParams() );
        }
        if (!TextUtils.isEmpty(DataClass.LocationProvince)) {
            mParams.addParams("province", DataClass.LocationProvince);
        }
        if (!TextUtils.isEmpty(DataClass.LocationCity)) {
            mParams.addParams("city", DataClass.LocationCity);
        }
        if (!TextUtils.isEmpty(DataClass.LocationDistrict)) {
            mParams.addParams("area", DataClass.LocationDistrict);
        }
        if (!TextUtils.isEmpty(DataClass.LocationAddress)) {
            mParams.addParams("address", DataClass.LocationAddress);
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LONGTUDE))) {
            mParams.addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LATITUDE))) {
            mParams.addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        }
        if (type == 1) {
            // 发布视频
            mParams.addParams("url", path);
            mParams.addParams("coverUrl", coverurl);
            mParams.addParams("tencentVideoId", videoid);
        }
        if (type == 2) {
            // 发布图片
           if(mUpLoadResultImgs.size() > 0){
               mParams.addParams("imageUrls", getImageUrlsParams());
           }
        }
        RestClient.getYfmNovate(ReleasedynamicActivity.this).post(Constant.API.PUBLISH_DYNAMIC,
                mParams.build(), new DmeycBaseSubscriber<GenSignBean>() {
                    @Override
                    public void onSuccess(GenSignBean gensign) {
                        LoadingUtils.cancelProgressDialog();
//                        EventBus.getDefault().post(Constant.HotUEventKeys.RELEASE_DYNAMIC_SUCCESS);
//                        ToastUtil.show("发布成功");
                        Toast.makeText(ReleasedynamicActivity.this,"发布成功",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    @Override
                    public void onError(Throwable e) {
                        LoadingUtils.cancelProgressDialog();
//                        ToastUtil.show(e.getMessage());
                    }
                });
    }

    private void getTopicIds() {
        List<TObject> mTopicList = ed_content.getObjects();
        if (mTopicList.size() > 0) {
            mTopicIds = new int[mTopicList.size()];
            for (int i = 0; i < mTopicList.size(); i++) {
                TObject tObject = mTopicList.get(i);
                if (tObject instanceof TopicInEditBean) {
                    TopicInEditBean bean = (TopicInEditBean) tObject;
                    mTopicIds[i] = bean.getTopicId();
                }
            }
        }
    }

    private String getTopicIdsParams() {
        String s = "";
        int num = mTopicIds.length;
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                if (i == num - 1) {
                    s = s + mTopicIds[i];
                } else {
                    s = s + mTopicIds[i] + ",";
                }
            }
        }
        return s;
    }

    private String getImageUrlsParams() {
        String s = "";
        int num = mUpLoadResultImgs.size();
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                if (i == num - 1) {
                    s = s + mUpLoadResultImgs.get(i);
                } else {
                    s = s + mUpLoadResultImgs.get(i) + ",";
                }
            }
        }
        return s;
    }
}
