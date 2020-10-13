package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Challenge {
    @SerializedName("success")
    private boolean success;
    @SerializedName("challenge")
    private List<ChallengeItem> challenge;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ChallengeItem> getChallenge() {
        return challenge;
    }

    public void setChallenge(List<ChallengeItem> challenge) {
        this.challenge = challenge;
    }
}

