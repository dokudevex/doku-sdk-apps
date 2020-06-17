package com.doku.android.sdk.ui.va.mandiri;

import com.doku.android.sdk.model.MandiriVaResponse;
import com.doku.android.sdk.ui.base.MvpView;

public interface MandiriVaMvpView extends MvpView {
    void showResult(MandiriVaResponse data);
    void errorMessage(String titileError, String errorMessage);
}
