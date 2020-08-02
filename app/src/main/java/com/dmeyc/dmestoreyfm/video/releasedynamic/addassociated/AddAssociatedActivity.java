package com.dmeyc.dmestoreyfm.video.releasedynamic.addassociated;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.dmeyc.dmestoreyfm.video.merchantentry.createstore.CreatStoreActivity;
import com.dmeyc.dmestoreyfm.video.releasedynamic.GenSignBean;
import com.dmeyc.dmestoreyfm.video.releasedynamic.ReleasedynamicActivity;
import com.dmeyc.dmestoreyfm.video.releasedynamic.associated.AssociatedBean;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class AddAssociatedActivity extends BaseActivity implements OnAddressChangeListener {

    public static void newIntent(Activity activity){
        Intent intent = new Intent(activity,AddAssociatedActivity.class);
        activity.startActivity(intent);
    }

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.ll_choice_city)
    LinearLayout ll_choice_city;

    @Bind(R.id.tv_city)
    TextView tvCity;

    @Bind(R.id.et_teachname)
    EditText et_teachname;


    @Bind(R.id.iv_add_pic)
    ImageView iv_add_pic;

    @OnClick(R.id.ll_choice_city)
    void onChoiceCitys(){
        initWheel();
        getData(ll_choice_city);
    }

    @Bind(R.id.et_detailaddrss)
    EditText etDetailaddrss;// 详细地址


    @OnClick(R.id.iv_add_pic)
    void onSelectPicClick(){
        new PhotoSelectHelper(AddAssociatedActivity.this, 0).setCrop(true).show();
    }

    @OnClick(R.id.btn_to_sure)
    void onToCommitModuleBusiness(){
        String content = et_teachname.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            ToastUtil.show("请输入商户名称");
            return;
        }
        if(TextUtils.isEmpty(province) | TextUtils.isEmpty(city) | TextUtils.isEmpty(district)){
            ToastUtil.show("请选择所在省市");
            return;
        }
        String address = etDetailaddrss.getText().toString().trim();
        if(TextUtils.isEmpty(address)){
             ToastUtil.show("请输入详细地址");
             return;
        }

        ParamMap.Build pb = new ParamMap.Build();
        pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        pb.addParams("name", content);
        pb.addParams("province", province);
        pb.addParams("city", city);
        pb.addParams("area", district);
        pb.addParams("address", address);
        if(!TextUtils.isEmpty(imageUrl)){
            pb.addParams("images", imageUrl);
        }
        RestClient.getYfmNovate(AddAssociatedActivity.this).post(Constant.API.YFM_CREATE_BUSINESS_MODULE,
             pb.build(), new DmeycBaseSubscriber<AddAssociatedBean>() {
                    @Override
                    public void onSuccess(AddAssociatedBean addAssociatedBean) {
                        // 获取签名成功，开始上传视频
                        //创建成功
                        ToastUtil.show("创建成功");
                        EventVideoBean bean = new EventVideoBean();
                        bean.setAssobusinessId(addAssociatedBean.getData().getId());
                        bean.setName(addAssociatedBean.getData().getName());
                        bean.setType(addAssociatedBean.getData().getType());
                        bean.setDistance(addAssociatedBean.getData().getDistance());
                        bean.setKey(Constant.HotUEventKeys.EVENT_KEYS_DYNAMIC_ASSOCIATED_CREATE);
                        EventBus.getDefault().post(bean);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_add_associated;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_title.setText("创建关联商户");

    }

    private ChooseAddressWheel chooseAddressWheel = null;

    private void initWheel(){
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this, images.get(0).getPath(), iv_add_pic);
                uplodeteach(images.get(0).getPath());
            }
        }
    }

    private String imageUrl;
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
}
