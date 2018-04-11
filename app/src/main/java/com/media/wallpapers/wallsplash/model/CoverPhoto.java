package com.media.wallpapers.wallsplash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class CoverPhoto {
    @SerializedName("id")
    private String coverPhotoId;
    @SerializedName("width")
    private int coverPhotoWidth;
    @SerializedName("height")
    private int coverPhotoHeight;
    @SerializedName("color")
    private String coverPhotoColor;
    @SerializedName("likes")
    private int coverPhotoLikes;
    @SerializedName("like_by_user")
    private boolean isLikedByUser;
    @SerializedName("description")
    private String coverPhotoDescription;
    @SerializedName("user")
    private User user;
    @SerializedName("urls")
    private PhotoUrls coverPhotoUrls;
    @SerializedName("categories")
    private List<PhotoCategories> photoCategoriesList;
    @SerializedName("links")
    private PhotoLinks coverPhotoLinks;

}
