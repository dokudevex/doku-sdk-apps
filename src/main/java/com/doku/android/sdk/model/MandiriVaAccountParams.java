package com.doku.android.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class MandiriVaAccountParams {
    @SerializedName("expired_time")
    @Expose
    public Integer expiredTime;
    @SerializedName("reusable_status")
    @Expose
    public String reusableStatus;
    @SerializedName("info1")
    @Expose
    public String info1;
    @SerializedName("info2")
    @Expose
    public String info2;
    @SerializedName("info3")
    @Expose
    public String info3;
}
