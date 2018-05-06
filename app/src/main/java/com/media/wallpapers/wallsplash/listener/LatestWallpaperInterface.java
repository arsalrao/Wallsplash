package com.media.wallpapers.wallsplash.listener;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.utill.Status;

public interface LatestWallpaperInterface {
    public LiveData<PagedList<Photo>> getPhotos();

    public LiveData<Status> getLoadStatus();
}
