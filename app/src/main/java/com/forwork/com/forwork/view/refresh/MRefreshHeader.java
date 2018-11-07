package com.forwork.com.forwork.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.forwork.com.forwork.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by Administrator on 2018/10/31.
 */

public class MRefreshHeader extends LinearLayout implements RefreshHeader {
    LayoutInflater inflater;
    ImageView imageView;
    private boolean hasSetPullDownAnim = false;

    private int[] imge_ids = new int[]{
            R.drawable.p10001,
            R.drawable.p10002,
            R.drawable.p10003,
            R.drawable.p10004,
            R.drawable.p10005,
            R.drawable.p10006,
            R.drawable.p10007,
            R.drawable.p10008,
            R.drawable.p10009,
            R.drawable.p10010,
            R.drawable.p10011,
            R.drawable.p10012,
            R.drawable.p10013
    };

    private AnimationDrawable pullDownAnim;
    private AnimationDrawable refreshingAnim;
    private String TAG = "refreshheader";

    public MRefreshHeader(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        init(context);


    }

    public MRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init(context);
    }

    public MRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        View header_view = View.inflate(context,R.layout.refresh_header_amin, this);
        imageView = header_view.findViewById(R.id.refresh_header_imge);

    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Scale;
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        // 下拉的百分比小于100%时，不断调用 setScale 方法改变图片大小
        if (percent < 1) {
            int position = (int) (percent*imge_ids.length);
            imageView.setImageResource(imge_ids[position]);
//            imageView.setScaleX(percent);
//            imageView.setScaleY(percent);

            //是否执行过翻跟头动画的标记
            if (hasSetPullDownAnim) {
                hasSetPullDownAnim = false;
            }
        }else {
            imageView.setImageResource(imge_ids[12]);

        }

        //当下拉的高度达到Header高度100%时，开始加载正在下拉的初始动画，即翻跟头
        if (percent >= 1.0) {
            //因为这个方法是不停调用的，防止重复
            if (!hasSetPullDownAnim) {
                imageView.setImageResource(R.drawable.pulltorefresh_anim);
                pullDownAnim = (AnimationDrawable) imageView.getDrawable();
//                pullDownAnim.start();

                hasSetPullDownAnim = true;
            }
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (pullDownAnim != null && pullDownAnim.isRunning()) {
            pullDownAnim.stop();
        }
        if (refreshingAnim != null && refreshingAnim.isRunning()) {
            refreshingAnim.stop();
        }
        //重置状态
        hasSetPullDownAnim = false;
        return 0;

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        Log.e(TAG, "onStateChanged: "+newState );
        switch (newState) {
            case PullDownToRefresh: //下拉刷新开始。正在下拉还没松手时调用
                //每次重新下拉时，将图片资源重置为小人的大脑袋
                imageView.setImageResource(R.drawable.p10013);
                break;
            case Refreshing: //正在刷新。只调用一次
                //状态切换为正在刷新状态时，设置图片资源为小人卖萌的动画并开始执行
                imageView.setImageResource(R.drawable.pulltorefresh_anim);
                refreshingAnim = (AnimationDrawable) imageView.getDrawable();
                refreshingAnim.start();
                break;
            case ReleaseToRefresh:

                break;
        }
    }
}
