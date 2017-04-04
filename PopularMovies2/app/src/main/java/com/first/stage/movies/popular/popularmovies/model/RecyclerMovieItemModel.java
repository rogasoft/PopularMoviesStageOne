package com.first.stage.movies.popular.popularmovies.model;

/**
 * Created by Rogalski on 2017-04-04.
 */

public class RecyclerMovieItemModel {

    private String image;
    private String title;

    public RecyclerMovieItemModel(String image, String title) {
        this.image = image;
        this.title = title;
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
}
