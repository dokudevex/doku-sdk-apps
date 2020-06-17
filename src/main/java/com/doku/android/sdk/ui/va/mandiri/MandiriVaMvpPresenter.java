package com.doku.android.sdk.ui.va.mandiri;

import com.doku.android.sdk.di.PerActivity;
import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.ui.base.MvpPresenter;

@PerActivity
public interface MandiriVaMvpPresenter<V extends MandiriVaMvpView, I extends MandiriVaMvpInteractor> extends MvpPresenter<V, I> {
    void getPaymentCode(MandiriVaParams data);
}
