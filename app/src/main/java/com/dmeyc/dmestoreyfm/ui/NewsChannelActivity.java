package com.dmeyc.dmestoreyfm.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.base.NewsChannelAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.NewsChannelTable;
import com.dmeyc.dmestoreyfm.listener.ItemDragHelperCallback;
import com.dmeyc.dmestoreyfm.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewsChannelActivity extends BaseActivity {

    private NewsChannelAdapter mNewsChannelAdapterMine;
    private NewsChannelAdapter mNewsChannelAdapterMore;

//    @BindView(R.id.news_channel_mine_rv)
    RecyclerView news_channel_mine_rv;
//    @BindView(R.id.news_channel_more_rv)
    RecyclerView news_channel_more_rv;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_channel;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    List<NewsChannelTable> newsChannelsMine=new ArrayList<>();
    List<NewsChannelTable> newsChannelsMore=new ArrayList<>();
    NewsChannelTable nct=null;
    @Override
    protected void initData() {
        news_channel_mine_rv=findViewById(R.id.news_channel_mine_rv);
        news_channel_more_rv=findViewById(R.id.news_channel_more_rv);

        for (int i=0;i<20;i++){
            nct=new NewsChannelTable();
            nct.setNewsChannelName("我的"+i);
            if(i%2==0){
                nct.setNewsChannelFixed(false);
            }else {
                nct.setNewsChannelFixed(true);
            }
            newsChannelsMine.add(nct);
            newsChannelsMore.add(nct);
        }

        initRecyclerViewMineAndMore(newsChannelsMine, newsChannelsMore);
    }
    private void initRecyclerViewMineAndMore(List<NewsChannelTable> newsChannelsMine, List<NewsChannelTable> newsChannelsMore) {
        initRecyclerView(news_channel_mine_rv, newsChannelsMine, true);
        initRecyclerView(news_channel_more_rv, newsChannelsMore, false);
    }

    private void initRecyclerView(RecyclerView recyclerView, List<NewsChannelTable> newsChannels
            , final boolean isChannelMine) {
        // !!!加上这句将不能动态增加列表大小。。。
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (isChannelMine) {
            mNewsChannelAdapterMine = new NewsChannelAdapter(newsChannels);
            recyclerView.setAdapter(mNewsChannelAdapterMine);
            setChannelMineOnItemClick();

            initItemDragHelper();
        } else {
            mNewsChannelAdapterMore = new NewsChannelAdapter(newsChannels);
            recyclerView.setAdapter(mNewsChannelAdapterMore);
            setChannelMoreOnItemClick();
        }

    }

    private void setChannelMineOnItemClick() {
        mNewsChannelAdapterMine.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = mNewsChannelAdapterMine.getData().get(position);
                boolean isNewsChannelFixed = newsChannel.getNewsChannelFixed();
                if (!isNewsChannelFixed) {
                    mNewsChannelAdapterMore.add(mNewsChannelAdapterMore.getItemCount(), newsChannel);
                    mNewsChannelAdapterMine.delete(position);

//                    mNewsChannelPresenter.onItemAddOrRemove(newsChannel, true);
                }
            }
        });
    }


    private void initItemDragHelper() {
        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback(mNewsChannelAdapterMine);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
//        itemTouchHelper.attachToRecyclerView(mNewsChannelMineRv);

        mNewsChannelAdapterMine.setItemDragHelperCallback(itemDragHelperCallback);
    }

    private void setChannelMoreOnItemClick() {
        mNewsChannelAdapterMore.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsChannelTable newsChannel = mNewsChannelAdapterMore.getData().get(position);
                mNewsChannelAdapterMine.add(mNewsChannelAdapterMine.getItemCount(), newsChannel);
                mNewsChannelAdapterMore.delete(position);

//                mNewsChannelPresenter.onItemAddOrRemove(newsChannel, false);

            }
        });
    }
}
