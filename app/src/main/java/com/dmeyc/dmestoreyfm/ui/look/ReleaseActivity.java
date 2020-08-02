package com.dmeyc.dmestoreyfm.ui.look;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.look.section.addImgSection;
import com.dmeyc.dmestoreyfm.utils.FileUtil;
import com.dmeyc.dmestoreyfm.utils.OfflineResourcesHelper;
import com.dmeyc.dmestoreyfm.utils.PicassoImageLoader;
import com.dmeyc.dmestoreyfm.utils.XPluginConstant;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.ResponseCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ReleaseActivity extends BaseActivity {

    @Bind(R.id.main_recyclerView)
    RecyclerView contentRecycler;
    @Bind(R.id.ed_content)
    EditText edContent;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> successList = new ArrayList<>();
    private ArrayList<String> imagesList = new ArrayList<>();
    private ArrayList<String> camera_list = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private List<File> lastImage = new ArrayList<>();
    private Map<String, RequestBody> bulider = new HashMap<>();
    private boolean isFirst = true;//第一次加载数据
    private addImgSection imgSection;
    private static final int REQUEST_CODE = 12;
    private String camera_path;
    private String destPath;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_release;
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
        tvTitle.setText("发布");
        initRecyclerView();
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
            imgSection = new addImgSection(ReleaseActivity.this, sectionedRecyclerViewAdapter,"");
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
                Intent intent = new Intent(ReleaseActivity.this, ImageGridActivity.class);
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
                        Toast.makeText(ReleaseActivity.this, "压缩失败请重试", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_upload)
    public void onViewClicked() {
//        HashMap<String, RequestBody> map = new HashMap<>();
//        map.put("images", imagesList);
//        map.put("name", SPUtils.getStringData(Constant.Config.NICK_NAME, ""));
//        map.put("avatar", SPUtils.getStringData(Constant.Config.AVATAR, ""));
//        map.put("member", 67);
//        map.put("introduction", edContent.getText().toString());
//        map.put("goods", "");
        RestClient.getNovate(this).rxUploadWithPartListByFile(Constant.API.UPLOAD_REVIEWIMAGE, lastImage, new ResponseCallback<Object, ResponseBody>() {
            @Override
            public Object onHandleResponse(ResponseBody response) throws Exception {
                return response;
            }

            @Override
            public void onError(Object tag, Throwable e) {

            }

            @Override
            public void onCancel(Object tag, Throwable e) {

            }

            @Override
            public void onNext(Object tag, Call call, Object response) {

            }
        });
    }
}
