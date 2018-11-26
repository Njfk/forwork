package com.forwork.com.forwork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.DynamicLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.forwork.com.forwork.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/11/20.
 */

public class SessionContentTextView extends android.support.v7.widget.AppCompatTextView{
    private int limitLines = 0;
    Context context;
    public SessionContentTextView(Context context) {
        this(context, null);

        this.context = context;
    }

    public SessionContentTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        this.context = context;
    }

    public SessionContentTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SessionContentTextView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();//获取自定义属性个数
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.SessionContentTextView_m_limit_lines:
                    limitLines = typedArray.getInt(index,1);
                    break;
            }

        }
        typedArray.recycle();
    }

    public void setText(String text){
        int indexStart = 0;
        while (true){
              int start = text.indexOf(" @");
              int end = text.indexOf("  ");
            if (start<0||end<0){
                append(text.substring(indexStart));
                break;
            }
            final String substring = text.substring(start, end + 1);
            append(text.substring(indexStart,start));
            SpannableString spannableString = new SpannableString(text.substring(start,end+1));
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Snackbar.make(SessionContentTextView.this, substring,Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(ContextCompat.getColor(getContext(), R.color.blue_2));
                    ds.setUnderlineText(false);
                }
            },0,end+1-start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            append(spannableString);
            text = text.substring(end+1);

        }
        setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            StaticLayout layout = null;
            Field field = null;
            try {
                Field staticField = DynamicLayout.class.getDeclaredField("sStaticLayout");
                staticField.setAccessible(true);
                layout = (StaticLayout) staticField.get(DynamicLayout.class);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (layout != null) {
                try {
                    field = StaticLayout.class.getDeclaredField("mMaximumVisibleLineCount");
                    field.setAccessible(true);
                    field.setInt(layout, limitLines);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (layout != null && field != null) {
                try {
                    field.setInt(layout, Integer.MAX_VALUE);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
    }
}
