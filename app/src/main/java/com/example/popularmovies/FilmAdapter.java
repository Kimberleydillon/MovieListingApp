package com.example.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Adapter provides the data and responsible for creating the views for the individual
public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private static final String TAG = FilmAdapter.class.getSimpleName();
    //private int[] films;
    private List<Film> films;
//    private Film film;
    TextView title;
    ImageView posterView;
    FilmAdapter.Listener listener;
    LinearLayout moviesquare;
    private Context context;

    // private Film film;


    public FilmAdapter(Context context) {
        this.context = context;
         films = new ArrayList<>();
    }

    public List<Film> getMovies() {
        return films;
    }

    public void setMovies(List<Film> filmResults) {
        this.films = filmResults;
    }

    /**
     * Constructor for FilmAdapter that accepts a list of film objects to display and the specification
     * for the filmClickListener.
     */


    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.film_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        FilmViewHolder viewHolder = new FilmViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = films.get(position);


        Log.d(TAG, "#" + position);
        FilmViewHolder filmView = holder;
        filmView.setItems(film);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }


    class FilmViewHolder extends RecyclerView.ViewHolder {


        public FilmViewHolder(View itemView) {
            super(itemView);

            moviesquare = itemView.findViewById(R.id.movie);
            //posterView = (ImageView) itemView.findViewById(R.id.iv_film_item_view);
            title = itemView.findViewById(R.id.tv_film_title);

        }

        void setItems(final Film film) {
            title.setText(film.getTitle());
            //posterView.setImageDrawable(film.getPoster());
            moviesquare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(film);
                }
            });

        }
    }

    abstract static class Listener {
        abstract void onClick(Film movie);
    }
}
