package com.forwork.com.forwork.ui.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.LazyFragment;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.material.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index5Fragment extends LazyFragment {
    @BindView(R.id.index_me_user_icon)
    ImageView index_me_user_icon;
    @BindView(R.id.index5_list)
    ListView index5_list;

    boolean isCreated = false;
    private String TAG = "index5";

    public Index5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index5, container, false);
        ButterKnife.bind(this, inflate);
        isCreated = true;

        return inflate;
    }

    @Override
    protected void lazyLoad() {
        if (isVisiable && isCreated) {
            isVisiable = false;
            isCreated = false;
            Glide.with(this)
                    .asBitmap()
                    .load(R.drawable.icon_face)
                    .apply(new RequestOptions().centerCrop())
                    .into(new BitmapImageViewTarget(index_me_user_icon) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            index_me_user_icon.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            index5_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e(TAG, "onItemClick: "+position );
                }
            });


            index_me_user_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: " );

                }
            });

        }
    }

    @Override
    public void refresh() {

    }

}
