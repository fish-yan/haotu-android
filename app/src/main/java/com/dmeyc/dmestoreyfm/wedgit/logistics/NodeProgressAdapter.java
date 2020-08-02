package com.dmeyc.dmestoreyfm.wedgit.logistics;

import java.util.List;

/**
 * Created by jockie on 2017/11/21
 * Email:jockie911@gmail.com
 */

public interface NodeProgressAdapter{

    /**
     * 返回集合大小
     *
     * @return
     */
    int getCount();

    /**
     * 适配数据的集合
     *
     * @return
     */
    List<LogisticsData> getData();
}