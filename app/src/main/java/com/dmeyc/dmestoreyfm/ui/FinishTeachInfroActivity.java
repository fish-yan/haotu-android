package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.TeachInBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FinishTeachInfroActivity extends BaseActivity {

    @Bind(R.id.et_teachinfo)
    TextView et_teachinfo;
    @Bind(R.id.et_teachname)
    EditText et_teachname;
    @Bind(R.id.et_teachage)
    EditText et_teachage;
    @Bind(R.id.tv_educationback)
    TextView tv_educationback;
     @Bind(R.id.cv_teacheheader)
    CircleImageView cv_teacheheader;
     @Bind(R.id.tv_inro)
     TextView tv_inro;
    int type=0;
    String sex="";
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_finishteachinfro;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.ll_Education,R.id.ll_intro,R.id.ll_sex,R.id.btn_next,R.id.cv_teacheheader})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.ll_Education==viewid){
      startActivityForResult(new Intent(FinishTeachInfroActivity.this,EducationBackActivity.class).putExtra("type","1").putExtra("backimages",photolist).putExtra("intro",intro),100);
        }else if(R.id.ll_intro==viewid){
            startActivityForResult(new Intent(FinishTeachInfroActivity.this,EducationBackActivity.class).putExtra("type","2").putExtra("backimages",photolistintro).putExtra("intro",personintro),100);
        }else if(R.id.ll_sex==viewid){
            startActivityForResult(new Intent(FinishTeachInfroActivity.this,SexChageActivity.class).putExtra("sex","1").putExtra("type","teachin"),222);
        }else if(R.id.btn_next==viewid){
            submit();
        }else if(R.id.cv_teacheheader==viewid){
            type=1;
            new PhotoSelectHelper(this,0).setCrop(true).show();
        }
    }
    ArrayList<String> photolist;
    String intro;
    ArrayList<String> photolistintro;
    String personintro;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==999){
            if(data.getStringExtra("sex").equals("1")){
                et_teachinfo.setText("男");
                sex="1";
            }else {
                sex="2";
                et_teachinfo.setText("女");
            }
        }else if(resultCode==888){

            photolist= data.getStringArrayListExtra("photolist");
            intro=data.getStringExtra("backtext");
            tv_educationback.setText("已填写");
        }else if(resultCode==777){
            photolistintro= data.getStringArrayListExtra("photolist");
            personintro=data.getStringExtra("backtext");
            tv_inro.setText("已填写");
        }     else if(data != null && resultCode == RESULT_OK&&type==1){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this,images.get(0).getPath(),cv_teacheheader);
//                photo1=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
    }

    String photo1;
    String photo2;
    String photo3;
    String photo4;


    private void uplodeteach(String filepath) {
        OkHttpClient client = new OkHttpClient();
        File file = new File(filepath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);


        MultipartBody.Builder mb= new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                .addFormDataPart("user_token",SPUtils.getStringData(Constant.Config.TOKEN));
        if(1==type){
            mb.addFormDataPart("isLogo",1+"");
        }else {
            mb.addFormDataPart("isLogo",0+"");
        }
        final RequestBody requestBody =mb.build();
//                .build();

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
//                        GlideUtil.loadImage(TeachInActivity.this, imap.getData(), iv_roundmage);
                        if(1==type){
                            photo1=imap.getData();
                        }/*else if(2==type){
                            photo2=imap.getData();
                        } else if (3==type) {
                            photo3=imap.getData();
                        }else if(4==type){
                            photo4=imap.getData();
                        }*/
                        ToastUtil.show(imap.getMsg());
                    }
                });

            }
        });
    }

    public void submit(){
//        if(TextUtils.isEmpty(photo1)) {
//            ToastUtil.show("请选择教练logo");
//            return;
//        }
        if(TextUtils.isEmpty(sex)){
            ToastUtil.show("请选择性别");
            return;
        }
//        if(TextUtils.isEmpty(et_teachname.getText().toString().trim())){
//            ToastUtil.show("请输入教练名字");
//            return;
//        }
        if(TextUtils.isEmpty(et_teachage.getText().toString().trim())){
            ToastUtil.show("请输入教练年龄");
            return;
        }
        if(!tv_educationback.getText().toString().trim().equals("已填写")){
            ToastUtil.show("请填写教育背景");
            return;
        }
        if(!tv_inro.getText().toString().trim().equals("已填写")){
            ToastUtil.show("请填写个人简介");
            return;
        }
        Intent intent=new Intent();
        TeachInBean teachInBean=new TeachInBean();
        teachInBean.setTeachlog(photo1);
        teachInBean.setTeachsex(sex);
        teachInBean.setTeachname(et_teachname.getText().toString().trim());
        teachInBean.setTeachage(et_teachage.getText().toString().trim());
        teachInBean.setTeacheducation(intro);
        teachInBean.setTeachintro(personintro);
        teachInBean.setTeachimags(photolist);
        teachInBean.setTeachintroimages(photolistintro);
        Bundle bundle = new Bundle();
        bundle.putSerializable("teachin", teachInBean);
        intent.putExtras(bundle);
         setResult(555,intent);
            finish();
    }
}
