package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import dalvik.system.BaseDexClassLoader;

/**
 * 初始化配置信息 用户数据加载及统计信息采集
 */
public class InitUtils {
    private static String TAG = "INIT";
    public static String UA = null;
    public static String IMEI = null;


    public static String deviceId=null;
    public static String getIMEI(Context context) {
        if(deviceId!=null)
            return deviceId;
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        try {
            String s = tm.getDeviceId();
            //s="";//for shape_usercenter_message_text_bg
            if (s == null) {
                return deviceId=createMUUID();
            } else {
                String ret= s.trim();

                if(ret.equals("")){
                    return deviceId=createMUUID();
                }

                if(ret.equals("000000000000000")){
                    return deviceId=createMUUID();
                }
                return deviceId=ret;
            }
        } catch (SecurityException e) {
            return deviceId=createMUUID();
        }
    }

    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context application) {
        WindowManager wm = (WindowManager) application
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }


    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getIpAddress(Context context) {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        if ("0.0.0.0".equals(ip)) {
            ip = getLocalIpAddress();
        }
        return ip;
    }

    public static String intToIp(int i) {

        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

    public static String getSdCardPath(Context context) {
        File path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory();
        } else {
            path = context.getCacheDir();
        }
        File newPath = new File(path, "crazy_Teacher");
        if (!newPath.exists()) {
            newPath.mkdir();
        }
        return newPath.toString();
    }

    /**
     * 获得本地的ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("IpAddress", ex.toString());
        }
        return null;
    }

    /***
     * this method is deprecated please call BitmapUtil.replaceURL3
     * @param url
     * @param w
     * @param h
     * @return
     */
    @Deprecated
    public static final String getResizeImageUrl(String url, int w, int h) {

        /*if (TextUtils.isEmpty(url)) {
            throw new NullPointerException();
        }

        if (!url.contains("?")) {
            url = url + "?";
        }*/
        return url + "imageView/3/w/" + w + "/h/" + h;
     //   return BitmapUtil.replaceURL3(url, w, h);
    }

    /**
     * 判断字符串是否为全是数字
     *
     * @param phone
     * @return
     */
    public static final boolean isNumeric(String phone) {
        return phone.matches("\\d*");
    }

    /**
     * 获取初始化信息 注意：初始化信息项有可变性
     *
     * @return
     */
    public static String initUA(Context context) {
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("{");
            // 1.品牌HTC
            String brand = Build.BRAND;
            buffer.append("BRAND:" + brand + ",");

            // 2.手机型号 HTC Desire
            String model = Build.MODEL;
            buffer.append("MODEL:" + model + ",");

            // 3.os版本（如android）
            buffer.append("OSVERSION:Android,");

            // 4.固件版本（如2.2）
            String release = Build.VERSION.RELEASE;
            buffer.append("RELEASE:" + release + ",");

            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
                // IMSI:国际移动用户识别码
                String IMSI = tm.getSubscriberId();
                buffer.append("IMSI:" + IMSI + ",");

                // 获取手机号
                String phoneNum = tm.getLine1Number();
                buffer.append("Line1Number:" + phoneNum + ",");

                // IMEI:手机串号
                String _IMEI = tm.getSimSerialNumber();
                buffer.append("IMEI:" + _IMEI + ",");
                IMEI = _IMEI;

                // 取得SIM卡驱动的ID号
                String deviceid = tm.getDeviceId();
                buffer.append("DeviceId:" + deviceid + ",");

                // 取得SIM状态信息
                String simstate = getSimState(tm);
                buffer.append("SimState:" + simstate + ",");

                // 取得当前使用的网络类型：
                String networkType = getNetworkType(tm);
                buffer.append("NetWorkType:" + networkType + ",");

                // 取得信号类型
                String phoneType = getPhoneType(tm);
                buffer.append("PhoneType:" + phoneType + ",");

                // 取得网络国别
                String networkCountry = tm.getNetworkCountryIso();
                buffer.append("NetworkCountryIso:" + networkCountry + ",");

                // 取得网络运营商代码
                String networkOperator = tm.getNetworkOperator();
                buffer.append("NetworkOperator:" + networkOperator + ",");

                // 取得网络运营商名称
                String networkOpName = tm.getNetworkOperatorName();
                buffer.append("NetworkOperatorName:" + networkOpName + ",");

                // 取得SIM卡国别
                String simCountry = tm.getSimCountryIso();
                buffer.append("SimCountryIso:" + simCountry + ",");

                // 取得SIM卡供货商代码
                String simOperatorCode = tm.getSimOperator();
                buffer.append("SimOperator:" + simOperatorCode + ",");

                // 取得SIM卡供货商名称
                String simOperatorName = tm.getSimOperatorName();
                if (simOperatorName == null || simOperatorName.equals("")) {
                    simOperatorName = null;
                }
                buffer.append("SimOperatorName:" + simOperatorName);

            }

            buffer.append("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UA = buffer.toString();
        return UA;
    }

    /**
     * 获取SIM状态
     *
     * @param tm
     * @return
     */
    public static String getSimState(TelephonyManager tm) {
        int simState = tm.getSimState();

        String state = null;
        if (simState == TelephonyManager.SIM_STATE_READY) {
            state = "良好";
        } else if (simState == TelephonyManager.SIM_STATE_ABSENT) {
            state = "无SIM卡";
        } else if (simState == TelephonyManager.SIM_STATE_NETWORK_LOCKED) {
            state = "需要NetWork PIN 解锁";
        } else if (simState == TelephonyManager.SIM_STATE_PIN_REQUIRED) {
            state = "需要SIM卡的PIN解锁";
        } else if (simState == TelephonyManager.SIM_STATE_PUK_REQUIRED) {
            state = "需要SIM卡的PUK解锁";
        } else if (simState == TelephonyManager.SIM_STATE_UNKNOWN) {
            state = "SIM卡状态未知";
        }

        // state = "{simstate:"+state+"}";
        return state;
    }

    /**
     * 匹配网络类型
     *
     * @param tm
     * @return
     */
    public static String getNetworkType(TelephonyManager tm) {
        int networkType = tm.getNetworkType();
        String type = null;
        if (networkType == TelephonyManager.NETWORK_TYPE_GPRS) {
            type = "NETWORK_TYPE_GPRS";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_EDGE) {
            type = "NETWORK_TYPE_EDGE";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_UMTS) {
            type = "NETWORK_TYPE_UMTS";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSDPA) {
            type = "NETWORK_TYPE_HSDPA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSUPA) {
            type = "NETWORK_TYPE_HSUPA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_HSPA) {
            type = "NETWORK_TYPE_HSPA";
        } else if (networkType == TelephonyManager.NETWORK_TYPE_CDMA) {
            type = "NETWORK_TYPE_CDMA";
        } else {
            type = "EVDO | 1xRTT";
        }

        return type;
    }

    public static String getApplicationName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取手机信号类型
     *
     * @param tm
     * @return
     */
    public static String getPhoneType(TelephonyManager tm) {
        int phoneType = tm.getPhoneType();
        String type = null;

        if (phoneType == TelephonyManager.PHONE_TYPE_GSM) {
            type = "PHONE_TYPE_GSM";
        } else if (phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
            type = "PHONE_TYPE_CDMA";
        } else {
            type = "PHONE_TYPE_NONE";// 无信号
        }

        return type;
    }

    /**
     * 生成唯一标识
     *
     * @return
     */
    public static String createMUUID() {
//		UUID uuid = UUID.randomUUID();
//		LogUtils.i(TAG, "Random UUID String " + uuid.toString());
//		LogUtils.i(TAG, "UUID version  = " + uuid.version());
//		LogUtils.i(TAG, "UUID variant       = " + uuid.variant());
//		return uuid.toString();
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + "/crazyteacher/logs", "uuid.dat");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //没有文件 先创建并写入uuid
        if (!file.exists()) {
            try {
                file.createNewFile();
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(file, false)));
                    UUID uuid = UUID.randomUUID();
                    out.write(uuid.toString());
                } catch (Exception e) {
                } finally {
                    try {
                        if (out != null) {
                            out.flush();
                            out.close();
                            out = null;
                        }
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e) {
            }
        }

        // 从文件读取
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            try {
                line = br.readLine();
                if (line == null) {
                    UUID uuid = UUID.randomUUID();
                    return uuid.toString();
                } else {
                    return line.trim();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String createRandomUUID(){
        return UUID.randomUUID().toString();
    }

    public static boolean isSDCardAvailable() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static void onCall(Context ctx, String phonenum) {
        Uri uri = Uri.parse("tel:" + phonenum);
        Intent call = new Intent(Intent.ACTION_DIAL, uri);
        ctx.startActivity(call);
    }

    public static String getPhotoPath() {
        File dir = new File(Environment.getExternalStorageDirectory(),
                "/sdp_mpos/photo");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

    public static void deletePhoto(String path, String name) {
        File file = new File(path, name);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * 获取CPU核心数
     *
     * @return
     */
    public static int getCPUNumCores() {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                // Check if filename is "cpu", followed by a single digit number
                return Pattern.matches("cpu[0-9]", pathname.getName());
            }
        }

        try {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            // Default to return 1 core
            return 1;
        }
    }

    public static int getCurrentOsVersionCode() {
        /*
		 *
		 * Build.VERSION_CODES 1 (0x00000001) Android 1.0 BASE 2 (0x00000002)
		 * Android 1.1 BASE_1_1 3 (0x00000003) Android 1.5 CUPCAKE 4
		 * (0x00000004) Android 1.6 DONUT 5 (0x00000005) Android 2.0 ECLAIR 6
		 * (0x00000006) Android 2.0.1 ECLAIR_0_1 7 (0x00000007) Android 2.1
		 * ECLAIR_MR1 8 (0x00000008) Android 2.2 FROYO 9 (0x00000009) Android
		 * 2.3 GINGERBREAD 10 (0x0000000a) Android 2.3.3 GINGERBREAD_MR1 11
		 * (0x0000000b) Android 3.0 HONEYCOMB 12 (0x0000000c) Android 3.1
		 * HONEYCOMB_MR1 13 (0x0000000d) Android 3.2 HONEYCOMB_MR2
		 */
		/* 获取当前系统的android版本号 */
        return Build.VERSION.SDK_INT;
    }

    public static void setDialogTypeToast(Window window) {
        if(InitUtils.getCurrentOsVersionCode() >= Build.VERSION_CODES.KITKAT) {
            window.setType(WindowManager.LayoutParams.TYPE_TOAST);
        }/*else{
            window.setType(WindowManager.LayoutParams.TYPE_PHONE);
        }*/
    }

    public static boolean isActivityRunning(Context context, Class cls) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        for (int i = 0; i < list.size(); i++) {
            ComponentName cn = list.get(i).baseActivity;
            if (cls.toString().contains(cn.getClassName()))
                return true;
        }
        return false;
    }

    public static boolean isTopRunning(Context context, Class cls) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        for (int i = 0; i < list.size(); i++) {
            ComponentName cn = list.get(i).topActivity;
            if (cls.toString().contains(cn.getClassName()))
                return true;
        }
        return false;
    }

    /**
     * 获得当前的版本
     *
     * @param context
     * @return
     */
    public static int getVersion(Context context) {
        int version = 0;
        try {
            String name = context.getPackageName();
            String verName = context.getPackageManager()
                    .getPackageInfo(name, 0).versionName;
            String v = "0";
            if (!TextUtils.isEmpty(verName)) {
                v = verName.replace(".", "");
            }
            version = Integer.parseInt(v);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获得版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int verCode = -1;
        try {
            String name = context.getPackageName();
            verCode = context.getPackageManager().getPackageInfo(name, 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }


    public static String getVersionWithDot(Context context) {
            String name = context.getPackageName();
        String verName = null;
        try {
            verName = context.getPackageManager()
                    .getPackageInfo(name, 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
            /*if (!StringUtil.isEmpty(verName)) {
                v = verName.replace(".", "");
            }*/

        return verName;
    }

    /**
     * 求俩个数的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static long greatestCommon(long a, long b) {
        if (a < b) {
            long temp;
            temp = a;
            a = b;
            b = temp;
        }
        if (0 == b) {
            return a;
        }
        return greatestCommon(b, a % b);
    }

    /**
     * 获得当前的版本
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            String name = context.getPackageName();
            verName = context.getPackageManager().getPackageInfo(name, 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    public static String getChannel(Context context) {
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString("UMENG_CHANNEL");
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTopActivity(Context context)
    {
        ActivityManager manager = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE) ;
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1) ;
        if(runningTaskInfos != null)
            return (runningTaskInfos.get(0).topActivity).toString() ;
        else
            return null ;
    }


//    public static String getAppDexPathList(){
//       BaseDexClassLoader aLoder = (BaseDexClassLoader) Luffy.getApplication().getClassLoader();
//
//        try {
//            Class loderCls= Class.forName("dalvik.system.BaseDexClassLoader");
//            Field f= loderCls.getDeclaredField("pathList");
//            f.setAccessible(true);
//            Object list=f.get(aLoder);
//            return list.toString();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 获取设备宽度（px）
     *
     * @param context
     * @return
     */
    public static int deviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     *
     * @param context
     * @return
     */
    public static int deviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneOS() {
        return "Android";
    }



    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前App进程的id
     *
     * @return
     */
    public static int getAppProcessId() {
        return android.os.Process.myPid();
    }

    /**
     * 获取当前App进程的Name
     *
     * @param context
     * @param processId
     * @return
     */
    public static String getAppProcessName(Context context, int processId) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取所有运行App的进程集合
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == processId) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));

                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.e(InitUtils.class.getName(), e.getMessage(), e);
            }
        }
        return processName;
    }


    /**
     * 获取AndroidManifest.xml里 的值
     * @param context
     * @param name
     * @return
     */
    public static String getMetaData(Context context, String name) {
        String value = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


}
