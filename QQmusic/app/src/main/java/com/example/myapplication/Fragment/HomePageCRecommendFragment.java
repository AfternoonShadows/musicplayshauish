package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

/**
 *
 **/
public class HomePageCRecommendFragment extends Fragment {
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(root==null){
            root = inflater.inflate(R.layout.fragment_homepage_recommend,container,false);
        }
        return root;
    }
}
