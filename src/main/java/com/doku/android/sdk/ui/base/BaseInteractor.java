package com.doku.android.sdk.ui.base;

import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.prefs.PreferencesHelper;
import javax.inject.Inject;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public class BaseInteractor implements MvpInteractor {

    public final ApiService ApiService;
    public final PreferencesHelper preferencesHelper;

    @Inject
    public BaseInteractor(PreferencesHelper preferencesHelper,ApiService ApiService) {
        this.ApiService = ApiService;
        this.preferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper(){
        return  preferencesHelper;
    }


    @Override
    public ApiService getApiService() {
        return ApiService;
    }
}
