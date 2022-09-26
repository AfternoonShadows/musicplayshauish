package com.example.myapplication.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.myapplication.MusicPlayActivity;
import com.example.myapplication.base.baseModel;
import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.service.MusicPlayService;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
    //    音乐信息列表，包含作家名，歌曲名，音乐路径等
    private List<MusicSongBean> musicInfoList = new ArrayList<MusicSongBean>();
    private String[] musicFilesAssets;
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
        } else {
            Log.e(TAG, "init : connect service");
        }
    }

    @Override
    public void release() {
        musicInfoList.clear();
        unbindService();
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

    private void unbindService() {
        Log.e(TAG, "unbindService: ");
        mContext.unbindService(connection);
    }

    //    进度条信息修改
    public void seekBarInfoChange(boolean judge, int position) {
        //    修改音乐播放进度条
        if (onBind != null) {
            onBind.seekTo(position);
        } else {
            Log.d(TAG, "seekBarInfoChange : no connect service");
        }
    }

    //    上一曲
    public void TuneUp(int position) {
        if (onBind != null) {
            onBind.next(getMusicSong().get(position).getPath());
        } else {
            Log.d(TAG, "TuneUp : no connect service");
        }
        musicPlayModelListener.onMusicInfoChange(musicInfoList.get(position));
        Log.e(TAG, "TuneUp:" + position);
        //    歌曲切换
    }

    //    下一曲
    public void TuneDown(int position) {
        if (onBind != null) {
            onBind.next(getMusicSong().get(position).getPath());
        } else {
            Log.d(TAG, "TuneDown : no connect service");
        }
        musicPlayModelListener.onMusicInfoChange(musicInfoList.get(position));
        Log.e(TAG, "TuneDown:" + position);
        //    歌曲切换
    }

    //    播放
    public void Play() {
        //    执行播放操作
        if (onBind != null) {
            onBind.resume();
        } else {
            Log.d(TAG, "Play : no connect service");
        }

    }

    public void Play(int position) {
        if (onBind != null) {
            onBind.play(musicInfoList.get(position).getPath());
        } else {
            Log.d(TAG, "Play(int position) : no connect service");
        }
        musicPlayModelListener.onMusicInfoChange(getMusicSong().get(position));

    }

    //    停止播放
    public void Stop() {
        //    执行停止播放操作
        if (onBind != null) {
            onBind.stop();
            unbindService();
        } else {
            Log.d(TAG, "Stop : no connect service");
        }
    }

    //    暂停
    public void Pause() {
        //    执行暂停操作
        if (onBind != null) {
            onBind.pause();
        } else {
            Log.d(TAG, "Pause : no connect service");
        }
    }

    //    获取音乐数量
    public int getSongNum() {
        return musicInfoList.size();
    }

    //    获取歌曲信息
    public List<MusicSongBean> getMusicSong() {
        getMusicAssetsFiles();
//        获取歌曲信息getExternalFilesDir
        /*
        File file = new File(String.valueOf(mContext.getExternalFilesDir(null)) + "/Music");
        if (!file.exists()) {
            file.mkdir();
        } else {
            Log.d(TAG, "getMusicSong : 文件已创建" + file.getAbsolutePath());
        }
         */
        if (musicFilesAssets != null) {
            MusicSongBean musicSongBean = null;
            MediaPlayer mediaPlayer;
            for (int i = 0; i < musicFilesAssets.length; i++) {
                mediaPlayer = new MediaPlayer();
                musicSongBean = new MusicSongBean();
                musicSongBean.setId(i);
                musicSongBean.setSong(musicFilesAssets[i].split("-", 2)[0]);
                musicSongBean.setSingger(musicFilesAssets[i].split("-", 2)[1].substring(0, musicFilesAssets[i].split("-", 2)[1].indexOf(".mp3")));

                AssetFileDescriptor assetFileDescriptor = null;
                try {
                    assetFileDescriptor = mContext.getAssets().openFd("musicList/" + musicFilesAssets[i]);
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    musicSongBean.setDuration(getAllTime(mediaPlayer.getDuration()));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                musicSongBean.setPath("musicList/" + musicFilesAssets[i]);
                musicInfoList.add(musicSongBean);
                musicSongBean = null;
                mediaPlayer = null;
            }
        }
        return musicInfoList;
    }

    public interface MusicPlayModelListener {
        //  初始化音乐播放界面信息
        public void onMusicInfoChange(MusicSongBean musicSongBean);
    }

    private void getMusicAssetsFiles() {
//        获取当前APP中的Assets
        AssetManager assetManager = mContext.getAssets();
        try {
//        获取当前APP中的Assets文件名(包含文件夹)
            musicFilesAssets = assetManager.list("musicList");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String getAllTime(int f) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(f);
    }
}
