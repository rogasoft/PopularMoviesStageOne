package com.first.stage.movies.popular.popularmovies;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.first.stage.movies.popular.popularmovies.adapter.MovieAdapter;
import com.first.stage.movies.popular.popularmovies.model.RecyclerMovieItemModel;
import com.first.stage.movies.popular.popularmovies.model.TotalResult;
import com.first.stage.movies.popular.popularmovies.rest.RetrofitInterface;
import com.first.stage.movies.popular.popularmovies.utils.Constants;
import com.first.stage.movies.popular.popularmovies.utils.NetworkHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MovieAdapter adapter;
    private List<RecyclerMovieItemModel> recyclerMovieItemModelList;
    private ProgressBar progressBar;
    private int screenOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        recyclerMovieItemModelList = new ArrayList<>();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        screenOrientation = getResources().getConfiguration().orientation;
        if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(context, 3);
        } else if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(context, 4);
        }
        recyclerView.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.INVISIBLE);
        if (NetworkHelper.isOnline(context)) {
            getPopularMovies();
        } else {
            Toast.makeText(this, R.string.network_disconnected, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortPopular:
                if (NetworkHelper.isOnline(context)) {
                    recyclerMovieItemModelList = new ArrayList<>();
                    getPopularMovies();
                } else {
                    Toast.makeText(this, R.string.network_disconnected, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sortTop:
                if (NetworkHelper.isOnline(context)) {
                    recyclerMovieItemModelList = new ArrayList<>();
                    getTopRatedMovies();
                } else {
                    Toast.makeText(this, R.string.network_disconnected, Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void getPopularMovies() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                Map<String, String> parameters = new HashMap<>();
                parameters.put("api_key", Constants.apiKey);
                Call<TotalResult> call = retrofitInterface.getMoviePopular(parameters);

                TotalResult movieResult = null;
                try {
                    movieResult = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < movieResult.getResults().size(); i++) {
                    RecyclerMovieItemModel recyclerMovieItemModel = new RecyclerMovieItemModel(
                            Constants.posterUrl + movieResult.getResults().get(i).getPosterPath(),
                            movieResult.getResults().get(i).getTitle(),
                            movieResult.getResults().get(i).getOverview(),
                            movieResult.getResults().get(i).getVoteAverage(),
                            movieResult.getResults().get(i).getReleaseDate());
                    recyclerMovieItemModelList.add(recyclerMovieItemModel);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter = new MovieAdapter(context, recyclerMovieItemModelList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }.execute();
    }

    private void getTopRatedMovies() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                Map<String, String> parameters = new HashMap<>();
                parameters.put("api_key", Constants.apiKey);
                Call<TotalResult> call = retrofitInterface.getMovieTop(parameters);

                TotalResult movieResult = null;
                try {
                    movieResult = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < movieResult.getResults().size(); i++) {
                    RecyclerMovieItemModel recyclerMovieItemModel = new RecyclerMovieItemModel(
                            Constants.posterUrl + movieResult.getResults().get(i).getPosterPath(),
                            movieResult.getResults().get(i).getTitle(),
                            movieResult.getResults().get(i).getOverview(),
                            movieResult.getResults().get(i).getVoteAverage(),
                            movieResult.getResults().get(i).getReleaseDate());
                    recyclerMovieItemModelList.add(recyclerMovieItemModel);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter = new MovieAdapter(context, recyclerMovieItemModelList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }.execute();
    }
}
