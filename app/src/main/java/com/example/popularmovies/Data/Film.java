package com.example.popularmovies.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "films")
public class Film {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "film_id")
    private int filmId;

    public int getFilmId() {

        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @ColumnInfo(name = "film_title")
    private String filmTitle;

    @ColumnInfo(name = "film_overview")
    private String filmOverview;

    @ColumnInfo(name = "film_poster_path")
    private String filmPosterPath;

    @ColumnInfo(name = "film_release_date")
    private String filmReleaseDate;

    @ColumnInfo(name = "favourite")
    private Boolean filmFavourite;

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmOverview() {
        return filmOverview;
    }

    public void setFilmOverview(String filmOverview) {
        this.filmOverview = filmOverview;
    }

    public String getFilmPosterPath() {
        return filmPosterPath;
    }

    public void setFilmPosterPath(String filmPosterPath) {
        this.filmPosterPath = filmPosterPath;
    }

    public String getFilmReleaseDate() {
        return filmReleaseDate;
    }

    public void setFilmReleaseDate(String filmReleaseDate) {
        this.filmReleaseDate = filmReleaseDate;
    }

    public Boolean getFilmFavourite() {
        return filmFavourite;
    }

    public void setFilmFavourite(Boolean filmFavourite) {
        this.filmFavourite = filmFavourite;
    }
}
