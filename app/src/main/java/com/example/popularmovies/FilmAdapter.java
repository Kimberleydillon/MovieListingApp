package com.example.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private static final String TAG = FilmAdapter.class.getSimpleName();
    private List<Film> films;
    TextView title;
    ImageView posterView;
    LinearLayout moviesquare;
    private Context context;
    FilmAdapter.Listener listener;

    public FilmAdapter(Context context, FilmAdapter.Listener listener) {
        this.context = context;
        films = new ArrayList<>();
        this.listener = listener;
    }


    public void setMovies(List<Film> filmResults) {
        this.films = filmResults;
        notifyDataSetChanged();
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {


        public FilmViewHolder(View itemView) {
            super(itemView);

            moviesquare = itemView.findViewById(R.id.movie);
            posterView = itemView.findViewById(R.id.iv_film_item_view);
            title = itemView.findViewById(R.id.tv_film_title);
        }

        void setItems(final Film film) {
            title.setText(film.getTitle());

            GlideApp.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + film.getPosterPath())
                    .placeholder(R.drawable.gunforhire)
                    .override(800, 300)
                    .into(posterView);

            moviesquare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(film);
                }
            });

        }
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
    public void onBindViewHolder(FilmViewHolder holder, final int position) {
        Film film = films.get(position);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(films.get(position));
            }
        });


        FilmViewHolder filmView = holder;
        filmView.setItems(film);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }


    abstract static class Listener {
        abstract void onClick(Film movie);
    }


}
