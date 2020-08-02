package com.dmeyc.dmestoreyfm.config;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newui.common.anchorlist.AnchorLivesFragment;
import com.dmeyc.dmestoreyfm.newui.common.goods.GoodsFragment;
import com.dmeyc.dmestoreyfm.newui.common.living.LivingFragment;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionFragment;
import com.dmeyc.dmestoreyfm.newui.home.search.searchvideo.SearchVideoPresenter;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.video.PersonVideoFragment;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/24
 */
public class PagerListConfig {
    public static List<String> getTabTitles() {
        List<String> titles = new ArrayList<>();
        String role = SPUtils.getStringData(Constant.Config.ROLECODE);
        switch (role) {
            case CommonConfig.ROLE_NORMAL:
                titles.add("视频");
                titles.add("点赞");
                break;
            case CommonConfig.ROLE_GROUP:
                titles.add("活动");
                titles.add("视频");
                titles.add("直播");
                titles.add("点赞");

                break;
            case CommonConfig.ROLE_COCAH:
                titles.add("课程");
                titles.add("视频");
                titles.add("直播");
                titles.add("点赞");

                break;
            case CommonConfig.ROLE_MERCHANT:
                titles.add("商品");
                titles.add("视频");
                titles.add("直播");
                titles.add("点赞");

                break;
            case CommonConfig.ROLE_ANCHOR:
                titles.add("视频");
                titles.add("直播");
                titles.add("点赞");
                break;
        }
        return titles;
    }

    public static List<BaseFragment> getTabFragments() {
        List<BaseFragment> fragments = new ArrayList<>();
        String role = SPUtils.getStringData(Constant.Config.ROLECODE);

        switch (role) {
            case CommonConfig.ROLE_NORMAL:
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIST,""));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIKE,""));
                break;
            case CommonConfig.ROLE_GROUP:
                fragments.add(GroupActionFragment.newInstance(GroupActionFragment.FROM_MINE_ACTION));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIST,""));
                fragments.add(AnchorLivesFragment.newInstance(AnchorLivesFragment.TYPE_HOME,""));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIKE,""));

                break;
            case CommonConfig.ROLE_COCAH:
                fragments.add(GoodsFragment.newInstance(GoodsFragment.TYPE_COURSE_HOME_MINE));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIST,""));
                fragments.add(AnchorLivesFragment.newInstance(AnchorLivesFragment.TYPE_HOME,""));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIKE,""));

                break;
            case CommonConfig.ROLE_MERCHANT:
                fragments.add(GoodsFragment.newInstance(GoodsFragment.TYPE_GOODS_HOME_MINE));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIST,""));
                fragments.add(AnchorLivesFragment.newInstance(AnchorLivesFragment.TYPE_HOME,""));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIKE,""));

                break;
            case CommonConfig.ROLE_ANCHOR:
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIST,""));
                fragments.add(AnchorLivesFragment.newInstance(AnchorLivesFragment.TYPE_HOME,""));
                fragments.add(PersonVideoFragment.newInstance(SearchVideoPresenter.TYPE_VIDEO_LIKE,""));
                break;
        }
        return fragments;
    }
}
