package com.forwork.com.forwork.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/10/23.
 */

public class MTextView extends android.support.v7.widget.AppCompatTextView{

    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/huayuncaiti.TTF");
        super.setTypeface(tf);
    }
}
