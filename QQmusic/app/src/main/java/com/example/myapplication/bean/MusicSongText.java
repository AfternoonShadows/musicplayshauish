package com.example.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicSongText implements Parcelable {
//    歌曲id
    private String Id;
//    歌曲名
    private String Song;
//    作者
    private String Tutor;
//    歌词
    private String Lyric;
//    图片地址
    private String Singger;
//    时长
    private String duration;
//    路径
    private String path;
    public MusicSongText(){};

    protected MusicSongText(Parcel in) {
        Id = in.readString();
        Song = in.readString();
        Tutor = in.readString();
        Lyric = in.readString();
        Singger = in.readString();
        duration = in.readString();
        path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Song);
        dest.writeString(Tutor);
        dest.writeString(Lyric);
        dest.writeString(Singger);
        dest.writeString(duration);
        dest.writeString(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MusicSongText> CREATOR = new Creator<MusicSongText>() {
        @Override
        public MusicSongText createFromParcel(Parcel in) {
            return new MusicSongText(in);
        }

        @Override
        public MusicSongText[] newArray(int size) {
            return new MusicSongText[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSong() {
        return Song;
    }

    public void setSong(String song) {
        Song = song;
    }

    public String getTutor() {
        return Tutor;
    }

    public void setTutor(String tutor) {
        Tutor = tutor;
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
                ", Tutor='" + Tutor + '\'' +
                ", Lyric='" + Lyric + '\'' +
                ", Singger='" + Singger + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
