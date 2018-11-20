package com.forwork.com.forwork.ui.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2018/11/6.
 */

public class App extends MultiDexApplication {
    private static App application;
    private String TAG = "app";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        ZXingLibrary.initDisplayOpinion(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static App getApplication() {
        return application;
    }
}
