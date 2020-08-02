package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommInAfterBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.TeachInBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tamic.novate.Throwable;
import com.xsm.library.TObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class TeachInActivity extends BaseActivity implements OnAddressChangeListener {
    @Bind(R.id.tv_selectcity)
    TextView tv_selectcity;
    @Bind(R.id.iv_roundmage)
    RoundedImageView iv_roundmage;

    @Bind(R.id.et_backtext)
    EditText et_backtext;

    @Bind(R.id.tv_textnum)
    TextView tv_textnum;
    @Bind(R.id.ll_front)
    ImageView ll_front;
    @Bind(R.id.iv_front)
    ImageView iv_front;
    @Bind(R.id.ll_revers)
    ImageView ll_revers;
    @Bind(R.id.iv_reviers)
    ImageView iv_reviers;
    @Bind(R.id.iv_certificate)
    ImageView iv_certificate;
    @Bind(R.id.ll_certificate)
    ImageView ll_certificate;
    @Bind(R.id.et_clubname)
    EditText et_clubname;
    @Bind(R.id.tv_projecttype)
    TextView tv_projecttype;

    @Bind(R.id.et_detailaddrss)
    EditText et_detailaddrss;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_phone_num)
    EditText et_phone_num;
    @Bind(R.id.et_teachinfo)
    TextView et_teachinfo;
    @Bind(R.id.cv_clubheader)
    CircleImageView cv_clubheader;

    @Bind(R.id.btn_upload)
    Button btn_upload;
    private List<String> startTimeData = new ArrayList<>();
    private List<List<String>> endTimeData = new ArrayList<>();
    private OptionsPickerView timePickerView;

    @OnClick(R.id.ll_choice_time)
    void onChoiceTimeToClick() {
        //
        if (timePickerView != null) {
            timePickerView.show();
        }
    }

    @Bind(R.id.tv_show_time)
    TextView tv_show_time;

    @Bind(R.id.et_price)
    EditText et_price;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_teachin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        et_backtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_textnum.setText(et_backtext.getText().toString().length() + "/" + 200);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        initTimeData();
        timePickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tv_show_time.setText(startTimeData.get(options1) + "-" + endTimeData.get(options1).get(option2));
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("关闭")//取消按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setSubmitColor(Color.parseColor("#007aff"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#1a1a1a"))//取消按钮文字颜色
                .setTitleBgColor(0xFFFAFAFA)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.parseColor("#dfdfdf"))
                .build();

        timePickerView.setPicker(startTimeData, endTimeData);
    }

    int type = 0;

    @OnClick({R.id.btn_upload, R.id.tv_selectcity, R.id.iv_roundmage, R.id.ll_certificate, R.id.ll_revers, R.id.ll_front, R.id.ll_perojecttype, R.id.ll_toteachinfor, R.id.cv_clubheader})
    public void onClick(View view) {
        int viewid = view.getId();
        if (R.id.tv_selectcity == viewid) {
            initWheel();
            getData(view);
        } else if (viewid == R.id.iv_roundmage) {
            type = 1;
            new PhotoSelectHelper(this, 0).setCrop(true).show();
        } else if (R.id.btn_upload == viewid) {
            submit();
        } else if (viewid == R.id.ll_certificate) {
            type = 4;
            new PhotoSelectHelper(this, 1).setCrop(true).show();
        } else if (viewid == R.id.ll_revers) {
            type = 3;
            new PhotoSelectHelper(this, 1).setCrop(true).show();
        } else if (viewid == R.id.ll_front) {
            type = 2;
            new PhotoSelectHelper(this, 1).setCrop(true).show();
        } else if (R.id.ll_perojecttype == viewid) {
            getProjectTyoe();
        } else if (R.id.ll_toteachinfor == viewid) {
            startActivityForResult(new Intent(TeachInActivity.this, FinishTeachInfroActivity.class), 456);
        } else if (R.id.cv_clubheader == viewid) {
            type = 1;
            new PhotoSelectHelper(TeachInActivity.this, 0).setCrop(true).show();
        }
    }

    private void initTimeData() {
        startTimeData.add("00：00");
        startTimeData.add("01：00");
        startTimeData.add("02：00");
        startTimeData.add("03：00");
        startTimeData.add("04：00");
        startTimeData.add("05：00");
        startTimeData.add("06：00");
        startTimeData.add("07：00");
        startTimeData.add("08：00");
        startTimeData.add("09：00");
        for (int i = 10; i < 25; i++) {
            startTimeData.add(i + "：00");
        }
        for (int i = 0; i < startTimeData.size(); i++) {
            endTimeData.add(startTimeData);
        }
    }

    String iamgesintro = "";
    String iamgeseducation = "";
    CommInAfterBean commonBean;

    private void submit() {
        photo1 = "http://1300375154.vod2.myqcloud.com/8b1f76ffvodcq1300375154/279255dd5285890794911293778/5285890794911293779.jpg";
        if (TextUtils.isEmpty(photo1)) {
            ToastUtil.show("请选择头像");
            return;
        }
        if (TextUtils.isEmpty(tv_projecttype.getText().toString().trim())) {
            ToastUtil.show("请选择项目类型");
            return;//
        }

        if (TextUtils.isEmpty(tv_selectcity.getText().toString().trim())) {
            ToastUtil.show("请选择城市");
            return;//
        }

        if (TextUtils.isEmpty(et_detailaddrss.getText().toString().trim())) {
            ToastUtil.show("请填写详细地址");
            return;//
        }
        if("营业时间".equals(tv_show_time.getText().toString().trim())){
            ToastUtil.show("请设置您的营业时间");
            return;
        }
        if(TextUtils.isEmpty(et_price.getText().toString().trim())){
            ToastUtil.show("请输入客单价");
            return;
        }

//        if(TextUtils.isEmpty(et_backtext.getText().toString().trim())) {
//            ToastUtil.show("请填写备注");
//            return;
//        }
//        TeachInBean teachInBean=new TeachInBean();
//        teachInBean.setArea(district);
//        teachInBean.setCity(city);
//        teachInBean.setProvince(province);
//        teachInBean.setBacktext(et_backtext.getText().toString().trim());
//        teachInBean.setDetailadress(et_detailaddrss.getText().toString().trim());
//        teachInBean.setProjecttype(pty.getData().get(poscode).getItemCode());
//        teachInBean.setClubname(et_teachname.getText().toString());
//        Intent intent=  new Intent(TeachInActivity.this,TeachNextActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("teachin", teachInBean);
//        intent.putExtras(bundle);
//        startActivity(intent);
//              finish();
//        TeachInBean teachInBean=new TeachInBean();
//        startActivity(new Intent(TeachInActivity.this,TeachNextActivity.class));
//
//        for (int i = 0; i < teachInBean.getTeachintroimages().size(); i++) {
//            iamgesintro = iamgesintro + teachInBean.getTeachintroimages().get(i);
//            if (i != teachInBean.getTeachintroimages().size() - 1) {
//                iamgesintro = iamgesintro + ",";
//            }
//        }
//        for (int i = 0; i < teachInBean.getTeachimags().size(); i++) {
//            iamgeseducation = iamgeseducation + teachInBean.getTeachimags().get(i);
//            if (i != teachInBean.getTeachimags().size()) {
//                iamgeseducation = iamgeseducation + ",";
//            }
//        }
//        if (iamgeseducation.endsWith(",")) {
//            iamgeseducation = iamgeseducation.substring(0, iamgeseducation.length() - 1);
//        }
        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        LoadingUtils.showProgressDialog(TeachInActivity.this,"申请中...");
        ParamMap.Build pb = new ParamMap.Build();
        pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        pb.addParams("groupLogo", photo1);
        if(!TextUtils.isEmpty(et_clubname.getText().toString())){
            pb.addParams("group_name", et_clubname.getText().toString());
        }
        pb.addParams("project_type", pty.getData().get(poscode).getItemCode());
        pb.addParams("province", province);
        pb.addParams("city", city);
        pb.addParams("area", district);
        pb.addParams("activity_venue_address", et_detailaddrss.getText().toString().trim());
        pb.addParams("time", tv_show_time.getText().toString().trim());
        pb.addParams("price", et_price.getText().toString().trim());
        RestClient.getYfmNovate(this).post(Constant.API.YFM_TEACHIN_NEW, pb
                .build(), new DmeycBaseSubscriber<CommInAfterBean>() {
            @Override
            public void onSuccess(CommInAfterBean bean) {
                LoadingUtils.cancelProgressDialog();
                commonBean = bean;
                SPUtils.savaStringData(Constant.Config.ROLECODE, "2");
//                goShop();
//                ToastUtil.show(bean.getMsg());
//                ToastUtil.show("您已经入驻成功，切换B端发布活动");
                finish();
            }

            @Override
            public void onError(Throwable e) {
                LoadingUtils.cancelProgressDialog();
                Toast.makeText(BaseApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_upload.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_upload.setClickable(true);
            }
        });
    }

    private void uplodeteach(String filepath) {
        OkHttpClient client = new OkHttpClient();
        File file = new File(filepath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);


        MultipartBody.Builder mb = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        if (1 == type) {
            mb.addFormDataPart("isLogo", 1 + "");
        } else {
            mb.addFormDataPart("isLogo", 0 + "");
        }
        final RequestBody requestBody = mb.build();

        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL + "api/v1.0/file/uploadSingle")
                .post(requestBody)
                .build();

//                client.newCall(request).execute(new Callback<ImagePathBean>());
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                ToastUtil.show(call.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Gson gs = new Gson();
                final ImagePathBean imap = gs.fromJson(response.body().string(), ImagePathBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        GlideUtil.loadImage(TeachInActivity.this, imap.getData(), iv_roundmage);
                        if (1 == type) {
                            photo1 = imap.getData();
                        } else if (2 == type) {
                            photo2 = imap.getData();
                        } else if (3 == type) {
                            photo3 = imap.getData();
                        } else if (4 == type) {
                            photo4 = imap.getData();
                        }
                        ToastUtil.show(imap.getMsg());
                    }
                });

            }
        });


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

    String province, city, district;

    @Override
    public void onAddressChange(String province, String city, String district) {
        tv_selectcity.setText(province + " " + city + " " + district);
        this.province = province;
        this.city = city;
        this.district = district;
    }

    String photo1;
    String photo2;
    String photo3;
    String photo4;

    TeachInBean teachInBean;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK && type == 1) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), cv_clubheader);
//                photo1=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (data != null && resultCode == RESULT_OK && type == 2) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ll_front);
                iv_front.setVisibility(View.GONE);
//                photo2=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (data != null && resultCode == RESULT_OK && type == 3) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ll_revers);
                iv_reviers.setVisibility(View.GONE);
//                photo3=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (data != null && resultCode == RESULT_OK && type == 4) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ll_certificate);
                iv_certificate.setVisibility(View.GONE);
//                photo4=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (resultCode == 555) {
            teachInBean = (TeachInBean) data.getSerializableExtra("teachin");
            et_teachinfo.setText("已填写");
        }
    }

    ProjectTypeBean pty;
    ArrayList<String> ar = new ArrayList<>();
    private PopupMenu popupMenu;
    int poscode;

    private void getProjectTyoe() {
        ar.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
//                .addParams("key","PROJECT_TYPE_3")
                .addParams("key", "PROJECT_TYPE_COACH")
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
//                ToastUtil.show(bean.getMsg());
                pty = bean;
                List<ProjectTypeBean.DataBean> lbean = pty.getData();
                for (int i = 0; i < lbean.size(); i++) {
                    ar.add(lbean.get(i).getItemName());
                }
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
                popupMenu = new PopupMenu(TeachInActivity.this, ar);
                popupMenu.showComminLocation(R.id.tv_projecttype);// 设置弹出菜单弹出的位置
//                popupMenu.showLocation(R.id.tv_projecttype);// 设置弹出菜单弹出的位置
                // 设置回调监听，获取点击事件
                popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                    @Override
                    public void onClick(PopupMenu.MENUITEM item, String str) {
                    }

                    @Override
                    public void onClick(String str, int pos) {
                        poscode = pos;
                        tv_projecttype.setText(str);
                        popupMenu.dismiss();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo, tv_changeident;

    public void goShop() {
        dialog = new Dialog(TeachInActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_commin);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo = dialog.findViewById(R.id.tv_toinfo);
//        lv_shop = dialog.findViewById(R.id.lv_shop);
        tv_changeident = dialog.findViewById(R.id.tv_changeident);
        dialog.show();
        tv_changeident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                SPUtils.savaStringData(Constant.Config.IDENITY, "0");
                startActivity(new Intent(TeachInActivity.this, BMainActivity.class));
                finish();
            }
        });

        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(TeachInActivity.this, ChartInforActivity.class).putExtra("group_id", (int) commonBean.getData()));
                finish();
            }
        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//
//
//            }
//        });

    }
}
