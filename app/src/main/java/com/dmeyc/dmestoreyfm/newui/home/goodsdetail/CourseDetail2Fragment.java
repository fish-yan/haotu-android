package com.dmeyc.dmestoreyfm.newui.home.goodsdetail;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import butterknife.Bind;

/**
 * create by cxg on 2020/1/1
 */
public class CourseDetail2Fragment extends BaseFragment {
    @Bind(R.id.civ_address)
    CustomItemView mCivAddress;
    @Override
    protected int setContentView() {
        return R.layout.fragment_course_detail2;
    }
    @Override
    protected void initViews() {

    }
    public void setData(GoodsDetailBean.DataBean bean){
        mCivAddress.setTitleHint(bean.getAddress());
        mCivAddress.setmLatitude(bean.getLatitude());
        mCivAddress.setmLongitude(bean.getLongitude());
        mCivAddress.setmAddress(bean.getAddress());
    }
}
