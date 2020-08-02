package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.TailorListBean;
import com.dmeyc.dmestoreyfm.bean.WeightHeightBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.zkk.view.rulerview.RulerView;

import butterknife.Bind;
import butterknife.OnClick;

public class WeightHeightActivity extends BaseActivity {

    @Bind(R.id.ruler_height)
    RulerView rulerHeight;
    @Bind(R.id.ruler_weight)
    RulerView rulerWeight;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.tv_height)
    TextView tvHeight;

    private float mDefaultHeight = 165f; //默认的身高
    private float mDefaultWeight = 55f;     //默认的体重
    private String timeStamp;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_weight_height;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("量身");
        rulerHeight.setValue(mDefaultHeight, 80, 250, 1);
        rulerWeight.setValue(mDefaultWeight, 20, 200, 0.5f);

        rulerHeight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                mDefaultHeight = value;
                tvHeight.setText(String.valueOf(value));
            }
        });
        rulerWeight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                mDefaultWeight = value;
                tvWeight.setText(String.valueOf(value));
            }
        });
    }

    @OnClick({R.id.tv_submit})
    public void onClick(View view) {
//        if(!Util.checkLoginStatus(this))
//            return;
//        File frontFile = new File(SPUtils.getStringData(Constant.Config.FRONT_PIC_PATH));
//        RequestBody frontBody = RequestBody.create(MediaType.parse("image/png"), frontFile);
//        File sideFile = new File(SPUtils.getStringData(Constant.Config.SIDE_PIC_PATH));
//        RequestBody sideBody = RequestBody.create(MediaType.parse("image/png"), sideFile);
//
//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("front\"; filename=\"" + frontFile.getPath(), frontBody);
//        map.put("side\"; filename=\""+ sideFile.getPath(), sideBody);
//
//        map.put("weight",RequestBody.create(null, String.valueOf(mDefaultWeight)));
//        map.put("height",RequestBody.create(null, String.valueOf(mDefaultHeight)));
//
//        timeStamp = String.valueOf(System.currentTimeMillis());
//        map.put("timeStamp",RequestBody.create(null, timeStamp));
//
//        String mobile = SPUtils.getStringData(Constant.Config.MOBILE);
//        map.put("mobile", RequestBody.create(null, mobile));
//        map.put("string", RequestBody.create(null, Util.MD5(timeStamp + Util.MD5(mobile + Constant.Config.MD5_KEY + String.valueOf(mDefaultHeight) + String.valueOf(mDefaultWeight) ))));
//        map.put("sex",RequestBody.create(null, String.valueOf(getIntent().getIntExtra(Constant.Config.GENDER, Constant.Config.GENDER_MALE))));
//
////        RestClient.getNovate(this,Constant.API.TBASE_URL).RxUploadWithBodyMaps()
//
//        RestClient.getNovate(this, Constant.API.TBASE_URL).uploadFlies(Constant.API.GET_MEASURE_INFO, map, new DmeycBaseSubscriber<MeasureBean>() {
//            @Override
//            public void onSuccess(MeasureBean bean) {
//                Intent intent = new Intent(WeightHeightActivity.this, TailorDetailActivity.class);
//                intent.putExtra("save",true);
//                intent.putExtra("time",timeStamp);
//                RecordBean.DataBean dataBean = new RecordBean.DataBean();
//                dataBean.setInfo(bean.getData().getInfo());
//                dataBean.setId(bean.getData().getId());
//                dataBean.setName(bean.getData().getName());
//                dataBean.setThreeshowurl(bean.getData().getThreeshowurl());
//                dataBean.setObjurl(bean.getData().getObjurl());
//                intent.putExtra(Constant.Config.ITEM,dataBean);
//                startActivity(intent);
//                EventBus.getDefault().post(new MeasureSuccessEvent());
//                finish();
//            }
//
//            @Override
//            public void onNext(ResponseBody t) {
//                try {
//                    JSONObject object = new JSONObject(t.string());
//                    if(object.has("iserror")){
//                        String code = object.getString("iserror");
//                        if(TextUtils.equals("0",code)){
//                            MeasureBean recordBean = new GsonTools().changeGsonToBean(object.toString(), MeasureBean.class);
//                            onSuccess(recordBean);
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        RestClient.getNovate(getApplication()).get(Constant.API.GET_MEASURE_INFO, new ParamMap.Build()
                .addParams("gender", 1)
                .addParams("pWeight", (int) mDefaultWeight)
                .addParams("pHeight", (int) mDefaultHeight).build(), new DmeycBaseSubscriber<WeightHeightBean>() {
            @Override
            public void onSuccess(WeightHeightBean bean) {
                TailorListBean.DataBean dataBean = new TailorListBean.DataBean();

                dataBean.setId(bean.getData().getId());
                dataBean.setAnkle(bean.getData().getAnkle());
                dataBean.setArm(bean.getData().getArm());
                dataBean.setBust(bean.getData().getBust());
                dataBean.setDownSide(bean.getData().getDownSide());
                dataBean.setShoulder(bean.getData().getShoulder());
                dataBean.setWrist(bean.getData().getWrist());
                dataBean.setWaistline(bean.getData().getWaistline());
                dataBean.setHipline(bean.getData().getHipline());
                dataBean.setPheight(bean.getData().getPheight());
                dataBean.setPweight(bean.getData().getPweight());

                Intent intent = new Intent(WeightHeightActivity.this, TailorDetailActivity.class);
                intent.putExtra("save", true);
                intent.putExtra(Constant.Config.ITEM, dataBean);
                startActivity(intent);
                finish();
            }
        });
    }
}
