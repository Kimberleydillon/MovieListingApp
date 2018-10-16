package com.example.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 100;

    private FilmAdapter fAdapter;
    private RecyclerView fFilmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fFilmsList = (RecyclerView) findViewById(R.id.rv_films);

        fFilmsList.setLayoutManager(new GridLayoutManager(this, 4));

        fFilmsList.setHasFixedSize(true);

        //Here is where you feed the data into the adapter.
        fAdapter = new FilmAdapter(NUM_LIST_ITEMS);

        fFilmsList.setAdapter(fAdapter);
    }
}
