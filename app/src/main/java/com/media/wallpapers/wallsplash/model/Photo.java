package com.media.wallpapers.wallsplash.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {
    @SerializedName("id")
    private String photoId;
    @SerializedName("created_at")
    private String photoCreatedDate;
    @SerializedName("updated_at")
    private String photoUpdatedDate;
    @SerializedName("width")
    private int photoWidth;
    @SerializedName("height")
    private int photoHeight;
    @SerializedName("color")
    private String photoColor;
    @SerializedName("description")
    private String photoDescription;
    @SerializedName("categories")
    private List<PhotoCategories> photoCategories;
    @SerializedName("user")
    private User user;
    @SerializedName("urls")
    private PhotoUrls photoUrl;
    @SerializedName("links")
    private PhotoLinks photoLinks;
    @SerializedName("liked_by_user")
    private boolean isLikedByUser;
    @SerializedName("sponsored")
    private String sponsored;
    @SerializedName("likes")
    private int likes;
    @SerializedName("current_user_collection")
    private List<CurrentUserCollection> currentUserCollectionList;


    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoCreatedDate() {
        return photoCreatedDate;
    }

    public String getPhotoUpdatedDate() {
        return photoUpdatedDate;
    }

    public int getPhotoWidth() {
        return photoWidth;
    }

    public int getPhotoHeight() {
        return photoHeight;
    }

    public String getPhotoColor() {
        return photoColor;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public List<PhotoCategories> getPhotoCategories() {
        return photoCategories;
    }

    public User getUser() {
        return user;
    }

    public PhotoUrls getPhotoUrl() {
        return photoUrl;
    }

    public PhotoLinks getPhotoLinks() {
        return photoLinks;
    }

    public boolean isLikedByUser() {
        return isLikedByUser;
    }

    public String getSponsored() {
        return sponsored;
    }

    public int getLikes() {
        return likes;
    }

    public List<CurrentUserCollection> getCurrentUserCollectionList() {
        return currentUserCollectionList;
    }
}
