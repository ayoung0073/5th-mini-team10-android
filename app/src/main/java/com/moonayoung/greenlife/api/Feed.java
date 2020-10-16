package com.moonayoung.greenlife.api;

import com.google.gson.annotations.SerializedName;
import com.moonayoung.greenlife.feed.FeedItem;

import java.util.List;

public class Feed {
    @SerializedName("success")
    private boolean success;
    @SerializedName("feed")
    private List<FeedItems> feed;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<FeedItems> getFeed() {
        return feed;
    }

    public void setFeed(List<FeedItems> feed) {
        this.feed = feed;
    }
}
