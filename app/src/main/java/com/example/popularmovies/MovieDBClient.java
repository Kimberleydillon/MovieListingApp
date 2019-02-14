package com.example.popularmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface MovieDBClient {
    @Headers("Accept: application/json")
    @GET("movie/")
    Call<List<Film>> listFilms(
            @Query("api_key") String apikey,
            @Query("sort_by") String sort,
            @Query("page") String page);
}

//{{BASE_URL}}/discover/movie?api_key={{API_KEY}}&sort_by=popularity.desc&page=1

//https://api.themoviedb.org/3/movie/?api_key=dc31184cbd225288f0a80af2ed71c703&sort_by=popularity.desc&page=1}
