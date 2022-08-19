package com.example.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MusicSongBean implements Parcelable {
//    Serializable
//    歌曲id
    private int Id;
//    歌曲名
    private String Song;
//    作者
    private String Singger;
//    歌词
    private String Lyric;
//    图片地址
    private String picture;
//    时长
    private String duration;
//    路径
    private String path;
    public MusicSongBean(){};

    protected MusicSongBean(Parcel in) {
        Id = in.readInt();
        Song = in.readString();
        picture = in.readString();
        Lyric = in.readString();
        Singger = in.readString();
        duration = in.readString();
        path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Song);
        dest.writeString(picture);
        dest.writeString(Lyric);
        dest.writeString(Singger);
        dest.writeString(duration);
        dest.writeString(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MusicSongBean> CREATOR = new Creator<MusicSongBean>() {
        @Override
        public MusicSongBean createFromParcel(Parcel in) {
            return new MusicSongBean(in);
        }

        @Override
        public MusicSongBean[] newArray(int size) {
            return new MusicSongBean[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSong() {
        return Song;
    }

    public void setSong(String song) {
        Song = song;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String tutor) {
        picture = tutor;
    }

    public String getLyric() {
        return Lyric;
    }

    public void setLyric(String lyric) {
        Lyric = lyric;
    }

    public String getSingger() {
        return Singger;
    }

    public void setSingger(String singger) {
        Singger = singger;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MusicSongText{" +
                "Id='" + Id + '\'' +
                ", Song='" + Song + '\'' +
                ", Tutor='" + picture + '\'' +
                ", Lyric='" + Lyric + '\'' +
                ", Singger='" + Singger + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
