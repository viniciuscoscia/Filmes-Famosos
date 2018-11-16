package com.example.viniciuscoscia.filmesfamosospt2.moviesAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static Retrofit retrofitInstance;
    private static final String baseUrl = "http://api.themoviedb.org/3/movie/";
    private MoviesRepository(){}

    public static Retrofit getRetrofitInstance() {
        if(retrofitInstance == null) {
            Gson gson = new GsonBuilder().setLenient().create();


            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }



}
