package com.doku.android.sdk.ui.va.mandirisyariah;

import com.doku.android.sdk.R;
import com.doku.android.sdk.app.MvpApp;
import com.doku.android.sdk.model.MandiriVaParams;
import com.doku.android.sdk.rx.SchedulerProvider;
import com.doku.android.sdk.ui.base.BasePresenter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MandiriSyariahVaPresenter<V extends MandiriSyariahVaMvpView, I extends MandiriSyariahVaMvpInteractor> extends BasePresenter<V, I> implements MandiriSyariahVaMvpPresenter<V, I> {

    @Inject
    MandiriSyariahVaPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void getPaymentCode(MandiriVaParams data) {
        getCompositeDisposable().add(getInteractor().getPaymentCode(data)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(ResponseBody -> {
                    String dataVA = ResponseBody.getVirtualAccountInfo().getVirtualAccountNumber();
                    if (dataVA != null) {
                        getMvpView().showResult(ResponseBody);
                    } else {
                        getMvpView().errorMessage("not Success", MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        if(httpException.code() == 500 || httpException.code() == 404 || httpException.code() == 400){
                            getMvpView().errorMessage("Internal Server Error",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                        }else {
                            getMvpView().errorMessage(String.valueOf(httpException.code()), MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                        }
                    } else if (throwable instanceof SocketTimeoutException) {
                        getMvpView().errorMessage("Socket Timeout",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_TimeOut));
                    } else if (throwable instanceof IOException) {
                        getMvpView().errorMessage("IO Exception",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    } else {
                        getMvpView().errorMessage("Other Error",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    }
                }));
    }
}
