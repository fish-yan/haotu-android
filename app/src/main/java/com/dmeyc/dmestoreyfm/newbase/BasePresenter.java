package com.dmeyc.dmestoreyfm.newbase;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.SubscriberManager;
import com.dmeyc.dmestoreyfm.utils.GUIDCreatUtils;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    protected V mView;
    private WeakReference<V> viewRef;

    protected CustomSubscriber subscriber;
    protected String mGuid;

    public void attachView(V view) {
        viewRef = new WeakReference<V>((V) view);
        this.mView = getView();
        mGuid = GUIDCreatUtils.createGUID();
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
        //解绑订阅
        SubscriberManager.getInstance().relieveSubscriber(mGuid);
    }

    /**
     * 获取 presenter 对应的view
     *
     * @return 如果对应, 返回对应实例, 否则返回 null
     */
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * 检查 presenter 是否存在对应View
     *
     * @return 如果存在返回true, 否则返回false
     */
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

}