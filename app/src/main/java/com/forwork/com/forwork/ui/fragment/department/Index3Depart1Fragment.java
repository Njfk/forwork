package com.forwork.com.forwork.ui.fragment.department;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index3Depart1Fragment extends Fragment {
    @BindView(R.id.index3Depart1Viewpager)
    ViewPager index3Depart1Viewpager;

    public Index3Depart1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index3_depart1, container, false);
        ButterKnife.bind(this,inflate);
        return inflate;
    }

}
