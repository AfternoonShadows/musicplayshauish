package com.example.myapplication.persent;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.base.basePresent;
import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.model.MusicPlayModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 **/
public class MusicPlayPresent extends basePresent {
    private String TAG = "MusicPlayPresent";
    private MusicPlayModel musicPlayModel;
    private Context mContext;

    public MusicPlayPresent(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    public void init() {
        musicPlayModel = new MusicPlayModel(mContext);
    }

    @Override
    public void release() {

    }

    //    设置监听
    public void setMusicPlayModelListener(MusicPlayModel.MusicPlayModelListener musicPlayModelListener) {
        musicPlayModel.setMusicPlayModelListener(musicPlayModelListener);
    }

    //
    //    修改正在播放音乐的颜色
    public void musicListInfoChange() {
    }

    //    修改成正在播放音乐的信息
    public void musicTextInfoChange() {
    }

    //    修改播放进度
    public void seekBarInfoChange(boolean judge, int position) {
        musicPlayModel.seekBarInfoChange(judge, position);
    }

    //    上一曲
    public void TuneUp(int position) {
        Log.e(TAG, "TuneUp:" + position);
        if (position > 0) {
            musicPlayModel.TuneUp(--position);
        } else {
            musicPlayModel.TuneUp(position);
        }
    }

    //    下一曲
    public void TuneDown(int position) {
        Log.e(TAG, "TuneDown:" + position);
        if (position >= getSongNum() - 1) {
            musicPlayModel.TuneDown(0);
        } else {
            musicPlayModel.TuneDown(++position);
        }
    }

    //    播放
    public void Play() {
        musicPlayModel.Play();
    }

    public void Play(int position) {
        musicPlayModel.Play(position);
    }

    //    停止播放
    public void Stop() {
        musicPlayModel.Stop();
    }

    //    暂停
    public void Pause() {
        musicPlayModel.Pause();
    }

    //    获取音乐数量
    public int getSongNum() {
        return musicPlayModel.getSongNum();
    }

    //    获取歌曲信息
    public List<MusicSongBean> getMusicSong() {
        List<MusicSongBean> list = new ArrayList<>();
        list = musicPlayModel.getMusicSong();
        return list;
    }
}
