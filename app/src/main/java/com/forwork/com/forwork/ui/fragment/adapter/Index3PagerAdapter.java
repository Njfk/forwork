package com.forwork.com.forwork.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */

public class Index3PagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    List<String> titles;

    public Index3PagerAdapter(List<Fragment> fragments, List<String> titles,FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
