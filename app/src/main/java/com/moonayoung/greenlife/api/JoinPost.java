package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;


public class JoinPost {

    @SerializedName("success")
    private boolean check;


    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
