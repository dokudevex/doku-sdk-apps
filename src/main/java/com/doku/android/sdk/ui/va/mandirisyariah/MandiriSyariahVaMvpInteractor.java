package com.doku.android.sdk.ui.va.mandirisyariah;

import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.model.MandiriVaResponse;
import com.doku.android.sdk.ui.base.MvpInteractor;
import io.reactivex.Observable;

public interface MandiriSyariahVaMvpInteractor extends MvpInteractor {
    Observable<MandiriVaResponse> getPaymentCode(MandiriVaParams data);
}
