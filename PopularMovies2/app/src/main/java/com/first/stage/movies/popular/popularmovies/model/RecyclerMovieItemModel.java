package com.first.stage.movies.popular.popularmovies.model;

/**
 * Created by Rogalski on 2017-04-04.
 */

public class RecyclerMovieItemModel {

    private String image;
    private String title;
    private String overview;
    private double rate;
    private String date;

    public RecyclerMovieItemModel(String image, String title, String overview, double rate, String date) {
        this.image = image;
        this.title = title;
        this.overview = overview;
        this.rate = rate;
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
