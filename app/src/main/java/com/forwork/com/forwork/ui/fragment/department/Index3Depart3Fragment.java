package com.forwork.com.forwork.ui.fragment.department;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.LazyFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index3Depart3Fragment extends LazyFragment {


    public Index3Depart3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index3_depart3, container, false);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void refresh() {

    }
}
