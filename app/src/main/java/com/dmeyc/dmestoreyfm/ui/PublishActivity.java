package com.dmeyc.dmestoreyfm.ui;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.view.ItemView;
import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActionRecordListBean;
import com.dmeyc.dmestoreyfm.bean.CommRecordListBean;
import com.dmeyc.dmestoreyfm.bean.SetChangeBean;
import com.dmeyc.dmestoreyfm.bean.WareActivityBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GradeViewForScrollView;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.tamic.novate.Throwable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class PublishActivity extends BaseActivity implements OnAddressChangeListener {
   @Bind(R.id. ll_womanreduce)
   LinearLayout  ll_womanreduce;
    @Bind(R.id. ll_shangmen)
    LinearLayout  ll_shangmen;
    @Bind(R.id. ll_activiyt_type)
    LinearLayout  ll_activiyt_type;

    @Bind(R.id.tv_time)
    TextView tv_time;
//    @Bind(R.id.tv_projecttype)
//    TextView tv_projecttype;
    @Bind(R.id.tv_actiontime)
    ItemView tv_actiontime;

    @Bind(R.id.btn_upload)
    Button btn_upload;

    private PopupMenu popupMenu;

    @Bind(R.id.et_cubname)
    EditText et_cubname;

    @Bind(R.id.et_activitylong)
    EditText et_activitylong;

    @Bind(R.id.cb_womanhas)
    CheckBox cb_womanhas;
    @Bind(R.id.et_toursepeice)
    EditText et_toursepeice;
    @Bind(R.id.et_vipmoney)
    EditText et_vipmoney;

    @Bind(R.id.tv_actionmumber)
    EditText tv_actionmumber;

    @Bind(R.id.et_waitcount)
    EditText et_waitcount;



    @Bind(R.id.tv_publinam)
    TextView tv_publinam;
    @Bind(R.id.tv_selectcity)
    TextView tv_selectcity;
    @Bind(R.id.et_actiondetail)
    EditText et_actiondetail;
//    @Bind(R.id.et_placenum)
//    EditText et_placenum;

    @Bind(R.id.et_phonemum)
    EditText et_phonemum;

    @Bind(R.id.et_backtext)
    EditText et_backtext;

    @Bind(R.id.cb_GUDING)
     CheckBox cb_GUDING;
    @Bind(R.id.cb_linshi)
    CheckBox cb_linshi;
    @Bind(R.id.ll_wo_procation)
    LinearLayout ll_wo_procation;
    @Bind(R.id.et_product)
    EditText et_product;

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.cb_yes)
    CheckBox cb_yes;
    @Bind(R.id.cb_no)
    CheckBox cb_no;
    @Bind(R.id.no_gv)
    GradeViewForScrollView no_gv;
    @Bind(R.id.tv_textnum)
    TextView tv_textnum;

    int type=1;
    int iswoman;

    int ispk=-1;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_publish;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {



        et_backtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tv_textnum.setText(et_backtext.getText().toString().trim().length()+"/200");
            }
        });

        ispk= getIntent().getIntExtra("ispk",-1);
        if(ispk==-1&&-1==getIntent().getIntExtra("activityid",-1)){
            tv_title.setText("发布活动");
            tv_publinam.setText("活动场馆");
            et_cubname.setHint("请输入活动名称");
        }else if(ispk==-1&&-1!=getIntent().getIntExtra("activityid",-1)){
            tv_title.setText("编辑活动");
            tv_publinam.setText("活动名称");
            et_cubname.setHint("请输入活动名称");
        }else if(ispk==1&&-1!=getIntent().getIntExtra("activityid",-1)){
            tv_title.setText("编辑PK");
            tv_publinam.setText("活动场馆");
            et_cubname.setHint("请输入活动名称");
            ll_activiyt_type.setVisibility(View.GONE);
        }else if(ispk==1&&-1==getIntent().getIntExtra("activityid",-1)){
            tv_title.setText("发布PK");
            tv_publinam.setText("活动名称");
            et_cubname.setHint("请输入活动名称");
            ll_activiyt_type.setVisibility(View.GONE);
        }else if(ispk==0&&-1!=getIntent().getIntExtra("activityid",-1)){
            tv_title.setText("编辑课程");
            ll_womanreduce.setVisibility(View.GONE);
            ll_wo_procation.setVisibility(View.GONE);
            ll_shangmen.setVisibility(View.GONE);
            tv_publinam.setText("课程名称");
            et_cubname.setHint("请输入课程名称");
        }else if(ispk==0&&-1==getIntent().getIntExtra("activityid",-1)){
            tv_title.setText("发布课程");
            ll_womanreduce.setVisibility(View.GONE);
            ll_wo_procation.setVisibility(View.GONE);
            ll_shangmen.setVisibility(View.GONE);
            tv_publinam.setText("课程名称");
            et_cubname.setHint("请输入课程名称");
        }

        cb_womanhas.setChecked(false);
        ll_wo_procation.setVisibility(View.GONE);
        cb_GUDING.setChecked(true);
        cb_GUDING.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    type=1;
                    cb_linshi.setChecked(false);
                }else {
                    type=2;
                    cb_linshi.setChecked(true);
                }
            }
        });
        cb_linshi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    type=2;
                    cb_GUDING.setChecked(false);
                }else {
                    type=1;
                    cb_GUDING.setChecked(true);
                }
            }
        });
        cb_womanhas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ll_wo_procation.setVisibility(View.VISIBLE);
                }else {
                    ll_wo_procation.setVisibility(View.GONE);
                }
            }
        });
        cb_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb_yes.setChecked(false);
                }else {
                    cb_yes.setChecked(true);
                }
            }
        });
        cb_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb_no.setChecked(false);
                }else {
                    cb_no.setChecked(true);
                }
            }
        });

if(-1!=getIntent().getIntExtra("activityid",-1)){
    if(0==ispk){
        initcourse();
        no_gv.setVisibility(View.GONE);
    }else {
        initAction();//pk 活动
    }
}
    }

    private void initcourse() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_INITACION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<WareActivityBean>() {
            @Override
            public void onSuccess(WareActivityBean bean) {
//                ToastUtil.show(bean.getMsg());
                tv_time.setText(bean.getData().getStart_date());
                et_cubname.setText(bean.getData().getActivity_name());
                et_activitylong.setText(bean.getData().getDuration()+"小时");
                if("1".equals(bean.getData().getIs_w_discount())){
                    cb_womanhas.setChecked(true);
                    ll_wo_procation.setVisibility(View.VISIBLE);
                }else {
                    cb_womanhas.setChecked(false);
                    ll_wo_procation.setVisibility(View.GONE);
                }
                ll_shangmen.setVisibility(View.VISIBLE);
                ll_womanreduce.setVisibility(View.GONE);
                ll_wo_procation.setVisibility(View.GONE);
                if(bean.getData().getIs_door().equals("1")){
                    cb_no.setChecked(false);
                    cb_yes.setChecked(true);
                }else {
                    cb_no.setChecked(true);
                    cb_yes.setChecked(false);
                }
                et_toursepeice.setText(bean.getData().getM_visitor_amount()+"");
                et_vipmoney.setText(bean.getData().getM_member_amount()+"");
                tv_actionmumber.setText(bean.getData().getTotal_no()+"");
                et_waitcount.setText(bean.getData().getReplace_no()+"");
                province=bean.getData().getProvince();
                city=bean.getData().getCity();
                district=bean.getData().getArea();
                if(bean.getData().getProvince().equals(bean.getData().getCity())){
                    tv_selectcity.setText(bean.getData().getProvince()+bean.getData().getArea());
                }else {
                    tv_selectcity.setText(bean.getData().getProvince()+bean.getData().getCity()+bean.getData().getArea());
                }
                et_actiondetail.setText(bean.getData().getActivity_address());
//                et_placenum.setText(bean.getData().getField_no());
                et_phonemum.setText(bean.getData().getActivity_phone_no());
                et_backtext.setText(bean.getData().getRemark());
                if(bean.getData().getActivity_type().equals("1")){
                    cb_GUDING.setChecked(true);
                    cb_linshi.setChecked(false);
                }else {
                    cb_GUDING.setChecked(false);
                    cb_linshi.setChecked(true);
                }
                et_product.setText(bean.getData().getW_discount_amount()+"");
            }
        });

    }

    private void initAction() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_INITACION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<WareActivityBean>() {
            @Override
            public void onSuccess(WareActivityBean bean) {
//                ToastUtil.show(bean.getMsg());
                if(bean.getData().getActivity_type().equals("1")){
                    no_gv.setVisibility(View.VISIBLE);
                }else {
                    no_gv.setVisibility(View.GONE);
                }
                tv_time.setText(bean.getData().getStart_date());
                et_cubname.setText(bean.getData().getActivity_name());
                et_activitylong.setText(bean.getData().getDuration()+"小时");
                if("1".equals(bean.getData().getIs_w_discount())){
                    cb_womanhas.setChecked(true);
                    ll_wo_procation.setVisibility(View.VISIBLE);
                }else {
                    cb_womanhas.setChecked(false);
                    ll_wo_procation.setVisibility(View.GONE);
                }
                et_toursepeice.setText(bean.getData().getM_visitor_amount()+"");
                et_vipmoney.setText(bean.getData().getM_member_amount()+"");
                tv_actionmumber.setText(bean.getData().getTotal_no()+"");
                et_waitcount.setText(bean.getData().getReplace_no()+"");
                province=bean.getData().getProvince();
                city=bean.getData().getCity();
                district=bean.getData().getArea();
                if(bean.getData().getProvince().equals(bean.getData().getCity())){
                    tv_selectcity.setText(bean.getData().getProvince()+bean.getData().getArea());
                }else {
                    tv_selectcity.setText(bean.getData().getProvince()+bean.getData().getCity()+bean.getData().getArea());
                }
                et_actiondetail.setText(bean.getData().getActivity_address());
//                et_placenum.setText(bean.getData().getField_no());
                et_phonemum.setText(bean.getData().getActivity_phone_no());
                et_backtext.setText(bean.getData().getRemark());
                if(bean.getData().getActivity_type().equals("1")){
                    cb_GUDING.setChecked(true);
                    cb_linshi.setChecked(false);
                }else {
                    cb_GUDING.setChecked(false);
                    cb_linshi.setChecked(true);
                }
                et_product.setText(bean.getData().getW_discount_amount()+"");
                no_gv.setVisibility(View.VISIBLE);
                initsetchange();
            }
        });

    }

    @OnClick({R.id.tv_projecttype,R.id.tv_actiontime,R.id.tv_time,R.id.btn_upload,R.id.tv_selectcity})
    public void onClick(View view){
        int viewid=view.getId();
     /*   if(viewid==R.id.tv_projecttype){
//            popupMenu.showAtLocation(tv_projecttype, Gravity.RIGHT, 0, 0);
            popupMenu.showLocation(R.id.tv_projecttype);// 设置弹出菜单弹出的位置
            // 设置回调监听，获取点击事件
            popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                @Override
                public void onClick(PopupMenu.MENUITEM item, String str) {
                    Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();

                }
            });
        }else*/ if(viewid==R.id.tv_actiontime) {
            showDatePickDialog(DateType.TYPE_ALL);
        }else if(viewid==R.id.tv_time){
            showDatePickDialog(DateType.TYPE_ALL);
        }else if(viewid==R.id.btn_upload){
         publishActivity();
        }else if(viewid==R.id.tv_selectcity){
            initWheel();
            getData(view);
        }
    }

    String api;
    private void publishActivity() {
            if(TextUtils.isEmpty(et_cubname.getText().toString().trim())){
                ToastUtil.show("请填写俱乐部名称");
                return;
            }
        if(TextUtils.isEmpty(tv_time.getText().toString().trim())){
            ToastUtil.show("请选择活动时间");
            return;
        }
        if(TextUtils.isEmpty(et_activitylong.getText().toString().trim())){
            ToastUtil.show("请填写活动时长");
            return;
        }
        if(TextUtils.isEmpty(et_vipmoney.getText().toString().trim())){
            ToastUtil.show("请填写会员价格");
            return;
        }

        if(TextUtils.isEmpty(et_toursepeice.getText().toString().trim())){
            ToastUtil.show("请填写游客价格");
            return;
        }
        if(Integer.parseInt(et_vipmoney.getText().toString().trim())>Integer.parseInt(et_toursepeice.getText().toString().trim())){

                ToastUtil.show("会员价不可以高于游客价格");
                 return;
        }
        if(TextUtils.isEmpty(tv_actionmumber.getText().toString().trim())){
            ToastUtil.show("请填写活动人数");
            return;
        }
        if(TextUtils.isEmpty(et_waitcount.getText().toString().trim())){
            ToastUtil.show("请填写后补人数");
            return;
        }
        if(TextUtils.isEmpty(tv_selectcity.getText().toString().trim())){
            ToastUtil.show("请选择城市");
            return;
        }
        if(TextUtils.isEmpty(et_actiondetail.getText().toString().trim())){
            ToastUtil.show("请填写详细地址");
            return;
        }
//        if(TextUtils.isEmpty(et_placenum.getText().toString().trim())){
//            ToastUtil.show("请填写场地编号");
//            return;
//        }
        if(TextUtils.isEmpty(et_phonemum.getText().toString().trim())){
            ToastUtil.show("请填写手机号");
            return;
        }
        if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
            ToastUtil.show("请填写活动备注");
            return;
        }
        ParamMap.Build build = new ParamMap.Build();
   /*     if(ispk==0){
            if(cb_yes.isChecked()||cb_no.isChecked()){
                build.addParams("is_door",cb_no.isChecked()?"0":"1");
            }else {
                ToastUtil.show("请选择是否上门");
            }
        }else*/ {
            if(cb_womanhas.isChecked()){
                iswoman=1;
            }else {
                iswoman=0;
            }

            if(iswoman==1){
                if(TextUtils.isEmpty(et_product.getText().toString().trim())){
                    ToastUtil.show("请输入优惠金额");
                    return;
                }
                build.addParams("w_discount_amount",et_product.getText().toString().trim());
            }
        }



            if(ispk==-1&&getIntent().getIntExtra("activityid",-1)==-1){
                api=Constant.API.YFM_ACTIONPUBLISH;
            }else if(ispk==-1&&getIntent().getIntExtra("activityid",-1)!=-1){
                api=Constant.API.YFM_ACTIONUPDAT;
                build .addParams("activity_id",getIntent().getIntExtra("activityid",-1));

            }else if(ispk==1&&getIntent().getIntExtra("activityid",-1)!=-1){
                api=Constant.API.YFM_ACTIONUPDATPK;
            }else if(ispk==1&&getIntent().getIntExtra("activityid",-1)==-1){
                api=Constant.API.YFM_PUBLISHPK;
                build .addParams("activity_id",getIntent().getIntExtra("activityid",-1));
            }
            else if(ispk==0&&getIntent().getIntExtra("activityid",-1)==-1){
                api=Constant.API.YFM_PUBLISHCOURSE;
            } else if(ispk==0&&getIntent().getIntExtra("activityid",-1)!=-1){
                api=Constant.API.YFM_ACTIONUPDAT;
                build .addParams("activity_id",getIntent().getIntExtra("activityid",-1));
            }
        RestClient.getYfmNovate(this).post(api, build
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_phone_no",et_phonemum.getText().toString().trim())
//                .addParams("status",1)
//                .addParams("isGroupPk", 0)
                .addParams("group_id", getIntent().getIntExtra("groupid",-1))
                .addParams("activity_name",et_cubname.getText().toString().trim())
                .addParams("activity_type", type)
                .addParams("start_date",tv_time.getText().toString().trim())
                .addParams("duration",et_activitylong.getText().toString().trim().split("小时")[0])
                .addParams("province", province)
                .addParams("city",city)
                .addParams("area", district)
                .addParams("activity_address",et_actiondetail.getText().toString().trim())
                .addParams("total_no",tv_actionmumber.getText().toString().trim())
                .addParams("replace_no", et_waitcount.getText().toString().trim())
                .addParams("is_w_discount",iswoman)
                .addParams("m_member_amount", Double.parseDouble(et_vipmoney.getText().toString().trim()))
                .addParams("m_visitor_amount",Double.parseDouble(et_toursepeice.getText().toString().trim()))
                .addParams("remark", et_backtext.getText().toString().trim())
//                .addParams("is_invite_discount",city)
//                .addParams("invite_discount_amount",district)
//                .addParams("field_no", et_placenum.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
//                ToastUtil.show(bean.getMsg());

                finish();
            }
            @Override
            public void onError(Throwable e) {

            }
        });


    }

    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                tv_time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
//                Toast.makeText(PublishActivity.this,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date),Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
    private ChooseAddressWheel chooseAddressWheel = null;
    private void initWheel() {
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setOnAddressChangeListener(this);
    }
    private void getData(View view) {

        String address = Utils.readAssert(this, "address.txt");
        AddressModel model = JsonUtil.parseJson(address, AddressModel.class);
        if (model != null) {
            AddressDtailsEntity data = model.Result;
            if (data == null) return;
//            chooseAddress.setText(data.Province + " " + data.City + " " + data.Area);
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            }
        }
        chooseAddressWheel.show(view);
    }
    String province,city, district;
    @Override
    public void onAddressChange(String province, String city, String district) {
        this.province=province;
        this.city=city;
        this.district=district;
        tv_selectcity.setText(province + " " + city + " " + district);
    }

    class Holders{
        ImageView iv_set1,iv_set2;
        TextView tv_set1name,tv_set2name,tv_numb;
        public Holders(View view){
            iv_set1=view.findViewById(R.id.iv_set1);
            iv_set2=view.findViewById(R.id.iv_set2);
            tv_set1name=view.findViewById(R.id.tv_set1name);
            tv_set2name=view.findViewById(R.id.tv_set2name);
            tv_numb=view.findViewById(R.id.tv_numb);
        }
    }

    SetChangeBean setChangeBean;
    int pos1=-1;
    int pos2=-2;
    int flag=0;
    ArrayList<Integer> al=new ArrayList<>();
    setAdapter setadapter;
    public void initsetchange(){


        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGESET, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<SetChangeBean>() {
            @Override
            public void onSuccess(final SetChangeBean bean) {
                ToastUtil.show(bean.getMsg());
                setChangeBean=bean;
                setadapter=new setAdapter();
                no_gv.setAdapter(setadapter);
            }
        });
    }

    public void chageSet(){

        pos1=al.get(0);
        pos2=al.get(1);
          flag=0;
        ParamMap.Build pb=   new ParamMap.Build();
        if(setChangeBean.getData().getMember_list().get(pos1).getSignUpId()==null){
             pb .addParams("sign_up_a_id","");
        }else {
            pb .addParams("sign_up_a_id",setChangeBean.getData().getMember_list().get(pos1).getSignUpId());
        }
        if(setChangeBean.getData().getMember_list().get(pos2).getSignUpId()==null){
            pb.addParams("sign_up_b_id","");
        }else {
            pb .addParams("sign_up_b_id",setChangeBean.getData().getMember_list().get(pos2).getSignUpId());
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGEd, pb
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("member_a_id",setChangeBean.getData().getMember_list().get(pos1).getTeam_member_id())
                .addParams("member_b_id",setChangeBean.getData().getMember_list().get(pos2).getTeam_member_id())
                .build(), new DmeycBaseSubscriber<SetChangeBean>() {
            @Override
            public void onSuccess(SetChangeBean bean) {
              ToastUtil.show(bean.getMsg());
                al.clear();
                setadapter.notifyDataSetChanged();
                freshsetchange();
            }
        });

    }

    public class setAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return setChangeBean.getData().getMember_list().size()/2;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        Holders holders;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_changeset,null);
                holders=new Holders(view);
                view.setTag(holders);
            }else {
                holders=(Holders) view.getTag();
            }

            holders.tv_numb.setText("0"+(i+1));
            holders.tv_set1name.setText(setChangeBean.getData().getMember_list().get(i*2).getUser_nickname());
            holders.tv_set2name.setText(setChangeBean.getData().getMember_list().get(i*2+1).getUser_nickname());

            holders.iv_set1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View view) {
//                                holders.tv_set1name.setBackgroundResource();
                    flag++;
//                                ToastUtil.show(i+"sss");
                    pos1=i;
                    al.add(i*2);
//                    holders.iv_set1.setBackground(PublishActivity.this.getDrawable(R.drawable.set_ku));
                    if(2==flag){
                        chageSet();
                    }
                    notifyDataSetChanged();
                }
            });
            holders.iv_set2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View view) {
//                                pos2=i;
                    flag++;
                    al.add(i*2+1);
//                                ToastUtil.show(i+"sss");
//                    holders.iv_set2.setBackground(PublishActivity.this.getDrawable(R.drawable.set_ku));
                    if(2==flag){
                        chageSet();
                    }
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }

    public void freshsetchange(){


        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGESET, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<SetChangeBean>() {
            @Override
            public void onSuccess(final SetChangeBean bean) {
                ToastUtil.show(bean.getMsg());
                setChangeBean=null;
                setChangeBean=bean;
                setadapter.notifyDataSetChanged();
//                setadapter=new setAdapter();
//                no_gv.setAdapter(setadapter);
            }
        });
    }
}
