package com.doku.android.sdk.ui.base;

import com.doku.android.sdk.rx.SchedulerProvider;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public class BasePresenter<V extends MvpView,I extends MvpInteractor> implements MvpPresenter<V,I> {

    private static final String TAG = "BasePresenter";

    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;

    private I mvpInteractor;
    private V mvpView;

    @Inject
    public BasePresenter(I mvpInteractor,SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mvpInteractor = mvpInteractor;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    @Override
    public I getInteractor() {
        return mvpInteractor;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
