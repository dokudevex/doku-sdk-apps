package com.doku.android.sdk.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.doku.android.sdk.di.ActivityContext;
import com.doku.android.sdk.di.PerActivity;
import com.doku.android.sdk.rx.AppSchedulerProvider;
import com.doku.android.sdk.rx.SchedulerProvider;
import com.doku.android.sdk.ui.resultpage.ResultPageInteractor;
import com.doku.android.sdk.ui.resultpage.ResultPageMvpInteractor;
import com.doku.android.sdk.ui.resultpage.ResultPageMvpPresenter;
import com.doku.android.sdk.ui.resultpage.ResultPageMvpView;
import com.doku.android.sdk.ui.resultpage.ResultPagePresenter;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaInteractor;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaMvpInteractor;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaMvpPresenter;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaMvpView;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaPresenter;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaInteractor;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaMvpInteractor;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaMvpPresenter;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaMvpView;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by dedyeirawan on 21,May,2020
 */
@Module
public class ActivityModule {

    private AppCompatActivity appCompatActivity;
    public ActivityModule(AppCompatActivity appCompatActivity) {this.appCompatActivity = appCompatActivity;}

    @Provides
    @ActivityContext
    Context provideContext() {return appCompatActivity;}

    @Provides
    AppCompatActivity provideActivity() { return appCompatActivity;}

    @Provides
    CompositeDisposable provideCompositeDisposable() { return new CompositeDisposable();}

    @Provides
    SchedulerProvider provideSchedulerProvider() {return new AppSchedulerProvider();}

    @Provides
    @PerActivity
    ResultPageMvpPresenter<ResultPageMvpView, ResultPageMvpInteractor> provideResultPagePresenter(
            ResultPagePresenter<ResultPageMvpView, ResultPageMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ResultPageMvpInteractor provideResultPageMvpInteractor(ResultPageInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    MandiriVaMvpPresenter<MandiriVaMvpView, MandiriVaMvpInteractor> provideMandiriVaPresenter(
            MandiriVaPresenter<MandiriVaMvpView, MandiriVaMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MandiriVaMvpInteractor provideMandiriVaMvpInteractor(MandiriVaInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    MandiriSyariahVaMvpPresenter<MandiriSyariahVaMvpView, MandiriSyariahVaMvpInteractor> provideMandiriSyariahPresenter(
            MandiriSyariahVaPresenter<MandiriSyariahVaMvpView, MandiriSyariahVaMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MandiriSyariahVaMvpInteractor provideMandiriSyariahMvpInteractor(MandiriSyariahVaInteractor interactor) {
        return interactor;
    }
}
