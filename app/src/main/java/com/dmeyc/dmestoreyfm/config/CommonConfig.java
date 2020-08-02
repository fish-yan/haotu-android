package com.dmeyc.dmestoreyfm.config;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

/**
 * create by cxg on 2019/12/13
 */
public class CommonConfig {


    public static final String ROLE_TEST = "-1";//测试，全部都可以看到
    public static final String ROLE_NORMAL_UN_LOGIN = "0";//普通用户未登入
    public static final String ROLE_NORMAL = "1";//普通用户登入
    public static final String ROLE_GROUP = "2";//群主
    public static final String ROLE_COCAH = "3";//教练
    public static final String ROLE_MERCHANT = "4";//商户
    public static final String ROLE_ANCHOR = "5";//主播



    /**
     * 1：我关注的，2：粉丝，3:相互关注
     *
     * @param status
     * @return
     */
    public static boolean isFollow(String status) {
        return "1".equals(status) || "3".equals(status);
    }

    public static boolean isCanWithDraw() {
        return ROLE_GROUP.equals(SPUtils.getStringData(Constant.Config.ROLECODE));
    }

    public static boolean isGroupOwner(String type){
        return "1".equals(type);
    }
}
