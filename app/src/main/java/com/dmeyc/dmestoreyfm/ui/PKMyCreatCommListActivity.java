package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
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
import com.dmeyc.dmestoreyfm.bean.MyCreatCommListBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CommDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.Bind;

public class PKMyCreatCommListActivity extends BaseActivity {
    MyCreatCommListBean myCreatCommListBean;
    @Bind(R.id.lv_mycreatcomm)
    ListView lv_mycreatcomm;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mycreatcomm;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        myCreatCommListBean=DataClass.myCreatCommListBean;
        getData();
    }
    public class MyCommViewHolder{
        RoundedImageView iv_commheader;
        TextView tv_mmbname;
        ImageView iv_grouptype;
        public MyCommViewHolder(View view){
            iv_grouptype=view.findViewById(R.id.iv_grouptype);
            iv_commheader=view.findViewById(R.id.iv_commheader);
            tv_mmbname=view.findViewById(R.id.tv_mmbname);
        }
    }
    MyCreatCommListBean creatrbean;
    private CommDialog.Builder builder;
    private CommDialog mDialog;
    private int chanpos;
    public void getData(){
        ParamMap.Build pb=  new ParamMap.Build();
        RestClient.getYfmNovate(PKMyCreatCommListActivity.this).post(Constant.API.YFM_GETCOMM,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .build(), new DmeycBaseSubscriber<MyCreatCommListBean>() {
                    @Override
                    public void onSuccess(final MyCreatCommListBean bean) {
                        creatrbean=bean;
                        lv_mycreatcomm.setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return bean.getData().size();
                            }

                            @Override
                            public Object getItem(int i) {
                                return null;
                            }

                            @Override
                            public long getItemId(int i) {
                                return 0;
                            }
                            MyCommViewHolder myCommViewHolder;
                            @Override
                            public View getView(int i, View view, ViewGroup viewGroup) {
                                if(view==null){
                                    view=getLayoutInflater().inflate(R.layout.adapter_mycreatecommitem,null);
                                    myCommViewHolder=new MyCommViewHolder(view);
                                    view.setTag(myCommViewHolder);
                                }else {
                                    myCommViewHolder=(MyCommViewHolder) view.getTag();
                                }
                                GlideUtil.loadImage(PKMyCreatCommListActivity.this,bean.getData().get(i).getGroup_logo(),myCommViewHolder.iv_commheader);
                                myCommViewHolder.tv_mmbname.setText(bean.getData().get(i).getGroup_name());

                                if(creatrbean.getData().get(i).getGroup_type().equals("1")){
                                    myCommViewHolder.iv_grouptype.setVisibility(View.GONE);
                                }else if(creatrbean.getData().get(i).getGroup_type().equals("3")){
                                    myCommViewHolder.iv_grouptype.setVisibility(View.VISIBLE);
                                    myCommViewHolder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
                                }else if(creatrbean.getData().get(i).getGroup_type().equals("4")){
                                    myCommViewHolder.iv_grouptype.setVisibility(View.VISIBLE);
                                    myCommViewHolder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
                                }else if(creatrbean.getData().get(i).getGroup_type().equals("5")){
                                    myCommViewHolder.iv_grouptype.setVisibility(View.VISIBLE);
                                    myCommViewHolder.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
                                }
                                return view;
                            }
                        });
                    }
                });


        lv_mycreatcomm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                chanpos=i;
                builder = new CommDialog.Builder(PKMyCreatCommListActivity.this);
                showTwoButtonDialog("提示\n确认发起挑战吗?", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        //这里写自定义处理XXX
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        ToastUtil.show(i+"wolaildsjfdsf");
                        ToChangeIn();
                        mDialog.dismiss();
                        //这里写自定义处理XXX
//                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        Uri data = Uri.parse("tel:" + activityDeatilBean.getData().getActivity_phone_no());
//                        intent.setData(data);
//                        startActivity(intent);
                    }
                });

//                startActivity(new Intent(PKMyCreatCommListActivity.this,PKInforActivity.class).putExtra("activityid",creatrbean.getActivityid()).
//                        putExtra("groupidpk",myCreatCommListBean.getData().get(i).getGroup_id()).putExtra("gorupid",creatrbean.getPkgroupid().get(i)));
//                finish();
            }
        });
    }

    private void showTwoButtonDialog(String alertText, String confirmText, String cancelText, View.OnClickListener conFirmListener, View.OnClickListener cancelListener) {
        mDialog = builder.setMessage(alertText)
                .setPositiveButton(confirmText, conFirmListener)
                .setNegativeButton(cancelText, cancelListener)
                .createTwoButtonDialog();
        mDialog.show();
    }

    public void ToChangeIn(){
        RestClient.getYfmNovate(PKMyCreatCommListActivity.this).post(Constant.API.YFM_TOCHALANGE,
                new ParamMap.Build().
                        addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN)).
                        addParams("group_pk_id",getIntent().getIntExtra("groupkid",-1)).
                        addParams("groupId", creatrbean.getData().get(chanpos).getGroup_id())
                        .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                    @Override
                    public void onSuccess(final PublishActionAfterBean bean) {
                             ToastUtil.show(bean.getMsg());
                              finish();
                   }
                 });
            }
}
