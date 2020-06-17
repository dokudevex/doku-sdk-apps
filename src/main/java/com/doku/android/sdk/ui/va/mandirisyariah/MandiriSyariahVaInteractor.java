package com.doku.android.sdk.ui.va.mandirisyariah;

import com.doku.android.sdk.api.ApiService;
import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.model.MandiriVaResponse;
import com.doku.android.sdk.prefs.PreferencesHelper;
import com.doku.android.sdk.ui.base.BaseInteractor;
import javax.inject.Inject;
import io.reactivex.Observable;

public class MandiriSyariahVaInteractor extends BaseInteractor implements MandiriSyariahVaMvpInteractor {

    @Inject
    MandiriSyariahVaInteractor(PreferencesHelper preferencesHelper, ApiService ApiService) {
        super(preferencesHelper, ApiService);
    }

    @Override
    public Observable<MandiriVaResponse> getPaymentCode(MandiriVaParams data) {
        return getApiService().mandiriSyariahVa(data);
    }
}
