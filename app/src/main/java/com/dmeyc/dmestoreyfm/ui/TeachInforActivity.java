package com.dmeyc.dmestoreyfm.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ProductPageAdapter;
import com.dmeyc.dmestoreyfm.adapter.photo.AlbumActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.CommonDefine;
import com.dmeyc.dmestoreyfm.adapter.photo.FileUtils;
import com.dmeyc.dmestoreyfm.adapter.photo.GridImageAdapter;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageDelActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageUtils;
import com.dmeyc.dmestoreyfm.adapter.photo.NoScrollGridView;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.GoodDetailBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.PlaceInfroBean;
import com.dmeyc.dmestoreyfm.bean.TeachDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CommDialog;
import com.dmeyc.dmestoreyfm.wedgit.CustomDialog;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.dmeyc.dmestoreyfm.wedgit.XLHRatingBar;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.tamic.novate.Throwable;
import com.youth.banner.Banner;

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


public class TeachInforActivity extends BaseActivity implements View.OnClickListener {
    private Banner banner;
//    private TextView tv_orang_price;
    private NoScrollListView lv_commentlist;
    private LinearLayout ll_course;
    @Bind(R.id.lv_courselist)
    NoScrollListView lv_courselist;

    @Bind(R.id.tv_teach_name)
    TextView tv_teach_name;
    @Bind(R.id.tv_intent)
    TextView tv_intent;
    @Bind(R.id.v_divede)
    View v_divede;
    @Bind(R.id.tv_comment)
    TextView tv_comment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_teachinfor;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        ll_course=(LinearLayout) findViewById(R.id.ll_course);
        ll_course.setOnClickListener(this);
        banner=(Banner) findViewById(R.id.banner);
        banner.setVisibility(View.VISIBLE);
//        tv_orang_price=(TextView) findViewById(R.id.tv_orang_price);

        setData();
        lv_commentlist=(NoScrollListView) findViewById(R.id.lv_commentlist);
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,width);
        banner.setLayoutParams(rl);
//        getPicData();

    }
//ArrayList<String>pic=new ArrayList<>();
    List<String> imgs = new ArrayList<>();
    List<String> tips = new ArrayList<>();
//    List<BeanDataType> bannerlist;
    TeachDetailBean teachDetailBean;
    private void setData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_TEACHDETAIL, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getStringExtra("groupid")).build(),
                new DmeycBaseSubscriber<TeachDetailBean>(this) {

            @Override
            public void onSuccess(final TeachDetailBean bean) {
                teachDetailBean=bean;
//                ToastUtil.show(bean.getMsg());
                if(bean.getData().getCourse_list().size()==0){
                    ll_course.setVisibility(View.GONE);
                    v_divede.setVisibility(View.GONE);
                }else {
                    v_divede.setVisibility(View.VISIBLE);
                    ll_course.setVisibility(View.VISIBLE);
                }
                tv_comment.setText("评价("+bean.getData().getComment_list().size()+")");
                imgs.clear();
                for (int i=0;i<bean.getData().getCoach_image_list().size();i++){
                    imgs.add(bean.getData().getCoach_image_list().get(i).getImage_url());
//                    imgs.add(url);
                    tips.add("");
                }
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(imgs);
                banner.start();

//                banner.setAdapter(new ProductPageAdapter(pic));
//                banner.setData(R.layout.view_image, imgs, tips);
                tv_teach_name.setText(bean.getData().getCoach_name());
                tv_intent.setText(bean.getData().getProject_type());

                lv_courselist.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getCourse_list().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }
                    ViewHolder viewHolder;
                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view= getLayoutInflater().inflate(R.layout.adapter_courselist,null);
                            viewHolder=new ViewHolder(view);
                            view.setTag(viewHolder);
                        }else {
                            viewHolder=(ViewHolder) view.getTag();
                        }
                        viewHolder.tv_orang_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
                        GlideUtil.loadImage(TeachInforActivity.this,bean.getData().getCourse_list().get(i).getGroup_logo(),viewHolder.iv_headteach);
                        viewHolder.tv_teach_name.setText(bean.getData().getCourse_list().get(i).getCourse_name());
                        viewHolder.tv_sellnum.setText("已售"+bean.getData().getCourse_list().get(i).getSold_count()+"件");
                        viewHolder.tv_price.setText(bean.getData().getCourse_list().get(i).getCourse_amount()+"");
                        viewHolder.tv_orang_price.setText(bean.getData().getCourse_list().get(i).getCourse_original_amount()+"");
                        return view;
                    }
                });

                lv_courselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        startActivity(new Intent(TeachInforActivity.this,CourseActivity.class).putExtra("course",bean.getData().getCourse_list().get(i).getCourse_id()));
                    }
                });

                lv_commentlist.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getComment_list().size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }
                    CommentViewHolder cvh;
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if(convertView==null){
                            convertView=  getLayoutInflater().inflate(R.layout.adapter_teachcomment,null);
                            cvh=new CommentViewHolder(convertView);
                            convertView.setTag(cvh);
                        }else {
                           cvh=(CommentViewHolder) convertView.getTag();
                        }
                   GlideUtil.loadImage(TeachInforActivity.this,bean.getData().getComment_list().get(position).getUser_logo(),cvh.iv_commicon);
                        cvh.tv_username.setText(bean.getData().getComment_list().get(position).getNick_name());
                        cvh.tv_commconten.setText(bean.getData().getComment_list().get(position).getContent());
                        cvh.tv_commtime.setText(bean.getData().getComment_list().get(position).getComment_time());
                        return convertView;
                    }
                });

            }
            @Override
            public void onError(Throwable e) {
//                mBaseView.requestDataError();
            }
        });
    }


    Dialog dialog;
    private NoScrollGridView noScrollGridView;
    protected ImageLoader loader;
    protected DisplayImageOptions options;
    private ArrayList<String> dataList;
    private GridImageAdapter gridImageAdapter;
    private Button btn_upload;
    private int uplodecount;
    private EditText et_backtext;
    private XLHRatingBar ratingBar;
    private int rationgcount;

    private CommDialog.Builder builder;
    private CommDialog mDialog;
    @OnClick({R.id.btn_login,R.id.ll_tocommdialog})
    public void onClick(View view) {
        int viewid=view.getId();
        if(R.id.ll_course==viewid){
   startActivity(new Intent(this,CourseActivity.class));
        }/*else if(viewid==R.id.btn_login){
            builder = new CommDialog.Builder(this);
            showTwoButtonDialog("提示\n确认拨打电话："+teachDetailBean.getData().getCoach_phone_no()+"吗?", "取消", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.dismiss();
                    //这里写自定义处理XXX
                }
            }, new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    //这里写自定义处理XXX
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + teachDetailBean.getData().getCoach_phone_no());
                        intent.setData(data);
                        startActivity(intent);

                }
            });


        }*/else if(R.id.ll_tocommdialog==viewid){

            dialog  = new Dialog(TeachInforActivity.this, R.style.MyDialog);
            //设置它的ContentView
            setFinishOnTouchOutside(false);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_comment);
            Window window = dialog.getWindow();

            WindowManager.LayoutParams lp = window.getAttributes();
            window.setGravity(Gravity.BOTTOM);
            WindowManager m = getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
            WindowManager.LayoutParams params = window.getAttributes();
            params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
            params.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.6
            window.setAttributes(params);
            ratingBar=  dialog.findViewById(R.id.ratingBar);
//            mRatingBar.setOnStarChangeListener(new RatingBar.OnStarChangeListener() {
//                @Override
//                public void OnStarChanged(float selectedNumber, int position) {
//                    // TODO 做相应的业务操作
//                }
//            });
            et_backtext=dialog.findViewById(R.id.et_backtext);
            noScrollGridView= dialog.findViewById(R.id.gridview_image);

            ratingBar.setOnRatingChangeListener(new XLHRatingBar.OnRatingChangeListener() {
                @Override
                public void onChange(int countSelected) {
                    rationgcount=countSelected;
//                    tvResult.setText(countSelected + "");
                }
            });

            options = new DisplayImageOptions.Builder()
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.drawable.pic_loading)
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .build();
            dataList = new ArrayList<String>();
            dataList.add("camera_default");
            gridImageAdapter = new GridImageAdapter(this, dataList, loader, options);
            noScrollGridView.setAdapter(gridImageAdapter);
            initListener();
            btn_upload=dialog.findViewById(R.id.btn_upload);
            btn_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(dataList.size()>0&&!dataList.get(0).equals("camera_default")){

                        for (int i=0;i<dataList.size();i++){
                            if(!dataList.get(i).equals("camera_default")){
                                upLodePic(dataList.get(i));
                                uplodecount++;
                            }else {
//                                ++size;
                            }
                        }

                    }else {
                        ToastUtil.show("请选择图片");
                    }

//                    submitcomment();
                }
            });
            dialog.show();

        }
    }


public class ViewHolder{
          TextView tv_orang_price,tv_teach_name,tv_sellnum,tv_price;
          ImageView iv_headteach;
        public ViewHolder(View view){
            tv_orang_price=(TextView) view.findViewById(R.id.tv_orang_price);
            iv_headteach=view.findViewById(R.id.iv_headteach);
            tv_teach_name=view.findViewById(R.id.tv_teach_name);
            tv_sellnum=view.findViewById(R.id.tv_sellnum);
            tv_price=view.findViewById(R.id.tv_price);
//            tv_orang_price=view.findViewById(R.id.tv_orang_price);
        }
}

    private void initListener() {

        noScrollGridView.setOnItemClickListener(new GridView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String path = dataList.get(position);
                if (path.contains("default") && position == dataList.size() -1 && dataList.size() -1 != 12) {
                    showSelectImageDialog();
                } else {
                    Intent intent = new Intent(TeachInforActivity.this, ImageDelActivity.class);
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

        StyledDialog.buildBottomItemDialog(TeachInforActivity.this, strings, "cancle",  new MyItemDialogListener() {
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

                    Intent intent1 = new Intent(TeachInforActivity.this, AlbumActivity.class);
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


public class CommentViewHolder{

        ImageView iv_commicon;
        TextView tv_username,tv_commconten,tv_commtime;
        public CommentViewHolder(View view){
            iv_commicon=view.findViewById(R.id.iv_commicon);
            tv_username=view.findViewById(R.id.tv_username);
            tv_commconten=view.findViewById(R.id.tv_commconten);
            tv_commtime=view.findViewById(R.id.tv_commtime);
        }
}
    private void showTwoButtonDialog(String alertText, String confirmText, String cancelText, View.OnClickListener conFirmListener, View.OnClickListener cancelListener) {
        mDialog = builder.setMessage(alertText)
                .setPositiveButton(confirmText, conFirmListener)
                .setNegativeButton(cancelText, cancelListener)
                .createTwoButtonDialog();
        mDialog.show();
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
                    gridImageAdapter.setdefoud(-1);
                    gridImageAdapter.notifyDataSetChanged();
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
                            dataList.add("camera_default");
                        }
                        gridImageAdapter.setdefoud(-1);
                        gridImageAdapter.notifyDataSetChanged();
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
                    gridImageAdapter.setdefoud(-1);
                    gridImageAdapter.notifyDataSetChanged();
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
                        if (dataList.size() < 12) {
                            dataList.add("camera_default");
                        }
                        gridImageAdapter.setdefoud(-1);
                        gridImageAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                .addFormDataPart("isLogo",1+"")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
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
//                flag++;

                filepath+=imap.getData()+",";
//                System.out.print(filepath+"11111111");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(dataList.contains("camera_default")&&dataList.size()-1==uplodecount){
                            uplodecount=0;
                            submitcomment(filepath);
                        }else if(dataList.size()==uplodecount){
                            uplodecount=0;
                            submitcomment(filepath);
                        }

//                        upLodeImages(filepath);
//                        if(type==1){
//                            for (int i=0;i<dataList.size();i++){
//                                if(!dataList.get(i).equals("camera_default")){
//                                    filepath=dataList.get(i)+",";
//                                    flag++;
//                                }
//
//                            }
//
//                        }
//                        if(flag==dataList.size()-size){
//                            System.out.print(flag+"11111111");
////                            ToastUtil.show(flag+"我走完");
//                            upLodeImages(filepath);
//                        }
                    }
                });
            }
        });
    }
    public void submitcomment(String path){

        if(TextUtils.isEmpty(et_backtext.getText().toString())){
            ToastUtil.show("请输入评价文字");
            return;
        }

        if(filepath.endsWith(",")){
            filepath=filepath.substring(0,filepath.length()-1);
        }

        RestClient.getYfmNovate(this).post(Constant.API.YFM_ADDPLACECOMMENT, new ParamMap.Build()
                        .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("venueId", getIntent().getStringExtra("venue_id"))
                        .addParams("commentScore",rationgcount)
                        .addParams("imgUrls",filepath)
                        .addParams("content", et_backtext.getText().toString()).build(),
                new DmeycBaseSubscriber<PlaceInfroBean>(this) {
                    @Override
                    public void onSuccess(PlaceInfroBean bean) {
                        ToastUtil.show(bean.getMsg());
                    }
                });
    }

}
