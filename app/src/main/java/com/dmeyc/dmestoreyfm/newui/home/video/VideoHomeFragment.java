package com.dmeyc.dmestoreyfm.newui.home.video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.VideoPlayListAdapter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.other.OtherDetailActivity;
import com.dmeyc.dmestoreyfm.newui.update.UpdateManager;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;
import com.dmeyc.dmestoreyfm.newui.release.live.LiveActivity;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.LocationUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.dmeyc.dmestoreyfm.wedgit.CommSharePopWin;
import com.dmeyc.dmestoreyfm.wedgit.dialog.CommentDialog;
import com.dmeyc.dmestoreyfm.wedgit.viewpager.OnViewPagerListener;
import com.dmeyc.dmestoreyfm.wedgit.viewpager.ViewPagerLayoutManager;
import com.orhanobut.logger.Logger;
import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.GONE;

/**
 * create by cxg on 2019/11/23
 */
public class VideoHomeFragment extends BaseRefreshFragment<IVideoHomeView, VideoHomePresenter, IndexDynamicBean.DataBean, VideoPlayListAdapter> implements IVideoHomeView {


    private int mLocalPosition;// 外部传过来的播放第几条
    private int mCurrentPosition;//当前播放第几条
    private List<IndexDynamicBean.DataBean> mLocalDatas;

    private boolean canPlay = true;

    @Override
    protected VideoHomePresenter createPresenter() {
        return new VideoHomePresenter();
    }

    @Override
    protected boolean enableEventBus() {
        return !isFromLocal();
    }

    @Override
    protected boolean enableRefresh() {
        return !isFromLocal();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mLocalPosition = bundle.getInt(ExtraKey.VIDEO_SELECOTR_POSITION);
            mLocalDatas = (List<IndexDynamicBean.DataBean>) bundle.getSerializable(ExtraKey.VIDEO_INFO_LIST);
            if (mLocalDatas != null) {
                for (int i = 0; i < mLocalDatas.size(); i++) {
                    mLocalDatas.get(i).setItemType(Constant.AdapterItemType.TYPE_HOME_VIDEO);
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_share:
                        toShareDynamic(mData.get(position));
                        break;
                    case R.id.iv_comment:
                        new CommentDialog.Build(mContent)
                                .setVideoId(mData.get(position).getId())
                                .setVideoPosition(position)
                                .create().show();
                        break;
                    case R.id.iv_like:
                        if (!CommonUtil.isLogin(getContext())) {
                            return;
                        }

                        view.setEnabled(false);
                        boolean isUnLikePre = mData.get(position).getIsLike() == 0;
                        if (isUnLikePre) {
                            ((ImageView) view).setImageResource(R.drawable.icon_like_like);
                        } else {
                            ((ImageView) view).setImageResource(R.drawable.icon_like_normal);
                        }
                        mPresenter.onLike(mData.get(position).getId(), position, isUnLikePre);
                        break;
                    case R.id.civ_header:
                        if ("1".equals(mData.get(position).getIsLiveBroadCast())) {
                            mPresenter.httpActivityDetail(mData.get(position).getLiveBroadCastActivityId());
                        }
                        OtherDetailActivity.newInstance(getContext(), mData.get(position).getUserId());
                        break;
                }
            }
        });
        initListener();
        if (isFromLocal()) {//从条目点击进来，不需要请求网络
            mData.addAll(mLocalDatas);
            mAdapter.replaceData(mData);
            mRecyclerView.scrollToPosition(mLocalPosition);
        } else
            // TODO: 2019/12/21  这个权限申请可以放到MainActivity
            EventBus.getDefault().post(Constant.HotUEventKeys.TO_CHECK_PERMISSION);
        mPresenter.updateVersion(getUpdateVersionParams());
    }

    // 分享
    private void toShareDynamic(final IndexDynamicBean.DataBean bean) {

        CommentRequestHelper.share(String.valueOf(bean.getId()), new CommentRequestHelper.CallBackAdapter() {
            @Override
            public void onSuccess(String returnStr) {
                String content = "动态";
                if (!TextUtils.isEmpty(bean.getUserNickName())) {
                    content = bean.getUserNickName() + "的动态";
                }

                String title = "";
                if (!TextUtils.isEmpty(bean.getContent())) {
                    title = bean.getContent();
                }

                CommSharePopWin dd = new CommSharePopWin.Builder(getContext()).setContent(content)
                        //Radius越大耗时越长,被图片处理图像越模糊
                        .setRadius(3).setTitle(title)
                        .setUrl(returnStr)
                        .setshowAtLocationType(1)
                        .setShowCloseButton(true)
                        .setOutSideClickable(false)
                        .show(mRecyclerView);
                dd.setGroupShareShowVisibility(GONE);
                dd.setOnCommShareClick(new CommSharePopWin.CommShareClickLisenter() {
                    @Override
                    public void onCOMMClick() {
                    }
                });
                dd.setiShare(new CommSharePopWin.IShare() {
                    @Override
                    public void onShareSuccess() {
                        CommentRequestHelper.httpRequestForward(bean.getId() + "");
                    }
                });
            }
        });


    }

    private boolean isFromLocal() {
        return mLocalDatas != null && mLocalDatas.size() > 0;
    }

    @Override
    protected VideoPlayListAdapter getAdapter() {
        return new VideoPlayListAdapter(getContext(), null);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new ViewPagerLayoutManager(mContent, LinearLayoutManager.VERTICAL);
    }


    private void initListener() {
        ((ViewPagerLayoutManager) mRecyclerView.getLayoutManager()).setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Logger.d(position);
                releaseVideo(position, false);
            }


            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Logger.d(position);
                mCurrentPosition = position;

                if (!canPlay) {
                    canPlay = true;
                    releaseVideo(position, false);
                    return;
                }
                playVideo(position);
            }
        });

    }

    private void playVideo(int position) {
        CommentRequestHelper.httpRequestVideoNum(mData.get(position).getId() + "");
        final View view = mRecyclerView.getLayoutManager().findViewByPosition(position);
        if (view == null) {
            return;
        }
        view.findViewById(R.id.rv_images).setVisibility(View.VISIBLE);
        view.findViewById(R.id.iv_video_pause).setVisibility(GONE);

        view.findViewById(R.id.cl_advice).setVisibility(GONE);
        if (mData.get(position).getType() != 1) {
            return;
        }

//        view.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                view.findViewById(R.id.rv_images).setVisibility(GONE);
//            }
//        }, 1000);

        if (!(TextUtils.isEmpty(mData.get(position).getLinkedType()) || TextUtils.isEmpty(mData.get(position).getLinkedTitle()))) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.findViewById(R.id.cl_advice).setVisibility(View.VISIBLE);
                }
            }, 5000);
        }

        SuperPlayerModel model = new SuperPlayerModel();
        model.url = mData.get(position).getUrl();
        SuperPlayerView superPlayerView = view.findViewById(R.id.mViewPlayer);
        superPlayerView.setPlayStateListener(new SuperPlayerView.IPlayStateListener() {
            @Override
            public void beginPlay() {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.rv_images).setVisibility(GONE);
                    }
                }, 1000);

            }
        });
        superPlayerView.playWithModel(model);
    }


    private void releaseVideo(int position, boolean isPause) {
        final View view = mRecyclerView.getLayoutManager().findViewByPosition(position);
        if (view == null) {
            return;
        }
        view.findViewById(R.id.rv_images).setVisibility(View.VISIBLE);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.findViewById(R.id.cl_advice).setVisibility(GONE);
            }
        }, 4500);
        view.findViewById(R.id.cl_advice).setVisibility(GONE);
        SuperPlayerView mViewPlayer = (SuperPlayerView) view.findViewById(R.id.mViewPlayer);
//        if (mViewPlayer.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
//            mViewPlayer.onPause();
//            mViewPlayer.release();
            mViewPlayer.resetPlayer();
//        }
    }

    @Override
    protected void curFragmentInVisible() {
        super.curFragmentInVisible();
        try {
            setNotPlay();
            releaseVideo(mCurrentPosition, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        if (!isFromLocal()) {
            mPresenter.getVideoList();
        }
    }

    @Override
    public void requestData() {
        mPresenter.getVideoList();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(mPageNo));
        params.put("size", PAGE_SIZE);
        if (TextUtils.isEmpty(DataClass.LocationDistrict)) {
            params.put("sorttype", "1");
        } else {
            params.put("sorttype", "4");
        }
        String longitude = SPUtils.getStringData(Constant.Config.LONGTUDE);
        if (TextUtils.isEmpty(longitude)) {
            longitude = "0";
        }
        params.put("longitude", longitude);
        String latitude = SPUtils.getStringData(Constant.Config.LATITUDE);
        if (TextUtils.isEmpty(latitude)) {
            latitude = "0";
        }
        params.put("latitude", latitude);
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))) {
            params.put("city", SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        } else {
            SPUtils.savaStringData(Constant.Config.CURRENT_CITY, "上海市");
        }
        return params;
    }

    @Override
    public void getVideosSucc() {

    }

    @Override
    public void handleLikeSucc(int position, boolean isUnLikePre) {
        mAdapter.getViewByPosition(mRecyclerView, position, R.id.iv_like).setEnabled(true);
        int isLike = 1;
        int likeCount = mData.get(position).getLikeCount();
        if (!isUnLikePre) {
            isLike = 0;
            likeCount--;
        } else {
            likeCount++;
        }
        if (likeCount<0){
            likeCount = 0;
        }
        ((TextView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.tv_like_count)).setText(String.valueOf(likeCount));
        mData.get(position).setIsLike(isLike);
        mData.get(position).setLikeCount(likeCount);
    }

    @Override
    public void handleLikeFail(int position, boolean isUnLikePre) {
        ImageView ivLike = ((ImageView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.iv_like));
        ivLike.setEnabled(true);
        if (isUnLikePre) {
            ivLike.setImageResource(R.drawable.icon_like_normal);
        } else {
            ivLike.setImageResource(R.drawable.icon_like_like);
        }
    }

    @Override
    public Map<String, String> getUpdateVersionParams() {
        Map<String, String> params = new HashMap<>();
        params.put("versionCode", Util.getLocalVersionName(getActivity()));
//        params.put("versionCode","1");
        return params;
    }

    @Override
    public void getUpdateVerSucc(UpdateResultBean bean) {
        // 获取版本信息成功
        if (bean != null && bean.getData() != null) {
            if ("0".equals(bean.getData().getVersionType()) || "1".equals(bean.getData().getVersionType())) {
                UpdateManager updateManager = new UpdateManager(getActivity());
                updateManager.showUpdateDialog(bean.getData());
            }
        }
    }

    @Override
    public void getActivityImage(String activityId, String group_logo) {
        LiveActivity.newInstance(getContext(), LiveActivity.TYPE_OTHER, activityId, group_logo);
    }

    /**
     * 事件响应方法
     * 接收消息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventVideoBean event) {
        String msg = event.getKey();
        if (Constant.HotUEventKeys.RELEASE_DYNAMIC_SUCCESS.equals(msg)) {
            // 发布成功后刷新首页数据
//            mPage = 1;
//            getListData();
        }
        if (Constant.HotUEventKeys.HAS_LOCATION_PERMISSION.equals(msg)) {
            //去定位
            new LocationUtil().requestLocation(LocationUtil.TYPE_HOME_VIDEO);
        }
        if (Constant.HotUEventKeys.NO_LOCATION_PERMISSION.equals(msg)) {
            //使用默认数据
//            setDefaultCitysInfo();
//            getListData();
//            getBanner();
//            getPkList();
//            getWither();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLocation(MyEvent.Location event) {
        if (event.mType == LocationUtil.TYPE_HOME_VIDEO) {
            mPageNo = 1;
            mPresenter.getVideoList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetPlay(MyEvent.CloseHomePlay event) {
        try {
            setNotPlay();
            releaseVideo(mCurrentPosition, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNotPlay() {
        canPlay = false;
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                canPlay = true;
            }
        }, 4000);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            canPlay = true;
        }
    };
}
