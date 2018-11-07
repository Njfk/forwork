package com.forwork.com.forwork.ui.base;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2018/11/6.
 */

public class App extends Application{
    private static  App application;
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        application = this;
    }

    public static App getApplication(){
        return application;
    }
}
