package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.TabLayoutViewPagerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.base.baseLazyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends baseLazyFragment {
    private final String TAG = "HomePageFragment";
    //    private View root = null;
    private TabLayoutViewPagerAdapter mTabLayoutViewPagerAdapter;
    private String[] resources;
    private List<Fragment> list = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void loadDataStart() {
    }

    @Override
    public void findViewById(View view) {
        mTabLayout = view.findViewById(R.id.tabL_fragment_homepage);
        mViewPager = view.findViewById(R.id.vpager_fragment_homepage);
        resources = getResources().getStringArray(R.array.fragmentHomepageTabLayout);
        HomePageRecommendFragment homePageRecommendFragment;
        for (int i = 0; i <= 23; i++) {
            homePageRecommendFragment = new HomePageRecommendFragment();
            list.add(homePageRecommendFragment);
            homePageRecommendFragment = null;
        }
        mTabLayoutViewPagerAdapter = new TabLayoutViewPagerAdapter(getChildFragmentManager(), resources, list);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mTabLayoutViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void clear() {
        list.clear();
        mTabLayoutViewPagerAdapter = null;
    }
}
