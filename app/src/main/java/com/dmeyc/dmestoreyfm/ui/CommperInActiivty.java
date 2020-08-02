package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
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

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommInAfterBean;
import com.dmeyc.dmestoreyfm.bean.CommperInBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
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

public class CommperInActiivty extends BaseActivity implements OnAddressChangeListener {
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
    //    @Bind(R.id.iv_front)
//    ImageView iv_front;
    @Bind(R.id.ll_revers)
    ImageView ll_revers;
    //    @Bind(R.id.iv_reviers)
//    ImageView iv_reviers;
//    @Bind(R.id.iv_certificate)
//    ImageView iv_certificate;
    @Bind(R.id.ll_certificate)
    ImageView ll_certificate;
    @Bind(R.id.et_teachname)
    EditText et_teachname;
    @Bind(R.id.tv_projecttype)
    TextView tv_projecttype;

    @Bind(R.id.et_detailaddrss)
    EditText et_detailaddrss;
    @Bind(R.id.tv_sporttype)
    TextView tv_sporttype;
    @Bind(R.id.et_phone_num)
    TextView et_phone_num;
    @Bind(R.id.ll_busintro)
    LinearLayout ll_busintro;

    CircleImageView cv_personheader;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.tv_introbus)
    TextView tv_introbus;
    @Bind(R.id.btn_upload)
    Button btn_upload;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commperin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        cv_personheader = mRootView.findViewById(R.id.cv_cirheader);
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
    }

    int type = 0;

    @OnClick({R.id.btn_upload, R.id.tv_selectcity, R.id.iv_roundmage, R.id.ll_certificate, R.id.ll_revers, R.id.ll_front, R.id.tv_projecttype, R.id.btn_next, R.id.ll_busintro, R.id.cv_cirheader})
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
        } else if (R.id.tv_projecttype == viewid) {
            getProjectTyoe();
        } else if (R.id.btn_next == viewid) {
            submit();
        } else if (R.id.ll_busintro == viewid) {
            Intent intent = new Intent(CommperInActiivty.this, BussinIntroActivity.class);
            startActivityForResult(intent, 321);
        }/*else if(R.id.tv_sporttype==viewid){
            getSportType();
        }*/ else if (R.id.cv_cirheader == viewid) {
            type = 1;
            new PhotoSelectHelper(CommperInActiivty.this, 0).setCrop(true).show();
        }
    }

    CommperInBean commperbean;

    private void submit() {


        if (TextUtils.isEmpty(photo1)) {
            ToastUtil.show("请选择商户logo");
            return;
        }
//        if(TextUtils.isEmpty(photo2)) {
//            ToastUtil.show("请填选择身份证正面照片");
//            return;
//        }
//        if(TextUtils.isEmpty(photo3)) {
//            ToastUtil.show("请选择身份证反面照片");
//            return;
//        }

//        if(TextUtils.isEmpty(tv_projecttype.getText().toString().trim())) {
//            ToastUtil.show("请选择项目类型");
//            return;
//        }

        if (TextUtils.isEmpty(et_name.getText().toString())) {
            ToastUtil.show("请填写联系姓名");//
            return;
        }
        if (TextUtils.isEmpty(et_teachname.getText().toString())) {
            ToastUtil.show("请填写商户名称");//
            return;
        }
        if (TextUtils.isEmpty(tv_selectcity.getText().toString().trim())) {
            ToastUtil.show("请选择城市");
            return;//
        }
        if (TextUtils.isEmpty(et_phone_num.getText().toString().trim())) {
            ToastUtil.show("请输入联系方式");
            return;//
        }
        if (TextUtils.isEmpty(et_detailaddrss.getText().toString().trim())) {
            ToastUtil.show("请输入具体地址");
            return;//
        }
        if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
            ToastUtil.show("请输入联系姓名");
            return;//
        }
        if (TextUtils.isEmpty(tv_introbus.getText().toString().trim())) {
            ToastUtil.show("请填写商户简介");
            return;
        }
        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        commperbean = new CommperInBean();
        commperbean.setCoppername(et_teachname.getText().toString());
        commperbean.setCityname(tv_selectcity.getText().toString().trim());
        commperbean.setPhonenumber(et_phone_num.getText().toString().trim());
        commperbean.setDetailadress(et_detailaddrss.getText().toString().trim());
        commperbean.setBacktext(tv_introbus.getText().toString().trim());
        commperbean.setProvince(province);
        commperbean.setCity(city);
        commperbean.setArea(district);
        submitAll();
//      Intent intent=  new Intent(CommperInActiivty.this,CommperPublishNextActivity.class);
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("commperbean", commperbean);
//        intent.putExtras(bundle);
//        startActivity(intent);
//        finish();


//
//        RestClient.getYfmNovate(this).post(Constant.API.YFM_BUSINESS, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("group_logo",photo1)
//                .addParams("owner_sfz_zm",photo2)
//                .addParams("owner_sfz_fm",photo3)
//                .addParams("group_name",et_teachname.getText().toString())
////                .addParams("project_type", 1)
//                .addParams("project_type", pty.getData().get(poscode).getItemCode())
////                .addParams("sport_type", pty.getData().get(poscodesport).getItemCode())
//
//                .addParams("province",province)
//                .addParams("city",city)
//                .addParams("area", district)
////                .addParams("address",et_detailaddrss.getText().toString().trim())
//                .addParams("remark",et_backtext.getText().toString().trim())
//                .addParams("business_license",photo4)
//                .build(), new DmeycBaseSubscriber<CommonBean>() {
//            @Override
//            public void onSuccess(CommonBean bean) {
//                ToastUtil.show(bean.getMsg());
//                finish();
//            }
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
    }

    private void uplodeteach(String filepath) {
        OkHttpClient client = new OkHttpClient();
        File file = new File(filepath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);


        MultipartBody.Builder mb = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        if (1 == type) {
            mb.addFormDataPart("isLogo", 1 + "");
        } else {
            mb.addFormDataPart("isLogo", 0 + "");
        }
        final RequestBody requestBody = mb.build();
//                .build();

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
    ArrayList<String> photolist;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK && type == 1) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), cv_personheader);
//                photo1=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (data != null && resultCode == RESULT_OK && type == 2) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ll_front);
//                iv_front.setVisibility(View.GONE);
//                photo2=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (data != null && resultCode == RESULT_OK && type == 3) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ll_revers);
//                iv_reviers.setVisibility(View.GONE);
//                photo3=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (data != null && resultCode == RESULT_OK && type == 4) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ll_certificate);
//                iv_certificate.setVisibility(View.GONE);
//                photo4=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if (resultCode == 999) {
            photolist = data.getStringArrayListExtra("photolist");
            tv_introbus.setText(data.getStringExtra("backtext"));
        }
    }

    ProjectTypeBean pty;
    ArrayList<String> ar = new ArrayList<>();
    private PopupMenu popupMenu;
    int poscode;

    private void getProjectTyoe() {
        ar.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
//                .addParams("key","PROJECT_TYPE_5")
                .addParams("key", "PROJECT_TYPE_COMMERCIAL")
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
                popupMenu = new PopupMenu(CommperInActiivty.this, ar);
                popupMenu.showLocation(R.id.tv_projecttype);// 设置弹出菜单弹出的位置
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

    ProjectTypeBean ptysporttype;
    ArrayList<String> ars = new ArrayList<>();
    private PopupMenu popupMenusport;
    int poscodesport;

    private void getSportType() {
        ars.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key", "MERCHANT_SPORT")
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
//                ToastUtil.show(bean.getMsg());
                ptysporttype = bean;
                List<ProjectTypeBean.DataBean> lbean = ptysporttype.getData();
                for (int i = 0; i < lbean.size(); i++) {
                    ars.add(lbean.get(i).getItemName());
                }
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
                popupMenusport = new PopupMenu(CommperInActiivty.this, ars);
                popupMenusport.showLocation(R.id.tv_sporttype);// 设置弹出菜单弹出的位置
                // 设置回调监听，获取点击事件
                popupMenusport.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                    @Override
                    public void onClick(PopupMenu.MENUITEM item, String str) {

                    }

                    @Override
                    public void onClick(String str, int pos) {
                        poscode = pos;
                        tv_sporttype.setText(str);
                        popupMenusport.dismiss();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    String iamges = "";
    CommInAfterBean commonBean;

    public void submitAll() {
//        if(TextUtils.isEmpty(photo3)) {
//            ToastUtil.show("请填选择身份证正面照片");
//            return;
//        }
//        if(TextUtils.isEmpty(photo4)) {
//            ToastUtil.show("请选择身份证反面照片");
//            return;
//        }
//        if(TextUtils.isEmpty(photo2)) {
//            ToastUtil.show("请上传营业执照");
//            return;
//        }
        for (int i = 0; i < photolist.size(); i++) {
            iamges = iamges + photolist.get(i);
            if (i != photolist.size() - 1) {
                iamges = iamges + ",";
            }
        }

        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BUSINESS, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("owner_sfz_zm",photo2)
//                .addParams("owner_sfz_fm",photo3)
                .addParams("group_name", commperbean.getCoppername())
                .addParams("remarkImg", iamges)
                .addParams("telphone", commperbean.getPhonenumber())
                .addParams("groupLogo", photo1)
                .addParams("name", et_name.getText().toString().trim())
                .addParams("province", commperbean.getProvince())
                .addParams("city", commperbean.getCity())
                .addParams("area", commperbean.getArea())
                .addParams("activity_venue_address", commperbean.getDetailadress())
                .addParams("remark", commperbean.getBacktext())
//                .addParams("business_license",photo4)
                .build(), new DmeycBaseSubscriber<CommInAfterBean>() {
            @Override
            public void onSuccess(CommInAfterBean bean) {
                commonBean = bean;
                SPUtils.savaStringData(Constant.Config.ROLECODE, "2");
                goShop();
//                ToastUtil.show(bean.getMsg());
//                ToastUtil.show("商户驻成功,您可切换身份来发布您的活动");
//                finish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(BaseApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_upload.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_upload.setClickable(true);
            }
        });

    }


    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo, tv_changeident;

    public void goShop() {
        dialog = new Dialog(CommperInActiivty.this, R.style.MyDialog);
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
                startActivity(new Intent(CommperInActiivty.this, BMainActivity.class));
                finish();
            }
        });

        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(CommperInActiivty.this, ChartInforActivity.class).putExtra("group_id", (int) commonBean.getData()));
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
