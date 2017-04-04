package com.first.stage.movies.popular.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.first.stage.movies.popular.popularmovies.model.TotalResult;
import com.first.stage.movies.popular.popularmovies.rest.RetrofitInterface;
import com.first.stage.movies.popular.popularmovies.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(layoutManager);

        getMovies();
    }

    private void getMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("api_key", Constants.apiKey);
        Call<TotalResult> call = retrofitInterface.getMovie(params);

        call.enqueue(new Callback<TotalResult>() {
            @Override
            public void onResponse(Call<TotalResult> call, Response<TotalResult> response) {
                TotalResult movieResult = response.body();
                int size = response.body().hashCode();
            }

            @Override
            public void onFailure(Call<TotalResult> call, Throwable t) {
                int size = t.hashCode();
            }
        });
    }
}
