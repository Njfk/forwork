package com.forwork.com.forwork.view.dialog;


import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.forwork.com.forwork.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends DialogFragment {
    @BindView(R.id.loading_dialog1)
    ImageView imageView;

    public LoadingFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View inflate = inflater.inflate(R.layout.fragment_loading, container, false);
        ButterKnife.bind(this, inflate);

        init();
        return inflate;
    }

    private void init() {
        imageView.setImageResource(R.drawable.loading_anim);
        AnimationDrawable background = (AnimationDrawable) imageView.getDrawable();
        background.start();

//        RotateAnimation rotateAnimation = new RotateAnimation(-45, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setRepeatMode(Animation.INFINITE);
//        rotateAnimation.setDuration(500);
//        imageView.startAnimation(rotateAnimation);

//        AnimationSet animationSet = new AnimationSet(false);
//        final RotateAnimation rotateAnimation = new RotateAnimation(45,341,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
//        rotateAnimation.setDuration(1000);

//        animationSet.addAnimation(rotateAnimation);

//        imageView.startAnimation(animationSet);

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setWindowAnimations(R.style.dialog_anim);
        getDialog().setCanceledOnTouchOutside(true);
//        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
//        attributes.width = getResources().getDisplayMetrics().widthPixels;
//        getDialog().getWindow().setAttributes(attributes);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(R.style.loading_style,R.style.loading_style);


    }
}
