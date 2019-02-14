package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    //private int[] posters = {R.drawable.gun_for_hire_poster, R.drawable.gun_for_hire_poster, R.drawable.gun_for_hire_poster, R.drawable.gun_for_hire_poster, R.drawable.gun_for_hire_poster, R.drawable.gun_for_hire_poster};

    //private static final int NUM_LIST_ITEMS = 100;

    private FilmAdapter fAdapter;
    private RecyclerView fFilmsList;
    private ProgressBar progressBar;
    private TextView responseView;
    //static final String API_KEY = "dc31184cbd225288f0a80af2ed71c703";

    static final String API_KEY = BuildConfig.ApiKey;
    static final String BASE_URL = "https://api.themoviedb.org/3/";


    // Most popular movies
    ///discover/movie? sort_by=popularity.desc
    // Highest rated Movies
    ///discover/movie? sort_by=vote_average.desc
    //or
    ///discover/movie/?sort_by=vote_average.desc

    //request url base is
    //https://api.themoviedb.org/3/movie/76341?api_key={api_key}

    // button for sorting one does an async for popular "Most Popular" sort_by=popularity.desc and the other by "user rating" sort_by=vote_average.desc


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        responseView = findViewById(R.id.responseViewText);

        fFilmsList =  findViewById(R.id.rv_films);
//
//        fFilmsList.setLayoutManager(new GridLayoutManager(this, 4));
//
//        fFilmsList.setHasFixedSize(true);

        //{{BASE_URL}}/discover/movie?api_key={{API_KEY}}&sort_by=popularity.desc&page=1


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDBClient service = retrofit.create(MovieDBClient.class);

        Call<List<Film>> popFilmsCall = service.listFilms("dc31184cbd225288f0a80af2ed71c703","popularity.desc","1");
        Log.v("MainActivity", "This is the call being made: " + popFilmsCall );

        // async call uses enqueue method in Retrofit.
        popFilmsCall.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                //show results on screen.
                Log.v("MainActivity", "Response" + response.toString());
                Log.v("MainActivity", "Here is getting hit!" );
                progressBar.setVisibility(View.VISIBLE);
                List<Film> films = response.body();


                //Films is null
                Log.v("MainActivity", "Films: " + films);
                Log.v("MainActivity", "LOGS" + films.size());



                fAdapter = new FilmAdapter(MainActivity.this);

                fFilmsList.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));

                fFilmsList.setHasFixedSize(true);

                fFilmsList.setAdapter(fAdapter);

            }


            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                //show error on screen
                responseView.setText("Sorry an Error Occured");

            }
        });
    }


    FilmAdapter.Listener listener = new FilmAdapter.Listener() {
        @Override
        void onClick(Film movie) {
            Intent filmDetail = new Intent(MainActivity.this, FilmDetailActivity.class);
            filmDetail.putExtra("filmTitle", movie.getTitle());
            MainActivity.this.startActivity(filmDetail);
        }
    };
}