package com.dmeyc.dmestoreyfm.video.dynamicdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
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
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.dynamicdetail.digs.DigsActivity;
import com.dmeyc.dmestoreyfm.video.shpodetail.ShopDetailActivity;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;
import com.dmeyc.dmestoreyfm.video.topicutils.OnTopicClickListener;
import com.dmeyc.dmestoreyfm.video.topicutils.SpanWeiBoTextUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.RoundAngleImageView;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.liteav.demo.play.v3.SuperPlayerVideoId;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class DynamicDetailActivity extends BaseActivity  implements  SuperPlayerView.OnSuperPlayerViewCallback{

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

    @Bind(R.id.mTopLayout)
    RelativeLayout mTopLayout;

    @Bind(R.id.mViewPlayer)
    SuperPlayerView mViewPlayer;

    private DynamicDetailModel model;
    private CommentAdapter commentAdapter;
    private List<DynamicCommentModel.DataBean> commentData = new ArrayList<>();

    public static void newIntent(Activity activity, int id) {
        Intent intent = new Intent(activity, DynamicDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    private int videoId;
    private int page = 1;

    @OnClick(R.id.tv_digs)
    void onDigsClick() {
        // 点赞
        DigsActivity.newIntent(DynamicDetailActivity.this);
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_dynamic_detail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);
        videoId = getIntent().getIntExtra("id", 0);
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
                    topLayout.setBackgroundColor(ContextCompat.getColor(DynamicDetailActivity.this, R.color.transparency));
                    titleUserName.setVisibility(View.GONE);
                    titleAvatar.setVisibility(View.GONE);
                    titleFocus.setVisibility(View.GONE);
                    share.setVisibility(View.VISIBLE);
                } else if (scrollY >= mTopLayout.getHeight()) { //滑动过程中 并且在mHeight之内
                    topLayout.setBackgroundColor(ContextCompat.getColor(DynamicDetailActivity.this, R.color.white));
                    titleUserName.setVisibility(View.VISIBLE);
                    titleAvatar.setVisibility(View.VISIBLE);
                    titleFocus.setVisibility(View.VISIBLE);
                    share.setVisibility(View.GONE);
                    back.setImageResource(R.drawable.gray_back);
                } else {//超过mHeight
                    back.setImageResource(R.drawable.camera_back);
                    topLayout.setBackgroundColor(ContextCompat.getColor(DynamicDetailActivity.this, R.color.transparency));
                    titleUserName.setVisibility(View.GONE);
                    titleAvatar.setVisibility(View.GONE);
                    titleFocus.setVisibility(View.GONE);
                    share.setVisibility(View.VISIBLE);
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

    private void getVideoDetail(int id) {

        RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/videodetail/" + id, new ParamMap.Build()
                .build(), new DmeycBaseSubscriber<DynamicDetailModel>() {
            @Override
            public void onSuccess(DynamicDetailModel gensign) {
                if (gensign != null && gensign.getData() != null) {
                    model = gensign;
                    likeCount.setText(gensign.getData().getLikeCount() + "");
                    commentCountTop.setText("(" + gensign.getData().getCommentCount() + ")");
                    nickName.setText(gensign.getData().getUserNickName());
                    titleUserName.setText(gensign.getData().getUserNickName());
                    createTime.setText(gensign.getData().getCreateTime());
                    if (!TextUtils.isEmpty(gensign.getData().getContent())) {
                        content.setText(SpanWeiBoTextUtils.getWeiBoContent(DynamicDetailActivity.this, gensign.getData().getContent(), content, new OnTopicClickListener() {
                            @Override
                            public void onClick(String topic) {
                                //
//                            ToastUtil.show("点击了话题："+topic);
                            }
                        }));
                    }
                    if (null != gensign.getData().getCompanyDetailDTO()) {
                        if (!TextUtils.isEmpty(gensign.getData().getCompanyDetailDTO().getGroupName())) {
                            shopName.setText(gensign.getData().getCompanyDetailDTO().getGroupName());
                            GroupName.setText(gensign.getData().getCompanyDetailDTO().getGroupName());
                            AssociationShop.setVisibility(View.VISIBLE);
                        } else {
                            AssociationShop.setVisibility(View.GONE);
                        }
                        averagePrice.setText("￥" + gensign.getData().getCompanyDetailDTO().getAveragePrice());
                        activityVenueAddress.setText(gensign.getData().getCompanyDetailDTO().getActivityVenueAddress());
                        Glide.with(DynamicDetailActivity.this.getApplicationContext()).load(gensign.getData().getCompanyDetailDTO().getGroupLogo()).apply(options).into(shopImage);
                    } else {
                        shopView.setVisibility(View.GONE);
                        AssociationShop.setVisibility(View.GONE);
                    }
                    seeCount.setText("浏览" + gensign.getData().getSeeCount());
                    Glide.with(DynamicDetailActivity.this.getApplicationContext()).load(gensign.getData().getUserImageUrl()).apply(options).into(Avatar);
                    Glide.with(DynamicDetailActivity.this.getApplicationContext()).load(gensign.getData().getUserImageUrl()).apply(options).into(titleAvatar);
                    Glide.with(DynamicDetailActivity.this.getApplicationContext()).load(gensign.getData().getUserImageUrl()).apply(options).into(bottomAvatar);
                    if (gensign.getData().getIsLike() == 0) {
                        ivLike.setImageResource(R.drawable.ic_praise_gray);
                    } else {
                        ivLike.setImageResource(R.drawable.icon_yizan_dynamic);
                    }
                    mViewPlayer.setPlayerViewCallback(DynamicDetailActivity.this);
                    SuperPlayerModel model = new SuperPlayerModel();
                    model.appId = 1300375154;
                    SuperPlayerVideoId videoId = new SuperPlayerVideoId();
                    videoId.fileId = "5285890794886967017";
                    model.videoId = videoId; //视频 FileId
                    mViewPlayer.playWithModel(model);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    private void getComment(int page) {

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
                    commentData.clear();
                    commentData.addAll(gensign.getData());
                    commentAdapter.notifyDataSetChanged();
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
                if (gensign.getCode()==200) {
                    model.getData().setIsLike(1);
                    model.getData().setLikeCount(model.getData().getLikeCount() + 1);
                    ivLike.setImageResource(R.drawable.icon_yizan_dynamic);
                    likeCount.setText(model.getData().getLikeCount() + 1 + "");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void delVideoLike() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/video/comment/like/delLike", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("video_id", videoId)
                .addParams("type", 1)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    model.getData().setIsLike(0);
                    ivLike.setImageResource(R.drawable.ic_praise_gray);
                    model.getData().setLikeCount(model.getData().getLikeCount() - 1);
                    likeCount.setText(model.getData().getLikeCount() - 1 + "");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @OnClick({R.id.shopLayout, R.id.back, R.id.likeView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopLayout:
//                ShopDetailActivity.newIntent(this,model.getData().getId());
                break;
            case R.id.back:
                onBackPressed();
                break;
            case R.id.likeView:
                if (model.getData().getIsLike() == 0) {
                    addVideoLike();
                } else {
                    delVideoLike();
                }
                break;
        }
    }

    @Bind(R.id.mLimit_layout)
    LinearLayout mLimit_layout;

    @Override
    public void onStartFullScreenPlay() {
        // 隐藏其他元素实现全屏
        mLimit_layout.setVisibility(GONE);
//        bottomLayout.setVisibility(GONE);
//        if (mIvAdd != null) {
//            mIvAdd.setVisibility(GONE);
//        }
    }

    @Override
    public void onStopFullScreenPlay() {
       // 恢复原有元素
        mLimit_layout.setVisibility(VISIBLE);
//        bottomLayout.setVisibility(VISIBLE);
//        if (mIvAdd != null) {
//            mIvAdd.setVisibility(VISIBLE);
//        }
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
            Glide.with(DynamicDetailActivity.this.getApplicationContext()).load(s.getUserImageUrl()).apply(options).into(avatar);
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


}
