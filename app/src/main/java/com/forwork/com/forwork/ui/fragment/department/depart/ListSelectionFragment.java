package com.forwork.com.forwork.ui.fragment.department.depart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSelectionFragment extends Fragment {
    @BindView(R.id.selection_list)
    RecyclerView selection_list;

    public ListSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_list_selection, container, false);
        ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }


    public void init(){

    }
}
