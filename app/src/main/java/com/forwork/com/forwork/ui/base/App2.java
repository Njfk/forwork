package com.forwork.com.forwork.ui.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2018/11/6.
 */

public class App2 extends Application {
    private static App2 application;
    private String TAG = "app";
    private static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        application = this;
//        MemberSDK.setEnvironment(Environment.TEST);
//        MemberSDK.turnOnDebug();
//
//        MemberSDK.init(this, new InitResultCallback() {
//            @Override
//            public void onFailure(int code, String msg) {
//                Log.e(TAG, "onFailure:" + code + "," + msg);
//            }
//
//            @Override
//            public void onSuccess() {
//                Log.e(TAG, "onSuccess:");
//            }
//        });
    }


    public static App2 getApplication() {
        return application;
    }
    public static Handler getHandler(){
        if (handler == null){
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;

    }
}
