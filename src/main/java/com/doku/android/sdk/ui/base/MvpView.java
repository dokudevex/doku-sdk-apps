package com.doku.android.sdk.ui.base;

import androidx.annotation.StringRes;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public interface MvpView {
    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
