package com.media.wallpapers.wallsplash.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.media.wallpapers.wallsplash.R;
import com.media.wallpapers.wallsplash.adapter.LatestWallpaperAdapter;
import com.media.wallpapers.wallsplash.listener.AddPageScrollListener;
import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.rest.ApiClient;
import com.media.wallpapers.wallsplash.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestWallpaperFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = LatestWallpaperFragment.class.getSimpleName();
    //private TextView sampleText;
    String API_KEY = "fc12a678353fd3cda9afb77ebbdeec416b4352021c85051b174d033603596f6f";
    private LatestWallpaperAdapter adapter;
    private List<Photo> mWallpaperList;
    private SwipeRefreshLayout mSwipeRefresh;
    private int currentPage = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWallpaperList = new ArrayList<>();
        mSwipeRefresh = view.findViewById(R.id.latest_swipe_fm);
        RecyclerView latestWallpaperRv = view.findViewById(R.id.latest_wallpaper_rv);

        latestWallpaperRv.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        adapter = new LatestWallpaperAdapter(getActivity(), mWallpaperList, null);
        latestWallpaperRv.setAdapter(adapter);


        int pageNumber = 1;
        fetchData(pageNumber);

        mSwipeRefresh.setOnRefreshListener(this);
        AddPageScrollListener addPageScrollListener = new AddPageScrollListener((GridLayoutManager) latestWallpaperRv.getLayoutManager()) {
            @Override
            public void loadMore(int currentPage) {
                Log.i(TAG, "current page-->" + currentPage);


                //if(!isLoadOnce) {
                setCurrentPage(currentPage + 1);
                fetchData(getCurrentPage()
                );

                // onRefresh();

            }
        };
        latestWallpaperRv.addOnScrollListener(addPageScrollListener);
    }

    private void fetchData(int pageNumber) {

        mSwipeRefresh.setRefreshing(true);


        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Photo>> call = apiService.getPhotos(pageNumber, 30, "latest", API_KEY);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.code() == 200) {
                    Log.i(TAG, "response is Successfully achieved");
                    Log.i(TAG, "Photo Body---> :" + response.body());
                    Log.i(TAG, "Size--> :" + response.body().size());


                    mWallpaperList.addAll(response.body());
                    Toast.makeText(getActivity(), mWallpaperList.size() + " ", Toast.LENGTH_LONG).show();

                    adapter.notifyItemInserted(response.body().size());
                    mSwipeRefresh.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.i(TAG, "ERROR--->" + t.getMessage());
                showSnackBar(getString(R.string.check_your_internet_connection));
                mSwipeRefresh.setRefreshing(false);
                Toast.makeText(getActivity(), "Error--> " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRefresh() {
        fetchData(getCurrentPage());

        Toast.makeText(getActivity(), "swipe refreshing", Toast.LENGTH_SHORT).show();

    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void showSnackBar(String msg) {
        View v = getActivity().findViewById(R.id.latest_swipe_fm);
        Snackbar snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.load, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData(getCurrentPage());
            }
        });
        snackbar.show();
    }
}
