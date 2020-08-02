package com.dmeyc.dmestoreyfm.ui.base;

import android.content.Context;


import com.dmeyc.dmestoreyfm.bean.ApplicationModule;
import com.dmeyc.dmestoreyfm.decoration.scope.ContextLife;
import com.dmeyc.dmestoreyfm.decoration.scope.PerApp;

import dagger.Component;

/**
 * Created by Eric on 2017/1/19.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}