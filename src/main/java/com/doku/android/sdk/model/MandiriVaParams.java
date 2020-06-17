package com.doku.android.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class MandiriVaParams {
    @SerializedName("client")
    @Expose
    public MandiriVaClientParams client;

    @SerializedName("order")
    @Expose
    public MandiriVaOrdersParams order;

    @SerializedName("virtual_account_info")
    @Expose
    public MandiriVaAccountParams virtualAccountInfo;

    @SerializedName("customer")
    @Expose
    public MandiriVaCustomerParams customer;

    @SerializedName("security")
    @Expose
    public MandiriVaSecurityParams security;
}
