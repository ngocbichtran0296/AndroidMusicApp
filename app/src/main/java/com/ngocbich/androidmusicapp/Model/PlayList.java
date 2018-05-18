package com.ngocbich.androidmusicapp.Model;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class PlayList {
    private String PlayListId;
    private String UserId;
    private String SongId;

    public PlayList(String playListId, String userId, String songId) {
        PlayListId = playListId;
        UserId = userId;
        SongId = songId;
    }

    public String getPlayListId() {
        return PlayListId;
    }

    public void setPlayListId(String playListId) {
        PlayListId = playListId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getSongId() {
        return SongId;
    }

    public void setSongId(String songId) {
        SongId = songId;
    }
}
