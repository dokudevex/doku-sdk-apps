package com.doku.android.sdk.di.module;

import android.app.Service;
import com.doku.android.sdk.service.SyncInteractor;
import com.doku.android.sdk.service.SyncMvpInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    SyncMvpInteractor provideSyncMvpInteractor(SyncInteractor interactor) {
        return interactor;
    }
}
