package com.media.wallpapers.wallsplash.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.media.wallpapers.wallsplash.R;

public class FeaturedWallpaperHolder extends RecyclerView.ViewHolder {
    private CardView mFeaturedWallpaperCV;
    private ImageView mFeaturedWallpaperIV;

    public FeaturedWallpaperHolder(View itemView) {
        super(itemView);
        //initialize views
        mFeaturedWallpaperCV = itemView.findViewById(R.id.latest_wallpaper_cv);
        mFeaturedWallpaperIV = itemView.findViewById(R.id.poster_iv);
    }

    public CardView getmFeaturedWallpaperCV() {
        return mFeaturedWallpaperCV;
    }

    public void setmFeaturedWallpaperCV(CardView mFeaturedWallpaperCV) {
        this.mFeaturedWallpaperCV = mFeaturedWallpaperCV;
    }

    public ImageView getmFeaturedWallpaperIV() {
        return mFeaturedWallpaperIV;
    }

    public void setmFeaturedWallpaperIV(ImageView mFeaturedWallpaperIV) {
        this.mFeaturedWallpaperIV = mFeaturedWallpaperIV;
    }
}
