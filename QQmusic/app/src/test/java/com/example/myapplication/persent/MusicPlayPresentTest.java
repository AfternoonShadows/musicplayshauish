package com.example.myapplication.persent;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;

import com.example.myapplication.MainActivity;
import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.model.MusicPlayModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.manipulation.Ordering;

import static org.junit.Assert.*;

/**
 *
 **/
public class MusicPlayPresentTest {
    private final String TAG = "MusicPlayPresentTest";
    Context context = null;
    MusicPlayPresent musicPlayPresent = new MusicPlayPresent(context);
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setMusicPlayModelListener() {
        musicPlayPresent.init();
        musicPlayPresent.setMusicPlayModelListener(new MusicPlayModel.MusicPlayModelListener() {
            @Override
            public void onMusicInfoChange(MusicSongBean musicSongBean) {
                System.out.println(musicSongBean.toString());
            }
        });
        musicPlayPresent.Play(0);
    }
}