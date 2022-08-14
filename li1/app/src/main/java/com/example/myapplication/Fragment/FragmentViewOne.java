package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MusicAdapter;
import com.example.myapplication.MusicPage;
import com.example.myapplication.R;
import com.example.myapplication.bean.MusicSongText;
import com.example.myapplication.persent.PresentMusicActivityMain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FragmentViewOne extends Fragment {
    private final String TAG = "FragmentViewOne";
    private View root = null;
    private RecyclerView mRecyclerView;
    private MusicAdapter mMusicAdapter;
    private List<MusicSongText> mList;
    private PresentMusicActivityMain mPresentMusicActivityMain;
    //    音频路径
    private File[] mFiles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        if (root == null) {
            root = inflater.inflate(R.layout.activity_main_viewone, container, false);
        }
        init();
        return root;
    }

    public void init() {
        mRecyclerView = root.findViewById(R.id.mainPageViewoneRecyclerView);
//        获取数据返回List<MusicSongText>
        mPresentMusicActivityMain = new PresentMusicActivityMain();
//        初始化
        mPresentMusicActivityMain.init();
        mList = mPresentMusicActivityMain.RequireMusicSongData();
//        适配数据
        mMusicAdapter = new MusicAdapter(mList);
        mRecyclerView.setAdapter(mMusicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        mMusicAdapter.setItemClickLisenter(new MusicAdapter.ItemClickLisenter() {
            @Override
            public void onClick(View view, int position) {
                Log.e(TAG, "setItemClickLisenter " + String.valueOf(position));
                Intent intent = new Intent(getActivity(), MusicPage.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}
