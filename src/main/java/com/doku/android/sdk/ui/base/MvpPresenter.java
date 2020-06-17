package com.doku.android.sdk.ui.base;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public interface MvpPresenter <V extends MvpView,I extends MvpInteractor>{

    void onAttach(V mvpView);

    void onDetach();

    V getMvpView();

    I getInteractor();

    boolean isViewAttached();
}
