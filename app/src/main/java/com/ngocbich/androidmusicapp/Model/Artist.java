package com.ngocbich.androidmusicapp.Model;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class Artist {
    private String ArtistId;
    private String ArtistName;

    public Artist(String artistId, String artistName) {
        ArtistId = artistId;
        ArtistName = artistName;
    }

    public String getArtistId() {
        return ArtistId;
    }

    public void setArtistId(String artistId) {
        ArtistId = artistId;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "ArtistId='" + ArtistId + '\'' +
                ", ArtistName='" + ArtistName + '\'' +
                '}';
    }
}
