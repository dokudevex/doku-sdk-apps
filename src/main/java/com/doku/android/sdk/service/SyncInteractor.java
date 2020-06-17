package com.doku.android.sdk.service;

import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.prefs.PreferencesHelper;
import com.doku.android.sdk.ui.base.BaseInteractor;
import javax.inject.Inject;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public class SyncInteractor extends BaseInteractor implements SyncMvpInteractor {

    @Inject
    public SyncInteractor(PreferencesHelper preferencesHelper, ApiService ApiService) {

        super(preferencesHelper,ApiService);
    }
}
