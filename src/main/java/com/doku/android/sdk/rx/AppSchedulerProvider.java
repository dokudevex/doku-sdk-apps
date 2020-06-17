package com.doku.android.sdk.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public class AppSchedulerProvider implements SchedulerProvider {

    private static AppSchedulerProvider INSTANCE;

    public AppSchedulerProvider(){}

    public static synchronized AppSchedulerProvider getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AppSchedulerProvider();
        }
        return INSTANCE;
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }
}
