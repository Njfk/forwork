package com.forwork.com.forwork.ui.fragment.department;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.forwork.com.forwork.R;
import com.forwork.com.forwork.view.tag.ImageDotLayout;
import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Index3Depart2Fragment extends Fragment {
    @BindView(R.id.index3_depart2_image_dot_layout)
    ImageDotLayout dotLayout;

    public Index3Depart2Fragment() {
        // Required empty public constructor
    }
    Handler handler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index3_depart2, container, false);
        ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }

    private void init() {


        dotLayout.setImage(R.drawable.navigation_1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p10013);
        final Drawable drawable = new BitmapDrawable(bitmap);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    dotLayout.addIcon(i, 0.2f * i, 0.1f * i, drawable);
                }
            }
        },1000);



    }

}
