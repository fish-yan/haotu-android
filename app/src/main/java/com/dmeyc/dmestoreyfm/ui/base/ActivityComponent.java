package com.dmeyc.dmestoreyfm.ui.base;

import android.app.Activity;
import android.content.Context;

import com.dmeyc.dmestoreyfm.bean.ActivityModule;
import com.dmeyc.dmestoreyfm.decoration.scope.ContextLife;
import com.dmeyc.dmestoreyfm.decoration.scope.PerActivity;
import com.dmeyc.dmestoreyfm.ui.NewsChannelActivity;

import dagger.Component;


/**
 * Created by Eric on 2017/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

//    void inject(NewsActivity newsActivity);

//    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsChannelActivity newsChannelActivity);

//    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);

    //void inject(PhotoActivity photoActivity);

    //void inject(PhotoDetailActivity photoDetailActivity);
}
