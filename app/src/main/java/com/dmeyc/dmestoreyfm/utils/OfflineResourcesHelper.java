package com.dmeyc.dmestoreyfm.utils;

import android.os.Environment;


public class OfflineResourcesHelper {

    public static final String FOLDER_PATH = "www";
    public static final String ZIP_FILE = "www.zip";

    private String basePath;
    private static OfflineResourcesHelper helper;
    private static boolean isInit = false;

    public static OfflineResourcesHelper getInstance() {
        if (helper == null) {
            synchronized (OfflineResourcesHelper.class) {
                if (helper == null) {
                    helper = new OfflineResourcesHelper();
                }
            }
        }
        return helper;
    }


    public boolean isInit() {
        return isInit;
    }

    public String getFilePath() {
        if (FileUtil.isFileExist(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data")) {
            return "/Android/data";
        } else {
            return "";
        }
    }
}