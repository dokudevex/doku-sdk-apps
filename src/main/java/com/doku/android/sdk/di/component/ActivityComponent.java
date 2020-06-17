package com.doku.android.sdk.di.component;

import com.doku.android.sdk.di.PerActivity;
import com.doku.android.sdk.di.module.ActivityModule;
import com.doku.android.sdk.ui.resultpage.ResultPageActivity;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaActivity;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaActivity;
import dagger.Component;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(ResultPageActivity resultPageActivity);
    void inject(MandiriVaActivity mandiriVaActivity);
    void inject(MandiriSyariahVaActivity mandiriSyariahVaActivity);
}