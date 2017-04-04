package com.first.stage.movies.popular.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.first.stage.movies.popular.popularmovies.R;
import com.first.stage.movies.popular.popularmovies.model.RecyclerMovieItemModel;
import com.first.stage.movies.popular.popularmovies.utils.CustomViewHolder;

import java.util.List;

/**
 * Created by Rogalski on 2017-04-04.
 */

public class MovieAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<RecyclerMovieItemModel> recyclerMovieItemModelList;

    public MovieAdapter(Context context, List<RecyclerMovieItemModel> recyclerMovieItemModelList) {
        this.context = context;
        this.recyclerMovieItemModelList = recyclerMovieItemModelList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Glide.with(context).load(recyclerMovieItemModelList.get(position).getImage()).into(holder.movieImageView);
        holder.movieTextView.setText(recyclerMovieItemModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recyclerMovieItemModelList.size();
    }
}
