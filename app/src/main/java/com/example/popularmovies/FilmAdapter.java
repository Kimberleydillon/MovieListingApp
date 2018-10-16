package com.example.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// Adapter provides the data and responsible for creating the views for the individual
public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private static final String TAG = FilmAdapter.class.getSimpleName();
    private int films;

    /**
     * Constructor for FilmAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfFilms Number of items to display in list
     */

    public FilmAdapter(int numberOfFilms) {
        films = numberOfFilms;
    }

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
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return films;
    }


    class FilmViewHolder extends RecyclerView.ViewHolder {

//        ImageView filmListItemView;
        TextView filmListItemView;

        public FilmViewHolder(View itemView) {
            super(itemView);

            filmListItemView = (TextView) itemView.findViewById(R.id.tv_film_item_view);
        }
        void bind (int listIndex) {
            filmListItemView.setText(String.valueOf(listIndex));
        }
    }
}
