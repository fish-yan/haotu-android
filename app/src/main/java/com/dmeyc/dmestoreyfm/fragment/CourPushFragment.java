package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.dmeyc.dmestoreyfm.ui.ActionRecordActivity;
import com.dmeyc.dmestoreyfm.ui.CourseBuyActivity;
import com.dmeyc.dmestoreyfm.ui.PublishActivity;
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
public class CourPushFragment  extends BaseFragment {
    private ListView lv_record;
    private TextView  tv_nodata,tv_pulish;

    int gooupid;
    int ispk;
    int activityid;
    @SuppressLint("ValidFragment")
    public CourPushFragment(int groupid,int ispk){
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
        lv_record=(ListView) mRootView.findViewById(R.id.lv_record);
        tv_pulish=mRootView.findViewById(R.id.tv_pulish);
        tv_pulish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final List<String> strings = new ArrayList<>();
                strings.add("单次活动");
                strings.add("上门活动");
                strings.add("打包课程");
                StyledDialog.buildBottomItemDialog(getActivity(), strings, "cancle",  new MyItemDialogListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
//                      ToastUtil.show("sss+"+position);
                        Intent intent = new Intent();
                        if(0==position){
                          final List<String> strings = new ArrayList<>();
                           Intent intent1 = new Intent();
                          intent1.putExtra("ispk",0);
                        intent1.putExtra("groupid",gooupid);
                        intent1.setClass(getActivity(), PublishActivity.class);
                        startActivity(intent);
                        }else if(1==position){

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
//        requestData();
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
    CourseAdapter courseAdapter;
    private void requestData() {

        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_COMMACTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id",gooupid)
                .addParams("is_group_pk", ispk)
                .build(), new DmeycBaseSubscriber<CommRecordListBean>() {
            @Override
            public void onSuccess(final CommRecordListBean bean) {

                commRecordListBean=bean;
                if(bean.getData().getActivity_list().size()>0){
                    tv_nodata.setVisibility(View.GONE);
                    lv_record.setVisibility(View.VISIBLE);
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_record.setVisibility(View.GONE);
                }
                courseAdapter  =new CourseAdapter();
                lv_record.setAdapter(courseAdapter);

                lv_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               getActivity(). startActivity(new Intent(getActivity(),CourseBuyActivity.class));
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
            case 6:
                //点击第一个菜单项要做的事，如获取点击listview的位置
//                Toast.makeText(getActivity(), "0", Toast.LENGTH_LONG).show();
                deletCourse();
                break;
            case 7:
                //点击第二个菜单项要做的事，如获取点击的数据
//                Toast.makeText(getActivity(),"1", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("groupid",gooupid);
                intent.putExtra("activityid",activityid);
                if(0==ispk){
                    intent.putExtra("ispk",0);
                    intent.setClass(getActivity(), PublishActivity.class);
                    startActivity(intent);
                }else {
                    intent.putExtra("ispk",1);
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void deletCourse() {
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_DELETACTON, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_activity_id",activityid)
                .build(), new DmeycBaseSubscriber<CommRecordListBean>() {
            @Override
            public void onSuccess(CommRecordListBean bean) {
//                ToastUtil.show(bean.getMsg());
                requestData();
            }
        });

    }


    public class ViewHorder{
        private TextView tv_title,tv_projecttype,tv_time,tv_losandwin;
        private CircleImageView civ_avatar;
        public ViewHorder(View view){
            civ_avatar=(CircleImageView)view.findViewById(R.id.civ_avatar);
            tv_title=(TextView) view.findViewById(R.id.tv_title);
            tv_projecttype=(TextView) view.findViewById(R.id.tv_projecttype);
            tv_time=(TextView) view.findViewById(R.id.tv_time);
            tv_losandwin=(TextView) view.findViewById(R.id.tv_losandwin);
        }

    }

    public class CourseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return commRecordListBean.getData().getActivity_list().size();
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
                convertView=  LayoutInflater.from(getActivity()).inflate(R.layout.adapter_actionrecord, null);
                viewHoder=new ViewHorder(convertView);
                convertView.setTag(viewHoder);
            }else {
                viewHoder=(ViewHorder) convertView.getTag();
            }
            GlideUtil.loadImage(getActivity(),commRecordListBean.getData().getActivity_list().get(position).getGroup_logo(),viewHoder.civ_avatar);
            viewHoder.tv_title.setText(commRecordListBean.getData().getActivity_list().get(position).getGroup_name());
            viewHoder.tv_projecttype.setText(commRecordListBean.getData().getActivity_list().get(position).getProject_type());
            viewHoder.tv_time.setText(commRecordListBean.getData().getActivity_list().get(position).getStart_date());
            if("1".equals(commRecordListBean.getData().getActivity_list().get(position).getStatus())){
                viewHoder.tv_losandwin.setText("报名中");
            }else if("2".equals(commRecordListBean.getData().getActivity_list().get(position).getStatus())){
                viewHoder.tv_losandwin.setText("进行中");
            }else if("3".equals(commRecordListBean.getData().getActivity_list().get(position).getStatus())){
                viewHoder.tv_losandwin.setText("活动完成");
            }

            lv_record.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    activityid=commRecordListBean.getData().getActivity_list().get(position).getActivity_id();
                    if(commRecordListBean.getData().getActivity_list().get(position).getIsDelete()==1){
                        contextMenu.add(Menu.NONE, 6, 0, "删除");
                    } if(commRecordListBean.getData().getActivity_list().get(position).getIsEdit()==1){
                        contextMenu.add(Menu.NONE, 7, 0, "编辑");
                    } /*if(bean.getData().getActivity_list().get(position).getIsEnterSocre()==1){
                                    contextMenu.add(Menu.NONE, 8, 0, "录入比分");
                                }*/
                }



            });

            return convertView;
        }
    }
}
