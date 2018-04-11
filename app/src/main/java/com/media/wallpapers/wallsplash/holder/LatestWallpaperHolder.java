package com.media.wallpapers.wallsplash.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.media.wallpapers.wallsplash.R;

public class LatestWallpaperHolder extends RecyclerView.ViewHolder {
    private CardView mLatestWallpaperCv;
    private ImageView mLatestWallpaperIv;

    public LatestWallpaperHolder(View itemView) {
        super(itemView);
        //initialize views
        mLatestWallpaperCv = itemView.findViewById(R.id.latest_wallpaper_cv);
        mLatestWallpaperIv = itemView.findViewById(R.id.poster_iv);
    }

    public CardView getmLatestWallpaperCv() {
        return mLatestWallpaperCv;
    }

    public void setmLatestWallpaperCv(CardView mLatestWallpaperCv) {
        this.mLatestWallpaperCv = mLatestWallpaperCv;
    }

    public ImageView getmLatestWallpaperIv() {
        return mLatestWallpaperIv;
    }

    public void setmLatestWallpaperIv(ImageView mLatestWallpaperIv) {
        this.mLatestWallpaperIv = mLatestWallpaperIv;
    }
}
