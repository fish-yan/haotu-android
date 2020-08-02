package com.dmeyc.dmestoreyfm.newui.home.goodsdetail;

import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.StringUtil;

import butterknife.Bind;

/**
 * create by cxg on 2020/1/1
 */
public class CourseDetail1Fragment extends BaseFragment {


    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.tv_valid_time)
    TextView mTvValidTime;
    @Bind(R.id.tv_feature)
    TextView mTvFeature;
    @Bind(R.id.tv_instruction)
    TextView mTvInstruction;

    @Override
    protected int setContentView() {
        return R.layout.fragment_course_detail1;
    }

    @Override
    protected void initViews() {

    }

    public void setData(GoodsDetailBean.DataBean bean) {
        mTvTime.setText(bean.getStartTime());
        mTvValidTime.setText(bean.getEndTime());
        StringUtil.setTVRetract(bean.getRemark(),mTvInstruction);
    }
}
