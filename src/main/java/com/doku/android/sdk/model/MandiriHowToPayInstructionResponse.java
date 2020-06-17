package com.doku.android.sdk.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedye on 08,June,2020
 */
public class MandiriHowToPayInstructionResponse {
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("step")
    @Expose
    private List<String> step = null;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public List<String> getStep() {
        return step;
    }

    public void setStep(List<String> step) {
        this.step = step;
    }
}
