package com.forwork.com.forwork.ui.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.RemoteViews;

import com.forwork.com.forwork.R;
import com.forwork.com.forwork.bean.base.Music;
import com.forwork.com.forwork.ui.activity.LockActivity;

import java.io.IOException;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;
    private IntentFilter filter;
    private IntentFilter play_filter;
    private BindLockScreen bindLockScreen;
    PlayReceiver playReceiver;
    NotificationManager mNofiticationManger;
    Notification notification;
    MyBinder myBinder = new MyBinder();
    private String url;
    private String TAG = "musicService";
    private String name = "";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }

    public class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNofiticationManger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource("");
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        play_filter = new IntentFilter();
        play_filter.addAction("play");
        play_filter.addAction("pause");
        play_filter.addAction("next");
        bindLockScreen = new BindLockScreen();
        playReceiver = new PlayReceiver();
        showNotification();
    }

    public void showNotification() {
        notification = new Notification(R.drawable.ic_ol_playall, "", System.currentTimeMillis());
        notification.flags = Notification.FLAG_NO_CLEAR;
        RemoteViews remoteView = new RemoteViews(this.getPackageName(), R.layout.item_music_notification);
        remoteView.setImageViewResource(R.id.notification_icon, R.drawable.ic_ol_playall);

        Intent play = new Intent("play");
        PendingIntent play_intent = PendingIntent.getBroadcast(this, 0, play, 0);
        remoteView.setOnClickPendingIntent(R.id.notification_pre, play_intent);
        Intent pause = new Intent("pause");
        PendingIntent pause_intent = PendingIntent.getBroadcast(this, 0, pause, 0);
        remoteView.setOnClickPendingIntent(R.id.notification_pause, pause_intent);
        Intent next = new Intent("next");
        PendingIntent next_intent = PendingIntent.getBroadcast(this, 0, next, 0);
        remoteView.setOnClickPendingIntent(R.id.notification_next, next_intent);


        notification.contentView = remoteView;
        mNofiticationManger.notify(100, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(bindLockScreen, filter);
        registerReceiver(playReceiver, play_filter);
        url = intent.getStringExtra("listenUrl");
        name = intent.getStringExtra("listenName");
        notification.contentView.setTextViewText(R.id.notification_title,name);
        mNofiticationManger.notify(100,notification);
        Log.e(TAG, "onStartCommand: " + url);
        play();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        registerReceiver(bindLockScreen, filter);
        super.onDestroy();
    }

    public void play() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
            notification.contentView.setImageViewResource(R.id.notification_pause, R.drawable.ic_notifaction_pause);
            mNofiticationManger.notify(100, notification);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "play: ");
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            notification.contentView.setImageViewResource(R.id.notification_pause, R.drawable.ic_notification_play_normal);
            mNofiticationManger.notify(100, notification);
        }else {
            play();
        }
        Log.e(TAG, "pause: ");
    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.e(TAG, "stop: ");
    }


    public class BindLockScreen extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                Intent lockscreen = new Intent(MusicService.this, LockActivity.class);
                lockscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lockscreen);
            }
        }
    }

    public class PlayReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e(TAG, "PlayReceiver: " + action);
            if (action.equals("play")) {
                play();
            } else if (action.equals("pause")) {
                pause();
            } else if (action.equals("next")) {

            }
        }
    }
}
