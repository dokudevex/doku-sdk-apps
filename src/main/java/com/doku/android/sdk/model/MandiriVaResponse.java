package com.doku.android.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class MandiriVaResponse {

    @SerializedName("client")
    @Expose
    private MandiriVaClientResponse client;
    @SerializedName("order")
    @Expose
    private MandiriVaOrderResponse order;
    @SerializedName("virtual_account_info")
    @Expose
    private MandiriVaAccountInfoResponse virtualAccountInfo;
    @SerializedName("security")
    @Expose
    private MandiriVaSecurityResponse security;

    public MandiriVaClientResponse getClient() {
        return client;
    }

    public void setClient(MandiriVaClientResponse client) {
        this.client = client;
    }

    public MandiriVaOrderResponse getOrder() {
        return order;
    }

    public void setOrder(MandiriVaOrderResponse order) {
        this.order = order;
    }

    public MandiriVaAccountInfoResponse getVirtualAccountInfo() {
        return virtualAccountInfo;
    }

    public void setVirtualAccountInfo(MandiriVaAccountInfoResponse virtualAccountInfo) {
        this.virtualAccountInfo = virtualAccountInfo;
    }

    public MandiriVaSecurityResponse getSecurity() {
        return security;
    }

    public void setSecurity(MandiriVaSecurityResponse security) {
        this.security = security;
    }

}