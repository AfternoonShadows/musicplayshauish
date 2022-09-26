package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
public abstract class baseLazyFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private View mRootView;
    // 表示是否已经请求过数据
    protected boolean mHaveLoadData;
    // 表示数据是否已经请求完毕
    protected boolean mLoadDataFinished;
    // 组件已加载
    protected boolean mInitModule;

    // 表示开始加载数据, 但不表示数据加载已经完成
    public abstract void loadDataStart();

    public abstract void findViewById(View view);

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    public void clear(){};

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        if (mRootView == null) {
            mRootView = initView(inflater, container, savedInstanceState);
        }
        findViewById(mRootView);
        mInitModule = true;
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: mHaveLoadData: " + mHaveLoadData);
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
        Log.e(TAG, "onDestroyView");
        clear();
        mLoadDataFinished = false;
        mHaveLoadData = false;
//        mInitModule = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRootView = null;
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
        // 如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (isVisibleToUser && !mHaveLoadData) {
            mHaveLoadData = true;
            loadDataStart();
        }
    }
}
