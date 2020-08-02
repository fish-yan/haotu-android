package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.H5PicRecycleViewAdapter;
import com.dmeyc.dmestoreyfm.adapter.photo.AlbumActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.CommonDefine;
import com.dmeyc.dmestoreyfm.adapter.photo.FileUtils;
import com.dmeyc.dmestoreyfm.adapter.photo.GridImageAdapter;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageDelActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageUtils;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.H5SubmitBean;
import com.dmeyc.dmestoreyfm.bean.H5UpdateReturnBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.PlaceInfroBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.tamic.novate.Throwable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.dmeyc.dmestoreyfm.utils.image.ImageUtil.createImageFile;

public class SharePicInActivity extends BaseActivity {
    H5SubmitBean h5SubmitBean;
    ArrayList<H5SubmitBean> arrayList=new ArrayList<>();
    private GridView gridView;
    private GridImageAdapter gridImageAdapter;
    protected ImageLoader loader;
    protected DisplayImageOptions options;
    private ArrayList<String> dataList;
    private ArrayList<String> orange=new ArrayList<>();
    private ArrayList<String> newurl=new ArrayList<>();
    private ArrayList<String> desc;
    private RecyclerView rl_pic;
      private Button btn_regist2;
    H5PicRecycleViewAdapter recycleViewAdapter;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sharepicin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        btn_regist2=(Button)findViewById(R.id.btn_regist2);
        rl_pic=(RecyclerView) findViewById(R.id.rl_pic);
        GridLayoutManager myLayoutManager = new GridLayoutManager(this, 2);
        rl_pic.setLayoutManager(myLayoutManager);

        gridView = (GridView) findViewById(R.id.gridview_image);
        options = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.drawable.pic_loading)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        dataList = new ArrayList<String>();
        desc = new ArrayList<String>();
        gridImageAdapter = new GridImageAdapter(SharePicInActivity.this, dataList, loader, options,desc);
        gridView.setAdapter(gridImageAdapter);
        recycleViewAdapter  =new H5PicRecycleViewAdapter(SharePicInActivity.this, dataList, loader, options,desc);
        rl_pic.setAdapter(recycleViewAdapter);
        recycleViewAdapter.setOnDeletClickList(new H5PicRecycleViewAdapter.OnDeletClick() {
            @Override
            public void onDelet(int pos) {
                Intent intent = new Intent(SharePicInActivity.this, ImageDelActivity.class);
                intent.putExtra("position", pos);
                intent.putExtra("path", dataList.get(pos));
                startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
            }

            @Override
            public void desc(String dec) {

            }

            @Override
            public void desc(ArrayList<String> dec) {

            }
        });

//        gridImageAdapter = new GridImageAdapter(this, dataList, loader, options);
//        gridView.setAdapter(gridImageAdapter);
//        gridImageAdapter.setOnDeletClickList(new GridImageAdapter.OnDeletClick() {
//            @Override
//            public void onDelet(int pos) {
//                ToastUtil.show(pos+"ssss");
//                Intent intent = new Intent(SharePicInActivity.this, ImageDelActivity.class);
//                intent.putExtra("position", pos);
//                intent.putExtra("path", dataList.get(pos));
//                startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
//            }
//
//            @Override
//            public void desc(String dec) {
//
//            }
//
//            @Override
//            public void desc(ArrayList<String> dec) {
//
//            }
//        });
//        initListener();
//
        getPicData();
    }
    private void initListener() {

        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = dataList.get(position);
                if (path.contains("default") && position == dataList.size() -1 && dataList.size() -1 != 12) {
                    showSelectImageDialog();
                } else {
                    Intent intent = new Intent(SharePicInActivity.this, ImageDelActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("path", dataList.get(position));
                    startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
                }
            }
        });
    }

    private Uri uri;
    // 閫夋嫨鐩稿唽锛岀浉鏈�
    private void showSelectImageDialog() {
        final List<String> strings = new ArrayList<>();
        strings.add("拍 照");
        strings.add("从手机相册选择");

        StyledDialog.buildBottomItemDialog(SharePicInActivity.this, strings, "cancle",  new MyItemDialogListener() {
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
//                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    uri= FileProvider.getUriForFile(SharePicInActivity.this, SharePicInActivity.this.getApplicationContext().getPackageName() + ".provider", file);
                    // 寮�鍚郴缁熸媿鐓х殑Activity
                    startActivityForResult(cameraIntent, CommonDefine.TAKE_PICTURE_FROM_CAMERA);
//                    picAddDialog.dismiss();
                }else {
//                    if(1==type){
//                        dataList.clear();
//                        dataList.add("camera_default");
//                    }
                    Intent intent1 = new Intent(SharePicInActivity.this, AlbumActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("dataList", getIntentArrayList(dataList));
                    intent1.putExtras(bundle);
                    startActivityForResult(intent1, CommonDefine.TAKE_PICTURE_FROM_GALLERY);
                }


            }

            @Override
            public void onBottomBtnClick() {
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
                    gridImageAdapter.setdefoud(-1);
//                    gridImageAdapter.notifyDataSetChanged();
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
                        gridImageAdapter.setdefoud(-1);
//                        gridImageAdapter.notifyDataSetChanged();
                        recycleViewAdapter.notifyDataSetChanged();
                    }
                    break;
                case CommonDefine.DELETE_IMAGE:
                    int position = data.getIntExtra("position", -1);
                    dataList.remove(position);
//                    desc.remove(position);
                    if(dataList.size() < 12 ) {
//                        dataList.add(dataList.size(), "camera_default");
                        for (int i = 0; i < dataList.size(); i++) {
                            String path = dataList.get(i);
                            if(path.contains("camera_default")) {
                                if(dataList.get(dataList.size()-2).contains("camera_default")){
                                    dataList.remove(dataList.size() - 2);
                                }
                            }
                        }
                    }
                    gridImageAdapter.setdefoud(-1);
//                    gridImageAdapter.cleardata();
//                    gridImageAdapter.notifyDataSetChanged();
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
                        gridImageAdapter.setdefoud(-1);
                        gridImageAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
//String et_desc="";
//    String json;
//    ArrayList<String> desc;
@OnClick({R.id.iv_right_title_bar,R.id.btn_regist2})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.iv_right_title_bar==viewid){
            showSelectImageDialog();
//            Intent cameraIntent = new Intent();
//            cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//            cameraIntent.addCategory(Intent.CATEGORY_DEFAULT);
//            // 鏍规嵁鏂囦欢鍦板潃鍒涘缓鏂囦欢
//            File file = new File(CommonDefine.FILE_PATH);
//            if (file.exists()) {
//                file.delete();
//            }
//            uri= FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
////            uri = Uri.fromFile(file);
//            // 璁剧疆绯荤粺鐩告満鎷嶆憚鐓х墖瀹屾垚鍚庡浘鐗囨枃浠剁殑瀛樻斁鍦板潃
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//
//            // 寮�鍚郴缁熸媿鐓х殑Activity
//            startActivityForResult(cameraIntent, CommonDefine.TAKE_PICTURE_FROM_CAMERA);
        }else if(R.id.btn_regist2==viewid){

            if(1==ischange){
                changeh5();
            }else {
                h5submit();
            }

//            gridImageAdapter.setOnDeletClickList(new GridImageAdapter.OnDeletClick() {
//                @Override
//                public void onDelet(int pos) {
//                }
//                @Override
//                public void desc(String dec) {
//                    if(dec.endsWith(",")){
//                        String endsec=dec.substring(0,dec.length());
//                    }
//
//                    ToastUtil.show(dec+"sssss");
//                }
//                @Override
//                public void desc(ArrayList<String> dec) {
//                    if(dataList.size()!=dec.size()){
//                        ToastUtil.show("描述文字不为空");
//                        return;
//                    }else {
//                            desc=dec;
//                            upLoadeImage();
////                        }
//                    }
//                }
//            });

        }/*else if(R.id.iv_delete==viewid){
            Intent intent = new Intent(SharePicInActivity.this, ImageDelActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("path", dataList.get(position));
            startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
        }*/
}
    private int uplodecount;
ArrayList<String> imagurl=new ArrayList<>();
    ArrayList<String> changeimagurl=new ArrayList<>();
public void upLoadeImage(){

        for (int i=0;i<dataList.size();i++){
            if(!dataList.get(i).equals("camera_default")){
                upLodePic(dataList.get(i));
                uplodecount++;
            }else {
            }
        }

      }
    public void changeupLoadeImage(){
        for (int i=0;i<newurl.size();i++){
            if(!newurl.get(i).equals("camera_default")){
//                upLodePic(newurl.get(i));
                changeupLodePic(newurl.get(i));
                uplodecount++;
            }else {
            }
        }
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
        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                ToastUtil.show(call.toString());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Gson gs=new Gson();
                final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
//                filepath+=imap.getData()+",";
                imagurl.add(imap.getData());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(imagurl.size()==dataList.size()){
                            submitcomment(filepath);
                        }

                    }
                });
            }
        });
    }

    public void changeupLodePic(String path){
        OkHttpClient client = new OkHttpClient();
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("isLogo",0+"")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build();
        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                ToastUtil.show(call.toString());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Gson gs=new Gson();
                final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
//                filepath+=imap.getData()+",";
                changeimagurl.add(imap.getData());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(changeimagurl.size()==newurl.size()){
                            submitchangecomment(filepath);
                        }
                    }
                });
            }
        });
    }


    public void submitcomment(String path){
                for(int i=0;i<desc.size();i++){
                    h5SubmitBean=new  H5SubmitBean();
                    h5SubmitBean.setContent(desc.get(i));
                    h5SubmitBean.setUrl(imagurl.get(i));
                    arrayList.add(h5SubmitBean);
                }
//          Gson gson=new Gson();
//          json= gson.toJson(arrayList);
       RestClient.getYfmNovate(SharePicInActivity.this).post(Constant.API.YFM_SHARE_H5, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("isPk", getIntent().getStringExtra("ispk"))
                .addParams("activityId", getIntent().getIntExtra("activityid",-1))
                .addParams("h5ShareImageList", new Gson().toJson(arrayList))
                .build(), new DmeycBaseSubscriber<H5UpdateReturnBean>() {
            @Override
            public void onSuccess(H5UpdateReturnBean bean) {
                arrayList.clear();
                imagurl.clear();
                btn_regist2.setAlpha(0.5f);
                btn_regist2.setClickable(false);
              ToastUtil.show(bean.getMsg());
              startActivity(new Intent(SharePicInActivity.this,H5PicLibActivty.class).putExtra("ispk",getIntent().getStringExtra("ispk")).putExtra("teampid",bean.getData()));
            }

           @Override
           public void onError(Throwable e) {
               super.onError(e);
               arrayList.clear();
               imagurl.clear();
           }
       });
    }

    public void submitchangecomment(String path){
        for(int i=0;i<desc.size();i++){
            h5SubmitBean=new  H5SubmitBean();
            h5SubmitBean.setContent(desc.get(i));
            if(i<orange.size()){
                h5SubmitBean.setUrl(orange.get(i));
            }else {

                h5SubmitBean.setUrl(changeimagurl.get(i-orange.size()));
            }

            arrayList.add(h5SubmitBean);
        }

        RestClient.getYfmNovate(SharePicInActivity.this).post(Constant.API.YFM_CHANGEH5SHARE, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("isPk", getIntent().getStringExtra("ispk"))
                .addParams("activityId", getIntent().getIntExtra("activityid",-1))
                .addParams("h5ShareImageList", new Gson().toJson(arrayList))
                .build(), new DmeycBaseSubscriber<H5UpdateReturnBean>() {
            @Override
            public void onSuccess(H5UpdateReturnBean bean) {
                arrayList.clear();
                changeimagurl.clear();
                btn_regist2.setAlpha(0.5f);
                btn_regist2.setClickable(false);
                ToastUtil.show(bean.getMsg());
                startActivity(new Intent(SharePicInActivity.this,H5PicLibActivty.class).putExtra("ispk",getIntent().getStringExtra("ispk")).putExtra("teampid",getH5PicBean.getData().getId()));
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                arrayList.clear();
                imagurl.clear();
            }
        });
    }

  private int ischange=0;
    GetH5PicBean getH5PicBean;
    public void getPicData(){
        RestClient.getYfmNovate(SharePicInActivity.this).get(Constant.API.YFM_GETH5, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activityId", getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<GetH5PicBean>() {
            @Override
            public void onSuccess(GetH5PicBean bean) {
//            ToastUtil.show(bean.getMsg());
                getH5PicBean=bean;
            for (int ii=0;ii<bean.getData().getH5ShareImageList().size();ii++){
                dataList.add(bean.getData().getH5ShareImageList().get(ii).getUrl());
                desc.add(bean.getData().getH5ShareImageList().get(ii).getContent());
            }
            if(bean.getData().getH5ShareImageList().size()>0){
                ischange=1;
            }
                recycleViewAdapter.notifyDataSetChanged();
//                gridImageAdapter = new GridImageAdapter(SharePicInActivity.this, dataList, loader, options,desc);
//                gridView.setAdapter(gridImageAdapter);
//                recycleViewAdapter  =new H5PicRecycleViewAdapter(SharePicInActivity.this, dataList, loader, options,desc);
//                rl_pic.setAdapter(recycleViewAdapter);
//                recycleViewAdapter.setOnDeletClickList(new H5PicRecycleViewAdapter.OnDeletClick() {
//                    @Override
//                    public void onDelet(int pos) {
//                        ToastUtil.show(pos+"ssss");
//                        Intent intent = new Intent(SharePicInActivity.this, ImageDelActivity.class);
//                        intent.putExtra("position", pos);
//                        intent.putExtra("path", dataList.get(pos));
//                        startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
//                    }
//
//                    @Override
//                    public void desc(String dec) {
//
//                    }
//
//                    @Override
//                    public void desc(ArrayList<String> dec) {
//
//                    }
//                });
//                gridImageAdapter.setOnDeletClickList(new GridImageAdapter.OnDeletClick() {
//                    @Override
//                    public void onDelet(int pos) {
//                        ToastUtil.show(pos+"ssss");
//                        Intent intent = new Intent(SharePicInActivity.this, ImageDelActivity.class);
//                        intent.putExtra("position", pos);
//                        intent.putExtra("path", dataList.get(pos));
//                        startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
//                    }
//
//                    @Override
//                    public void desc(String dec) {
//
//                    }
//
//                    @Override
//                    public void desc(ArrayList<String> dec) {
//
//                    }
//                });

            }
        });
    }

    public void h5submit(){
        recycleViewAdapter.cleardata(1);
        recycleViewAdapter.setOnDeletClickList(new H5PicRecycleViewAdapter.OnDeletClick() {
            @Override
            public void onDelet(int pos) {
            }
            @Override
            public void desc(String dec) {
            }
            @Override
            public void desc(ArrayList<String> dec) {
                if(dataList.size()!=dec.size()){
                    ToastUtil.show("描述文字不为空");
                    return;
                }else {
                    desc=dec;
                    upLoadeImage();
//                        }
                }
            }
        });
    }

    public void changeh5(){
        recycleViewAdapter.cleardata(1);
        recycleViewAdapter.setOnDeletClickList(new H5PicRecycleViewAdapter.OnDeletClick() {
            @Override
            public void onDelet(int pos) {
            }
            @Override
            public void desc(String dec) {
            }
            @Override
            public void desc(ArrayList<String> dec) {
                for (int i=0;i<dataList.size();i++){
                     if(dataList.get(i).startsWith("http")){
                         orange.add(dataList.get(i));
                     }else {
                         newurl.add(dataList.get(i)) ;
                     }
                }
                if(dataList.size()!=dataList.size()){
                    ToastUtil.show("描述文字不为空");
                    return;
                }else {
                    desc=dec;
                    changeupLoadeImage();
                }
            }
        });
    }
}
