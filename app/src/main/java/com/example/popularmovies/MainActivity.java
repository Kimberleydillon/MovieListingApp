package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

<<<<<<< HEAD
import com.example.popularmovies.Data.AppDatabase;

=======
>>>>>>> c066aa77a1a2ae2939c33b044b65a00805c6efd9
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
    private Switch sortSwitch;
    private TextView sortTitle;
<<<<<<< HEAD
    private AppDatabase appDb;
=======
>>>>>>> c066aa77a1a2ae2939c33b044b65a00805c6efd9


    static final String API_KEY = BuildConfig.ApiKey;
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String Popular = "Popular Movies";
    static final String HighRated = "Highest User Rating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = findViewById(R.id.progressBar);
        filmsList = findViewById(R.id.rv_films);
        sortSwitch = findViewById(R.id.sort_switch);
        sortTitle = findViewById(R.id.popular_movies);


<<<<<<< HEAD

        appDb = AppDatabase.getDatabase(getApplicationContext());


=======
>>>>>>> c066aa77a1a2ae2939c33b044b65a00805c6efd9
        adapter = new FilmAdapter(MainActivity.this, listener);
        filmsList.setAdapter(adapter);
        filmsList.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        filmsList.setHasFixedSize(true);


        //Onclick listener for sorting between popular and highest rating movies
        sortSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MovieDBClient service = retrofit.create(MovieDBClient.class);
                progressBar.setVisibility(View.VISIBLE);
                if (on) {
                    sortTitle.setText(HighRated);

                    Call<FilmResults> highRatingFilmsCall = service.listFilms("top_rated", API_KEY);
                    // async call uses enqueue method in Retrofit.
                    highRatingFilmsCall.enqueue(new Callback<FilmResults>() {
                        @Override
                        public void onResponse(Call<FilmResults> call, Response<FilmResults> response) {
                            //show results on screen.
                            List<Film> films = fetchResults(response);
                            adapter.setMovies(films);
                        }

                        @Override
                        public void onFailure(Call<FilmResults> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                } else {

                    sortTitle.setText(Popular);
                    Call<FilmResults> popFilmsCall = service.listFilms("popular", API_KEY);

                    // async call uses enqueue method in Retrofit.
                    popFilmsCall.enqueue(new Callback<FilmResults>() {
                        @Override
                        public void onResponse(Call<FilmResults> call, Response<FilmResults> response) {
                            //show results on screen.
                            List<Film> films = fetchResults(response);
                            adapter.setMovies(films);
                        }

                        @Override
                        public void onFailure(Call<FilmResults> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }
                progressBar.setVisibility(View.GONE);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDBClient service = retrofit.create(MovieDBClient.class);
        progressBar.setVisibility(View.VISIBLE);

        Call<FilmResults> popFilmsCall = service.listFilms("popular", API_KEY);


        // async call uses enqueue method in Retrofit.
        popFilmsCall.enqueue(new Callback<FilmResults>() {
            @Override
            public void onResponse(Call<FilmResults> call, Response<FilmResults> response) {
                //show results on screen.
                List<Film> films = fetchResults(response);
                adapter.setMovies(films);


            }

            @Override
            public void onFailure(Call<FilmResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    private List<Film> fetchResults(Response<FilmResults> response) {
        FilmResults topRatedFilms = response.body();
        return topRatedFilms.getResults();
    }

    FilmAdapter.Listener listener = new FilmAdapter.Listener() {
        @Override
        void onClick(Film movie) {
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