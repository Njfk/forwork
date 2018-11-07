package com.forwork.com.forwork.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.forwork.com.forwork.R;

/**
 * Created by Administrator on 2018/11/6.
 */

public class LoadingDialog extends Dialog{
    LayoutInflater inflater;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View inflate = inflater.inflate(R.layout.fragment_loading, null);
        ImageView imageView =  inflate.findViewById(R.id.loading_dialog1);

        imageView.setImageResource(R.drawable.loading_anim);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.start();
        setContentView(inflate);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = getContext().getResources().getDisplayMetrics().widthPixels;
        getWindow().setWindowAnimations(R.style.dialog_anim);
        getWindow().setAttributes(attributes);
    }

    public void init(Context context){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        inflater = LayoutInflater.from(context);
    }
}
