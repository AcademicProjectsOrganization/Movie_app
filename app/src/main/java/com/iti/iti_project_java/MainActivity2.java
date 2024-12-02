package com.iti.iti_project_java;

import static com.iti.iti_project_java.MyItemAdapter.IMAGE_BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iti.iti_project_java.databinding.ActivityMain2Binding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity2 extends AppCompatActivity implements MyItemAdapter.productListOnCLickListener{

    private MyItemAdapter moviesAdapter;
    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie 1", "1-2-2017"));
        movies.add(new Movie("Movie 2", "1-3-2017"));

        moviesAdapter = new MyItemAdapter(movies, this);
        binding.itemsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService service = retrofit.create(MovieService.class);
        Call<MoviesModel> call = service.getMoviesModel();
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()) {
                    MoviesModel moviesModel=response.body();
                    updateData(moviesModel.getMovies());
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        binding.itemsList.setAdapter(moviesAdapter);


    }
    public void updateData(ArrayList<Movie> movies) {
        moviesAdapter.setData(movies);
    }

    @Override
    public void onItemClick(Movie product) {
        Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("details" , product.getOverview());
        bundle.putString("release_date",product.getRelease_date());
        bundle.putString("img_url",(product.getPoster_path()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

}