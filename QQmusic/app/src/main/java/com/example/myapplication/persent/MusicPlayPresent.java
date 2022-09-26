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
    private static final String TAG = "MusicPlayPresent";
    private MusicPlayModel mMusicPlayModel;
    private volatile static MusicPlayPresent mMusicPlayPresent;
    private Context mContext;

    public MusicPlayPresent(Context mContext) {
        super(mContext);
        this.mContext = mContext;
        init();
    }

    public static MusicPlayPresent getInstance(Context mContext) {
        if (mMusicPlayPresent == null) {
            synchronized (MusicPlayPresent.class) {
                if (mMusicPlayPresent == null) {
                    mMusicPlayPresent = new MusicPlayPresent(mContext);
                }
            }
        }
        return mMusicPlayPresent;
    }

    @Override
    public void init() {
        mMusicPlayModel = new MusicPlayModel(mContext);
        mMusicPlayModel.init();
    }

    @Override
    public void release() {
        mMusicPlayModel.release();
        mMusicPlayModel = null;
    }

    //    设置监听
    public void setMusicPlayModelListener(MusicPlayModel.MusicPlayModelListener musicPlayModelListener) {
        mMusicPlayModel.setMusicPlayModelListener(musicPlayModelListener);
    }

    //    修改播放进度
    public void seekBarInfoChange(boolean judge, int position) {
        mMusicPlayModel.seekBarInfoChange(judge, position);
    }

    //    上一曲
    public void TuneUp(int position) {
        Log.e(TAG, "TuneUp:" + position);
        if (position > 0) {
            mMusicPlayModel.TuneUp(--position);
        } else {
            mMusicPlayModel.TuneUp(position);
        }
    }

    //    下一曲
    public void TuneDown(int position) {
        Log.e(TAG, "TuneDown:" + position);
        if (position >= getSongNum() - 1) {
            mMusicPlayModel.TuneDown(0);
        } else {
            mMusicPlayModel.TuneDown(++position);
        }
    }

    //    播放
    public void Play() {
        mMusicPlayModel.Play();
    }

    public void Play(int position) {
        mMusicPlayModel.Play(position);
    }

    //    停止播放
    public void Stop() {
        mMusicPlayModel.Stop();
    }

    //    暂停
    public void Pause() {
        mMusicPlayModel.Pause();
    }

    //    获取音乐数量
    public int getSongNum() {
        return mMusicPlayModel.getSongNum();
    }

    //    获取歌曲信息
    public List<MusicSongBean> getMusicSong() {
        List<MusicSongBean> list = new ArrayList<>();
        list = mMusicPlayModel.getMusicSong();
        return list;
    }
}
