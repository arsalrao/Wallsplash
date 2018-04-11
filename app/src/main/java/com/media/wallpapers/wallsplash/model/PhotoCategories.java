package com.media.wallpapers.wallsplash.model;

import com.google.gson.annotations.SerializedName;

class PhotoCategories {
    @SerializedName("id")
    private int categoryId;
    @SerializedName("title")
    private String categoryTitle;
    @SerializedName("photo_count")
    private int totalPhotos;
    @SerializedName("links")
    private PhotoLinks categoryLinks;

}
