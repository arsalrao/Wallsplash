package com.media.wallpapers.wallsplash.fragments;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.wallpapers.wallsplash.R;
import com.media.wallpapers.wallsplash.adapter.LatestWallpaperAdapter;
import com.media.wallpapers.wallsplash.model.viewmodel.PhotoViewModel;

public class LatestWallpaperFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LifecycleOwner {
    private static final String TAG = LatestWallpaperFragment.class.getSimpleName();
    private PhotoViewModel viewModel;
    //private TextView sampleText;
    private LatestWallpaperAdapter adapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mLatestRv;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView Called");
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getPhotos().observe(this, pageList -> {
            adapter.submitList(pageList);
        });

        mSwipeRefresh = view.findViewById(R.id.latest_swipe_fm);
        viewModel.getLoadStatus().observe(this, status -> {

                    assert status != null;
                    switch (status) {
                        case LOADING:
                            mSwipeRefresh.setRefreshing(true);
                            Log.i(TAG, "Loading--> swipe true");

                            break;
                        case LOADED:
                            mSwipeRefresh.setRefreshing(false);
                            Log.i(TAG, "Loaded--> swipe false");
                            break;
                        case FAILED:
                            mSwipeRefresh.setRefreshing(false);
                            Log.i(TAG, "Failed--> swipe false");
                            showSnackBar(getString(R.string.check_internet));
                            break;
                        default:
                            mSwipeRefresh.setRefreshing(false);
                            Log.i(TAG, "swipe false by default");


                    }

                }
        );

        mLatestRv = view.findViewById(R.id.latest_wallpaper_rv);

        setupLayoutmanager();
        adapter = new LatestWallpaperAdapter(getContext(), null, null);

        mLatestRv.setAdapter(adapter);
        mSwipeRefresh.setOnRefreshListener(this);

    }

    private void setupLayoutmanager() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        mLatestRv.setLayoutManager(linearLayoutManager);
    }


    public void showSnackBar(String msg) {
        View v = getActivity().findViewById(R.id.latest_swipe_fm);
        Snackbar snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.load, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fetchData(getCurrentPage());
            }
        });
        snackbar.show();
    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(false);
    }
}
