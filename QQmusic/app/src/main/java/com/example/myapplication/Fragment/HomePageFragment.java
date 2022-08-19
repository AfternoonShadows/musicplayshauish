package com.example.myapplication.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.TabLayoutViewPagerAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private final String TAG = "HomePageFragment";
    private View root = null;
    private TabLayoutViewPagerAdapter mTabLayoutViewPagerAdapter;
    private String[] resources;
    private List<Fragment> list = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_homepage, container, false);
        }
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
        init();
    }

    public void init() {
        mTabLayout = root.findViewById(R.id.tabL_fragment_homepage);
        mViewPager = root.findViewById(R.id.vp_fragment_homepage);
        resources = getResources().getStringArray(R.array.fragmentHomepageTabLayout);
        HonePageCRecommendFragment honePageCRecommendFragment;
        for (int i = 0; i <= 23; i++) {
            honePageCRecommendFragment = new HonePageCRecommendFragment();
            list.add(honePageCRecommendFragment);
            honePageCRecommendFragment = null;
        }
        mTabLayoutViewPagerAdapter = new TabLayoutViewPagerAdapter(getChildFragmentManager(), resources, list);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mTabLayoutViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        list.clear();
        mTabLayoutViewPagerAdapter = null;
        Log.e(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach");
    }
}
