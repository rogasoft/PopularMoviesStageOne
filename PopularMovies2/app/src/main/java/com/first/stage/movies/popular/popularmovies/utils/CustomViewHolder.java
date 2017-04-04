package com.first.stage.movies.popular.popularmovies.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.first.stage.movies.popular.popularmovies.R;

/**
 * Created by Rogalski on 2017-04-04.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImageView;
    public TextView movieTextView;

    public CustomViewHolder(View itemView) {
        super(itemView);
        movieImageView = (ImageView) itemView.findViewById(R.id.movieImageView);
        movieTextView = (TextView) itemView.findViewById(R.id.movieTextView);
    }
}
