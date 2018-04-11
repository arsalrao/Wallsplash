package com.media.wallpapers.wallsplash.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("profile_image")
    public UserProfileImage userProfileImage;
    @SerializedName("id")
    private String userId;
    @SerializedName("updated_at")
    private String userUpdatedAt;
    @SerializedName("username")
    private String userName;
    @SerializedName("name")
    private String name;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("twitter_username")
    private String twitterUserName;
    @SerializedName("portfolio_url")
    private String portfolioLink;
    @SerializedName("bio")
    private String bio;
    @SerializedName("location")
    private String location;
    @SerializedName("links")
    private PhotoLinks userProfileLinks;
    @SerializedName("total_collection")
    private int totalCollection;
    @SerializedName("instagram_username")
    private String instagramUserName;
    @SerializedName("total_likes")
    private int totalLikes;
    @SerializedName("total_Photos")
    private int totalPhotos;

    private static class UserProfileImage {
        @SerializedName("small")
        String profileImageSmall;
        @SerializedName("medium")
        String profileImageMedium;
        @SerializedName("large")
        String profileImageLarge;
    }
}
