package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

public class FeedUser {
    @SerializedName("_id")
    private String _id;
    @SerializedName("nickname")
    private String nickname;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
