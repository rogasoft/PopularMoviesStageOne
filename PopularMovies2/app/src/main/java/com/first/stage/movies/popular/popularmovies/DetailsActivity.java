package com.first.stage.movies.popular.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Rogalski on 2017-04-01.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_POSTER = "extra_poster";
    public static final String EXTRA_OVERVIEW = "extra_overview";
    public static final String EXTRA_RATE = "extra_rate";
    public static final String EXTRA_DATE = "extra_date";
    private Context context;
    private ImageView detailsPosterImageView;
    private TextView detailsTitleTextView;
    private TextView detailsOverviewTextView;
    private TextView detailsReleaseDateTextView;
    private RatingBar detailsRatingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        context = this;
        initView();
    }

    private void initView() {
        detailsPosterImageView = (ImageView) findViewById(R.id.detailsPosterImageView);
        detailsTitleTextView = (TextView) findViewById(R.id.detailsTitleTextView);
        detailsOverviewTextView = (TextView) findViewById(R.id.detailsOverviewTextView);
        detailsReleaseDateTextView = (TextView) findViewById(R.id.detailsReleaseDateTextView);
        detailsRatingBar = (RatingBar) findViewById(R.id.detailsRatingBar);

        initData();
    }

    private void initData() {
        Picasso.with(context).load(getIntent().getStringExtra(EXTRA_POSTER)).into(detailsPosterImageView);
        detailsTitleTextView.setText(getIntent().getStringExtra(EXTRA_TITLE));
        detailsOverviewTextView.setText(getIntent().getStringExtra(EXTRA_OVERVIEW));
        detailsReleaseDateTextView.setText(getIntent().getStringExtra(EXTRA_DATE));
        detailsRatingBar.setRating((float) getIntent().getDoubleExtra(EXTRA_RATE, 0.0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
