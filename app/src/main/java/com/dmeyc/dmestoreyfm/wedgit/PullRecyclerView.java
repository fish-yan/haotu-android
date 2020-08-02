package com.dmeyc.dmestoreyfm.wedgit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;


public class PullRecyclerView extends LinearLayout {

    private Context mContext;

    private boolean isRefresh = false;//正在下拉刷新中。。。
    private boolean isLoadMore = false;//正在上拉加载更多

    private boolean hasMore = true;//是否有更多数据

    private boolean pullDownRefreshEnable = true;//允许下拉刷新
    private boolean pullUpLoadMoreEnable = true;//允许上拉加载

    private boolean isLoadingNoTouch = false;//正在加载中时（上拉or下拉），禁止滑动控件

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mFooterView;
    private ProgressBar loadMoreProgressBar;
    private TextView mLoadMoreText;

    private RecyclerView mRecyclerView;

    private PullDownRefreshListener mPullDownRefreshListener;//下拉刷新监听
    private PullUpLoadMoreListener mPullUpLoadMoreListener;//上拉加载更多监听


    private long footerAnimateDuration = 150;//底部栏显示隐藏的动画时长


    public PullRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public PullRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pull_recyclerview_layout, null);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
//        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefresh && !isLoadMore) {//启动下拉刷新
                    setHasMore(true);
                    onPullDownRefresh();
                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setVerticalScrollBarEnabled(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener());
        mRecyclerView.setOnTouchListener(new onTouchRecyclerView());

        mFooterView = view.findViewById(R.id.footerView);
        loadMoreProgressBar = view.findViewById(R.id.loadMoreProgressBar);
        mLoadMoreText = view.findViewById(R.id.loadMoreText);

        mFooterView.setVisibility(View.GONE);

        this.addView(view);

    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    /**
     * 默认样式
     *
     * @param context   这里要传递当前activity
     * @param spanCount 列数
     */
    public void setDefaultMode(Activity context, RecyclerView.Adapter adapter, int spanCount) {
        if (spanCount > 1) {
            //      绑定 GridLayoutManager
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, spanCount));
            //设置分割线
//            mRecyclerView.addItemDecoration(new GridLayoutDivider());
        } else {
            //绑定 LinearLayoutManager
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            //设置分割线
            mRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        }
        //设置adapter
        setAdapter(adapter);
        //设置Item 增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }



    public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastItem = 0;
            int firstItem = 0;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int totalItemCount = layoutManager.getItemCount();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
                firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                //Position to find the final item of the current LayoutManager
                lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem == -1) lastItem = gridLayoutManager.findLastVisibleItemPosition();
            } else if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
                firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem == -1) lastItem = linearLayoutManager.findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
                // since may lead to the final item has more than one StaggeredGridLayoutManager the particularity of the so here that is an array
                // this array into an array of position and then take the maximum value that is the last show the position value
                int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
                lastItem = findMax(lastPositions);
                firstItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
            }
//            if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION) {
//                if (isPullDownRefreshEnable()) {
//                    setPullDownRefreshEnable(true);
//                }
//            } else {
//                setPullDownRefreshEnable(false);
//            }
            if (isPullUpLoadMoreEnable()//允许上拉加载
//                    && isHasMore()//有更多数据
                    && !isRefresh && !isLoadMore //没有在下拉刷新中&&没有在上拉加载中
                    && (lastItem == totalItemCount - 1) && (dx > 0 || dy > 0)) {
                onPullUpLoadMore();
            }
        }

        //To find the maximum value in the array
        private int findMax(int[] lastPositions) {
            int max = lastPositions[0];
            for (int value : lastPositions) {
                if (value > max) {
                    max = value;
                }
            }
            return max;
        }
    }


    /**
     * Solve IndexOutOfBoundsException exception
     */
    public class onTouchRecyclerView implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (isLoadingNoTouch) {
                return isRefresh || isLoadMore;
            } else {
                return isLoadingNoTouch;
            }
        }
    }

    public void onPullDownRefresh() {
        isRefresh = true;
        if (mPullDownRefreshListener != null) {
            mPullDownRefreshListener.onRefresh();
        }
    }

    public void onPullUpLoadMore() {
        isLoadMore = true;
        if (mPullUpLoadMoreListener != null && !isRefresh) {
            if (isHasMore()) {
                loadMoreProgressBar.setVisibility(View.VISIBLE);
                mLoadMoreText.setText("加载中...");
                setFooterViewShow();
                mPullUpLoadMoreListener.onLoadMore();
            } else {
                isLoadMore = false;
                loadMoreProgressBar.setVisibility(View.GONE);
                mLoadMoreText.setText("没有更多数据...");
                setFooterViewShow();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setFooterViewHide();
                    }
                }, footerAnimateDuration * 3);

            }
        }
    }


    private void setFooterViewShow() {
        mFooterView.animate().translationY(0).setDuration(footerAnimateDuration).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mFooterView.setVisibility(View.VISIBLE);
            }
        }).start();
        invalidate();
    }

    private void setFooterViewHide() {
        mFooterView.animate().translationY(mFooterView.getHeight()).setDuration(footerAnimateDuration * 2).setInterpolator(new AccelerateDecelerateInterpolator()).start();
    }


    public interface PullDownRefreshListener {
        void onRefresh();
    }

    public interface PullUpLoadMoreListener {
        void onLoadMore();
    }

    public void setPullDownRefreshListener(PullDownRefreshListener pullDownRefreshListener) {
        this.mPullDownRefreshListener = pullDownRefreshListener;
    }


    public void setPullUpLoadMoreListener(PullUpLoadMoreListener mPullUpLoadMoreListener) {
        this.mPullUpLoadMoreListener = mPullUpLoadMoreListener;
    }


    /**
     * 下拉刷新完成
     */
    public void setPullDownRefreshCompleted() {
        isRefresh = false;
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (pullDownRefreshEnable && outRefreshLayout == null)
                    mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        if (outRefreshLayout != null) outRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                outRefreshLayout.setRefreshing(false);
            }
        });


    }


    /**
     * 上拉加载更多完成
     */
    public void setPullUpLoadMoreCompleted() {
        isLoadMore = false;
        loadMoreProgressBar.setVisibility(View.INVISIBLE);
        mLoadMoreText.setText("加载完成");
        setFooterViewHide();
    }


    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * 设置是否还有更多数据
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }


    public boolean isPullDownRefreshEnable() {
        return pullDownRefreshEnable;
    }

    /**
     * 设置是否允许下拉
     */
    public void setPullDownRefreshEnable(boolean pullDownRefreshEnable) {
        this.pullDownRefreshEnable = pullDownRefreshEnable;
//        setSwipeRefreshEnable(pullDownRefreshEnable);
    }

    public void setSwipeRefreshEnable(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public boolean isPullUpLoadMoreEnable() {
        return pullUpLoadMoreEnable;
    }

    /**
     * 设置是否允许上拉加载
     */
    public void setPullUpLoadMoreEnable(boolean pullUpLoadMoreEnable) {
        this.pullUpLoadMoreEnable = pullUpLoadMoreEnable;
    }

    /**
     * 加载中是否禁止触摸控件：默认false
     *
     * @param loadingNoTouch
     */
    public void setLoadingNoTouch(boolean loadingNoTouch) {
        isLoadingNoTouch = loadingNoTouch;
    }

    /**
     * 设置FooterView背景
     */
    public void setFooterViewBackgroundColor(int color) {
        mFooterView.setBackgroundColor(ContextCompat.getColor(mContext, color));
    }

    /**
     * 设置上拉加载文字显示
     */
    public void setFooterViewText(CharSequence text) {
        mLoadMoreText.setText(text);
    }

    /**
     * 设置上拉加载文字颜色
     */
    public void setFooterViewTextColor(int color) {
        mLoadMoreText.setTextColor(ContextCompat.getColor(mContext, color));
    }



    /*TODO 设置一个外部的下拉刷新控件 SwipeRefreshLayout*/
    /*TODO 外部的SwipeRefreshLayout启用，当前封装中的SwipeRefreshLayout弃用*/

    private SwipeRefreshLayout outRefreshLayout;

    public void setOutSwipeRefreshLayout(SwipeRefreshLayout outRefreshLayout) {
        this.outRefreshLayout = outRefreshLayout;
        if (outRefreshLayout != null) {
            setPullDownRefreshEnable(false);
            setSwipeRefreshEnable(false);
            initOutSwipeRefreshLayout();
        }
    }


    private void initOutSwipeRefreshLayout() {
        outRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
        outRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefresh && !isLoadMore) {//启动下拉刷新
                    setHasMore(true);
                    onPullDownRefresh();
                } else {
                    outRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

}
