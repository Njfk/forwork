package com.forwork.com.forwork.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.forwork.com.forwork.R;

/**
 * Created by Administrator on 2018/10/23.
 */

public class MyDialog extends Dialog{
    LayoutInflater inflater;
   Context context;
    public MyDialog(@NonNull Context context) {
        super(context);

        inflater = LayoutInflater.from(context);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        inflater = LayoutInflater.from(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            getWindow().setWindowAnimations(R.style.dialg_anim);

        final View inflate2 = inflater.inflate(R.layout.dialog_login, null);
//        icon = inflate2.findViewById(R.id.icon);
//        icon.setImageResource(R.drawable.anim_loading);
//        animation = (AnimationDrawable) icon.getDrawable();
//
//        animation.start();
        setContentView(inflate2);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = context.getResources().getDisplayMetrics().widthPixels;
        getWindow().setAttributes(p);




    }

}
