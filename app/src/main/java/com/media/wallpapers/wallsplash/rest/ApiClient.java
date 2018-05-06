package com.media.wallpapers.wallsplash.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //https://api.unsplash.com/photos/?page=1&per_page=30&order_by=latest&client_id=fc12a678353fd3cda9afb77ebbdeec416b4352021c85051b174d033603596f6f
    private static final String BASE_URL = "https://api.unsplash.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
