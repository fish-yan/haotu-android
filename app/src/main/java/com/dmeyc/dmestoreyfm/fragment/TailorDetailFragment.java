package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.TailorListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jockie on 2017/12/4
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class TailorDetailFragment extends BaseFragment {

    //    @Bind(R.id.recycleview)
//    RecyclerView mRecycleView;
    @Bind(R.id.ll_father)
    LinearLayout ll_father;

    private TailorListBean.DataBean item;


    public TailorDetailFragment(TailorListBean.DataBean item) {
        this.item = item;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fm_tailor_detail;
    }

    @Override
    protected void initData() {
//        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mRecycleView.setAdapter(new CommonAdapter<TailorListBean.DataBean>(getActivity(), R.layout.item_rv_tailor_detail, item) {
//            @Override
//            protected void convert(ViewHolder holder, TailorListBean.DataBean infoBean, int position) {
//                holder.setText(R.id.tv_detail_sort,String.valueOf(position + 1));
//
////                holder.setText(R.id.tv_detail_desc,infoBean.getName());
////                holder.setText(R.id.tv_detail_number,infoBean.getValue());
//            }
//        });
        List<String> detail_desc = new ArrayList<>();
        detail_desc.add("裸脚踝");
        detail_desc.add("臂长");
        detail_desc.add("胸围");
        detail_desc.add("下档长");
        detail_desc.add("肩宽");
        detail_desc.add("腕围");
        detail_desc.add("腰围");
        detail_desc.add("臀围");
        detail_desc.add("身高");
        detail_desc.add("体重");
        List<String> detail_number = new ArrayList<>();
        detail_number.add(String.valueOf(item.getAnkle()));
        detail_number.add(String.valueOf(item.getArm()));
        detail_number.add(String.valueOf(item.getBust()));
        detail_number.add(String.valueOf(item.getDownSide()));
        detail_number.add(String.valueOf(item.getShoulder()));
        detail_number.add(String.valueOf(item.getWrist()));
        detail_number.add(String.valueOf(item.getWaistline()));
        detail_number.add(String.valueOf(item.getHipline()));
        detail_number.add(String.valueOf(item.getPheight()));
        detail_number.add(String.valueOf(item.getPweight()));
        ll_father.removeAllViews();
        for (int i = 0; i <detail_desc.size() ; i++) {

            final RelativeLayout sonView = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(
                    R.layout.item_rv_tailor_detail, null);
            TextView tv_detail_sort = sonView.findViewById(R.id.tv_detail_sort);
            TextView tv_detail_desc = sonView.findViewById(R.id.tv_detail_desc);
            TextView tv_detail_number = sonView.findViewById(R.id.tv_detail_number);
            tv_detail_sort.setText(String.valueOf(i + 1));
            tv_detail_desc.setText(detail_desc.get(i));
            tv_detail_number.setText(detail_number.get(i));
            ll_father.addView(sonView);
        }
    }

    @Override
    protected void initData(View view) {

    }
}
