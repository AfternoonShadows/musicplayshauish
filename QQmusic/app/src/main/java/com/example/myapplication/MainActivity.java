package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.TabLayoutViewPagerAdapter;
import com.example.myapplication.Fragment.MusicListFragment;
import com.example.myapplication.Fragment.PersonalCenterFragment;
import com.example.myapplication.Fragment.HomePageFragment;
import com.example.myapplication.bean.MusicSongBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {
//    implements ActivityFragmentListener
    private final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] resources;
    private List<Fragment> list = new ArrayList<>();
    private TabLayoutViewPagerAdapter pagerAdapter;
    private Button btnMainPagePlay;
    private TextView tvCurrentPlaySong;
    private ImageView ivCurrentPlayPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        init();
    }

    public void init() {
        tabLayout = findViewById(R.id.tabl_activity_main_page);
        viewPager = findViewById(R.id.vp_activity_main_page);
        btnMainPagePlay = findViewById(R.id.btn_main_page_play);
        ivCurrentPlayPicture = findViewById(R.id.iv_current_play_picture);
        tvCurrentPlaySong = findViewById(R.id.tv_current_play_song);
        resources = getResources().getStringArray(R.array.mainPageTabLayoutButton);

        getWindow().getDecorView().setSystemUiVisibility(View. SYSTEM_UI_FLAG_LOW_PROFILE);

//        获取需要加载的页面
        MusicListFragment musicListFragment = new MusicListFragment();
        list.add(musicListFragment);
        list.add(new HomePageFragment());
        list.add(new PersonalCenterFragment());
        pagerAdapter = new TabLayoutViewPagerAdapter(getSupportFragmentManager(), resources, list);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        主要目的是为了修改主页面的播放内容，接口位置修改到，播放页面MusicPlayActivity可能会更好
        musicListFragment.setMusicPlayListener(new MusicListFragment.MusicPlayListener() {
            @Override
            public void playMusicTextInfoChange(MusicSongBean musicSongBean) {
                ivCurrentPlayPicture.setBackground(getResources().getDrawable(R.mipmap.next));
                tvCurrentPlaySong.setText(musicSongBean.getSong()+"-"+musicSongBean.getSingger());
                btnMainPagePlay.setBackground(getResources().getDrawable(R.mipmap.pause));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}