package com.media.wallpapers.wallsplash.repository;

import android.arch.lifecycle.MutableLiveData;
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

public class ItemWallpaperKeyedDataSource extends android.arch.paging.PageKeyedDataSource<Integer, Photo> {
    public static final String TAG = ItemWallpaperKeyedDataSource.class.getSimpleName();
    ApiInterface apiClient;
    MutableLiveData liveData = new MutableLiveData();
    MutableLiveData pageNumberLiveData = new MutableLiveData();

    public ItemWallpaperKeyedDataSource(ApiInterface apiClient) {
        this.apiClient = apiClient;


    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {
        liveData.postValue(Status.LOADING);
        //apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Photo>> wallpaperList = apiClient.getPhotos(1, params.requestedLoadSize, "latest", WallpaperUtils.API_KEY);
        Log.i(TAG, "initial requested load size key " + params.requestedLoadSize);
        wallpaperList.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    //response.body()
                    callback.onResult(response.body(), 1, 2);
                    liveData.postValue(Status.LOADED);
                    Log.i(TAG, "Initial called");
                    Log.i(TAG, "initial -->" + response.body().toString());
                } else {
                    liveData.postValue(Status.FAILED);
                    Log.e(TAG, "Error");
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
//           Toast.makeText(MyApp.getInstance(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage());
                liveData.postValue(Status.FAILED);
            }
        });
    }


    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
//        liveData.postValue(Status.LOADING);
        Log.i(TAG, "before key--> " + params.key);
        if (params.key > 1) {
            liveData.postValue(Status.LOADING);

            Call<List<Photo>> wallpaperList = apiClient.getPhotos(params.key, params.requestedLoadSize, "latest", WallpaperUtils.API_KEY);
            pageNumberLiveData.postValue(params.key);
            wallpaperList.enqueue(new Callback<List<Photo>>() {

                @Override
                public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        Integer adjacentKey = params.key - 1;

                        callback.onResult(response.body(), adjacentKey);
                        liveData.postValue(Status.LOADED);
                        Log.i(TAG, "before called " + adjacentKey);
                        Log.i(TAG, "before called-->" + response.body().toString());
                    } else {
                        liveData.postValue(Status.FAILED);
                        Log.e(TAG, "Error on load before");
                    }
                }

                @Override
                public void onFailure(Call<List<Photo>> call, Throwable t) {
                    //  Toast.makeText(MyApp.getInstance(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "error on load before -->" + t.getMessage());
                    liveData.postValue(Status.FAILED);
                }
            });
        } /*else {
            Log.i(TAG, "load before is less than 1 ");
            liveData.postValue(Status.LOADED);
        }*/
    }


    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        liveData.postValue(Status.LOADING);
        Log.i(TAG, "after key " + params.key);

        Call<List<Photo>> wallpaperList = apiClient.getPhotos(params.key, params.requestedLoadSize, "latest", WallpaperUtils.API_KEY);
        pageNumberLiveData.postValue(params.key);
        wallpaperList.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.onResult(response.body(), params.key + 1);

                    liveData.postValue(Status.LOADED);
                    Log.i(TAG, "after called");
                    Log.i(TAG, "after called -->" + response.body().toString());

                } else {
                    liveData.postValue(Status.FAILED);
                    Log.e(TAG, "Error on load after");
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                // Toast.makeText(MyApp.getInstance(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "error on load after -->" + t.getMessage());
                liveData.postValue(Status.FAILED);
            }
        });
    }

    public MutableLiveData getLiveData() {
        return liveData;
    }

    public MutableLiveData getPageNumberLiveData() {
        return pageNumberLiveData;
    }
}
