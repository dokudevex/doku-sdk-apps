package com.doku.android.sdk.ui.resultpage;

import com.doku.android.sdk.model.MandiriHowToPayResponse;
import com.doku.android.sdk.ui.base.MvpInteractor;
import io.reactivex.Observable;

public interface ResultPageMvpInteractor extends MvpInteractor {
    Observable<MandiriHowToPayResponse> getHowTopPaymandiri(String noVa);
    Observable<MandiriHowToPayResponse> getHowTopPaymandiriSyariah(String noVa);
}
