package com.doku.android.sdk.ui.resultpage;

import com.doku.android.sdk.R;
import com.doku.android.sdk.app.MvpApp;
import com.doku.android.sdk.rx.SchedulerProvider;
import com.doku.android.sdk.ui.base.BasePresenter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ResultPagePresenter<V extends ResultPageMvpView, I extends ResultPageMvpInteractor> extends BasePresenter<V, I> implements ResultPageMvpPresenter<V, I> {

    @Inject
    ResultPagePresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void getHowTopPaymandiri(String noVa) {
        getCompositeDisposable().add(getInteractor().getHowTopPaymandiri(noVa)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(ResponseBody -> {
                    if (ResponseBody.getPaymentInstruction().size() != 0) {
                        getMvpView().showHowInstruction(ResponseBody);
                    }else{
                        getMvpView().errorMessage("not Success", MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        if(httpException.code() == 500 || httpException.code() == 404){
                            getMvpView().errorMessage("Internal Server Error",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                        }else {
                            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                            getMvpView().errorMessage(String.valueOf(httpException.code()), responseBody.string());
                        }
                    } else if (throwable instanceof SocketTimeoutException) {
                        getMvpView().errorMessage("Socket Timeout",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    } else if (throwable instanceof IOException) {
                        getMvpView().errorMessage("IO Exception",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    } else {
                        getMvpView().errorMessage("Other Error",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    }
                }));
    }

    @Override
    public void getHowTopPaymandiriSyariah(String noVa) {
        getCompositeDisposable().add(getInteractor().getHowTopPaymandiriSyariah(noVa)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(ResponseBody -> {
                    if (ResponseBody.getPaymentInstruction().size() != 0) {
                        getMvpView().showHowInstruction(ResponseBody);
                    }else{
                        getMvpView().errorMessage("not Success", MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    }
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        HttpException httpException = (HttpException) throwable;
                        if(httpException.code() == 500 || httpException.code() == 404){
                            getMvpView().errorMessage("Internal Server Error",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                        }else {
                            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                            getMvpView().errorMessage(String.valueOf(httpException.code()), responseBody.string());
                        }
                    } else if (throwable instanceof SocketTimeoutException) {
                        getMvpView().errorMessage("Socket Timeout",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    } else if (throwable instanceof IOException) {
                        getMvpView().errorMessage("IO Exception",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    } else {
                        getMvpView().errorMessage("Other Error",MvpApp.getAppResources().getString(R.string.Warning_ResponServer_Error));
                    }
                }));
    }
}
