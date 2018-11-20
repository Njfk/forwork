package com.forwork.com.forwork.ui.fragment.department;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.IndexBean1;
import com.forwork.com.forwork.bean.base.Product;
import com.forwork.com.forwork.net.ListApi;
import com.forwork.com.forwork.net.presenter.ListApiPresenter;
import com.forwork.com.forwork.net.view.IListView;
import com.forwork.com.forwork.ui.adapter.IndexList1Adapter;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.forwork.com.forwork.ui.fragment.adapter.Indexdepart1List1Adapter;
import com.forwork.com.forwork.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index2Depart1Fragment extends LazyFragment implements IListView {
    @BindView(R.id.index2_department1_list1)
    RecyclerView recyclerView;
    Indexdepart1List1Adapter indexList1Adapter;

    List<Product> products = new ArrayList<>();
    ListApiPresenter listApiPresenter;

    boolean isCreated = false;
    private String TAG = "index_depart1";

    public Index2Depart1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index2_depart1, container, false);
        ButterKnife.bind(this, inflate);
        isCreated = true;
        init();
        lazyLoad();
        return inflate;
    }

    private void init() {
        listApiPresenter = new ListApiPresenter();

        indexList1Adapter = new Indexdepart1List1Adapter(products, getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addItemDecoration(new SpaceItemDecoration(5));
        recyclerView.setAdapter(indexList1Adapter);
    }

    @Override
    protected void lazyLoad() {
        Log.e(TAG, "lazyLoad: "+isCreated+isVisiable );

        if (isCreated) {
            products.clear();
            listApiPresenter.attchView(this);
            listApiPresenter.getList1(1);
            isVisiable = false;
            isCreated = false;
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void error(int code) {

    }

    @Override
    public void getListSuccess(IndexBean1 indexBean1) {

        if (indexBean1 != null) {
            products.addAll(indexBean1.getData().getItems());
        }
        indexList1Adapter.notifyDataSetChanged();
    }
}
