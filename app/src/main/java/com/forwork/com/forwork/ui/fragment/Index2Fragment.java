package com.forwork.com.forwork.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.forwork.com.forwork.ui.dialog.LoginDialog;
import com.forwork.com.forwork.view.dialog.LoadingDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index2Fragment extends LazyFragment {
    boolean isCreated = false;

    public Index2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index2, container, false);
        isCreated = true;
        lazyLoad();
        return inflate;
    }

    @Override
    protected void lazyLoad() {
        if (isVisiable&&isCreated){
            LoadingDialog loginDialog = new LoadingDialog(getActivity(),R.style.loading_style);
            loginDialog.show();
        }
    }

    @Override
    public void refresh() {

    }
}
