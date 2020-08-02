package com.dmeyc.dmestoreyfm.video.releasedynamic.associated;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;
import com.dmeyc.dmestoreyfm.video.releasedynamic.addassociated.AddAssociatedActivity;
import com.dmeyc.dmestoreyfm.wedgit.PullRecyclerView;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class AssociatedListActivity  extends BaseActivity {

    public static void newIntent(Activity activity,AssociatedBean.DataBean bean){
        Intent intent = new Intent(activity,AssociatedListActivity.class);
        intent.putExtra("bean",bean);
        activity.startActivity(intent);
    }

    private ArrayList<AssociatedBean.DataBean> mListData = new ArrayList<>();
    private AssociatedRecyclerviewAdapter mAdapter;

    private AssociatedBean.DataBean mSelectedBean;

    @Bind(R.id.edit_topic)
    EditText edit_topic;

    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.rv_topic)
    PullRecyclerView mRvTopic;

    private String mSearchBusinessName;
    private int mPage = 1;

    @OnClick(R.id.tv_add)
    void toAddClick() {
        //新增模板商户数据
        AddAssociatedActivity.newIntent(AssociatedListActivity.this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_associated_list_layout;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mSelectedBean = (AssociatedBean.DataBean) getIntent().getSerializableExtra("bean");
        initRecyclerView();
        initEditText();
        hideKeyboard(edit_topic.getWindowToken());
    }

    /**
     * 事件响应方法
     * 接收消息
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventVideoBean event) {
        String msg = event.getKey();
        if (Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_ASSOCIATED_CREATE.equals(msg)){
            //创建新模板商户成功
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPage = 1;
        getAssociatedListData();
    }

    private void initRecyclerView() {
        mAdapter = new AssociatedRecyclerviewAdapter(AssociatedListActivity.this, R.layout.item_associated_list_layout, mListData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AssociatedListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTopic.getRecyclerView().setLayoutManager(layoutManager);
        mRvTopic.setAdapter(mAdapter);
        mRvTopic.setOutSwipeRefreshLayout(mSwipeRefreshLayout);
        //下拉刷新
        mRvTopic.setPullDownRefreshListener(new PullRecyclerView.PullDownRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                getAssociatedListData();
            }
        });
        //上拉加载
        mRvTopic.setPullUpLoadMoreListener(new PullRecyclerView.PullUpLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage ++;
                getAssociatedListData();
            }
        });

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                AssociatedBean.DataBean mCurrentBean =  mListData.get(position);
                EventVideoBean bean = new EventVideoBean();
                bean.setAssobusinessId(mCurrentBean.getId());
                bean.setName(mCurrentBean.getName());
                bean.setType(mCurrentBean.getType());
                bean.setDistance(mCurrentBean.getDistance());
                bean.setKey(Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_ASSOCIATED);
                EventBus.getDefault().post(bean);
                finish();
            }
            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initEditText(){
        edit_topic.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideKeyboard(edit_topic.getWindowToken());
                    mSearchBusinessName = edit_topic.getText().toString().trim();
                    mPage = 1;
                    getAssociatedListData();
                    return true;
                }
                return false;
            }
        });
    }

    private void getAssociatedListData(){
        //获取关联数据列表
        final ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("page", mPage);
        mParams.addParams("size", 50);
        if(!TextUtils.isEmpty(mSearchBusinessName)){
            mParams.addParams("search", mSearchBusinessName);
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LONGTUDE))) {
            mParams.addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LATITUDE))) {
            mParams.addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        }
        RestClient.getYfmNovate(AssociatedListActivity.this).post(Constant.API.GET_ASSOCIATED_DATA, mParams.build(),
                new DmeycBaseSubscriber<AssociatedBean>(){
                    @Override
                    public void onSuccess(AssociatedBean bean) {
                        if(mPage == 1){
                            mListData.clear();
                        }
                        if(bean != null && bean.getData() != null && bean.getData().size() > 0){
                            mListData.addAll(bean.getData());
                        }
                        if(mSelectedBean != null){
                            for(int i = 0 ; i < mListData.size() ; i++){
                                mListData.get(i).setSelected(false);
                                if(mSelectedBean.getId() == mListData.get(i).getId()){
                                    mListData.get(i).setSelected(true);
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                        stopLoading();
                    }
                    @Override
                    public void onError(Throwable e){
                        super.onError(e);
                        ToastUtil.show(e.getMessage());
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
