package com.example.myapplication.model;

import android.util.Log;

import com.example.myapplication.bean.MusicSongText;

import java.util.ArrayList;
import java.util.List;

/**
 *
 **/
public class ModelMusicActivityMain {
    private  String TAG = "ModelMusicActivityMain";
    //    修改正在播放音乐的颜色
    public void musicListInfoChange() {
    }

    //    修改成正在播放音乐的信息
    public void musicTextInfoChange() {
    }

    //    进度条信息修改
    public void seekBarInfoChange(boolean judge , int position) {
        //    修改音乐播放进度条
    }

    //    上一曲
    public void musicTuneUp() {
        //    歌曲切换
    }

    //    下一曲
    public void musicTuneDown() {
        //    歌曲切换
    }

    //    播放
    public void musicTunePlay() {
        //    执行播放操作
    }

    //    停止播放
    public void musicTuneStop() {
        //    执行停止播放操作
    }

    //    暂停
    public void musicTunePause() {
        //    执行暂停操作
    }
    //    获取音乐数量
    public int judgeMusicNum() {
        return 3;
    }
    //    获取歌曲信息
    public List<MusicSongText> RequireMusicSongData(){
        List<MusicSongText> list = new ArrayList<MusicSongText>();
        MusicSongText musicSongText = new MusicSongText();
        musicSongText.setId("1");
        list.add(musicSongText);
        MusicSongText musicSongText1 = new MusicSongText();
        musicSongText1.setId("2");
        list.add(musicSongText1);
        return list;
    }
}
