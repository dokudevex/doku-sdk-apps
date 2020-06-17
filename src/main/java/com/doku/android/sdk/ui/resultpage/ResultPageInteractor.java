package com.doku.android.sdk.ui.resultpage;

import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.model.MandiriHowToPayResponse;
import com.doku.android.sdk.prefs.PreferencesHelper;
import com.doku.android.sdk.ui.base.BaseInteractor;
import javax.inject.Inject;
import io.reactivex.Observable;

public class ResultPageInteractor extends BaseInteractor implements ResultPageMvpInteractor {

    @Inject
    ResultPageInteractor(PreferencesHelper preferencesHelper, ApiService ApiService) {
        super(preferencesHelper, ApiService);
    }

    @Override
    public Observable<MandiriHowToPayResponse> getHowTopPaymandiri(String noVa) {
        return getApiService().howToPayVaMandiri(noVa);
    }

    @Override
    public Observable<MandiriHowToPayResponse> getHowTopPaymandiriSyariah(String noVa) {
        return getApiService().howToPayVaMandiriSyariah(noVa);
    }
}
