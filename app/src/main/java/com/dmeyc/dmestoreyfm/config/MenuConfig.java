package com.dmeyc.dmestoreyfm.config;

import android.content.Context;

import com.dmeyc.dmestoreyfm.bean.MenuItemBean;

import java.util.ArrayList;
import java.util.List;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newui.menu.action.ActionReleaseActivity;
import com.dmeyc.dmestoreyfm.newui.menu.apply.ApplyForActivity;
import com.dmeyc.dmestoreyfm.newui.menu.club.MyClubActivity;
import com.dmeyc.dmestoreyfm.newui.menu.goods.MyCourseAndGoodsActivity;
import com.dmeyc.dmestoreyfm.newui.menu.policy.MyPolicyActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.SettingActivity;
import com.dmeyc.dmestoreyfm.newui.menu.wallet.WalletActivity;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

/**
 * create by cxg on 2019/11/23
 */
public class MenuConfig {


    public enum MenuType {
        ACTION,//发布的活动
        WALLET,//钱包
        CLUB,//我的俱乐部
        POLICY,//我的保单
        APPLY_TO,//申请成为
        PROTOCOL,//用户使用协议
        SETTING,//设置
        RELEASE_COURSE,//发布课程
        RELEASE_GOODS,//发布商品
    }

    private final static String[] MENU_TITLES = {"发布的活动", "钱包", "我的俱乐部", "我的保单", "申请成为", "用户使用协议", "设置", "发布的课程", "发布的商品"};
    private final static int[] MENU_ICONS = {R.drawable.icon_activity, R.drawable.icon_ballet, R.drawable.icon_club, R.drawable.icon_policy,
            R.drawable.icon_apply, R.drawable.icon_protocal, R.drawable.icon_setting, R.drawable.icon_activity, R.drawable.icon_activity};
    private final static MenuType[] MENU_TYPES = {MenuType.ACTION,
            MenuType.WALLET,
            MenuType.CLUB,
            MenuType.POLICY,
            MenuType.APPLY_TO,
            MenuType.PROTOCOL,
            MenuType.SETTING,
            MenuType.RELEASE_COURSE,
            MenuType.RELEASE_GOODS};


    private final static int[] TEST = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    // 教练
    private static final int[] TYPE_COACH = {7, 1, 2, 3, 5, 6};
    //商家
    private static final int[] TYPE_MERCHANT = {8, 1, 2, 3, 5, 6};
    //主播
    private static final int[] TYPE_ANCHOR = {1, 4, 3, 5, 6};

    //群主
    private static final int[] TYPE_GROUP = {0,1,2,3, 5, 6};
    //普通用户
    private static final int[] TYPE_NORMAL = {1, 4, 3, 5, 6};

    private static final int[] TYPE_UN_LOGIN = {};

    public static List<MenuItemBean> getMenuList() {
        List<MenuItemBean> list = new ArrayList<>();
        int[] types ;
        String role = SPUtils.getStringData(Constant.Config.ROLECODE);
        switch (role) {
            case CommonConfig.ROLE_TEST:
                types = TEST;
                break;
//            case CommonConfig.ROLE_NORMAL_UN_LOGIN:
//                types = UN_LOGIN;
//                break;
            case CommonConfig.ROLE_NORMAL:
                types = TYPE_NORMAL;
                break;
            case CommonConfig.ROLE_GROUP:
                types = TYPE_GROUP;
                break;
            case CommonConfig.ROLE_COCAH:
                types = TYPE_COACH;
                break;
            case CommonConfig.ROLE_MERCHANT:
                types = TYPE_MERCHANT;
                break;
            case CommonConfig.ROLE_ANCHOR:
                types = TYPE_ANCHOR;
                break;
            default:
                types = TYPE_UN_LOGIN;
        }

        for (int i = 0; i < types.length; i++) {
            list.add(new MenuItemBean(MENU_TITLES[types[i]], MENU_ICONS[types[i]], MENU_TYPES[types[i]]));
        }
        return list;
    }

    public static void doAction(Context context, MenuType menuType) {
        switch (menuType) {
            case ACTION:
                ActionReleaseActivity.newInstance(context);
                break;
            case WALLET:
                WalletActivity.startActivity(context);
                break;
            case CLUB:
                MyClubActivity.newInstance(context);
                break;
            case POLICY:
                MyPolicyActivity.newInstance(context);
                break;
            case APPLY_TO:
                ApplyForActivity.startActivity(context);
                break;
            case PROTOCOL:
                WebviewActivity.newInstance(context,"用户使用协议"," http://www.hotu.club:9595/agreement/agreementhz.html");
                break;
            case SETTING:
                SettingActivity.startActivity(context);
                break;
            case RELEASE_GOODS:
                MyCourseAndGoodsActivity.newInstance(context, MyCourseAndGoodsActivity.TYPE_GOODS);
                break;
            case RELEASE_COURSE:
                MyCourseAndGoodsActivity.newInstance(context, MyCourseAndGoodsActivity.TYPE_COURSE);
                break;
            default:
                break;
        }
    }
}
