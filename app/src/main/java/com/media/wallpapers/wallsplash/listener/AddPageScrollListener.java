package com.media.wallpapers.wallsplash.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class AddPageScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotalItems = 0;
    private boolean loading = true;

    private int currentPage = 0;

    private GridLayoutManager layoutManager;

    protected AddPageScrollListener(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;

    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        if (layoutManager != null) {

            int visibleItemCount = recyclerView.getChildCount();

            int totalItemCount = layoutManager.getItemCount();


            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();


            if (loading) {
                if (totalItemCount > previousTotalItems) {
                    loading = false;
                    previousTotalItems = totalItemCount;
                }

            }

            int threshHold = 10;
            if (!loading && (totalItemCount - visibleItemCount) <= (lastVisibleItemPosition + threshHold)) {
                loading = true;
                currentPage++;


                loadMore(currentPage);
            }
        }

    }

    public abstract void loadMore(int currentPage);


}
