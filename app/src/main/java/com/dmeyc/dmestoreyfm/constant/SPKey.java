package com.dmeyc.dmestoreyfm.constant;

import com.dmeyc.dmestoreyfm.utils.SPUtils;

/**
 * create by cxg on 2019/11/29
 */
public class SPKey {
    public static final String TYPE_POLICY = "TYPE_POLICY";


    //搜索历史
    public static final String SEARCH_HISTORY = "SEARCH_HISTORY";
    //实名认证结果
    public static final String AUTHENTICATION_RESULT = "AUTHENTICATION_RESULT";

    public static String PUSH_COMMEND = "PUSH_COMMEND"+ SPUtils.getStringData(Constant.Config.USER_ID);
    public static String PUSH_LIKE = "PUSH_LIKE"+SPUtils.getStringData(Constant.Config.USER_ID);
    public static String PUSH_AT = "PUSH_AT"+SPUtils.getStringData(Constant.Config.USER_ID);
    public static String PUSH_FOLLOW = "PUSH_FOLLOW"+SPUtils.getStringData(Constant.Config.USER_ID);
    public static String PUSH_HAS_INFO = "PUSH_HAS_INFO"+SPUtils.getStringData(Constant.Config.USER_ID);
}
