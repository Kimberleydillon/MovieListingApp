package com.example.popularmovies.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.popularmovies.BuildConfig;
import com.example.popularmovies.Data.AppDatabase;
import com.example.popularmovies.R;
import com.example.popularmovies.model.FilmResults;
import com.example.popularmovies.service.MovieDBClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private FilmAdapter adapter;
    private RecyclerView filmsList;
    private ProgressBar progressBar;
    private TextView testdb;

    private AppDatabase appDb;

    static final String API_KEY = BuildConfig.ApiKey;
    static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = findViewById(R.id.progressBar);
        filmsList = findViewById(R.id.rv_films);
        testdb = findViewById(R.id.testDatabase);


        appDb = AppDatabase.getDatabase(getApplicationContext());


        adapter = new FilmAdapter(MainActivity.this, listener);
        filmsList.setAdapter(adapter);
        filmsList.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        filmsList.setHasFixedSize(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_ranking:
                popularFilmCall("popular");
                return true;
            case R.id.highest_user_ranking:
                popularFilmCall("top_rated");
                return true;
            case R.id.favourites:
                List<com.example.popularmovies.Data.Film> favourites = (List<com.example.popularmovies.Data.Film>) appDb.filmDao().getAllFilms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<com.example.popularmovies.Data.Film> fetchResults(Response<FilmResults> response) {
        FilmResults topRatedFilms = response.body();
        return topRatedFilms.getResults();
    }

    private void popularFilmCall(String sort) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieDBClient service = retrofit.create(MovieDBClient.class);
        progressBar.setVisibility(View.VISIBLE);
        Call<FilmResults> highRatingFilmsCall = service.listFilms(sort, API_KEY);
        // async call uses enqueue method in Retrofit.
        highRatingFilmsCall.enqueue(new Callback<FilmResults>() {
            @Override
            public void onResponse(Call<FilmResults> call, Response<FilmResults> response) {
                //show results on screen.
                List<com.example.popularmovies.Data.Film> films = fetchResults(response);
                adapter.setMovies(films);
            }

            @Override
            public void onFailure(Call<FilmResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    FilmAdapter.Listener listener = new FilmAdapter.Listener() {
        @Override
        void onClick(com.example.popularmovies.Data.Film movie) {
            Intent filmDetail = new Intent(MainActivity.this, FilmDetailActivity.class);
            filmDetail.putExtra("title", movie.getTitle());
            filmDetail.putExtra("rating", movie.getVoteAverage().toString());
            filmDetail.putExtra("synopsis", movie.getOverview());
            filmDetail.putExtra("releaseDate", movie.getReleaseDate());
            filmDetail.putExtra("posterPath", movie.getPosterPath());
            filmDetail.putExtra("id", movie.getId().toString());
            MainActivity.this.startActivity(filmDetail);
        }
    };
}