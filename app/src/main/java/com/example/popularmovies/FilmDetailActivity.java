package com.example.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FilmDetailActivity extends AppCompatActivity {
    TextView filmTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        String film = getIntent().getStringExtra("filmTitle");
        filmTV = findViewById(R.id.movie_title);
        filmTV.setText(film);
    }
}
