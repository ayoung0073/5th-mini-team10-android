package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

public class Participate {
    @SerializedName("success")
    private boolean success;
    @SerializedName("count")
    private int count;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
