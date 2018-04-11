package com.media.wallpapers.wallsplash.viewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> tabName;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabName) {
        super(fm);
        this.fragments = fragments;
        this.tabName = tabName;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabName.get(position);
    }
}
