package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubChallenge {
    @SerializedName("success")
    private boolean success;
    @SerializedName("challenge")
    private List<SubChallenge> challenge;
}
