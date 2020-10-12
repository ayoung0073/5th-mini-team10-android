package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChallengeItem{
    @SerializedName("subchallenges")
    private List<String> subchallenges;
    @SerializedName("_id")
    private String _id;
    @SerializedName("title")
    private String title;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("text")
    private String text;

    public List<String> getSubchallenges(){
        return subchallenges;
    }
    public void setSubchallenges(List<String> subchallenges){
        this.subchallenges = subchallenges;
    }
    public String get_id(){
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

