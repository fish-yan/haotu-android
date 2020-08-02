package com.dmeyc.dmestoreyfm.utils.updateUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;

import butterknife.ButterKnife;

/**
 * Created by shadyY on 18/12/22.
 * <p/>
 * BaseDialog 自定义总的Dialog
 */
public abstract class BaseDialog extends Dialog {
    View mView;
    LayoutInflater inflater;
    Context mContext;

    public BaseDialog(Context context, int layoutId) {
        super(context, R.style.Dialog_Fullscreen);
        this.mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(layoutId, null);
        this.setContentView(mView);
        ButterKnife.bind(this);
    }

    /**
     * 查找View,不用强制转换
     *
     * @param id   控件的id
     * @param <VT> View的类型
     * @return 对应的View
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mView.findViewById(id);
    }

    /**
     * 打印当前的类名的log, 调用Log.d()
     *
     * @param s 需要打印的信息
     */
    protected void logForDebug(String s) {
        if (!TextUtils.isEmpty(s)) {
            Log.d(mContext.getClass().getSimpleName(), s);
        }
    }

    /**
     * 为多个View 添加点击事件
     *
     * @param listener 点击事件监听器
     * @param views    需要设置点击事件的所有组件
     */
    protected void setOnClickListeners(View.OnClickListener listener, View... views) {
        if (listener != null) {
            for (View view : views) {
                view.setOnClickListener(listener);
            }
        }
    }

    /**
     * 通过类名启动Activity
     *
     * @param cls 需要跳转到的类
     */
    protected void openActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        mContext.startActivity(intent);
    }
}