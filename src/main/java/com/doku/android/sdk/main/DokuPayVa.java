package com.doku.android.sdk.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.doku.android.sdk.model.PaymentItems;
import com.doku.android.sdk.ui.va.mandiri.MandiriVaActivity;
import com.doku.android.sdk.ui.va.mandirisyariah.MandiriSyariahVaActivity;
import com.doku.android.sdk.utils.PaymentChannel;

/**
 * Created by dedye on 03,June,2020
 */
public class DokuPayVa {

    private static PaymentItems paymentItems;

    public static class Builder {
        private Context context;
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

        public Builder (Context context) {
            this.context = context;
        }

        public DokuPayVa.Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public DokuPayVa.Builder invoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public DokuPayVa.Builder dataAmount(String dataAmount) {
            this.dataAmount = dataAmount;
            return this;
        }

        public DokuPayVa.Builder expiredTime(Integer expiredTime) {
            this.expiredTime = expiredTime;
            return this;
        }

        public DokuPayVa.Builder reusableStatus(String reusableStatus) {
            this.reusableStatus = reusableStatus;
            return this;
        }

        public DokuPayVa.Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public DokuPayVa.Builder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public DokuPayVa.Builder isProduction(Boolean isProduction) {
            this.isProduction = isProduction;
            return this;
        }

        public DokuPayVa.Builder dataWords(String dataWords) {
            this.dataWords = dataWords;
            return this;
        }

        public DokuPayVa.Builder usePageResult(Boolean usePageResult) {
            this.usePageResult = usePageResult;
            return this;
        }

        public DokuPayVa.Builder paymentChannel(Integer paymentChannel) {
            this.paymentChannel = paymentChannel;
            return this;
        }

        public DokuPayVa connect() {
            DokuPayVa dokuPayVa = new DokuPayVa(this);
            if (paymentItems.getPaymentChannel() == PaymentChannel.VA_MANDIRI.getValue()) {
                Intent intent = MandiriVaActivity.getStartIntent(context);
                ((Activity) context).startActivityForResult(intentWithputExtra(intent), 300);
            } else if (paymentItems.getPaymentChannel() == PaymentChannel.VA_SYARIAH_MANDIRI.getValue()) {
                Intent intent = MandiriSyariahVaActivity.getStartIntent(context);
                ((Activity) context).startActivityForResult(intentWithputExtra(intent), 300);
            }
            return dokuPayVa;
        }

        private Intent intentWithputExtra(Intent intent) {
            intent.putExtra("clientId", paymentItems.getClientId());
            intent.putExtra("invoiceNumber", paymentItems.getInvoiceNumber());
            intent.putExtra("amount", paymentItems.getDataAmount());
            intent.putExtra("expiredTime", String.valueOf(paymentItems.getExpiredTime()));
            intent.putExtra("reusableStatus", paymentItems.getReusableStatus());
            intent.putExtra("customerName", paymentItems.getCustomerName());
            intent.putExtra("customerEmail", paymentItems.getCustomerEmail());
            intent.putExtra("isProduction", paymentItems.getIsProduction());
            intent.putExtra("words", paymentItems.getDataWords());
            intent.putExtra("usePageResult", paymentItems.getUsePageResult());
            return intent;
        }
    }

    private DokuPayVa(Builder builder) {
        paymentItems = new PaymentItems();
        paymentItems.setContext(builder.context);
        paymentItems.setClientId(builder.clientId);
        paymentItems.setInvoiceNumber(builder.invoiceNumber);
        paymentItems.setDataAmount(builder.dataAmount);
        paymentItems.setExpiredTime(builder.expiredTime);
        paymentItems.setReusableStatus(builder.reusableStatus);
        paymentItems.setCustomerName(builder.customerName);
        paymentItems.setCustomerEmail(builder.customerEmail);
        paymentItems.setIsProduction(builder.isProduction);
        paymentItems.setDataWords(builder.dataWords);
        paymentItems.setUsePageResult(builder.usePageResult);
        paymentItems.setPaymentChannel(builder.paymentChannel);
    }
}