package com.media.wallpapers.wallsplash.model;

import com.google.gson.annotations.SerializedName;

class PhotoLinks {
    @SerializedName("self")
    private String self;
    @SerializedName("html")
    private String html;
    //this download done by browser
    @SerializedName("download")
    private String photoDownload;
    //this download done by api call so for downloading the image use this field
    @SerializedName("download_location")
    private String photoDownloadLink;

    @SerializedName("photos")
    private String userPhotos;
    @SerializedName("likes")
    private String userLikes;
    @SerializedName("portfolio")
    private String userPortfolio;
    @SerializedName("following")
    private String userFollowing;
    @SerializedName("followers")
    private String userFollowers;

    //this constructor is used for downloading the wallpaper
    public PhotoLinks(String self, String html, String photoDownload, String photoDownloadLink) {
        this.self = self;
        this.html = html;
        this.photoDownload = photoDownload;
        this.photoDownloadLink = photoDownloadLink;
    }

    //this constructor is used for user info i.e likes,following etc
    public PhotoLinks(String self, String html, String userPhotos, String userLikes, String userPortfolio, String userFollowing, String userFollowers) {
        this.self = self;
        this.html = html;
        this.userPhotos = userPhotos;
        this.userLikes = userLikes;
        this.userPortfolio = userPortfolio;
        this.userFollowing = userFollowing;
        this.userFollowers = userFollowers;
    }

    //this constructor is used for PhotoCategory
    public PhotoLinks(String self, String photos) {
        this.self = self;
        userPhotos = photos;
    }

    //this constructor is used for currentUserCollection
    public PhotoLinks(String self, String html, String userPhotos, String userLikes, String userPortfolio) {
        this.self = self;
        this.html = html;
        this.userPhotos = userPhotos;
        this.userLikes = userLikes;
        this.userPortfolio = userPortfolio;
    }

    //this is used for current user Collection
    public PhotoLinks(String self, String html, String userPhotos) {
        this.self = self;
        this.html = html;
        this.userPhotos = userPhotos;
    }
}

