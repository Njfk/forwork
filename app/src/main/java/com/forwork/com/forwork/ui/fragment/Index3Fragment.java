package com.forwork.com.forwork.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.forwork.com.forwork.ui.fragment.adapter.Index3PagerAdapter;
import com.forwork.com.forwork.ui.fragment.department.Index3Depart1Fragment;
import com.forwork.com.forwork.ui.fragment.department.Index3Depart2Fragment;
import com.forwork.com.forwork.ui.fragment.department.Index3Depart3Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index3Fragment extends LazyFragment {
    @BindView(R.id.index3_viewpager)
    ViewPager index3_viewpager;
    @BindView(R.id.index3_tab)
    TabLayout index3_tab;

    Index3PagerAdapter index3PagerAdapter;

    boolean isCreated = false;

    public Index3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index3, container, false);
        ButterKnife.bind(this, inflate);
        isCreated = true;
        return inflate;
    }

    @Override
    protected void lazyLoad() {
        if (isVisiable && isCreated) {
            List<String> titles = new ArrayList<>();
            titles.add("衣服");
            titles.add("裤子");
            titles.add("裙子");
            titles.add("羽绒服");
            titles.add("鞋靴");
            titles.add("包箱");
            titles.add("电子产品");
            titles.add("医疗用品");
            titles.add("护肤品");
            titles.add("其他");

            List<Fragment> fragments=new ArrayList<>();
            fragments.add(new Index3Depart1Fragment());
            fragments.add(new Index3Depart2Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());
            fragments.add(new Index3Depart3Fragment());

            index3PagerAdapter = new Index3PagerAdapter(fragments,titles,getChildFragmentManager());
            index3_viewpager.setAdapter(index3PagerAdapter);
            index3_tab.setupWithViewPager(index3_viewpager);

            isVisiable = false;
            isCreated = false;
        }
    }

    @Override
    public void refresh() {

    }
}
