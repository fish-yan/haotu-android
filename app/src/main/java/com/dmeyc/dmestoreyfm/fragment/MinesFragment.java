package com.dmeyc.dmestoreyfm.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.section.MineGoodsListSection;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.AboutMeActivity;
import com.dmeyc.dmestoreyfm.ui.AboutUsActivity;
import com.dmeyc.dmestoreyfm.ui.ActionRecordActivity;
import com.dmeyc.dmestoreyfm.ui.AllActionActivity;
import com.dmeyc.dmestoreyfm.ui.CacluliteListActivity;
import com.dmeyc.dmestoreyfm.ui.CommInActivity;
import com.dmeyc.dmestoreyfm.ui.CommperInActiivty;
import com.dmeyc.dmestoreyfm.ui.CopperateActivity;
import com.dmeyc.dmestoreyfm.ui.CreditCarActivity;
import com.dmeyc.dmestoreyfm.ui.MyAccountActivity;
import com.dmeyc.dmestoreyfm.ui.MyCommActivity;
import com.dmeyc.dmestoreyfm.ui.MyPromotionActivity;
import com.dmeyc.dmestoreyfm.ui.NameChangeActivity;
import com.dmeyc.dmestoreyfm.ui.PlaceInActivity;
import com.dmeyc.dmestoreyfm.ui.PlaceWaiteActivity;
import com.dmeyc.dmestoreyfm.ui.ProdRecordActivity;
import com.dmeyc.dmestoreyfm.ui.ReversToMeActivity;
import com.dmeyc.dmestoreyfm.ui.TeachInActivity;
import com.dmeyc.dmestoreyfm.ui.VacancyActivity;
import com.dmeyc.dmestoreyfm.ui.YFMSettingActivity;
import com.dmeyc.dmestoreyfm.ui.order.WholeOrderStatusActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.video.merchantentry.register.MerchantentryRegisterActivity;
import com.dmeyc.dmestoreyfm.video.merchantentry.register.TeachRegisterActivity;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.tamic.novate.Novate.TAG;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */

public class MinesFragment extends BaseFragment {

    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.civ_avatar)
    CircleImageView civAvatar;

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_count)
    TextView tv_count;
    @Bind(R.id.tv_activityre)
    TextView tv_activityre;
    @Bind(R.id.tv_comm)
    TextView tv_comm;
    @Bind(R.id.tv_verson)
    TextView tv_verson;

    @Bind(R.id.tv_mesumcount)
    TextView tv_mesumcount;

    private YFMUserBean yfmUserBean;
    private Context ctx;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private ArrayList<GoodsBean> goodsList = new ArrayList<>();
    private MineGoodsListSection mineGoodsListSection;

//SPUtils.getStringData(Constant.Config.AVATAR, "")


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mines;
    }

    @Override
    protected void initData() {

//        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_USER_INFO, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
//            @Override
//            public void onSuccess(YFMUserBean bean) {
//                yfmUserBean=bean;
//                if(!TextUtils.isEmpty(bean.getData().getUser_logo())){
//                    GlideUtil.loadImage(getActivity(),bean.getData().getUser_logo(),civAvatar);
//                }
//                tv_name.setText(bean.getData().getNick_name()+"");
//                tv_count.setText(bean.getData().getAccount_amount()+"");
//                tv_activityre.setText(bean.getData().getActivity_count()+"");
//                tv_comm.setText(bean.getData().getGroup_count()+"");
//            }
//        });
//        tv_verson.setText(Util.getLocalVersionName(getActivity())+"");

    }


    private void initSection(List<GoodsBean> goods) {
        goodsList.clear();
        goodsList.addAll(goods);
        mineGoodsListSection = new MineGoodsListSection(getActivity(), sectionedRecyclerViewAdapter);
        mineGoodsListSection.setData(goodsList);
        sectionedRecyclerViewAdapter.addSection(mineGoodsListSection);
    }
    @Override
    protected void initData(View view) {

    }

    @OnClick({R.id.iv_seting ,R.id.rl_teach_setin,R.id.rl_commin,R.id.rl_placein,R.id.tv_credit,R.id.tv_clear_cash,R.id.tv_about_me,
            R.id.civ_avatar,R.id.tv_name,R.id.ll_account,R.id.ll_activityrecord,R.id.ll_mycomm,
            R.id.ll_caculater,R.id.rl_commp,R.id.rl_my_card,R.id.rl_my_cacher,R.id.rl_aboutme,R.id.rl_insumentrecord,R.id.rl_vacancy,
            R.id.rl_copperate,R.id.tv_allactivity,R.id.rl_my_proguard,
            R.id.rl_promotion,R.id.rl_unstart,R.id.rl_staring,R.id.rl_started})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_msg:
//                startActivity(new Intent(getActivity(), TotalMessageActivity.class));
//                break;
            case R.id.rl_teach_setin:
                TeachRegisterActivity.newIntent(getActivity());
                break;
            case R.id.rl_commin:
                startActivity(new Intent(getActivity(), CommInActivity.class));
                break;
            case R.id.rl_placein:
//                startActivity(new Intent(getActivity(), PlaceInActivity.class));
//                startActivity(new Intent(getActivity(), PlaceWaiteActivity.class));
                SPUtils.savaStringData(Constant.Config.BUSINESS_REGISTER_TYPE,"6");
                MerchantentryRegisterActivity.newIntent(getActivity());
                break;
            case R.id.tv_credit:
                startActivity(new Intent(getActivity(), CreditCarActivity.class));
                break;
            case R.id.tv_clear_cash:
                SnackBarUtil.showShortSnackbar(tvLogin,"清理缓存成功");
                break;
            case R.id.tv_about_me:
                startActivity(new Intent(getActivity(),AboutUsActivity.class));
                break;
            case R.id.civ_avatar:
//                new PhotoSelectHelper(getActivity(),0).setCrop(true).show();
//                startActivity(new Intent(getActivity(),AboutUsActivity.class));
                break;
            case R.id.tv_name:
//                startActivityForResult(new Intent(getActivity(),NameChangeActivity.class).putExtra("name_nick",tv_name.getText().toString().trim()),100);
                break;
            case R.id.ll_account:
                startActivity(new Intent(getActivity(),MyAccountActivity.class));
                break;
            case R.id.ll_caculater:
                startActivity(new Intent(getActivity(),CacluliteListActivity.class));
                break;
            case R.id.ll_activityrecord:
                startActivity(new Intent(getActivity(),ActionRecordActivity.class));
                break;
            case R.id.ll_mycomm:
                startActivity(new Intent(getActivity(),MyCommActivity.class));
                break;
            case R.id.iv_seting:
                startActivity(new Intent(getActivity(),YFMSettingActivity.class));
                break;
            case R.id.rl_commp:
                // 商户入驻
                SPUtils.savaStringData(Constant.Config.BUSINESS_REGISTER_TYPE,"4");
                MerchantentryRegisterActivity.newIntent(getActivity());
//                startActivity(new Intent(getActivity(),CommperInActiivty.class));
                break;
            case R.id.rl_my_card:
                startActivity(new Intent(getActivity(), CreditCarActivity.class));
                break;
            case R.id.rl_my_cacher:
                startActivity(new Intent(getActivity(),ReversToMeActivity.class).putExtra("type","1"));
//                SnackBarUtil.showShortSnackbar(tvLogin,"清理缓存成功");
                break;
            case R.id.rl_aboutme:
//                startActivity(new Intent(getActivity(),AboutUsActivity.class));
                startActivity(new Intent(getActivity(),AboutMeActivity.class));
                break;
            case R.id.rl_insumentrecord:
                startActivity(new Intent(getActivity(),ProdRecordActivity.class));
                break;
            case R.id.rl_vacancy:
                startActivity(new Intent(getActivity(),VacancyActivity.class).putExtra("type","client"));
                break;
            case R.id.rl_copperate:
                startActivity(new Intent(getActivity(),CopperateActivity.class));
                break;
            case R.id.tv_allactivity:
                startActivity(new Intent(getActivity(),AllActionActivity.class).putExtra("actiontype","1"));
                break;
            case R.id.rl_my_proguard:
                startActivity(new Intent(getActivity(),ProdRecordActivity.class));
                break;
            case R.id.rl_promotion:
                startActivity(new Intent(getActivity(),MyPromotionActivity.class));
                break;
            case R.id.rl_unstart:
                startActivity(new Intent(getActivity(),AllActionActivity.class).putExtra("actiontype","2"));
                break;
            case R.id.rl_staring:
                startActivity(new Intent(getActivity(),AllActionActivity.class).putExtra("actiontype","3"));
                break;
            case R.id.rl_started:
                startActivity(new Intent(getActivity(),AllActionActivity.class).putExtra("actiontype","4"));
                break;

        }
    }

    private void startActivity(int position) {
        Intent intent = new Intent(getActivity(), WholeOrderStatusActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_USER_INFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
            @Override
            public void onSuccess(YFMUserBean bean) {
                yfmUserBean=bean;
                if(!TextUtils.isEmpty(bean.getData().getUser_logo())){
                    GlideUtil.loadImage(getActivity(),bean.getData().getUser_logo(),civAvatar);
                }
                tv_name.setText(bean.getData().getNick_name()+"");
                tv_count.setText(bean.getData().getAccount_amount()+"");
                tv_activityre.setText(bean.getData().getActivity_count()+"");
                tv_comm.setText(bean.getData().getGroup_count()+"");
                tv_mesumcount.setText(bean.getData().getMeasure_count()+"");
            }
        });
        tv_verson.setText(Util.getLocalVersionName(getActivity())+"");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                final ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
//                if (getActivity() != null) {
//                    GlideUtil.loadImage(getActivity(), images.get(0).getPath(), civAvatar);
//                }
                OkHttpClient client = new OkHttpClient();
                File file = new File(images.get(0).getPath());
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

                final RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                        .addFormDataPart("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GlideUtil.loadImage(getActivity(), imap.getData(), civAvatar);
                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(SPUtils.getStringData(Constant.Config.USER_ID), SPUtils.getStringData(Constant.Config.NICK_NAME), Uri.parse(imap.getData())));
                                ToastUtil.show(imap.getMsg());
                            }
                        });

//                        System.out.print(response.body().string()+"22222222222");
//                        response.body().string();
//                        ToastUtil.show("chengg");
                    }
                });

//                File dir = new File(images.get(0).getPath());
//                Uri uri = Uri.parse(images.get(0).getPath());
//                File file = new File(images.get(0).getPath());
//                List<Uri> uris = new ArrayList<>();
//                uris.add(uri);
//                Call<ImagePathBean> myCall = mMyService.getPic(file);
//                RestClient.getYfmNovate((Context) getActivity()).uploadImage(Constant.API.YFM_UPLOAD_HEADING, new File(images.get(0).getPath()), new DmeycBaseSubscriber<CommonBean>() {
//                File file = new File(images.get(0).getPath());


//                Map<String, Map<String, RequestBody>> params = new HashMap<>();
//                HttpParameterBuilder bulider = HttpParameterBuilder.newBuilder().addParameter("file", file);
////                Map<String, RequestBody> bulider = HttpParameterBuilder.newBuilder().addParameter("file", file);
//                Map<String, RequestBody>  builderparm=   bulider.addParameter("user_token", Constant.TEST_USERTOKEN).bulider();
//                params.put("file",bulider);

//                Map<String, Map<String, RequestBody>> params = new HashMap<>();
//                File file = new File(images.get(0).getPath());
//                Map<String, RequestBody> bulider = HttpParameterBuilder.newBuilder().addParameter("file", file).addParameter("user_token", "token").bulider();

//                Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.API.YFM_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//                NetInter service = retrofit.create(NetInter.class);
//                Call<ImagePathBean> myCall = service.getPic(builderparm);
//                myCall.enqueue(new Callback<ImagePathBean>() {
//                    @Override
//                    public void onResponse(Call<ImagePathBean> call, Response<ImagePathBean> response) {
//                        ToastUtil.show("chengg");
//                    }
//                    @Override
//                    public void onFailure(Call<ImagePathBean> call, java.lang.Throwable t) {
//                        ToastUtil.show("shibai");
//                    }
//                });

            }
        }else if(resultCode==222){
            tv_name.setText(data.getStringExtra("name_nake"));
        }
//

//                RestClient.getYfmNovateheader((Context) getActivity()).post(Constant.API.YFM_UPLOAD_HEADING, new ParamMap.Build().
//                        addParams("user_token", Constant.TEST_USERTOKEN).
//                        addParams("file",dir).build(), new DmeycBaseSubscriber<CommonBean>() {
//                    public void onSuccess(CommonBean bean) {
//                        String url = (String) bean.getData();
//                        updateUserInfo("avatar",url);
//                    }
//                });
//                requestPost();

//                File file = new File(picAbsPath);
//                RestClient.getYfmNovate((Context) getActivity()).uploadImage(Constant.API.YFM_UPLOAD_HEADING, new File(images.get(0).getPath()), new DmeycBaseSubscriber<CommonBean>() {
//
//                    @Override
//                    public void onSuccess(CommonBean bean) {
//                        if(bean.getData() instanceof Integer || bean.getData() instanceof Double || bean.getData() instanceof Float || bean.getData() instanceof BigDecimal){ //服务端返回的是int，这里返回的double
//                            int data = new Double((double)bean.getData()).intValue();
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });

//            }
//        }else if(resultCode==222){
//            tv_name.setText(data.getStringExtra("name_nake"));
//        }




    }

    public void updateUserInfo(final String key, final Object value){
        if(value == null) return;
        if(value instanceof String && TextUtils.isEmpty((String)value)) return;
        RestClient.getNovate(getActivity()).post(Constant.API.USER_INFOUPDATE, new ParamMap.Build()
                .addParams(key, value)
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
//                ToastUtil.show("操作成功");
                if(TextUtils.equals("avatar",key)){
                    SPUtils.savaStringData(Constant.Config.AVATAR,(String) value);
                }else if(TextUtils.equals("nickname",key)){
                    SPUtils.savaStringData(Constant.Config.NICK_NAME,(String) value);
                }
            }
        });
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
}
