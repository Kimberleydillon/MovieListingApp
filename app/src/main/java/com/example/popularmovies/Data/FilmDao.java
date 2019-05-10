package com.example.popularmovies.Data;
import com.example.popularmovies.Data.Film;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insert(Film film);

    @Query("SELECT * FROM films")
    LiveData<List<Film>> getAllFilms();

    @Query("SELECT COUNT(*) FROM films")
    int countFilms();

    @Delete
    void delete(Film film);
}
