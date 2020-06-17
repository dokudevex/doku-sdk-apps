package com.doku.android.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class MandiriVaOrderResponse {

    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}