package com.forwork.com.forwork.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static android.graphics.Color.BLACK;

/**
 * Created by Administrator on 2018/12/5.
 */

public class MCodeUtils {
    public static Bitmap CreateOneDCode(String content) throws WriterException {
        // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.CODE_128, 500, 200);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static void setOneCodeImage(String text, ImageView targetView, Context context){
        String contentString = text;
        int size = contentString.length();
        for (int i = 0; i < size; i++) {
            int c = contentString.charAt(i);
            if ((19968 <= c && c < 40623)) {
                Toast.makeText(context, "text not be chinese", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Bitmap mBmpOneCode = null;
        try {
            if (contentString != null && !"".equals(contentString)) {
                mBmpOneCode = MCodeUtils.CreateOneDCode(contentString);
//                qrStrEditText.setText("");
//                mTextView.setText(contentString);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if (mBmpOneCode != null) {
            Glide.with(context).asBitmap().load(mBmpOneCode).into(targetView);
        }

    }
    public static void setOneCodeImage(String text, ImageView targetView, int w, int h, Context context){
        String contentString = text;
        int size = contentString.length();
        for (int i = 0; i < size; i++) {
            int c = contentString.charAt(i);
            if ((19968 <= c && c < 40623)) {
                Toast.makeText(context, "text not be chinese", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Bitmap mBmpOneCode = null;
        try {
            if (contentString != null && !"".equals(contentString)) {
                mBmpOneCode = MCodeUtils.CreateOneDCode(contentString);
//                qrStrEditText.setText("");
//                mTextView.setText(contentString);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if (mBmpOneCode != null) {
            Glide.with(context).asBitmap().apply(new RequestOptions().override(w,h)).load(mBmpOneCode).into(targetView);
        }

    }

}
