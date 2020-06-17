package com.doku.android.sdk.ui.va.mandirisyariah;

import com.doku.android.sdk.di.PerActivity;
import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.ui.base.MvpPresenter;

@PerActivity
public interface MandiriSyariahVaMvpPresenter<V extends MandiriSyariahVaMvpView, I extends MandiriSyariahVaMvpInteractor> extends MvpPresenter<V, I> {
    void getPaymentCode(MandiriVaParams data);
}
