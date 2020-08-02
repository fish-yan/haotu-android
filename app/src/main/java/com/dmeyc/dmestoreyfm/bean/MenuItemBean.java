package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.config.MenuConfig;

/**
 * create by cxg on 2019/11/23
 */
public class MenuItemBean {
    public String title;
    public int icon;
    public MenuConfig.MenuType menuType;

    public MenuItemBean(String title, int icon, MenuConfig.MenuType menuType) {
        this.title = title;
        this.icon = icon;
        this.menuType = menuType;
    }
}
