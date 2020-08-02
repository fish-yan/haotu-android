package com.dmeyc.dmestoreyfm.video.dynamicdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.dynamicdetail.digs.DigsActivity;
import com.dmeyc.dmestoreyfm.video.report.ReportActivity;
import com.dmeyc.dmestoreyfm.video.shpodetail.ShopDetailActivity;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;
import com.dmeyc.dmestoreyfm.video.topicutils.OnTopicClickListener;
import com.dmeyc.dmestoreyfm.video.topicutils.SpanWeiBoTextUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.CommSharePopWin;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.dmeyc.dmestoreyfm.wedgit.RoundAngleImageView;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.liteav.demo.play.v3.SuperPlayerVideoId;
import com.youth.banner.Banner;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class DynamicPlayDetailActivity extends Activity implements SuperPlayerView.OnSuperPlayerViewCallback {

    @Bind(R.id.commentRv)
    RecyclerView commentRv;
    @Bind(R.id.Avatar)
    CircleImageView Avatar;
    @Bind(R.id.shopLayout)
    RelativeLayout shopLayout;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.titleAvatar)
    CircleImageView titleAvatar;
    @Bind(R.id.titleUserName)
    TextView titleUserName;
    @Bind(R.id.titleFocus)
    TextView titleFocus;
    @Bind(R.id.tv_followOne)
    TextView tv_followOne;
    @Bind(R.id.topLayout)
    RelativeLayout topLayout;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.likeCount)
    TextView likeCount;
    @Bind(R.id.commentCount_Top)
    TextView commentCountTop;
    @Bind(R.id.nickName)
    TextView nickName;
    @Bind(R.id.createTime)
    TextView createTime;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.shopName)
    TextView shopName;
    @Bind(R.id.AssociationShop)
    LinearLayout AssociationShop;
    @Bind(R.id.seeCount)
    TextView seeCount;
    @Bind(R.id.shopImage)
    RoundAngleImageView shopImage;
    @Bind(R.id.bottomLayout)
    RelativeLayout bottomLayout;
    @Bind(R.id.tv_digs)
    TextView tvDigs;
    @Bind(R.id.GroupName)
    TextView GroupName;
    @Bind(R.id.activity_venue_address)
    TextView activityVenueAddress;
    @Bind(R.id.shopView)
    CardView shopView;
    @Bind(R.id.average_price)
    TextView averagePrice;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.bottomAvatar)
    CircleImageView bottomAvatar;
    @Bind(R.id.likeView)
    LinearLayout likeView;
    @Bind(R.id.ivLike)
    ImageView ivLike;
    @Bind(R.id.loadMore)
    TextView loadMore;

    @OnClick(R.id.titleFocus)
    void onfocusClick() {
        toFollow();
    }

    @OnClick(R.id.tv_followOne)
    void onfollowClick() {
        toFollow();
    }

    @OnClick(R.id.share)
    void toShareClick() {
        //分享
        toShareDynamic();
    }

    @Bind(R.id.mTopLayout)
    RelativeLayout mTopLayout;

    @Bind(R.id.mViewPlayer)
    SuperPlayerView mViewPlayer;

    @Bind(R.id.mImgLoader)
    Banner mImageLoader;

    @Bind(R.id.mDistanceLayout)
    LinearLayout mDistanceLayout;

    @Bind(R.id.mDistance)
    TextView mDistance;

    private String mCurrentDistance;

    @OnClick(R.id.tv_toReport)
    void toReportClick(){
        if(videoId != -1){
            ReportActivity.newIntent(DynamicPlayDetailActivity.this,videoId+"");
        }else{
            ToastUtil.show("获取数据失败");
        }
    }

    private DynamicDetailModel model;
    private CommentAdapter commentAdapter;
    private List<DynamicCommentModel.DataBean> commentData = new ArrayList<>();

    private int mType;

    public static void newIntent(Activity activity, int id, int type,String distance) {
        Intent intent = new Intent(activity, DynamicPlayDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("distance", distance);
        activity.startActivity(intent);
    }

    private int videoId = -1;
    private int page = 1;

    @OnClick(R.id.tv_digs)
    void onDigsClick() {
        // 点赞
        DigsActivity.newIntent(DynamicPlayDetailActivity.this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                // 隐藏状态栏
                // 定义全屏参数
                int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
                // 获得当前窗体对象
                Window window = DynamicPlayDetailActivity.this.getWindow();
                window.setFlags(flag, flag);
//                Window window = getWindow();
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(getResources().getColor(R.color.black));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ac_dynamic_detail);
        ButterKnife.bind(this);
        initData();
        setUerImage();
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
////            Toast.makeText(getApplicationContext(), "横屏", Toast.LENGTH_SHORT).show();
//            // 隐藏标题栏
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            // 隐藏状态栏
//            // 定义全屏参数
//            int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            // 获得当前窗体对象
//            Window window = DynamicPlayDetailActivity.this.getWindow();
//            // 设置当前窗体为全屏显示
//            window.setFlags(flag, flag);
//        } else {
////            Toast.makeText(getApplicationContext(), "竖屏", Toast.LENGTH_SHORT).show();
//        }
//    }


    public void initData() {
        videoId = getIntent().getIntExtra("id", 0);
        mType = getIntent().getIntExtra("type", 2);
        mCurrentDistance = getIntent().getStringExtra("distance");
        Log.e("videoId==", videoId + "");
        if (mType == 1) {
            // 视频布局
            mImageLoader.setVisibility(GONE);
            mViewPlayer.setVisibility(VISIBLE);
            mTopLayout.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            // 图片布局
            mImageLoader.setVisibility(VISIBLE);
            mViewPlayer.setVisibility(GONE);
            mTopLayout.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }
        if(!TextUtils.isEmpty(DataClass.LocationDistrict)
                && !TextUtils.isEmpty(mCurrentDistance)
        ){
            mDistanceLayout.setVisibility(VISIBLE);
            mDistance.setText(mCurrentDistance);
        }else{
            mDistanceLayout.setVisibility(GONE);
        }
        //初始化评论的RecyclerView
        commentRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        commentAdapter = new CommentAdapter(this, R.layout.item_comment_layout, commentData);
        commentRv.setAdapter(commentAdapter);
        commentAdapter.notifyDataSetChanged();
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 0) {//未滑动
                    back.setImageResource(R.drawable.camera_back);
                    topLayout.setBackgroundColor(ContextCompat.getColor(DynamicPlayDetailActivity.this, R.color.transparency));
                    titleUserName.setVisibility(GONE);
                    titleAvatar.setVisibility(GONE);
                    titleFocus.setVisibility(GONE);
                    share.setVisibility(VISIBLE);
                } else if (scrollY >= mTopLayout.getHeight()) { //滑动过程中 并且在mHeight之内
                    topLayout.setBackgroundColor(ContextCompat.getColor(DynamicPlayDetailActivity.this, R.color.white));
                    titleUserName.setVisibility(VISIBLE);
                    titleAvatar.setVisibility(VISIBLE);
                    titleFocus.setVisibility(VISIBLE);
                    share.setVisibility(GONE);
                    back.setImageResource(R.drawable.gray_back);
                } else {//超过mHeight
                    back.setImageResource(R.drawable.camera_back);
                    topLayout.setBackgroundColor(ContextCompat.getColor(DynamicPlayDetailActivity.this, R.color.transparency));
                    titleUserName.setVisibility(GONE);
                    titleAvatar.setVisibility(GONE);
                    titleFocus.setVisibility(GONE);
                    share.setVisibility(VISIBLE);
                }
            }
        });
        getVideoDetail(videoId);
        getComment(page);
        etComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (etComment.getText().toString().equals("")) {
                        ToastUtil.show("请先输入评论内容");
                    } else {
                        addComment(etComment.getText().toString());
                    }
                }
                return false;
            }
        });
        commentAdapter.setListener(new ContentClickListener() {
            @Override
            public void onLikeClick(int position) {
                if (commentData.get(position).getIsLike() == 0) {
                    addCommentLike(position, commentData.get(position).getId());
                } else {
                    delCommentLike(position, commentData.get(position).getId());
                }
            }
        });
    }

    RequestOptions options = new RequestOptions()
            .centerCrop()
            .error(R.drawable.image_default);


    /**
     * 去关注
     */
    private boolean isFollowing;

    private void toFollow() {
        if (model != null && model.getData() != null) {
            if (!isFollowing) {
                isFollowing = true;
                if (model.getData().getIsFollow() == 0) {
                    // 去关注
                    RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/follow/addFollow", new ParamMap.Build()
                            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                            .addParams("follow_user_id", model.getData().getUserId())
                            .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
                        @Override
                        public void onSuccess(ReadMsgModel gensign) {
                            if (gensign.getCode()==200) {
                                //关注成功
                                isFollowing = false;
                                setFollowShow(1);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            isFollowing = false;
                            ToastUtil.show(e.getMessage());
                        }
                    });
                } else {
                    // 取消关注
                    RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/follow/delFollow", new ParamMap.Build()
                            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                            .addParams("follow_user_id", model.getData().getUserId())
                            .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
                        @Override
                        public void onSuccess(ReadMsgModel gensign) {
                            if (gensign.getCode()==200) {
                                //取消关注成功
                                isFollowing = false;
                                setFollowShow(0);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            isFollowing = false;
                            ToastUtil.show(e.getMessage());
                        }
                    });
                }
            }
        }
    }

    private void setFollowShow(int isFollow) {
        if (model != null && model.getData() != null) {
            model.getData().setIsFollow(isFollow);
        }
        if (isFollow == 0) {
            //未关注
            tv_followOne.setText("关注");
            titleFocus.setText("关注");
            tv_followOne.setSelected(true);
            titleFocus.setSelected(true);
            tv_followOne.setTextColor(Color.parseColor("#32aaff"));
            titleFocus.setTextColor(Color.parseColor("#32aaff"));
        } else {
            // 已关注
            tv_followOne.setText("已关注");
            titleFocus.setText("已关注");
            tv_followOne.setSelected(false);
            titleFocus.setSelected(false);
            tv_followOne.setTextColor(Color.parseColor("#EBEBEB"));
            titleFocus.setTextColor(Color.parseColor("#EBEBEB"));
        }
    }

    private void getVideoDetail(int id) {
        RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/videodetail/" + id, new ParamMap.Build()
                .addParams("userToken", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<DynamicDetailModel>() {
            @Override
            public void onSuccess(DynamicDetailModel gensign) {
                if (gensign != null && gensign.getData() != null) {
                    model = gensign;
                    setFollowShow(model.getData().getIsFollow());
                    mCurrentCount = gensign.getData().getLikeCount();
                    likeCount.setText(mCurrentCount + "");
                    commentCountTop.setText("(" + gensign.getData().getCommentCount() + ")");
                    nickName.setText(gensign.getData().getUserNickName());
                    titleUserName.setText(gensign.getData().getUserNickName());
                    createTime.setText(gensign.getData().getCreateTime());
                    if (!TextUtils.isEmpty(gensign.getData().getContent())) {
                        content.setText(SpanWeiBoTextUtils.getWeiBoContent(DynamicPlayDetailActivity.this, gensign.getData().getContent(), content, new OnTopicClickListener() {
                            @Override
                            public void onClick(String topic) {
//                               ToastUtil.show("点击了话题："+topic);
                            }
                        }));
                    }
                    if (null != gensign.getData().getCompanyDetailDTO()) {
                        if (!TextUtils.isEmpty(gensign.getData().getCompanyDetailDTO().getGroupName())) {
                            shopName.setText(gensign.getData().getCompanyDetailDTO().getGroupName());
                            GroupName.setText(gensign.getData().getCompanyDetailDTO().getGroupName());
                            AssociationShop.setVisibility(VISIBLE);
                        } else {
                            AssociationShop.setVisibility(GONE);
                        }
                        if (gensign.getData().getCompanyDetailDTO().getAveragePrice() > 0) {
                            averagePrice.setText("￥" + gensign.getData().getCompanyDetailDTO().getAveragePrice() + "元/位");
                        } else {
                            averagePrice.setText("");
                        }
                        activityVenueAddress.setText(gensign.getData().getCompanyDetailDTO().getActivityVenueAddress());
                        Glide.with(DynamicPlayDetailActivity.this.getApplicationContext()).load(gensign.getData().getCompanyDetailDTO().getGroupLogo()).apply(options).into(shopImage);
                    } else {
                        shopView.setVisibility(GONE);
                        AssociationShop.setVisibility(GONE);
                    }
                    seeCount.setText("浏览" + gensign.getData().getSeeCount());
                    Glide.with(DynamicPlayDetailActivity.this.getApplicationContext()).load(gensign.getData().getUserImageUrl()).apply(options).into(Avatar);
                    Glide.with(DynamicPlayDetailActivity.this.getApplicationContext()).load(gensign.getData().getUserImageUrl()).apply(options).into(titleAvatar);

                    if (gensign.getData().getIsLike() == 0) {
                        ivLike.setImageResource(R.drawable.ic_praise_gray);
                    } else {
                        ivLike.setImageResource(R.drawable.icon_yizan_dynamic);
                    }
                    if (gensign.getData().getType() == 1) {
                        // 视频类型
                        mViewPlayer.setPlayerViewCallback(DynamicPlayDetailActivity.this);
                        mViewPlayer.findViewById(R.id.danmaku_view).setVisibility(GONE);
                        SuperPlayerModel model = new SuperPlayerModel();
                        model.appId = Constant.Config.TC_VIDEO_APPID;
                        SuperPlayerVideoId videoId = new SuperPlayerVideoId();
                        if (!TextUtils.isEmpty(gensign.getData().getTxId())) {
                            videoId.fileId = gensign.getData().getTxId();
                        } else {
                            videoId.fileId = "5285890794898146187";
                        }
                        model.videoId = videoId; //视频 FileId
                        mViewPlayer.playWithModel(model);
                    } else {
                        // 图片类型。
                        setImageDatas(gensign.getData().getImageUrls());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }


    private List<ImageView> imageViews;
    private List<String> imageUrls;

    private void setImageDatas(List<DynamicDetailModel.DataBean.ImageUrlBean> lists) {
        if (!(lists.size() > 0)) {
            return;
        }
        imageViews = new ArrayList<>();
        imageUrls = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            imageUrls.add(lists.get(i).getUrl());
            ImageView imageView = new ImageView(DynamicPlayDetailActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(DynamicPlayDetailActivity.this.getApplicationContext()).load(lists.get(i).getUrl()).apply(options).into(imageView);
            imageViews.add(imageView);
        }
        mImageLoader.setVisibility(View.VISIBLE);
        mImageLoader.setImageLoader(new GlideImageLoader());
        mImageLoader.setImages(imageUrls);
        mImageLoader.isAutoPlay(false);
        mImageLoader.start();

    }

    private void getComment(final int page) {
        RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/comment/listComment", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("video_id", videoId)
                .addParams("sorttype", 1)
                .addParams("page", page)
                .addParams("size", "20")
                .build(), new DmeycBaseSubscriber<DynamicCommentModel>() {
            @Override
            public void onSuccess(DynamicCommentModel gensign) {
                if (gensign != null && gensign.getData() != null) {
                    if (page == 1) {
                        commentData.clear();
                    }
                    commentData.addAll(gensign.getData());
                    commentAdapter.notifyDataSetChanged();
                    if (gensign.getData().size() < 20) {
                        loadMore.setVisibility(GONE);
                    } else {
                        loadMore.setVisibility(VISIBLE);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    private void addComment(String content) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/comment/addComment", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("video_id", videoId)
                .addParams("content", content)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    ToastUtil.show("评论成功");
                    getVideoDetail(videoId);
                    page = 1;
                    getComment(page);
                    etComment.setText("");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    private void addCommentLike(final int position, int commentId) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/comment/like/addLike", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("comment_id", commentId)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    commentData.get(position).setIsLike(1);
                    commentAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void delCommentLike(final int position, int commentId) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/comment/like/delLike", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("comment_id", commentId)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    commentData.get(position).setIsLike(0);
                    commentAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void addVideoLike() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/likeorstore/addLikeOrStore", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("video_id", videoId)
                .addParams("type", 1)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                isDigs = false;
                if (gensign.getCode()==200) {
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                isDigs = false;
            }
        });
    }

    private void delVideoLike() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/likeorstore/delLikeOrStore", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("video_id", videoId)
                .addParams("type", 1)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                isDigs = false;
                if (gensign.getCode()==200) {

                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                isDigs = false;
            }
        });
    }

    private boolean isDigs;
    private int mCurrentCount = 0;

    @OnClick({R.id.shopLayout, R.id.back, R.id.likeView, R.id.loadMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopLayout:
                if (model != null && model.getData() != null && model.getData().getCompanyDetailDTO() != null) {
                    ShopDetailActivity.newIntent(this, model.getData().getCompanyDetailDTO().getId(), model.getData().getCompanyDetailDTO().getGroupType());
                }
                break;
            case R.id.back:
                onBackPressed();
                break;
            case R.id.likeView:
                if (model.getData().getIsLike() == 0) {
                    if (!isDigs) {
                        isDigs = true;
                        addVideoLike();
                        model.getData().setIsLike(1);
                        ivLike.setImageResource(R.drawable.icon_yizan_dynamic);
                        mCurrentCount += 1;
                        likeCount.setText(mCurrentCount + "");
                    }
                } else {
                    if (!isDigs) {
                        isDigs = true;
                        model.getData().setIsLike(0);
                        ivLike.setImageResource(R.drawable.ic_praise_gray);
                        mCurrentCount -= 1;
                        if (mCurrentCount < 0) {
                            mCurrentCount = 0;
                        }
                        likeCount.setText(mCurrentCount + "");
                        delVideoLike();
                    }
                }
                break;
            case R.id.loadMore:
                page++;
                getComment(page);
                break;
        }
    }

    @Bind(R.id.mLimit_layout)
    LinearLayout mLimit_layout;

    @Override
    public void onStartFullScreenPlay() {
        // 隐藏其他元素实现全屏
        mLimit_layout.setVisibility(GONE);
        bottomLayout.setVisibility(GONE);
        topLayout.setVisibility(GONE);
        mDistanceLayout.setVisibility(GONE);
        mViewPlayer.findViewById(R.id.danmaku_view).setVisibility(GONE);
    }

    @Override
    public void onStopFullScreenPlay() {
        // 恢复原有元素
        mLimit_layout.setVisibility(VISIBLE);
        bottomLayout.setVisibility(VISIBLE);
        topLayout.setVisibility(VISIBLE);
        if(!TextUtils.isEmpty(DataClass.LocationDistrict)
           && !TextUtils.isEmpty(mCurrentDistance)
        ){
            mDistanceLayout.setVisibility(VISIBLE);
        }else{
            mDistanceLayout.setVisibility(GONE);
        }
    }

    @Override
    public void onClickFloatCloseBtn() {
        // 点击悬浮窗关闭按钮，那么结束整个播放
        mViewPlayer.resetPlayer();
        finish();
    }

    @Override
    public void onClickSmallReturnBtn() {
        // 点击小窗模式下返回按钮，开始悬浮播放
//        showFloatWindow();
    }

    @Override
    public void onStartFloatWindowPlay() {
        // 开始悬浮播放后，直接返回到桌面，进行悬浮播放
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mViewPlayer.getPlayState() == SuperPlayerConst.PLAYSTATE_PLAY) {
            mViewPlayer.onResume();
            if (mViewPlayer.getPlayMode() == SuperPlayerConst.PLAYMODE_FLOAT) {
                mViewPlayer.requestPlayMode(SuperPlayerConst.PLAYMODE_WINDOW);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mViewPlayer.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            mViewPlayer.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPlayer.release();
        if (mViewPlayer.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            mViewPlayer.resetPlayer();
        }
    }

    private class CommentAdapter extends CommonAdapter<DynamicCommentModel.DataBean> {

        ContentClickListener listener;

        public void setListener(ContentClickListener listener) {
            this.listener = listener;
        }

        public CommentAdapter(Context context, int layoutId, List<DynamicCommentModel.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, DynamicCommentModel.DataBean s, final int position) {
            ImageView avatar = holder.getConvertView().findViewById(R.id.Avatar);
            ImageView isLike = holder.getConvertView().findViewById(R.id.isLike);
            holder.setText(R.id.userName, s.getUserNickName())
                    .setText(R.id.createTime, s.getCreateTime())
                    .setText(R.id.content, s.getContent());
            Glide.with(DynamicPlayDetailActivity.this.getApplicationContext()).load(s.getUserImageUrl()).apply(options).into(avatar);
            if (s.getIsLike() == 0) {
                isLike.setImageResource(R.drawable.icon_zan_dynamic);
            } else {
                isLike.setImageResource(R.drawable.icon_yizan_dynamic);
            }
            isLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onLikeClick(position);
                    }
                }
            });
        }
    }

    interface ContentClickListener {
        void onLikeClick(int position);
    }

    private void setUerImage() {
        RestClient.getYfmNovate(DynamicPlayDetailActivity.this).post(Constant.API.YFM_USER_INFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
            @Override
            public void onSuccess(YFMUserBean bean) {
                if (!TextUtils.isEmpty(bean.getData().getUser_logo())) {
                    Glide.with(DynamicPlayDetailActivity.this.getApplicationContext()).load(bean.getData().getUser_logo()).apply(options).into(bottomAvatar);
                }
            }
        });
    }

    private boolean isShareing;

    // 获取分享链接去分享
    private void toShareDynamic() {
        if (!isShareing) {
            isShareing = true;
            RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + "api/v1.0/topic/share/video/{groupId}", new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                    .addParams("video_id", videoId)
                    .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
                @Override
                public void onSuccess(ReadMsgModel gensign) {

                    isShareing = false;
                    if (gensign.getCode()==200) {
                        String url = gensign.getData();
                        if (!TextUtils.isEmpty(url)) {
                               Log.e("url==",url);
                            if (model != null && model.getData() != null) {

                                String content = "动态";
                                if (!TextUtils.isEmpty(model.getData().getUserNickName())) {
                                    content = model.getData().getUserNickName() + "的动态";
                                }

                                String title = "";
                                if (!TextUtils.isEmpty(model.getData().getContent())) {
                                    title = model.getData().getContent();
                                }

                                CommSharePopWin dd = new CommSharePopWin.Builder(DynamicPlayDetailActivity.this).setContent(content)
                                        //Radius越大耗时越长,被图片处理图像越模糊
                                        .setRadius(3).setTitle(title)
                                        .setUrl(url)
                                        //设置居中还是底部显示
//                                    .setid(actyDeatilBean.getData().getGroup_id()+"")
//                                    .setlogo(activityDeatilBean.getData().getGroup_logo())
                                        .setshowAtLocationType(1)
                                        .setShowCloseButton(true)
                                        .setOutSideClickable(false)
                                        /*.onClick(new BlurPopWin.PopupCallback() {
                                            @Override
                                            public void onClick(@NonNull BlurPopWin blurPopWin) {
                                                Toast.makeText(MainActivity.this, "中间被点了", Toast.LENGTH_SHORT).show();
                                                blurPopWin.dismiss();
                                            }
                                        })*/.show(share);
                                dd.setGroupShareShowVisibility(GONE);
                                dd.setOnCommShareClick(new CommSharePopWin.CommShareClickLisenter() {
                                    @Override
                                    public void onCOMMClick() {
                                    }
                                });
                            } else {
                                ToastUtil.show("获取数据失败");
                            }
                        } else {
                            ToastUtil.show("获取数据失败");
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    isShareing = false;
                }
            });
        }

    }
}
