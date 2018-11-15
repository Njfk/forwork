package com.forwork.com.forwork.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2018/11/14.
 */

public class BundleScrollView extends RelativeLayout {
    Rect total_normal = new Rect();
    Rect header_normal = new Rect();
    Rect title_normal = new Rect();
    Rect list_normal = new Rect();
    ViewGroup total;
    ViewGroup header;
    ViewGroup title;
    ViewGroup list;
    private String TAG = "BundleScrollView";

    public BundleScrollView(Context context) {
        super(context);
    }

    public BundleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BundleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 0) {
            total = (ViewGroup) getChildAt(0);
            header = (ViewGroup) total.getChildAt(1);
            title = (ViewGroup) total.getChildAt(0);
            list = (ViewGroup) total.getChildAt(2);
        }
    }

    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (header != null && title != null && list != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (header_normal.isEmpty()) {
                        header_normal.set(header.getLeft(), header.getTop(), header.getRight(), header.getBottom());
                    }
                    if (title_normal.isEmpty()) {
                        title_normal.set(title.getLeft(), title.getTop(), title.getRight(), title.getBottom());
                    }
                    if (list_normal.isEmpty()) {
                        list_normal.set(list.getLeft(), title.getBottom(), list.getRight(), list.getBottom());
                    }
                    if (total_normal.isEmpty()) {
                        total_normal.set(total.getLeft(), total.getTop(), total.getRight(), total.getBottom());
                    }

                    float y = ev.getY();
                    int v = (int) (downY - y) / 4;
                    downY = y;

                    if (v <= header.getHeight() / 2) {
                        title.layout(title.getLeft(), title.getTop() - v, title.getRight(), title.getBottom() - v);
                    }
                    if (v <= header.getHeight() / 2) {
                        Log.e(TAG, "onTouchEvent: "+(list.getTop() - v) +"----"+(list.getBottom() - v));
                        list.layout(list.getLeft(), list.getTop() - v, list.getRight(), list.getBottom() - v);
                    }
                    break;


                case MotionEvent.ACTION_UP:
                    if (!total_normal.isEmpty()&&!list_normal.isEmpty()&&!title_normal.isEmpty()) {
                        resetAnimotion();
                    }
                    break;
            }
        }

        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    public void resetAnimotion() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, title.getTop(), title_normal.top);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new BounceInterpolator());
        title.startAnimation(translateAnimation);

        title.layout(title_normal.left, title_normal.top, title_normal.right, title_normal.bottom);
        title_normal.setEmpty();

        //貌似是由于这个动画移动是相对于自身移动，所以用（list.getTop()-list_normal.top, 0）作为Y轴移动坐标，具体也没搞太明白
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0, 0,list.getTop()-list_normal.top, 0);
        translateAnimation2.setDuration(500);
        translateAnimation2.setFillAfter(false);
        translateAnimation2.setInterpolator(new BounceInterpolator());

        list.startAnimation(translateAnimation2);
        list.layout(list_normal.left, list_normal.top, list_normal.right, list_normal.bottom);
        list_normal.setEmpty();

//        TranslateAnimation translateAnimation3 = new TranslateAnimation(0, 0, total.getTop(), total_normal.top);
//        translateAnimation3.setDuration(300);
//        translateAnimation3.setInterpolator(new BounceInterpolator());
//        total.startAnimation(translateAnimation3);
//        total.layout(total_normal.left, total_normal.top, total_normal.right, total_normal.bottom);
//        total_normal.setEmpty();

    }

}
