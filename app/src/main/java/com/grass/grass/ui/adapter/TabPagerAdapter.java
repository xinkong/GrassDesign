package com.grass.grass.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabPagerAdapter(FragmentManager fm,List<Fragment> lists){
        super(fm);
        this.mFragments = lists;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}