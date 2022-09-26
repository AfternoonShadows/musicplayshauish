package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.myapplication.MusicPlayActivity;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 **/
public class MusicPlayService extends Service {
    private final String TAG = getClass().getSimpleName();
    private static MediaPlayer mediaPlayer;
    private static Timer timer;
    private static TimerTask timerTask;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new onBind();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //    添加计时器
    public static void addtimer() {
        Log.e("TAG", "addtimer");
        if (timer == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {

//                    创建消息对象
                    Message message = MusicPlayActivity.handler.obtainMessage();
//                    封装到对象
                    Bundle bundle = new Bundle();
                    bundle.putInt("MAX", mediaPlayer.getDuration());
                    bundle.putInt("CUR", mediaPlayer.getCurrentPosition());
                    message.setData(bundle);
//                    将消息发送到主线程
                    MusicPlayActivity.handler.sendMessage(message);
                }
            };
            timer.schedule(timerTask, 5, 500);
        }
    }

    public class onBind extends Binder {
        public void resume() {
            Log.e(TAG, "resume");
            mediaPlayer.start();
            addtimer();
        }

        public void pause() {
            Log.e(TAG, "pause");
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                timer.cancel();
                timer = null;
            }
        }

        public void next(String path) {
            Log.e(TAG, "next");
            play(path);

        }

        public void last(String path) {
            Log.e(TAG, "last");
            play(path);
        }

        public void play(String path) {
            Log.e(TAG, "play : " + path);
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            try {
                AssetFileDescriptor assetFileDescriptor = getAssets().openFd(path);
                mediaPlayer.reset();//重置路径
                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                mediaPlayer.prepare();
                mediaPlayer.start();
                addtimer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            if (mediaPlayer.isPlaying() && timer != null) {
                mediaPlayer.stop();
                timer.cancel();
            }
        }

        public void seekTo(int progress) {
            mediaPlayer.seekTo(progress);
        }
    }
}
