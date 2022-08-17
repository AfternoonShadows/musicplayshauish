package com.example.myapplication.base;

import android.content.Context;

import com.example.myapplication.Interface.GeneralInterface;

/**
 *
 **/
public abstract class basePresent implements GeneralInterface {
    private Context mContext;

    public basePresent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }
}
