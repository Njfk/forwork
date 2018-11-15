package com.forwork.com.forwork.reader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.reader.view.Config;
import com.forwork.com.forwork.reader.view.PageFactory;
import com.forwork.com.forwork.reader.view.PageWidget;
import com.forwork.com.forwork.reader.view.utils.BitmapUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    PageWidget pageWidget;
    private int mWidth;
    private int mHeight;

    Bitmap bitmap2;
    private Paint mPaint;
    private String TAG = "reader";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        mContext = this;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        mWidth = getResources().getDisplayMetrics().widthPixels;


        init();
    }

    private void init() {
        pageWidget = (PageWidget) findViewById(R.id.pageWidget);

        //必加 否则翻页带阴影
        if(Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 19){
            pageWidget.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 画笔
        mPaint.setTextAlign(Paint.Align.LEFT);// 左对齐
//        mPaint.setTextSize(m_fontSize);// 字体大小
//        mPaint.setColor(m_textColor);// 字体颜色
//        mPaint.setTypeface(typeface);
        mPaint.setSubpixelText(true);// 设置该项为true，将有助于文本在LCD屏幕上的显示效果



        pageWidget.setPageMode(Config.PAGE_MODE_SIMULATION);
        pageWidget.setBgColor(this.getResources().getColor(R.color.read_background_paperYellow));


        PageFactory.setmStatus(PageFactory.FINISH);


        setBookBg(Config.createConfig(this).getBookBgType());

        final Page page = new Page();
        page.setNum(1);
        onDraw(pageWidget.getCurPage(),page.getNum());
        onDraw(pageWidget.getNextPage(),page.getNum());

        //必加触摸事件

        pageWidget.setTouchListener(new PageWidget.TouchListener() {
            @Override
            public void center() {

            }

            @Override
            public Boolean prePage() {

                Log.e(TAG, "prePage: " );
                //返回true 表明存在上一页
                if (page.getNum() ==1){
                    return false;
                }else {
                    onDraw(pageWidget.getCurPage(),page.getNum() );
                    onDraw(pageWidget.getNextPage(),page.getNum() -1);
                    page.setNum(page.getNum() -1);
                }

                return true;
            }

            @Override
            public Boolean nextPage() {
                onDraw(pageWidget.getCurPage(),page.getNum() );
                onDraw(pageWidget.getNextPage(),page.getNum() +1);
                page.setNum(page.getNum() +1);
                return true;
            }

            @Override
            public void cancel() {
            }
        });

    }


    //绘制文字
    public void onDraw(Bitmap bitmap,int page) {
        List<String> m_lines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            m_lines.add("测试文本****第"+page+"页,第"+i+"*****行");
        }

        Canvas c = new Canvas(bitmap);
        c.drawBitmap(bitmap2, 0, 0, null);
//        word.setLength(0);
        c.drawColor(getResources().getColor(R.color.read_background_paperYellow));

        mPaint.setTextSize(55f);
        mPaint.setColor(Color.BLUE);

        if (m_lines.size() == 0) {
            return;
        }

        if (m_lines.size() > 0) {
            float y = 100;
            for (String strLine : m_lines) {
                y += 20 + 50;
                c.drawText(strLine, 100, y, mPaint);
//                word.append(strLine);
            }
        }

        pageWidget.postInvalidate();
    }



    //设置页面的背景
    Context mContext;

    public void setBookBg(int type) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        int color = 0;
        switch (type) {
            case Config.BOOK_BG_DEFAULT:
                canvas = null;
                bitmap.recycle();
                bitmap = BitmapUtil.decodeSampledBitmapFromResource(
                        mContext.getResources(), R.drawable.paper, mWidth, mHeight);
                color = mContext.getResources().getColor(R.color.read_font_default);
                setBookPageBg(mContext.getResources().getColor(R.color.read_bg_default));
                break;
            case Config.BOOK_BG_1:
                canvas.drawColor(mContext.getResources().getColor(R.color.read_bg_1));
                color = mContext.getResources().getColor(R.color.read_font_1);
                setBookPageBg(mContext.getResources().getColor(R.color.read_bg_1));
                break;
            case Config.BOOK_BG_2:
                canvas.drawColor(mContext.getResources().getColor(R.color.read_bg_2));
                color = mContext.getResources().getColor(R.color.read_font_2);
                setBookPageBg(mContext.getResources().getColor(R.color.read_bg_2));
                break;
            case Config.BOOK_BG_3:
                canvas.drawColor(mContext.getResources().getColor(R.color.read_bg_3));
                color = mContext.getResources().getColor(R.color.read_font_3);
                if (pageWidget != null) {
                    pageWidget.setBgColor(mContext.getResources().getColor(R.color.read_bg_3));
                }
                break;
            case Config.BOOK_BG_4:
                canvas.drawColor(mContext.getResources().getColor(R.color.read_bg_4));
                color = mContext.getResources().getColor(R.color.read_font_4);
                setBookPageBg(mContext.getResources().getColor(R.color.read_bg_4));
                break;
        }

        setBgBitmap(bitmap);
        //设置字体颜色
    }


    public void setBookPageBg(int color){
        if (pageWidget != null) {
            pageWidget.setBgColor(color);
        }
    }

    public void setBgBitmap(Bitmap bgBitmap) {
        this.bitmap2 = bgBitmap;
    }

    class Page{
        int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
