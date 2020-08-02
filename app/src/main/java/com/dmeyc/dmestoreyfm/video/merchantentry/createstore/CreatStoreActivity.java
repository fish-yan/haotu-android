package com.dmeyc.dmestoreyfm.video.merchantentry.createstore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.merchantentry.claim.ClaimStoreBean;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;
import com.tamic.novate.Throwable;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreatStoreActivity extends BaseActivity implements OnAddressChangeListener {

    @Bind(R.id.et_teachname)
    EditText etTeachname;
    @Bind(R.id.et_teach_mobile)
    EditText etTeachMobile;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.ll_choice_city)
    LinearLayout llChoiceCity;
    @Bind(R.id.iv_add_pic)
    ImageView ivAddPic;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.ll_choice_time)
    LinearLayout llChoiceTime;
    @Bind(R.id.et_price)
    EditText etPrice;
    @Bind(R.id.btn_to_sure)
    TextView btnToSure;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.et_detailaddrss)
    EditText etDetailaddrss;

    private String imageUrl;
    private String business_token;

    private List<String> startTimeData = new ArrayList<>();
    private List<List<String>> endTimeData = new ArrayList<>();
    private OptionsPickerView timePickerView;

    private ClaimStoreBean.DataBean dataBean;

    public static void newIntent(Activity activity, ClaimStoreBean.DataBean bean, String business_token) {
        Intent intent = new Intent(activity, CreatStoreActivity.class);
        intent.putExtra("bean", bean);
        intent.putExtra("business_token", business_token);
        activity.startActivity(intent);
    }

    /**
     * 获取商户名称
     *
     * @return
     */
    private String getShopName() {
        return etTeachname.getText().toString().trim();
    }

    /**
     * 获取商户电话
     *
     * @return
     */
    private String getShopPhone() {
        return etTeachMobile.getText().toString().trim();
    }

    /**
     * 获取店铺省市
     *
     * @return
     */
    private String getShopCity() {
        return tvCity.getText().toString().trim();
    }

    /**
     * 获取店铺详细地址
     *
     * @return
     */
    private String getDetailAddress() {
        return etDetailaddrss.getText().toString().trim();
    }

    /**
     * 获取营业时间
     *
     * @return
     */
    private String getWorkTime() {
        return tvTime.getText().toString().trim();
    }


    /**
     * 获取客单价
     *
     * @return
     */
    private String getPrice() {
        return etPrice.getText().toString().trim();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.ac_creat_store_layout;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        dataBean = (ClaimStoreBean.DataBean) getIntent().getSerializableExtra("bean");
        business_token = getIntent().getStringExtra("business_token");
        if (dataBean != null) {
            tvCity.setText(dataBean.getProvince() + " " + dataBean.getCity() + " " + dataBean.getArea());
            etDetailaddrss.setText(dataBean.getAddress());
            etTeachname.setText(dataBean.getName());
            etTeachMobile.setText(dataBean.getTel());
        }

        tv_title.setText("创建新门店");
        initTimeData();
        timePickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvTime.setText(startTimeData.get(options1) + "-" + endTimeData.get(options1).get(option2));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
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


    @OnClick({R.id.ll_choice_city, R.id.ll_choice_time, R.id.btn_to_sure
            , R.id.iv_add_pic
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_choice_city:
                initWheel();
                getData(view);
                break;
            case R.id.ll_choice_time:
                timePickerView.show();
                break;
            case R.id.btn_to_sure:
                if (TextUtils.isEmpty(getShopName())) {
                    ToastUtil.show("请输入商户名");
                } else if (TextUtils.isEmpty(getShopPhone())) {
                    ToastUtil.show("请输入商户名电视");
                } else if (TextUtils.isEmpty(getShopCity())) {
                    ToastUtil.show("请选择城市");
                } else if (TextUtils.isEmpty(getDetailAddress())) {
                    ToastUtil.show("请填写详细地址");
                } else if (TextUtils.isEmpty(imageUrl)) {
                    ToastUtil.show("请上传清晰，有效的门头图");
                } else if (TextUtils.isEmpty(getWorkTime())) {
                    ToastUtil.show("请选择营业时间");
                } else if (TextUtils.isEmpty(getPrice())) {
                    ToastUtil.show("请填写客单价");
                } else {
                    addBusinessInfo();
                }
                break;
            case R.id.iv_add_pic:
                new PhotoSelectHelper(CreatStoreActivity.this, 0).setCrop(true).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), ivAddPic);
                uplodeteach(images.get(0).getPath());
            }
        }
    }

    private void addBusinessInfo() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/business/addBusinessInfo", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("images", imageUrl)
                .addParams("business_token", business_token)
                .addParams("type", SPUtils.getStringData(Constant.Config.BUSINESS_REGISTER_TYPE,"4"))
                .addParams("name", getShopName())
                .addParams("tel", getShopPhone())
                .addParams("province", province)
                .addParams("city", city)
                .addParams("area", district)
                .addParams("address", getDetailAddress())
                .addParams("time", getWorkTime())
                .addParams("price", getPrice())
                .addParams("model_id", "")
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    ToastUtil.show("商家入驻成功");
                    EventBus.getDefault().post("create_success");
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
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
        mb.addFormDataPart("isLogo", 0 + "");
        final RequestBody requestBody = mb.build();

        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL + "api/v1.0/file/uploadSingle")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.show(call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gs = new Gson();
                final ImagePathBean imap = gs.fromJson(response.body().string(), ImagePathBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageUrl = imap.getData();
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
        tvCity.setText(province + " " + city + " " + district);
        this.province = province;
        this.city = city;
        this.district = district;
    }


}
