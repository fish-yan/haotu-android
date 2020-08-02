package com.dmeyc.dmestoreyfm.newui.update;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 捕捉crash信息保存至本地
 * 
 * @author Jeanth.Bian
 * @date 2013-3-31
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {

	// 系统默认的UncaughtException处理类
	private UncaughtExceptionHandler mDefaultHandler;

	// CrashHandler实例
	private static CrashHandler instance;
	// 程序的Context对象
	private Context mContext;

	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH");

	@SuppressLint("SimpleDateFormat")
	private CrashHandler() {
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public synchronized static CrashHandler getIntance() {
		if (instance == null) {
			instance = new CrashHandler();
		}
		return instance;
	}

	public void init(Context context) {
		this.mContext = context;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		handleException(ex);
		mDefaultHandler.uncaughtException(thread, ex);// 系统默认异常处理器
	}

	/**
	 * 异常处理，收集异常信息
	 *
	 * @param ex
	 * @return
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return false;
		}

		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				//Toast.makeText(mContext, "很抱歉,程序出现异常,即将重启", Toast.LENGTH_SHORT).show();
				saveCrashLog2File(ex);
				Looper.loop();
			}
		}.start();



		return true;
	}

	@SuppressLint("SimpleDateFormat")
	private void saveCrashLog2File(Throwable ex) {

		StringBuffer crashBuffer = new StringBuffer();

		String time = formatter.format(new Date());
		String filename = "crash_" + time + ".log";

		String build = Build.BRAND;
		String mode = Build.MODEL;
		String release = Build.VERSION.RELEASE;

		//crashBuffer.append("================================================\n");
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
		crashBuffer.append(format.format(new Date()) + "\n");
		//crashBuffer.append("deviceInfo:bulid=" + build + ";mode=" + mode + ";release=" + release + "\n");

		try {
			/*PackageManager pm = mContext.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);

			if (pi != null) {
				String versionName = pi.versionName;
				crashBuffer.append("appInfo:versionName=" + versionName + "\n");
			}*/
		}
		catch (Exception e) {
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		crashBuffer.append(result);
		crashBuffer.append("\r\n");

		write(filename,crashBuffer.toString(),false);
		//System.out.println(crashBuffer.toString());

//		StaticLogUtils.onError(mContext,crashBuffer.toString());
		//write(filename, crashBuffer.toString(), true);
	}

	/**
	 * 写入文件
	 *
	 * @param fileName
	 * @param content
	 * @param append
	 *            是否追加写文件
	 */
	private void write(String fileName, String content, boolean append) {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + "/somall/logs", fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
			}
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
			out.write(content);
		}
		catch (Exception e) {
		}
		finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
					out = null;
				}
			}
			catch (IOException e) {
			}
		}
	}

	 public int sendMailByIntent() {
	        String[] reciver = new String[] { "182542931@qq.com" };
	        String[] mySbuject = new String[] { "shape_usercenter_message_text_bg" };
	        String myCc = "cc";
	        String mybody = "测试Email Intent";
	        Intent myIntent = new Intent(Intent.ACTION_SEND);
	        myIntent.setType("plain/text");
	        myIntent.putExtra(Intent.EXTRA_EMAIL, reciver);
	        myIntent.putExtra(Intent.EXTRA_CC, myCc);
	        myIntent.putExtra(Intent.EXTRA_SUBJECT, mySbuject);
	        myIntent.putExtra(Intent.EXTRA_TEXT, mybody);
	        mContext.startActivity(Intent.createChooser(myIntent, "mail shape_usercenter_message_text_bg"));
	  
	        return 1;  
	  
	    }  
}
