package com.doku.android.sdk.ui.base;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public interface SubMvpView extends MvpView {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void attachParentMvpView(MvpView mvpView);
}
