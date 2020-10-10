package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

public class LoginPost {
    @SerializedName("success")
    private boolean success;
    @SerializedName("token")
    private String token;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
