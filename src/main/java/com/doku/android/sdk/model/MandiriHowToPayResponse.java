package com.doku.android.sdk.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedye on 08,June,2020
 */
public class MandiriHowToPayResponse {
    @SerializedName("payment_instruction")
    @Expose
    private List<MandiriHowToPayInstructionResponse> paymentInstruction = null;

    public List<MandiriHowToPayInstructionResponse> getPaymentInstruction() {
        return paymentInstruction;
    }

    public void setPaymentInstruction(List<MandiriHowToPayInstructionResponse> paymentInstruction) {
        this.paymentInstruction = paymentInstruction;
    }

}
