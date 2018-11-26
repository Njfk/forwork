package com.forwork.com.forwork.ui.fragment.department.depart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.fragment.department.depart.adapter.ListSelectionAdapter;
import com.forwork.com.forwork.ui.fragment.department.depart.bean.Selection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListSelectionFragment extends Fragment {
    @BindView(R.id.selection_list)
    RecyclerView selection_list;

    ListSelectionAdapter listSelectionAdapter;

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
        List<Selection> selections = new ArrayList<>();
        for (int i = 0;i<10;i++){
            selections.add(new Selection(R.drawable.p10013,""+i));
        }
        listSelectionAdapter = new ListSelectionAdapter(selections,getActivity());
        selection_list.setLayoutManager(new GridLayoutManager(getActivity(),5));
        selection_list.setAdapter(listSelectionAdapter);
    }
}
