package com.media.wallpapers.wallsplash.rest;

import com.media.wallpapers.wallsplash.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://api.unsplash.com/photos/?page=1&per_page=30&order_by=latest&client_id=fc12a678353fd3cda9afb77ebbdeec416b4352021c85051b174d033603596f6f
    @GET("photos")
    Call<List<Photo>> getPhotos(
            @Query("page") int pageNumber,
            @Query("per_page") int perPage,
            @Query("order_by") String orderBy,
            @Query("client_id") String apiKey
    );
}

