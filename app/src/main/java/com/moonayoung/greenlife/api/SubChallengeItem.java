package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

public class SubChallengeItem {
    @SerializedName("participate")
    private int participate;
    @SerializedName("_id")
    private String _id;
    @SerializedName("title")
    private String title;

    public int getParticipate() {
        return participate;
    }

    public void setParticipate(int participate) {
        this.participate = participate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
