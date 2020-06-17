package com.doku.android.sdk.di.component;

import com.doku.android.sdk.di.PerService;
import com.doku.android.sdk.di.module.ServiceModule;
import com.doku.android.sdk.service.SyncService;
import dagger.Component;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(SyncService service);
}
