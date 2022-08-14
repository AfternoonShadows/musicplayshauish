package com.example.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.MusicSongText;

import java.util.ArrayList;
import java.util.List;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MHodler> {
    private final String TAG = "MusicAdapter";
    private List<MusicSongText> list = new ArrayList<>();
    private ItemClickLisenter itemClickLisenter;
    private View view;
    public MusicAdapter(List<MusicSongText> list) {
        this.list = list;
    }

    @Override
    public MusicAdapter.MHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_viewone_recyclerview_content, parent, false);
        Log.e(TAG, " It's created");
        return new MHodler(view);
    }

    @Override
    public void onBindViewHolder(MusicAdapter.MHodler holder, int position) {
        holder.itemLoaclMusicNum.setText(list.get(position).getId());
        holder.itemLoaclMusicSong.setText(list.get(position).getSong());
        holder.itemLoaclMusicSinger.setText(list.get(position).getSingger());
        holder.itemLoaclMusicDurtion.setText(list.get(position).getDuration());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickLisenter != null){
                    int position = holder.getLayoutPosition();
                    itemClickLisenter.onClick(view,position);
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
        void onClick(View view,int position);
    }

    //    将接口传进来
    public void setItemClickLisenter(ItemClickLisenter itemClickLisenter) {
        this.itemClickLisenter = itemClickLisenter;
    }
}
