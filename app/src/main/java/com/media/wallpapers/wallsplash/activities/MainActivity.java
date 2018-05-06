package com.media.wallpapers.wallsplash.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.media.wallpapers.wallsplash.R;
import com.media.wallpapers.wallsplash.fragments.LatestWallpaperFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("WallSplash");
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tabs_layout);
        ViewPager pager = findViewById(R.id.viewPager);
        PagerAdapter adapter = new com.media.wallpapers.wallsplash.viewpager.PagerAdapter(getSupportFragmentManager(), getFlist(), getTabName());
        pager.setOffscreenPageLimit(3);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, adapter.getPageTitle(position) + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();
    }


    public List<String> getTabName() {
        List<String> tabName = new ArrayList<>();
        tabName.add("Latest");
        tabName.add("Featured");
        tabName.add("Collection");

        return tabName;
    }

    public List<Fragment> getFlist() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LatestWallpaperFragment());
        /*fragments.add(new FeaturedWallpaperFragment());
        fragments.add(new CollectionWallpaperFragment());*/
        return fragments;
    }
}
