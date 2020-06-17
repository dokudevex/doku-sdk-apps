package com.doku.android.sdk.ui.base;

import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.prefs.PreferencesHelper;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public interface MvpInteractor {

    ApiService getApiService();

    PreferencesHelper getPreferencesHelper();
}
