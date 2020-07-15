package com.doku.android.sdk.model;

import android.content.Context;

/**
 * Created by dedye on 03,June,2020
 */
public class PaymentItems {
    private String merchantName;
    private String clientId;
    private String invoiceNumber;
    private String dataAmount;
    private Integer expiredTime;
    private String reusableStatus;
    private String customerName;
    private String customerEmail;
    private Boolean isProduction;
    private String dataWords;
    private Boolean usePageResult;
    private Integer paymentChannel;
    private Context context;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Boolean getUsePageResult() {
        return usePageResult;
    }

    public void setUsePageResult(Boolean usePageResult) {
        this.usePageResult = usePageResult;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Boolean getIsProduction() {
        return isProduction;
    }

    public void setIsProduction(Boolean isProduction) {
        this.isProduction = isProduction;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDataWords() {
        return dataWords;
    }

    public void setDataWords(String dataWords) {
        this.dataWords = dataWords;
    }

    public String getDataAmount() {
        return dataAmount;
    }

    public void setDataAmount(String dataAmount) {
        this.dataAmount = dataAmount;
    }

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getReusableStatus() {
        return reusableStatus;
    }

    public void setReusableStatus(String reusableStatus) {
        this.reusableStatus = reusableStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(Integer paymentChannel) {
        this.paymentChannel = paymentChannel;
    }
}
