package com.dmeyc.dmestoreyfm.base;


import com.dmeyc.dmestoreyfm.bean.NewsChannelTable;

import java.util.List;

/**
 * Created by Eric on 2017/1/19.
 */

public interface NewsChannelView extends BaseView {
    void initRecyclerViews(List<NewsChannelTable> newsChannelsMine, List<NewsChannelTable> newsChannelsMore);
}
