package com.example.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.Data.AppDatabase;

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
    MaterialIconView heart;
    MaterialIconView heartOutline;
    ImageView filmPoster;
    private AppDatabase appDb;

    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static final String API_KEY = BuildConfig.ApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        final String title = getIntent().getStringExtra("title");
        final String rating = getIntent().getStringExtra("rating");
        final String synopsis = getIntent().getStringExtra("synopsis");
        final String releaseDate = getIntent().getStringExtra("releaseDate");
        final String poster = getIntent().getStringExtra("posterPath");
        String movieId = getIntent().getStringExtra("id");

        final int movieIdInt = Integer.parseInt(movieId);


        filmReleaseDate = findViewById(R.id.movie_release);
        filmTitle = findViewById(R.id.movie_title);
        filmRating = findViewById(R.id.movie_rating);
        filmSynopsis = findViewById(R.id.movie_description);
        back = findViewById(R.id.back);
        heart = findViewById(R.id.heart);
        heartOutline = findViewById(R.id.heart_outline);
        filmPoster = findViewById(R.id.movie_poster);
        appDb = AppDatabase.getDatabase(getApplicationContext());


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

        heartOutline.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                final com.example.popularmovies.Data.Film dbTestFilm = makeATestFilm(movieIdInt, title, synopsis, poster, releaseDate, true);
                Log.d("DBTest Film", dbTestFilm.toString());
                heartOutline.setVisibility(View.GONE);
                heart.setVisibility(View.VISIBLE);
                appDb.filmDao().insert(dbTestFilm);
            }
        });

        heart.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                heart.setVisibility(View.GONE);
                heartOutline.setVisibility(View.VISIBLE);
                //code to delete entry from db or just mark favourite as false.
                //search db for favourite true will be the new query, then when inserting check if entry exists and mark entry as true.

            }
        });
    }


    public Film makeATestFilm(int filmId, String filmTitle, String filmOverview, String filmposter, String releaseDate, Boolean Favourite) {
        Film testFilm = new Film();
        testFilm.setId(filmId);
        testFilm.setTitle(filmTitle);
        testFilm.setOverview(filmOverview);
        testFilm.setPosterPath(filmposter);
        testFilm.setReleaseDate(releaseDate);
        testFilm.setFavourite(Favourite);

        return testFilm;
    }

}
