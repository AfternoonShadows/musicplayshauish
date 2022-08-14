package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.myapplication.MusicPage;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 **/
public class MusicPlayService extends Service {
    private final String TAG= "MusicPlayService";
    private static MediaPlayer mediaPlayer;
    private static Timer timer;
    private static TimerTask timerTask;
    private Boolean musicstatus = false;
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
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
    public static void addtimer()
    {
        if(timer==null) {
            timer = new Timer();
            timerTask= new TimerTask() {
                @Override
                public void run() {

//                    创建消息对象
                    Message message = MusicPage.handler.obtainMessage();
//                    封装到对象
                    Bundle bundle =new Bundle();
                    bundle.putInt("MAX",mediaPlayer.getDuration());
                    bundle.putInt("CUR",mediaPlayer.getCurrentPosition());
                    message.setData(bundle);
//                    将消息发送到主线程
                    MusicPage.handler.sendMessage(message);

                }
            };
            timer.schedule(timerTask,5,500);
        }
    }

    public void resume() {
        Log.i(TAG,"resume");
        mediaPlayer.start();
        addtimer();
    }
    public void pause(){
        Log.i(TAG,"pause");
        mediaPlayer.pause();
    }
    public void next(String path){
        Log.i(TAG,"next");
        play(path);

    }
    public void last(String path){
        Log.i(TAG,"last");
        play(path);
    }
    public void play(String path){
        Log.i(TAG,path);
        File path1 =new File(path);
        if(musicstatus) {
            mediaPlayer.stop();
        }
        try {
            mediaPlayer.reset();//重置路径
            mediaPlayer.setDataSource(path1.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            musicstatus=true;
            addtimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop(){
        mediaPlayer.stop();
        musicstatus = false;
    }
    public void seekTo(int progress){
        mediaPlayer.seekTo(progress);
    }
}
