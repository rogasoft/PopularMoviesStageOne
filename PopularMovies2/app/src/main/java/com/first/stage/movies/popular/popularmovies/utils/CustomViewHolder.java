package com.first.stage.movies.popular.popularmovies.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.first.stage.movies.popular.popularmovies.DetailsActivity;
import com.first.stage.movies.popular.popularmovies.R;

/**
 * Created by Rogalski on 2017-04-04.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    private String title;
    private String poster;
    private String overview;
    private double rate;
    private String date;
    public ImageView movieImageView;

    public CustomViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        movieImageView = (ImageView) itemView.findViewById(R.id.movieImageView);
    }

    public void initData(String title, String poster, String overview, double rate, String date) {
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.rate = rate;
        this.date = date;
    }

    @Override
    public void onClick(View v) {
        Log.d("Position ", String.valueOf(getAdapterPosition()));
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_TITLE, title);
        intent.putExtra(DetailsActivity.EXTRA_POSTER, poster);
        intent.putExtra(DetailsActivity.EXTRA_OVERVIEW, overview);
        intent.putExtra(DetailsActivity.EXTRA_RATE, rate);
        intent.putExtra(DetailsActivity.EXTRA_DATE, date);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
