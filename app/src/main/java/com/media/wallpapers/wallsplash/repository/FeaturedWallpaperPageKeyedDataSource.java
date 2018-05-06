package com.media.wallpapers.wallsplash.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.rest.ApiInterface;
import com.media.wallpapers.wallsplash.utill.Status;
import com.media.wallpapers.wallsplash.utill.WallpaperUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedWallpaperPageKeyedDataSource extends PageKeyedDataSource<Integer, Photo> {
    private static final String TAG = FeaturedWallpaperPageKeyedDataSource.class.getSimpleName();
    private ApiInterface mApiClient;
    private MutableLiveData liveData = new MutableLiveData();

    public FeaturedWallpaperPageKeyedDataSource(ApiInterface mApiClient) {
        this.mApiClient = mApiClient;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Photo> callback) {
        liveData.postValue(Status.LOADING);
        Call<List<Photo>> featuredWallpaperList = mApiClient.getCuratedPhotos(1, params.requestedLoadSize, "latest", WallpaperUtils.API_KEY);
        Log.i(TAG, "initial requested load size key " + params.requestedLoadSize);
        featuredWallpaperList.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.onResult(response.body(), 1, 2);
                    liveData.postValue(Status.LOADED);
                } else {
                    liveData.postValue(Status.FAILED);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                liveData.postValue(Status.FAILED);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        //liveData.postValue(Status.LOADING);
        if (params.key > 1) {
            liveData.postValue(Status.LOADING);
            Call<List<Photo>> featuredWallpaperList = mApiClient.getCuratedPhotos(params.key, params.requestedLoadSize, "latest", WallpaperUtils.API_KEY);
            Log.i(TAG, "initial requested load size key " + params.requestedLoadSize);
            featuredWallpaperList.enqueue(new Callback<List<Photo>>() {
                @Override
                public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        callback.onResult(response.body(), params.key - 1);
                        liveData.postValue(Status.LOADED);
                    } else {
                        liveData.postValue(Status.FAILED);
                    }
                }

                @Override
                public void onFailure(Call<List<Photo>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    liveData.postValue(Status.FAILED);
                }
            });
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        liveData.postValue(Status.LOADING);
        Call<List<Photo>> featuredWallpaperList = mApiClient.getCuratedPhotos(params.key, params.requestedLoadSize, "latest", WallpaperUtils.API_KEY);
        Log.i(TAG, "initial requested load size key " + params.requestedLoadSize);
        featuredWallpaperList.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.onResult(response.body(), params.key + 1);
                    liveData.postValue(Status.LOADED);
                } else {
                    liveData.postValue(Status.FAILED);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                liveData.postValue(Status.FAILED);
            }
        });
    }

    public MutableLiveData getLiveData() {
        return liveData;
    }
}
