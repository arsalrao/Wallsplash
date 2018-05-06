package com.media.wallpapers.wallsplash.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.media.wallpapers.wallsplash.AppsExecutor;
import com.media.wallpapers.wallsplash.listener.LatestWallpaperInterface;
import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.repository.FeaturedWallpaperDataSourceFactory;
import com.media.wallpapers.wallsplash.repository.FeaturedWallpaperPageKeyedDataSource;
import com.media.wallpapers.wallsplash.utill.Status;

import static android.arch.lifecycle.Transformations.switchMap;

public class FeaturedPhotoViewModel extends AndroidViewModel implements LatestWallpaperInterface {

    FeaturedWallpaperDataSourceFactory dataSourceFactory;
    private LiveData<PagedList<Photo>> photos;
    private FeaturedWallpaperPageKeyedDataSource dataSource;

    public FeaturedPhotoViewModel(@NonNull Application application) {
        super(application);
        dataSourceFactory = new FeaturedWallpaperDataSourceFactory();
        PagedList.Config config = new PagedList.Config.Builder().setInitialLoadSizeHint(30)
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build();
        photos = new LivePagedListBuilder<Integer, Photo>(dataSourceFactory, config)
                .setFetchExecutor(AppsExecutor.networkIO())
                .build();


    }

    @Override
    public LiveData<PagedList<Photo>> getPhotos() {
        return photos;
    }

    @Override
    public LiveData<Status> getLoadStatus() {
        return switchMap(dataSourceFactory.getDataSource(), dataSource -> dataSource.getLiveData());
    }
}
