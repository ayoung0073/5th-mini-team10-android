package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Rank {
    @SerializedName("success")
    private boolean success;
    @SerializedName("user")
    private ArrayList<RankUser> user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<RankUser> getUser() {
        return user;
    }

    public void setUser(ArrayList<RankUser> user) {
        this.user = user;
    }
}
