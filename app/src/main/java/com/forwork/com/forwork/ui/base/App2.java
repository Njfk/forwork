package com.forwork.com.forwork.ui.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2018/11/6.
 */

public class App2 extends Application {
    private static App2 application;

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        application = this;
    }


    public static App2 getApplication() {
        return application;
    }
}
