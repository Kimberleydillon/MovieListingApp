package com.example.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.popularmovies.Data.Film;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Film>> films;

     public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
