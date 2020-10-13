package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

public class UploadPost {
    @SerializedName("success")
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
