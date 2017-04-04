package com.first.stage.movies.popular.popularmovies.rest;

import com.first.stage.movies.popular.popularmovies.model.TotalResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Rogalski on 2017-04-01.
 */

public interface RetrofitInterface {

    @GET("3/movie/popular")
    Call<TotalResult> getMovie(@QueryMap Map<String, String> params);
}
