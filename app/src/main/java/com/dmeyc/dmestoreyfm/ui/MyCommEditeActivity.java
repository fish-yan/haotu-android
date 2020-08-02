package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ClubIntroBean;
import com.dmeyc.dmestoreyfm.bean.CreatCommEditeBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.TrueNameBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;
import com.tamic.novate.Throwable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyCommEditeActivity extends BaseActivity {
    @Bind(R.id.cv_personheader)
    CircleImageView cv_personheader;
    @Bind(R.id.tv_groupname)
    EditText tv_groupname;
    @Bind(R.id.tv_grouptype)
    TextView tv_grouptype;
    @Bind(R.id.tv_orgname)
    TextView tv_orgname;
    @Bind(R.id.tv_adress)
    TextView tv_adress;
    @Bind(R.id.tv_phonenom)
    TextView tv_phonenom;
    @Bind(R.id.ll_clubintr)
    LinearLayout ll_clubintr;
    @Bind(R.id.ll_personintro)
    LinearLayout ll_personintro;
    @Bind(R.id.ll_binesintro)
    LinearLayout ll_binesintro;
    @Bind(R.id.tv_binesintro)
    TextView tv_binesintro;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mycommedite;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    CreatCommEditeBean creatCommEditeBean;
    public void getData() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETMYCOMMINFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getIntExtra("groupid", -1) + "")
                .build(), new DmeycBaseSubscriber<CreatCommEditeBean>() {
            @Override
            public void onSuccess(CreatCommEditeBean bean) {
                creatCommEditeBean = bean;
                GlideUtil.loadImage(MyCommEditeActivity.this, bean.getData().getGroup_logo(), cv_personheader);
                heardicon = bean.getData().getGroup_logo();
                tv_groupname.setText(bean.getData().getGroup_name());
                if (bean.getData().getGroupType().equals("1")) {
                    tv_grouptype.setText("个人组织");
                }

                tv_orgname.setText(bean.getData().getNotice());
                tv_phonenom.setText(bean.getData().getPhoneNo() + "");
                if (bean.getData().getProvince().equals(bean.getData().getCity())) {
                    tv_adress.setText(bean.getData().getCity() + bean.getData().getArea());
                } else {
                    tv_adress.setText(bean.getData().getProvince() + bean.getData().getCity() + bean.getData().getArea() + bean.getData().getActivity_venue_address());
                }
                tv_orgname.setText(bean.getData().getUsername());
                if ("1".equals(bean.getData().getGroupType()) || "4".equals(bean.getData().getGroupType())) {
                    ll_clubintr.setVisibility(View.VISIBLE);
                    ll_personintro.setVisibility(View.GONE);
                    ll_binesintro.setVisibility(View.GONE);
                } else if ("5".equals(bean.getData().getGroupType())) {
                    ll_clubintr.setVisibility(View.GONE);
                    ll_binesintro.setVisibility(View.VISIBLE);
                    ll_personintro.setVisibility(View.GONE);
                    tv_binesintro.setText("商户简介");
                } else if ("3".equals(bean.getData().getGroupType())) {
                    ll_clubintr.setVisibility(View.GONE);
                    ll_binesintro.setVisibility(View.VISIBLE);
                    ll_personintro.setVisibility(View.VISIBLE);
                } else {
                    ll_clubintr.setVisibility(View.GONE);
                    ll_personintro.setVisibility(View.GONE);
                    ll_binesintro.setVisibility(View.GONE);
                }

//                getComminfo();
//                ToastUtil.show(bean.getMsg());
            }
        });

    }

    @OnClick({R.id.cv_personheader, R.id.ll_editpage, R.id.tv_phonenom, R.id.tv_adress, R.id.btn_regist, R.id.ll_phone_edite, R.id.ll_clubintr, R.id.ll_personintro, R.id.ll_binesintro})
    public void onClick(View view) {
        int viewid = view.getId();
        if (viewid == R.id.cv_personheader) {
            new PhotoSelectHelper(MyCommEditeActivity.this, 0).setCrop(true).show();
        } else if (R.id.ll_editpage == viewid) {
            startActivityForResult(new Intent(MyCommEditeActivity.this, GroupnameChangeActivity.class).
                    putExtra("groupname", creatCommEditeBean.getData().getGroup_name())
                    .putExtra("groupid", creatCommEditeBean.getData().getGroup_id()).putExtra("grouplog", heardicon), 111);
        } else if (R.id.tv_phonenom == viewid) {
//            startActivityForResult(new Intent(MyCommEditeActivity.this,GroupnameChangeActivity.class).putExtra("phonenumber",creatCommEditeBean.getData().getPhoneNo())
//                    .putExtra("groupid",creatCommEditeBean.getData().getGroup_id()),222);
        } else if (R.id.tv_adress == viewid) {
            startActivityForResult(new Intent(MyCommEditeActivity.this, GroupnameChangeActivity.class).putExtra("address", creatCommEditeBean.getData().getActivity_venue_address())
                    .putExtra("groupid", creatCommEditeBean.getData().getGroup_id()), 222);
        } else if (R.id.btn_regist == viewid) {
            changeName();
        } else if (R.id.ll_phone_edite == viewid) {
            startActivityForResult(new Intent(MyCommEditeActivity.this, GroupnameChangeActivity.class).putExtra("phonenumber", creatCommEditeBean.getData().getPhoneNo())
                    .putExtra("groupid", creatCommEditeBean.getData().getGroup_id()), 222);
        } else if (R.id.ll_clubintr == viewid) {
            startActivity(new Intent(MyCommEditeActivity.this, CommIntroEditeActivity.class)
                    .putExtra("groupid", creatCommEditeBean.getData().getGroup_id()).putExtra("remark",creatCommEditeBean.getData().getRemark()));
        } else if (R.id.ll_binesintro == viewid) {
            startActivity(new Intent(MyCommEditeActivity.this, ChangeBackActivity.class).
                    putExtra("groupid", creatCommEditeBean.getData().getGroup_id()).
                    putExtra("education", "education").putExtra("grouptype", creatCommEditeBean.getData().getGroupType()));
        } else if (R.id.ll_personintro == viewid) {
            startActivity(new Intent(MyCommEditeActivity.this, ChangeBackActivity.class).putExtra("groupid", creatCommEditeBean.getData().getGroup_id())
                    .putExtra("education", "person"));
        }
    }

    String heardicon = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                final ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                OkHttpClient client = new OkHttpClient();
                File file = new File(images.get(0).getPath());
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

                final RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                        .addFormDataPart("isLogo", 1 + "")
                        .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .build();

                Request request = new Request.Builder()
                        .url(Constant.API.YFM_BASE_URL + "api/v1.0/file/uploadSingle")
                        .post(requestBody)
                        .build();
                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        ToastUtil.show(call.toString());
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        Gson gs = new Gson();
                        final ImagePathBean imap = gs.fromJson(response.body().string(), ImagePathBean.class);
                        MyCommEditeActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                heardicon = imap.getData();
                                GlideUtil.loadImage(MyCommEditeActivity.this, imap.getData(), cv_personheader);
                                RongIM.getInstance().refreshGroupInfoCache(new Group(creatCommEditeBean.getData().getGroup_id() + "", creatCommEditeBean.getData().getGroup_name(), Uri.parse(imap.getData())));
                                //
//  ToastUtil.show(imap.getMsg());
                                ChageHeader(imap.getData());
                            }
                        });
                    }
                });
            }
        } else if (resultCode == 444) {
            if (!TextUtils.isEmpty(data.getStringExtra("name"))) {
                tv_groupname.setText(data.getStringExtra("name"));
            } else if (!TextUtils.isEmpty(data.getStringExtra("phone"))) {
                tv_phonenom.setText(data.getStringExtra("phone"));
            }
        }
    }

    public void ChageHeader(String heaerd) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGECOMMINFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_logo", heaerd)
                .addParams("group_id", creatCommEditeBean.getData().getGroup_id())
                .build(), new DmeycBaseSubscriber<TrueNameBean>() {
            @Override
            public void onSuccess(TrueNameBean bean) {

//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
                ToastUtil.show(bean.getMsg());
//                finish();
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


    public void changeName() {
        if (TextUtils.isEmpty(tv_groupname.getText().toString().trim())) {
            ToastUtil.show("请输入群名称");
            return;
        }
        if (TextUtils.isEmpty(tv_phonenom.getText().toString().trim())) {
            ToastUtil.show("请输入手机号");
            return;
        }
        ParamMap.Build pb = new ParamMap.Build();
//        if("name".equals(type)){
//            pb .addParams("group_name", et_name.getText().toString().trim());
//        }else if("phone".equals(type)){
//            pb .addParams("phoneNo", et_name.getText().toString().trim());
//        }else if("address".equals(type)){
//            pb .addParams("activity_venue_address", et_name.getText().toString().trim());
//        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGECOMMINFO,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("group_id", getIntent().getIntExtra("groupid", -1))
                        .addParams("group_name", tv_groupname.getText().toString().trim())
                        .addParams("phoneNo", tv_phonenom.getText().toString().trim())
                        .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                    @Override
                    public void onSuccess(PublishActionAfterBean bean) {
//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
//                        Intent intent=new Intent();
//                        if("name".equals(type)){
//                            intent.putExtra("name",et_name.getText().toString().trim());
//                        }else if("phone".equals(type)){
//                            intent.putExtra("phone",et_name.getText().toString().trim());
//                        }
//                        setResult(444,intent);
                        RongIM.getInstance().refreshGroupInfoCache(new Group(creatCommEditeBean.getData().getGroup_id() + "", tv_groupname.getText().toString().trim(), Uri.parse(heardicon)));

                        ToastUtil.show(bean.getMsg());
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getComminfo() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETCOMMINRO,
                new ParamMap.Build().addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("group_id", getIntent().getIntExtra("groupid", -1))
                        .build(), new DmeycBaseSubscriber<ClubIntroBean>() {
                    @Override
                    public void onSuccess(ClubIntroBean bean) {
                          ToastUtil.show(bean.getMsg());
                 }
               });
             }
}