package com.example.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainPageViewPaperAdapter extends FragmentPagerAdapter {
    private String[] mresources;
    private List<Fragment> mlist = new ArrayList<Fragment>();

    public MainPageViewPaperAdapter(@NonNull FragmentManager fm, String[] mresources,List<Fragment> mlist) {
        super(fm);
        this.mlist = mlist;
        this.mresources = mresources;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mresources[position];
    }
}
