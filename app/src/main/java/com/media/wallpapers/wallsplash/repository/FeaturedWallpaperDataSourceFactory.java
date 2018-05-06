package com.media.wallpapers.wallsplash.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.rest.ApiClient;
import com.media.wallpapers.wallsplash.rest.ApiInterface;

public class FeaturedWallpaperDataSourceFactory extends DataSource.Factory<Integer, Photo> {
    private MutableLiveData<FeaturedWallpaperPageKeyedDataSource> dataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Photo> create() {
        FeaturedWallpaperPageKeyedDataSource pageKeyedDataSource = new FeaturedWallpaperPageKeyedDataSource(ApiClient.getRetrofit().create(ApiInterface.class));
        dataSource.postValue(pageKeyedDataSource);
        return pageKeyedDataSource;
    }

    public MutableLiveData<FeaturedWallpaperPageKeyedDataSource> getDataSource() {
        return dataSource;
    }
}

