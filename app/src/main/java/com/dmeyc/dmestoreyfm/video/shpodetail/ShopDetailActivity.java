package com.dmeyc.dmestoreyfm.video.shpodetail;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.CommSharePopWin;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ShopDetailActivity extends BaseActivity {

    @Bind(R.id.photoRv)
    RecyclerView photoRv;
    @Bind(R.id.bottomAvatar)
    CircleImageView bottomAvatar;
    @Bind(R.id.shopName)
    TextView shopName;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.telePhone)
    ImageView telePhone;
    @Bind(R.id.shopAddress)
    TextView shopAddress;
    @Bind(R.id.commentCount)
    TextView commentCount;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.etComment)
    EditText etComment;
    @Bind(R.id.loadMore)
    TextView loadMore;
    private TopBannerAdapter bannerAdapter;
    private List<ShopDetailModel.DataBean.ImagesBean> bannerData = new ArrayList<>();

    @Bind(R.id.bottomLayout)
    RelativeLayout bottomLayout;

    @Bind(R.id.mCommentTitleLayout)
    LinearLayout mCommentTitleLayout;

    @Bind(R.id.commentRv)
    RecyclerView commentRv;
    private CommentAdapter commentAdapter;
    private List<ShopDetailCommetModel.DataBean> commentData = new ArrayList<>();
    private int groupId;
    private int groupType;

    private ShopDetailModel model;

    private int page = 1;


    public static void newIntent(Activity activity, int groupId, int groupType) {
        Intent intent = new Intent(activity, ShopDetailActivity.class);
        intent.putExtra("groupType", groupType);
        intent.putExtra("groupId", groupId);
        activity.startActivity(intent);
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    private final static String API1 = "/api/v1.0/topic/video/bussydetail/";
    private final static String API2 = "/api/v1.0/topic/video/modelBussyDetail/";
    private String mCurrentApi;

    private void setViewVisible() {
        if (groupType == 2) {
            mCurrentApi = API2;
            commentRv.setVisibility(GONE);
            bottomLayout.setVisibility(GONE);
            mCommentTitleLayout.setVisibility(GONE);
            share.setVisibility(GONE);
        } else {
            mCurrentApi = API1;
            commentRv.setVisibility(VISIBLE);
            bottomLayout.setVisibility(VISIBLE);
            mCommentTitleLayout.setVisibility(VISIBLE);
            share.setVisibility(VISIBLE);
        }
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        groupId = getIntent().getIntExtra("groupId", 0);
        groupType = getIntent().getIntExtra("groupType", 1);
        setViewVisible();
        getShopDetail();
        //初始化评论的RecyclerView
        commentRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        commentAdapter = new CommentAdapter(this, R.layout.item_comment_layout, commentData);
        commentRv.setAdapter(commentAdapter);
        commentAdapter.setListener(new CommentAdapter.ContentClickListener() {
            @Override
            public void onLikeClick(int position) {
                if (commentData.get(position).getIsLike() == 0) {
                    addCommentLike(position, commentData.get(position).getId());
                } else {
                    delCommentLike(position, commentData.get(position).getId());
                }
            }
        });
        //初始化顶部Banner
        photoRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bannerAdapter = new TopBannerAdapter(this, R.layout.item_shopdetail_banner, bannerData);
        new LinearSnapHelper().attachToRecyclerView(photoRv);
        photoRv.setAdapter(bannerAdapter);

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
        if (groupType == 2) {
            mCurrentApi = API2;
            commentRv.setVisibility(GONE);
            bottomLayout.setVisibility(GONE);
            mCommentTitleLayout.setVisibility(GONE);
            share.setVisibility(GONE);
        } else {
            getComment(page);
            mCurrentApi = API1;
            commentRv.setVisibility(VISIBLE);
            bottomLayout.setVisibility(VISIBLE);
            mCommentTitleLayout.setVisibility(VISIBLE);
            share.setVisibility(VISIBLE);
        }
        setUerImage();
    }

    private void getComment(final int page) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/comment/listComment", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", groupId)
                .addParams("sorttype", 1)
                .addParams("page", page)
                .addParams("size", "20")
                .build(), new DmeycBaseSubscriber<ShopDetailCommetModel>() {
            @Override
            public void onSuccess(ShopDetailCommetModel gensign) {
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
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/comment/addComment", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", groupId)
                .addParams("content", content)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    ToastUtil.show("评论成功");
                    page = 1;
                    getComment(page);
                    getShopDetail();
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
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/comment/like/addLike", new ParamMap.Build()
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
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/comment/like/delLike", new ParamMap.Build()
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


    private void getShopDetail() {
        RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + mCurrentApi + groupId, new ParamMap.Build()
                .build(), new DmeycBaseSubscriber<ShopDetailModel>() {
            @Override
            public void onSuccess(ShopDetailModel gensign) {
                model = gensign;
                if (model != null) {
                    if (!TextUtils.isEmpty(model.getData().getGroupName())) {
                        shopName.setText(gensign.getData().getGroupName());
                    } else {
                        shopName.setText("");
                    }
                    if (gensign.getData().getAveragePrice() > 0) {
                        price.setText("￥" + gensign.getData().getAveragePrice() + "元/位");
                    } else {
                        price.setText("");
                    }
                    if (!TextUtils.isEmpty(gensign.getData().getActivityVenueAddress())) {
                        shopAddress.setText(gensign.getData().getActivityVenueAddress());
                    } else {
                        shopAddress.setText("");
                    }
                    bannerData.clear();
                    bannerData.addAll(gensign.getData().getImages());
                    bannerAdapter.notifyDataSetChanged();
                    commentCount.setText("(" + gensign.getData().getCommentCount() + ")");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    private boolean isShareing;

    // 获取分享链接去分享
    private void toShareDynamic() {
        if (!isShareing) {
            isShareing = true;
            RestClient.getYfmNovate(this).get(Constant.API.YFM_BASE_URL + "/api/v1.0/topic/share/company/{groupId}",
                     new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                    .addParams("group_id", groupId)
                    .build(), new DmeycBaseSubscriber<ReadMsgModel>(){
                @Override
                public void onSuccess(ReadMsgModel gensign){
                    isShareing = false;
                    if (gensign.getCode()==200){
                        String url = gensign.getData();
                        if (!TextUtils.isEmpty(url)){
                            if (model != null && model.getData() != null) {
                                String content = "动态";
                                if (!TextUtils.isEmpty(model.getData().getGroupName())){
                                    content = model.getData().getGroupName();
                                }
                                String title = "";
                                CommSharePopWin dd = new CommSharePopWin.Builder(ShopDetailActivity.this).setContent(content)
                                        //Radius越大耗时越长,被图片处理图像越模糊
                                        .setRadius(3)
                                        .setTitle(title)
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
                                dd.setOnCommShareClick(new CommSharePopWin.CommShareClickLisenter(){
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

    RequestOptions options = new RequestOptions()
            .centerCrop()
            .error(R.drawable.image_default);

    private void setUerImage() {
        RestClient.getYfmNovate(ShopDetailActivity.this).post(Constant.API.YFM_USER_INFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
            @Override
            public void onSuccess(YFMUserBean bean) {
                if (!TextUtils.isEmpty(bean.getData().getUser_logo())) {
                    Glide.with(ShopDetailActivity.this.getApplicationContext()).load(bean.getData().getUser_logo()).apply(options).into(bottomAvatar);
                }
            }
        });
    }

    private void checkP(String action, String mobile) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this, "请授权！", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
            }
        } else {
            // 已经获得授权，可以打电话
            call(action, mobile);
        }
    }

    private void call(String action, String mobile) {
        String phone = mobile;
        if (phone != null && phone.trim().length() > 0) {
            //这里"tel:"+电话号码 是固定格式，系统一看是以"tel:"开头的，就知道后面应该是电话号码。
            Intent intent = new Intent(action, Uri.parse("tel:" + phone.trim()));
            startActivity(intent);//调用上面这个intent实现拨号
        } else {
            Toast.makeText(this, "电话号码不能为空", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick({
            R.id.back,
            R.id.telePhone,
            R.id.share,
            R.id.loadMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.telePhone:
                if (model != null && !TextUtils.isEmpty(model.getData().getTelNo())) {
                    checkP(Intent.ACTION_CALL, model.getData().getTelNo());
                } else {
                    ToastUtil.show("该店铺暂无联系方式");
                }
                break;
            case R.id.share:
                toShareDynamic();
                break;
            case R.id.loadMore:
                page++;
                getComment(page);
                break;
        }
    }
}
