package com.doku.android.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class MandiriVaAccountInfoResponse {

    @SerializedName("virtual_account_number")
    @Expose
    private String virtualAccountNumber;
    @SerializedName("how_to_pay_page")
    @Expose
    private String howToPayPage;
    @SerializedName("how_to_pay_api")
    @Expose
    private String howToPayApi;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("expired_date")
    @Expose
    private String expiredDate;

    public String getVirtualAccountNumber() {
        return virtualAccountNumber;
    }

    public void setVirtualAccountNumber(String virtualAccountNumber) {
        this.virtualAccountNumber = virtualAccountNumber;
    }

    public String getHowToPayPage() {
        return howToPayPage;
    }

    public void setHowToPayPage(String howToPayPage) {
        this.howToPayPage = howToPayPage;
    }

    public String getHowToPayApi() {
        return howToPayApi;
    }

    public void setHowToPayApi(String howToPayApi) {
        this.howToPayApi = howToPayApi;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

}