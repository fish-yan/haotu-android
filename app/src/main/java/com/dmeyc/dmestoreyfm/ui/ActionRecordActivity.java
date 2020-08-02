package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActionRecordListBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

public class ActionRecordActivity extends BaseActivity implements View.OnClickListener {

    private ListView lv_record;
    private ImageView iv_right_title_bar;

    private TextView tv_nodata;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_actionrecord;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {

        tv_nodata=(TextView) findViewById(R.id.tv_nodata);
        iv_right_title_bar=(ImageView) findViewById(R.id.iv_right_title_bar);
        iv_right_title_bar.setOnClickListener(this);
        if("0".equals(getIntent().getStringExtra("isMycomm"))){
            iv_right_title_bar.setVisibility(View.VISIBLE);
        }

        lv_record=(ListView) findViewById(R.id.lv_record);
        lv_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActionRecordActivity.this,MachesResultActivity.class).putExtra("activityid",actionRecordListBean.getData().getActivity_list().get(position).getActivity_id()));
//                Toast.makeText(ActionRecordActivity.this,position+"ssss",Toast.LENGTH_LONG).show();
               }
        });

//        lv_record.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
////                return false;
////            }
////        });
////        lv_record.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
////            @Override
////            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
////                contextMenu.add(Menu.NONE, 0, 0, "删除");
////                contextMenu.add(Menu.NONE, 1, 0, "分享");
////                contextMenu.add(Menu.NONE, 0, 0, "删除");
////                contextMenu.add(Menu.NONE, 1, 0, "分享");
////            }
////        });
        requestData();
    }
    ActionRecordListBean actionRecordListBean;
    private void requestData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_Activity_RECORDLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ActionRecordListBean>() {
            @Override
            public void onSuccess(final ActionRecordListBean bean) {
//                ToastUtil.show(bean.getMsg());
                actionRecordListBean=bean;
                if(bean.getData().getActivity_list().size()>0){
                    tv_nodata.setVisibility(View.GONE);
                    lv_record.setVisibility(View.VISIBLE);
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_record.setVisibility(View.GONE);
                }
                lv_record.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getActivity_list().size();
                    }
                    @Override
                    public Object getItem(int position) {
                        return null;
                    }
                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHorder viewHoder=null;
                        if(convertView==null){
                            convertView=  LayoutInflater.from(ActionRecordActivity.this).inflate(R.layout.adapter_actionrecord, null);
                            viewHoder=new ViewHorder(convertView);
                            convertView.setTag(viewHoder);
                        }else {
                            viewHoder=(ViewHorder) convertView.getTag();
                        }
                        if(bean.getData().getActivity_list().get(position).getGroupType().equals("1")){
                            viewHoder.iv_gouptype.setVisibility(View.GONE);
                        }else if(bean.getData().getActivity_list().get(position).getGroupType().equals("3")){
                            viewHoder.iv_gouptype.setVisibility(View.VISIBLE);
                            viewHoder.iv_gouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
                        }else if(bean.getData().getActivity_list().get(position).getGroupType().equals("4")){
                            viewHoder.iv_gouptype.setVisibility(View.VISIBLE);
                            viewHoder.iv_gouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
                        }else if(bean.getData().getActivity_list().get(position).getGroupType().equals("5")){
                            viewHoder.iv_gouptype.setVisibility(View.VISIBLE);
                            viewHoder.iv_gouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
                        }
                        if(bean.getData().getActivity_list().get(position).getIsGroupPk().equals("0")){
                            viewHoder.iv_gouptype.setVisibility(View.GONE);
                        }else {
                            viewHoder.iv_gouptype.setVisibility(View.VISIBLE);
                            viewHoder.iv_gouptype.setBackground(getResources().getDrawable(R.drawable.pk_type));
                        }
                        if(bean.getData().getActivity_list().get(position).getIs_safe().equals("0")){
                            viewHoder.iv_isprode.setVisibility(View.GONE);
                        }else {
                            viewHoder.iv_isprode.setVisibility(View.VISIBLE);
                        }
                        GlideUtil.loadImage(ActionRecordActivity.this,bean.getData().getActivity_list().get(position).getGroup_logo(),viewHoder.civ_avatar);
                        viewHoder.tv_title.setText(bean.getData().getActivity_list().get(position).getGroup_name());
                        viewHoder.tv_projecttype.setText(bean.getData().getActivity_list().get(position).getProject_type());
                        viewHoder.tv_time.setText(bean.getData().getActivity_list().get(position).getStart_date());
                        viewHoder.tv_losandwin.setText(bean.getData().getActivity_list().get(position).getWinner_no()+" 胜"+bean.getData().getActivity_list().get(position).getLoser_no()+" 负");
                        return convertView;
                    }
                });
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int vierid=view.getId();
        if(R.id.iv_right_title_bar==vierid){
                    Intent intent = new Intent();
            intent.putExtra("groupid",getIntent().getIntExtra("groupid",-1));
        intent.setClass(this, PublishActivity.class);
//        intent.setClass(getContext(), ReleaseActivity.class);
        startActivity(intent);
        }
    }

    public class ViewHorder{
private TextView tv_title,tv_projecttype,tv_time,tv_losandwin;
private ImageView iv_gouptype,iv_isprode;
        private CircleImageView civ_avatar;
        public ViewHorder(View view){
            civ_avatar=(CircleImageView)view.findViewById(R.id.civ_avatar);
            tv_title=(TextView) view.findViewById(R.id.tv_title);
            tv_projecttype=(TextView) view.findViewById(R.id.tv_projecttype);
            tv_time=(TextView) view.findViewById(R.id.tv_time);
            tv_losandwin=(TextView) view.findViewById(R.id.tv_losandwin);
            iv_gouptype=view.findViewById(R.id.iv_gouptype);
            iv_isprode=view.findViewById(R.id.iv_isprode);
        }
    }
}
