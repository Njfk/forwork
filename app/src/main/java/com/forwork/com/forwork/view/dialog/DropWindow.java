package com.forwork.com.forwork.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.forwork.com.forwork.R;

/**
 * Created by Administrator on 2019/2/19.
 */

public class DropWindow {
    PopupWindow popupWindow;
    LayoutInflater inflater;
    View convertView;
    Activity context;

    public DropWindow(Activity context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        convertView = inflater.inflate(R.layout.layout_pop, null);
        popupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.dialg_anim);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0));

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                lightOn();
            }
        });
//
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    popupWindow.dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    public void showAsDropDown(View view) {
        Log.e("--", "showAsDropDown: " + popupWindow.isShowing());
        if (!popupWindow.isShowing()) {
            lightOff();
            popupWindow.showAsDropDown(view);
        } else {
            popupWindow.dismiss();
        }
    }


    public void lightOn() {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 1f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        context.getWindow().setAttributes(lp);
    }

    public void lightOff() {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.3f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        context.getWindow().setAttributes(lp);
    }
}
