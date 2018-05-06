package com.media.wallpapers.wallsplash;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.media.wallpapers.wallsplash.listener.LatestWallpaperInterface;
import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.repository.ItemWallpaperKeyedDataSource;
import com.media.wallpapers.wallsplash.repository.LatestWallpaperDataSourceFactory;
import com.media.wallpapers.wallsplash.utill.Status;

import static android.arch.lifecycle.Transformations.switchMap;

public class LatestWallpaperImp implements LatestWallpaperInterface {
    private final static int PAGE_SIZE = 30;
    private LiveData<PagedList<Photo>> photos;
    private ItemWallpaperKeyedDataSource dataSource;
    private LatestWallpaperDataSourceFactory dataSourceFactory;

    public LatestWallpaperImp(LatestWallpaperDataSourceFactory dataSourceFactory) {
        // this.dataSource = dataSource;
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public LiveData<PagedList<Photo>> getPhotos() {


        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE).build();
        photos = new LivePagedListBuilder<Integer, Photo>(dataSourceFactory, config)
                .setInitialLoadKey(1)
                .setFetchExecutor(AppsExecutor.networkIO()).build();
        Log.i("get photos called", "called");


        return photos;
    }

    @Override
    public LiveData<Status> getLoadStatus() {
        return switchMap(dataSourceFactory.dataSourceLiveData,
                dataSource -> dataSource.getLiveData());
    }
}
