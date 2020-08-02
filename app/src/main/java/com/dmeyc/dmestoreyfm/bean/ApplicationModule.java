package com.dmeyc.dmestoreyfm.bean;

import android.content.Context;

import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.decoration.scope.ContextLife;
import com.dmeyc.dmestoreyfm.decoration.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 2017/1/19.
 */
@Module
public class ApplicationModule {
    private BaseApp mApplication;

    public ApplicationModule(BaseApp application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
