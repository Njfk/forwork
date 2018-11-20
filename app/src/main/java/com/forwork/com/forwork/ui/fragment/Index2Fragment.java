package com.forwork.com.forwork.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.forwork.com.forwork.ui.dialog.LoginDialog;
import com.forwork.com.forwork.ui.dialog.popwindow.ScreenPop;
import com.forwork.com.forwork.ui.fragment.department.Index2Depart1Fragment;
import com.forwork.com.forwork.ui.fragment.department.Index2Depart2Fragment;
import com.forwork.com.forwork.view.dialog.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index2Fragment extends LazyFragment {
    @BindView(R.id.index2_toolbar)
    Toolbar toolbar;
    @BindView(R.id.ishowpop)
    ImageView ishowpop;
    @BindView(R.id.index2_list_item)
    ListView listView;
    boolean isCreated = false;
    Handler handler = new Handler();

    Index2Depart1Fragment index2Depart1Fragment;
    Index2Depart2Fragment index2Depart2Fragment;
    ScreenPop pop;
    private String TAG = "index2Fragment";

    public Index2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index2, container, false);
        ButterKnife.bind(this, inflate);
        isCreated = true;
        lazyLoad();
        return inflate;
    }


    @Override
    protected void lazyLoad() {
        if (isVisiable && isCreated) {
            isCreated = false;
            isVisiable = false;
            final LoadingDialog loginDialog = new LoadingDialog(getActivity(), R.style.loading_style);
            loginDialog.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loginDialog.dismiss();
                }
            }, 2000);

            index2Depart1Fragment = new Index2Depart1Fragment();
            index2Depart2Fragment = new Index2Depart2Fragment();

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.index2_partment, index2Depart1Fragment);
            fragmentTransaction.commit();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position % 2 == 0) {
                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.index2_partment, index2Depart1Fragment);
                        fragmentTransaction.commit();
                    } else {
                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.index2_partment, index2Depart2Fragment);
                        fragmentTransaction.commit();
                    }
                }
            });


            ishowpop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pop==null){
                        pop = new ScreenPop(getActivity());
                    }
                    Log.e(TAG, "onClick: "+pop.isShowing() );
                    if (!pop.isShowing()){
                        pop.showAsDropDown(toolbar,0,0);
                    }
                }
            });
        }
    }

    @Override
    public void refresh() {

    }

    public void dismissPop(){
        if (pop!=null){
            pop.dismiss();
        }
    }
}
