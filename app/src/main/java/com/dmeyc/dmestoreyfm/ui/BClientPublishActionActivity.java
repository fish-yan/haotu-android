package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ChangeActionBean;
import com.dmeyc.dmestoreyfm.bean.GetActionInforByIdBean;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class BClientPublishActionActivity extends BaseActivity implements OnAddressChangeListener {
    @Bind(R.id.tv_clubname)
    TextView tv_clubname;
    @Bind(R.id.et_projectname)
    TextView et_projectname;
    @Bind(R.id.tv_categroy)
    TextView tv_categroy;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.ll_reducenumber)
    LinearLayout ll_reducenumber;

    @Bind(R.id.tv_secity)
    TextView tv_secity;
    @Bind(R.id.et_activityname)
    EditText et_activityname;
    @Bind(R.id.tv_phonenumber)
    EditText tv_phonenumber;
    @Bind(R.id.et_adressdetil)
    EditText et_adressdetil;
    @Bind(R.id.et_nomalprice)
    EditText et_nomalprice;
    @Bind(R.id.et_vistprice)
    EditText et_vistprice;
    @Bind(R.id.et_backtext)
    TextView et_backtext;
    @Bind(R.id.et_perperinnumb)
    EditText et_perperinnumb;
    @Bind(R.id.et_placename)
    EditText et_placename;
    @Bind(R.id.et_reduce)
    EditText et_reduce;
    @Bind(R.id.tv_leader)
    TextView tv_leader;
    @Bind(R.id.tv_quanname)
    TextView tv_quanname;

    @Bind(R.id.iv_womanarrow)
    ImageView iv_womanarrow;
    @Bind(R.id.tv_publishintro)
    LinearLayout tv_publishintro;
    @Bind(R.id.et_age)
    EditText et_age;
    private String type="";
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.btn_upload)
    Button btn_upload;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientpublish;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        type=getIntent().getStringExtra("type");
        if("1".equals(type)){
            tv_title.setText("修改活动");
            getActivityData();
        }else {
            tv_title.setText("发布活动");
        }
//        if(ll_reducenumber.getVisibility()==View.VISIBLE){
////            iv_womanarrow.setBackground(BClientPublishActionActivity.this.getResources().getDrawable(R.drawable.ic_arrow_upward));
////        }

    }
    @OnClick({R.id.actionproject,R.id.ll_actiontime,R.id.ll_actioncategory,R.id.ll_choiceclub,R.id.ll_learder,R.id.ll_womanreduces,R.id.tv_secity,R.id.ll_quan,R.id.btn_upload,R.id.tv_publishintro})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.actionproject){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            if(!"5".equals(projecttype)){
                ToastUtil.show("商户群才能选择");
                return;
            }
            Intent intent=  new Intent(BClientPublishActionActivity.this,BClientChoiceProjectActivity.class);//项目
             startActivityForResult(intent,333);
        }else if(viewid==R.id.ll_choiceclub){
          Intent intent=  new Intent(BClientPublishActionActivity.this,BClientClubChoiceActivity.class);//名字
               startActivityForResult(intent.putExtra("ispublish","yes"),111);
        }else if(viewid==R.id.ll_actioncategory){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            if(!"5".equals(projecttype)){
                ToastUtil.show("商户群才能选择");
                return;
            }
          Intent intent=  new Intent(BClientPublishActionActivity.this,ActionCateoryActivity.class);//类别
            startActivityForResult(intent,222);
        }else if(viewid==R.id.ll_actiontime){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            Intent intent=   new Intent(BClientPublishActionActivity.this,BClientActionTimeActivity.class);
            intent.putExtra("groupty",projecttype);
            startActivityForResult(intent,444);
        }else if(R.id.ll_learder==viewid){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            Intent intent=  new Intent(BClientPublishActionActivity.this,LeaderActivity.class);
            intent.putExtra("groupid",groupid);
            startActivityForResult(intent,666);
        }else if(R.id.ll_womanreduces==viewid){
            if(ll_reducenumber.getVisibility()==View.VISIBLE){
                ll_reducenumber.setVisibility(View.GONE);
            }else {
                ll_reducenumber.setVisibility(View.VISIBLE);
               }
//               startActivity(new Intent(BClientPublishActionActivity.this,BClientProjectActivity.class));
//            startActivityForResult();
        }else if(R.id.tv_secity==viewid){
            initWheel();
            getData(view);
        }else if(R.id.ll_quan==viewid){
            if(TextUtils.isEmpty(tv_clubname.getText().toString())){
                ToastUtil.show("请选择俱乐部");
                return;
            }
            Intent intent=  new Intent(this,BClientPromotionActivitys.class);
            intent.putExtra("groupid",groupid);
            startActivityForResult(intent,555);
        }else if(R.id.btn_upload==viewid){
            if(type.equals("1")){
//                btn_upload.setClickable(false);
                edateActivity();
            }else {
//                btn_upload.setClickable(false);
                publishActivity();
            }
        }else if(R.id.tv_publishintro==viewid){
         startActivityForResult(new Intent(BClientPublishActionActivity.this,EducationBackActivity.class)
               .putExtra("type","3").putExtra("intro",actionintro).putExtra("backimages",backimages),100);
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
    int groupid;
    int chectype;
    int category;
    String duration;
    String starttime;
    int promotid;
    int leaderid;
    String leadername;
    String projecttype;

    ArrayList<String> photolisaction;
    String actionintro;
    String calanderselect;
    String backimage;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(210==resultCode){
            groupid= data.getIntExtra("groupid",-1);
            projecttype=data.getStringExtra("projecttype");
            tv_clubname.setText(data.getStringExtra("groupname"));
            et_projectname.setText(data.getStringExtra("projectname"));


            if("1".equals(type)){

            }else {
                getActionInfro(groupid);
            }

//            if(!"5".equals(projecttype)){
//                et_projectname.setText("羽毛球");
//            }
        }else if(1234==resultCode){
            category= data.getIntExtra("type",-1);
            tv_categroy.setText(data.getStringExtra("category"));
        }else if(212==resultCode){
            int projectid= data.getIntExtra("projectid",-1);
//            int projectid= data.getStringExtra("projectname");
            et_projectname.setText(data.getStringExtra("projectname"));
        }else if(234==resultCode){
            chectype = data.getIntExtra("chectype",-1);
            duration = data.getStringExtra("duration");
            calanderselect = data.getStringExtra("calanderselect");
            starttime= data.getStringExtra("starttime");
            tv_time.setText(data.getStringExtra("starttime")+" 时长"+data.getStringExtra("duration")+"小时");
        }else if(321==resultCode){
            promotid= data.getIntExtra("promotid",-1);
            tv_quanname.setText(data.getStringExtra("promotionname"));
        }else if(221==resultCode){
            leaderid= data.getIntExtra("leaderid",-1);
            leadername=data.getStringExtra("leaderidnaem");
            tv_leader.setText(leadername);
        }else if(resultCode==666){
            backimages.clear();

            photolisaction= data.getStringArrayListExtra("photolist");
            backimages.addAll(photolisaction);
            actionintro=data.getStringExtra("backtext");
            et_backtext.setText("已填写");
        }else if(resultCode==888){
            photolisaction= data.getStringArrayListExtra("photolist");
            actionintro=data.getStringExtra("backtext");
            et_backtext.setText("已填写");
        }
    }
    String province,city, district;
    String imageslist="";
    @Override
    public void onAddressChange(String province, String city, String district) {
        this.province=province;
        this.city=city;
        this.district=district;
        tv_secity.setText(province + " " + city + " " + district);
    }
    String api;
    int activitytype=1;
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
            ToastUtil.show("请输入非会员活动费用");
            return;
        }
        if(!"4".equals(projecttype)){
            if(Double.parseDouble(et_nomalprice.getText().toString().trim())<5){
                ToastUtil.show("非会员活动费用至少5元");
                return;
            }
        }
        if(TextUtils.isEmpty(et_vistprice.getText().toString().trim())){
            ToastUtil.show("请输入会员活动费用");
            return;
        }
         if(!"4".equals(projecttype)){
            if(Double.parseDouble(et_vistprice.getText().toString().trim())<5){
                ToastUtil.show("会员活动费用至少5元");
                return;
            }
        }

        if(Double.parseDouble(et_nomalprice.getText().toString().trim())<Double.parseDouble(et_vistprice.getText().toString().trim())){
            ToastUtil.show("会员价不可以高于游客价格");
            return;
        }
        if(TextUtils.isEmpty(et_perperinnumb.getText().toString().trim())){
            ToastUtil.show("请填写活动人数");
            return;
        }
        if("1".equals(projecttype)){
            et_perperinnumb.setHint("请填写活动人数(2-36)人");
            if(Integer.parseInt(et_perperinnumb.getText().toString().trim())<2||Integer.parseInt(et_perperinnumb.getText().toString().trim())>36){
                ToastUtil.show("请填写活动人数(2-36)人");
                return;
            }
        }else if("4".equals(projecttype)){
            if(Integer.parseInt(et_perperinnumb.getText().toString().trim())<2||Integer.parseInt(et_perperinnumb.getText().toString().trim())>50){
                ToastUtil.show("请填写活动人数(2-50)人");
                return;
            }
        }

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
        if(!et_backtext.getText().toString().trim().equals("已填写")){
            ToastUtil.show("请填写活动简介");
            return;
        }
        if(TextUtils.isEmpty(tv_leader.getText().toString().trim())){
            ToastUtil.show("请选择组织者");
            return;
        }

        if(TextUtils.isEmpty(et_placename.getText().toString().trim())){
            ToastUtil.show("请输入场馆名称");
            return;
        }
        if(TextUtils.isEmpty(et_age.getText().toString().trim())){
            ToastUtil.show("请输入适合年龄段");
            return;
        }

        ParamMap.Build build = new ParamMap.Build();
            api=Constant.API.YFM_ACTIONPUBLISH;
            if(TextUtils.isEmpty(et_reduce.getText().toString().trim())){
                build.addParams("is_w_discount","0");
            }else {
               if(Double.parseDouble(et_vistprice.getText().toString().trim())-Double.parseDouble(et_reduce.getText().toString().trim())<5){
                        ToastUtil.show("活动费用不能小于5元");
                        return;
                    }
                build.addParams("is_w_discount","1");
                build.addParams("w_discount_amount",Double.parseDouble(et_reduce.getText().toString().trim()));
            }
        imageslist="";
            if(photolisaction!=null){
                for (int i=0;i<photolisaction.size();i++){
                    imageslist=imageslist+photolisaction.get(i);
                    if(i!=photolisaction.size()){
                        imageslist=imageslist+",";
                    }
                }
            }

        if(imageslist.endsWith(",")){
            imageslist=imageslist.substring(0,imageslist.length()-1);
        }

        if("5".equals(projecttype)){
            build.addParams("start_date",calanderselect);
        }else {
            build .addParams("start_date",starttime);
        }


        if("1".equals(projecttype)||"4".equals(projecttype)){
            if(2==chectype){
                build .addParams("activity_name",et_activityname.getText().toString().trim()+"(临时场)");
            }else {
                build .addParams("activity_name",et_activityname.getText().toString().trim());
            }
        }else {
            build   .addParams("activity_name",et_activityname.getText().toString().trim());
        }

        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        RestClient.getYfmNovate(this).post(api, build
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_phone_no",tv_phonenumber.getText().toString().trim())
                .addParams("activity_property", chectype+"")
                .addParams("group_id", groupid)

                .addParams("activity_type", activitytype)
                 .addParams("sport_type", category+"")
                .addParams("duration",Double.parseDouble(duration))
                .addParams("province", province)
                .addParams("city",city)
                .addParams("area", district)
                .addParams("activity_address",et_adressdetil.getText().toString().trim())
                .addParams("total_no",et_perperinnumb.getText().toString().trim())
                 .addParams("inviteRewardId",promotid)
                .addParams("m_member_amount", Double.parseDouble(et_vistprice.getText().toString().trim()))
                .addParams("m_visitor_amount",Double.parseDouble(et_nomalprice.getText().toString().trim()))
                .addParams("remark", actionintro)
                .addParams("remarkImg", imageslist)
                .addParams("suitAge", et_age.getText().toString().trim())
                .addParams("venue_name",et_placename.getText().toString().trim())
                .addParams("manager_user_id",leaderid)

//                .addParams("invite_discount_amount",district)
//                .addParams("field_no", et_placenum.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("活动发布成功");
                Intent intent=new Intent();
                setResult(555,intent);
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


    public void getActivityData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACTIVITYDETAIL, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<ChangeActionBean>() {
            @Override
            public void onSuccess(final ChangeActionBean bean) {
//                ToastUtil.show(bean.getMsg());
                tv_clubname.setText(bean.getData().getGroup_name());
                groupid=bean.getData().getGroup_id();
                activitytype=Integer.parseInt(bean.getData().getProjectType());
                et_projectname.setText(bean.getData().getProjectTypeName());
//                category=Integer.parseInt(bean.getData().getSportType());
//                if("1".equals(bean.getData().getSportType())){
//                    tv_categroy.setText("亲子");
//                }else if("2".equals(bean.getData().getSportType())){
//                    tv_categroy.setText("团建");
//                }else {
//                    tv_categroy.setText("普通");
//                }
                if("5".equals(bean.getData().getGroupType())){
                    category=Integer.parseInt(bean.getData().getSportType());
                    if("1".equals(bean.getData().getSportType())){
                        tv_categroy.setText("亲子");
                    }else if("2".equals(bean.getData().getSportType())){
                        tv_categroy.setText("团建");
                    }else {
                        tv_categroy.setText("普通");
                    }
                }
                projecttype=bean.getData().getGroupType();
                et_activityname.setText(bean.getData().getActivity_name());
                starttime=bean.getData().getStart_date();
                tv_time.setText(bean.getData().getStart_date()+" 时长"+bean.getData().getDuration()+"小时");
                duration=bean.getData().getDuration()+"";
                et_nomalprice.setText(bean.getData().getW_visitor_amount()+"");
                et_vistprice.setText(bean.getData().getW_member_amount()+"");
                if("0".equals(bean.getData().getIs_w_discount())){
                    ll_reducenumber.setVisibility(View.GONE);
                }else {
                    ll_reducenumber.setVisibility(View.VISIBLE);
                    et_reduce.setText(bean.getData().getW_visitor_amount()+"");
                }
                leadername=bean.getData().getManager_name();
                tv_leader.setText(leadername);
                leaderid=bean.getData().getManager_id();
                tv_phonenumber.setText(bean.getData().getActivity_phone_no());
                et_placename.setText(bean.getData().getVenueName());
                province=bean.getData().getProvince();city=bean.getData().getCity(); district=bean.getData().getArea();
                tv_secity.setText(bean.getData().getProvince()+"  "+bean.getData().getCity()+"  "+bean.getData().getArea());
                et_adressdetil.setText(bean.getData().getActivity_address());
                et_perperinnumb.setText(bean.getData().getTotal_no()+"");
                et_age.setText(bean.getData().getSuitAge());
                if(!TextUtils.isEmpty(bean.getData().getRemark())){
                    et_backtext.setText("已填写");
                    actionintro=bean.getData().getRemark();
                }
                backimages.clear();
                for (int i=0;i<bean.getData().getCoachRemarkImg().size();i++){
                    backimages.add(bean.getData().getCoachRemarkImg().get(i));
                }
                for (int i=0;i<bean.getData().getRemarkImg().size();i++){
                    backimages.add(bean.getData().getRemarkImg().get(i));
                }
            }});
    }

    public void edateActivity(){
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

        if(TextUtils.isEmpty(et_vistprice.getText().toString().trim())){
            ToastUtil.show("请填写游客价格");
            return;
        }
        if(Double.parseDouble(et_nomalprice.getText().toString().trim())<Double.parseDouble(et_vistprice.getText().toString().trim())){
            ToastUtil.show("会员价不可以高于游客价格");
            return;
        }
        if("1".equals(projecttype)){
            et_perperinnumb.setHint("请填写活动人数(2-36)人");
            if(Integer.parseInt(et_perperinnumb.getText().toString().trim())<2||Integer.parseInt(et_perperinnumb.getText().toString().trim())>36){
                ToastUtil.show("请填写活动人数(2-36)人");
                return;
            }
        }else if("4".equals(projecttype)){
            if(Integer.parseInt(et_perperinnumb.getText().toString().trim())<2||Integer.parseInt(et_perperinnumb.getText().toString().trim())>50){
                ToastUtil.show("请填写活动人数(2-50)人");
                return;
            }
        }
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
        if(!et_backtext.getText().toString().trim().equals("已填写")){
            ToastUtil.show("请填写活动简介");
            return;
        }
        if(TextUtils.isEmpty(tv_leader.getText().toString().trim())){
            ToastUtil.show("请选择组织者");
            return;
        }

        if(TextUtils.isEmpty(et_placename.getText().toString().trim())){
            ToastUtil.show("请输入场馆名称");
            return;
        }
        if(TextUtils.isEmpty(et_age.getText().toString().trim())){
            ToastUtil.show("请输入适合年龄段");
            return;
        }

        ParamMap.Build build = new ParamMap.Build();
        api=Constant.API.YFM_ACTIONUPDAT;
        if(TextUtils.isEmpty(et_reduce.getText().toString().trim())){
            build.addParams("is_w_discount","0");
        }else {
            if(Integer.parseInt(et_vistprice.getText().toString().trim())-Integer.parseInt(et_reduce.getText().toString().trim())<5){
                ToastUtil.show("活动费用不能小于5元");
                return;
            }

            build.addParams("is_w_discount","1");
            build.addParams("w_discount_amount",Double.parseDouble(et_reduce.getText().toString().trim()));
        }
        imageslist="";
        if(photolisaction!=null&&photolisaction.size()>0){
            for (int i=0;i<photolisaction.size();i++){
                imageslist=imageslist+photolisaction.get(i);
                if(i!=photolisaction.size()){
                    imageslist=imageslist+",";
                }
            }
        }
        if(imageslist.endsWith(",")){
            imageslist=imageslist.substring(0,imageslist.length()-1);
        }
        if("5".equals(projecttype)){
            build.addParams("start_date",calanderselect);
        }else {
            build .addParams("start_date",starttime);
        }
        if("1".equals(projecttype)||"4".equals(projecttype)){

            build .addParams("activity_name",et_activityname.getText().toString().trim()+"(临时场)");
        }else {
            build   .addParams("activity_name",et_activityname.getText().toString().trim());
        }

        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        RestClient.getYfmNovate(this).post(api, build
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_phone_no",tv_phonenumber.getText().toString().trim())
                .addParams("activity_property", chectype+"")
                  .addParams("activity_id", getIntent().getIntExtra("activityid",-1))
                .addParams("group_id", groupid)
//                .addParams("activity_name",et_activityname.getText().toString().trim())
                .addParams("activity_type", activitytype)
                .addParams("sport_type", category+"")
                .addParams("duration",Double.parseDouble(duration))
                .addParams("province", province)
                .addParams("city",city)
                .addParams("area", district)
                .addParams("activity_address",et_adressdetil.getText().toString().trim())
                .addParams("total_no",et_perperinnumb.getText().toString().trim())
                .addParams("inviteRewardId",promotid)
                .addParams("m_member_amount", Double.parseDouble(et_vistprice.getText().toString().trim()))
                .addParams("m_visitor_amount",Double.parseDouble(et_nomalprice.getText().toString().trim()))
                .addParams("remark", actionintro)
                .addParams("remarkImg", imageslist)
                .addParams("suitAge", et_age.getText().toString().trim())
                .addParams("venue_name",et_placename.getText().toString().trim())
                .addParams("manager_user_id",leaderid)

//                .addParams("invite_discount_amount",district)
//                .addParams("field_no", et_placenum.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("活动发布成功");
                btn_upload.setClickable(false);
                Intent intent=new Intent();
                setResult(555,intent);
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
private  ArrayList<String> backimages=new ArrayList<>();
    public void getActionInfro(int group_id){

            RestClient.getYfmNovate(BClientPublishActionActivity.this).post(Constant.API.YFM_MYCOMMHISTORY, new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                    .addParams("group_id", group_id+"")
                    .build(), new DmeycBaseSubscriber<GetActionInforByIdBean>() {
                @Override
                public void onSuccess(GetActionInforByIdBean bean) {
                    if(1==bean.getData().getIsThereActivity()){
                        tv_clubname.setText(bean.getData().getActivityDetail().getGroup_name());
                        groupid=bean.getData().getActivityDetail().getGroup_id();
                        activitytype=Integer.parseInt(bean.getData().getActivityDetail().getProjectType());
                        et_projectname.setText(bean.getData().getActivityDetail().getProjectTypeName());

                        if("5".equals(bean.getData().getActivityDetail().getGroupType())){
                            category=Integer.parseInt(bean.getData().getActivityDetail().getSportType());
                            if("1".equals(bean.getData().getActivityDetail().getSportType())){
                                tv_categroy.setText("亲子");
                            }else if("2".equals(bean.getData().getActivityDetail().getSportType())){
                                tv_categroy.setText("团建");
                            }else {
                                tv_categroy.setText("普通");
                            }
                        }
                        projecttype=bean.getData().getActivityDetail().getGroupType();
                        et_activityname.setText(bean.getData().getActivityDetail().getActivity_name());
                        starttime=bean.getData().getActivityDetail().getStart_date();
                        tv_time.setText(bean.getData().getActivityDetail().getStart_date()+" 时长"+bean.getData().getActivityDetail().getDuration()+"小时");
                        duration=bean.getData().getActivityDetail().getDuration()+"";
                        et_nomalprice.setText(bean.getData().getActivityDetail().getW_visitor_amount()+"");
                        et_vistprice.setText(bean.getData().getActivityDetail().getM_member_amount()+"");
                        if("0".equals(bean.getData().getActivityDetail().getIs_w_discount())){
                            ll_reducenumber.setVisibility(View.GONE);
                        }else {
                            ll_reducenumber.setVisibility(View.VISIBLE);
                            et_reduce.setText(bean.getData().getActivityDetail().getW_visitor_amount()+"");
                        }
                        leadername=bean.getData().getActivityDetail().getManager_name();
                        tv_leader.setText(leadername);
                        leaderid=bean.getData().getActivityDetail().getManager_id();
                        tv_phonenumber.setText(bean.getData().getActivityDetail().getActivity_phone_no());
                        et_placename.setText(bean.getData().getActivityDetail().getVenueName());
                        province=bean.getData().getActivityDetail().getProvince();city=bean.getData().getActivityDetail().getCity(); district=bean.getData().getActivityDetail().getArea();
                        tv_secity.setText(bean.getData().getActivityDetail().getProvince()+"  "+bean.getData().getActivityDetail().getCity()+"  "+bean.getData().getActivityDetail().getArea());
                        et_adressdetil.setText(bean.getData().getActivityDetail().getActivity_address());
                        et_perperinnumb.setText(bean.getData().getActivityDetail().getTotal_no()+"");
                        et_age.setText(bean.getData().getActivityDetail().getSuitAge());
                        if(!TextUtils.isEmpty(bean.getData().getActivityDetail().getRemark())){
                            et_backtext.setText("已填写");
                            actionintro=bean.getData().getActivityDetail().getRemark();
                        }
                        backimages.clear();
                        if(bean.getData().getActivityDetail().getCoachRemarkImg()!=null){
                            for (int i=0;i<bean.getData().getActivityDetail().getCoachRemarkImg().size();i++){
                                backimages.add(bean.getData().getActivityDetail().getCoachRemarkImg().get(i));
                            }
                        }
                    if(bean.getData().getActivityDetail().getRemarkImg()!=null){
                        for (int i=0;i<bean.getData().getActivityDetail().getRemarkImg().size();i++){
                            backimages.add(bean.getData().getActivityDetail().getRemarkImg().get(i));
                        }
                    }

                    }
                }
            });
    }
}