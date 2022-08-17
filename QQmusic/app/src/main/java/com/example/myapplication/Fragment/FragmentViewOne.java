package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MusicAdapter;
import com.example.myapplication.MusicPlayActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.persent.MusicPlayPresent;

import java.io.File;
import java.util.List;

public class FragmentViewOne extends Fragment {
    private final String TAG = "FragmentViewOne";
    private View root = null;
    private RecyclerView mRecyclerView;
    private MusicAdapter mMusicAdapter;
    private List<MusicSongBean> mList;
    private MusicPlayPresent mMusicPlayPresent;
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
        mMusicPlayPresent = new MusicPlayPresent(getContext());
//        初始化
        mMusicPlayPresent.init();
        mList = mMusicPlayPresent.getMusicSong();
//        适配数据
        mMusicAdapter = new MusicAdapter(mList);
        mRecyclerView.setAdapter(mMusicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        mMusicAdapter.setItemClickLisenter(new MusicAdapter.ItemClickLisenter() {
            @Override
            public void onClick(View view, int position) {
                Log.e(TAG, "setItemClickLisenter : song position:" + String.valueOf(position));
                Intent intent = new Intent(getActivity(), MusicPlayActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}
