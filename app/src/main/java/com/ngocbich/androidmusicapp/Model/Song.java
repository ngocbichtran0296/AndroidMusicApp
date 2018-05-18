package com.ngocbich.androidmusicapp.Model;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class Song {
    private String SongId;
    private String SongName;
    private String SongLink;
    private String SongGenre;
    private String SongArtist;
    private String SongRating;

    public Song() {
    }

    public Song(String songId, String songName, String songLink, String songGenre, String songArtist, String songRating) {
        SongId = songId;
        SongName = songName;
        SongLink = songLink;
        SongGenre = songGenre;
        SongArtist = songArtist;
        SongRating = songRating;
    }

    public String getSongId() {
        return SongId;
    }

    public void setSongId(String songId) {
        SongId = songId;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getSongLink() {
        return SongLink;
    }

    public void setSongLink(String songLink) {
        SongLink = songLink;
    }

    public String getSongGenre() {
        return SongGenre;
    }

    public void setSongGenre(String songGenre) {
        SongGenre = songGenre;
    }

    public String getSongArtist() {
        return SongArtist;
    }

    public void setSongArtist(String songArtist) {
        SongArtist = songArtist;
    }

    public String getSongRating() {
        return SongRating;
    }

    public void setSongRating(String songRating) {
        SongRating = songRating;
    }

    @Override
    public String toString() {
        return "Song{" +
                "SongId='" + SongId + '\'' +
                ", SongName='" + SongName + '\'' +
                ", SongLink='" + SongLink + '\'' +
                ", SongGenre='" + SongGenre + '\'' +
                ", SongArtist='" + SongArtist + '\'' +
                ", SongRating='" + SongRating + '\'' +
                '}';
    }
}
