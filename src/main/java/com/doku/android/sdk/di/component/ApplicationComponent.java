package com.doku.android.sdk.di.component;

import android.app.Application;
import android.content.Context;
import com.doku.android.sdk.app.MvpApp;
import com.doku.android.sdk.di.ApplicationContext;
import com.doku.android.sdk.di.module.ApplicationModule;
import com.doku.android.sdk.di.module.NetworkModule;
import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.prefs.PreferencesHelper;
import com.doku.android.sdk.service.SyncService;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Singleton
@Component(modules={ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    ApiService SapkApiService();

    PreferencesHelper preferencesHelper();

}
