package com.doku.android.sdk.app;

import android.app.Application;
import android.content.res.Resources;
import com.doku.android.sdk.di.component.ApplicationComponent;
import com.doku.android.sdk.di.component.DaggerApplicationComponent;
import com.doku.android.sdk.di.module.ApplicationModule;
import com.doku.android.sdk.utils.AppLogger;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public class MvpApp extends Application {

    private ApplicationComponent applicationComponent;
    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();

        resources = getResources();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);
        AppLogger.init();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public static Resources getAppResources() {
        return resources;
    }
}
