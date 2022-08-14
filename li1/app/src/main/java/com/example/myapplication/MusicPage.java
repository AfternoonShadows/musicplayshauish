package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.bean.MusicSongText;
import com.example.myapplication.persent.PresentMusicActivityMain;
import com.example.myapplication.service.MusicPlayService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MusicPage extends Activity {
    private final String TAG = "MusicPage";
    private Button next,last,pause;
    private static SeekBar musicplay_seekbar;
    private static TextView process;
    private static TextView total;
    private TextView song,singer;
    private boolean play =false;
    private int mPosition = -1;
    private PresentMusicActivityMain presentMusicActivityMain;
    private List<MusicSongText> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_musicpage);
        mPosition = getIntent().getIntExtra("position",1);
        init();
    }
    public void init(){
        presentMusicActivityMain = new PresentMusicActivityMain();
        presentMusicActivityMain.init();
        musicplay_seekbar =findViewById(R.id.musicplay_seekbar);
        process =findViewById(R.id.musicplay_progress);
        total =findViewById(R.id.musicplay_total);
        song = findViewById(R.id.musicplay_tv_song);
        singer = findViewById(R.id.musicplay_tv_singer);
        next =findViewById(R.id.musicplay_iv_next);
        last =findViewById(R.id.musicplay_iv_last);
        pause =findViewById(R.id.musicplay_iv_play);

        mList = presentMusicActivityMain.RequireMusicSongData();


        Onclick onclick =new Onclick();
        next.setOnClickListener(onclick);
        last.setOnClickListener(onclick);
        pause.setOnClickListener(onclick);

        musicplay_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                判断是不是用户拖动
                if(fromUser) {
                    presentMusicActivityMain.seekBarInfoChange(true , progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    class Onclick implements View.OnClickListener{
        //        如果play为false表示音乐为未播放变为播放
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.musicplay_iv_play:
//                    音乐播放光盘转动
                    if(play)
                    {
                        pause.setBackgroundResource(R.mipmap.start);
                        presentMusicActivityMain.musicTunePlay();
                        play=false;
                    }else{
                        pause.setBackgroundResource(R.mipmap.pause);
                        presentMusicActivityMain.musicTunePause();
                        play=true;
                    }
                    break;
                case R.id.musicplay_iv_next:
                    if(mPosition<presentMusicActivityMain.judgeMusicNum())
                    {
                        mPosition++;
                    }
                    presentMusicActivityMain.musicTuneUp(mPosition);
                    break;
                case R.id.musicplay_iv_last:
                    if(mPosition>0)
                    {
                        mPosition--;
                    }
                    presentMusicActivityMain.musicTuneDown(mPosition);
                    break;
            }
        }
    }
    //    获取从MusicPlayService传递的信息
    public static Handler handler =new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//        获取子线程发送过来的音乐播放进度
            Bundle bundle =msg.getData();
            int max = bundle.getInt("MAX");
            int currentposition = bundle.getInt("CUR");
            musicplay_seekbar.setMax(max);
            musicplay_seekbar.setProgress(currentposition);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            process.setText(simpleDateFormat.format(new Date(currentposition)));
            total.setText(simpleDateFormat.format(new Date(max)));
        }
    };
}
