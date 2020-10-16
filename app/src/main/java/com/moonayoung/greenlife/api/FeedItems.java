package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

public class FeedItems {
    @SerializedName("_id")
    private String _id;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("user")
    private FeedUser user;
    @SerializedName("created")
    private String created;
    @SerializedName("__v")
    private int __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public FeedUser getUser() {
        return user;
    }

    public void setUser(FeedUser user) {
        this.user = user;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
