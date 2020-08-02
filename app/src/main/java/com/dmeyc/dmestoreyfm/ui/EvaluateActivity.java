package com.dmeyc.dmestoreyfm.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.EvaliateImagesBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.LoadingDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.look.section.addImgSection;
import com.dmeyc.dmestoreyfm.utils.FileUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.OfflineResourcesHelper;
import com.dmeyc.dmestoreyfm.utils.PicassoImageLoader;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.XPluginConstant;
import com.dmeyc.dmestoreyfm.wedgit.CountView;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.ResponseCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.ResponseBody;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class EvaluateActivity extends BaseActivity {

    @Bind(R.id.main_recyclerView)
    RecyclerView contentRecycler;
    @Bind(R.id.ed_content)
    EditText edContent;
    @Bind(R.id.ll_father_goods)
    LinearLayout ll_father_goods;
    @Bind(R.id.ll_father_sever)
    LinearLayout ll_father_sever;
    @Bind(R.id.ll_father_move)
    LinearLayout ll_father_move;
    @Bind(R.id.item_iv_cover)
    ImageView item_iv_cover;
    @Bind(R.id.item_tv_title)
    TextView item_tv_title;
    @Bind(R.id.item_countview)
    CountView item_countview;
    @Bind(R.id.item_tv_custom)
    TextView item_tv_custom;
    @Bind(R.id.item_priceview)
    PriceView item_priceview;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> successList = new ArrayList<>();
    private ArrayList<String> imagesList = new ArrayList<>();
    private ArrayList<String> camera_list = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private List<File> lastImage = new ArrayList<>();
    private boolean isFirst = true;//第一次加载数据
    private addImgSection imgSection;
    private static final int REQUEST_CODE = 12;
    private String camera_path;
    private String destPath;
    private LoadingDialog loadingDialog;
    private String reviewImage = "";
    private int goods_stars = 3;
    private int sever_stars = 3;
    private int move_stars = 3;
    private int goods;
    private int orderItemId;
    private int orderId;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_evaluate;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        EventBus.getDefault().register(this);
        tvTitle.setText("晒单");
        loadingDialog = new LoadingDialog(EvaluateActivity.this);
        initView();
        initRecyclerView();
        initLlGoods();
        initLlSever();
        initLlMove();
    }

    private void initView() {
        orderId = getIntent().getIntExtra("orderId", 0);
        orderItemId = getIntent().getIntExtra("orderItemId", 0);
        goods = getIntent().getIntExtra("goods", 0);
        GlideUtil.loadImage(EvaluateActivity.this, getIntent().getStringExtra("img"), item_iv_cover);
        item_tv_title.setText(getIntent().getStringExtra("title"));
        item_countview.setCount(getIntent().getIntExtra("quantity", 0));
        item_tv_custom.setText(getIntent().getStringExtra("size"));
        item_priceview.setPrice(Double.valueOf(getIntent().getStringExtra("price")));
    }

    private void initLlMove() {
        ll_father_move.removeAllViews();
        for (int i = 0; i < 5; i++) {
            final LinearLayout sonView = (LinearLayout) LayoutInflater.from(EvaluateActivity.this).inflate(
                    R.layout.son_stars, null);
            ImageView img_stars = sonView.findViewById(R.id.img_stars);
            if (i <= move_stars) {
                img_stars.setImageResource(R.drawable.grade_icon_grade_orange);
            } else {
                img_stars.setImageResource(R.drawable.grade_icon_grade_gray);
            }
            final int finalI = i;
            img_stars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    move_stars = finalI;
                    initLlMove();
                }
            });
            ll_father_move.addView(sonView);
        }
    }

    private void initLlSever() {
        ll_father_sever.removeAllViews();
        for (int i = 0; i < 5; i++) {
            final LinearLayout sonView = (LinearLayout) LayoutInflater.from(EvaluateActivity.this).inflate(
                    R.layout.son_stars, null);
            ImageView img_stars = sonView.findViewById(R.id.img_stars);
            if (i <= sever_stars) {
                img_stars.setImageResource(R.drawable.grade_icon_grade_orange);
            } else {
                img_stars.setImageResource(R.drawable.grade_icon_grade_gray);
            }
            final int finalI = i;
            img_stars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sever_stars = finalI;
                    initLlSever();
                }
            });
            ll_father_sever.addView(sonView);
        }
    }

    private void initLlGoods() {
        ll_father_goods.removeAllViews();
        for (int i = 0; i < 5; i++) {
            final LinearLayout sonView = (LinearLayout) LayoutInflater.from(EvaluateActivity.this).inflate(
                    R.layout.son_stars, null);
            ImageView img_stars = sonView.findViewById(R.id.img_stars);
            if (i <= goods_stars) {
                img_stars.setImageResource(R.drawable.grade_icon_grade_orange);
            } else {
                img_stars.setImageResource(R.drawable.grade_icon_grade_gray);
            }
            final int finalI = i;
            img_stars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goods_stars = finalI;
                    initLlGoods();
                }
            });
            ll_father_goods.addView(sonView);
        }
    }

    private void initRecyclerView() {
        GridLayoutManager myLayoutManager = new GridLayoutManager(this, 3);
        contentRecycler.setLayoutManager(myLayoutManager);
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        contentRecycler.setAdapter(sectionedRecyclerViewAdapter);
        initSection();
    }

    private void initSection() {
        list.clear();
        list.addAll(imagesList);
        if (isFirst) {
            imgSection = new addImgSection(EvaluateActivity.this, sectionedRecyclerViewAdapter, "isEvaluate");
            imgSection.setData(list);
        } else {
            sectionedRecyclerViewAdapter.removeAllSections();
            imgSection.setData(list);
        }
        imgSection.getstartActivityListener(new addImgSection.startActivity() {
            @Override
            public void CheckAllListener(Intent i, int code, String path) {
                startActivityForResult(i, code);
                camera_path = path;
            }
        });
        imgSection.getstartActivityListener_2(new addImgSection.startActivity_2() {
            @Override
            public void CheckAllListener_2(int i) {
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
                imagePicker.setShowCamera(false);  //显示拍照按钮
                imagePicker.setCrop(false);        //允许裁剪（单选才有效）
                imagePicker.setSaveRectangle(true); //是否按矩形区域保存
                imagePicker.setSelectLimit(i);    //选中数量限制
                Intent intent = new Intent(EvaluateActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        sectionedRecyclerViewAdapter.addSection(imgSection);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        images.clear();
        camera_list.clear();
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            for (int i = 0; i < imageItems.size(); i++) {
                images.add(imageItems.get(i).path);
            }
        } else if (requestCode == XPluginConstant.REQ_CAMERA) {
            if (resultCode == RESULT_OK) {
                if (!"".equals(camera_path)) {
                    camera_list.add(camera_path);
                }
            }
        }
        imagesList.addAll(camera_list);
        imagesList.addAll(images);
        isFirst = false;

        destPath = Environment.getExternalStorageDirectory().getAbsolutePath() + OfflineResourcesHelper.getInstance().getFilePath() + "/" + OfflineResourcesHelper.FOLDER_PATH;
        new File(destPath).mkdirs();
//            启用luban压缩
        Luban.with(this)
                .load(imagesList)
                .setTargetDir(destPath)
                .ignoreBy(100)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        if (loadingDialog != null) {
                            if (loadingDialog.isShowing()) {
                                loadingDialog.dismiss();
                            }
                            loadingDialog.show();
                        }
                    }

                    @Override
                    public void onSuccess(File file) {
                        /**
                         * 压缩图片成功上传压缩图片
                         */
//                        uploadMap.put(file.toString(), file);
                        successList.add(file.getAbsolutePath());
                        lastImage.add(file);
                        if (imagesList.size() == successList.size()) {
                            if (loadingDialog != null && loadingDialog.isShowing()) {
                                loadingDialog.dismiss();
                            }
                            imagesList.clear();
                            imagesList.addAll(successList);
                            successList.clear();
                            initSection();
                        }
                    }

                    @Override
                    public void onError(java.lang.Throwable e) {
                        /**
                         * 压缩失败上传原图
                         */
                        if (loadingDialog != null && loadingDialog.isShowing()) {
                            loadingDialog.dismiss();
                        }
                        Toast.makeText(EvaluateActivity.this, "压缩失败,请清理内存后重试", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }).launch();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upDataUI(List<String> list) {
        imagesList.clear();
        imagesList.addAll(list);
        initSection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        FileUtil.delete(destPath);
    }


    @OnClick({R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if(lastImage.size() == 0){
                    submit();
                    return;
                }
                if (loadingDialog != null) {
                    if (loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                    loadingDialog.show();
                }
                RestClient.getNovate(this).rxUploadWithPartListByFile(Constant.API.UPLOAD_REVIEWIMAGE, lastImage, new ResponseCallback<Object, ResponseBody>() {
                    @Override
                    public Object onHandleResponse(ResponseBody response) throws Exception {
                        JSONObject object = new JSONObject(response.string());
                        return object;
                    }

                    @Override
                    public void onError(Object tag, Throwable e) {

                    }

                    @Override
                    public void onCancel(Object tag, Throwable e) {

                    }

                    @Override
                    public void onNext(Object tag, Call call, Object response) {
                        if (loadingDialog != null && loadingDialog.isShowing()) {
                            loadingDialog.dismiss();
                        }
                        Gson gson = new Gson();
                        EvaliateImagesBean evaliateImagesBean = gson.fromJson(response.toString(), EvaliateImagesBean.class);
                        List<EvaliateImagesBean.DataBean> data = evaliateImagesBean.getData();
                        try {
                             reviewImage = URLEncoder.encode(gson.toJson(data), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        data.clear();
                        submit();
                    }
                });
                break;
        }
    }

    private void submit() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("member", SPUtils.getStringData(Constant.Config.USER_ID, ""));
        map.put("orderId", orderId);
        map.put("orderItemId", orderItemId);
        map.put("goods", goods);
        map.put("content", edContent.getText().toString());
        if(!reviewImage.equals("")){
            map.put("reviewImageA", reviewImage);
        }
        map.put("goodsScore", goods_stars + 1);
        map.put("logisticsScore", move_stars + 1);
        map.put("serviceScore", sever_stars + 1);
        RestClient.getNovate(EvaluateActivity.this).get(Constant.API.REVIEW_ORDER, map, new DmeycBaseSubscriber<Object>() {
            @Override
            public void onSuccess(Object bean) {
               ToastUtil.show("操作成功");
               finish();
            }
        });
    }
}
