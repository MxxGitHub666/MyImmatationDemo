package com.mxx.myimmatationdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.mxx.myimmatationdemo.fragment.BaseFragment;

import java.util.List;

public class SubPagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragments;
    private List<String> titles;

    public SubPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<BaseFragment> fragments, List<String> titles) {
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
