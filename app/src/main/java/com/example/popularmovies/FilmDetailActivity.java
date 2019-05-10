package com.example.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.steamcrafted.materialiconlib.MaterialIconView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmDetailActivity extends AppCompatActivity {
    TextView filmTitle;
    TextView filmRating;
    TextView filmSynopsis;
    TextView filmReleaseDate;
    MaterialIconView back;
    ImageView filmPoster;

    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String API_KEY = BuildConfig.ApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        String title = getIntent().getStringExtra("title");
        String rating = getIntent().getStringExtra("rating");
        String synopsis = getIntent().getStringExtra("synopsis");
        String releaseDate = getIntent().getStringExtra("releaseDate");
        String poster = getIntent().getStringExtra("posterPath");

        filmReleaseDate = findViewById(R.id.movie_release);
        filmTitle = findViewById(R.id.movie_title);
        filmRating = findViewById(R.id.movie_rating);
        filmSynopsis = findViewById(R.id.movie_description);
        back = findViewById(R.id.back);
        filmPoster = findViewById(R.id.movie_poster);

        GlideApp.with(this)
                .load("https://image.tmdb.org/t/p/w500" + poster)
                .override(800, 500)
                .placeholder(R.drawable.gunforhire)
                .into(filmPoster);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDBClient service = retrofit.create(MovieDBClient.class);
        //progressBar.setVisibility(View.VISIBLE);

        Call<FilmResults> filmReviewsCall = service.listFilms("reviews", API_KEY);
        filmReviewsCall.enqueue(new Callback<FilmResults>() {


                                    @Override
                                    public void onResponse(Call<FilmResults> call, Response<FilmResults> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<FilmResults> call, Throwable t) {

                                    }
                                });

        filmTitle.setText(title);
        filmRating.setText(rating);
        filmSynopsis.setText(synopsis);
        filmReleaseDate.setText(releaseDate);

        back.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                FilmDetailActivity.this.finish();
            }
        });
    }
}
