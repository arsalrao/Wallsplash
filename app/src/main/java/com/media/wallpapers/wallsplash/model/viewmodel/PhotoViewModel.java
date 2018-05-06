package com.media.wallpapers.wallsplash.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.media.wallpapers.wallsplash.AppsExecutor;
import com.media.wallpapers.wallsplash.listener.LatestWallpaperInterface;
import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.repository.ItemWallpaperKeyedDataSource;
import com.media.wallpapers.wallsplash.repository.LatestWallpaperDataSourceFactory;
import com.media.wallpapers.wallsplash.utill.Status;

import static android.arch.lifecycle.Transformations.switchMap;


public class PhotoViewModel extends AndroidViewModel implements LatestWallpaperInterface {
    private static final String TAG = PhotoViewModel.class.getSimpleName();
    ItemWallpaperKeyedDataSource dataSource;
    private LiveData<PagedList<Photo>> photo;
    private LatestWallpaperDataSourceFactory dataSourceFactory;

    public PhotoViewModel(Application application) {
        super(application);
        // this.wallpaperInterface = new LatestWallpaperImp(new LatestWallpaperDataSourceFactory());
        dataSourceFactory = new LatestWallpaperDataSourceFactory();
        Log.i(TAG, "view model constructor called");
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(30)
                .setPageSize(30)
                .build();
        photo = new LivePagedListBuilder<Integer, Photo>(dataSourceFactory, config)
                .setInitialLoadKey(1)
                .setFetchExecutor(AppsExecutor.networkIO()).build();


    }

    @Override
    public LiveData<PagedList<Photo>> getPhotos() {
        return photo;
    }

    public LiveData<Status> getLoadStatus() {

        return switchMap(dataSourceFactory.dataSourceLiveData, dataSource -> dataSource.getLiveData());
    }
}
