package com.media.wallpapers.wallsplash.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.util.Log;

import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.rest.ApiClient;
import com.media.wallpapers.wallsplash.rest.ApiInterface;

public class LatestWallpaperDataSourceFactory extends DataSource.Factory<Integer, Photo> {
    private static final String TAG = LatestWallpaperDataSourceFactory.class.getSimpleName();
    public MutableLiveData<ItemWallpaperKeyedDataSource> dataSourceLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Photo> create() {
        ItemWallpaperKeyedDataSource dataSource = new ItemWallpaperKeyedDataSource(ApiClient.getRetrofit().create(ApiInterface.class));

        dataSourceLiveData.postValue(dataSource);
        Log.i(TAG, "datasource create function called");
        return dataSource;

    }
}
