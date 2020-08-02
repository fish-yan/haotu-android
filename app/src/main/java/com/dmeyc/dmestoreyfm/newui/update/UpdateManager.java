package com.dmeyc.dmestoreyfm.newui.update;

import android.Manifest;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.permission.PermissionListener;
import com.dmeyc.dmestoreyfm.newui.permission.PermissionUtils;
import com.dmeyc.dmestoreyfm.utils.FileUtil;
import com.dmeyc.dmestoreyfm.utils.city.ToastUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateManager {

	private Context mContext;

	// 安装包下载地址
	private String apkUrl = "";

	private Dialog downloadDialog;
	/* 下载包安装路径 */
	private static String saveApkPath;
	private static String saveApkFileName;
	/* 进度条与通知ui刷新的handler和msg常量 */
	private ProgressBar mProgress;
	private static final int DOWN_UPDATE = 1001;
	private static final int DOWN_OVER = 1002;
	private int progress;
	private Thread downLoadThread;
	private boolean interceptFlag = false;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				installApk();
				break;
			default:
				break;
			}
		};
	};
	public UpdateManager(Context context){
		this.mContext = context;
		saveApkPath = FileUtil.getSDCardPath();
		saveApkFileName = saveApkPath + "/haotu.apk";
	}

	private UpdateResultBean.DataBean mResult;

	/**
	 * 判断是否需要更新，是哪一种更新
	 * @param result
	 */
	public void showUpdateDialog(UpdateResultBean.DataBean result) {
		mResult = result;
		// 检查更新，若有更新则先弹框提示
			apkUrl = result.getUrl();
			// 建议更新
			if ("0".equals(result.getVersionType())) {
				showSuggestUpdateDialog();
			}  
			// 必须更新
			else if ("1".equals(result.getVersionType())){
				showMustUpdateDialog();
			}
	}

	/**
	 * 建议更新
	 */
	private void showSuggestUpdateDialog() {
		YesCancelDialog.Builder builder = new YesCancelDialog.Builder(mContext);
		builder.setMessage(mResult.getVersionNote());
		// builder.setTitle("提示");
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
				final PermissionUtils permissionUtils = new PermissionUtils((FragmentActivity) mContext);
				if (permissionUtils.isPermission(permission)) {
					// 设置你的操作事项
					dialog.dismiss();
					showDownloadDialog(true);
				}else{
					permissionUtils.requestPermissions(permission, new PermissionListener() {
						@Override
						public void onGranted(int requestCode, String grantedPermission, int grantResults) {
							// 设置你的操作事项
							showDownloadDialog(true);
						}

						@Override
						public void onDenied(int requestCode, String deniedPermission, int grantResults) {
							//拒絕
							ToastUtils.showToast(mContext,"获取必要的权限才能下载更新哦~");
							showUpdateDialog(mResult);
						}

						@Override
						public void onNoPrompt(int requestCode, String deniedPermission, int grantResults) {
							ToastUtils.showToast(mContext,"请前往手机设置开启存储权限~");
						}
					});
				}
			}
		});

		builder.setNegativeButton("下次再说",
				new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		YesCancelDialog ycd = builder.create();
		ycd.setCanceledOnTouchOutside(false);
		//ycd.setCancelable(false);
		ycd.show();
	}

	/**
	 * 必须更新
	 */
	private void showMustUpdateDialog() {
		YesDialog.Builder builder = new YesDialog.Builder(mContext);
		builder.setMessage(mResult.getVersionNote());
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
				final PermissionUtils permissionUtils = new PermissionUtils((FragmentActivity) mContext);
				if (permissionUtils.isPermission(permission)) {
					// 设置你的操作事项
					dialog.dismiss();
					showDownloadDialog(false);
				}else{
					permissionUtils.requestPermissions(permission, new PermissionListener() {
						@Override
						public void onGranted(int requestCode, String grantedPermission, int grantResults) {
							// 设置你的操作事项
							showDownloadDialog(false);
						}

						@Override
						public void onDenied(int requestCode, String deniedPermission, int grantResults) {
							//拒絕
							ToastUtils.showToast(mContext,"获取必要的权限才能下载更新哦~");
							showUpdateDialog(mResult);
						}

						@Override
						public void onNoPrompt(int requestCode, String deniedPermission, int grantResults) {
							ToastUtils.showToast(mContext,"请前往手机设置开启存储权限~");
						}
					});
				}
			}
		});
		YesDialog yd = builder.create();
		yd.setCanceledOnTouchOutside(false);
		yd.setCancelable(false);
		yd.show();
	}

	/**
	 * 下载中dialog
	 * @param canIntercept
	 * 下载过程中是否可以中途取消
	 */
	private void showDownloadDialog(final boolean canIntercept) {
		Builder builder = new Builder(mContext);
		builder.setTitle("下载中......");
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.progress_bar, null);
		mProgress = (ProgressBar) v.findViewById(R.id.progress);
		builder.setView(v);
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				interceptFlag = true;
				if (!canIntercept) {
					CrashHandler.getIntance().uncaughtException(null,
					new Exception("版本过低，请及时更新"));
				}
			}
		});
		downloadDialog = builder.create();
		downloadDialog.setCanceledOnTouchOutside(false);
		downloadDialog.setCancelable(false);
		downloadDialog.show();
		downloadApk();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestProperty("Accept-Encoding", "identity");
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				File file = new File(saveApkPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String apkFile = saveApkFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);
				int count = 0;
				byte buf[] = new byte[1024];
				do {
					int numread = is.read(buf);
					count += numread;
					progress = ((count * 1.0 / length) > 0.99) ? 100
							: (int) ((count * 1.0 / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载.
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 下载apk
	 * @param
	 */
	private void downloadApk(){
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}
	/**
	 * 安装apk
	 */
	private void installApk(){
		File apkfile = new File(saveApkFileName);
		if (!apkfile.exists()){
			return;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		//判断是否是AndroidN以及更高的版本
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri contentUri = FileProvider.getUriForFile(mContext,  "com.dmeyc.dmestoreyfm.fileprovider", apkfile);
			intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		} else {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.parse("file://" + apkfile.toString()),
					"application/vnd.android.package-archive");
		}
		mContext.startActivity(intent);
	}
}
