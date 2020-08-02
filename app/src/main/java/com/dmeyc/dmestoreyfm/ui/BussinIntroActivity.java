package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.AdapterCommIntro;
import com.dmeyc.dmestoreyfm.adapter.H5PicRecycleViewAdapter;
import com.dmeyc.dmestoreyfm.adapter.photo.AlbumActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.CommonDefine;
import com.dmeyc.dmestoreyfm.adapter.photo.FileUtils;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageDelActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageUtils;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.H5SubmitBean;
import com.dmeyc.dmestoreyfm.bean.H5UpdateReturnBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
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
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class BussinIntroActivity extends BaseActivity {
    @Bind(R.id.et_backtext)
    EditText et_backtext;

    private RecyclerView rl_pic;
    AdapterCommIntro recycleViewAdapter;

    protected ImageLoader loader;
    protected DisplayImageOptions options;
    private ArrayList<String> dataList;
    private ArrayList<String> desc;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bussintro;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
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
        dataList = new ArrayList<String>();
        desc = new ArrayList<String>();
//        dataList.add("camera_default");
        recycleViewAdapter  =new AdapterCommIntro(BussinIntroActivity.this, dataList, loader, options,desc);
        rl_pic.setAdapter(recycleViewAdapter);
        recycleViewAdapter.setOnDeletClickList(new AdapterCommIntro.OnDeletClick() {
            @Override
            public void onDelet(int position) {
                String path = dataList.get(position);
                if (path.contains("default") && position == dataList.size() -1 && dataList.size() -1 != 12) {
                    showSelectImageDialog();
                } else {
                    Intent intent = new Intent(BussinIntroActivity.this, ImageDelActivity.class);
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

//    private void initListener() {
//        noScrollGridView.setOnItemClickListener(new GridView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                String path = dataList.get(position);
//                if (path.contains("default") && position == dataList.size() -1 && dataList.size() -1 != 12) {
//                    showSelectImageDialog();
//                } else {
//                    Intent intent = new Intent(TeachInforActivity.this, ImageDelActivity.class);
//                    intent.putExtra("position", position);
//                    intent.putExtra("path", dataList.get(position));
//                    startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
//                }
//            }
//        });
//    }

    private Uri uri;
    // 閫夋嫨鐩稿唽锛岀浉鏈�
    private void showSelectImageDialog() {
        final List<String> strings = new ArrayList<>();
        strings.add("拍 照");
        strings.add("从手机相册选择");

        StyledDialog.buildBottomItemDialog(BussinIntroActivity.this, strings, "cancle",  new MyItemDialogListener() {
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
                    Intent intent1 = new Intent(BussinIntroActivity.this, AlbumActivity.class);
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
                    if(dataList.size() < 12) {
                        dataList.add("camera_default");
                    }
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
                        if (dataList.size() < 12) {
//                            dataList.add("camera_default");
                        }
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
@OnClick({R.id.btn_next,R.id.iv_right_title_bar})
    public void onClick(View view){
        int viweid=view.getId();
        if(viweid==R.id.btn_next){
            if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
                ToastUtil.show("请输入商户简介");
            }
            imagurl.clear();
            upLoadeImage();
//            submitIntro();
        }else if(R.id.iv_right_title_bar==viweid){
            showSelectImageDialog();
        }
     }
     public void submitIntro(){
         RestClient.getYfmNovate(BussinIntroActivity.this).post(Constant.API.YFM_USER_INFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
            @Override
            public void onSuccess(YFMUserBean bean) {

            }
         });
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

    public void submitcomment(String path){

Intent intent=new Intent();
intent.putExtra("backtext",et_backtext.getText().toString().trim());
intent.putStringArrayListExtra("photolist",imagurl);
setResult(999,intent);
finish();
//        for(int i=0;i<desc.size();i++){
//            h5SubmitBean=new H5SubmitBean();
//            h5SubmitBean.setContent(desc.get(i));
//            h5SubmitBean.setUrl(imagurl.get(i));
//            arrayList.add(h5SubmitBean);
//        }
//          Gson gson=new Gson();
//          json= gson.toJson(arrayList);
//        RestClient.getYfmNovate(BussinIntroActivity.this).post(Constant.API.YFM_SHARE_H5, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("isPk", getIntent().getStringExtra("ispk"))
//                .addParams("activityId", getIntent().getIntExtra("activityid",-1))
////                .addParams("h5ShareImageList", new Gson().toJson(arrayList))
//                .build(), new DmeycBaseSubscriber<H5UpdateReturnBean>() {
//            @Override
//            public void onSuccess(H5UpdateReturnBean bean) {
//                arrayList.clear();
//                imagurl.clear();
//                btn_regist2.setAlpha(0.5f);
//                btn_regist2.setClickable(false);
//                ToastUtil.show(bean.getMsg());
//                startActivity(new Intent(BussinIntroActivity.this,H5PicLibActivty.class).putExtra("ispk",getIntent().getStringExtra("ispk")).putExtra("teampid",bean.getData()));
            }

//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
////                arrayList.clear();
////                imagurl.clear();
//            }
//        });
//    }
}
