package com.example.myapplication.persent;

import android.util.Log;

import com.example.myapplication.bean.MusicSongText;
import com.example.myapplication.model.ModelMusicActivityMain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 **/
public class PresentMusicActivityMain {
    private  String TAG = "PresentMusicActivityMain";
    private ModelMusicActivityMain modelMusicActivityMain ;
    public void init(){
        modelMusicActivityMain = new ModelMusicActivityMain();
    }
    //    修改正在播放音乐的颜色
    public void musicListInfoChange() {
    }

    //    修改成正在播放音乐的信息
    public void musicTextInfoChange() {
    }

    //    进度条信息修改
    public void seekBarInfoChange(boolean judge , int position) {
        modelMusicActivityMain.seekBarInfoChange(judge,position);
    }

    //    上一曲
    public void musicTuneUp(int position) {
    }

    //    下一曲
    public void musicTuneDown(int position) {
    }

    //    播放
    public void musicTunePlay() {
        modelMusicActivityMain.musicTunePlay();
    }

    //    停止播放
    public void musicTuneStop() {
        modelMusicActivityMain.musicTuneStop();
    }

    //    暂停
    public void musicTunePause() {
    }

    //    获取音乐数量
    public int judgeMusicNum() {
        return modelMusicActivityMain.judgeMusicNum();
    }
    //    获取歌曲信息
    public List<MusicSongText> RequireMusicSongData(){
        List<MusicSongText> list = new ArrayList<>();
        list = modelMusicActivityMain.RequireMusicSongData();
        return list;
    }
}
