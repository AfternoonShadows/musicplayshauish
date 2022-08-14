package com.example.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.MainPageViewPaperAdapter;
import com.example.myapplication.Fragment.FragmentViewFive;
import com.example.myapplication.Fragment.FragmentViewFour;
import com.example.myapplication.Fragment.FragmentViewOne;
import com.example.myapplication.Fragment.FragmentViewSecond;
import com.example.myapplication.Fragment.FragmentViewThree;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] resources;
    private List<Fragment> list = new ArrayList<>();
    private MainPageViewPaperAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        init();
        acquireData();
    }

    public void init() {
        tabLayout = findViewById(R.id.mainActivityPageTabLayoutBottom);
        viewPager = findViewById(R.id.mainActivityPageViewPager);
        resources = getResources().getStringArray(R.array.mainPageTabLayoutButton);
//        获取需要加载的页面
        list.add(new FragmentViewOne());
        list.add(new FragmentViewSecond());
        list.add(new FragmentViewThree());
        list.add(new FragmentViewFour());
        list.add(new FragmentViewFive());
        pagerAdapter = new MainPageViewPaperAdapter(getSupportFragmentManager(), resources, list);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void acquireData() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, "addOnTabSelectedListener:选中");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e(TAG, "addOnTabSelectedListener:未选中");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e(TAG, "addOnTabSelectedListener:再次选中");
            }
        });
    }
}