package com.media.wallpapers.wallsplash.model;

import com.google.gson.annotations.SerializedName;

class CurrentUserCollection {
    @SerializedName("id")
    private int currentUserCollectionId;
    @SerializedName("title")
    private String collectionTitle;
    @SerializedName("published_at")
    private String publishAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("curated")
    private boolean isCurated;
    @SerializedName("cover_photo")
    private CoverPhoto coverPhoto;
    @SerializedName("user")
    private User userInfo;
    @SerializedName("links")
    private PhotoLinks currentUserCollectionLinks;
}
