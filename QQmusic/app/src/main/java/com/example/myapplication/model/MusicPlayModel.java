package com.example.myapplication.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.base.baseModel;
import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.service.MusicPlayService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 **/
public class MusicPlayModel extends baseModel {
    private String TAG = "MusicPlayModel";
    private MusicPlayModelListener musicPlayModelListener;
    private MusicPlayService.onBind onBind;
    private Context mContext;
    private List<MusicSongBean> list = new ArrayList<MusicSongBean>();
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (onBind == null) {
                onBind = (MusicPlayService.onBind) iBinder;
                Log.e(TAG, "onServiceConnected");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected");
        }
    };

    public MusicPlayModel(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    public void init() {
        if (onBind == null) {
            connect();
        }
    }

    //    设置监听
    public void setMusicPlayModelListener(MusicPlayModelListener musicPlayModelListener) {
        this.musicPlayModelListener = musicPlayModelListener;
    }

    //    连接服务
    public void connect() {
        Log.e(TAG, "connect");
//        "com.example.myapplication"
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(mContext.getPackageName(), "com.example.myapplication.service.MusicPlayService");
        intent.setComponent(componentName);
        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService() {
        mContext.unbindService(connection);
    }

    //    修改正在播放音乐的颜色
    public void musicListInfoChange() {
    }

    //    修改成正在播放音乐的信息
    public void musicTextInfoChange() {
    }

    //    进度条信息修改
    public void seekBarInfoChange(boolean judge, int position) {
        //    修改音乐播放进度条
        if (onBind != null) {
            onBind.seekTo(position);
        }else{
            Log.e(TAG, "no:");
        }
    }

    //    上一曲
    public void TuneUp(int position) {
        if (onBind != null) {
            onBind.next(getMusicSong().get(position).getPath());
            musicPlayModelListener.init(list.get(position));
        }
        Log.e(TAG, "TuneUp:" + position);
        //    歌曲切换
    }

    //    下一曲
    public void TuneDown(int position) {
        if (onBind != null) {
            onBind.next(getMusicSong().get(position).getPath());
            musicPlayModelListener.init(list.get(position));
        }
        Log.e(TAG, "TuneDown:" + position);
        //    歌曲切换
    }

    //    播放
    public void Play() {
        //    执行播放操作
        if (onBind != null) {
            onBind.resume();
        }

    }

    public void Play(int position) {
        //    执行播放操作
        if(onBind == null){
            connect();
        }
        musicPlayModelListener.init(list.get(position));
        onBind.play(list.get(position).getPath());

    }

    //    停止播放
    public void Stop() {
        //    执行停止播放操作
        if (onBind != null) {
            onBind.stop();
            unbindService();
        }
    }

    //    暂停
    public void Pause() {
        //    执行暂停操作
        if (onBind != null) {
            onBind.pause();
        }
    }

    //    获取音乐数量
    public int getSongNum() {
        return list.size();
    }

    //    获取歌曲信息
    public List<MusicSongBean> getMusicSong() {
        MusicSongBean musicSongBean = new MusicSongBean();
        musicSongBean.setId(0);
        musicSongBean.setSong("丑八怪");
        musicSongBean.setSingger("薛之谦");
        musicSongBean.setDuration("10:50");
        list.add(musicSongBean);
        MusicSongBean musicSongBean1 = new MusicSongBean();
        musicSongBean1.setId(1);
        musicSongBean1.setSong("丑八怪");
        musicSongBean1.setSingger("薛之谦");
        musicSongBean1.setDuration("09:50");
        list.add(musicSongBean1);
        return list;
    }

    public interface MusicPlayModelListener {
        public void init(MusicSongBean musicSongBean);
    }
}
