package com.forwork.com.forwork.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.forwork.com.forwork.ui.base.App2;

/**
 * Created by Administrator on 2019/1/18.
 */

public class ViewUtils {
    public static LayoutInflater inflater;
    public static Context getContext(){
        return App2.getApplication();
    }
    public static View inflate(@LayoutRes int res){
        return View.inflate(getContext(),res,null);
    }
    public static void runOnUIThread(Runnable runnable){
        App2.getHandler().post(runnable);
    }
}
