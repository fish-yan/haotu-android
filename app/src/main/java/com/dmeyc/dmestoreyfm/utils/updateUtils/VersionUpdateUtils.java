package com.dmeyc.dmestoreyfm.utils.updateUtils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.VersionUpBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版本更新工具类
 * Created by shadyY on 18/12/22.
 */
public class VersionUpdateUtils {

    private static VersionUpdateUtils mVersionUpdateUtils;
    private Activity context;
    private final String mApkName = "com.dmeyc.dmestore";
    private List<VersionUpBean.DataBean> vlist = new ArrayList();

    private VersionUpdateUtils(Activity context) {
        this.context = context;
    }

    public static VersionUpdateUtils getInstance(Activity context) {
//        if (mVersionUpdateUtils == null) {
        mVersionUpdateUtils = new VersionUpdateUtils(context);
//        }
        return mVersionUpdateUtils;
    }

//    public void checkVersion() {
//        final Map maps = new HashMap();
//        maps.put("type", 1);
//        RestClient.getNovate(context).get(Constant.API.VERSION_UP, maps, new DmeycBaseSubscriber<VersionUpBean>() {
//            @Override
//            public void onSuccess(VersionUpBean bean) {
//                vlist.add(bean.getData());
//                if (vlist != null && vlist.size() > 0) {
//                    int versionCode = PackageUtils.getVersionCode(context);
//                    if (versionCode <  vlist.get(0).getVersion()) {
//                        //显示普通升级对话框
////                        Log.i("checkVersion", "普通版本升级");
//                        if (!vlist.get(0).isIsForceUpdate()) {
//                            showUpdateDialog();
//                        } else {
//                            showMustUpdateDialog();
//                        }
//                    } else {
//                        return;
//                    }
//                }
//            }
//        });
//    }
    public void checkyfmVersion() {
//        final Map maps = new HashMap();
//        maps.put("type", 1);
//        maps.put("versionCode", PackageUtils.getVersionName(context));
        RestClient.getYfmNovate(context).post(Constant.API.YFMVERSION_UP, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("versionCode",PackageUtils.getVersionCode(context))
                .build(), new DmeycBaseSubscriber<VersionUpBean>() {
            @Override
            public void onSuccess(VersionUpBean bean) {
                if(bean.getData()!=null){
//                    vlist.add(bean.getData());
                    if ("0".equals(vlist.get(0).getVersionType())) {
                        showUpdateDialog();
                    } else {
                        showMustUpdateDialog();
                    }
                }
                }
            });

//        RestClient.getYfmNovate(context).post(Constant.API.YFMVERSION_UP, new ParamMap.Build().
//                        addParams("versionCode", PackageUtils.getVersionName(context))
//                        .build(),
//                new DmeycBaseSubscriber<VersionUpBean>() {
//            @Override
//            public void onSuccess(VersionUpBean bean) {
//                vlist.add(bean.getData());
//                if (vlist != null && vlist.size() > 0) {

//                }
//                    int versionCode = PackageUtils.getVersionCode(context);
//                    if (versionCode <  vlist.get(0).getVersion()) {
                        //显示普通升级对话框
//                        Log.i("checkVersion", "普通版本升级");
//                        if (!vlist.get(0).isIsForceUpdate()) {
//                            showUpdateDialog();
//                        } else {
//                            showMustUpdateDialog();
//                        }
//                    } else {
//                        return;
//                    }

//                }
//            }
//        });
    }
    /**
     * 普通升级dialog
     */
    public void showUpdateDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.genxin_dialog, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        TextView btnOk = (TextView) view.findViewById(R.id.btn_ok);
        TextView text_banben = (TextView) view.findViewById(R.id.text_banben);
        text_banben.setText(vlist.get(0).getVersionName());
        ImageView btnCancel = (ImageView) view.findViewById(R.id.btn_cancel);
        tvMessage.setText(Html.fromHtml(vlist.get(0).getVersionNote() + ""));
        btnOk.setText("确定");
        builder.setView(view);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(true);//设置点击back建能取消
        dialog.setCanceledOnTouchOutside(false);//触屏幕中dialog以外部分，不取消
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.alpha = 1.0f;
        dialogWindow.setAttributes(lp);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startDownloadApk();
            }
        });
        dialog.show();
    }

    /**
     * 强制升级dialog
     */
    public void showMustUpdateDialog() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.genxin_dialog, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        TextView btnOk = (TextView) view.findViewById(R.id.btn_ok);
        TextView text_banben = (TextView) view.findViewById(R.id.text_banben);
        text_banben.setText(vlist.get(0).getVersionName());
        ImageView btnCancel = (ImageView) view.findViewById(R.id.btn_cancel);
        btnCancel.setVisibility(View.GONE);
        tvMessage.setText(Html.fromHtml(vlist.get(0).getVersionNote() + ""));
        btnOk.setText("确定");
        builder.setView(view);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);//设置点击back建能取消
        dialog.setCanceledOnTouchOutside(false);//触屏幕中dialog以外部分，不取消
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.alpha = 1.0f;
        dialogWindow.setAttributes(lp);
        btnCancel.setVisibility(View.GONE);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startDownloadApk();
            }
        });
        dialog.show();
    }

    private String newVersionPath;

    private long startDownloadApk() {
        final DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(vlist.get(0).getUrl());
        if (uri != null && !uri.toString().equals("")) {//判断后台的apk地址是否存在
            DownloadManager.Request request = new DownloadManager.Request(uri);
            // 设置允许使用的网络类型，这里是移动网络和wifi都可以
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            newVersionPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.Config.DOWNLOAD_PATH + mApkName;
            File file = new File(newVersionPath);
            if (file.exists()) {
                file.delete();
            }

            final long downloadId;

            try {
                // 外部存储
                request.setDestinationInExternalPublicDir(Constant.Config.DOWNLOAD_PATH, mApkName);
                // 内部存储
                //request.setDestinationInExternalFilesDir(context,Const.DOWNLOAD_FILE,mApkName);
                //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);// 隐藏notification

                downloadId = downloadManager.enqueue(request);///返回下载的Id，可以根据id查询下载的状态，下载进度

//            if (map.get("UpdateRequired").equals("0")) {//强制更新时显示自定义进度对话框
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        queryDownloadProgress(downloadId, downloadManager);
                    }
                }).start();
//            }
                return downloadId;
            } catch (Exception e) {
                //e.printStackTrace();
                Log.i("VersionUpdateUtils", "e=" + e.getMessage());
                ToastUtil.showCenter("请您在设置—>权限管理中检查是否开启了读写手机存储等权限，如果没有开启，请开启后重新打开该应用!");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                    }
                }, 5000);
            }
        }
        return -1;
    }

    private void queryDownloadProgress(long requestId, DownloadManager downloadManager) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(requestId);
        try {
            boolean isGoging = true;
            while (isGoging) {
                Cursor cursor = downloadManager.query(query);
                if (cursor != null && cursor.moveToFirst()) {
                    int state = cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    switch (state) {
                        case DownloadManager.STATUS_SUCCESSFUL://下载成功
                            isGoging = false;
                            //发送信息到主线程，更新UI
                            handler.obtainMessage(DownloadManager.STATUS_SUCCESSFUL).sendToTarget();
                            break;
                        case DownloadManager.STATUS_FAILED://下载失败
                            isGoging = false;
                            //发送信息到主线程，更新UI
                            handler.obtainMessage(DownloadManager.STATUS_FAILED).sendToTarget();
                            break;
                        case DownloadManager.STATUS_RUNNING://下载中...
                            //计算下载进度
                            int totalSize = cursor.getInt(
                                    cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                            int currentSize = cursor.getInt(
                                    cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                            int progress = (int) (((float) currentSize) / ((float) totalSize) * 100);
                            handler.obtainMessage(DownloadManager.STATUS_RUNNING, progress).sendToTarget();
                            break;
                        case DownloadManager.STATUS_PAUSED://下载暂停
                            handler.obtainMessage(DownloadManager.STATUS_PAUSED).sendToTarget();
                            break;
                        case DownloadManager.STATUS_PENDING://准备下载
                            handler.obtainMessage(DownloadManager.STATUS_PENDING).sendToTarget();
                            break;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DownloadDialog downloadDialog;//下载进度对话框

    /**
     * 显示下载进度对话框
     */
    private void showDownloadDialog() {
        if (downloadDialog == null) {
            downloadDialog = new DownloadDialog(context);
        }
        if (!downloadDialog.isShowing()) {
            downloadDialog.show();
        }
    }

    /**
     * 取消下载进度对话框
     */
    private void cancleDownloadDialog() {
        if (downloadDialog != null && downloadDialog.isShowing()) {
            downloadDialog.dismiss();
        }
    }

    /**
     * 更新UI的handler
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case DownloadManager.STATUS_SUCCESSFUL://下载成功
                    downloadDialog.setProgress(100);
                    cancleDownloadDialog();
                    installApk();
                    break;
                case DownloadManager.STATUS_FAILED://下载失败
                    cancleDownloadDialog();
                    break;
                case DownloadManager.STATUS_RUNNING://下载中...
                    if (msg.obj != null) {
                        downloadDialog.setProgress((int) msg.obj);
                    }
                    break;
                case DownloadManager.STATUS_PAUSED://下载暂停

                    break;
                case DownloadManager.STATUS_PENDING://准备下载
                    showDownloadDialog();
                    break;

            }
            return false;
        }
    });

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(newVersionPath);
        if (!apkfile.exists()) {
            return;
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(context, "com.dmeyc.dmestoreyfm.fileprovider", apkfile);//在AndroidManifest中的android:authorities值
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
        } else {
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);


    }

}
