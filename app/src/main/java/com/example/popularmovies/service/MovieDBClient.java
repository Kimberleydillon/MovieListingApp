package com.example.popularmovies.service;

import com.example.popularmovies.BuildConfig;
import com.example.popularmovies.model.FilmResults;
import com.example.popularmovies.model.ReviewsResults;
import com.example.popularmovies.model.TrailerResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieDBClient {
    static final String API_KEY = BuildConfig.ApiKey;
    static final String BASE_URL = "https://api.themoviedb.org/3/";

    // Main network call for sorting main movie objects
    @Headers("Accept: application/json")
    @GET("movie/{sort}")
    Call<FilmResults> listFilms(
            @Path("sort") String sort,
            @Query("api_key") String apikey);

    // Network call for Reviews
    @GET("{movie_id}/reviews")
    Call<ReviewsResults> listReviews(
            @Path("movie_id") String movieid,
            @Query("api_key") String apikey);

    // Network call for Trailers
    @GET("{movie_id}/videos")
    Call<TrailerResults> listTrailers(
            @Path("movie_id") String movieid,
            @Query("api_key") String apikey);
}

//{{BASE_URL}}/discover/movie?api_key={{API_KEY}}&sort_by=popularity.desc&page=1

//https://api.themoviedb.org/3/movie/?api_key=dc31184cbd225288f0a80af2ed71c703&sort_by=popularity.desc&page=1}

//https://api.themoviedb.org/discover/movie/?api_key=dc31184cbd225288f0a80af2ed71c703&sort_by=popularity.desc&page=1

//{{base}}{{movie_id}}/reviews?{{api_key}}&language=en-US&page=1

//{{base}}{{movie_id}}/videos?{{api_key}}&language=en-US&page=1
