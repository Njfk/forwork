package com.forwork.com.forwork.ui.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.ui.base.BaseActivity;
import com.forwork.com.forwork.ui.service.MusicService;

public class MusicActivity extends BaseActivity {


    MusicService musicService;
//    ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MusicService.MyBinder myBinder = (MusicService.MyBinder) service;
//            musicService = myBinder.getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void setStatusBar() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
