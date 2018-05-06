package com.media.wallpapers.wallsplash.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.wallpapers.wallsplash.R;
import com.media.wallpapers.wallsplash.holder.FeaturedWallpaperHolder;
import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.utill.GlideApp;

public class FeaturedWallpaperAdapter extends PagedListAdapter<Photo, FeaturedWallpaperHolder> {
    private static final DiffUtil.ItemCallback<Photo> DIFF_CALL_BACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.getPhotoId().equals(newItem.getPhotoId());
        }

        @Override
        public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.equals(newItem);
        }
    };
    private Context mContext;


    public FeaturedWallpaperAdapter(Context context) {
        super(DIFF_CALL_BACK);
        this.mContext = context;
    }

    @NonNull
    @Override
    public FeaturedWallpaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_latest, parent, false);
        return new FeaturedWallpaperHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedWallpaperHolder holder, int position) {
        Photo photo = getItem(position);
        assert photo != null;
        String paletteRGBColor = photo.getPhotoColor() != null ? photo.getPhotoColor() : mContext.getString(R.string.default_palette_color);
        int color = Color.parseColor(paletteRGBColor);
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int height = photo.getPhotoHeight();
        int width = photo.getPhotoWidth();

        float finalHeight = displayMetrics.widthPixels / ((float) width / (float) height);


        GlideApp.with(mContext).load(photo.getPhotoUrl().getRegular())
                .placeholder(new ColorDrawable(color))
                .fallback(R.mipmap.ic_launcher_round)
                .into(holder.getmFeaturedWallpaperIV());
        holder.getmFeaturedWallpaperIV().setMinimumHeight((int) finalHeight);


    }
}
