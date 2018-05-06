package com.media.wallpapers.wallsplash.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.wallpapers.wallsplash.R;
import com.media.wallpapers.wallsplash.adapter.FeaturedWallpaperAdapter;
import com.media.wallpapers.wallsplash.model.viewmodel.FeaturedPhotoViewModel;

public class FeaturedWallpaperFragment extends Fragment {
    private RecyclerView mFeaturedRv;
    private FeaturedPhotoViewModel viewModel;
    private FeaturedWallpaperAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_featured, container, false);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(FeaturedPhotoViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getPhotos().observe(this, photos -> mAdapter.submitList(photos));
        mSwipeRefreshLayout = view.findViewById(R.id.featured_swipe_rl);
        viewModel.getLoadStatus().observe(this, status -> {
            assert status != null;
            switch (status) {
                case LOADING:
                    mSwipeRefreshLayout.setRefreshing(true);
                    break;
                case LOADED:
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                case FAILED:
                    mSwipeRefreshLayout.setRefreshing(false);
                    showSnackBarMsg(getString(R.string.check_your_internet_connection));
                    break;
                default:
                    mSwipeRefreshLayout.setRefreshing(false);
                    showSnackBarMsg(getString(R.string.check_your_internet_connection));
            }
        });
        mFeaturedRv = view.findViewById(R.id.featured_rv);
        mAdapter = new FeaturedWallpaperAdapter(getContext());
        mFeaturedRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturedRv.setAdapter(mAdapter);


    }

    private void showSnackBarMsg(String msg) {
        View v = getActivity().findViewById(R.id.featured_swipe_rl);
        Snackbar snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.load, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        snackbar.show();
    }
}
