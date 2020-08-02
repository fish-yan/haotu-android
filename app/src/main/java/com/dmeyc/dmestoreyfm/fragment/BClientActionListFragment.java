package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.BClientActionBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.BClientChangeSetActivity;
import com.dmeyc.dmestoreyfm.ui.BClientPKdetail;
import com.dmeyc.dmestoreyfm.ui.BClientPublishActionActivity;
import com.dmeyc.dmestoreyfm.ui.BClientPublishChalegeActivity;
import com.dmeyc.dmestoreyfm.ui.BClientSorckerInActivity;
import com.dmeyc.dmestoreyfm.ui.BclientSocketInPKActivity;
import com.dmeyc.dmestoreyfm.ui.PkResultActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class BClientActionListFragment extends BaseFragment {
    @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;
    private int itempos;
    @SuppressLint("ValidFragment")
    public BClientActionListFragment(int itempos){
        this.itempos=itempos;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bclientactionlist;
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void initData(View view) {
//        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    class ActionViewHolder{
        TextView tv_sockerin,tv_cancel;
        public ActionViewHolder(View view){
//            tv_result= view.findViewById(R.id.tv_result);
            tv_sockerin=view.findViewById(R.id.tv_sockerin);
            tv_cancel=view.findViewById(R.id.tv_cancel);
        }
    }
    AllActionAdapter allActionAdapter;
    public void getData(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_BCLIENALLACTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("user_token", "6d70fb6591f54373ad45c56951931f4b")
                .addParams("status", itempos+"")
                .build(), new DmeycBaseSubscriber<BClientActionBean>() {
            @Override
            public void onSuccess(final BClientActionBean bean) {
//                ToastUtil.show(bean.getMsg());
                newActionBean=bean;
                allActionAdapter=new AllActionAdapter();
                lv_pkinglist.setAdapter(allActionAdapter);
                allActionAdapter.notifyDataSetChanged();
            }
        });
    }
    private int clickpos;
    BClientActionBean newActionBean;

    private int startflag=0;
    private int tijiao=0;
    public class AllActionAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newActionBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        ActionViewHolder actionViewHolder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_actionitem,null);
                actionViewHolder=new ActionViewHolder(view);
                view.setTag(actionViewHolder);
            }else {
                actionViewHolder=(ActionViewHolder) view.getTag();
            }
            GlideUtil.loadImage(getActivity(),newActionBean.getData().get(i).getGroupLogo(),actionViewHolder.cv_header);
            actionViewHolder.tv_actiontime.setText(newActionBean.getData().get(i).getStartTime());
            actionViewHolder.tv_actionclub.setText(newActionBean.getData().get(i).getActivityName());
            actionViewHolder.tv_adress.setText(newActionBean.getData().get(i).getActivityAddress());
            if(newActionBean.getData().get(i).getStatus().equals("1")){

                if("0".equals(newActionBean.getData().get(i).getIsSigned())){
                        if("1".equals(newActionBean.getData().get(i).getActivityProperty())){
                                if("1".equals(newActionBean.getData().get(i).getIsOwer())){
                                    if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                        actionViewHolder.tv_stopaction.setVisibility(View.VISIBLE);
                                    }else {
                                        actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                                    }

                                    if("1".equals(newActionBean.getData().get(i).getIsPk())){
                                        if("1".equals(newActionBean.getData().get(i).getIsSigned())){
                                            if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                                actionViewHolder.tv_changeaction.setVisibility(View.VISIBLE);
                                            }else {
                                                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                            }

                                            actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                                        }else {
                                            actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                            actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                                        }

                                    }else {
                                        actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                        if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                            actionViewHolder.tv_changeoragnalaction.setVisibility(View.VISIBLE);
                                        }else {
                                            actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                                        }
                                    }
                                }else {
                                    actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                    actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                                    actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                                }
                        }else {
                            if("1".equals(newActionBean.getData().get(i).getIsOwer())){
                                 if("1".equals(newActionBean.getData().get(i).getIsPk())){
                                     if("1".equals(newActionBean.getData().get(i).getIsSponser())){
                                         if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                             actionViewHolder.tv_changeaction.setVisibility(View.VISIBLE);
                                         }else {
                                             actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                         }

                                         actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                                     }else {
                                         actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                         actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                                     }

                                 }else {
                                     actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                     if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                         actionViewHolder.tv_changeaction.setVisibility(View.VISIBLE);
                                     }else {
                                         actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                     }

                                 }
                            }else {
                                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                            }
                                 actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                        }
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                    actionViewHolder.tv_startaction.setVisibility(View.GONE);
                    actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                }else {
                    if("1".equals(newActionBean.getData().get(i).getActivityProperty())){
                        if("1".equals(newActionBean.getData().get(i).getIsOwer())){
                            if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                actionViewHolder.tv_stopaction.setVisibility(View.VISIBLE);
                            }else {
                                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                            }

                            actionViewHolder.tv_changeset.setVisibility(View.VISIBLE);
                        }else {
                            actionViewHolder.tv_changeset.setVisibility(View.GONE);
                            actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                        }
                    }else {
                        if("1".equals(newActionBean.getData().get(i).getIsOwer())){
                            actionViewHolder.tv_changeset.setVisibility(View.VISIBLE);
                        }else {
                            actionViewHolder.tv_changeset.setVisibility(View.GONE);
                        }
                        actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                    }

                    actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                    actionViewHolder.tv_startaction.setVisibility(View.GONE);
                    actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                }

                if("3".equals(newActionBean.getData().get(i).getGroupType())||"5".equals(newActionBean.getData().get(i).getGroupType())){
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                }
            }else if(newActionBean.getData().get(i).getStatus().equals("0")){
                if(newActionBean.getData().get(i).getIsPk().equals("1")){
                    if(newActionBean.getData().get(i).getIsSponser()!=null&&newActionBean.getData().get(i).getIsSponser().equals("1")){

                            if(!TextUtils.isEmpty(newActionBean.getData().get(i).getGovernmentIsStart())){
                                if("0".equals(newActionBean.getData().get(i).getGovernmentIsStart())&&"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                    actionViewHolder.tv_startaction.setVisibility(View.VISIBLE);
                                    startflag=1;
                                    actionViewHolder.tv_startaction.setText("开始活动");
                                }else {
                                    actionViewHolder.tv_startaction.setVisibility(View.GONE);
                                }
                            }
                            actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                            actionViewHolder.tv_changeaction.setVisibility(View.GONE                                                );

                    }else {
                        if(!TextUtils.isEmpty(newActionBean.getData().get(i).getGovernmentIsStart())){
                            if("0".equals(newActionBean.getData().get(i).getGovernmentIsStart())&&"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                                actionViewHolder.tv_startaction.setVisibility(View.VISIBLE);
                                actionViewHolder.tv_startaction.setText("开始活动");
                                startflag=1;
                            }else {
                                actionViewHolder.tv_startaction.setVisibility(View.GONE);
                            }
                        }
                        actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                        actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                    }
                }else {
                    actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                    actionViewHolder.tv_changeoragnalaction.setVisibility(View.VISIBLE);
                }
                actionViewHolder.tv_result.setVisibility(View.GONE);
                actionViewHolder.tv_sokin.setVisibility(View.GONE);
                actionViewHolder.tv_changeset.setVisibility(View.GONE);
//                actionViewHolder.tv_startaction.setVisibility(View.GONE);
                actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
//                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                if("3".equals(newActionBean.getData().get(i).getGroupType())||"5".equals(newActionBean.getData().get(i).getGroupType())){
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                }
            }else if(newActionBean.getData().get(i).getStatus().equals("2")){

                actionViewHolder.tv_sokin.setVisibility(View.VISIBLE);
                if("1".equals(newActionBean.getData().get(i).getIsBeginSaveScore())){
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                }else {
                    if(newActionBean.getData().get(i).getIsGovernment().equals("1")){
                        actionViewHolder.tv_changeset.setVisibility(View.GONE);
                    }else {
                        actionViewHolder.tv_changeset.setVisibility(View.VISIBLE);
                    }

                }
                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                actionViewHolder.tv_result.setVisibility(View.GONE);
                actionViewHolder.tv_startaction.setVisibility(View.GONE);
                actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                if("3".equals(newActionBean.getData().get(i).getGroupType())||"5".equals(newActionBean.getData().get(i).getGroupType())){
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                }
                if("1".equals(newActionBean.getData().get(i).getIsGovernment())&&"1".equals(newActionBean.getData().get(i).getIsPk())&&"1".equals(newActionBean.getData().get(i).getGovernmentIsStart())){
                    actionViewHolder.tv_changeset.setVisibility(View.VISIBLE);
                    actionViewHolder.tv_changeset.setText("提交活动");
                    tijiao=1;
                }
//                if(newActionBean.getData().get(i).getIsGovernment().equals("1")){
//                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
//                }
                if(newActionBean.getData().get(i).getIsGovernment().equals("1")&&!"1".equals(newActionBean.getData().get(i).getIsPk())){
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                }
            }else if(newActionBean.getData().get(i).getStatus().equals("3")){
                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                actionViewHolder.tv_result.setVisibility(View.VISIBLE);
                actionViewHolder.tv_sokin.setVisibility(View.VISIBLE);
                actionViewHolder.tv_changeset.setVisibility(View.GONE);
                actionViewHolder.tv_startaction.setVisibility(View.GONE);
                actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                if("3".equals(newActionBean.getData().get(i).getGroupType())||"5".equals(newActionBean.getData().get(i).getGroupType())||("1".equals(newActionBean.getData().get(i).getIsGovernment())&&!"1".equals(newActionBean.getData().get(i).getIsPk()))){
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                }
            }else if(newActionBean.getData().get(i).getStatus().equals("5")){

                if(newActionBean.getData().get(i).getIsOwer()!=null&&newActionBean.getData().get(i).getIsOwer().equals("1")){
                    if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                        actionViewHolder.tv_startaction.setVisibility(View.VISIBLE);
                    }else {
                        actionViewHolder.tv_startaction.setVisibility(View.GONE);
                    }

                }else {
                    actionViewHolder.tv_startaction.setVisibility(View.GONE);
                }
                if(newActionBean.getData().get(i).getIsPk().equals("1")){
                    if("1".equals(newActionBean.getData().get(i).getIsSponser())){
                        if(!"1".equals(newActionBean.getData().get(i).getIsGovernment())){
                            actionViewHolder.tv_startaction.setVisibility(View.VISIBLE);
                        }else {
                            actionViewHolder.tv_startaction.setVisibility(View.GONE);
                        }

                    }
                }
                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                actionViewHolder.tv_result.setVisibility(View.GONE);
                actionViewHolder.tv_sokin.setVisibility(View.GONE);
                actionViewHolder.tv_changeset.setVisibility(View.GONE);
                actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
                if("3".equals(newActionBean.getData().get(i).getGroupType())||"5".equals(newActionBean.getData().get(i).getGroupType())){
                    actionViewHolder.tv_result.setVisibility(View.GONE);
                    actionViewHolder.tv_sokin.setVisibility(View.GONE);
                    actionViewHolder.tv_changeset.setVisibility(View.GONE);
                }
            }else if(newActionBean.getData().get(i).getStatus().equals("6")) {
                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                actionViewHolder.tv_result.setVisibility(View.GONE);
                actionViewHolder.tv_sokin.setVisibility(View.GONE);
                actionViewHolder.tv_changeset.setVisibility(View.GONE);
                actionViewHolder.tv_startaction.setVisibility(View.GONE);
                actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
            }else if(newActionBean.getData().get(i).getStatus().equals("7")){
                actionViewHolder.tv_changeaction.setVisibility(View.GONE);
                actionViewHolder.tv_result.setVisibility(View.GONE);
                actionViewHolder.tv_sokin.setVisibility(View.GONE);
                actionViewHolder.tv_changeset.setVisibility(View.GONE);
                actionViewHolder.tv_startaction.setVisibility(View.GONE);
                actionViewHolder.tv_cancleaction.setVisibility(View.GONE);
                actionViewHolder.tv_stopaction.setVisibility(View.GONE);
                actionViewHolder.tv_changeoragnalaction.setVisibility(View.GONE);
            }
            actionViewHolder.tv_result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), BClientPKdetail.class).putExtra("activityid",newActionBean.getData().get(i).getActivityId()));

//                    startActivity(new Intent(getActivity(),CulbResultActivity.class).putExtra("activityid",newActionBean.getData().get(i).getActivityId()));
//                        startActivity(new Intent(getActivity(),NewPkResultActivity.class).putExtra("ispk",newActionBean.getData().get(i).getIsPk()));
                }
            });

            actionViewHolder.tv_sokin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    getActivity().startActivity(new Intent(getActivity(),BClientPublishChalegeActivity.class).putExtra("activityid",newActionBean.getData().get(i).getActivityId()).putExtra("type","1"));
                 if("0".equals(newActionBean.getData().get(i).getIsPk())){
                     startActivity(new Intent(getActivity(), BClientSorckerInActivity.class)
                             .putExtra("activityid",newActionBean.getData().get(i).getActivityId()).putExtra("is_pk",newActionBean.getData().get(i).getIsPk()));
                 }else {
                     startActivity(new Intent(getActivity(), BclientSocketInPKActivity.class)
                             .putExtra("activityid",newActionBean.getData().get(i).getActivityId())
                             .putExtra("is_pk",newActionBean.getData().get(i).getIsPk()).
                                     putExtra("isgover",newActionBean.getData().get(i).getIsGovernment()).putExtra("grovpkid",newActionBean.getData().get(i).getGroupPkId()));
                 }

                }
            });
            if("0".equals(newActionBean.getData().get(i).getIsPk())){
                actionViewHolder.iv_ispk.setVisibility(View.GONE);
            }else {
                actionViewHolder.iv_ispk.setVisibility(View.VISIBLE);
            }
            if("4".equals(newActionBean.getData().get(i).getGroupType())){
                actionViewHolder.iv_intrance.setVisibility(View.VISIBLE);
            }else {
                actionViewHolder.iv_intrance.setVisibility(View.GONE);
            }
            actionViewHolder.ll_actionitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if("0".equals(newActionBean.getData().get(i).getIsPk())){
                        startActivity(new Intent(getActivity(), ActionItemActivity.class).putExtra("activityid", newActionBean.getData().get(i).getActivityId()));
                    }else {
//                        ToastUtil.show(i+"ssss");
                        startActivity(new Intent(getActivity(), PkResultActivity.class)
                                .putExtra("PK_groupid",newActionBean.getData().get(i).getGroupPkId())
                                .putExtra("headurl",newActionBean.getData().get(i).getGroupLogo())
                                .putExtra("graopname",newActionBean.getData().get(i).getGroupName()));
                    }
                }
            });
            actionViewHolder.tv_changeset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                    if(tijiao==0){
                        startActivity(new Intent(getActivity(), BClientChangeSetActivity.class).putExtra("activityid",newActionBean.getData().get(i).getActivityId()));

                    }else {
                        tijiao();
                    }
                }
            });
            actionViewHolder.tv_stopaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                   stopaction();
                }
            });
            actionViewHolder.tv_startaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                    if(0==startflag){
                        startaction();
                    }else {
                        kaishiaction();
                    }
                }
            });
            actionViewHolder.tv_cancleaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                    cancelaction();
                }
            });
            actionViewHolder.tv_changeaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                    getActivity().startActivity(new Intent(getActivity(), BClientPublishChalegeActivity.class).
                   putExtra("activityid",newActionBean.getData().get(i).getActivityId()).putExtra("type","1"));
//                    cancelaction();
                }
            });
            actionViewHolder.tv_changeoragnalaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent bClientPublishActionActivity=  new Intent(getActivity(), BClientPublishActionActivity.class);
                    bClientPublishActionActivity.putExtra("type","1");
                    bClientPublishActionActivity.putExtra("activityid",newActionBean.getData().get(i).getActivityId());
                    getActivity().startActivityForResult(bClientPublishActionActivity,123);
                }
            });
            actionViewHolder.tv_week.setText(newActionBean.getData().get(i).getWeekDay());
            if(TextUtils.isEmpty(newActionBean.getData().get(i).getGovernmentActivityStage())){
                actionViewHolder.tv_machstate.setVisibility(View.GONE);
            }else {
                actionViewHolder.tv_machstate.setVisibility(View.VISIBLE);
                if("2".equals(newActionBean.getData().get(i).getGovernmentActivityStage())){
                    actionViewHolder.tv_machstate.setText("小组赛");
                }else if("3".equals(newActionBean.getData().get(i).getGovernmentActivityStage())){
                    actionViewHolder.tv_machstate.setText("16进8");
                }else if("4".equals(newActionBean.getData().get(i).getGovernmentActivityStage())){
                    actionViewHolder.tv_machstate.setText("8进4");
                }else if("5".equals(newActionBean.getData().get(i).getGovernmentActivityStage())){
                    actionViewHolder.tv_machstate.setText("半决赛");
                }else {
                    actionViewHolder.tv_machstate.setText("决赛");
                }
            }
            return view;
      }
        class ActionViewHolder{
            TextView tv_result,tv_actiontime,tv_actionclub,tv_adress,tv_sokin,tv_changeset,tv_changeaction,tv_startaction,tv_cancleaction,tv_stopaction,tv_week,tv_changeoragnalaction;
            CircleImageView cv_header;
            ImageView iv_ispk,iv_intrance;
            LinearLayout ll_actionitem;
            TextView tv_machstate;
            public ActionViewHolder(View view){
                tv_machstate=view.findViewById(R.id.tv_machstate);
                iv_ispk=view.findViewById(R.id.iv_ispk);
                tv_result= view.findViewById(R.id.tv_result);
                cv_header=(CircleImageView) view.findViewById(R.id.cv_header);
                tv_actiontime=(TextView) view.findViewById(R.id.tv_actiontime);
                tv_actionclub=(TextView) view.findViewById(R.id.tv_actionclub);
                tv_adress=(TextView) view.findViewById(R.id.tv_adress);
                ll_actionitem=(LinearLayout) view.findViewById(R.id.ll_actionitem);
                tv_sokin=(TextView) view.findViewById(R.id.tv_sokin);
                tv_changeset=(TextView)view.findViewById(R.id.tv_changeset);
                tv_changeaction=view.findViewById(R.id.tv_changeaction);
                tv_startaction=view.findViewById(R.id.tv_startaction);
                tv_cancleaction=view.findViewById(R.id.tv_cancleaction);
                tv_stopaction=view.findViewById(R.id.tv_stopaction);
                tv_week=(TextView) view.findViewById(R.id.tv_week);
                tv_changeoragnalaction=(TextView) view.findViewById(R.id.tv_changeoragnalaction);
                iv_intrance=view.findViewById(R.id.iv_intrance);
               }
        }
  }

    public void stopaction(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_STOPACTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",newActionBean.getData().get(clickpos).getActivityId())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                getData();
            }});
    }

    public void startaction(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_STARTCTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",newActionBean.getData().get(clickpos).getActivityId())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                getData();
            }});
    }
    public void kaishiaction(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_kaiqiCTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("pkId",newActionBean.getData().get(clickpos).getGroupPkId())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                getData();
            }});
    }
    public void tijiao(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_TIJIAOCTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("pkId",newActionBean.getData().get(clickpos).getGroupPkId())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                getData();
            }});
    }
    public void cancelaction(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_CANCELACTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_activity_id",newActionBean.getData().get(clickpos).getActivityId())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                getData();
            }});
        }

    public void notifydata(){
//        getData();

//        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_BCLIENALLACTION, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("status", itempos+"")
//                .build(), new DmeycBaseSubscriber<BClientActionBean>() {
//            @Override
//            public void onSuccess(final BClientActionBean bean) {
////                ToastUtil.show(bean.getMsg());
//                newActionBean=bean;
////                allActionAdapter=new AllActionAdapter();
////                lv_pkinglist.setAdapter(allActionAdapter);
//                allActionAdapter.notifyDataSetChanged();
//            }
//        });
    };
}
