package com.dmeyc.dmestoreyfm.config;

import android.content.Context;

import com.dmeyc.dmestoreyfm.bean.ReleaseItemBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.common.container.ContainerActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.authentication.AuthenticationActivity;
import com.dmeyc.dmestoreyfm.newui.release.action.ReleaseActionActivity;
import com.dmeyc.dmestoreyfm.newui.release.course.ReleaseCourseActivity;
import com.dmeyc.dmestoreyfm.newui.release.goods.ReleaseGoodsActivity;
import com.dmeyc.dmestoreyfm.newui.release.match.ReleaseMatchActivity;
import com.dmeyc.dmestoreyfm.newui.release.video.ReleaseVideoActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by cxg on 2019/11/30
 * 首页发布配置
 */
public class ReleaseConfig {


    public enum Type {
        VIDEO,//视频
        IMAGE,//图片
        MATCH,//赛事
        ACTION,//活动
        LIVE,//直播
        GOODS,//商品
        COURSE//课程
    }

    private static Type NEED_AUTH[] = {
            Type.MATCH,
            Type.ACTION,
            Type.LIVE,
            Type.GOODS,
            Type.COURSE};

    private static final Type[] TYPES = {
            Type.VIDEO,
            Type.IMAGE,
            Type.MATCH,
            Type.ACTION,
            Type.LIVE,
            Type.GOODS,
            Type.COURSE
    };

    private static final String[] TITLES = {
            "发布视频",
            "发布图片",
            "发布赛事",
            "发布活动",
            "开始直播",
            "发布商品",
            "发布课程"
    };


    private static final int[] UN_LOGIN = {};
    private static final int[] LOGIN_NORAML = {0, 1};
    private static final int[] ANCHOR = {0, 1, 4};
    private static final int[] MERCHANT = {0, 1, 5, 4};
    private static final int[] COACH = {0, 1, 6, 4};
    private static final int[] GROUP = {0, 1, 2, 3, 4};
    private static final int[] TEST = {0, 1, 2, 3, 4, 5, 6};

    public static List<ReleaseItemBean> getList() {
        int[] types;
        String role = SPUtils.getStringData(Constant.Config.ROLECODE);
        switch (role) {
            case CommonConfig.ROLE_TEST:
                types = TEST;
                break;
            case CommonConfig.ROLE_NORMAL_UN_LOGIN:
                types = UN_LOGIN;
                break;
            case CommonConfig.ROLE_NORMAL:
                types = LOGIN_NORAML;
                break;
            case CommonConfig.ROLE_GROUP:
                types = GROUP;
                break;
            case CommonConfig.ROLE_COCAH:
                types = COACH;
                break;
            case CommonConfig.ROLE_MERCHANT:
                types = MERCHANT;
                break;
            case CommonConfig.ROLE_ANCHOR:
                types = ANCHOR;
                break;
            default:
                types = UN_LOGIN;
        }


        List<ReleaseItemBean> list = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            list.add(new ReleaseItemBean(TYPES[types[i]], TITLES[types[i]]));
        }
        return list;
    }

    /**
     * 点击对应条目跳转
     * LIVE :
     * 商家、教练→选择赛事→开始直播页面（带分享）→ 直播（banner+ 三行列表）
     * 主播→ 直播双列  → 开始直播（不带分享） → 直播（banner+ 三行列表）
     */

    public static void startActivityByType(final Context context, final Type type) {
        if (needUthentication(type) && !CommonConfig.isGroupOwner(SPUtils.getStringData(SPKey.AUTHENTICATION_RESULT+ SPUtils.getStringData(Constant.Config.USER_ID)))) {
            CommentRequestHelper.httpAuthentication(new CommentRequestHelper.CallBackAdapter() {
                @Override
                public void onSuccess(String string) {
                    if (CommonConfig.isGroupOwner(string)) {
                        startActivityByTypeNext(context, type);
                    } else {
                        AuthenticationActivity.startActivity(context);
                        ToastUtil.show("请先实名认证");
                    }
                }
            });
        } else {
            startActivityByTypeNext(context, type);
        }
    }

    private static void startActivityByTypeNext(final Context context, final Type type) {
        switch (type) {
            case VIDEO:
                ReleaseVideoActivity.newInstance(context, ReleaseVideoActivity.TYPE_OF_VIDEO);
                break;
            case IMAGE:
                ReleaseVideoActivity.newInstance(context, ReleaseVideoActivity.TYPE_OF_PIC);
                break;
            case MATCH:
                ReleaseMatchActivity.newInstance(context);
                break;
            case ACTION:
                ReleaseActionActivity.newInstance(context);
                break;
            case LIVE:
                // TODO: 2019/12/25 商家、教练
                switch (SPUtils.getStringData(Constant.Config.ROLECODE)) {
                    case CommonConfig.ROLE_GROUP:
                        ContainerActivity.newInstance(context, ContainerActivity.TYPE_SELECTOR_MATCH, "选择赛事");
                        break;
                    case CommonConfig.ROLE_COCAH:
                    case CommonConfig.ROLE_MERCHANT:
                    case CommonConfig.ROLE_ANCHOR:
                        ContainerActivity.newInstance(context, ContainerActivity.TYPE_LIVE_LIST, "直播");
                        break;
                    default:
                        break;
                }

                break;
            case GOODS:
                ReleaseGoodsActivity.newInstance(context);
                break;
            case COURSE:
                ReleaseCourseActivity.newInstance(context);
                break;
            default:
        }
    }

    private static boolean needUthentication(Type type) {
        return Arrays.asList(NEED_AUTH).contains(type);
    }
}
