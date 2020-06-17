package com.doku.android.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class MandiriVaOrdersParams {
    @SerializedName("invoice_number")
    @Expose
    public String invoiceNumber;
    @SerializedName("amount")
    @Expose
    public Integer amount;
}
