package com.doku.android.sdk.ui.resultpage;

import com.doku.android.sdk.model.MandiriHowToPayResponse;
import com.doku.android.sdk.ui.base.MvpView;

public interface ResultPageMvpView extends MvpView {
    void showHowInstruction(MandiriHowToPayResponse data);
    void errorMessage(String titileError, String errorMessage);
}
