package com.moonayoung.greenlife.feed;

import com.moonayoung.greenlife.api.Feed;

public class FeedItem {
    int imageId;
    String imageUrl;

    public FeedItem(int imageId, String imageUrl) {
        super();
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
