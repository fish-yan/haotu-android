package com.dmeyc.dmestoreyfm.base;

import android.support.annotation.NonNull;

/**
 * Created by Eric on 2017/1/16.
 */

public interface BasePresenters {
//    void onResume();

    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
