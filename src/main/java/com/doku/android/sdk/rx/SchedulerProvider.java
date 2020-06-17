package com.doku.android.sdk.rx;

import androidx.annotation.NonNull;
import io.reactivex.Scheduler;

/**
 * Created by dedyeirawan on 21,May,2020
 */
public interface SchedulerProvider {

    @NonNull
    Scheduler ui();

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

}