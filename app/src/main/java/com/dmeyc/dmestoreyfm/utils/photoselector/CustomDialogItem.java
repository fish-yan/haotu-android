package com.dmeyc.dmestoreyfm.utils.photoselector;

/**
 * 微信仿iPhone风格对话框选项Item
 * 
 * @author yuanhang <729741121@qq.com>
 * 
 */
public abstract class CustomDialogItem {

    int textId;// 文字id
    int viewId;// 组件id

    public CustomDialogItem(int textId, int viewId) {
        this.textId = textId;
        this.viewId = viewId;
    }

    // 点击事件
    public abstract void onClick();
}
