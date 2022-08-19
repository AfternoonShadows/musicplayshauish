package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.MusicSongBean;

import java.util.ArrayList;
import java.util.List;


public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MHodler> {
    private final String TAG = "MusicAdapter";
    private List<MusicSongBean> list = new ArrayList<>();
    private ItemClickLisenter itemClickLisenter;
    private View view;
    private int mPosition = -1;

    public MusicListAdapter(List<MusicSongBean> list) {
        this.list = list;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    @Override
    public MusicListAdapter.MHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_music_list_recyclerview_content, parent, false);
        Log.e(TAG, " It's created");
        return new MHodler(view);
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.MHodler holder, int position) {
        holder.itemLoaclMusicNum.setText(String.valueOf(list.get(position).getId() + 1));
        holder.itemLoaclMusicSong.setText(list.get(position).getSong());
        holder.itemLoaclMusicSinger.setText(list.get(position).getSingger());
        holder.itemLoaclMusicDurtion.setText(list.get(position).getDuration());
//        设置当前播放音乐的文本颜色
        if (mPosition == position) {
            holder.itemLoaclMusicNum.setTextColor(view.getResources().getColor(R.color.purple_200));
            holder.itemLoaclMusicSong.setTextColor(view.getResources().getColor(R.color.purple_200));
            holder.itemLoaclMusicSinger.setTextColor(view.getResources().getColor(R.color.purple_200));
            holder.itemLoaclMusicDurtion.setTextColor(view.getResources().getColor(R.color.purple_200));
        } else {
            holder.itemLoaclMusicNum.setTextColor(view.getResources().getColor(R.color.black));
            holder.itemLoaclMusicSong.setTextColor(view.getResources().getColor(R.color.black));
            holder.itemLoaclMusicSinger.setTextColor(view.getResources().getColor(R.color.black));
            holder.itemLoaclMusicDurtion.setTextColor(view.getResources().getColor(R.color.black));
        }
//        设置列表监听
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickLisenter != null) {
                    int position = holder.getLayoutPosition();
                    itemClickLisenter.onClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MHodler extends RecyclerView.ViewHolder {
        //        序号
        TextView itemLoaclMusicNum;
        //        歌名
        TextView itemLoaclMusicSong;
        //        歌手名
        TextView itemLoaclMusicSinger;
        //        时长
        TextView itemLoaclMusicDurtion;

        public MHodler(View itemView) {
            super(itemView);
            itemLoaclMusicNum = itemView.findViewById(R.id.item_loacl_music_num);
            itemLoaclMusicSong = itemView.findViewById(R.id.item_loacl_music_song);
            itemLoaclMusicSinger = itemView.findViewById(R.id.item_loacl_music_singer);
            itemLoaclMusicDurtion = itemView.findViewById(R.id.item_loacl_music_durtion);
        }
    }

    //    设置监听器
    public interface ItemClickLisenter {
        void onClick(View view, int position);
    }

    //    将接口传进来
    public void setItemClickLisenter(ItemClickLisenter itemClickLisenter) {
        this.itemClickLisenter = itemClickLisenter;
    }
}
