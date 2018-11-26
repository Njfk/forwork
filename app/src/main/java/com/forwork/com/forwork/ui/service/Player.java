package com.forwork.com.forwork.ui.service;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sev-14 on 2018/9/12.
 */

public class Player {
//implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener
    private String url = "http://abv.cn/music/光辉岁月.mp3";
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    int currentPosition = mediaPlayer.getCurrentPosition();
//                    int duration = mediaPlayer.getDuration();
//                    Log.e("222", "handleMessage: "+currentPosition+"---"+duration );
//                    if (duration > 0) {
//                        int i =  seekBar.getMax()*currentPosition / duration ;
//                        Log.e("222111", "handleMessage: "+i +"---"+seekBar.getMax());
//                        seekBar.setProgress(i);
//                    }
//
//
//                    break;
//            }
//        }
//    };
//    private Timer timer = new Timer();
//    TimerTask timerTask = new TimerTask() {
//        @Override
//        public void run() {
//            if (mediaPlayer == null)
//                return;
//            if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
//                handler.sendEmptyMessage(0); // 发送消息
//            }
//
//        }
//    };

    public Player(SeekBar seekBar) {
        this.seekBar = seekBar;

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setOnBufferingUpdateListener(this);
//            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        timer.schedule(timerTask, 0, 1000);
    }


    public void play(){
        mediaPlayer.start();
    }

    public void playurl(String url){
        url = this.url;
//        try {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(url);
            Log.e("22", "playurl: "+1 );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.e("22", "playurl: "+2 );

        } catch (IOException e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

//    public void pause(){
//        mediaPlayer.pause();
//    }
//
//    public void stop(){
//        if (mediaPlayer!=null){
//
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }
//
//
//    @Override
//    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        seekBar.setSecondaryProgress(percent);
//        int currentProgress = seekBar.getMax()
//                * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
//        Log.e(currentProgress + "% play", percent + " buffer");
//
//    }
//
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        mp.start();
//    }
}
