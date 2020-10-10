package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;


public class JoinPost {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
