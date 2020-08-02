package com.dmeyc.dmestoreyfm.video.topic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.topic.addtopic.AddTopicActivity;
import com.dmeyc.dmestoreyfm.wedgit.PullRecyclerView;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class TopicListActivity extends BaseActivity {

    public static void newIntent(Activity activity) {
        Intent intent = new Intent(activity, TopicListActivity.class);
        activity.startActivity(intent);
    }

    private ArrayList<TopicListBean.DataBean> mListData = new ArrayList<>();
    private TopicListAdapter mAdapter;

    @Bind(R.id.edit_topic)
    EditText edit_topic;

    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.rv_topic)
    PullRecyclerView mRvTopic;

    @OnClick(R.id.tv_add)
    void toAddClick() {
        //新增话题按钮
        AddTopicActivity.newIntent(TopicListActivity.this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_topic_list;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        initRecyclerView();
        initEditText();
        getTopicList();
        hideKeyboard(edit_topic.getWindowToken());
    }

    /**
     * 事件响应方法
     * 接收消息
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventVideoBean event){
        String msg = event.getKey();
        if (Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_CREATE.equals(msg)) {
            //选择完话题
             finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initEditText(){
        edit_topic.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideKeyboard(edit_topic.getWindowToken());
                    mSearchTopic = edit_topic.getText().toString().trim();
                    if(!TextUtils.isEmpty(mSearchTopic)){
                        mPage = 1;
                        getTopicList();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new TopicListAdapter(TopicListActivity.this, R.layout.item_topic_list, mListData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TopicListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTopic.getRecyclerView().setLayoutManager(layoutManager);
        mRvTopic.setAdapter(mAdapter);
        mRvTopic.setOutSwipeRefreshLayout(mSwipeRefreshLayout);
        //下拉刷新
        mRvTopic.setPullDownRefreshListener(new PullRecyclerView.PullDownRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                getTopicList();
            }
        });
        //上拉加载
        mRvTopic.setPullUpLoadMoreListener(new PullRecyclerView.PullUpLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage ++;
                getTopicList();
            }
        });
        mAdapter.setOnItemClickListener(new TopicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 点击话题
                String topic = mListData.get(position).getContent();
                EventVideoBean bean = new EventVideoBean();
                bean.setTopic(topic);
                bean.setTopicId(mListData.get(position).getId());
                bean.setKey(Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC);
                EventBus.getDefault().post(bean);
                finish();
            }
        });

    }

    private int mPage = 1;
    private String mSearchTopic;

    private void getTopicList() {
        final ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("page", mPage);
        mParams.addParams("size", 20);
        mParams.addParams("sorttype", 1);
        if(!TextUtils.isEmpty(mSearchTopic)){
            mParams.addParams("search", mSearchTopic);
        }
        RestClient.getYfmNovate(TopicListActivity.this).get(Constant.API.VIDEO_TO_GET_LIST_IMG, mParams.build(),
                new DmeycBaseSubscriber<TopicListBean>() {
            @Override
            public void onSuccess(TopicListBean bean) {
                //
                if(mPage == 1){
                    mListData.clear();
                }
                if(bean.getData() != null && bean.getData().size() > 0){
                    mListData.addAll(bean.getData());
                }
                if(mPage == 1 && mListData.size() == 0){
                    ToastUtil.show("暂无话题数据");
                }
                mAdapter.notifyDataSetChanged();
                stopLoading();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                stopLoading();
            }
        });
    }

    /* 停止上拉刷新或者下拉加载 */
    private void stopLoading() {
        mRvTopic.setPullUpLoadMoreCompleted();
        mRvTopic.setPullDownRefreshCompleted();
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
