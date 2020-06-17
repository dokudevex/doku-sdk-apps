package com.doku.android.sdk.ui.va.mandirisyariah;

import com.doku.android.sdk.model.MandiriVaResponse;
import com.doku.android.sdk.ui.base.MvpView;

public interface MandiriSyariahVaMvpView extends MvpView {
    void showResult(MandiriVaResponse data);
    void errorMessage(String titileError, String errorMessage);
}
