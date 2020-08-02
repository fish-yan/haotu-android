package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PKPublicEditeBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;


public class BClientPublishChalegeActivity extends BaseActivity implements View.OnClickListener,OnAddressChangeListener {
    @Bind(R.id.tv_clubname)
    TextView tv_clubname;
    @Bind(R.id.et_projectname)
    TextView et_projectname;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_leader)
    TextView tv_leader;
    @Bind(R.id.tv_secity)
    TextView tv_secity;
    @Bind(R.id.tv_equname)
    TextView tv_equname;
    @Bind(R.id.et_machrule)
    TextView et_machrule;

    @Bind(R.id.ll_womanreduces)
    LinearLayout ll_womanreduces;
    @Bind(R.id.ll_reducenumber)
    LinearLayout ll_reducenumber;
    @Bind(R.id.ll_machrule)
    LinearLayout ll_machrule;
    @Bind(R.id.ll_equeinfor)
    LinearLayout ll_equeinfor;
    @Bind(R.id.btn_upload)
    Button btn_upload;

    @Bind(R.id.et_activityname)
    EditText et_activityname;
    @Bind(R.id.et_nomalprice)
    EditText et_nomalprice;
    @Bind(R.id.et_vistprice)
    EditText et_vistprice;
    @Bind(R.id.et_adressdetil)
    EditText et_adressdetil;
    @Bind(R.id.tv_phonenumber)
    EditText tv_phonenumber;
    @Bind(R.id.et_placename)
    EditText et_placename;

    @Bind(R.id.et_reduce)
    EditText et_reduce;
    @Bind(R.id.et_maxpower)
    EditText et_maxpower;
    @Bind(R.id.et_mixpower)
    EditText et_mixpower;
    @Bind(R.id.et_machgrouup)
    EditText et_machgrouup;
    @Bind(R.id.ll_learder)
    LinearLayout ll_learder;
    private LinearLayout actionproject,ll_choiceclub,ll_actiontime;
    private String equename,equurl;
@Bind(R.id.tv_title)
TextView tv_title;

    private String type;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_chalengepublish;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        type= getIntent().getStringExtra("type");
        actionproject=(LinearLayout) mRootView.findViewById(R.id.actionproject);
        actionproject.setOnClickListener(this);
        ll_choiceclub=(LinearLayout) mRootView.findViewById(R.id.ll_choiceclub);
        ll_choiceclub.setOnClickListener(this);
        ll_actiontime=(LinearLayout) mRootView.findViewById(R.id.ll_actiontime);
        ll_actiontime.setOnClickListener(this);
        ll_womanreduces.setOnClickListener(this);
        tv_secity.setOnClickListener(this);
        ll_machrule.setOnClickListener(this);
        ll_equeinfor.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
        ll_learder.setOnClickListener(this);
        if("1".equals(type)){
            getPKData();
            tv_title.setText("修改PK活动");
        }

    }
//  @OnClick({R.id.actionproject,R.id.ll_choiceclub,R.id.ll_actiontime,R.id.ll_actiontime})
    @Override
   public   void onClick(View view){
        int viewid=view.getId();
        if(R.id.actionproject==viewid){
//            startActivity(new Intent(BClientPublishChalegeActivity.this,BClientChoiceProjectActivity.class));
        }else if(R.id.ll_choiceclub==viewid){
            Intent intent=  new Intent(BClientPublishChalegeActivity.this,BClientClubChalageActivity.class);//名字
            startActivityForResult(intent,111);
//            startActivity(new Intent(BClientPublishChalegeActivity.this,BClientClubChoiceActivity.class));
        }else if(R.id.ll_actiontime==viewid){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            Intent intent=   new Intent(BClientPublishChalegeActivity.this,BClientActionTimeActivity.class);
            intent.putExtra("actiontype","chanlange");
            startActivityForResult(intent,444);
        }else if(R.id.ll_womanreduces==viewid){
            if(ll_reducenumber.getVisibility()==View.VISIBLE){
                ll_reducenumber.setVisibility(View.GONE);
            }else {
                ll_reducenumber.setVisibility(View.VISIBLE);
            }
        }else if(R.id.tv_secity==viewid){
            initWheel();
            getData(view);
        }else if(R.id.ll_machrule==viewid){
            startActivityForResult(new Intent(BClientPublishChalegeActivity.this,MatchRuleEditActivity.class),800);
        }else if(R.id.ll_equeinfor==viewid){
            startActivityForResult(new Intent(BClientPublishChalegeActivity.this,EquInfromationActivity.class),900);

        }else if(R.id.btn_upload==viewid){
            if("1".equals(type)){
                changeactin();
            }else {
                publishActivity();
            }

        }else if(R.id.ll_learder==viewid){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            Intent intent=  new Intent(BClientPublishChalegeActivity.this,LeaderActivity.class);
            intent.putExtra("groupid",groupid);
            startActivityForResult(intent,666);
        }
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
        tv_secity.setText(province + " " + city + " " + district);
    }
    int groupid;
    int chectype;
    int category;
    String duration;
    String starttime;
    int promotid;
    int leaderid;
    String leadername;
    String projecttype;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(210==resultCode){
            groupid= data.getIntExtra("groupid",-1);
            projecttype=data.getStringExtra("projecttype");
            tv_clubname.setText(data.getStringExtra("groupname"));
            if(!"5".equals(projecttype)){
                projecttype="1";
                et_projectname.setText("羽毛球");
            }
        }else if(1234==resultCode){
            category= data.getIntExtra("type",-1);
//            tv_categroy.setText(data.getStringExtra("category"));
        }else if(212==resultCode){
            int projectid= data.getIntExtra("projectid",-1);
//            int projectid= data.getStringExtra("projectname");
            et_projectname.setText(data.getStringExtra("projectname"));
        }else if(234==resultCode){
            chectype = data.getIntExtra("chectype",-1);
            duration = data.getStringExtra("duration");
            starttime= data.getStringExtra("starttime");
            tv_time.setText(data.getStringExtra("starttime")+" 时长"+data.getStringExtra("duration")+"小时");
        }else if(221==resultCode){
            leaderid= data.getIntExtra("leaderid",-1);
            leadername=data.getStringExtra("leaderidnaem");
            tv_leader.setText(leadername);
        }else if(999==resultCode){
            tv_equname.setText(data.getStringExtra("eqname"));
            equurl=data.getStringExtra("equrl");
        }else if(888==resultCode){
            et_machrule.setText(data.getStringExtra("machrule"));
        }else if(221==resultCode){
            leaderid= data.getIntExtra("leaderid",-1);
            leadername=data.getStringExtra("leaderidnaem");
            tv_leader.setText(leadername);
        }
    }
    String api;

    private void publishActivity() {

        if(TextUtils.isEmpty(et_activityname.getText().toString().trim())){
            ToastUtil.show("请填写活动主题");
            return;
        }

        if(TextUtils.isEmpty(tv_time.getText().toString().trim())){
            ToastUtil.show("请选择活动时间");
            return;
        }
        if(TextUtils.isEmpty(duration)){
            ToastUtil.show("请填写活动时长");
            return;
        }
        if(TextUtils.isEmpty(et_nomalprice.getText().toString().trim())){
            ToastUtil.show("请填写会员价格");
            return;
        }

//        if(TextUtils.isEmpty(et_vistprice.getText().toString().trim())){
//            ToastUtil.show("请填写非会员价格");
//            return;
//        }
//        if(Double.parseDouble(et_nomalprice.getText().toString().trim())<Double.parseDouble(et_vistprice.getText().toString().trim())){
//            ToastUtil.show("会员价不可以高于游客价格");
//            return;
//        }
//        if(TextUtils.isEmpty(et_perperinnumb.getText().toString().trim())){
//            ToastUtil.show("请填写活动人数");
//            return;
//        }

        if(TextUtils.isEmpty(tv_secity.getText().toString().trim())){
            ToastUtil.show("请选择城市");
            return;
        }
        if(TextUtils.isEmpty(et_adressdetil.getText().toString().trim())){
            ToastUtil.show("请填写详细地址");
            return;
        }
//        if(TextUtils.isEmpty(et_placenum.getText().toString().trim())){
//            ToastUtil.show("请填写场地编号");
//            return;
//        }
        if(TextUtils.isEmpty(tv_phonenumber.getText().toString().trim())){
            ToastUtil.show("请填写手机号");
            return;
        }
//        if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
//            ToastUtil.show("请填写活动备注");
//            return;
//        }
        if(TextUtils.isEmpty(tv_leader.getText().toString().trim())){
            ToastUtil.show("请选择组织者");
            return;
        }

        if(TextUtils.isEmpty(et_placename.getText().toString().trim())){
            ToastUtil.show("请输入场馆名称");
            return;
        }
        if(TextUtils.isEmpty(et_mixpower.getText().toString().trim())){
            ToastUtil.show("请输入最小战斗力");
            return;
        }
        if(TextUtils.isEmpty(et_maxpower.getText().toString().trim())){
            ToastUtil.show("请输入最大战斗力");
            return;
        }
        if(TextUtils.isEmpty(et_machgrouup.getText().toString().trim())){
            ToastUtil.show("请输入赛组");
            return;
        }
        if("1".equals(projecttype)){
            if(Integer.parseInt(et_machgrouup.getText().toString().trim())<1||Integer.parseInt(et_machgrouup.getText().toString().trim())>15){
                ToastUtil.show("赛组数1-15");
                return;
            }
        }

        if(TextUtils.isEmpty(et_machrule.getText().toString().trim())){
            ToastUtil.show("请填写比赛规则");
            return;
        }
        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        ParamMap.Build build = new ParamMap.Build();
        api=Constant.API.YFM_PKPUBLISH;
        if(TextUtils.isEmpty(et_reduce.getText().toString().trim())){
            build.addParams("is_w_discount","0");
        }else {
            build.addParams("is_w_discount","1");
            build.addParams("w_discount_amount",Double.parseDouble(et_reduce.getText().toString().trim()));
        }
        RestClient.getYfmNovate(this).post(api, build
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_phone_no",tv_phonenumber.getText().toString().trim())
                .addParams("activity_property", chectype+"")
                .addParams("group_id", groupid)
                .addParams("activity_name",et_activityname.getText().toString().trim())
                .addParams("activity_type", 1)
                .addParams("sport_type", category+"")
                .addParams("start_date",starttime)
                .addParams("duration",Double.parseDouble(duration))
                .addParams("province", province)
                .addParams("city",city)
                .addParams("area", district)
                .addParams("activity_address",et_adressdetil.getText().toString().trim())
//                .addParams("total_no",et_perperinnumb.getText().toString().trim())
                .addParams("inviteRewardId",promotid)
//                .addParams("m_member_amount", Double.parseDouble(et_nomalprice.getText().toString().trim()))
                .addParams("m_visitor_amount",Double.parseDouble(et_nomalprice.getText().toString().trim()))
                .addParams("remark", et_machrule.getText().toString().trim())
                .addParams("venue_name",et_placename.getText().toString().trim())
                .addParams("manager_user_id",leaderid)
                        .addParams("group_num",Integer.parseInt(et_machgrouup.getText().toString().trim()))
                        .addParams("brand_name",tv_equname.getText().toString().trim())
                        .addParams("brand_ball_url",equurl)
                        .addParams("min_battle",Integer.parseInt(et_mixpower.getText().toString().trim()))
                        .addParams("max_battle",Integer.parseInt(et_maxpower.getText().toString().trim()))

//                .addParams("invite_discount_amount",district)
//                .addParams("field_no", et_placenum.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("群PK发布成功");
                finish();
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(BaseApp.getContext(),  e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_upload.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_upload.setClickable(true);
            }
        });

    }

    PKPublicEditeBean pkPublicEditeBean;
    public void getPKData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PKCGET, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("activityId",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<PKPublicEditeBean>() {
            @Override
            public void onSuccess(PKPublicEditeBean bean) {
                pkPublicEditeBean=bean;
                tv_clubname.setText(bean.getData().getActivity_name());
                et_projectname.setText(bean.getData().getActivity_type_name());
                et_machgrouup.setText(bean.getData().getGroup_num()+"");
                et_activityname.setText(bean.getData().getGroup_name());
                tv_time.setText(bean.getData().getStart_date());
                et_nomalprice.setText(bean.getData().getM_visitor_amount()+"");
                tv_leader.setText(bean.getData().getManager_user_name());
                tv_secity.setText(bean.getData().getProvince()+"-"+bean.getData().getCity()+"-"+bean.getData().getArea());
                province=bean.getData().getProvince();
                city=bean.getData().getCity();
                district=bean.getData().getArea();
                et_adressdetil.setText(bean.getData().getActivity_address());
                tv_phonenumber.setText(bean.getData().getActivity_phone_no());
                et_placename.setText(bean.getData().getVenue_name());
                tv_equname.setText(bean.getData().getBrand_name());
                et_machrule.setText(bean.getData().getRemark());
                et_maxpower.setText(bean.getData().getMax_battle()+"");
                et_mixpower.setText(bean.getData().getMin_battle()+"");
                leaderid=bean.getData().getManager_user_id();
                groupid=bean.getData().getGroup_id();
                projecttype=bean.getData().getActivity_type();
                equurl=bean.getData().getBrand_ball_url();
//             ToastUtil.show(bean.getMsg());
            }
        });
    }


    public void changeactin(){

        if(TextUtils.isEmpty(et_activityname.getText().toString().trim())){
            ToastUtil.show("请填写活动主题");
            return;
        }

        if(TextUtils.isEmpty(tv_time.getText().toString().trim())){
            ToastUtil.show("请选择活动时间");
            return;
        }
        if(TextUtils.isEmpty(duration)){
            ToastUtil.show("请填写活动时长");
            return;
        }
        if(TextUtils.isEmpty(et_nomalprice.getText().toString().trim())){
            ToastUtil.show("请填写活动费用");
            return;
        }
        if(!"4".equals(projecttype)){
            if(Integer.parseInt(et_nomalprice.getText().toString().trim())<5){
                ToastUtil.show("活动费用至少5元");
                return;
            }
        }
//        if(TextUtils.isEmpty(et_vistprice.getText().toString().trim())){
//            ToastUtil.show("请填写非会员价格");
//            return;
//        }
//        if(Double.parseDouble(et_nomalprice.getText().toString().trim())<Double.parseDouble(et_vistprice.getText().toString().trim())){
//            ToastUtil.show("会员价不可以高于游客价格");
//            return;
//        }
//        if(TextUtils.isEmpty(et_perperinnumb.getText().toString().trim())){
//            ToastUtil.show("请填写活动人数");
//            return;
//        }

        if(TextUtils.isEmpty(tv_secity.getText().toString().trim())){
            ToastUtil.show("请选择城市");
            return;
        }
        if(TextUtils.isEmpty(et_adressdetil.getText().toString().trim())){
            ToastUtil.show("请填写详细地址");
            return;
        }
//        if(TextUtils.isEmpty(et_placenum.getText().toString().trim())){
//            ToastUtil.show("请填写场地编号");
//            return;
//        }
        if(TextUtils.isEmpty(tv_phonenumber.getText().toString().trim())){
            ToastUtil.show("请填写手机号");
            return;
        }
//        if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
//            ToastUtil.show("请填写活动备注");
//            return;
//        }
        if(TextUtils.isEmpty(tv_leader.getText().toString().trim())){
            ToastUtil.show("请选择组织者");
            return;
        }

        if(TextUtils.isEmpty(et_placename.getText().toString().trim())){
            ToastUtil.show("请输入场馆名称");
            return;
        }
        if(TextUtils.isEmpty(et_mixpower.getText().toString().trim())){
            ToastUtil.show("请输入最小战斗力");
            return;
        }
        if(TextUtils.isEmpty(et_maxpower.getText().toString().trim())){
            ToastUtil.show("请输入最大战斗力");
            return;
        }
        if(TextUtils.isEmpty(et_machgrouup.getText().toString().trim())){
            ToastUtil.show("请输入赛组");
            return;
        }
        if(TextUtils.isEmpty(et_machrule.getText().toString().trim())){
            ToastUtil.show("请输入比赛规则");
            return;
        }

        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);

//        ParamMap.Build build = ;
//        api=;
//        if(TextUtils.isEmpty(et_reduce.getText().toString().trim())){
//            build.addParams("is_w_discount","0");
//        }else {
//            build.addParams("is_w_discount","1");
//            build.addParams("w_discount_amount",Double.parseDouble(et_reduce.getText().toString().trim()));
//        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGEPKPUBLISH, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_phone_no",tv_phonenumber.getText().toString().trim())
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .addParams("activity_property", chectype+"")
                .addParams("group_id", groupid)
                .addParams("activity_name",et_activityname.getText().toString().trim())
                .addParams("activity_type", 1)
                .addParams("sport_type", category+"")
                .addParams("start_date",starttime)
                .addParams("duration",Double.parseDouble(duration))
                .addParams("province", province)
                .addParams("city",city)
                .addParams("area", district)
                .addParams("activity_address",et_adressdetil.getText().toString().trim())
//                .addParams("total_no",et_perperinnumb.getText().toString().trim())
                .addParams("inviteRewardId",promotid)
//                .addParams("m_member_amount", Double.parseDouble(et_nomalprice.getText().toString().trim()))
                .addParams("m_visitor_amount",Double.parseDouble(et_nomalprice.getText().toString().trim()))
                .addParams("remark", et_machrule.getText().toString().trim())
                .addParams("venue_name",et_placename.getText().toString().trim())
                .addParams("manager_user_id",leaderid)
                .addParams("group_num",Integer.parseInt(et_machgrouup.getText().toString().trim()))
                .addParams("brand_name",tv_equname.getText().toString().trim())
                .addParams("brand_ball_url",equurl)
                .addParams("min_battle",Integer.parseInt(et_mixpower.getText().toString().trim()))
                .addParams("max_battle",Integer.parseInt(et_maxpower.getText().toString().trim()))
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
//                ToastUtil.show(bean.getMsg());
                finish();
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(BaseApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_upload.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_upload.setClickable(true);
            }
        });



    }
}
