package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.MyCreatGroupBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.BClientActionListFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class BClientClubChalageActivity  extends BaseActivity {
    @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;

    //    ArrayList<MyCreatGroupBean.DataBean> publislist=new ArrayList<>();
    ArrayList<MyCreatGroupBean.DataBean> chalagelist=new ArrayList<>();
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientclubchoice;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        getMyGrop();
    }
    MyCreatGroupBean myCreatGroupBean;
    MyCreatGroupBean.DataBean mydata;
    public void getMyGrop(){
        chalagelist.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETMYCERATGROP, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", 1)
                .build(), new DmeycBaseSubscriber<MyCreatGroupBean>() {
            @Override
            public void onSuccess(final MyCreatGroupBean bean) {
                myCreatGroupBean=bean;
                for (int i=0;i<myCreatGroupBean.getData().size();i++){
                    if("yes".equals(getIntent().getStringExtra("ispublish"))){
                    }else {
                        if("1".equals(myCreatGroupBean.getData().get(i).getGroup_type())||"4".equals(myCreatGroupBean.getData().get(i).getGroup_type())){
                            mydata=new MyCreatGroupBean.DataBean();
                            mydata.setAddress(myCreatGroupBean.getData().get(i).getAddress());
                            mydata.setGroup_id(myCreatGroupBean.getData().get(i).getGroup_id());
                            mydata.setGroup_logo(myCreatGroupBean.getData().get(i).getGroup_logo());
                            mydata.setGroup_name(myCreatGroupBean.getData().get(i).getGroup_name());
                            mydata.setGroup_type(myCreatGroupBean.getData().get(i).getGroup_type());
                            mydata.setProject_name(myCreatGroupBean.getData().get(i).getProject_name());
                            mydata.setProject_type(myCreatGroupBean.getData().get(i).getProject_type());
                            chalagelist.add(mydata);
//                            myCreatGroupBean.getData().remove(i);
                        }
                    }
                  }
                MyCreatAdapter myCreatAdapter=new MyCreatAdapter();
                lv_pkinglist.setAdapter(myCreatAdapter);
            }
        });
    }
    public class MyCreatAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return chalagelist.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        MyGroupViewHorlder actionViewHolder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_clubitem,null);
                actionViewHolder=new MyGroupViewHorlder(view);
                view.setTag(actionViewHolder);
            }else {
                actionViewHolder=(MyGroupViewHorlder) view.getTag();
            }

            if(chalagelist.get(i).getGroup_type().equals("1")){
                actionViewHolder.iv_grouptype.setVisibility(View.GONE);
            }else if(chalagelist.get(i).getGroup_type().equals("3")){
                actionViewHolder.iv_grouptype.setVisibility(View.VISIBLE);
                actionViewHolder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
            }else if(chalagelist.get(i).getGroup_type().equals("4")){
                actionViewHolder.iv_grouptype.setVisibility(View.VISIBLE);
                actionViewHolder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
            }else if(chalagelist.get(i).getGroup_type().equals("5")){
                actionViewHolder.iv_grouptype.setVisibility(View.VISIBLE);
                actionViewHolder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
            }

            GlideUtil.loadImage(BClientClubChalageActivity.this,chalagelist.get(i).getGroup_logo(),actionViewHolder.cv_header);
            actionViewHolder.tv_groupname.setText(chalagelist.get(i).getGroup_name());
            actionViewHolder.tv_desc.setText(chalagelist.get(i).getAddress());
            actionViewHolder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        ToastUtil.show(i+"sss");
                    Intent intent=new Intent();
                    intent.putExtra("groupid",chalagelist.get(i).getGroup_id());
                    intent.putExtra("groupname",chalagelist.get(i).getGroup_name());
                    intent.putExtra("projecttype",chalagelist.get(i).getGroup_type());
                    intent.putExtra("projectname",chalagelist.get(i).getProject_name());
                    setResult(210,intent);
                    finish();
                }
            });
            return view;
        }
        public class MyGroupViewHorlder{
            CircleImageView cv_header;
            TextView tv_groupname,tv_desc;
            LinearLayout ll_item;
            ImageView iv_grouptype;
            public MyGroupViewHorlder(View view){
                cv_header=(CircleImageView) view.findViewById(R.id.cv_header);
                tv_groupname=(TextView ) view.findViewById(R.id.tv_groupname);
                tv_desc=(TextView) view.findViewById(R.id.tv_desc);
                ll_item=(LinearLayout) view.findViewById(R.id.ll_item);
                iv_grouptype=(ImageView) view.findViewById(R.id.iv_grouptype);
            }
        }
    }



}
