package com.colvengames.wallpapertumblr.config;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.colvengames.wallpapertumblr.fragments.CategoryFragment;
import com.colvengames.wallpapertumblr.fragments.FavoritesFragment;
import com.colvengames.wallpapertumblr.fragments.Home;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int count;

    public PagerAdapter(FragmentManager fm, int c) {
        super(fm);
        this.count = c;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Home HomeFragment = new Home();
                return HomeFragment;
            case 1:
                CategoryFragment categoryFragment = new CategoryFragment();
                return categoryFragment;
            case 2:
                FavoritesFragment favoritesFragment = new FavoritesFragment();
                return favoritesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
