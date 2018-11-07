package com.forwork.com.forwork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.forwork.com.forwork.R;

/**
 * Created by Administrator on 2018/11/6.
 */

public class PriceText extends TextView {
    float textSize;
    private String TAG = "priceText";

    public PriceText(Context context) {
        this(context,null);
    }

    public PriceText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PriceText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.price_title);
        textSize = typedArray.getDimension(R.styleable.price_title_textSize,0.5f);
    }

    public void setText(String text) {
        super.setText("");
        int indexStart = 0;
        while (true) {
            int start = text.indexOf("￥");

            if (start < 0) {
                append(text.substring(indexStart));
                break;
            }
            append(text.substring(indexStart, start));

            SpannableString spannableString = new SpannableString(text.substring(start, start + 1));
            spannableString.setSpan(new RelativeSizeSpan(textSize), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            append(spannableString);
            text = text.substring(start + 1);
        }
        setMovementMethod(LinkMovementMethod.getInstance());

    }

}
