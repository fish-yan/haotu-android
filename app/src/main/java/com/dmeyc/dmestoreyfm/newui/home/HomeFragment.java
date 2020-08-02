package com.dmeyc.dmestoreyfm.newui.home;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseNewTabFragment;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionSelectFragment;
import com.dmeyc.dmestoreyfm.newui.home.message.MessageActivity;
import com.dmeyc.dmestoreyfm.newui.home.search.SearchHistoryActivity;
import com.dmeyc.dmestoreyfm.newui.home.video.VideoHomeFragment;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/23
 */
public class HomeFragment extends BaseNewTabFragment {
    @Bind(R.id.iv_search)
    ImageView mIvSearch;

    @Bind(R.id.iv_message)
    ImageView mIvMessage;
    @Bind(R.id.cl_top)
    ConstraintLayout mClTop;

    @Bind(R.id.iv_message_red_point)
    ImageView mIvMessageRedPoint;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_home_new;
    }



    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                boolean isVideo = "视频".equals(tab.getText().toString());
                reSetTopStyle(isVideo);
                EventBus.getDefault().post(new MyEvent.MainBottomBar(isVideo));
                int tabColor = isVideo? getResources().getColor(R.color.white) : getResources().getColor(R.color.black);

                mTabLayout.setTabTextColors(tabColor,tabColor);
                mTabLayout.setSelectedTabIndicatorColor(tabColor);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    /**
     * tab title list
     *
     * @param mTitleLists
     */
    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("视频");
        mTitleLists.add("群活动");
    }

    /**
     * 导航条长度形态改变,适应字体长度或者等分, 详情见Util
     */
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(12));
    }

    /**
     * fragment list
     *
     * @param mFragmentLists
     */
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.add(new VideoHomeFragment());
        mFragmentLists.add(new GroupActionSelectFragment());
    }

    @OnClick({R.id.iv_search, R.id.iv_message})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                SearchHistoryActivity.newInstance(mContent);
                break;
            case R.id.iv_message:
                if (!CommonUtil.isLogin(getContext())) {
                    return;
                }
                if (CommonUtil.hasUnReadMessage()){
                    mIvMessageRedPoint.setVisibility(View.GONE);
                }
                MessageActivity.newInstance(mContent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addMessageRedPoint(RefreshEvent.PUSH_NEW_INFO evet){
        mIvMessageRedPoint.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void reSetTopStyle(boolean isVideo) {
        int searchRes = isVideo ? R.drawable.icon_search_white : R.drawable.icon_search_black;
        int messageRes = isVideo ? R.drawable.icon_no_message_white : R.drawable.icon_no_message_black;
        mIvSearch.setImageResource(searchRes);
        mIvMessage.setImageResource(messageRes);
        if (isVideo) {
            mClTop.setBackgroundResource(R.drawable.shape_home_top_bg);
        } else mClTop.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
