package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.ActionRecordActivity;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.ui.CommActionRecord;
import com.dmeyc.dmestoreyfm.ui.CommSettingActivity;
import com.dmeyc.dmestoreyfm.ui.DetailActivity;
import com.dmeyc.dmestoreyfm.ui.VipManagerActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import io.rong.imkit.RongIM;

@SuppressLint("ValidFragment")
public class MyCommFragment extends BaseFragment  implements View.OnClickListener {
    private ListView lv_my_commlist;
//    commAdapter cma;
    MyCommListBean.DataBean dataBean;

//    LinearLayout ll_bottom;
    TextView tv_nodata;
    public MyCommFragment( MyCommListBean.DataBean dataBean ){
        this.dataBean=dataBean;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mycomm;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initData(View view) {
        tv_nodata=view.findViewById(R.id.tv_nodata);
        lv_my_commlist=(ListView) view.findViewById(R.id.lv_my_commlist);

//        cma =new commAdapter();
//        lv_my_commlist.setAdapter(cma);
//        if(dataBean.getCreate_group_list().size()>0){
//            tv_nodata.setVisibility(View.GONE);
//            lv_my_commlist.setVisibility(View.VISIBLE);
//        }else {
//            tv_nodata.setVisibility(View.VISIBLE);
//            lv_my_commlist.setVisibility(View.GONE);
//        }
        lv_my_commlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(getActivity(),ChartInforActivity.class).putExtra("group_id",dataBean.getCreate_group_list().get(i).getGroup_id()));
            }
        });
    }
    @Override
    public void onClick(View view) {
        int viewid=view.getId();
    }

    class ViewHoder{
        public TextView tv_actionmanager,tv_vipmanager,tv_moneymanager,tv_commmanager,tv_cub_name,tv_desc,tv_cub_time;
        private ImageView iv_icon,iv_chat,iv_isprode,iv_grouptype;
        private LinearLayout ll_bottom;
        public ViewHoder(View view){
            tv_actionmanager=view.findViewById(R.id.tv_actionmanager);
            tv_vipmanager=view.findViewById(R.id.tv_vipmanager);
            tv_moneymanager=view.findViewById(R.id.tv_moneymanager);
            tv_commmanager=view.findViewById(R.id.tv_commmanager);
            ll_bottom=(LinearLayout) view.findViewById(R.id.ll_bottom);
            iv_icon=view.findViewById(R.id.iv_icon);
            tv_cub_name=view.findViewById(R.id.tv_cub_name);
            tv_desc=view.findViewById(R.id.tv_desc);
            tv_cub_time=view.findViewById(R.id.tv_cub_time);
            iv_chat=view.findViewById(R.id.iv_chat);
            iv_isprode=view.findViewById(R.id.iv_isprode);
            iv_grouptype=view.findViewById(R.id.iv_grouptype);
        }
    }
//    public void notifiy(int posionfrag){
//        cma.notifyDataSetChanged();
//    }
//
//    public void setData(int pos, MyCommListBean.DataBean dataBean){
//        posionfrag=pos;
//        this.dataBean=dataBean;
//    }
//    public class commAdapter extends BaseAdapter{
//        @Override
//        public int getCount() {
//
//                return dataBean.getCreate_group_count();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(final int i, View view, ViewGroup viewGroup) {
//
//
//
//            ViewHoder viewHoder;
//            if(view==null){
//                view=  getLayoutInflater().inflate(R.layout.adapter_mycommitem,null);
//                viewHoder=new ViewHoder(view);
//                view.setTag(viewHoder);
//            }else {
//                viewHoder=(ViewHoder) view.getTag();
//            }
////            if(posionfrag==0){
//                GlideUtil.loadImage(getActivity(),dataBean.getCreate_group_list().get(i).getGroup_logo(),viewHoder.iv_icon);
//                viewHoder.tv_cub_name.setText(dataBean.getCreate_group_list().get(i).getGroup_name());
////            }else if(posionfrag==1){
////                viewHoder.tv_cub_name.setText(dataBean.getFollow_group_list().get(i).getGroup_name());
////                GlideUtil.loadImage(getActivity(),dataBean.getFollow_group_list().get(i).getGroup_logo(),viewHoder.iv_icon);
////            }else {
////                viewHoder.tv_cub_name.setText(dataBean.getJoin_group_list().get(i).getGroup_name());
////                GlideUtil.loadImage(getActivity(),dataBean.getJoin_group_list().get(i).getGroup_logo(),viewHoder.iv_icon);
////            }
//
//            if(dataBean.getCreate_group_list().get(i).getGroup_type().equals("1")){
//                viewHoder.iv_grouptype.setVisibility(View.GONE);
//            }else if(dataBean.getCreate_group_list().get(i).getGroup_type().equals("3")){
//                viewHoder.iv_grouptype.setVisibility(View.VISIBLE);
//                viewHoder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
//            }else if(dataBean.getCreate_group_list().get(i).getGroup_type().equals("4")){
//                viewHoder.iv_grouptype.setVisibility(View.VISIBLE);
//                viewHoder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
//            }else if(dataBean.getCreate_group_list().get(i).getGroup_type().equals("5")){
//                viewHoder.iv_grouptype.setVisibility(View.VISIBLE);
//                viewHoder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
//            }
//
//
//            if(dataBean.getCreate_group_list().get(i).getIs_safe().equals("0")){
//                viewHoder.iv_isprode.setVisibility(View.GONE);
//            }else {
//                viewHoder.iv_isprode.setVisibility(View.VISIBLE);
//            }
//
//            viewHoder.tv_actionmanager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getActivity(),CommActionRecord.class).putExtra("isMycomm","0").putExtra("groupid",dataBean.getCreate_group_list().get(i).getGroup_id()).putExtra("grouptype",dataBean.getCreate_group_list().get(i).getGroup_type()));
//                }
//            });
//            viewHoder.tv_vipmanager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getActivity(),VipManagerActivity.class).putExtra("groupid",dataBean.getCreate_group_list().get(i).getGroup_id()));
//                }
//            });
//            viewHoder.tv_moneymanager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getActivity(),DetailActivity.class).putExtra("groupid",dataBean.getCreate_group_list().get(i).getGroup_id()));
//                }
//            });
//            viewHoder.tv_commmanager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getActivity(),CommSettingActivity.class).putExtra("groupid",dataBean.getCreate_group_list().get(i).getGroup_id()));
//                }
//            });
//
//            viewHoder.iv_chat.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    RongIM.getInstance().startGroupChat(getActivity(), dataBean.getCreate_group_list().get(i).getGroup_id()+"", dataBean.getCreate_group_list().get(i).getGroup_name());
//                }
//            });
//
////            if(1==posionfrag||2==posionfrag){
////                viewHoder.ll_bottom.setVisibility(View.GONE);
////            }else {
//                viewHoder.ll_bottom.setVisibility(View.VISIBLE);
////            }
//            return view;
//        }
//    }
}
