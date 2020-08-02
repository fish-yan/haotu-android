package com.dmeyc.dmestoreyfm.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.AdapterCommIntro;
import com.dmeyc.dmestoreyfm.adapter.photo.AlbumActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.CommonDefine;
import com.dmeyc.dmestoreyfm.adapter.photo.FileUtils;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageDelActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageUtils;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.TestUplodeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EducationBackActivity extends BaseActivity {
    @Bind(R.id.et_backtext)
    EditText et_backtext;
    @Bind(R.id.tv_title)
    TextView tv_title;
    private RecyclerView rl_pic;
    AdapterCommIntro recycleViewAdapter;

    protected ImageLoader loader;
    protected DisplayImageOptions options;
    private ArrayList<String> dataList;
    private ArrayList<String> desc;
    @Override
    protected int getLayoutRes() {
        return R.layout.actiivty_educationback;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        dataList = new ArrayList<String>();
        initPhotoError();
        if(getIntent().getStringExtra("type").equals("1")){
            et_backtext.setHint("请输入您的教育经历");
            tv_title.setText("教育背景");
            dataList.clear();
            ArrayList<String> backimages=  getIntent().getStringArrayListExtra("backimages");
            if(backimages!=null){
                for (int ii=0;ii<backimages.size();ii++){
                    dataList.add(backimages.get(ii));
                }
            }

        }else if(getIntent().getStringExtra("type").equals("2")) {
            et_backtext.setHint("请输入您的个人简介");
            tv_title.setText("个人简介");
            dataList.clear();
            ArrayList<String> backimages=  getIntent().getStringArrayListExtra("backimages");
            if(backimages!=null){
                for (int ii=0;ii<backimages.size();ii++){
                    dataList.add(backimages.get(ii));
                }
            }
        }else {
            tv_title.setText("活动简介");
            et_backtext.setHint("请输入活动/课程介绍");
            dataList.clear();
            ArrayList<String> backimages=  getIntent().getStringArrayListExtra("backimages");
            if(backimages!=null){
                for (int ii=0;ii<backimages.size();ii++){
                    dataList.add(backimages.get(ii));
                }
            }
//            recycleViewAdapter.notifyDataSetChanged();
        }
        et_backtext.setText(getIntent().getStringExtra("intro"));

        rl_pic=(RecyclerView) findViewById(R.id.rl_pic);
        GridLayoutManager myLayoutManager = new GridLayoutManager(this, 4);
        rl_pic.setLayoutManager(myLayoutManager);

        options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.drawable.pic_loading)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        desc = new ArrayList<String>();
//        dataList.add("camera_default");
        recycleViewAdapter  =new AdapterCommIntro(EducationBackActivity.this, dataList, loader, options,desc);
        rl_pic.setAdapter(recycleViewAdapter);
        recycleViewAdapter.setOnDeletClickList(new AdapterCommIntro.OnDeletClick() {
            @Override
            public void onDelet(int position) {
                String path = dataList.get(position);
                if (path.contains("default") && position == dataList.size() -1 && dataList.size() -1 != 12) {
                    showSelectImageDialog();
                } else {
                    Intent intent = new Intent(EducationBackActivity.this, ImageDelActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("path", dataList.get(position));
                    startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
                }
            }
            @Override
            public void desc(String dec) {

            }
            @Override
            public void desc(ArrayList<String> dec) {

            }
        });


    }
    @Bind(R.id.btn_next)
    Button btn_next;
    @OnClick({R.id.iv_right_title_bar,R.id.btn_next})
    public void onClick(View view){
        int viweid=view.getId();
        if(viweid==R.id.iv_right_title_bar){
            showSelectImageDialog();
        }else if(R.id.btn_next==viweid){
            if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
                if(getIntent().getStringExtra("type").equals("1")){
                    ToastUtil.show("教育背景不为空");
                }else if(getIntent().getStringExtra("type").equals("2")) {
                    ToastUtil.show("简介不为空");
                }else {
                    ToastUtil.show("活动简介不为空");
                }
                return;
            }
            imagurl.clear();
            if(dataList.size()==0){
                Intent intent=new Intent();
                intent.putExtra("backtext",et_backtext.getText().toString().trim());
                intent.putStringArrayListExtra("photolist",imagurl);
                if(getIntent().getStringExtra("type").equals("1")){
                    setResult(888,intent);
                }else if(getIntent().getStringExtra("type").equals("2")){
                    setResult(777,intent);
                }else {
                    setResult(666,intent);
                }
                finish();
            }else {
                btn_next.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
                btn_next.setClickable(false);
//                btn_next.setClickable(false);
                upLoadeImage();
            }
        }
    }
    private ArrayList<String> orange=new ArrayList<>();
    private ArrayList<String> newurl=new ArrayList<>();
    private int uplodecount;
    ArrayList<String> imagurl=new ArrayList<>();
    ArrayList<String> changeimagurl=new ArrayList<>();
    public void upLoadeImage(){
        for (int i=0;i<dataList.size();i++){
            if(dataList.get(i).startsWith("http")){
                orange.add(dataList.get(i));
            }else {
                if(!"camera_default".equals(dataList.get(i))){
                    newurl.add(dataList.get(i)) ;
                }
            }
        }
        if(newurl.size()!=0){
            for (int i=0;i<newurl.size();i++){
                if(!newurl.get(i).equals("camera_default")){
                    upLodePic(newurl.get(i));
                    uplodecount++;
                }else {
                }
            }
        }else {
            imagurl.addAll(orange);
            Intent intent=new Intent();
            intent.putExtra("backtext",et_backtext.getText().toString().trim());
            intent.putStringArrayListExtra("photolist",imagurl);
            if(getIntent().getStringExtra("type").equals("1")){
                setResult(888,intent);
            }else if(getIntent().getStringExtra("type").equals("2")){
                setResult(777,intent);
            }else {
                setResult(666,intent);
            }
            finish();
        }

//        for (int i=0;i<dataList.size();i++){
//            if(!dataList.get(i).equals("camera_default")){
//                upLodePic(dataList.get(i));
//                uplodecount++;
//            }else {
//            }
//        }

    }
    String filepath;
    int flag=0;
    public void upLodePic(String path){
        OkHttpClient client = new OkHttpClient();
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("isLogo",0+"")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build();
        final Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                btn_next.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_next.setClickable(true);
            }
            @Override
            public void onResponse(final Call call,  Response response) throws IOException {
                Gson gs=new Gson();
              ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
//                Log.e("ssssssssssssss",response.body().string());
//                System.out.print("ssssssssssssss"+imap.getData());

                imagurl.add(imap.getData());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(imagurl.size()+orange.size()==dataList.size()){
                            imagurl.addAll(orange);
                            Intent intent=new Intent();
                            intent.putExtra("backtext",et_backtext.getText().toString().trim());
                            intent.putStringArrayListExtra("photolist",imagurl);
                            if(getIntent().getStringExtra("type").equals("1")){
                                setResult(888,intent);
                            }else if(getIntent().getStringExtra("type").equals("2")){
                                setResult(777,intent);
                            }else {
                                setResult(666,intent);
                            }
                            finish();
                        }
                    }
                });
            }
        });
    }
    private Uri uri;
    // 閫夋嫨鐩稿唽锛岀浉鏈�
    private void showSelectImageDialog() {
        final List<String> strings = new ArrayList<>();
        strings.add("拍 照");
        strings.add("从手机相册选择");
        StyledDialog.buildBottomItemDialog(EducationBackActivity.this, strings, "cancle",  new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {
//                      ToastUtil.show("sss+"+position);
                Intent intent = new Intent();
                if(0==position){
                    Intent cameraIntent = new Intent();
                    cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    // 鏍规嵁鏂囦欢鍦板潃鍒涘缓鏂囦欢
                    File file = new File(CommonDefine.FILE_PATH);
                    if (file.exists()) {
                        file.delete();
                    }
                    uri = Uri.fromFile(file);
                    // 璁剧疆绯荤粺鐩告満鎷嶆憚鐓х墖瀹屾垚鍚庡浘鐗囨枃浠剁殑瀛樻斁鍦板潃
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    // 寮�鍚郴缁熸媿鐓х殑Activity
                    startActivityForResult(cameraIntent, CommonDefine.TAKE_PICTURE_FROM_CAMERA);

                }else {
                    Intent intent1 = new Intent(EducationBackActivity.this, AlbumActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("dataList", getIntentArrayList(dataList));
                    intent1.putExtras(bundle);
                    startActivityForResult(intent1, CommonDefine.TAKE_PICTURE_FROM_GALLERY);
                }

            }

            @Override
            public void onBottomBtnClick() {
//                        ToastUtil.show("sss+");
            }
        }).show();
    }
    private ArrayList<String> getIntentArrayList(ArrayList<String> dataList) {
        ArrayList<String> tDataList = new ArrayList<String>();
        for (String s : dataList) {
            if (!s.contains("default")) {
                tDataList.add(s);
            }
        }
        return tDataList;
    }


    private ArrayList<String> tDataList;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CommonDefine.TAKE_PICTURE_FROM_CAMERA:
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                        return;
                    }
                    Bitmap bitmap = ImageUtils.getUriBitmap(this, uri, 400, 400);
                    String cameraImagePath = FileUtils.saveBitToSD(bitmap, System.currentTimeMillis()+"");

//				Bundle bundle = data.getExtras();
//				Bitmap bitmap = (Bitmap) bundle.get("data");
//				String cameraImagePath = ImageUtils.setCameraImage(bitmap);

                    for (int i = 0; i < dataList.size(); i++) {
                        String path = dataList.get(i);
                        if(path.contains("default")) {
                            dataList.remove(dataList.size() - 1);
                        }
                    }
                    dataList.add(cameraImagePath);
//                    if(dataList.size() < 12) {
//                        dataList.add("camera_default");
//                    }
//                    recycleViewAdapter.setdefoud(-1);
                    recycleViewAdapter.notifyDataSetChanged();
                    break;
                case CommonDefine.TAKE_PICTURE_FROM_GALLERY:
                    Bundle bundle2 = data.getExtras();
                    tDataList = (ArrayList<String>) bundle2.getSerializable("dataList");
                    if (tDataList != null) {
                        for (int i = 0; i < tDataList.size(); i++) {
                            String string = tDataList.get(i);
                            dataList.add(string);
                        }
//                        if (dataList.size() < 12) {
//                            dataList.add("camera_default");
//                        }
//                        gridImageAdapter.setdefoud(-1);
                        recycleViewAdapter.notifyDataSetChanged();
                    }
                    break;
                case CommonDefine.DELETE_IMAGE:
                    int position = data.getIntExtra("position", -1);
                    dataList.remove(position);
                    if(dataList.size() < 12 ) {
                        dataList.add(dataList.size(), "camera_default");
                        for (int i = 0; i < dataList.size(); i++) {
                            String path = dataList.get(i);
                            if(path.contains("camera_default")) {
                                if(dataList.get(dataList.size()-2).contains("camera_default")){
                                    dataList.remove(dataList.size() - 2);
                                }

                            }
                        }
                    }
//                    gridImageAdapter.setdefoud(-1);
                    recycleViewAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }else {
            if(requestCode==CommonDefine.TAKE_PICTURE_FROM_GALLERY){
                if(data!=null){
                    Bundle bundle2 = data.getExtras();
                    dataList.clear();

                    tDataList = (ArrayList<String>) bundle2.getSerializable("dataList");
                    if (tDataList != null) {
                        for (int i = 0; i < tDataList.size(); i++) {
                            String string = tDataList.get(i);
                            if(!tDataList.get(i).equals("camera_default")){
                                dataList.add(string);
                            }
                        }
//                        if (dataList.size() < 12) {
//                            dataList.add("camera_default");
//                        }
//                        gridImageAdapter.setdefoud(-1);
                        recycleViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @SuppressLint("NewApi")
    private void initPhotoError(){
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

}
