package com.ngocbich.androidmusicapp.Model;

/**
 * Created by Ngoc Bich on 5/17/2018.
 */

public class Genre {
    private String GenreId;
    private String GenreName;

    public Genre(String genreId, String genreName) {
        GenreId = genreId;
        GenreName = genreName;
    }

    public String getGenreId() {
        return GenreId;
    }

    public void setGenreId(String genreId) {
        GenreId = genreId;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }
}
