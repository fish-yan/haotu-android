package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
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
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.MyJoinCommBean;
import com.dmeyc.dmestoreyfm.bean.RoClodBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.google.gson.Gson;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

public class MyJoinCommActivity extends BaseActivity {
    @Bind(R.id.lv_createcommlist)
    ListView lv_createcommlist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_myjoin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    MyJoinCommBean dataBean;
    private void getData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_MYCOMMJOIN, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<MyJoinCommBean>() {
            @Override
            public void onSuccess(final MyJoinCommBean bean) {
                dataBean=bean;
               MyCOmmAdapter myCOmmAdapter=new MyCOmmAdapter();
                lv_createcommlist.setAdapter(myCOmmAdapter);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
        lv_createcommlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                TextMessage myTextMessage=null;
//                RoClodBean roClodBean=null;
//
//                    myTextMessage = TextMessage.obtain("欢迎大家报名新活动！----点击报名");
//                    roClodBean =new RoClodBean();
//                    roClodBean.setActivityId(getIntent().getIntExtra("activityid", -1));
//                    roClodBean.setType(DataClass.message.getObjectName());
//
//                Gson gs=new Gson();
//                myTextMessage.setExtra(gs.toJson(roClodBean));
                Message myMessage = Message.obtain(dataBean.getData().get(i).getGroup_id()+"", Conversation.ConversationType.GROUP, DataClass.message.getContent());
                RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {
                        //消息本地数据库存储成功的回调
                    }
                    @Override
                    public void onSuccess(Message message) {
                        //消息通过网络发送成功的回调
                        ToastUtil.show("已转发");
                     finish();
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                        //消息发送失败的回调
                    }
                });

//                startActivity(new Intent(MyJoinCommActivity.this,MyCommEditeActivity.class).putExtra("groupid",dataBean.getData().get(i).getGroup_id()));
            }
        });
    }



    public class MyCOmmAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
       MyCOmmAdapter.CommViewHorler commViewHorler;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_mycommlist,null);
                commViewHorler=new MyCOmmAdapter.CommViewHorler(view);
                view.setTag(commViewHorler);
            }else {
                commViewHorler=(MyCOmmAdapter.CommViewHorler) view.getTag();
            }
            GlideUtil.loadImage(MyJoinCommActivity.this,dataBean.getData().get(i).getGroup_logo(),commViewHorler.cv_commhead);
            commViewHorler.tv_name.setText(dataBean.getData().get(i).getGroup_name());

            if(dataBean.getData().get(i).getGroup_type().equals("1")){
                commViewHorler.iv_grouptype.setVisibility(View.GONE);
            }else if(dataBean.getData().get(i).getGroup_type().equals("3")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
            }else if(dataBean.getData().get(i).getGroup_type().equals("4")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
            }else if(dataBean.getData().get(i).getGroup_type().equals("5")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
            }
            commViewHorler.cv_commhead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MyJoinCommActivity.this,ChartInforActivity.class).putExtra("group_id",dataBean.getData().get(i).getGroup_id()));

                }
            });

            return view;
        }
        public class CommViewHorler{
            CircleImageView cv_commhead;
            ImageView iv_grouptype;
            TextView tv_name;
            public CommViewHorler(View view){
                iv_grouptype=view.findViewById(R.id.iv_grouptype);
                cv_commhead=(CircleImageView) view.findViewById(R.id.cv_commhead);
                tv_name=(TextView) view.findViewById(R.id.tv_name);
            }

        }
    }
}
