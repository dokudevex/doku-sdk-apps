package com.doku.android.sdk.di.module;

import android.app.Application;
import android.content.Context;
import com.doku.android.sdk.di.ApplicationContext;
import com.doku.android.sdk.di.PreferenceInfo;
import com.doku.android.sdk.prefs.AppPreferencesHelper;
import com.doku.android.sdk.prefs.PreferencesHelper;
import com.doku.android.sdk.utils.AppConstants;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
}
