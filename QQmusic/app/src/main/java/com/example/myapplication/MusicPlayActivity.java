package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.model.MusicPlayModel;
import com.example.myapplication.persent.MusicPlayPresent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicPlayActivity extends Activity {
    private final String TAG = "MusicPlayActivity";
    private static Button btnNext, btnLast, btnPause;
    private static SeekBar sbMusicPlay;
    private static TextView tvProcess;
    private static TextView tvTotal;
    private TextView tvSong, tvSinger;
    private static boolean playStatus = true;
    private int mPosition = -1;
    private static MusicPlayPresent musicPlayPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        init();
    }

    //    虚拟返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("position", mPosition);
            setResult(1010, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayPresent.Stop();
//        musicPlayPresent.release();
    }

    public void init() {
        Log.e(TAG, "init");
        musicPlayPresent = MusicPlayPresent.getInstance(getApplicationContext());
        sbMusicPlay = findViewById(R.id.sb_music_play);
        tvProcess = findViewById(R.id.tv_start_progress);
        tvTotal = findViewById(R.id.tv_end_progress);
        tvSong = findViewById(R.id.tv_song_name);
        tvSinger = findViewById(R.id.tv_singer);
        btnNext = findViewById(R.id.btn_next);
        btnLast = findViewById(R.id.btn_last);
        btnPause = findViewById(R.id.btn_play);

        //        获取列表中歌曲的位置
        mPosition = getIntent().getIntExtra("position", 1);

        Onclick onclick = new Onclick();
        btnNext.setOnClickListener(onclick);
        btnLast.setOnClickListener(onclick);
        btnPause.setOnClickListener(onclick);
        //        回调将歌曲信息显示出来
        //        musicPlayPresent.init();
        musicPlayPresent.setMusicPlayModelListener(new MusicPlayModel.MusicPlayModelListener() {
            @Override
            public void onMusicInfoChange(MusicSongBean musicSongBean) {
                Log.e(TAG, "CALLBACK songPosition :" + musicSongBean.getId());
                mPosition = musicSongBean.getId();
                tvSinger.setText(musicSongBean.getSingger());
                tvSong.setText(musicSongBean.getSong());
                tvTotal.setText(musicSongBean.getDuration());
                tvProcess.setText("00:00");
            }
        });
        musicPlayPresent.Play(mPosition);

        sbMusicPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //                判断是不是用户拖动
                if (fromUser) {
                    musicPlayPresent.seekBarInfoChange(true, progress);
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

    class Onclick implements View.OnClickListener {
        //        如果play为false表示音乐为未播放变为播放
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play:
                    if (playStatus) {
                        btnPause.setBackgroundResource(R.mipmap.start);
                        musicPlayPresent.Pause();
                        playStatus = false;
                    } else {
                        btnPause.setBackgroundResource(R.mipmap.pause);
                        musicPlayPresent.Play();
                        playStatus = true;
                    }
                    break;
                case R.id.btn_next:
                    musicPlayPresent.TuneDown(mPosition);
                    btnPause.setBackgroundResource(R.mipmap.pause);
                    playStatus = true;
                    break;
                case R.id.btn_last:
                    musicPlayPresent.TuneUp(mPosition);
                    btnPause.setBackgroundResource(R.mipmap.pause);
                    playStatus = true;
                    break;
            }
        }
    }

    //    获取从MusicPlayService传递的信息
    public static Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//        获取子线程发送过来的音乐播放进度
            Bundle bundle = msg.getData();

            int max = bundle.getInt("MAX");
            int currentPosition = bundle.getInt("CUR");
            if (max <= currentPosition) {
                btnPause.setBackgroundResource(R.mipmap.start);
                musicPlayPresent.Pause();
                playStatus = false;
            }
            sbMusicPlay.setMax(max);
            sbMusicPlay.setProgress(currentPosition);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            tvProcess.setText(simpleDateFormat.format(new Date(currentPosition)));
            tvTotal.setText(simpleDateFormat.format(new Date(max)));
        }
    };
}
