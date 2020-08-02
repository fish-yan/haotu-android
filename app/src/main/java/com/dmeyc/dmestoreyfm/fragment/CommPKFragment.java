package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.ActionRecordListBean;
import com.dmeyc.dmestoreyfm.bean.CommRecordListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.ActionRecordActivity;
import com.dmeyc.dmestoreyfm.ui.PublishActivity;
import com.dmeyc.dmestoreyfm.ui.SorcerInActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class CommPKFragment  extends BaseFragment {
    private ListView lv_pkrecord;
    private TextView  tv_nodata,tv_pulish;

    int gooupid;
    int ispk;
    int activityid;
    @SuppressLint("ValidFragment")
    public CommPKFragment(int groupid,int ispk){
        this.gooupid=groupid;
        this.ispk=ispk;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_commrecord;
    }

    @Override
    protected void initData() {
        tv_nodata=(TextView) mRootView.findViewById(R.id.tv_nodata);
        lv_pkrecord=(ListView) mRootView.findViewById(R.id.lv_record);
        tv_pulish=mRootView.findViewById(R.id.tv_pulish);
        tv_pulish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<String> strings = new ArrayList<>();
                strings.add("发布活动");
                strings.add("发布PK");

                StyledDialog.buildBottomItemDialog(getActivity(), strings, "cancle",  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
//                      ToastUtil.show("sss+"+position);
                        Intent intent = new Intent();
                        if(0==position){
                            intent.putExtra("ispk",0);
                        }else {
                            intent.putExtra("ispk",1);
                        }
                        intent.putExtra("groupid",gooupid);
                        intent.setClass(getActivity(), PublishActivity.class);
//        intent.setClass(getContext(), ReleaseActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onBottomBtnClick() {
//                        ToastUtil.show("sss+");
                    }
                }).show();
            }
        });
//        lv_pkrecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(activityid!=0||!TextUtils.isEmpty(activityid+"")){
//                    startActivity(new Intent(getActivity(),ActionItemActivity.class).putExtra("activityid",commRecordListBean.getData().getActivity_list().get(i).getActivity_id()));
//                }else {
////                    ToastUtil.show("该活动取消");
//                }
//
//            }
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    @Override
    protected void initData(View view) {

    }
    CommRecordListBean commRecordListBean;
    private void requestData() {

        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_COMMACTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id",gooupid)
                .addParams("is_group_pk", ispk)
                .build(), new DmeycBaseSubscriber<CommRecordListBean>() {
            @Override
            public void onSuccess(final CommRecordListBean bean) {
//                ToastUtil.show(bean.getMsg());
                commRecordListBean=bean;
                if(bean.getData().getActivity_list().size()>0){
                    tv_nodata.setVisibility(View.GONE);
                    lv_pkrecord.setVisibility(View.VISIBLE);
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_pkrecord.setVisibility(View.GONE);
                }

                lv_pkrecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(activityid!=0||!TextUtils.isEmpty(activityid+"")){
                            startActivity(new Intent(getActivity(),ActionItemActivity.class).putExtra("activityid",commRecordListBean.getData().getActivity_list().get(i).getActivity_id()));
                        }else {
//                    ToastUtil.show("该活动取消");
                        }

                    }
                });


                lv_pkrecord.setAdapter(new BaseAdapter() {
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
                    public View getView(final int position, View convertView, ViewGroup parent) {
                        ViewHorder viewHoder=null;
                        if(convertView==null){
                            convertView=  LayoutInflater.from(getActivity()).inflate(R.layout.adapter_pkrecord, null);
                            viewHoder=new ViewHorder(convertView);
                            convertView.setTag(viewHoder);
                        }else {
                            viewHoder=(ViewHorder) convertView.getTag();
                        }
                        GlideUtil.loadImage(getActivity(),bean.getData().getActivity_list().get(position).getGroup_logo(),viewHoder.civ_avatar);
                        viewHoder.tv_title.setText(bean.getData().getActivity_list().get(position).getGroup_name());
                        viewHoder.tv_projecttype.setText(bean.getData().getActivity_list().get(position).getProject_type());
                        viewHoder.tv_time.setText(bean.getData().getActivity_list().get(position).getStart_date());
                        if("1".equals(bean.getData().getActivity_list().get(position).getStatus())){
                            viewHoder.tv_losandwin.setText("活动报名中");
                        }else if("2".equals(bean.getData().getActivity_list().get(position).getStatus())){
                            viewHoder.tv_losandwin.setText("活动进行中");
                        }else if("3".equals(bean.getData().getActivity_list().get(position).getStatus())){
                            viewHoder.tv_losandwin.setText("活动完成");
                        }else if("0".equals(bean.getData().getActivity_list().get(position).getStatus())){
                            viewHoder.tv_losandwin.setText("活动匹配中");
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

                        if(bean.getData().getActivity_list().get(position).getIs_safe().equals("0")){
                            viewHoder.iv_isprode.setVisibility(View.GONE);
                        }else {
                            viewHoder.iv_isprode.setVisibility(View.VISIBLE);
                        }




                        viewHoder.tv_commperson.setText(bean.getData().getActivity_list().get(position).getSign_up_no()+"/"+bean.getData().getActivity_list().get(position).getTotal_no());
                        lv_pkrecord.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                            @Override
                            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                                activityid=bean.getData().getActivity_list().get(position).getActivity_id();
                                if(bean.getData().getActivity_list().get(position).getIsDelete()==1){
                                    contextMenu.add(Menu.NONE, 3, ispk, "删除");
                                } if(bean.getData().getActivity_list().get(position).getIsEdit()==1){
                                    contextMenu.add(Menu.NONE, 4, ispk, "编辑");
                                } if(bean.getData().getActivity_list().get(position).getIsEnterSocre()==1){
                                    contextMenu.add(Menu.NONE, 5, ispk, "录入比分");
                                }
                            }
                        });

                        return convertView;
                    }
                });



            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    //长按菜单响应函数

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        //关键代码在这里
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case 3:
                //点击第一个菜单项要做的事，如获取点击listview的位置
//                Toast.makeText(getActivity(), "0", Toast.LENGTH_LONG).show();
                deletpk();
                break;
            case 4:
                //点击第二个菜单项要做的事，如获取点击的数据
//                Toast.makeText(getActivity(),"1", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("groupid",gooupid);
                intent.putExtra("activityid",activityid);
                if(0==ispk){
                    intent.putExtra("ispk",-1);
                    intent.setClass(getActivity(), PublishActivity.class);
                    startActivity(intent);
                }else {
                    intent.putExtra("ispk",1);
                    intent.setClass(getActivity(), PublishActivity.class);
                    startActivity(intent);
                }

                break;
            case 5:
                startActivity(new Intent(getActivity(),SorcerInActivity.class).putExtra("activityid",activityid).putExtra("ispk",1).putExtra("groupid",gooupid));
                break;
        }
        return super.onContextItemSelected(item);
    }
//
//    private void deletpk() {
//        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_DELETpk, new ParamMap.Build()
//                .addParams("user_token", Constant.TEST_USERTOKEN)
//                .addParams("group_pk_id ",activityid)
//                .build(), new DmeycBaseSubscriber<CommRecordListBean>() {
//            @Override
//            public void onSuccess(CommRecordListBean bean) {
//                ToastUtil.show(bean.getMsg());
//            }
//        });
//
//    }


    public class ViewHorder{
        private TextView tv_title,tv_projecttype,tv_time,tv_losandwin,tv_commperson;
        private CircleImageView civ_avatar;
        private ImageView iv_gouptype,iv_isprode;
        public ViewHorder(View view){
            civ_avatar=(CircleImageView)view.findViewById(R.id.civ_avatar);
            tv_title=(TextView) view.findViewById(R.id.tv_title);
            tv_projecttype=(TextView) view.findViewById(R.id.tv_projecttype);
            tv_time=(TextView) view.findViewById(R.id.tv_time);
            tv_losandwin=(TextView) view.findViewById(R.id.tv_losandwin);
            tv_commperson=(TextView) view.findViewById(R.id.tv_commperson);
            iv_gouptype=view.findViewById(R.id.iv_gouptype);
            iv_isprode=view.findViewById(R.id.iv_isprode);
        }
    }

    public void deletpk(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_DELETPKACTION, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",activityid)
                .build(), new DmeycBaseSubscriber<CommRecordListBean>() {
            @Override
            public void onSuccess(CommRecordListBean bean) {
                ToastUtil.show(bean.getMsg());
            }
        });
    }
}
