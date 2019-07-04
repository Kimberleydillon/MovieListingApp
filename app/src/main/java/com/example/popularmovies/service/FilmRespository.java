package com.example.popularmovies.service;

import com.example.popularmovies.Data.FilmDao;

import java.util.concurrent.Executor;

public class FilmRespository {

    // Member Variables
    private FilmDao filmDao;

    // private LiveData<List<Film>> AllFilms;
    private final MovieDBClient movieDBClient;
    private final Executor executor;

    public FilmRespository(FilmDao filmDao, MovieDBClient movieDBClient, Executor executor) {
        this.filmDao = filmDao;
        this.movieDBClient = movieDBClient;
        this.executor = executor;
    }

    public void getfavfilms(String movieID){

    }

    private void refreshFavFilms( final String filmId ){
        executor.execute();

        boolean favMovieExists = (filmDao.());



        ;

    }



//    // Constructor that gets handle to the database and initializes the member variables.
//
//    FilmRespository(Application application){
//        AppDatabase database = AppDatabase.getDatabase(application);
//        filmDao = database.filmDao();
//        AllFilms = filmDao.getAllFilms();
//    }
//
//    LiveData<List<Film>> getAllFilms() {
//        return AllFilms;
//    }
//
//    public void insert(Film film){
//        new insertAsyncTask(filmDao).execute(film);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<Film, Void, Void> {
//
//        private FilmDao mAsyncTaskDao;
//
//        insertAsyncTask(FilmDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final Film... params) {
//            mAsyncTaskDao.insert(params[0]);
//            return null;
//        }
//    }
}
