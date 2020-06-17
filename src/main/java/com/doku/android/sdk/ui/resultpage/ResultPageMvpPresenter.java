package com.doku.android.sdk.ui.resultpage;

import com.doku.android.sdk.di.PerActivity;
import com.doku.android.sdk.ui.base.MvpPresenter;

@PerActivity
public interface ResultPageMvpPresenter<V extends ResultPageMvpView, I extends ResultPageMvpInteractor> extends MvpPresenter<V, I> {
    void getHowTopPaymandiri(String noVa);
    void getHowTopPaymandiriSyariah(String noVa);
}
