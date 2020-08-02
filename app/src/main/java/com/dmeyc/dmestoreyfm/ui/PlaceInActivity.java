package com.dmeyc.dmestoreyfm.ui;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;

import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.NetInter;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.HttpParameterBuilder;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PlaceInActivity  extends BaseActivity  implements OnAddressChangeListener {

    @Bind(R.id.tv_selectcity)
    TextView tv_selectcity;
    @Bind(R.id.iv_roundmage)
    RoundedImageView iv_roundmage;
    @Bind(R.id.et_backtext)
    EditText et_backtext;
    @Bind(R.id.tv_textnum)
    TextView tv_textnum;

    @Bind(R.id.tv_placename)
    TextView tv_placename;

    @Bind(R.id.tv_projecttype)
    TextView tv_projecttype;

    @Bind(R.id.et_detailplace)
    EditText et_detailplace;


    @Bind(R.id.et_aver_price)
     EditText et_aver_price;

    @Bind(R.id.et_max_persong)
    EditText et_max_persong;

    @Bind(R.id.et_are)
    EditText et_are;

    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_phone)
    EditText et_phone;
        @Bind(R.id.tv_placetime)
        EditText tv_placetime;

    private ChooseAddressWheel chooseAddressWheel = null;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_placein;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        et_backtext.requestFocus();
        et_backtext.setSelection(0);
        et_backtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_textnum.setText(et_backtext.getText().toString().length()+"/"+200);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @OnClick({R.id.tv_selectcity,R.id.iv_roundmage,R.id.btn_upload,R.id.tv_projecttype,R.id.tv_placetime})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_selectcity){
            initWheel();
            getData(view);
        }else if(viewid==R.id.iv_roundmage){
            new PhotoSelectHelper(this,0).setCrop(true).show();
        }else if(viewid==R.id.btn_upload){
           uplodPhoto();
        }else if(R.id.tv_projecttype==viewid){
            getProjectTyoe();
        }/*else if(R.id.tv_placetime==viewid){
            showDatePickDialog(DateType.TYPE_ALL);
        }*/
    }
    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(1);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
//        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        dialog.setMessageFormat("yyyy-MM-dd");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                tv_placetime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
            }
        });
        dialog.show();
    }

    @Override
    public void onAddressChange(String province, String city, String district) {
        tv_selectcity.setText(province + " " + city + " " + district);
        this.province=province;
        this.city=city;
        this.district=district;
    }
    private void initWheel() {
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setOnAddressChangeListener(this);
    }
    String province,city,district;
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
String photoicon;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
//                if(this!=null){
                    GlideUtil.loadImage(this,images.get(0).getPath(),iv_roundmage);
//                }
               photoicon=images.get(0).getPath();
//                RestClient.getNovate((Context) getActivity()).uploadImage(Constant.API.UPLOAD_HEADING, new File(images.get(0).getPath()), new DmeycBaseSubscriber<CommonBean>() {
//                    @Override
//                    public void onSuccess(CommonBean bean) {
//                        String url = (String) bean.getData();
//                        updateUserInfo("avatar",url);
//                    }
//                });
            }
        }
    }

    public void submit(String pho){
        if(TextUtils.isEmpty(pho)) {
            ToastUtil.show("请上传头像");
            return;
        }

        if(TextUtils.isEmpty(tv_placename.getText().toString().trim())) {
                    ToastUtil.show("请填写场地名称");
                    return;
                }
        if(TextUtils.isEmpty(tv_projecttype.getText().toString().trim())) {
            ToastUtil.show("请填选择项目类型");
            return;
        }
        if(TextUtils.isEmpty(tv_selectcity.getText().toString().trim())) {
            ToastUtil.show("请选择城市");
            return;
        }
        if(TextUtils.isEmpty(et_detailplace.getText().toString().trim())) {
            ToastUtil.show("请填写详细地址");
            return;
        }
        if(TextUtils.isEmpty(et_are.getText().toString().trim())) {
            ToastUtil.show("请填写场地面积");
            return;
        }

        if(TextUtils.isEmpty(et_max_persong.getText().toString().trim())) {
            ToastUtil.show("请填写最大人数");
            return;
        }
        if(TextUtils.isEmpty(tv_placetime.getText().toString().trim())) {
            ToastUtil.show("请输入营业时间");
            return;
        }

        if(TextUtils.isEmpty(et_name.getText().toString().trim())) {
            ToastUtil.show("请填写姓名");
            return;
        }

        if(TextUtils.isEmpty(et_phone.getText().toString().trim())) {
            ToastUtil.show("请填写联系方式");
            return;
        }
        if(TextUtils.isEmpty(et_aver_price.getText().toString().trim())) {
            ToastUtil.show("请填写人均消费");
            return;
        }
        if(TextUtils.isEmpty(et_backtext.getText().toString().trim())) {
            ToastUtil.show("请填写备注");
            return;
        }

        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTIN, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("venue_name", tv_placename.getText().toString().trim())
//                .addParams("project_type", 1)
                .addParams("project_type", pty.getData().get(poscode).getItemCode())
                .addParams("venue_linkman", et_name.getText().toString().trim())
                .addParams("venue_phone_no", et_phone.getText().toString().trim())
                .addParams("venue_address",et_detailplace.getText().toString().trim())
                .addParams("venue_logo", pho)
                .addParams("province", province)
                .addParams("city", city)
                .addParams("area", district)
                .addParams("maximum", et_max_persong.getText().toString().trim())
                .addParams("space_no", et_are.getText().toString().trim())
                .addParams("average_amount", et_aver_price.getText().toString().trim())
               .addParams("business_time", tv_placetime.getText().toString().trim())
                .addParams("remark", et_backtext.getText().toString())
                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
            @Override
            public void onSuccess(YFMUserBean bean) {
//                ToastUtil.show(bean.getMsg());
                startActivity(new Intent(PlaceInActivity.this,UploadeImagUrlActivity.class));
                finish();
            }
        });
    }

    public void uplodPhoto(){


if(TextUtils.isEmpty(photoicon)){
    ToastUtil.show("请输入照片");
    return;
}
        OkHttpClient client = new OkHttpClient();
        File file = new File(photoicon);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                .addFormDataPart("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addFormDataPart("isLogo",1+"")
                .build();

        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
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
                Gson gs=new Gson();
                final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GlideUtil.loadImage(PlaceInActivity.this, imap.getData(), iv_roundmage);
                        submit(imap.getData());
//                        submitApply(imap.getData());
//                        ToastUtil.show(imap.getMsg());
                    }
                });
            }
        });

    }

    ProjectTypeBean pty;
    ArrayList<String> ar=new ArrayList<>();
    private PopupMenu popupMenu;
    int poscode;
    private void getProjectTyoe() {
        ar.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key","PROJECT_TYPE")
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
//                ToastUtil.show(bean.getMsg());
                pty=bean;
                List<ProjectTypeBean.DataBean> lbean= pty.getData();
                for (int i=0;i<lbean.size();i++){
                    ar.add(lbean.get(i).getItemName());
                }
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
                popupMenu = new PopupMenu(PlaceInActivity.this,ar);
                popupMenu.showLocation(R.id.tv_projecttype);// 设置弹出菜单弹出的位置
                // 设置回调监听，获取点击事件
                popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                    @Override
                    public void onClick(PopupMenu.MENUITEM item, String str) {

                    }

                    @Override
                    public void onClick(String str,int pos) {
                        poscode=pos;
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

}
