package com.example.myapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MusicListAdapter;
import com.example.myapplication.MusicPlayActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.baseLazyFragment;
import com.example.myapplication.bean.MusicSongBean;
import com.example.myapplication.persent.MusicPlayPresent;

import java.util.List;

/**
 *
 **/
public class MusicListFragment extends Fragment {
    private final String TAG = "MusicListFragment";
    private View root = null;
    private RecyclerView mRecyclerView;
    private MusicListAdapter mMusicListAdapter;
    private List<MusicSongBean> mList;
    private MusicPlayPresent mMusicPlayPresent;
    private MusicPlayListener musicPlayListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        if (root == null) {
            root = inflater.inflate(R.layout.activity_music_list, container, false);
        }
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        init();
    }


    public void init() {
        //        获取数据返回List<MusicSongText>
        mMusicPlayPresent = new MusicPlayPresent(getContext());
//        初始化
        mMusicPlayPresent.init();
        mList = mMusicPlayPresent.getMusicSong();

//        适配数据
        mMusicListAdapter = new MusicListAdapter(mList);
        mRecyclerView = root.findViewById(R.id.rv_music_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mRecyclerView.setAdapter(mMusicListAdapter);
        mMusicListAdapter.setItemClickLisenter(new MusicListAdapter.ItemClickLisenter() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "setItemClickLisenter : song position:" + String.valueOf(position));
                Intent intent = new Intent(getActivity(), MusicPlayActivity.class);
                intent.putExtra("position", position);
//              跳转的界面被销毁会返回结果
                startActivityForResult(intent, 1009);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mList.clear();
        mMusicPlayPresent.release();
        mMusicPlayPresent = null;
        mMusicListAdapter = null;
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }


    //    返回结果
//    修改
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult : " + resultCode + " " + requestCode);
        switch (requestCode) {
            case 1009:
                if (resultCode == 1010) {
                    int position = data.getIntExtra("position", 10);
                    mMusicListAdapter.setmPosition(position);
                    mMusicListAdapter.notifyDataSetChanged();
                    musicPlayListener.playMusicTextInfoChange(mList.get(position));
                }
                break;
            default:
                break;
        }
    }

    public void setMusicPlayListener(MusicPlayListener musicPlayListener) {
        Log.d(TAG, "setMusicPlayListener : song position:");
        this.musicPlayListener = musicPlayListener;
    }

    public interface MusicPlayListener {
        void playMusicTextInfoChange(MusicSongBean musicSongBean);
    }

}
